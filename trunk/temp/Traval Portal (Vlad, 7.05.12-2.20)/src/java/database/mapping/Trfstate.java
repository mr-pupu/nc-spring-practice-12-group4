package database.mapping;
// Generated Apr 27, 2012 12:30:04 PM by Hibernate Tools 3.2.1.GA

import java.util.Date;
import javax.persistence.*;

/**
 * Trfstate generated by hbm2java
 */
@Entity
@Table(name = "TRFSTATE", schema = "GUEST")
public class Trfstate implements java.io.Serializable {

    private long id;
    private Trf trf;
    private String commentary;
    private Date changeDate;
    private Short status;
    private Long changer;

    public Trfstate() {
    }

    public Trfstate(long id) {
        this.id = id;
    }

    public Trfstate(long id, Trf trf, String commentary, Date changeDate, Short status, Long changer) {
        this.id = id;
        this.trf = trf;
        this.commentary = commentary;
        this.changeDate = changeDate;
        this.status = status;
        this.changer = changer;
    }

    @Id
    @SequenceGenerator(name = "trfstate_id", sequenceName = "trfstate_id_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "trfstate_id")
    @Column(name = "ID", unique = true, nullable = false, precision = 10, scale = 0)
    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TRF_ID")
    public Trf getTrf() {
        return this.trf;
    }

    public void setTrf(Trf trf) {
        this.trf = trf;
    }

    @Column(name = "COMMENTARY", length = 1000)
    public String getCommentary() {
        return this.commentary;
    }

    public void setCommentary(String commentary) {
        this.commentary = commentary;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "CHANGE_DATE", length = 7)
    public Date getChangeDate() {
        return this.changeDate;
    }

    public void setChangeDate(Date changeDate) {
        this.changeDate = changeDate;
    }

    @Column(name = "STATUS", precision = 1, scale = 0)
    public Short getStatus() {
        return this.status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

    @Column(name = "CHANGER", precision = 10, scale = 0)
    public Long getChanger() {
        return this.changer;
    }

    public void setChanger(Long changer) {
        this.changer = changer;
    }
}
