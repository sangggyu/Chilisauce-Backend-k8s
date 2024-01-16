package com.example.chillisaucek8s.domain.users.service;

import com.example.chillisaucek8s.domain.reservations.entity.Reservation;
import com.example.chillisaucek8s.domain.reservations.respository.ReservationRepository;
import com.example.chillisaucek8s.domain.schedules.entity.Schedule;
import com.example.chillisaucek8s.domain.schedules.repository.ScheduleRepository;
import com.example.chillisaucek8s.domain.spaces.entity.UserLocation;
import com.example.chillisaucek8s.domain.spaces.repository.UserLocationRepository;
import com.example.chillisaucek8s.domain.users.dto.request.RoleDeptUpdateRequestDto;
import com.example.chillisaucek8s.domain.users.dto.response.UserDetailResponseDto;
import com.example.chillisaucek8s.domain.users.dto.response.UserListResponseDto;
import com.example.chillisaucek8s.domain.users.entity.User;
import com.example.chillisaucek8s.domain.users.entity.UserRoleEnum;
import com.example.chillisaucek8s.domain.users.exception.UserErrorCode;
import com.example.chillisaucek8s.domain.users.exception.UserException;
import com.example.chillisaucek8s.domain.users.repository.UserRepository;
import com.example.chillisaucek8s.global.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AdminService {

    private final UserRepository userRepository;
    private final ScheduleRepository scheduleRepository;
    private final ReservationRepository reservationRepository;
    private final UserLocationRepository userLocationRepository;
    private final CacheManager cacheManager;

    /* 사원 목록 전체 조회 */
    @Transactional(readOnly = true)
    @Cacheable(value = "UserResponseDtoList", key = "#userDetails.user.companies.companyName")
    public UserListResponseDto getAllUsers(UserDetailsImpl userDetails) {
        User user = userDetails.getUser();

        if (!user.getRole().equals(UserRoleEnum.ADMIN) && !user.getRole().equals(UserRoleEnum.MANAGER)) {
            throw new UserException(UserErrorCode.NOT_HAVE_PERMISSION);
        }
        List<User> allList = userRepository.findAllByCompanies_CompanyName(user.getCompanies().getCompanyName());
        return new UserListResponseDto(allList.stream().map(UserDetailResponseDto::new).toList());
    }

    /* 사원 선택 조회 */
    @Transactional(readOnly = true)
    public UserDetailResponseDto getUsers(Long userId, UserDetailsImpl userDetails) {
        User user = userDetails.getUser();
        if (!user.getRole().equals(UserRoleEnum.ADMIN) && !user.getRole().equals(UserRoleEnum.MANAGER)) {
            throw new UserException(UserErrorCode.NOT_HAVE_PERMISSION);
        }
        User getUser = findOneUser(userId, user);

        return new UserDetailResponseDto(getUser);
    }

    /* 사원 권한 수정 */
    @Transactional
    public UserDetailResponseDto editUser(Long userId, UserDetailsImpl userDetails, RoleDeptUpdateRequestDto requestDto) {
        User user = userDetails.getUser();
        if (!user.getRole().equals(UserRoleEnum.ADMIN) && !user.getRole().equals(UserRoleEnum.MANAGER)) {
            throw new UserException(UserErrorCode.NOT_HAVE_PERMISSION);
        }
        User getUser = findOneUser(userId, user);
        String userEmail = getUser.getEmail();
        evictCacheByEmail(userEmail);   //Evicting user from cache
        evictCacheByCompanyName(userDetails);   //Evicting userList from cache

        if (requestDto.getRole().equals(UserRoleEnum.ADMIN)) {
            throw new UserException(UserErrorCode.UNABLE_MODIFY_PERMISSION_FOR_ADMIN);
        }

        if (requestDto.isUpdateRole() && getUser.getRole().equals(UserRoleEnum.ADMIN)) {
            throw new UserException(UserErrorCode.DO_NOT_CHANGED_PERMISSION);
        }

        getUser.update(requestDto);
        userRepository.save(getUser);
        return new UserDetailResponseDto(getUser);
    }

    private User findOneUser(Long userId, User user) {
        return userRepository.findByIdAndCompanies_CompanyName(userId, user.getCompanies().getCompanyName()).orElseThrow(
                () -> new UserException(UserErrorCode.USER_NOT_FOUND));
    }

    /* 사원 삭제 */
    @Transactional
    public String deleteUser(Long userId, UserDetailsImpl userDetails) {
        User user = userDetails.getUser();
        if (!user.getRole().equals(UserRoleEnum.ADMIN)) {
            throw new UserException(UserErrorCode.NOT_HAVE_PERMISSION);
        }
        //퇴사처리 하려는 사원 찾기
        User getUser = userRepository.findById(userId).orElseThrow(
                () -> new UserException(UserErrorCode.USER_NOT_FOUND));

        String userEmail = getUser.getEmail();
        evictCacheByEmail(userEmail);   //Evicting user from cache
        evictCacheByCompanyName(userDetails);   //Evicting userList from cache

        //사원의 스케줄 삭제
        List<Schedule> schedules = scheduleRepository.findAllByUserId(userId);
        scheduleRepository.deleteAll(schedules);

        //사원의 예약 삭제
        List<Reservation> reservations = reservationRepository.findAllByUserId(userId);
        reservationRepository.deleteAll(reservations);

        //사원의 로케이션 삭제
        Optional<UserLocation> userLocation = userLocationRepository.findByUserId(userId);
        userLocation.ifPresent(userLocationRepository::delete);

        //회원 삭제
        userRepository.delete(getUser);
        return "사원 삭제 성공";
    }


    /* 캐시 삭제용 메서드 */
    @CacheEvict(cacheNames = "UserDetails", key = "#email")
    public void evictCacheByEmail(String email) {
        log.info("Evicting user from cache={}", email);
        Cache userDetailsCache = cacheManager.getCache("UserDetails");
        if (userDetailsCache != null) {
            userDetailsCache.evict(email);
        }
    }

    /* 캐시 삭제용 메서드 */
    @CacheEvict(cacheNames = "UserResponseDtoList", key = "#userDetails.user.companies.companyName")
    public void evictCacheByCompanyName(UserDetailsImpl userDetails) {
        log.info("Evicting userList from cache={}", userDetails.getUser().getCompanies().getCompanyName());
        Cache userListCache = cacheManager.getCache("UserResponseDtoList");
        if (userListCache != null) {
            userListCache.evict(userDetails.getUser().getCompanies().getCompanyName());
        }
    }

}
