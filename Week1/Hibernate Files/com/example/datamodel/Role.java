package com.example.datamodel;
// Generated Apr 13, 2012 2:20:12 AM by Hibernate Tools 3.2.1.GA


import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

/**
 * Role generated by hbm2java
 */
@Entity
@Table(name="ROLE"
    ,schema="GUEST"
)
public class Role  implements java.io.Serializable {


     private long id;
     private String roleName;
     private Set<Department> department = new HashSet<Department>(0);

    public Role() {
    }

	
    public Role(long id) {
        this.id = id;
    }
    public Role(long id, String roleName) {
       this.id = id;
       this.roleName = roleName;
    }
   
     @Id 
     @SequenceGenerator(name="role_id",sequenceName="role_id_seq")
    @GeneratedValue(strategy=GenerationType.SEQUENCE,generator="role_id") 
    
    @Column(name="ID", unique=true, nullable=false, precision=10, scale=0)
    public long getId() {
        return this.id;
    }
    
    public void setId(long id) {
        this.id = id;
    }
    
    @Column(name="ROLE_NAME", length=200)
    public String getRoleName() {
        return this.roleName;
    }
    
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @ManyToMany(cascade= CascadeType.ALL, fetch= FetchType.LAZY, mappedBy="role")
    public Set<Department> getDepartment(){
        return this.department;
    }

    public void setDepartment(Set<Department> department){
        this.department = department;
    }
}


