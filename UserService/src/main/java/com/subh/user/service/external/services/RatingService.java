package com.subh.user.service.external.services;
;
import com.subh.user.service.entity.Rating;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name="RATING-SERVICE")
public interface RatingService {

    @PostMapping("/rating")
    public Rating createRating(Rating rating);
    //or public ResponseEntity<Rating> createRating(Rating rating);

    @PutMapping("/rating/{ratingId}")
    public Rating updateRating(@PathVariable String ratingId, Rating rating);
    //or public ResponseEntity<Rating> updateRating(@PathVariable String ratingId, Rating rating);

    @DeleteMapping("/rating/{ratingId}")
    public void deleRating(@PathVariable("ratingId") String ratingId);
    //or public ResponseEntity<Rating> deleRating(@PathVariable("ratingId") String ratingId);
}
