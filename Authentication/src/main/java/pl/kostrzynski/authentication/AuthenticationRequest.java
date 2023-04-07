package pl.kostrzynski.authentication;

import jakarta.validation.constraints.NotNull;

record AuthenticationRequest(@NotNull String username, @NotNull String password) {
}
