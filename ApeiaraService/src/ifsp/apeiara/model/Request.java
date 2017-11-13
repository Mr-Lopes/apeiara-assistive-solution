package ifsp.apeiara.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;


@Entity
public class Request {

    @Id
	@GeneratedValue	(strategy = GenerationType.AUTO)
    private int ID;
    private String DATE;
    private String TYPE;
    private String STATUS;
    private String LATITUDE;
    private String LONGITUDE;
    @OneToOne
    private User CUIDANDO;
    @OneToOne
    private User CUIDADOR;


    public Request(){

    }

    public Request(int ID, String DATE, String TYPE, String STATUS, String LATITUDE,String LONGITUDE, User CUIDANDO, User CUIDADOR) {
        this.ID = ID;
        this.DATE = DATE;
        this.TYPE = TYPE;
        this.STATUS = STATUS;
        this.LONGITUDE = LONGITUDE;
        this.LATITUDE = LATITUDE;
        this.CUIDANDO = CUIDANDO;
        this.CUIDADOR = CUIDADOR;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getDATE() {
        return DATE;
    }

    public void setDATE(String DATE) {
        this.DATE = DATE;
    }

    public String getTYPE() {
        return TYPE;
    }

    public void setTYPE(String TYPE) {
        this.TYPE = TYPE;
    }

    public String getSTATUS() {
        return STATUS;
    }

    public void setSTATUS(String STATUS) {
        this.STATUS = STATUS;
    }

    public String getLONGITUDE() {
        return LONGITUDE;
    }

    public void setLONGITUDE(String LONGITUDE) {
        this.LONGITUDE = LONGITUDE;
    }

    public String getLATITUDE() {
        return LATITUDE;
    }

    public void setLATITUDE(String LATITUDE) {
        this.LATITUDE = LATITUDE;
    }

    public User getCUIDANDO() {
        return CUIDANDO;
    }

    public void setCUIDANDO(User CUIDANDO) {
        this.CUIDANDO = CUIDANDO;
    }

    public User getCUIDADOR() {
        return CUIDADOR;
    }

    public void setCUIDADOR(User CUIDADOR) {
        this.CUIDADOR = CUIDADOR;
    }
    
    public void shows()
    {
        System.out.println(this.ID);
        System.out.println(this.DATE);
        System.out.println(this.TYPE);
        System.out.println(this.STATUS);
        System.out.println(this.LATITUDE);
        System.out.println(this.LONGITUDE);
        System.out.println(this.CUIDANDO.getID());
        System.out.println(this.CUIDADOR.getID());
    }
}
