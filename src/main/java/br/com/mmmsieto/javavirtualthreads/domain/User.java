package br.com.mmmsieto.javavirtualthreads.domain;

public record User(
        String id,
        String email,
        String password,
        String passwordType) {
}
