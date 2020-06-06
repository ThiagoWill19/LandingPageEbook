package com.thiagowill.landingpage.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thiagowill.landingpage.models.UserContact;
import com.thiagowill.landingpage.repositories.UserContactRepository;

@Service
public class UserContactService {
	
	@Autowired
	private UserContactRepository repo;
	
	public void save(UserContact user) {
		repo.save(user);
	}
	
	public boolean autenticationEmail(UserContact user) {
		
		ArrayList<UserContact> list = (ArrayList<UserContact>) repo.findAll();
		for(UserContact u : list) {
			if(u.getEmail().equals(user.getEmail())) return false;
		}
		return true;
	}
	
	
}
