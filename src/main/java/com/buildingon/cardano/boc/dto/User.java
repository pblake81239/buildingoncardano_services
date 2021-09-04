package com.buildingon.cardano.boc.dto;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity(name = "User")
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
     
    @Column(name = "email")
    private String email;
    
    @Column(name = "email_confirmed")
    private String emailConfirmed;
     
    @Column(name = "password")
    private String password;
    
    @Column(name = "temp_password")
    private String tempPassword;
    
    @Column(name = "verification_code")
    private String verificationCode;
    
    @Column(name = "superuser")
    private String superuser;
     
     
//    @Column(name = "first_name", nullable = false, length = 20)
//    private String firstName;
//     
//    @Column(name = "last_name", nullable = false, length = 20)
//    private String lastName;

	
}