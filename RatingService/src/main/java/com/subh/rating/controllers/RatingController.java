package com.subh.rating.controllers;

import com.subh.rating.entity.Rating;
import com.subh.rating.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rating")
public class RatingController {

    @Autowired
    private RatingService ratingService;

    @GetMapping("/health")
    public String getHealth(){
        return "OK";
    }

    @PostMapping()
    public ResponseEntity<Rating> createRating(@RequestBody Rating rating){
        Rating ratingEntity = ratingService.saveRating(rating);
        return ResponseEntity.status(HttpStatus.CREATED).body(ratingEntity);
    }

    @GetMapping()
    public ResponseEntity<List<Rating>> getAllRating(){
        List<Rating> ratingEntity = ratingService.getAllRatings();
        return ResponseEntity.ok(ratingEntity);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Rating>> getRatingByUser(@PathVariable String userId){
        List<Rating> ratingEntity = ratingService.getRatingByUserId(userId);
        return ResponseEntity.status(HttpStatus.OK).body(ratingEntity);
    }

    @GetMapping("/hotel/{hotelId}")
    public ResponseEntity<List<Rating>> getRatingByHotel(@PathVariable String hotelId){
        List<Rating> ratingEntity = ratingService.getRatingByHotelId(hotelId);
        return ResponseEntity.status(HttpStatus.OK).body(ratingEntity);
    }
}
