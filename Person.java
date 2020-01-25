
public class Person {
	private String name, surname;
	private char [] amka;
	
	public Person(String name, String surname, char [] amka) {
		this.name = name;
		this.surname = surname;
		this.amka = amka;
	}
	//Getters
	public String getName() {
		return name;
	}
	public String getSurname() {
		return surname;
	}
	public String getFullName () {
		return (surname.toUpperCase() + " " + name);
	}
	public String getAmka() {
		return String.valueOf(amka);
	}
	
	//Setters
	public void setName(String name) {
		this.name = name;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public void setAMKA(char [] amka) {
		this.amka = amka;
	}
	
	
	public String toString() {
		return String.valueOf(amka) + ": "+ surname.toUpperCase() + " " + name; 
	}
}
