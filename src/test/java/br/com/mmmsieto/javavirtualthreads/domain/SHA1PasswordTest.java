package br.com.mmmsieto.javavirtualthreads.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SHA1PasswordTest {

    private static final String PASSWORD = "123456";
    @Test
    @DisplayName("should hash the password")
    void should_hash_the_password(){

        var password = SHA1Password.create(PASSWORD);

        assertNotEquals(PASSWORD, password.value());
    }

    @Test
    @DisplayName("should match passwords")
    void should_match_passwords(){

        var password = SHA1Password.create(PASSWORD);

        assertTrue(password.validate(PASSWORD));
    }

}