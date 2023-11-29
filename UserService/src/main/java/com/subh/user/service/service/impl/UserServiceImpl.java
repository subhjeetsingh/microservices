package com.subh.user.service.service.impl;

import com.subh.user.service.constants.Constants;
import com.subh.user.service.entity.Hotel;
import com.subh.user.service.entity.Rating;
import com.subh.user.service.entity.UserEntity;
import com.subh.user.service.exceptions.ResourceNotFoundException;
import com.subh.user.service.external.services.HotelService;
import com.subh.user.service.repository.UserRepository;
import com.subh.user.service.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private HotelService hotelService;

    @Override
    public UserEntity saveUser(UserEntity user) {
        String randomId = UUID.randomUUID().toString();
        user.setUserId(randomId);
        return userRepo.save(user);
    }

    @Override
    public List<UserEntity> getAllUser() {
        return userRepo.findAll();
    }

    @Override
    public UserEntity getUser(String id) {
        logger.info("userid: "+id);
        UserEntity user = userRepo.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("User with given id not found :"+id));

        Rating[] ratingByUser = restTemplate.getForObject(Constants.dyna_rating_user_url+"/"+id, Rating[].class);
        logger.info("ratingByUser: "+ratingByUser.toString());
        List<Rating> ratings = Arrays.stream(ratingByUser).toList();

        List<Rating> ratingList = ratings.stream().map(rating -> {

            //ResponseEntity<Hotel> hotelEntity = restTemplate.getForEntity(Constants.dyna_hotel_by_id+"/"+rating.getHotelId(), Hotel.class);
            Hotel hotelDtls = hotelService.getHotel(rating.getHotelId());// hotelEntity.getBody();
            logger.info("hotel obj1: {}",hotelDtls.toString());
            rating.setHotel(hotelDtls);
            return rating;
        }).collect(Collectors.toList());

        user.setRatings(ratingList);
        return user;
    }
}
