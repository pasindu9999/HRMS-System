package com.example.hrms.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class LeaveNotFoundException extends RuntimeException {

    public LeaveNotFoundException(String msg){
        super(msg);
    }
}
