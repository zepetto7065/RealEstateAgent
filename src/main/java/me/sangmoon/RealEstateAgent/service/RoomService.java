package me.sangmoon.RealEstateAgent.service;

import lombok.RequiredArgsConstructor;
import me.sangmoon.RealEstateAgent.Exception.MemberRuntimeException;
import me.sangmoon.RealEstateAgent.Exception.RoomRuntimeException;
import me.sangmoon.RealEstateAgent.repository.RoomSearchRepository;
import me.sangmoon.RealEstateAgent.domain.User;
import me.sangmoon.RealEstateAgent.domain.room.MonthlyPayRoom;
import me.sangmoon.RealEstateAgent.domain.room.Room;
import me.sangmoon.RealEstateAgent.domain.room.YearlyPayRoom;
import me.sangmoon.RealEstateAgent.dto.RoomDto;
import me.sangmoon.RealEstateAgent.dto.SearchDto;
import me.sangmoon.RealEstateAgent.repository.RoomRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class RoomService {

    private final RoomRepository roomRepository;
    private final RoomSearchRepository roomSearchRepository;
    private final UserService userService;

    public List<Room> selectRoomList(SearchDto searchDto) {
        return roomSearchRepository.findAllBySearch(searchDto);
    }

    public List<Room> selectMyRoomList(Long userId) {
        return roomRepository.findAllById(userId);
    }

    public void insertMyRoom(RoomDto roomDto) {

        User user = userService.getUserById(roomDto.getUserId())
                .orElseThrow(() -> new MemberRuntimeException("해당 ID에 맞는 user가 존재하지 않습니다."));

        Room room;
        if ("M".equals(roomDto.getPayType())) {
            room = MonthlyPayRoom.builder()
                    .user(user)
                    .roomType(roomDto.getRoomType())
                    .address(roomDto.getAddress())
                    .deposit(roomDto.getDeposit())
                    .rentPrice(roomDto.getRentPrice())
                    .build();
        } else {
            room = YearlyPayRoom.builder()
                    .user(user)
                    .roomType(roomDto.getRoomType())
                    .deposit(roomDto.getDeposit())
                    .address(roomDto.getAddress())
                    .build();
        }
        roomRepository.save(room);
    }

    public void updateMyRoom(RoomDto roomDto) {
        if ("M".equals(roomDto.getPayType())) {
            MonthlyPayRoom monthlyPayRoomById = (MonthlyPayRoom) roomRepository.findById(roomDto.getRoomId())
                    .orElseThrow(() -> new RoomRuntimeException("해당 ID에 맞는 내 방이 존재하지 않습니다."));
            monthlyPayRoomById.setDeposit(roomDto.getDeposit());
            monthlyPayRoomById.setRentPrice(roomDto.getRentPrice());
        }else{
            YearlyPayRoom yearlyPayRoom = (YearlyPayRoom) roomRepository.findById(roomDto.getRoomId())
                    .orElseThrow(() -> new RoomRuntimeException("해당 ID에 맞는 내 방이 존재하지 않습니다."));
            yearlyPayRoom.setDeposit(roomDto.getDeposit());
        }
    }

    public void deleteMyRoomById(Long roomId) {
        roomRepository.deleteById(roomId);
    }
}
