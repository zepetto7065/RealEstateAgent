package me.sangmoon.RealEstateAgent.domain.room;

import lombok.*;
import me.sangmoon.RealEstateAgent.domain.User;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@Getter @Setter
@DiscriminatorValue("M")
@NoArgsConstructor(access= AccessLevel.PROTECTED)
public class MonthlyPayRoom extends Room {
    private long rentPrice;

    @Builder
    public MonthlyPayRoom(Long id, User user, String address, RoomType roomType, long deposit, long rentPrice) {
        super(id, user,address, roomType,deposit);
        this.rentPrice = rentPrice;
    }
}
