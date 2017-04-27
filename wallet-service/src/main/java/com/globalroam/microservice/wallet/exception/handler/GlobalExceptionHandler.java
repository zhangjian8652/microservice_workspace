package com.globalroam.microservice.wallet.exception.handler;


import com.globalroam.microservice.wallet.entity.APIResponse;
import com.globalroam.microservice.wallet.exception.DataNotFoundException;
import com.globalroam.microservice.wallet.exception.InvalidRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * @Author zhangjian
 * @Date 2017/2/13
 * @Copyright:
 * @Describe:
 */
@RestController
@ControllerAdvice
public class GlobalExceptionHandler extends Throwable {


    public GlobalExceptionHandler() {
    }


    @ExceptionHandler(InvalidRequestException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public Object handleInvalidRequestError(InvalidRequestException ex) {
        return new APIResponse<Object>(ex.getCode(), ex.getMessage());
    }


    @ExceptionHandler(DataNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ResponseBody
    public Object handleDataNotFoundException(DataNotFoundException ex) {
        return new APIResponse<Object>(ex.getCode(), ex.getMessage());
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ResponseBody
    public Object handleNoHandlerFoundException(NoHandlerFoundException ex) {
        return new APIResponse<Object>("404", ex.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public Object handleUnexpectedServerError(RuntimeException ex) {
        return new APIResponse<Object>("500", "internal server error : " + ex.getMessage());
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public Object defaultErrorHandler(Exception e) throws Exception {
        return new APIResponse<Object>("500", "internal server error : " + e.getMessage());
    }


}
