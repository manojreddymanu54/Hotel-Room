package com.example.nxtstayz.repository;

import com.example.nxtstayz.model.Room;
import com.example.nxtstayz.model.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public interface RoomJpaRepository extends JpaRepository<Room, Integer> {
    ArrayList<Room> findByHotel(Hotel hotel);
}