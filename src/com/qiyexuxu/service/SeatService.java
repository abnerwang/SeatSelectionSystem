package com.qiyexuxu.service;

import com.qiyexuxu.domain.Seat;
import com.qiyexuxu.exception.SeatOccupiedException;
import com.qiyexuxu.exception.SeatSelectedException;

import java.lang.reflect.InvocationTargetException;

public interface SeatService {
    Seat select(String studentID, String classroomID, int seatRow, int seatColumn)
            throws SeatOccupiedException, SeatSelectedException, IllegalAccessException, InstantiationException, InvocationTargetException;

    boolean release(String classroomID, int seatRow, int seatColumn);
}
