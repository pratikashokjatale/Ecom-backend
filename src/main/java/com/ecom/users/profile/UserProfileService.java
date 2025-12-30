package com.ecom.users.profile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecom.auth.model.User;
import com.ecom.auth.repository.UserRepository;

@Service
public class UserProfileService {

    @Autowired
    private UserProfileRepository userProfileRepository;

    @Autowired
    private UserRepository userRepository;
    
    

    public Userprofile createUserProfile(Long userId, Userprofile userProfile) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        
        Userprofile userprofil=new Userprofile();
        userprofil.setUserId(userId);
        userprofil.setEmail(userProfile.getEmail());
        userprofil.setFirstName(userProfile.getFirstName());
        userprofil.setGender(userProfile.getGender());
        userprofil.setLastName(userProfile.getLastName());
        userprofil.setNumber(userProfile.getNumber());
        
        return userProfileRepository.save(userprofil);
    }



	
    public Userprofile getUserProfileByUserId(Long userId) {
        Userprofile user = userProfileRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        return user;
    }
}
