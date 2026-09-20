package com.spring.SchoolManagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SchoolManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(SchoolManagementApplication.class, args);
	}

}


//web page :- http://localhost:12345/login ( hit on this link )
//after login shows students details 







// step 1:- first register users;
//   http://localhost:12345/api/auth/register
//  {
//  "username": "ravi",
 //  "password": "123"
 //  }

//step 2:- check login
//    http://localhost:12345/api/auth/login
//{
//"username": "ravi",
//  "password": "123"
//  }


