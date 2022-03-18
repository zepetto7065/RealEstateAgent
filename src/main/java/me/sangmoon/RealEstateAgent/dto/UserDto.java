package me.sangmoon.RealEstateAgent.dto;


import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
@Builder
public class UserDto {

    @NotNull
    private String username;

    @NotNull
    private String password;

    @NotNull
    private String nickname;
}
