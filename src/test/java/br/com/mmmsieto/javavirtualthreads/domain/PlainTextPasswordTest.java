package br.com.mmmsieto.javavirtualthreads.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlainTextPasswordTest {

    @Test
    @DisplayName("should create a plain password")
    void should_create_a_plain_password() {

        final var expectedPassword = "123456";

        var plainTextPassword = new PlainTextPassword(expectedPassword);

        assertTrue(plainTextPassword.validate(expectedPassword));
    }

}