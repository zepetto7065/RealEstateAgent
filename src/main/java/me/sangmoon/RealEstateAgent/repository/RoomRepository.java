package me.sangmoon.RealEstateAgent.repository;

import me.sangmoon.RealEstateAgent.domain.room.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Long> {
    List<Room> findAllById(Long userId);
}
