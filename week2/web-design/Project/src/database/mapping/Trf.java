package database.mapping;
// Generated Apr 27, 2012 12:30:04 PM by Hibernate Tools 3.2.1.GA

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

/**
 * Trf generated by hbm2java
 */
@Entity
@Table(name = "TRF", schema = "GUEST")
public class Trf implements java.io.Serializable {

    private long id;
    private Customer customer;
    private Destination destination;
    private Employee employeeByProjectManager;
    private Employee employeeByEmpId;
    private Date beginDate;
    private Date endDate;
    private Boolean carRental;
    private Boolean carPayment;
    private Boolean curState;
    private Set<Trfstate> trfstates = new HashSet<Trfstate>(0);

    public Trf() {
    }

    public Trf(long id) {
        this.id = id;
    }

    public Trf(long id, Customer customer, Destination destination, Employee employeeByProjectManager, Employee employeeByEmpId, Date beginDate, Date endDate, Boolean carRental, Boolean carPayment, Boolean curState, Set<Trfstate> trfstates) {
        this.id = id;
        this.customer = customer;
        this.destination = destination;
        this.employeeByProjectManager = employeeByProjectManager;
        this.employeeByEmpId = employeeByEmpId;
        this.beginDate = beginDate;
        this.endDate = endDate;
        this.carRental = carRental;
        this.carPayment = carPayment;
        this.curState = curState;
        this.trfstates = trfstates;
    }

    @Id
    @SequenceGenerator(name = "trf_id", sequenceName = "trf_id_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "trf_id")
    @Column(name = "ID", unique = true, nullable = false, precision = 10, scale = 0)
    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CUSTOMER_ID")
    public Customer getCustomer() {
        return this.customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DESTINATION_ID")
    public Destination getDestination() {
        return this.destination;
    }

    public void setDestination(Destination destination) {
        this.destination = destination;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PROJECT_MANAGER")
    public Employee getEmployeeByProjectManager() {
        return this.employeeByProjectManager;
    }

    public void setEmployeeByProjectManager(Employee employeeByProjectManager) {
        this.employeeByProjectManager = employeeByProjectManager;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "EMP_ID")
    public Employee getEmployeeByEmpId() {
        return this.employeeByEmpId;
    }

    public void setEmployeeByEmpId(Employee employeeByEmpId) {
        this.employeeByEmpId = employeeByEmpId;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "BEGIN_DATE", length = 7)
    public Date getBeginDate() {
        return this.beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "END_DATE", length = 7)
    public Date getEndDate() {
        return this.endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Column(name = "CAR_RENTAL", precision = 1, scale = 0)
    public Boolean getCarRental() {
        return this.carRental;
    }

    public void setCarRental(Boolean carRental) {
        this.carRental = carRental;
    }

    @Column(name = "CAR_PAYMENT", precision = 1, scale = 0)
    public Boolean getCarPayment() {
        return this.carPayment;
    }

    public void setCarPayment(Boolean carPayment) {
        this.carPayment = carPayment;
    }

    @Column(name = "CUR_STATE", precision = 1, scale = 0)
    public Boolean getCurState() {
        return this.curState;
    }

    public void setCurState(Boolean curState) {
        this.curState = curState;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "trf")
    public Set<Trfstate> getTrfstates() {
        return this.trfstates;
    }

    public void setTrfstates(Set<Trfstate> trfstates) {
        this.trfstates = trfstates;
    }
}
