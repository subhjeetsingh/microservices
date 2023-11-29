package com.subh.hotel.service.impl;

import com.subh.hotel.entity.HotelEntity;
import com.subh.hotel.exceptions.ResourceNotFoundException;
import com.subh.hotel.repository.HotelRepository;
import com.subh.hotel.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class HotelServiceImpl implements HotelService {

    @Autowired
    private HotelRepository hotelRepo;

    @Override
    public HotelEntity saveHotel(HotelEntity hotel) {
        String randomId = UUID.randomUUID().toString();
        hotel.setId(randomId);
        return hotelRepo.save(hotel);
    }

    @Override
    public List<HotelEntity> getAllHotel() {

        return hotelRepo.findAll();
    }

    @Override
    public HotelEntity getHotel(String id) {
        return hotelRepo.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("User with given id not found :"+id));
    }
}
