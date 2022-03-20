package me.sangmoon.RealEstateAgent.domain.room;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.sangmoon.RealEstateAgent.domain.User;

import javax.persistence.*;

@Entity
@DiscriminatorColumn(name = "PAY_TYPE", discriminatorType = DiscriminatorType.STRING)
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class Room {

    @Id
    @GeneratedValue
    @Column(name = "ROOM_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User user;

    private String address;

    @Enumerated(EnumType.STRING)
    @Column(name = "ROOM_TYPE")
    private RoomType roomType; //주문 상태

    @Column(name = "PAY_TYPE", insertable = false, updatable = false)
    private String payType;

    @Setter
    private long deposit;

    public Room(Long id, User user, String address, RoomType roomType, long deposit) {
        this.id = id;
        this.user = user;
        this.address = address;
        this.roomType = roomType;
        this.deposit = deposit;
    }
}
