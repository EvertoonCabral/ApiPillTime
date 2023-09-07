package br.unipar.api.ApiPillTime.controller;

import br.unipar.api.ApiPillTime.exception.ApiErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.nio.file.AccessDeniedException;
import java.util.ArrayList;

@RestController
public class AplicationControllerAdvice {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrorMessage handlerException(Exception ex){
        return new ApiErrorMessage("Um erro inesperado ocorreu: " + ex.getMessage());
    }
    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ApiErrorMessage handleAccessDenied(AccessDeniedException ex) {
        return new ApiErrorMessage("Acesso negado: " + ex.getMessage());
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrorMessage handleMethodNotValidException(MethodArgumentNotValidException ex) {

        ArrayList<String> listaErros = new ArrayList<>();

        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            listaErros.add(error.getField() + ": " + error.getDefaultMessage());
        }

        return new ApiErrorMessage(listaErros);
    }



}
