package com.example.booking.controllers;

import com.example.booking.dto.CalculateTableDTO;
import com.example.booking.services.BookingService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookingController {

    @Autowired
    BookingService bookingService;

    @PostMapping("/calculate-table")
    public ResponseEntity<Integer> calculateTable(@Valid @RequestBody CalculateTableDTO body) {
        return new ResponseEntity<>(bookingService.getMaximumTableAmount(body), HttpStatus.OK);
    }
}
