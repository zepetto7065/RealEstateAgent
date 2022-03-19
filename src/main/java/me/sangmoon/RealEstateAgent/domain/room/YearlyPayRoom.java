package me.sangmoon.RealEstateAgent.domain.room;

import lombok.*;
import me.sangmoon.RealEstateAgent.domain.User;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@Getter @Setter
@DiscriminatorValue("Y")
@NoArgsConstructor(access= AccessLevel.PROTECTED)
public class YearlyPayRoom extends Room{
    private long deposit;

    @Builder
    public YearlyPayRoom(Long id, User user, String address, RoomType roomType, long deposit) {
        super(id, user, address, roomType);
        this.deposit = deposit;
    }
}
