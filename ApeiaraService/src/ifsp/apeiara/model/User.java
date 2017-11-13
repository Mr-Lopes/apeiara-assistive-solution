package ifsp.apeiara.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class User {

    @Id
	@GeneratedValue	(strategy = GenerationType.AUTO)
    private int ID;
    private String NAME;
    private String CATEGORY;
    private String EMAIL;
    private String PASS;
    private String PHONE;
    @OneToMany
    private List<User> USERS;





	public User() {
		super();
		
	}
	public User(int iD, String nAME, String cATEGORY, String eMAIL,
			String pASS, String pHONE, List<User> uSERS) {
		ID = iD;
		NAME = nAME;
		CATEGORY = cATEGORY;
		EMAIL = eMAIL;
		PASS = pASS;
		PHONE = pHONE;
		USERS = uSERS;
	}
	
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getNAME() {
		return NAME;
	}
	public void setNAME(String nAME) {
		NAME = nAME;
	}
	public String getCATEGORY() {
		return CATEGORY;
	}
	public void setCATEGORY(String cATEGORY) {
		CATEGORY = cATEGORY;
	}
	public String getEMAIL() {
		return EMAIL;
	}
	public void setEMAIL(String eMAIL) {
		EMAIL = eMAIL;
	}
	
	public String getPASS() {
		return PASS;
	}
	public void setPASS(String pASS) {
		PASS = pASS;
	}
	public String getPHONE() {
		return PHONE;
	}
	public void setPHONE(String pHONE) {
		PHONE = pHONE;
	}
	public List<User> getUSERS() {
		return USERS;
	}
	public void setUSERS(List<User> uSERS) {
		USERS = uSERS;
	}

	
    
}
