package com.subh.user.service;

import com.subh.user.service.external.services.RatingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserServiceApplicationTests {

	@Autowired
	private RatingService ratingService;

	@Test
	void contextLoads() {
	}

//	@Test
//	void createRating(){
//		Rating rating = Rating.builder().rating(3).userId("7b392edd-0f8b-4f26-9273-8fb98c54bb72")
//				.hotelId("da83d4fc-365e-422c-b316-a78a584c3536").feedback("Nice Location, Friendly Staff(created using Feign Client)").build();
//		Rating savedRating = ratingService.createRating(rating);
//		//or ResponseEntity<Rating> savedRating = ratingService.createRating(rating);
//		// savedRating.getBody();
//		System.out.println("Rating Created with values :"+savedRating.toString());
//	}
}
