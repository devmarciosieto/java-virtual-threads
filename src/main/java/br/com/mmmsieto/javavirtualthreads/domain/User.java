package br.com.mmmsieto.javavirtualthreads.domain;

public record User(
        String id,
        String email,
        Password password
) {

    public static User create(String email, PasswordType passwordType, String password) {
        return new User(IdGenerator.nextId(), email, passwordType.create(password));
    }

}
