package com.example.nxtstayz.repository;

import com.example.nxtstayz.model.*;
import java.util.*;

public interface RoomRepository {

    public ArrayList<Room> getRooms();

    public Room getRoomById(int roomId);

    public Room addRoom(Room room);

    public Room updateRoom(int roomId, Room room);

    public void deleteRoom(int roomId);

    public Hotel getRoomHotel(int roomId);

}