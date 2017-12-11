package com.qiyexuxu.domain;

public class SelectedClassroom {
    private int sumOfSeats;
    private int restOfSeats;
    private Seat[] occupiedSeats;

    public int getSumOfSeats() {
        return sumOfSeats;
    }

    public void setSumOfSeats(int sumOfSeats) {
        this.sumOfSeats = sumOfSeats;
    }

    public int getRestOfSeats() {
        return restOfSeats;
    }

    public void setRestOfSeats(int restOfSeats) {
        this.restOfSeats = restOfSeats;
    }

    public Seat[] getOccupiedSeats() {
        return occupiedSeats;
    }

    public void setOccupiedSeats(Seat[] occupiedSeats) {
        this.occupiedSeats = occupiedSeats;
    }
}
