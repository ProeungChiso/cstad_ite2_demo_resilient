package co.istad.productservice.Exception;

import co.istad.productservice.base.BasedError;
import co.istad.productservice.base.BasedResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class ValidationException {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    BasedResponse<?> handleValidationException(MethodArgumentNotValidException ex){
        List<Map<String, Object>> errors = new ArrayList<>();

        ex.getBindingResult()
                .getFieldErrors()
                .forEach((error) -> {
            Map<String, Object> errorMap = new HashMap<>();
            errorMap.put("field", error.getField());
            errorMap.put("reason", error.getDefaultMessage());
            errors.add(errorMap);
        });

        BasedError<?> basedError = BasedError.builder()
                .code(HttpStatus.BAD_GATEWAY.getReasonPhrase())
                .description(errors)
                .build();

        return BasedResponse.builder()
                .error(basedError)
                .build();
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    BasedResponse<?> handleHttpMessageNotReadableError(HttpMessageNotReadableException ex) {
        BasedError<?> basedError = BasedError.builder()
                .code(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .description("The data types of submitted values don't match what the API expects")
                .build();
        return BasedResponse.builder()
                .error(basedError)
                .build();
    }
}
