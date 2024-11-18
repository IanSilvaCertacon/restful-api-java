package br.com.certacon.restful_api_java_parameters.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UnsupportedMathOperationException extends RuntimeException{

    public UnsupportedMathOperationException(String ex){
        super(ex);
    }

}
