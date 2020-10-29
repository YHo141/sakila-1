package sakila.vo;

public class Actor {
	private int actiorId;
	private String firstName;
	private String lastName;
	private String lastUpdate;
	
	public int getActiorId() {
		return actiorId;
	}
	public void setActiorId(int actiorId) {
		this.actiorId = actiorId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getLastUpdate() {
		return lastUpdate;
	}
	public void setLastUpdate(String lastUpdate) {
		this.lastUpdate = lastUpdate;
	}
	
	@Override
	public String toString() {
		return "Actor [actiorId=" + actiorId + ", firstName=" + firstName + ", lastName=" + lastName + ", lastUpdate="
				+ lastUpdate + "]";
	}
}
