package az.express.user.management.exception;

import lombok.Getter;

@Getter
public class RoleNotFoundException extends RuntimeException {
    private final String code;
    private final String message;

    public RoleNotFoundException(String code, String message) {
        super(message);
        this.message = message;
        this.code = code;
    }
}
