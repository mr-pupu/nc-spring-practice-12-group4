package database.mapping;
// Generated May 7, 2012 12:41:52 AM by Hibernate Tools 3.2.1.GA

import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

/**
 * Country generated by hbm2java
 */
@Entity
@Table(name = "COUNTRY", schema = "GUEST")
public class Country implements java.io.Serializable {

    private long id;
    private String countryName;
    private Set<City> cities = new HashSet<City>(0);

    public Country() {
    }

    public Country(long id) {
        this.id = id;
    }

    public Country(long id, String countryName, Set<City> cities) {
        this.id = id;
        this.countryName = countryName;
        this.cities = cities;
    }

    @Id
    @SequenceGenerator(name = "country_id", sequenceName = "country_id_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "country_id")
    @Column(name = "ID", unique = true, nullable = false, precision = 10, scale = 0)
    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(name = "COUNTRY_NAME", length = 20)
    public String getCountryName() {
        return this.countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "country")
    public Set<City> getCities() {
        return this.cities;
    }

    public void setCities(Set<City> cities) {
        this.cities = cities;
    }
}
