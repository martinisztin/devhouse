package com.martinisztin.varazslak;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
class VarazslakApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    void testPassword() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String rawPassword = "n16645";
        String password = "$2a$10$6OTykjXLci9UUqCnDP9FheHUynzGXH3sA8rV/qErHSaZCx/MVwZfC";

        assert encoder.matches(rawPassword, password);
    }

}
