package fr.istic.pdl.ticpbackend.utils;

import org.json.simple.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class Constants {
    static public ResponseEntity<Object> error(Exception e, int code){
        JSONObject obj = new JSONObject();
        obj.put("errors",e.getMessage());
        obj.put("code", code);

        return new ResponseEntity<>(obj , HttpStatus.OK);
    }

    static public ResponseEntity<Object> success(Object data, int code){
        JSONObject obj = new JSONObject();
        obj.put("code",code);
        obj.put("data",data);

        return new ResponseEntity<>(obj , HttpStatus.OK);
    }
}
