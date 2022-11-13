package com.example.roomservices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rooms")
public class RoomWebServices {

    @Autowired
    private RoomRepository roomRepository;

    @GetMapping
    public Iterable<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    @GetMapping("{id}")
    public Room getRoomById(@PathVariable("id") long id) {
        return roomRepository.findById(id).orElse(null);
    }
}
