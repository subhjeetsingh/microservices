package com.subh.hotel.controllers;

import com.subh.hotel.entity.HotelEntity;
import com.subh.hotel.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

    /*** Only User with access as Admin can create hotel, internally(UserService then HotelService)
     * or directly accessing hotel service through Api Gateway or direct Hotel service call ***/
    @PreAuthorize("hasAuthority('Admin')")
    @PostMapping()
    public ResponseEntity<HotelEntity> createHotel(@RequestBody HotelEntity hotel){
        HotelEntity hotelEntity = hotelService.saveHotel(hotel);
        return ResponseEntity.status(HttpStatus.CREATED).body(hotelEntity);
    }

    /*** This api will only called by internal Api. But we can't call it directly.
     In OkTA we have created one SCOPE as internal, that we used here ***/
    @PreAuthorize("hasAuthority('SCOPE_internal')")
    @GetMapping("/{id}")
    public ResponseEntity<HotelEntity> getHotel(@PathVariable String id){
        HotelEntity hotelEntity = hotelService.getHotel(id);
        return ResponseEntity.status(HttpStatus.OK).body(hotelEntity);
    }

    /*** This api will called by internal Api or user with Admin access, Normal user will not be able
     * to call it. But we can call it directly,
     * If it's Admin.In OkTA we have created one SCOPE as internal, that we used here ***/
    @PreAuthorize("hasAuthority('SCOPE_internal') || hasAuthority('Admin')")
    @GetMapping()
    public ResponseEntity<List<HotelEntity>> getAllHotel(){
        List<HotelEntity> hotelEntity = hotelService.getAllHotel();
        return ResponseEntity.ok(hotelEntity);
    }
}
