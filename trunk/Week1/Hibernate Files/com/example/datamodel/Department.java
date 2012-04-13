package com.example.datamodel;
// Generated Apr 13, 2012 2:20:12 AM by Hibernate Tools 3.2.1.GA


import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

/**
 * Department generated by hbm2java
 */
@Entity
@Table(name="DEPARTMENT"
    ,schema="GUEST"
)
public class Department  implements java.io.Serializable {


     private long id;
     private Department department;
     private String depName;
     private Long managerId;
     private Set<Department> departments = new HashSet<Department>(0);
     private Set<Employee> employees = new HashSet<Employee>(0);

    public Department() {
    }

	
    public Department(long id) {
        this.id = id;
    }
    public Department(long id, Department department, String depName, Long managerId, Set<Department> departments, Set<Employee> employees) {
       this.id = id;
       this.department = department;
       this.depName = depName;
       this.managerId = managerId;
       this.departments = departments;
       this.employees = employees;
    }
   
     @Id  
     @SequenceGenerator(name="department_id",sequenceName="department_id_seq")
    @GeneratedValue(strategy=GenerationType.SEQUENCE,generator="department_id") 

    
    @Column(name="ID", unique=true, nullable=false, precision=10, scale=0)
    public long getId() {
        return this.id;
    }
    
    public void setId(long id) {
        this.id = id;
    }
@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="PARENT_ID")
    public Department getDepartment() {
        return this.department;
    }
    
    public void setDepartment(Department department) {
        this.department = department;
    }
    
    @Column(name="DEP_NAME", length=40)
    public String getDepName() {
        return this.depName;
    }
    
    public void setDepName(String depName) {
        this.depName = depName;
    }
    
    @Column(name="MANAGER_ID", precision=10, scale=0)
    public Long getManagerId() {
        return this.managerId;
    }
    
    public void setManagerId(Long managerId) {
        this.managerId = managerId;
    }
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="department")
    public Set<Department> getDepartments() {
        return this.departments;
    }
    
    public void setDepartments(Set<Department> departments) {
        this.departments = departments;
    }
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="department")
    public Set<Employee> getEmployees() {
        return this.employees;
    }
    
    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }




}


