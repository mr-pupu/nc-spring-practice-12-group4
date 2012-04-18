package com.example.datamodel;
// Generated Apr 13, 2012 2:20:12 AM by Hibernate Tools 3.2.1.GA


import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

/**
 * Destination generated by hbm2java
 */
@Entity
@Table(name="DESTINATION"
    ,schema="GUEST"
)
public class Destination  implements java.io.Serializable {


     private long id;
     private City city;
     private Set<Trf> trfs = new HashSet<Trf>(0);

    public Destination() {
    }

	
    public Destination(long id) {
        this.id = id;
    }
    public Destination(long id, City city, Set<Trf> trfs) {
       this.id = id;
       this.city = city;
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
    @JoinColumn(name="DEST_CITY_ID")
    public City getCity() {
        return this.city;
    }
    
    public void setCity(City city) {
        this.city = city;
    }
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="destination")
    public Set<Trf> getTrfs() {
        return this.trfs;
    }
    
    public void setTrfs(Set<Trf> trfs) {
        this.trfs = trfs;
    }




}

