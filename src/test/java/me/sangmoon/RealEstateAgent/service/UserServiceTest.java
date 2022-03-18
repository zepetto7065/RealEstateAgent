package me.sangmoon.RealEstateAgent.service;

import me.sangmoon.RealEstateAgent.Exception.MemberRuntimeException;
import me.sangmoon.RealEstateAgent.dto.UserDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    private UserDto userDto;

    @BeforeEach
    void init() {
        userDto = UserDto.forJunitTest()
                .username("zepetto@naver.com")
                .password("1234")
                .nickname("zepetto")
                .build();
    }

    @Test
    void 회원가입() {
        //when
        userService.signup(userDto);

        //then
        assertEquals("zepetto", userService.getUserWithAuthorities(userDto.getUsername()).get().getNickname());
    }

    @Test
    void 중복_가입예외(){
        assertThrows(MemberRuntimeException.class, ()->{
            userService.signup(userDto);
            userService.signup(userDto);
        });
    }

}