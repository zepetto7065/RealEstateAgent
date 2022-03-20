package me.sangmoon.RealEstateAgent.dto;

import lombok.Builder;
import lombok.Getter;
import me.sangmoon.RealEstateAgent.domain.room.RoomType;

@Getter
public class SearchDto {

    private RoomType roomType;
    private String payType;
    private Long minDeposit;
    private Long maxDeposit;

    @Builder(builderMethodName = "forJunitTest")
    public SearchDto(RoomType roomType, String payType, long minDeposit, long maxDeposit) {
        this.roomType = roomType;
        this.payType = payType;
        this.minDeposit = minDeposit;
        this.maxDeposit = maxDeposit;
    }
}
