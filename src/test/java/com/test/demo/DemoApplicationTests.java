package com.test.demo;


import com.test.demo.service.TestPetService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.test.demo.DTO.PetResponseDTO;
import com.test.demo.service.TestPetService;

@SpringBootTest
class DemoApplicationTests {
		
	   @Autowired
	    private TestPetService service;

		@Test	
		public void checkCount() {

			PetResponseDTO response = this.service.getMyPetCountByStatus("available","doggie");

			System.out.println("hello here is the count  => "+response.petCount);

			if(response.petCount<0) {
				 Assert.fail(response.errorMessage);
				 System.out.println("hello here is the count --> "+response.petCount);
			 }
		}
}

