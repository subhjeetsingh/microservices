package com.subh.rating.service.impl;

import com.subh.rating.entity.Rating;
import com.subh.rating.repository.RatingRepository;
import com.subh.rating.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingServiceImpl implements RatingService {

    @Autowired
    private RatingRepository repo;
    @Override
    public Rating saveRating(Rating rating) {
        return repo.save(rating);
    }

    @Override
    public List<Rating> getAllRatings() {
        return repo.findAll();
    }

    @Override
    public List<Rating> getRatingByUserId(String userId) {
        return repo.findByUserId(userId);
    }

    @Override
    public List<Rating> getRatingByHotelId(String hotelId) {
        return repo.findByHotelId(hotelId);
    }
}
