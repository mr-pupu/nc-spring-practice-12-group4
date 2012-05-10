/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets.ajax;

import database.mapping.*;
import database.utilities.AdministratorDesktop;
import database.utilities.HibernateUtil;
import database.utilities.TrfEdit;
import java.io.IOException;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author Merle
 */
abstract public class AJAXSendHandler extends AJAXHandler {

    public static void putEmployeeToJSON(JSONObject jsonObject, Employee employee) {
        jsonObject.put("employeeName", getEmployeeFullName(employee));
        jsonObject.put("employeeId", employee.getId());
    }

    public static void putOfficeToJSON(JSONObject jsonObject, Office office) {
        City city = office.getCity();
        String officeName = city.getCountry().getCountryName() + ", " + city.getCityName();
        jsonObject.put("officeName", officeName);
        jsonObject.put("officeId", office.getId());
    }

    public static void putDatesToJson(JSONObject jsonObject, Date beginDate, Date endDate) {
        jsonObject.put("beginDate", getDateFormated(beginDate));
        jsonObject.put("endDate", getDateFormated(beginDate));
    }

    public static void putCountriesToJson(JSONObject jsonObject) {
        List<Country> countries = HibernateUtil.CountryList();
        Map<Long, String> countriesMap = new HashMap<Long, String>();
        for (Country country : countries) {
            countriesMap.put(country.getId(), country.getCountryName());
        }
        jsonObject.put("countries", countriesMap);
    }
    /* Methods that put all offices and item <all> to json string
     * @param json string with form data
     * author OleksandrDudinskyi
     */
    public static void putAllOfficesToJSON(JSONObject jsonObject) {
        List<Office> offices = HibernateUtil.OfficesList();
        Map<Long, String> officesMap = new HashMap<Long, String>();
        int i = 0;
        for (Office office : offices) {
            i++;
            officesMap.put(office.getId(), office.getOfficeName());
            if (i == offices.size()) {
                officesMap.put(office.getId() + 1, "All");
            }
        }
        jsonObject.put("offices", officesMap);
    }
    /*
     * Methods that put all departments and item <all> to json string
     * @param json string with form data
     * author OleksandrDudinskyi
     */
    public static void putAllDepartmentToJSON(JSONObject jsonObject) {
        List<Department> departments = HibernateUtil.DepartmentsList();
        Map<Long, String> departmentsMap = new HashMap<Long, String>();
        int i = 0;
        for (Department dep : departments) {
            i++;
            departmentsMap.put(dep.getId(), dep.getDepName());
            if (i == departments.size()) {
                departmentsMap.put(dep.getId() + 1, "All");
            }
        }
        jsonObject.put("departments", departmentsMap);
        System.out.print(jsonObject);
    }
    public static void putPositionsToJSON(JSONObject jsonObject) {
       List<Occupation> positions = HibernateUtil.OccupationsList();
       Map<Long, String> positionsMap = new HashMap<Long, String>();
       for (Occupation pos: positions) {
            positionsMap.put(pos.getId(), pos.getPosName());
        }
        jsonObject.put("positions", positionsMap);
    }
    
    public static void putDepEmployersToJSON(JSONObject jsonObject, Department dep) {
       Set<Employee> depemployees= dep.getEmployees();
       Map<Long, String> depemployeesMap = new HashMap<Long, String>();
       for (Employee emp: depemployees) {
            depemployeesMap.put(emp.getId(), emp.getFirstName()+' '+emp.getSecondName());
       }
       jsonObject.put("depemployees", depemployeesMap);
    }
    
    public static void putDepRolesToJSON(JSONObject jsonObject, Department dep) {
       Set<Deprole> deproles= dep.getDeprole();
       List<Deprole> allroles = HibernateUtil.DeprolesList();
       ArrayList<Long> deprolesid= new ArrayList<Long>(); 
       Map<Long, String> allrolesMap = new HashMap<Long, String>();
       for (Deprole role: allroles) {
            allrolesMap.put(role.getId(), role.getRoleName());
       }
       for (Deprole role: deproles){
           deprolesid.add(role.getId());
       }
       jsonObject.put("allroles", allrolesMap);
       jsonObject.put("deproles", deprolesid);
       jsonObject.put("rolesNumber", allroles.size());
    }
    
     public static void putOfficesToJSON(JSONObject jsonObject) {
       List<Office> offices = HibernateUtil.OfficesList();
       Map<Long, String> officesMap = new HashMap<Long, String>();
       for (Office office: offices) {
            officesMap.put(office.getId(), office.getOfficeName());
        }
        jsonObject.put("offices", officesMap);
    }
     
