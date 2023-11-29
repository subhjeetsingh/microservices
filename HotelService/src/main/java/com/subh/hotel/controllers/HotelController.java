package com.subh.hotel.controllers;

import com.subh.hotel.entity.HotelEntity;
import com.subh.hotel.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotel")
public class HotelController {

    @Autowired
    private HotelService hotelService;

    @GetMapping("/health")
    public String getHealth(){
        return "OK";
    }

    @PostMapping()
    public ResponseEntity<HotelEntity> createHotel(@RequestBody HotelEntity hotel){
        HotelEntity hotelEntity = hotelService.saveHotel(hotel);
        return ResponseEntity.status(HttpStatus.CREATED).body(hotelEntity);
    }

    @GetMapping("/{id}")
    public ResponseEntity<HotelEntity> getHotel(@PathVariable String id){
        HotelEntity hotelEntity = hotelService.getHotel(id);
        return ResponseEntity.status(HttpStatus.OK).body(hotelEntity);
    }

    @GetMapping()
    public ResponseEntity<List<HotelEntity>> getAllHotel(){
        List<HotelEntity> hotelEntity = hotelService.getAllHotel();
        return ResponseEntity.ok(hotelEntity);
    }
}
