package com.subh.hotel.service;

import com.subh.hotel.entity.HotelEntity;

import java.util.List;

public interface HotelService {

    HotelEntity saveHotel(HotelEntity hotel);

    List<HotelEntity> getAllHotel();

    HotelEntity getHotel(String id);
}
