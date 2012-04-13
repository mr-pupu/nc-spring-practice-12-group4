package com.example.datamodel;
// Generated Apr 13, 2012 2:20:12 AM by Hibernate Tools 3.2.1.GA


import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

/**
 * Employee generated by hbm2java
 */
@Entity
@Table(name="EMPLOYEE"
    ,schema="GUEST"
)
public class Employee  implements java.io.Serializable {


     private long id;
     private Occupation occupation;
     private Office office;
     private Department department;
     private String firstName;
     private String secondName;
     private String email;
     private String login;
     private String password;
     private Set<Trf> trfs = new HashSet<Trf>(0);

    public Employee() {
    }

	
    public Employee(long id) {
        this.id = id;
    }
    public Employee(long id, Occupation occupation, Office office, Department department, String firstName, String secondName, String email, String login, String password, Set<Trf> trfs) {
       this.id = id;
       this.occupation = occupation;
       this.office = office;
       this.department = department;
       this.firstName = firstName;
       this.secondName = secondName;
       this.email = email;
       this.login = login;
       this.password = password;
       this.trfs = trfs;
    }
   
     @Id 
     @SequenceGenerator(name="destination_id",sequenceName="destination_id_seq")
    @GeneratedValue(strategy=GenerationType.SEQUENCE,generator="destination_id")

    
    @Column(name="ID", unique=true, nullable=false, precision=10, scale=0)
    public long getId() {
        return this.id;
    }
    
    public void setId(long id) {
        this.id = id;
    }
@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="POSITION_ID")
    public Occupation getOccupation() {
        return this.occupation;
    }
    
    public void setOccupation(Occupation occupation) {
        this.occupation = occupation;
    }
@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="OFFICE_ID")
    public Office getOffice() {
        return this.office;
    }
    
    public void setOffice(Office office) {
        this.office = office;
    }
@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="DEP_ID")
    public Department getDepartment() {
        return this.department;
    }
    
    public void setDepartment(Department department) {
        this.department = department;
    }
    
    @Column(name="FIRST_NAME", length=20)
    public String getFirstName() {
        return this.firstName;
    }
    
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    @Column(name="SECOND_NAME", length=20)
    public String getSecondName() {
        return this.secondName;
    }
    
    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }
    
    @Column(name="EMAIL", length=256)
    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    @Column(name="LOGIN", length=20)
    public String getLogin() {
        return this.login;
    }
    
    public void setLogin(String login) {
        this.login = login;
    }
    
    @Column(name="PASSWORD", length=30)
    public String getPassword() {
        return this.password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="employee")
    public Set<Trf> getTrfs() {
        return this.trfs;
    }
    
    public void setTrfs(Set<Trf> trfs) {
        this.trfs = trfs;
    }




}


