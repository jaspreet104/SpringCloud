package com.example.guestservices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/guests")
public class GuestWebServices {
    @Autowired
    private GuestRepository guestRepository;

    @GetMapping
    public Iterable<Guest> getAllGuests() {
        return guestRepository.findAll();
    }

    @GetMapping("/{id}")
    public Guest getGuestById(@PathVariable("id") long id) {
        return guestRepository.findById(id).orElse(null);
    }

}
