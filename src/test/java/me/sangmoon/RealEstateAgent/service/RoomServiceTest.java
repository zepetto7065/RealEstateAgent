package me.sangmoon.RealEstateAgent.service;

import me.sangmoon.RealEstateAgent.domain.User;
import me.sangmoon.RealEstateAgent.domain.room.Room;
import me.sangmoon.RealEstateAgent.domain.room.RoomType;
import me.sangmoon.RealEstateAgent.dto.RoomDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@SpringBootTest
class RoomServiceTest {

    @Autowired
    private RoomService roomService;

    private PasswordEncoder passwordEncoder;

    private User user;

    @BeforeEach
    void init(){

    }

    @Test
    void 전체조회(){
        //when
        roomService.selectRoomList();
        //then

    }

    @Test
    void 내방_목록_조회(){
        //given

        //when
        List<Room> userRooms = roomService.selectMyRoomList(2L);

        Assertions.assertThat(user.getUsername())
                .isEqualTo(userRooms.get(0).getUser());
    }

    @Test
    void 내방_등록(){
        //given
        RoomDto roomDto = RoomDto.builder()
                .roomType(RoomType.ALL)
                .payType("M")
                .rentPrice(400000L)
                .deposit(100000000L)
                .address("서울시 금천구 시흥동")
                .build();

        //when
        roomService.insertMyRoom(roomDto, 1L);
        List<Room> rooms = roomService.selectMyRoomList(1L);

        //then
        Assertions.assertThat(rooms.size()).isEqualTo(1);
    }
}