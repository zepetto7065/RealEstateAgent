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

    @Builder(builderMethodName = "forJunitTest")
    public UserDto(String username, String password, String nickname) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
    }
}
