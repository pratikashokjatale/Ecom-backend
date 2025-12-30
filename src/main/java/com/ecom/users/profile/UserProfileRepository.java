package com.ecom.users.profile;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecom.auth.model.User;



@Repository
public interface UserProfileRepository extends JpaRepository<Userprofile, Long> {


   
}
