package me.tomaszterlecki.travel.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.BAD_REQUEST, reason="Delete pictures first")
public class DeleteObjectException extends RuntimeException {
    private int objectId;

}
