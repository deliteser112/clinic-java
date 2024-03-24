
public class Doctor extends Person {
	private String officeNumber;
	private String specialty;
	
	public Doctor(String name, String surname, char [] amka, String officeNumber, String specialty) {
		super(name, surname, amka);
		this.officeNumber = officeNumber;
		this.specialty = specialty;
	}
	//Setters
	public void setSpecialty (String specialty) {
		this.specialty = specialty;
	}
	public void setOfficeNumber(String officeNumber) {
		this.officeNumber = officeNumber;
	}
	//Getters
	public String getSpecialty() {
		return specialty;
	}
	public String getOfficeNumber() {
		return officeNumber;
	}
	
	@Override 
	public String toString() {
		return String.valueOf(super.getAmka()) + ", " + super.getSurname().toUpperCase() + ", "
				+ super.getName()+ ", " + specialty + ", " + officeNumber+"\n";
	}
}
