package com.qiyexuxu.service;

import com.qiyexuxu.exception.SeatOccupiedException;
import com.qiyexuxu.exception.SeatSelectedException;

public interface SeatService {
    boolean select(String studentID, String classroomID, int seatRow, int seatColumn)
            throws SeatOccupiedException, SeatSelectedException;

    boolean release(String classroomID, int seatRow, int seatColumn);
}
