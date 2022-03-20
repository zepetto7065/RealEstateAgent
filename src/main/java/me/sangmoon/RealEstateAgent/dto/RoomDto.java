package me.sangmoon.RealEstateAgent.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import me.sangmoon.RealEstateAgent.domain.room.RoomType;

@Getter
@Builder
@AllArgsConstructor
public class RoomDto {
    private Long roomId;
    private String payType;
    private RoomType roomType;
    private String address;
    private Long deposit;
    private Long rentPrice;

    private Long userId;
}
