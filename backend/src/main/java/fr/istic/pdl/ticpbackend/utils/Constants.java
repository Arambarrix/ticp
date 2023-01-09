package fr.istic.pdl.ticpbackend.utils;

import org.json.simple.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class Constants {
    public static final String URL = "api/v1/";
    public static final String TOURNOIS = URL+"tournoi/";
    public static final String TABLEAUX = URL+"tableaux/";
    public static final String POULES = URL+"groupes/";
    public static final String EQUIPES = URL+"equipes/";
    public static final String JOUEURS = URL+"joueurs/";
    public static final String MATCHSTABLEAU = URL+"match_poules/";
    public static final String MATCHSPOULE = URL+"match_tableaux/";
    public static final String ADMIN = URL+"admin/";
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
