package pl.kostrzynski.authentication;

import java.util.List;

record AuthenticatedUser(String username, List<String> roles) {
}
