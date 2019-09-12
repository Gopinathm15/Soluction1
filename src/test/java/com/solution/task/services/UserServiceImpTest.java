package com.solution.task.services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import org.junit.Assert;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserServiceImpTest {

	@Autowired
	@Qualifier("userServiceImp")
	private UserService userService;

	@Test
	public void saveData() {
		String jsonString = "{\n" + "\"filePath\":\"/home/gopinath/local-store\",\n"
				+ "\"data\":{\"firstName\":\"sam\"},\n" + "\"id\":\"00000123456789098765432123456789\",\n"
				+ "\"expiry\":200\n" + "}";
		ResponseEntity responseEntity = userService.saveData(jsonString);
		Assert.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	}

	@Test(expected = Exception.class)
	public void getData() throws Exception {
		ResponseEntity responseEntity = userService.findData("00000123456789098765432123456783");
		Assert.assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
	}

	@Test(expected = Exception.class)
	public void removeData() throws Exception {
		ResponseEntity responseEntity = userService.deleteData("00000123456789098765432123456783");
		Assert.assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
	}

}
