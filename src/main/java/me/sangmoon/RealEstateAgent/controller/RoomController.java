package me.sangmoon.RealEstateAgent.controller;

import lombok.RequiredArgsConstructor;
import me.sangmoon.RealEstateAgent.domain.room.Room;
import me.sangmoon.RealEstateAgent.dto.Message;
import me.sangmoon.RealEstateAgent.dto.RoomDto;
import me.sangmoon.RealEstateAgent.dto.SearchDto;
import me.sangmoon.RealEstateAgent.service.RoomService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class RoomController {

    private final RoomService roomService;

    @GetMapping("/rooms")
    public ResponseEntity<Message> findAll(@RequestBody SearchDto searchDto) {
        List<Room> rooms = roomService.selectRoomList(searchDto);
        Message message = new Message();
        message.setData(rooms);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @GetMapping("/rooms/{userId}")
    public ResponseEntity<Message> findAllById(@PathVariable("userId") Long userId) {
        List<Room> rooms = roomService.selectMyRoomList(userId);
        Message message = new Message();
        message.setData(rooms);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @PostMapping("/room/new")
    public ResponseEntity<Message> createRoom(
            @ModelAttribute("roomDto") RoomDto roomDto,
            @RequestParam("userId") Long userId){
        roomService.insertMyRoom(roomDto, userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/room/update")
    public ResponseEntity<Message> updateRoom(
            @ModelAttribute("roomDto") RoomDto roomDto,
            @RequestParam("userId") Long userId){
        roomService.updateMyRoom(roomDto, userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/room/delete")
    public ResponseEntity<Message> deleteRoom(
            @RequestParam("roomId") Long roomId){
        roomService.deleteMyRoomById(roomId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
