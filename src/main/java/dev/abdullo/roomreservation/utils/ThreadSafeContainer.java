package dev.abdullo.roomreservation.utils;

import dev.abdullo.roomreservation.dao.RoomDAO;
import dev.abdullo.roomreservation.domains.Room;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public interface ThreadSafeContainer {
    ConcurrentMap<Long, LocalDate> reservationDates = new ConcurrentHashMap<>();
//    List<Room> rooms = RoomDAO.getInstance().findAll();
//    List<Room> threadSafe = Collections.synchronizedList(rooms);

}
