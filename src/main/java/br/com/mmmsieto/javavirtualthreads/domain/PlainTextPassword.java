package br.com.mmmsieto.javavirtualthreads.domain;

public record PlainTextPassword(String value) implements Password {

    public static PlainTextPassword create(String password) {
        return new PlainTextPassword(password);
    }

    public static PlainTextPassword restore(String password) {
        return new PlainTextPassword(password);
    }

    @Override
    public boolean validate(final String password) {
        return value().equals(password);
    }
}
