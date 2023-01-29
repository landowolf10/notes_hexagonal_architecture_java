package com.lando.notes_hexagonal_architecture_java.exceptions;

import com.lando.notes_hexagonal_architecture_java.notes.domain.dto.ErrorDetailDTO;
import com.lando.notes_hexagonal_architecture_java.notes.domain.dto.ValidationErrorDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@RestControllerAdvice
public class RestExceptionHandler {

    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler(MethodArgumentNotValidException.class)
    ErrorDetailDTO handleMethodArgumentNotValidException(MethodArgumentNotValidException argumentNotValidException) {
        //Llenar los detalles del error
        ErrorDetailDTO errorDetail = new ErrorDetailDTO();
        errorDetail.setTimeStamp(new Date().getTime());
        errorDetail.setStatus(HttpStatus.BAD_REQUEST.value());
        errorDetail.setTitle("Validation error");
        errorDetail.setDetail("El formulario tiene algunos errores de validación");

        // crear y agregar los errores de validación
        List<FieldError> fieldErrors = argumentNotValidException.getBindingResult().getFieldErrors();

        for (FieldError fe : fieldErrors) {
            List<ValidationErrorDTO> validationErrorList = errorDetail.getErrors().get(fe.getField());

            if (validationErrorList == null) {
                validationErrorList = new ArrayList<>();
                errorDetail.getErrors().put(fe.getField(), validationErrorList);
            }

            ValidationErrorDTO validationError = new ValidationErrorDTO();
            validationError.setCode(fe.getCode());
            validationError.setMessage(messageSource.getMessage(fe, Locale.getDefault()));
            validationErrorList.add(validationError);
        }

        return errorDetail;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(EntityNotFoundException.class)
    ErrorDetailDTO handleEntityNotFoundException() {
        ErrorDetailDTO errorDetail = new ErrorDetailDTO(); // llenar los detalles del error
        errorDetail.setTimeStamp(new Date().getTime());
        errorDetail.setStatus(HttpStatus.NOT_FOUND.value());
        errorDetail.setTitle("Resource doesn't exists");
        errorDetail.setDetail("The resource you're trying to access doesn't exists.");

        return errorDetail;
    }
}