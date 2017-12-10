package com.qiyexuxu.domain;

public class Seat {
    private String classroomID;  // 座位所在教室号
    private int seatRow;    // 座位所在行
    private int seatColumn;  // 座位所在列
    private String studentID;   // 占据该座位的学生学号
    private char occupationFlag;   // 该座位是否被占用

    public String getClassroomID() {
        return classroomID;
    }

    public void setClassroomID(String classroomID) {
        this.classroomID = classroomID;
    }

    public int getSeatRow() {
        return seatRow;
    }

    public void setSeatRow(int seatRow) {
        this.seatRow = seatRow;
    }

    public int getSeatColumn() {
        return seatColumn;
    }

    public void setSeatColumn(int seatColumn) {
        this.seatColumn = seatColumn;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public char getOccupationFlag() {
        return occupationFlag;
    }

    public void setOccupationFlag(char occupationFlag) {
        this.occupationFlag = occupationFlag;
    }
}
