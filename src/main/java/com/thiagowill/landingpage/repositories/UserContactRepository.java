package com.thiagowill.landingpage.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.thiagowill.landingpage.models.UserContact;

@Repository
public interface UserContactRepository extends JpaRepository<UserContact, Integer>{

}
