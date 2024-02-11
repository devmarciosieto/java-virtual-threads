package br.com.mmmsieto.javavirtualthreads.infrastructure.repositories;

import br.com.mmmsieto.javavirtualthreads.application.repository.UserRepository;
import br.com.mmmsieto.javavirtualthreads.domain.User;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class UserInMemoryRepository implements UserRepository {

    private final Map<String, User> table;
    private final Map<String, User> emailIndex;

    public UserInMemoryRepository() {
        this.table = new ConcurrentHashMap<>();
        this.emailIndex = new ConcurrentHashMap<>();
    }

    @Override
    public User save(User user) {
        this.table.put(user.id(), user);
        this.emailIndex.put(user.email(), user);
        return user;
    }

    @Override
    public Optional<User> userOfId(String userId) {
        return Optional.ofNullable(this.table.get(userId));
    }

    @Override
    public Optional<User> userOfEmail(String email) {
        return Optional.ofNullable(this.emailIndex.get(email));
    }

    @Override
    public Collection<User> allUsers() {
        return this.table.values();
    }

}
