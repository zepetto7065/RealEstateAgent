package me.sangmoon.RealEstateAgent.dto;

import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Builder
public class LoginDto {

    @NotNull
    private String username;

    @NotNull
    private String password;
}
