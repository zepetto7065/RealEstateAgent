package me.sangmoon.RealEstateAgent.service;

import me.sangmoon.RealEstateAgent.domain.User;
import me.sangmoon.RealEstateAgent.domain.room.MontlyPayRoom;
import me.sangmoon.RealEstateAgent.domain.room.Room;
import me.sangmoon.RealEstateAgent.domain.room.RoomType;
import me.sangmoon.RealEstateAgent.domain.room.YearlyPayRoom;
import me.sangmoon.RealEstateAgent.dto.RoomDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.Month;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class RoomServiceTest {

    @Autowired
    private RoomService roomService;

    private PasswordEncoder passwordEncoder;

    private User user;

    private RoomDto roomDto;

    @BeforeEach
    void init() {
        roomDto = RoomDto.builder()
                .roomType(RoomType.ALL)
                .payType("M")
                .rentPrice(400000L)
                .deposit(100000000L)
                .address("서울시 금천구 시흥동")
                .build();

    }

    @Test
    void 전체조회() {
        //given
        RoomDto roomDto = new RoomDto(1L, "M", RoomType.ALL, "서울시 강서구 방화동", 200000000L, 1000000L);
        RoomDto roomDto2 = new RoomDto(2L, "Y", RoomType.ONEROOM, "서울시 금천구 시흥동", 400000000L,0L);
        RoomDto roomDto3 = new RoomDto(3L, "M", RoomType.THREEROOM, "제주도 제주시 제주동", 100000000L, 1000000L);
        //when
        roomService.insertMyRoom(roomDto, 1L);
        roomService.insertMyRoom(roomDto2, 1L);
        roomService.insertMyRoom(roomDto3, 1L);
        List<Room> rooms = roomService.selectRoomList();
        //then
        assertThat(rooms.size()).isEqualTo(3);
        assertThat(rooms.get(0).getAddress()).isEqualTo("서울시 강서구 방화동");
        assertThat(((MontlyPayRoom) rooms.get(0)).getRentPrice()).isEqualTo(1000000L);
    }

    @Test
    void 내방_목록_조회() {
        //given
        User user = User.builder()
                .userId(1L)
                .username("유상문")
                .build();
        //when
        roomService.insertMyRoom(roomDto, user.getUserId());
        List<Room> rooms = roomService.selectMyRoomList(user.getUserId());

        //then
        assertThat(rooms.size())
                .isEqualTo(1);
    }

    @Test
    void 내방_등록() {
        //given
        User user = User.builder()
                .userId(1L)
                .build();

        //when
        roomService.insertMyRoom(roomDto, user.getUserId());
        List<Room> rooms = roomService.selectMyRoomList(user.getUserId());

        //then
        assertThat(rooms.size()).isEqualTo(1);
    }

    @Test
    void 내방_수정() {
        //given
        User user = User.builder()
                .userId(1L)
                .build();
        RoomDto roomDto2 = RoomDto.builder()
                .roomType(RoomType.ALL)
                .payType("M")
                .rentPrice(200000L)
                .deposit(5000000L)
                .address("서울시 금천구 시흥동")
                .build();

        //when
        roomService.insertMyRoom(roomDto, user.getUserId());
        roomService.updateMyRoom(roomDto2, user.getUserId());
        List<Room> rooms = roomService.selectMyRoomList(user.getUserId());

        //then
        assertThat(((MontlyPayRoom) rooms.get(0)).getDeposit()).isEqualTo(5000000L);
        assertThat(((MontlyPayRoom) rooms.get(0)).getRentPrice()).isEqualTo(200000L);
    }

    @Test
    void 내방_삭제(){
        //given
        RoomDto roomDto = RoomDto.builder()
                .id(1L)
                .build();
        User user = User.builder()
                .userId(1L)
                .build();
        //when
        roomService.insertMyRoom(roomDto, user.getUserId());
        roomService.deleteMyRoomById(roomDto.getId());
        List<Room> rooms = roomService.selectMyRoomList(user.getUserId());

        //then
        assertThat(rooms.size()).isEqualTo(0);
    }
}