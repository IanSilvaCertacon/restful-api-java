package br.com.certacon.restful_api_java_obj_list.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UnsupportedOperationException extends RuntimeException{

    public UnsupportedOperationException(String ex){
        super(ex);
    }

}
