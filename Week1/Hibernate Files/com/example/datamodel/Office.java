package com.example.datamodel;
// Generated Apr 13, 2012 2:20:12 AM by Hibernate Tools 3.2.1.GA


import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

/**
 * Office generated by hbm2java
 */
@Entity
@Table(name="OFFICE"
    ,schema="GUEST"
)
public class Office  implements java.io.Serializable {


     private long id;
     private City city;
     private Set<Employee> employees = new HashSet<Employee>(0);

    public Office() {
    }

	
    public Office(long id) {
        this.id = id;
    }
    public Office(long id, City city, Set<Employee> employees) {
       this.id = id;
       this.city = city;
       this.employees = employees;
    }
   
     @Id 
     @SequenceGenerator(name="office_id",sequenceName="office_id_seq")
    @GeneratedValue(strategy=GenerationType.SEQUENCE,generator="office_id") 

    
    @Column(name="ID", unique=true, nullable=false, precision=10, scale=0)
    public long getId() {
        return this.id;
    }
    
    public void setId(long id) {
        this.id = id;
    }
@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="CITY_ID")
    public City getCity() {
        return this.city;
    }
    
    public void setCity(City city) {
        this.city = city;
    }
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="office")
    public Set<Employee> getEmployees() {
        return this.employees;
    }
    
    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }




}


