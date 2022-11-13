package com.example.roomreservationservice;

import lombok.Data;

import java.util.Date;

@Data
public class Reservation {
    private long id;
    private long roomId;
    private long guestId;
    private Date reservationDate;
}