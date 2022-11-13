package com.example.roomreservationservice;

import lombok.Data;

@Data
public class Guest {

    private long id;

    private String firstName;

    private String lastName;

    private String email;

    private String address;

    private String country;

    private String state;

    private String phoneNumber;
}
