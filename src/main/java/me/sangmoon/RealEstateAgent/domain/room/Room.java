package me.sangmoon.RealEstateAgent.domain.room;

import lombok.*;
import me.sangmoon.RealEstateAgent.domain.User;
import me.sangmoon.RealEstateAgent.dto.RoomDto;

import javax.persistence.*;
import java.util.Optional;

@Entity
@DiscriminatorColumn(name = "PAY_TYPE", discriminatorType = DiscriminatorType.STRING)
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class Room {

    @Id
    @GeneratedValue
    @Column(name="ROOM_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User user;

    private String address;

    @Enumerated(EnumType.STRING)
    private RoomType roomType; //주문 상태

    public Room(Long id, User user,String address, RoomType roomType) {
        this.id = id;
        this.user = user;
        this.address = address;
        this.roomType = roomType;
    }
}
