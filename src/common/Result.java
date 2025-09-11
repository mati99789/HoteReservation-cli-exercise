package common;

public class Result<T> {
    private boolean ok;
    private T data;
    private String error;

    private Result(boolean ok, T data, String error) {
        this.ok = ok;
        this.data = data;
        this.error = error;
    }

    public static <T> Result<T> ok(T data) {
        return new Result<>(true, data, null);
    }

    public static <T> Result<T> error(String message) {
        return new Result<>(false, null, message);
    }

    public boolean isOk() {
        return ok;
    }

    public T getData() {
        return data;
    }

    public String getError() {
        return error;
    }
}
