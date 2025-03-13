package com.xzit;

import com.xzit.rental.security.PasswordConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.security.SecureRandom;

@SpringBootTest
class AutoRentalApplicationTests {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private PasswordConfig passwordConfig;

    @Test
    void contextLoads() {
        System.out.println(passwordConfig.passwordEncoder().encode("123456"));
        System.out.println(passwordEncoder.encode("123456"));
        String encodedPassword = "$2a$06$2NPuAHzfc/dqkSSLgy4KfOfs9c0E4KE3VHZzRWMcUTNWyYzL2goHi";
        boolean matches = passwordEncoder.matches("123456", encodedPassword);
        System.out.println("Password matches: " + matches);
    }

}
