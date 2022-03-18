package me.sangmoon.RealEstateAgent.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.sangmoon.RealEstateAgent.domain.room.Room;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "USER")
@Getter
@NoArgsConstructor
public class User {

    @Id
    @Column(name = "USER_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String username;

    private String password;

    private String nickname;

    private boolean activated;

    @OneToMany(mappedBy = "user")
    private List<Room> rooms = new ArrayList<>();

    @Builder
    public User(Long userId, String username, String password, String nickname, boolean activated, Set<Authority> authorities, List<Room> rooms) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.activated = activated;
        this.authorities = authorities;
        this.rooms = rooms;
    }

    @ManyToMany
    @JoinTable(
            name = "user_authority",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "authority_name", referencedColumnName = "authority_name")})
    private Set<Authority> authorities;
}