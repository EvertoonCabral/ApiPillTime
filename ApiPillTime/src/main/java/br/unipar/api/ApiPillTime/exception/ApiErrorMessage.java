package br.unipar.api.ApiPillTime.exception;

import java.util.Arrays;
import java.util.List;

public class ApiErrorMessage {

    private Object error;

    public Object getError() {
        return error;
    }

    public void setError(Object error) {
        this.error = error;
    }

    public ApiErrorMessage(Object error) {
        this.error = error;
    }

    public static ApiErrorMessage fromString(String errorMessage) {
        return new ApiErrorMessage(errorMessage);
    }

    public static ApiErrorMessage fromList(List<String> errorList) {
        return new ApiErrorMessage(errorList);
    }

    public static ApiErrorMessage fromException(Exception exception) {
        return new ApiErrorMessage(exception.getMessage());
    }

}
