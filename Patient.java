
public class Patient extends Person{
	private char rhesus;
	private String bloodType;
	private String birthDate;

	
	public Patient(String name, String surname, char [] amka, String bloodType, char rhesus, String birthDate) {
		super(name, surname, amka);
		this.bloodType = bloodType;
		this.rhesus = rhesus;
		this.birthDate = birthDate;
		
	}
	//Setters
	public void setBloodType(String bloodType) {
		this.bloodType = bloodType;
	}
	public void setRhesus(char rhesus) {
		this.rhesus = rhesus;
	}
	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}
	//Getters
	public String getBloodType() {
		return bloodType;
	}
	public String getBirthDate() {
		return birthDate;
	}
	public char getRhesus() {
		return rhesus;
	}
	/*public String returnCsv() {
		return String.valueOf(super.getAmka()) + "," + super.getSurname().toUpperCase() + ","
				+ super.getName()+ "," + bloodType + rhesus + "," + birthDate+"\n";
	}*/
	@Override 
	public String toString() {
		return String.valueOf(super.getAmka()) + ", " + super.getSurname().toUpperCase() + ", "
				+ super.getName()+ ", " + bloodType + rhesus + ", " + birthDate+"\n";
	}
}
