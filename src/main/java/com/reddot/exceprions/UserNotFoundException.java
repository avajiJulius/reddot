package com.reddot.exceprions;

public class UserNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 6829572932258750125L;

    public UserNotFoundException() {
        super();
    }

    public UserNotFoundException(String message) {
        super(message);
    }


    public UserNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserNotFoundException(Throwable cause) {
        super(cause);
    }


}
