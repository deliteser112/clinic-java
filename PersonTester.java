import java.io.*;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class PersonTester {
	public static void main(String[] args) {
		StringBuilder doctorsSb = new StringBuilder();
		StringBuilder patientsSb = new StringBuilder();
		doctorsSb.append("AMKA, Επώνυμο, Όνομα, Ειδικότητα, Αρ. Γραφείου\n");
		patientsSb.append("AMKA, Επώνυμο, Όνομα, Ομ. Αίματος, Ημ. Γέννησης\n");
		Person p = new Person("John", "Doe", "01010100458".toCharArray());
		System.out.println(p);
		System.out.printf("%nName: %s%nSurname: %s%nFull Name: %s%nAMKA: %s%n",
				p.getName(), p.getSurname(), p.getFullName(), p.getAmka());
		p.setName("Jane");
		p.setSurname("Daniels");
		p.setAMKA("11201554848".toCharArray());
		Patient pat = new Patient("John", "Doe", "01010100458".toCharArray(), "AB",'+', "04/08/1995");
		patientsSb.append(pat);
		Doctor doc = new Doctor("John", "Doe", "01010100458".toCharArray(), "205","Cardiologist");
		doctorsSb.append(doc);
		System.out.println(p);
		System.out.println(pat);
		System.out.println(doc);
		try (PrintWriter writer = new PrintWriter(new File("doctors.csv"))) {
			writer.write(doctorsSb.toString());
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		}		
		try (PrintWriter writer = new PrintWriter(new File("patients.csv"))) {
			writer.write(patientsSb.toString());
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		}		
		System.exit(0);
	}
	
	
}
	    		
	/*	try (PrintWriter writer = new PrintWriter(new File("test.csv"))) {
			public StringBuilder sb = new StringBuilder();
			 sb.append("id,");
		      sb.append(',');
		      sb.append("Name");
		      sb.append('\n');

		      sb.append("1");
		      sb.append(',');
		      sb.append("Prashant Ghimire");
		      sb.append('\n');

		      writer.write(sb.toString());
		      System.out.println(sb);
		      System.out.println("done!");

		    } catch (FileNotFoundException e) {
		      System.out.println(e.getMessage());*/

