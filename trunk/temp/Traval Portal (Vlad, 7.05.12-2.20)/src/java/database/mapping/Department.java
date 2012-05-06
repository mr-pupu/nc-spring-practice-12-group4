package database.mapping;
// Generated Apr 27, 2012 12:30:04 PM by Hibernate Tools 3.2.1.GA

import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

/**
 * Department generated by hbm2java
 */
@Entity
@Table(name = "DEPARTMENT", schema = "GUEST", uniqueConstraints =
@UniqueConstraint(columnNames = "MANAGER_ID"))
public class Department implements java.io.Serializable {

    private long id;
    private Department department;
    private String depName;
    private Long managerId;
    private Set<Department> departments = new HashSet<Department>(0);
    private Set<Employee> employees = new HashSet<Employee>(0);
    private Set<Deprole> deprole = new HashSet<Deprole>(0);

    public Department() {
    }

    public Department(long id) {
        this.id = id;
    }

    public Department(long id, Department department, String depName, Long managerId, Set<Department> departments, Set<Employee> employees, Set<Deprole> deproles) {
        this.id = id;
        this.department = department;
        this.depName = depName;
        this.managerId = managerId;
        this.departments = departments;
        this.employees = employees;
        this.deprole = deproles;
    }

    @Id
    @Column(name = "ID", unique = true, nullable = false, precision = 10, scale = 0)
    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PARENT_ID")
    public Department getDepartment() {
        return this.department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    @Column(name = "DEP_NAME", length = 40)
    public String getDepName() {
        return this.depName;
    }

    public void setDepName(String depName) {
        this.depName = depName;
    }

    @Column(name = "MANAGER_ID", unique = true, precision = 10, scale = 0)
    public Long getManagerId() {
        return this.managerId;
    }

    public void setManagerId(Long managerId) {
        this.managerId = managerId;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "department")
    public Set<Department> getDepartments() {
        return this.departments;
    }

    public void setDepartments(Set<Department> departments) {
        this.departments = departments;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "department")
    public Set<Employee> getEmployees() {
        return this.employees;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "department")
    public Set<Deprole> getDeprole() {
        return this.deprole;
    }

    public void setDeprole(Set<Deprole> deprole) {
        this.deprole = deprole;
    }
}
