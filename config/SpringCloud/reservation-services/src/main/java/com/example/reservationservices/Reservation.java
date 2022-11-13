package com.example.reservationservices;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "RESERVATION")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "RESERVATION_ID")
    private long id;

    @Column(name = "ROOM_ID", nullable = false)
    private long roomId;

    @Column(name = "GUEST_ID", nullable = false)
    private long guestId;

    @Column(name = "RES_DATE")
    private Date reservationDate;
}
