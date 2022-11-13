package com.example.roomreservationservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@FeignClient(value = "guestservices", url = "http://192.168.1.7:8800/")
public interface GuestClient {

    @GetMapping("/guests")
    public List<Guest> getAllGuests();

    @GetMapping("/guests/{id}")
    public Guest getGuest(@PathVariable("id") long id);
}