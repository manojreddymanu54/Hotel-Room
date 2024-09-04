package com.example.nxtstayz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.ArrayList;
import java.util.List;
import com.example.nxtstayz.model.Room;
import com.example.nxtstayz.model.Hotel;
import com.example.nxtstayz.repository.RoomJpaRepository;
import com.example.nxtstayz.repository.HotelJpaRepository;
import com.example.nxtstayz.repository.RoomRepository;
import java.util.NoSuchElementException;

@Service
public class RoomJpaService implements RoomRepository {

    @Autowired
    private HotelJpaRepository hotelJpaRepository;
    @Autowired
    private RoomJpaRepository roomJpaRepository;

    @Override
    public Room addRoom(Room room) {
        Hotel hotel = room.getHotel();
        int hotelId = hotel.getHotelId();
        try {
            Hotel hotell = hotelJpaRepository.findById(hotelId).get();
            room.setHotel(hotell);
            return roomJpaRepository.save(room);

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public Room updateRoom(int roomId, Room room) {
        try {
            Room oldroom = roomJpaRepository.findById(roomId).get();
            if (room.getRoomNumber() != null) {
                oldroom.setRoomNumber(room.getRoomNumber());
            }
            if (room.getRoomType() != null) {
                oldroom.setRoomType(room.getRoomType());
            }
            if (room.getPrice() != 0) {
                oldroom.setPrice(room.getPrice());
            }
            if (room.getHotel() != null) {
                Hotel hotel = room.getHotel();
                int hotelId = hotel.getHotelId();
                Hotel hotell = hotelJpaRepository.findById(hotelId).get();
                oldroom.setHotel(hotell);
            }
            return roomJpaRepository.save(oldroom);

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

    }

    @Override
    public void deleteRoom(int roomId) {
        try {
            roomJpaRepository.deleteById(roomId);
            throw new ResponseStatusException(HttpStatus.NO_CONTENT);

        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

    }

    @Override
    public ArrayList<Room> getRooms() {
        List<Room> rooms = roomJpaRepository.findAll();
        ArrayList<Room> allrooms = new ArrayList<>(rooms);
        return allrooms;
    }

    @Override
    public Room getRoomById(int roomId) {
        try {
            return roomJpaRepository.findById(roomId).get();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public Hotel getRoomHotel(int roomId) {
        try {
            Room room = roomJpaRepository.findById(roomId).get();
            return room.getHotel();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

}
