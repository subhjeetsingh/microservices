package com.subh.user.service.service;

import com.subh.user.service.entity.UserEntity;

import java.util.List;

public interface UserService {

    UserEntity saveUser(UserEntity user);

    List<UserEntity> getAllUser();

    UserEntity getUser(String id);
}
