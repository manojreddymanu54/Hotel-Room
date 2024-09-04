package com.example.nxtstayz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.ArrayList;
import java.util.List;
import com.example.nxtstayz.model.Hotel;
import com.example.nxtstayz.model.Room;
import com.example.nxtstayz.repository.RoomJpaRepository;
import com.example.nxtstayz.repository.HotelJpaRepository;
import com.example.nxtstayz.repository.HotelRepository;
import java.util.NoSuchElementException;

@Service
public class HotelJpaService implements HotelRepository {

    @Autowired
    private HotelJpaRepository hotelJpaRepository;

    @Autowired
    private RoomJpaRepository roomJpaRepository;

    @Override
    public Hotel getHotelById(int hotelId) {
        try {
            Hotel hotel = hotelJpaRepository.findById(hotelId).get();
            return hotel;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public Hotel addHotel(Hotel hotel) {
        return hotelJpaRepository.save(hotel);

    }

    @Override
    public Hotel updateHotel(int hotelId, Hotel hotel) {
        try {
            Hotel oldhotel = hotelJpaRepository.findById(hotelId).get();
            if (hotel.getHotelName() != null) {
                oldhotel.setHotelName(hotel.getHotelName());
            }
            if (hotel.getLocation() != null) {
                oldhotel.setLocation(hotel.getLocation());
            }
            if (hotel.getRating() != 0) {
                oldhotel.setRating(hotel.getRating());
            }
            return hotelJpaRepository.save(oldhotel);

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

    }

    @Override
    public void deleteHotel(int hotelId) {
        try {
            Hotel hotel = hotelJpaRepository.findById(hotelId).get();
            List<Room> rooms = roomJpaRepository.findByHotel(hotel);
            for (Room room : rooms) {
                room.setHotel(null);
            }
            roomJpaRepository.saveAll(rooms);
            hotelJpaRepository.deleteById(hotelId);
            throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

    }

    @Override
    public ArrayList<Hotel> getHotels() {
        List<Hotel> hotels = hotelJpaRepository.findAll();
        ArrayList<Hotel> allhotels = new ArrayList<>(hotels);
        return allhotels;
    }

    @Override
    public ArrayList<Room> getHotelRooms(int hotelId) {
        try {
            Hotel hotel = hotelJpaRepository.findById(hotelId).get();
            return roomJpaRepository.findByHotel(hotel);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
