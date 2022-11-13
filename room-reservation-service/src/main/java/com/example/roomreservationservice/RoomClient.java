package com.example.roomreservationservice;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

//had to pur url because its not going via load balancer somehow
@FeignClient(value = "roomservices", url = "http://192.168.1.7:8802/")
public interface RoomClient {
    @GetMapping("/rooms")
    List<Room> getAllRooms();

    @GetMapping("/rooms/{id}")
    Room getRoom(@PathVariable("id") long id);
}
