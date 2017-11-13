package ifsp.apeiara.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Log {
	
    @Id
	@GeneratedValue (strategy = GenerationType.AUTO)	
	private int ID;
	private String DATE;
	@OneToOne
	private Request REQUEST;
	private String REQ_STATUS;
	@OneToOne
	private User USER;
	private String ADDITIONAL;
	private String LATITUDE;
	private String LONGITUTE;
	
	public Log(){
		
	}

	public Log(int iD, String dATE, Request REQUEST, String rEQ_STATUS,
			User uSER, String aDDITIONAL, String lATITUDE, String lONGITUTE) {
		this.ID = iD;
		this.DATE = dATE;
		this.REQUEST = REQUEST;
		this.REQ_STATUS = rEQ_STATUS;
		this.USER = uSER;
		this.ADDITIONAL = aDDITIONAL;
		this.LATITUDE = lATITUDE;
		this.LONGITUTE = lONGITUTE;
	}

	
	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getDATE() {
		return DATE;
	}

	public void setDATE(String dATE) {
		DATE = dATE;
	}

	public Request getREQUEST() {
		return REQUEST;
	}

	public void setREQUEST(Request REQUEST) {
		this.REQUEST = REQUEST;
	}

	public String getREQ_STATUS() {
		return REQ_STATUS;
	}

	public void setREQ_STATUS(String rEQ_STATUS) {
		REQ_STATUS = rEQ_STATUS;
	}

	public User getUSER() {
		return USER;
	}

	public void setUSER(User uSER) {
		USER = uSER;
	}

	public String getADDITIONAL() {
		return ADDITIONAL;
	}

	public void setADDITIONAL(String aDDITIONAL) {
		ADDITIONAL = aDDITIONAL;
	}

	public String getLATITUDE() {
		return LATITUDE;
	}

	public void setLATITUDE(String lATITUDE) {
		LATITUDE = lATITUDE;
	}

	public String getLONGITUTE() {
		return LONGITUTE;
	}

	public void setLONGITUTE(String lONGITUTE) {
		LONGITUTE = lONGITUTE;
	}
	
}
