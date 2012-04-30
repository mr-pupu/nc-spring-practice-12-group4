package com.example.datamodel;
// Generated Apr 13, 2012 2:20:12 AM by Hibernate Tools 3.2.1.GA

import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

/**
 * Role generated by hbm2java
 */
@Entity
@Table(name = "ROLEDEP", schema = "GUEST")
public class Roledep implements java.io.Serializable {

    private Role role;
    private Department dep;

    public Roledep() {
    }

    public Roledep(Role role, Department dep) {
        this.role = role;
        this.dep = dep;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ROLE_ID")
    public long getRole() {
        return this.role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DEP_ID")
    public String getDepartment() {
        return this.dep;
    }

    public void setRoleName(Department dep) {
        this.dep = dep;
    }

}
