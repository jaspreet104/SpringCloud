package com.example.reservationservices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Optional;

@RestController
@RequestMapping("/reservations")
public class ReservationWebServices {

    @Autowired
    private ReservationRepository reservationRepository;

    @GetMapping
    public Iterable<Reservation> getAllReservations(@RequestParam("date") @DateTimeFormat(pattern="yyyy-MM-dd") Optional<Date> date) {
        Date forDate = date.orElse(null);
        if (null != forDate) {
            System.out.println(forDate);
            return reservationRepository.findAllByReservationDate(forDate);
        }
        return reservationRepository.findAll();
    }

    @GetMapping("/{id}")
    public Reservation getReservationById(@PathVariable("id") long id) {
        return reservationRepository.findById(id).orElse(null);
    }
}
