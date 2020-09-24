package com.revature;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.*;

import java.util.Optional;

import com.revature.models.*;
import com.revature.repositories.*;
import com.revature.services.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
	
	@Mock
	private IUserDAO udao;
	
	@InjectMocks
	private UserService us;
	
	@Test
	void getUserTest() {
		User user = new User(0, "testman", "lastname",70,"user1","pass1",null,null,null,10);
		when(udao.findByUsername("user1")).thenReturn(user);
		
		User temp = us.findByUsername("user1");
		
		assertEquals(0, temp.getUserid());
		assertEquals("testman",temp.getFirstName());
	}
	
	@Test
	void updateUserTest(){
		User user = new User(0, "testman", "lastname",70,"user1","pass1",null,null,null,10);
		when(udao.findByUsername("user1")).thenReturn(user);
		
		User updatedUser = us.findByUsername("user1");
		
		updatedUser.setPoints(20);
		
		us.updateUser(updatedUser);
		
		User n = us.findByUsername("user1");
		
		assertEquals(20, n.getPoints());
		
		
	}

}
