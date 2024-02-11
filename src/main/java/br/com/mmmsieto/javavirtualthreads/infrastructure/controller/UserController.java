package br.com.mmmsieto.javavirtualthreads.infrastructure.controller;

import br.com.mmmsieto.javavirtualthreads.application.usecases.CreateUser;
import br.com.mmmsieto.javavirtualthreads.application.usecases.GetUserOfId;
import br.com.mmmsieto.javavirtualthreads.infrastructure.dtos.CreateUserInput;
import org.checkerframework.checker.units.qual.N;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping(value = "users")
public class UserController {

    private final CreateUser createUser;
    private final GetUserOfId getUserOfId;

    public UserController(CreateUser createUser,
                          GetUserOfId getUserOfId) {
        this.createUser = Objects.requireNonNull(createUser);
        this.getUserOfId = Objects.requireNonNull(getUserOfId);
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

}
