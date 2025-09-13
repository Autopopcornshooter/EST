package EST.blog.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    record ErrorResponse(String message){}
    @ExceptionHandler(IllegalArgumentException.class) //IllegalArgumentException 발생 시 해당 메서드 호출
    @ResponseStatus(HttpStatus.BAD_REQUEST) //HTTP 상태 코드를 bad request (400)으로 설정
    public ErrorResponse handleIllegalArgumentException(IllegalArgumentException e){
        return new ErrorResponse(e.getMessage());
    }
}
