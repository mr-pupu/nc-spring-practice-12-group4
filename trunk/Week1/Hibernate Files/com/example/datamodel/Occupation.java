package database.mapping;
// Generated Apr 27, 2012 12:30:04 PM by Hibernate Tools 3.2.1.GA

import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

/**
 * Occupation generated by hbm2java
 */
@Entity
@Table(name = "OCCUPATION", schema = "GUEST")
public class Occupation implements java.io.Serializable {

    private long id;
    private String posName;
    private Set<Employee> employees = new HashSet<Employee>(0);

    public Occupation() {
    }

    public Occupation(long id) {
        this.id = id;
    }

    public Occupation(long id, String posName, Set<Employee> employees) {
        this.id = id;
        this.posName = posName;
        this.employees = employees;
    }

    @Id
    @SequenceGenerator(name = "occupation_id", sequenceName = "occupation_id_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "occupation_id")
    @Column(name = "ID", unique = true, nullable = false, precision = 10, scale = 0)
    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(name = "POS_NAME", length = 25)
    public String getPosName() {
        return this.posName;
    }

    public void setPosName(String posName) {
        this.posName = posName;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "occupation")
    public Set<Employee> getEmployees() {
        return this.employees;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }
}
