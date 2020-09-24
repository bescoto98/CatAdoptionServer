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
public class CatServiceTest {
	
	@Mock 
	private ICatsDAO cdao;
	
	@InjectMocks
	private CatService cs;
	
	@Test
	void getAllCats() {

		List<Cats> allCats = new ArrayList<Cats>();
		Cats c1 = new Cats(1,200,null,true);
		Cats c2 = new Cats(2,20,null,true);
		Cats c3 = new Cats(3,80,null,true);
		
		
		allCats.add(c1);
		allCats.add(c2);
		allCats.add(c3);
		
		when(cdao.findAll()).thenReturn(allCats);
		
		List<Cats> testCats = cs.findAll();
		
		assertEquals(3,testCats.size());
		verify(cdao,times(1)).findAll();
		
		
	}
	
	@Test
	void updateCatTest() {
		Optional<Cats> c = Optional.of(new Cats(2,200,null,true));
		when(cdao.findById(2)).thenReturn(c);
		
		Cats t = cs.findById(2);
		
		t.setAdoptionStatus(false);
		cs.addCat(t);
		
		Cats q = cs.findById(2);
		
		assertThat(q.isAdoptionStatus()).isEqualTo(false);
		
		
		
	}

}
