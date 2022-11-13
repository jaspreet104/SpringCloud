package com.example.roomreservationservice;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@FeignClient(value = "reservationservices", url = "http://192.168.1.7:8801/")
public interface ReservationClient {
    @GetMapping("/reservations")
    public List<Reservation> getAllReservations(@RequestParam("date") @DateTimeFormat(pattern="yyyy-MM-dd") Date date);

    @GetMapping("/reservations/{id}")
    public Reservation getReservationById(@PathVariable("id") long id);
}
