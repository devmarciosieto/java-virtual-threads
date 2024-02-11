package br.com.mmmsieto.javavirtualthreads.infrastructure.controller;

import br.com.mmmsieto.javavirtualthreads.application.usecases.CreateUser;
import br.com.mmmsieto.javavirtualthreads.application.usecases.GetUserOfId;
import br.com.mmmsieto.javavirtualthreads.application.usecases.ListUsers;
import br.com.mmmsieto.javavirtualthreads.infrastructure.dtos.CreateUserInput;
import org.checkerframework.checker.units.qual.N;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestController
@RequestMapping(value = "users")
public class UserController {

    private static final ExecutorService EXECUTOR = Executors.newVirtualThreadPerTaskExecutor();

    private final CreateUser createUser;
    private final GetUserOfId getUserOfId;

    private final ListUsers listUsers;

    public UserController(CreateUser createUser,
                          GetUserOfId getUserOfId,
                          ListUsers listUsers) {
        this.createUser = Objects.requireNonNull(createUser);
        this.getUserOfId = Objects.requireNonNull(getUserOfId);
        this.listUsers = Objects.requireNonNull(listUsers);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody CreateUserInput input,
                                    @RequestParam(name = "latency", defaultValue = "10", required = false) int latency) throws InterruptedException {
        Thread.sleep(latency);
        try {
            final var output = createUser.execute(new CreateUser.Input(input.email(), input.passwordType(), input.password()));
            return ResponseEntity.created(URI.create(STR."/users/\{output.userId()}")).body(output);
        } catch (Throwable t) {
            return ResponseEntity.internalServerError().body(Map.of("error", t.getMessage()));
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<?> userOfId(@PathVariable String id) {
        try {
            final var output = this.getUserOfId.execute(new GetUserOfId.Input(id));
            return ResponseEntity.ok(output);
        } catch (RuntimeException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public List<ListUsers.Output> allList(@RequestParam(required = false, defaultValue = "", name = "ids") String ids) {

        if (ids.isEmpty()) {
            return this.listUsers.execute();
        } else {

            var allids = ids.split(",");

            var futures = Arrays.stream(allids)
                    .map(GetUserOfId.Input::new)
                    .map(input -> EXECUTOR.submit(() -> this.getUserOfId.execute(input)))
                    .toList();

            return futures.stream()
                    .map(f -> {
                        try {
                            return f.get();
                        } catch (InterruptedException | ExecutionException ex) {
                            throw new RuntimeException(ex);
                        }
                    })
                    .map(it -> new ListUsers.Output(it.id(), it.email()))
                    .toList();

        }
    }

}