    public static void putDepartmentsToJSON(JSONObject jsonObject) {
       List<Department> departments = HibernateUtil.DepartmentsList();
       Map<Long, String> departmentsMap = new HashMap<Long, String>();
       for (Department dep: departments) {
            departmentsMap.put(dep.getId(), dep.getDepName());
        }
        jsonObject.put("departments", departmentsMap);
    }

    public static void putCitiesToJson(JSONObject jsonObject, Country country) {
        Set<City> cities = country.getCities();
        Map<Long, String> citiesMap = new HashMap<Long, String>();
        for (City city : cities) {
            citiesMap.put(city.getId(), city.getCityName());
        }
        jsonObject.put("cities", citiesMap);
    }

    public static void putLineManagerByEmployeeLogin(JSONObject jsonObject, String login) {
        String[] arrLineManager = TrfEdit.LineManager(login);
        String lineMgrName = arrLineManager[0];
        jsonObject.put("lineManagerName", lineMgrName);
    }

    public static void putProjectManagerToJSON(JSONObject jsonObject, Employee manager) {
        Map<Long, String> managersMap = new HashMap<Long, String>();
        Set<Employee> managers = manager.getDepartment().getEmployees();
        for (Employee employee : managers) {
            managersMap.put(employee.getId(), getEmployeeFullName(employee));
        }
        jsonObject.put("projectManagers", managersMap);
        jsonObject.put("projectManagerId", manager.getId());
    }

    public static void putCitiesAndCountriesToJSON(JSONObject jsonObject, City city) {
        jsonObject.put("cityId", city.getId());
        jsonObject.put("countryId", city.getCountry().getId());
        putCountriesToJson(jsonObject);
        putCitiesToJson(jsonObject, city.getCountry());
    }

    public static void putCustomersToJSON(JSONObject jsonObject, Customer customer) {
        List<Customer> customers = TrfEdit.Customers();
        Map<Long, String> customersMap = new HashMap<Long, String>();
        for (Customer eachCustomer : customers) {
            customersMap.put(eachCustomer.getId(), eachCustomer.getCustName());
        }
        jsonObject.put("customers", customersMap);
        jsonObject.put("customerId", customer.getId());
    }

    public static void putCustomersToJSON(JSONObject jsonObject) {
       List<Customer> customers = TrfEdit.Customers();
        Map<Long, String> customersMap = new HashMap<Long, String>();
        for (Customer eachCustomer : customers) {
            customersMap.put(eachCustomer.getId(), eachCustomer.getCustName());
        }
        jsonObject.put("customers", customersMap);
    }

    public static void putDestinationsToJSON(JSONObject jsonObject, City city) {
        Set<Destination> destinations = (city.getDestinations());
        Map<Long, String> hotelNames = new HashMap<Long, String>();
        Map<Long, String> hotelSites = new HashMap<Long, String>();
        for (Destination destination : destinations) {
            hotelNames.put(destination.getId(), destination.getHotelname());
            hotelSites.put(destination.getId(), destination.getHotelsite());
        }
        jsonObject.put("hotelNames", hotelNames);
        jsonObject.put("hotelSites", hotelSites);
    }
    public static void putChildDepartments(JSONObject json, Long id, int level) {
        List<Department> deps = AdministratorDesktop.ChildDeps(id);
        JSONArray jsarr = new JSONArray();
        for (Department dep : deps) {
            JSONObject js = new JSONObject();
            js.put("id", dep.getId());
            js.put("name", dep.getDepName());
            if (!dep.getDepartments().isEmpty()) {
                js.put("expanded", true);
                js.put("isLeaf", false);
            } else {
                js.put("expanded", false);
                js.put("isLeaf", true);
            }
            js.put("parent", id.longValue());
            js.put("loadonce", true);
            js.put("level", (level+1));
            jsarr.add(js);
        }
        json.put("rows", jsarr);
    }

    public static void putHeadDepartments(JSONObject jsonObject) {
        List<Department> deps = AdministratorDesktop.HeadDepartments();
//        Map<Long, String> depmap = new HashMap<Long, String>();
        JSONArray jsarr = new JSONArray();
        for (Department dep : deps) {
            JSONObject js = new JSONObject();
//            epmap.put(dep.getId(), dep.getDepName());
            js.put("id", dep.getId());
            js.put("name", dep.getDepName());
            if (!dep.getDepartments().isEmpty()) {
                js.put("expanded", true);
                js.put("isLeaf", false);
            } else {
                js.put("expanded", false);
                js.put("isLeaf", true);
            }
            js.put("parent", null);
            js.put("loadonce", true);
            js.put("level", 0);
            jsarr.add(js);
        }
        jsonObject.put("rows", jsarr);

//        jsonObject.put("deps", depmap);
    }
}
