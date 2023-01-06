package fr.istic.pdl.ticpbackend.utils;

import org.json.simple.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class Constants {
    static public ResponseEntity<Object> error(Exception e, HttpStatus httpStatus){
        JSONObject obj = new JSONObject();
        obj.put("message",e.getMessage());
        return new ResponseEntity<>(obj , HttpStatus.valueOf(httpStatus.value()));
    }
}
