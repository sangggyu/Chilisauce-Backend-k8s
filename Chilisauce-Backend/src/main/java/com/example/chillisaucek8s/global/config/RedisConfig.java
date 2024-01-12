//package com.example.chillisaucek8s.global.config;
//
//
//import com.example.chillisaucek8s.global.security.GrantedAuthorityDeserializer;
//import com.example.chillisaucek8s.global.security.GrantedAuthoritySerializer;
//import com.example.chillisaucek8s.global.security.UserDetailsImpl;
//import com.example.chillisaucek8s.domain.spaces.dto.response.FloorResponseDto;
//import com.example.chillisaucek8s.domain.spaces.dto.response.SpaceResponseDto;
//import com.example.chillisaucek8s.domain.users.dto.response.UserListResponseDto;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.databind.SerializationFeature;
//import com.fasterxml.jackson.databind.module.SimpleModule;
//import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.cache.CacheManager;
//import org.springframework.cache.annotation.EnableCaching;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.redis.cache.RedisCacheConfiguration;
//import org.springframework.data.redis.cache.RedisCacheManager;
//import org.springframework.data.redis.connection.RedisConnectionFactory;
//import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
//import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
//import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
//import org.springframework.data.redis.serializer.RedisSerializationContext;
//import org.springframework.security.core.GrantedAuthority;
//
//import java.time.Duration;
//import java.util.List;
//
//@Configuration
//@EnableCaching
//public class RedisConfig {
//
//    @Value("${spring.data.redis.host}")
//    private String host;
//
//    @Value("${spring.data.redis.port}")
//    private int port;
//
////    @Value("${spring.redis.password}")
////    private String password;
//
//    @Bean
//    public RedisConnectionFactory redisConnectionFactory() {
//        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
//        redisStandaloneConfiguration.setHostName(host);
//        redisStandaloneConfiguration.setPort(port);
////        redisStandaloneConfiguration.setPassword(password);
//        return new LettuceConnectionFactory(redisStandaloneConfiguration);
//    }
//
//
//    @Bean
//    public CacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
//        ObjectMapper objectMapper = new ObjectMapper();
//        objectMapper.registerModule(new JavaTimeModule());
//        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
//
//        Jackson2JsonRedisSerializer<List<FloorResponseDto>> floorSerializer = new Jackson2JsonRedisSerializer<>
//                (objectMapper.getTypeFactory().constructCollectionType(List.class, FloorResponseDto.class));
//        floorSerializer.setObjectMapper(objectMapper);
//
//        Jackson2JsonRedisSerializer<List<SpaceResponseDto>> spaceSerializer = new Jackson2JsonRedisSerializer<>
//                (objectMapper.getTypeFactory().constructCollectionType(List.class, SpaceResponseDto.class));
//        spaceSerializer.setObjectMapper(objectMapper);
//
//        Jackson2JsonRedisSerializer<SpaceResponseDto> spaceListSerializer = new Jackson2JsonRedisSerializer<>(SpaceResponseDto.class);
//        spaceListSerializer.setObjectMapper(objectMapper);
//
//        Jackson2JsonRedisSerializer<UserListResponseDto> userSerializer = new Jackson2JsonRedisSerializer<>
//                (objectMapper.getTypeFactory().constructType(UserListResponseDto.class));
//        userSerializer.setObjectMapper(objectMapper);
//
//
//        Jackson2JsonRedisSerializer<UserDetailsImpl> userDetailsSerializer = new Jackson2JsonRedisSerializer<>
//                (objectMapper.getTypeFactory().constructType(UserDetailsImpl.class));
//        userDetailsSerializer.setObjectMapper(objectMapper);
//        SimpleModule grantedAuthorityModule = new SimpleModule();
//        grantedAuthorityModule.addSerializer(GrantedAuthority.class, new GrantedAuthoritySerializer());
//        grantedAuthorityModule.addDeserializer(GrantedAuthority.class, new GrantedAuthorityDeserializer());
//        objectMapper.registerModule(grantedAuthorityModule);
//
//        RedisCacheManager.RedisCacheManagerBuilder builder = RedisCacheManager.builder(redisConnectionFactory);
//
//        builder.withCacheConfiguration("FloorResponseDtoList",
//                RedisCacheConfiguration.defaultCacheConfig()
//                        .entryTtl(Duration.ofMinutes(60))
//                        .disableCachingNullValues()
//                        .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(floorSerializer))
//        );
//
//        builder.withCacheConfiguration("SpaceResponseDtoList",
//                RedisCacheConfiguration.defaultCacheConfig()
//                        .entryTtl(Duration.ofMinutes(60))
//                        .disableCachingNullValues()
//                        .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(spaceSerializer))
//        );
//
//        builder.withCacheConfiguration("SpaceResponseDto",
//                RedisCacheConfiguration.defaultCacheConfig()
//                        .entryTtl(Duration.ofMinutes(60))
//                        .disableCachingNullValues()
//                        .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(spaceListSerializer))
//        );
//
//        builder.withCacheConfiguration("UserResponseDtoList",
//                RedisCacheConfiguration.defaultCacheConfig()
//                        .entryTtl(Duration.ofMinutes(60))
//                        .disableCachingNullValues()
//                        .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(userSerializer))
//        );
//
//        builder.withCacheConfiguration("UserDetails",
//                RedisCacheConfiguration.defaultCacheConfig()
//                        .entryTtl(Duration.ofMinutes(120))
//                        .disableCachingNullValues()
//                        .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(userDetailsSerializer))
//        );
//
//        return builder.build();
//    }
//
//}