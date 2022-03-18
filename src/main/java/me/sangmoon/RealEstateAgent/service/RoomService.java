package me.sangmoon.RealEstateAgent.service;

import lombok.RequiredArgsConstructor;
import me.sangmoon.RealEstateAgent.Exception.MemberRuntimeException;
import me.sangmoon.RealEstateAgent.domain.User;
import me.sangmoon.RealEstateAgent.domain.room.MontlyPayRoom;
import me.sangmoon.RealEstateAgent.domain.room.Room;
import me.sangmoon.RealEstateAgent.domain.room.YearlyPayRoom;
import me.sangmoon.RealEstateAgent.dto.RoomDto;
import me.sangmoon.RealEstateAgent.repository.RoomRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class RoomService {

    private final RoomRepository roomRepository;
    private final UserService userService;

    public List<Room> selectRoomList() {
        return roomRepository.findAll();
    }

    public List<Room> selectMyRoomList(Long userId) {
        return roomRepository.findAllById(userId);
    }

    public void insertMyRoom(RoomDto roomDto, Long userId) {

        Optional<User> user = userService.getUserById(userId);
        if (!user.isPresent()) {
            new MemberRuntimeException("해당 ID에 맞는 user가 존재하지 않습니다.");
        }

        Room room ;
        if("M".equals(roomDto.getPayType())){
            room = MontlyPayRoom.builder()
                    .user(user.get())
                    .roomType(roomDto.getRoomType())
                    .address(roomDto.getAddress())
                    .deposit(roomDto.getDeposit())
                    .rentPrice(roomDto.getRentPrice())
                    .build();
        }else{
            room = YearlyPayRoom.builder()
                    .user(user.get())
                    .roomType(roomDto.getRoomType())
                    .address(roomDto.getAddress())
                    .deposit(roomDto.getDeposit())
                    .build();
        }
        roomRepository.save(room);
    }
}
