package me.sangmoon.RealEstateAgent.dto;

import lombok.Builder;
import lombok.Getter;
import me.sangmoon.RealEstateAgent.domain.room.RoomType;

@Getter
public class SearchDto {

    private RoomType roomType;
    private String payType;
    private long minPrice;
    private long maxPrice;

    @Builder(builderMethodName = "forJunitTest")
    public SearchDto(RoomType roomType, String payType, long minPrice, long maxPrice) {
        this.roomType = roomType;
        this.payType = payType;
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
    }
}
