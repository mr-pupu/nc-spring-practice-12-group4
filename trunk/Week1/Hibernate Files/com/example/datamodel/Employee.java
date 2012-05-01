package database.mapping;
// Generated Apr 27, 2012 12:30:04 PM by Hibernate Tools 3.2.1.GA

import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

/**
 * Employee generated by hbm2java
 */
@Entity
@Table(name = "EMPLOYEE", schema = "GUEST", uniqueConstraints =
@UniqueConstraint(columnNames = "LOGIN"))
public class Employee implements java.io.Serializable {

    private long id;
    private Occupation occupation;
    private Office office;
    private Department department;
    private String firstName;
    private String secondName;
    private String email;
    private String login;
    private String password;
    private Set<Trf> trfsForEmpId = new HashSet<Trf>(0);
    private Set<Trf> trfsForProjectManager = new HashSet<Trf>(0);

    public Employee() {
    }

    public Employee(long id) {
        this.id = id;
    }

    public Employee(long id, Occupation occupation, Office office,
            Department department, String firstName, String secondName,
            String email, String login, String password, Set<Trf> trfsForEmpId,
            Set<Trf> trfsForProjectManager) {
        this.id = id;
        this.occupation = occupation;
        this.office = office;
        this.department = department;
        this.firstName = firstName;
        this.secondName = secondName;
        this.email = email;
        this.login = login;
        this.password = password;
        this.trfsForEmpId = trfsForEmpId;
        this.trfsForProjectManager = trfsForProjectManager;
    }

    @Id
    @SequenceGenerator(name = "employee_id", sequenceName = "employee_id_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employee_id")
    @Column(name = "ID", unique = true, nullable = false, precision = 10, scale = 0)
    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "POSITION_ID")
    public Occupation getOccupation() {
        return this.occupation;
    }

    public void setOccupation(Occupation occupation) {
        this.occupation = occupation;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "OFFICE_ID")
    public Office getOffice() {
        return this.office;
    }

    public void setOffice(Office office) {
        this.office = office;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DEP_ID")
    public Department getDepartment() {
        return this.department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    @Column(name = "FIRST_NAME", length = 20)
    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name = "SECOND_NAME", length = 20)
    public String getSecondName() {
        return this.secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    @Column(name = "EMAIL", length = 256)
    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "LOGIN", unique = true, length = 20)
    public String getLogin() {
        return this.login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Column(name = "PASSWORD", length = 30)
    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "employeeByEmpId")
    public Set<Trf> getTrfsForEmpId() {
        return this.trfsForEmpId;
    }

    public void setTrfsForEmpId(Set<Trf> trfsForEmpId) {
        this.trfsForEmpId = trfsForEmpId;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "employeeByProjectManager")
    public Set<Trf> getTrfsForProjectManager() {
        return this.trfsForProjectManager;
    }

    public void setTrfsForProjectManager(Set<Trf> trfsForProjectManager) {
        this.trfsForProjectManager = trfsForProjectManager;
    }
}
