package com.ecom.users.profile;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "user_profiles")
public class Userprofile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

   
private long userId;

   
    public long getUserId() {
	return userId;
}

public void setUserId(long userId) {
	this.userId = userId;
}

	@Size(max = 50)
    private String firstName;

    
    @Size(max = 50)
    private String lastName;

    private String gender;

  
    @Size(max = 100)
    private String email;

    
    @Size(max = 15)
    private String number;

    public Userprofile() {
    }

    public Userprofile( String firstName, String lastName, String gender, String email, String number) {
     
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.email = email;
        this.number = number;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

   
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}


