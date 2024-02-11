package br.com.mmmsieto.javavirtualthreads.infrastructure.configurations;

import br.com.mmmsieto.javavirtualthreads.application.repository.UserRepository;
import br.com.mmmsieto.javavirtualthreads.application.usecases.CreateUser;
import br.com.mmmsieto.javavirtualthreads.application.usecases.GetUserOfId;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfig {

    private final UserRepository userRepository;

    public UseCaseConfig(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Bean
    public CreateUser createUser() {
        return new CreateUser(userRepository);
    }

    @Bean
    public GetUserOfId getUserOfId() {
        return new GetUserOfId(userRepository);
    }

}
