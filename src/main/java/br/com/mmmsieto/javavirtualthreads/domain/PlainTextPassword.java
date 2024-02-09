package br.com.mmmsieto.javavirtualthreads.domain;

public record PlainTextPassword(String value) implements Password {

    @Override
    public boolean validate(final String password) {
        return value().equals(password);
    }
}
