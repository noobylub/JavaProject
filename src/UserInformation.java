//All users have this information; therefore, it should be a masterclass
public abstract class UserInformation {
	private int systemID;
    private Address personAdd;
    private String personName;

    public UserInformation(int systemID, Address personAdd, String personName) {
        this.systemID = systemID;
        this.personAdd = personAdd;
        this.personName = personName;
    }

    public int getSystemID() {
        return systemID;
    }

    public void setSystemID(int systemID) {
        this.systemID = systemID;
    }

    public Address getPersonAdd() {
        return personAdd;
    }

    public void setPersonAdd(Address personAdd) {
        this.personAdd = personAdd;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }
}

