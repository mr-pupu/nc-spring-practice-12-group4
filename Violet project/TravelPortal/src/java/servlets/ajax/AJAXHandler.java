package servlets.ajax;

import database.mapping.Employee;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpServlet;


/**
 *
 * @author Merle
 */

abstract public class AJAXHandler extends HttpServlet {
    private static SimpleDateFormat formate = new SimpleDateFormat("dd-MM-yyyy");
    
    public static String getEmployeeFullName(Employee employee){
        return employee.getFirstName() + ", " + employee.getSecondName();
    }
    
    public static String getDateFormated(Date date){
        return formate.format(date);
    }
    
    public static Date getDateFromString(String string) throws ParseException{
        return formate.parse(string);
    }
}
