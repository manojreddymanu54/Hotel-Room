package com.example.nxtstayz.repository;

import com.example.nxtstayz.model.*;
import java.util.*;

public interface HotelRepository {

    public ArrayList<Hotel> getHotels();

    public Hotel getHotelById(int hotelId);

    public Hotel addHotel(Hotel hotel);

    public Hotel updateHotel(int hotelId, Hotel hotel);

    public void deleteHotel(int hotelId);

    public ArrayList<Room> getHotelRooms(int hotelId);
}
