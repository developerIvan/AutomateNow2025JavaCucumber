package ResultPattern;

import java.util.Optional;

public class Result<T> {
    private final T value;
    private final String error;

    private Result(T value, String error) {
        this.value = value;
        this.error = error;
    }

    // Método para éxito
    public static <T> Result<T> success(T value) {
        return new Result<>(value, null);
    }


    public static <T> Result<T> failure(String error) {
        return new Result<T>(null, error);
    }


    public boolean isSuccess() {
        return value != null;
    }

    public boolean isFailure() {
        return error != null;
    }

    public Optional<T> getValue() {
        return Optional.ofNullable(value);
    }


    public Optional<String> getError() {
        return Optional.ofNullable(error);
    }
}
