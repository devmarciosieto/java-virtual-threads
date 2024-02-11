package br.com.mmmsieto.javavirtualthreads.application.usecases;

import br.com.mmmsieto.javavirtualthreads.application.repository.UserRepository;
import br.com.mmmsieto.javavirtualthreads.domain.PasswordType;
import br.com.mmmsieto.javavirtualthreads.domain.User;

public class CreateUser {

    private final UserRepository userRepository;

    public CreateUser(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Output execute(final Input input) {

        final var aUser = this.userRepository.save(User.create(input.email, input.passwordType, input.password));

        return new Output(aUser.id());
    }

    public record Input(String email, PasswordType passwordType, String password) {}

    public record Output(String userId) {}

}
