package com.test.demo;


import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.test.demo.DTO.PetResponseDTO;
import com.test.demo.service.TestWireService;

@SpringBootTest
class DemoApplicationTests {
		
	   @Autowired
	    private TestWireService service;

		@Test	
		public void checkCount() {
			
			 PetResponseDTO response = this.service.getMyPetCountByStatus("available","doggie");
			 
			 if(response.petCount<0) {
				 Assert.fail(response.errorMessage);
			 }
		}
}

