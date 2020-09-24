package com.revature;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.revature.models.*;
import com.revature.repositories.*;
import com.revature.services.*;

@ExtendWith(MockitoExtension.class)
public class TaskServiceTests {
	
	@Mock
	private ITasksDAO tdao;
	
	@InjectMocks
	private TaskService ts;
	
	@Test
	void getUserTasks() {
		
		User user = new User(1, "testman", "lastname",70,"user1","pass1",null,null,null,10);
		List<Tasks> all = new ArrayList<Tasks>();
		Tasks t1 = new Tasks(1,"cleaning litter",false,10,user);
		Tasks t2 = new Tasks(1,"walking cat",true,2,user);
		Tasks t3 = new Tasks(1,"wash dishes",false,25,user);
		
		all.add(t1);
		all.add(t2);
		all.add(t3);
		when(tdao.findByDoer(user)).thenReturn(all);
		
		List<Tasks> t = ts.findUserTasks(user);
		User u2 = new User(2, "testman", "lastname",70,"user1","pass1",null,null,null,10);
		
		List<Tasks> task2 = ts.findUserTasks(u2);
		assertEquals(3,t.size());
		assertEquals(0,task2.size());
	}

}
