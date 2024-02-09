package br.com.mmmsieto.javavirtualthreads.domain;

import com.google.common.hash.Hashing;

import java.nio.charset.StandardCharsets;

public record SHA1Password(String value) implements Password {

    public static SHA1Password create(String password) {
        return new SHA1Password(hash(password));
    }

    public static SHA1Password restore(String password) {
        return new SHA1Password(password);
    }

    @Override
    public boolean validate(final String password) {
        return value().equals(hash(password));
    }

    private static String hash(final String plainText) {
        return Hashing.sha1().hashString(plainText, StandardCharsets.UTF_8)
                .toString();
    }
}
