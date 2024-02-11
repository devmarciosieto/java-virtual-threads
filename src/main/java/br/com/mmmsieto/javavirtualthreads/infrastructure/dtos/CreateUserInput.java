package br.com.mmmsieto.javavirtualthreads.infrastructure.dtos;

import br.com.mmmsieto.javavirtualthreads.domain.PasswordType;

public record CreateUserInput(
        String email,
        PasswordType passwordType,
        String password
) {
}
