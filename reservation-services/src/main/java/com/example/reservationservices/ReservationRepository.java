package com.example.reservationservices;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    public Iterable<Reservation> findAllByReservationDate(Date date);
}
