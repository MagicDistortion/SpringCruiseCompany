package project.models;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    PASSENGER, ADMIN, STAFF, NOT_ASSIGNED;

    @Override
    public String getAuthority() {
        return name();
    }
}
