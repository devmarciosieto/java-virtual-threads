package br.com.mmmsieto.javavirtualthreads.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PasswordTypeTest {

    @Test
    void deveriaCriarPlainTextPassword() {

        final var expectedPassword = "123456";

        final var actualPassword = PasswordType.PLAIN.create(expectedPassword);

        assertEquals(expectedPassword, actualPassword.value());
    }

}