package com.buildingon.cardano.boc.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.buildingon.cardano.boc.dto.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
    @Query("SELECT u FROM User u WHERE u.email = ?1")
    public User findByEmail(String email);
    
    @Query(value = "SELECT u.id, u.email, u.password, u.temp_password, u.superuser, u.email_confirmed, u.verification_code FROM public.users u where email = ?1", nativeQuery = true)
    public User findByEmailNative(String email);

    
    @Query("SELECT u FROM User u WHERE u.verificationCode = ?1")
    public User findByVerificationCode(String code);
    
}
