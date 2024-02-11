package br.com.mmmsieto.javavirtualthreads.domain;

import java.util.function.Function;

public enum PasswordType {
    PLAIN(PlainTextPassword::create, PlainTextPassword::restore),
    SHA1(SHA1Password::create, SHA1Password::restore),
    PBKDF2(PBKDF2Password::create, PBKDF2Password::restore);

    private final Function<String, Password> creatFn;
    private final Function<String, Password> restorFn;

    PasswordType(Function<String, Password> creatFn,
                 Function<String, Password> restorFn) {
        this.creatFn = creatFn;
        this.restorFn = restorFn;
    }

    public Password create(final String plainPassword) {
        return creatFn.apply(plainPassword);
    }

    public Password restore(final String password) {
        return restorFn.apply(password);
    }
}

//public class PasswordType {
//
//    public static String create(String passwordType, String password) {
//
//        Object passwordStrategy = new PlainTextPassword("123456");
//
//        if (passwordStrategy instanceof PlainTextPassword(String pass)) {
//            System.out.println(pass);
//        }
//
//        var plainPass = switch (passwordStrategy) {
//            case Password p when p instanceof PlainTextPassword(String pass) -> pass;
//            case Password p when p instanceof SHA1Password(String shapass) -> shapass;
//            case PBKDF2Password p -> p.value();
//            default -> "";
//        };
//
//        return plainPass;
//    }
//
//}
