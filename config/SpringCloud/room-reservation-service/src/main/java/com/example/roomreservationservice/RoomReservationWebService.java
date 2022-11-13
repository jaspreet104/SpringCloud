package com.example.roomreservationservice;

import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@RestController
@RequestMapping("/room-reservations")
public class RoomReservationWebService {

    @Autowired
    private DiscoveryClient discoveryClient;

    // not autowired becuase it was unable to resolve ROOMSERVICES
    private RestTemplate restTemplate;

    @Autowired
    private RoomClient roomClient;

    @Autowired
    private GuestClient guestClient;

    @Autowired
    private ReservationClient reservationClient;

    @GetMapping("/using-eureka")
    public List<RoomReservation> getRoomReservations() {
        List<Room> roomList = this.getAllRooms();
        List<RoomReservation> roomReservationList = new ArrayList<>();
        roomList.forEach(room -> {
            RoomReservation roomReservation = new RoomReservation();
            roomReservation.setRoomId(room.getId());
            roomReservation.setRoomName(room.getName());
            roomReservation.setRoomNumber(room.getRoomNumber());
            roomReservationList.add(roomReservation);
        });

        return roomReservationList;
    }

    public List<Room> getAllRooms() {
        String s = discoveryClient.getServices().toString();
        System.out.println(s);
        String s1 = discoveryClient.getInstances("ROOMSERVICES").get(0).getUri().toString()+"/rooms";
        System.out.println(s1);
        restTemplate = new RestTemplate();
        ResponseEntity<List<Room>> responseEntity = restTemplate.exchange(s1,
                HttpMethod.GET, null, new ParameterizedTypeReference<List<Room>>() {} );
        return responseEntity.getBody();
    }

    @GetMapping("/test-feign")
    public String testFeign() {
        String s = "";
        s+="rooms: "+roomClient.getAllRooms().toString()+"<br><br><br>";
        s+="guests: "+guestClient.getAllGuests().toString()+"<br><br><br>";
        s+="reservations: "+reservationClient.getAllReservations(null).toString()+"<br><br><br>";
        return s;
    }

    @GetMapping("/using-feign/forDate")
    public List<RoomReservation> getRoomReservationsForDate(@RequestParam("date") @DateTimeFormat(pattern="yyyy-MM-dd") Date date) {
        System.out.println(date);
        List<Reservation> reservations = reservationClient.getAllReservations(date);
        System.out.println(reservations);
        List<RoomReservation> roomReservationList = new ArrayList<>();
        reservations.forEach(reservation -> {
            RoomReservation roomReservation = new RoomReservation();

            long roomId = reservation.getRoomId();
            Room room = roomClient.getRoom(roomId);
            roomReservation.setRoomId(room.getId());
            roomReservation.setRoomName(room.getName());
            roomReservation.setRoomNumber(room.getRoomNumber());

            long guestId = reservation.getGuestId();
            Guest guest = guestClient.getGuest(guestId);
            roomReservation.setGuestId(guest.getId());
            roomReservation.setFirstName(guest.getFirstName());
            roomReservation.setLastName(guest.getLastName());

            roomReservation.setDate(date);
            System.out.println(roomReservation);

            roomReservationList.add(roomReservation);
        });
        return roomReservationList;
    }
}
