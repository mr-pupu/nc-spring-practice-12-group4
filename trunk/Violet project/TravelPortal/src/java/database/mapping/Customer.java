package database.mapping;
// Generated May 7, 2012 12:41:52 AM by Hibernate Tools 3.2.1.GA


import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

/**
 * Customer generated by hbm2java
 */
@Entity
@Table(name="CUSTOMER",schema="GUEST")
public class Customer  implements java.io.Serializable {


     private long id;
     private String custName;
     private Set<Trf> trfs = new HashSet<Trf>(0);

    public Customer() {
    }

	
    public Customer(long id) {
        this.id = id;
    }
    public Customer(long id, String custName, Set<Trf> trfs) {
       this.id = id;
       this.custName = custName;
       this.trfs = trfs;
    }
   
     @Id 
    @SequenceGenerator(name = "customer_id", sequenceName = "customer_id_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_id")
    @Column(name="ID", unique=true, nullable=false, precision=10, scale=0)
    public long getId() {
        return this.id;
    }
    
    public void setId(long id) {
        this.id = id;
    }
    
    @Column(name="CUST_NAME", length=20)
    public String getCustName() {
        return this.custName;
    }
    
    public void setCustName(String custName) {
        this.custName = custName;
    }
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="customer")
    public Set<Trf> getTrfs() {
        return this.trfs;
    }
    
    public void setTrfs(Set<Trf> trfs) {
        this.trfs = trfs;
    }




}


