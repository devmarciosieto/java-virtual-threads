package br.com.mmmsieto.javavirtualthreads.domain;

public sealed interface Password permits PlainTextPassword {

    String value();

    boolean validate(String password);
}
