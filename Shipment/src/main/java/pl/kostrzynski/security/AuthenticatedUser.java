package pl.kostrzynski.security;

import java.util.List;

record AuthenticatedUser(String username, List<String> roles) {
}
