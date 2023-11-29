package com.subh.user.service.controllers;

import com.subh.user.service.entity.UserEntity;
import com.subh.user.service.service.UserService;
import com.subh.user.service.service.impl.UserServiceImpl;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;

    @GetMapping("/health")
    public String getHealth(){
        return "OK";
    }

    @PostMapping()
    public ResponseEntity<UserEntity> createUser(@RequestBody UserEntity user){
        UserEntity userEntity = userService.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(userEntity);
    }
    int retryCount = 1;
    /*** We can either use circuit-breaker or retry mechanism***/
    @GetMapping("/{id}")
    //@CircuitBreaker(name="ratingHotelBreaker", fallbackMethod = "ratingHotelFallback")
    //@Retry(name="ratingHotelService", fallbackMethod = "ratingHotelFallback")
    @RateLimiter(name="userRateLimiter", fallbackMethod = "ratingHotelFallback")
    public ResponseEntity<UserEntity> getUser(@PathVariable String id){
        logger.info("RetryCount: {}",retryCount);
        retryCount++;
        UserEntity userEntity = userService.getUser(id);
        return ResponseEntity.ok(userEntity);
    }

    @GetMapping()
    public ResponseEntity<List<UserEntity>> getUserAll(){
        List<UserEntity> userEntity = userService.getAllUser();
        return ResponseEntity.ok(userEntity);
    }

    // Create fallback method for circuitbreaker, return type should be same as main method(getUser)
    public ResponseEntity<UserEntity> ratingHotelFallback(String id, Exception ex){
        logger.info("Fallback method called because service id down: ",ex.getMessage());
        UserEntity user = UserEntity.builder().email("fallback@dummy.com").name("Fallback dummy user")
                .about("Fallback user").userId("121212").build();
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
