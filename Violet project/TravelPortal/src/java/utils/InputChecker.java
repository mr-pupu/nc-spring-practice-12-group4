/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import database.utilities.HibernateUtil;
import org.json.simple.JSONObject;

/**
 *
 * @author Master
 */
public class InputChecker {
    
    private static final String namePattern = "([A-Z]{1}([a-zA-Z])+(\\s)*)+";
    private static final String emailPattern = "^[a-z0-9!#$%&'*+/=?^_`{|}~-]+"
            + "(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?$";
    
    public static JSONObject nameCheck (String name, String fieldname) {
        if ((name != null) && (name.matches(namePattern))) {
            return null;
        }
        else {
            JSONObject obj = new JSONObject();
            obj.put("field", fieldname);
            obj.put("err", "Invalid name.");
            return obj;
        }
    }
    
    public static JSONObject emailCheck (String email, String fieldname) {
        if ((email != null) && (email.matches(emailPattern))) {
            return null;
        }
        else {
            JSONObject obj = new JSONObject();
            obj.put("field", fieldname);
            obj.put("err", "Invalid email format.");
            return obj;
        }
    }
    
    public static JSONObject loginCheck (String login, String fieldname) {
        if ((login == null) || login.equals("")) {
            JSONObject obj = new JSONObject();
            obj.put("field", fieldname);
            obj.put("err", "Invalid login.");
            return obj;
        } 
        if (!HibernateUtil.EmpIdByLogin(login).isEmpty()) {
            JSONObject obj = new JSONObject();
            obj.put("field", fieldname);
            obj.put("err", "Login already exists.");
            return obj;
        }
        return null;
    }
}
