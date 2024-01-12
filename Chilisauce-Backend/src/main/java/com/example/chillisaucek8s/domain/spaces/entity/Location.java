package com.example.chillisaucek8s.domain.spaces.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;



import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Data
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "locationType")
public abstract class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty
    @Column(nullable = false)
    private String locationName;
    @NotEmpty
    @Column(nullable = false)
    private String x;
    @NotEmpty
    @Column(nullable = false)
    private String y;

    @OneToMany(mappedBy = "location", cascade = CascadeType.ALL)
    private List<UserLocation> userLocations = new ArrayList<>();


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "space_id")
    private Space space;

    public Location(Long id, String locationName, String x, String y, Space space) {
        this.id = id;
        this.locationName = locationName;
        this.x = x;
        this.y = y;
        this.space = space;
    }

    public void update(Space space) {
        this.space = space;
    }

    public Location(String locationName, String x, String y) {
        this.locationName = locationName;
        this.x = x;
        this.y = y;
    }

    public Location(String locationName, String x, String y, Space space) {
        this.locationName = locationName;
        this.x = x;
        this.y = y;
        this.space = space;
    }

    public Location(Long id, String locationName, String x, String y) {
        this.id = id;
        this.locationName = locationName;
        this.x = x;
        this.y = y;
    }

    public abstract boolean isBox();
    public abstract boolean isMultiBox();
    public abstract boolean isMr();
}
