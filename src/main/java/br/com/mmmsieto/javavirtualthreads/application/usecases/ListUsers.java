package br.com.mmmsieto.javavirtualthreads.application.usecases;

import br.com.mmmsieto.javavirtualthreads.application.repository.UserRepository;
import br.com.mmmsieto.javavirtualthreads.domain.User;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ListUsers {

    private final UserRepository userRepository;

    public ListUsers(UserRepository userRepository) {
        this.userRepository = Objects.requireNonNull(userRepository);
    }

    public List<Output> execute() {
        return this.userRepository.allUsers()
                .stream().map(Output::new)
                .collect(Collectors.toList());
    }

    public record Output(String id, String email) {
        public Output(User user) {
            this(user.id(), user.email());
        }
    }
}
