package MIN.DosiNongBu.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RestControllerAdvice(annotations = RestController.class)
public class ExceptionControllerAdvice {

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler
    public ResponseEntity<ErrorDto> MethodArgumentNotValidExceptionHandler(MethodArgumentNotValidException  e){
        List<String> errorMessages = new ArrayList<>();
        for(FieldError fieldError : e.getBindingResult().getFieldErrors()){
            errorMessages.add(fieldError.getDefaultMessage());
        }

        return new ResponseEntity<ErrorDto>(new ErrorDto("400", String.join(".", errorMessages)), BAD_REQUEST);
    }
}
