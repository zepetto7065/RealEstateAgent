package me.sangmoon.RealEstateAgent.domain.room;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.sangmoon.RealEstateAgent.domain.User;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@Getter
@DiscriminatorValue("M")
@NoArgsConstructor(access= AccessLevel.PROTECTED)
public class MontlyPayRoom extends Room {
    private long rentPrice;
    private long deposit;

    @Builder
    public MontlyPayRoom(Long id, User user,String address, RoomType roomType, long rentPrice, long deposit) {
        super(id, user,address, roomType);
        this.rentPrice = rentPrice;
        this.deposit = deposit;
    }
}
