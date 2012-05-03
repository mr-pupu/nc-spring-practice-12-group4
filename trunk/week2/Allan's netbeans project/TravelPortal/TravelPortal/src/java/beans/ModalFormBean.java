/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import database.mapping.Trf;
import java.io.Serializable;
import java.util.Date;

/**
 * Bean class for handling forms in modalform
 * @author Master
 */
public class ModalFormBean implements Serializable{
    
    private String employee;
    private Date begin;
    private Date end;
    private Long country;
    private Long city;
    private Long projectManager;
    private String hotelName;
    private String hotelSite;
    private Long customer;
    private Boolean car;
    private Boolean cash;
    private Trf trf;

    public ModalFormBean() {
        employee="";
        hotelName="";
        hotelSite="";
        trf = null;
    }

    public Date getBegin() {
        return begin;
    }

    public Boolean getCar() {
        return car;
    }

    public Boolean getCash() {
        return cash;
    }

    public Long getCity() {
        return city;
    }

    public Long getCountry() {
        return country;
    }

    public Long getCustomer() {
        return customer;
    }

    public String getEmployee() {
        return employee;
    }

    public Date getEnd() {
        return end;
    }

    public String getHotelName() {
        return hotelName;
    }

    public String getHotelSite() {
        return hotelSite;
    }

    public Long getProjectManager() {
        return projectManager;
    }

    public void setBegin(Date begin) {
        this.begin = begin;
    }

    public void setCar(Boolean car) {
        this.car = car;
    }

    public void setCash(Boolean cash) {
        this.cash = cash;
    }

    public void setCity(Long city) {
        this.city = city;
    }

    public void setCountry(Long country) {
        this.country = country;
    }

    public void setCustomer(Long customer) {
        this.customer = customer;
    }

    public void setEmployee(String employee) {
        this.employee = employee;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public void setHotelSite(String hotelSite) {
        this.hotelSite = hotelSite;
    }

    public void setProjectManager(Long projectManager) {
        this.projectManager = projectManager;
    }

    public Trf getTrf() {
        return trf;
    }

    public void setTrf(Trf trf) {
        this.trf = trf;
    }
    
}
