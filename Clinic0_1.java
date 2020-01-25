import java.io.BufferedWriter;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;


public class Clinic0_1 {

	public static void main(String[] args) {

		int choice=0;
		ArrayList<Doctor> doctors = new ArrayList<Doctor>();
		ArrayList<Patient> patients = new ArrayList<Patient>();
		ArrayList<String> measurements = new ArrayList<String>();

		
		// Reading from .csv files
		// If they do not exist, they are created in the project folder
		doctors = initializeDoctorList(doctors); 
		patients = initializePatientList(patients);
		measurements = initializeMeasurementList(measurements);


		System.out.println("*******************************");
		System.out.println("***							***");
		System.out.println("***							***");
		System.out.println("***			 Κλινική 		***");
		System.out.println("***							***");
		System.out.println("***							***");
		System.out.println("*******************************");
		
		// Choices menu:
		/*
		1 doctor list
		2 doctor edit
		3 new doctor
		4 patient list
		5 new patient
		6 patient measurements
		7 new measurement	
		 */
		
		while (true){
			printMenu();
			choice = readChoice(8);
			Scanner sc = new Scanner(System.in); // Waits for enter key when displaying the lists
			
			
			switch (choice) {
			
			case 1: // Doctor list display
				for (Doctor doctor : doctors) {
					System.out.print(doctor.toString());
				}
				sc.nextLine();
				break;
				
			case 2: { // Doctor edit
				int localChoice = 0;
				int foundAt = searchByAmkaDoc(doctors);
				if (foundAt > -1) // -1 NOT found
				{
					printEditDoctor();
					localChoice = readChoice(5);
					switch (localChoice) {
					/* 
					 * 1 Name change
					 * 2 Amka change
					 * 3 Specialty change
					 * 4 Office change
					 */
					
					case 1: { // Name
						String temp = doctors.get(foundAt).getName();
						String name = readName();
						doctors.get(foundAt).setName(name);
						System.out.println("Το όνομα άλλαξε επιτυχώς από " + temp
								+ " σε " + name);
						break;
					}
					case 2: { // Surname
						String temp = doctors.get(foundAt).getSurname();
						String surname = readSurname();
						doctors.get(foundAt).setSurname(surname);
						System.out.println("Το επώνυμο άλλαξε επιτυχώς από " + temp
								+ " σε " + surname);
						break;
					}
					case 3: { // AMKA
						String temp = doctors.get(foundAt).getAmka();
						char[] amka = readAmka().toCharArray();
						doctors.get(foundAt).setAMKA(amka);
						System.out.println("Ο AMKA άλλαξε επιτυχώς από " + temp
								+ " σε " + amka.toString());
						break;
					}
					case 4: { // Specialty
						String temp = doctors.get(foundAt).getSpecialty();
						String specialty = readSpecialty();
						doctors.get(foundAt).setSpecialty(specialty);
						System.out.println("Η ειδικότητα άλλαξε επιτυχώς από " + temp
								+ " σε " + specialty);
						break;
					}
					case 5: { // Office
						String temp = doctors.get(foundAt).getOfficeNumber();
						String officeNumber = readOfficeNumber();
						doctors.get(foundAt).setOfficeNumber(officeNumber);
						System.out.println("Ο αριθμός γραφείου άλλαξε επιτυχώς από " + temp
								+ " σε " + officeNumber);
						break;
					}
					}
				} else { // NOT found
					System.out.println("Δεν υπάρχει καταχωρημένος ιατρός με αυτό τον ΑΜΚΑ.");
				}
				break;
			}
			case 3: { // New doctor
				String name = readName();
				String surname = readSurname();
				char[] amka = readAmka().toCharArray();
				String officeNumber = readOfficeNumber();
				String specialty = readSpecialty();
				Doctor d = new Doctor(name, surname, amka, officeNumber, specialty);
				doctors.add(d);
				break;
			}
			case 4: // Patient List
				for (Patient patient : patients) {
					System.out.print(patient.toString());
				}
				sc.nextLine();
				break; 
			case 5: { // New Patient
				String name = readName();
				String surname = readSurname();
				char[] amka = readAmka().toCharArray();
				String bloodType = readBloodType();
				String birthDate = readBirthdate();
				Patient p = new Patient(name, surname, amka, 
						bloodType.substring(0, bloodType.length()-1), //returns "AB" for example
						bloodType.charAt(bloodType.length()-1), //returns the Rhesus
						birthDate);
				patients.add(p);
				break;
			}
			case 6: // Display measurements for a given AMKA
			{
				String amka = readAmka();
				boolean flag = false;
				for (String element : measurements) {
					if (element.startsWith(amka)) {
						System.out.println(element);
						flag = true;
					}
				}
				if (flag == false) {
					System.out.println("Δεν υπάρχουν μετρήσεις για τον ασθενή με ΑΜΚΑ: "+amka);
				}
			}
			sc.nextLine();
			break;
			
			case 7:{
				int localChoice = 0;
				int foundAt = searchByAmkaPat(patients);
				if (foundAt > -1) {
					Patient pat = patients.get(foundAt);
					System.out.println("Επιλέξτε τον αριθμό που αντιστοιχεί στην νέα μέτρηση");
					System.out.println("1. Μέτρηση Πίεσης");
					System.out.println("2. Μέτρηση καρδιακών παλμών");
					System.out.println("3. Μέτρηση θερμοκρασίας");
					System.out.println("4. Μέτρηση ρυθμού αναπνοής");
					localChoice = readChoice(4);
					Scanner keyboard = new Scanner(System.in);

					switch (localChoice) {

					case 1:
						String meas = readBloodPressure();
						BloodPressure bp = new BloodPressure(pat,meas);
						measurements.add(bp.toString());
						break;

					case 2:
						meas = readPulses();
						Pulses puls = new Pulses(pat,meas);
						measurements.add(puls.toString());
						break;
					case 3: 
						meas = readTemp();
						Temperature temp = new Temperature(pat, meas);
						measurements.add(temp.toString());
						break;
					case 4:
						meas = readResp();
						RespiratoryRate resp = new RespiratoryRate(pat, meas);
						measurements.add(resp.toString());
						break;
					}
				} else {
					System.out.println("Δεν υπάρχει καταχωρημένος ασθενής με αυτόν τον ΑΜΚΑ.\n"
							+ "Δημιουργήστε εγγραφή για τον ασθενή και ξαναδοκιμάστε.");
				}
				
				break;
			}
			case 8:
				saveDoctors(doctors);
				savePatients(patients);
				saveMeasurements(measurements);
				sc.close();
				System.exit(0);

			}
		}
	}		

	public static String readResp() {
		String prompt = "Εισάγετε τον ρυθμό αναπνοής του ασθενή ανά λεπτό: ";
		Scanner keyboard = new Scanner(System.in);
		String resp = "";
		int respirations = -1;

		do {
			System.out.print(prompt);
			prompt = "Μη αποδεκτή μορφή μέτρησης, παρακαλώ δοκιμάστε ξανά: ";
			resp = keyboard.nextLine();
			try {
				respirations = Integer.parseInt(resp);
			} catch (NumberFormatException e) {
				continue;
			}
		} while(respirations < 0);
		return resp;
	}



	public static String readTemp() {
		String prompt = "Εισάγετε την θερμοκρασία του ασθενή: ";
		Scanner keyboard = new Scanner(System.in);
		String temp = "";
		double temperature = 0;
		do {
			System.out.print(prompt);
			prompt = "Μη αποδεκτή μορφή μέτρησης, παρακαλώ δοκιμάστε ξανά: ";
			temp = keyboard.nextLine();
			try {
				temperature = Double.parseDouble(temp);
			} catch (NumberFormatException e) {
				continue;
			}
		} while(temperature < 35);
		return temp;

	}
	public static String readPulses() {
		String prompt = "Εισάγετε τον ρυθμό των καρδιακών χτύπων ανά λεπτό: ";
		Scanner keyboard = new Scanner(System.in);
		String PulsesIn = "";
		int pulses = -1;

		do {
			System.out.print(prompt);
			prompt = "Μη αποδεκτή μορφή μέτρησης, παρακαλώ δοκιμάστε ξανά: ";
			PulsesIn = keyboard.nextLine();
			try {
				pulses = Integer.parseInt(PulsesIn);
			} catch (NumberFormatException e) {
				continue;
			}
		} while(pulses < 0);
		return PulsesIn;


	}


	public static String readBloodPressure() {
		String prompt = "Εισάγετε την αρτηριακή πίεση με τη μορφή \"Συστολική/Διαστολική\": ";
		Scanner keyboard = new Scanner(System.in);
		String bloodPressure = "";
		int sys = 0;
		int dias = 0;
		do {
			System.out.print(prompt);
			prompt = "Μη αποδεκτή μορφή μέτρησης, παρακαλώ δοκιμάστε ξανά: ";
			bloodPressure = keyboard.nextLine();
			try {
				sys = Integer.parseInt(bloodPressure.split("/")[0]);
				dias = Integer.parseInt(bloodPressure.split("/")[1]);
			} catch (NumberFormatException|ArrayIndexOutOfBoundsException e) {
				continue;
			}
		} while(sys < 40 ||
				sys > 220 ||
				dias < 40 ||
				dias > 130 );
		return bloodPressure;


	}


	static int searchByAmkaDoc(ArrayList<Doctor> doctors) {
		String amka = readAmka();
		for (int i = 0; i < doctors.size(); ++i) {

			if (doctors.get(i).getAmka().equals(amka)) {
				return i;
			} 
		}
		System.out.println("Ο ΑΜΚΑ " + amka + " δεν βρέθηκε.");
		return -1;
	}
	static int searchByAmkaPat(ArrayList<Patient> patients) {
		String amka = readAmka();
		int i = 0;
		for (Patient pat : patients) {

			if (pat.getAmka().equals(amka)) {
				return i;
			} 
			++i;
		}
		System.out.println("Ο ΑΜΚΑ " + amka + " δεν βρέθηκε.");
		return -1;
	}



	static ArrayList<Doctor> initializeDoctorList(ArrayList<Doctor> doctors) {
		try (Scanner dsc = new Scanner(new File("doctors.csv"))) {
			ArrayList<String> data = new ArrayList<String>();
			while (dsc.hasNextLine()) {
				data.add(dsc.nextLine());
			}
			//String[] doctorAttributes = new String[5];
			for (int i = 0; i < data.size(); ++i) {
				String x = data.get(i).replace(" ", "");
				//System.out.println(x);
				String doctorAttributes[] = x.split(",");
				String name = doctorAttributes[2];
				String surname = doctorAttributes[1];
				char[] amka = doctorAttributes[0].toCharArray();
				String officeNumber = doctorAttributes[4];
				String specialty = doctorAttributes[3];
				Doctor d = new Doctor(name, surname, amka, officeNumber, specialty); 
				doctors.add(d);
			}
		} catch (FileNotFoundException e){
			System.err.println(e);
		}
		return doctors;
	}
	static ArrayList<Patient> initializePatientList(ArrayList<Patient> patients) {
		try (Scanner dsc = new Scanner(new File("patients.csv"))) {
			ArrayList<String> data = new ArrayList<String>();
			while (dsc.hasNextLine()) {
				data.add(dsc.nextLine());
			}

			for (int i = 0; i < data.size(); ++i) {
				String x = data.get(i).replace(" ", "");
				//System.out.println(x);
				String patientAttributes[] = x.split(",");
				String name = patientAttributes[2];
				String surname = patientAttributes[1];
				char[] amka = patientAttributes[0].toCharArray();
				String bloodType = patientAttributes[3].substring(0, patientAttributes[3].length()-1); //returns "AB" for example
				char rhesus = patientAttributes[3].charAt(patientAttributes[3].length()-1); //returns the rhesus
				String birthdate = patientAttributes[4];
				Patient p = new Patient(name, surname, amka, bloodType, rhesus, birthdate); 
				patients.add(p);
			}
		} catch (FileNotFoundException e){
			System.err.println(e);
		}
		return patients;
	}

	static ArrayList<String> initializeMeasurementList(ArrayList<String> measurements) {
		try (Scanner msc = new Scanner(new File("measurements.csv"))) {
			while (msc.hasNextLine()) {
				measurements.add(msc.nextLine()+"\n");
			}

		} catch (FileNotFoundException e){
			System.err.println(e);
		}
		return measurements;
	}

	static void saveMeasurements(ArrayList<String> measurements) {
		PrintWriter outDocs = null;
		try	{
			outDocs = new PrintWriter(new BufferedWriter(new FileWriter("measurements.csv", false)));
			for (String measurement : measurements) {
				outDocs.print(measurement);
				
			}
		} catch (IOException e) {
			System.err.println(e);
		} finally {
			if (outDocs != null) {
				outDocs.close();
			}
		}
	}


	static void saveDoctors(ArrayList<Doctor> doctors) {
		PrintWriter outDocs = null;
		try	{
			outDocs = new PrintWriter(new BufferedWriter(new FileWriter("doctors.csv", false)));
			for (Doctor doctor : doctors) {
				outDocs.print(doctor.toString());
				//out.print(doctors.toString().substring(1, doctors.toString().length()-1));
			}
		} catch (IOException e) {
			System.err.println(e);
		} finally {
			if (outDocs != null) {
				outDocs.close();
			}
		}
	}
	static void savePatients(ArrayList<Patient> patients) {
		PrintWriter outPats = null;
		try	{
			outPats = new PrintWriter(new BufferedWriter(new FileWriter("patients.csv", false)));
			for (Patient patient : patients) {
				outPats.print(patient.toString());
				//out.print(doctors.toString().substring(1, doctors.toString().length()-1));
			}
		} catch (IOException e) {
			System.err.println(e);
		} finally {
			if (outPats != null) {
				outPats.close();
			}
		}
	}

	public static void printEditDoctor() {
		System.out.println("Πιέστε το νούμερο που αντιστοιχεί στην επιθυμητή επιλογή.");
		System.out.println("1. Αλλαγή ονόματος");
		System.out.println("2. Αλλαγή επωνύμου");
		System.out.println("3. Αλλαγή ΑΜΚΑ");
		System.out.println("4. Αλλαγή Ειδικότητας");
		System.out.println("5. Αλλαγή Γραφείου");
	}

	public static void printMenu() {
		System.out.println("*** Παρακαλώ πιέστε τον αριθμό που αντιστοιχεί στην επιθυμητή λειτουργία και πατήστε Enter ***");
		//System.out.println("*** ***");
		System.out.println("1. Προβολή ιατρών");
		System.out.println("2. Επεξεργασία ιατρού");
		System.out.println("3. Νέος ιατρός");
		System.out.println("4. Κατάλογος ασθενών");
		System.out.println("5. Νέος ασθενής");
		System.out.println("6. Προβολή μετρήσεων ασθενή");
		System.out.println("7. Νέα μέτρηση");
		System.out.println("8. Έξοδος");
	}

	public static int readChoice(int NoChoices) {
		String prompt = "Επιλογή: ";
		int choice = 0;
		Scanner keyboard = new Scanner(System.in);
		do {
			System.out.printf(prompt);
			choice = keyboard.nextInt();
			prompt = "Λάθος επιλογή. Παρακαλώ εισάγετε έναν αριθμό από 1 έως "+ NoChoices + ": ";
		} while (!(choice > 0 && choice <= NoChoices));
		//keyboard.close();
		return choice;
	}

	public static String readAmka () {
		String prompt = "Παρακαλώ εισάγετε ΑΜΚΑ: ";
		String amka;
		Scanner keyboard = new Scanner(System.in);
		do {
			System.out.printf(prompt);
			prompt = "Λάθος μορφή ΑΜΚΑ. Η σωστή μορφή περιλαμβάνει 11 αριθμητικά ψηφία";
			amka = keyboard.nextLine();
		} while (amka.length() != 11 || !(amka.chars().allMatch(Character::isDigit)));
		//keyboard.close();
		return amka;
	}

	public static String readSurname () {
		String prompt = "Παρακαλώ εισάγετε Επώνυμο: ";
		String surname;
		Scanner keyboard = new Scanner(System.in);
		do {
			System.out.printf(prompt);
			prompt = "Μη αποδεκτή μορφή, παρακαλώ ξαναδοκιμάστε: ";
			surname = keyboard.nextLine();
		} while (!(surname.chars().allMatch(Character::isLetter)));
		//keyboard.close();
		return surname;
	}

	public static String readName () {
		String prompt = "Παρακαλώ εισάγετε Όνομα: ";
		String name;
		Scanner keyboard = new Scanner(System.in);
		do {
			System.out.printf(prompt);
			prompt = "Μη αποδεκτή μορφή, παρακαλώ ξαναδοκιμάστε: ";
			name = keyboard.nextLine();
		} while (!(name.chars().allMatch(Character::isLetter)));
		//keyboard.close();
		return name;
	}

	public static String readBloodType () {
		String prompt = "Παρακαλώ εισάγετε την ομάδα αίματος: ";
		String bloodType;
		final String[] validBloodTypes = {"A+", "B+", "AB+", "O+", "A-", "B-", "AB-", "O-",
				"Α+", "Β+", "ΑΒ+", "Ο+", "Α-", "Β-", "ΑΒ-", "Ο-"};
		Scanner keyboard = new Scanner(System.in);
		do {
			System.out.printf(prompt);
			prompt = "Μη αποδεκτή μορφή, παρακαλώ ξαναδοκιμάστε: ";
			bloodType = keyboard.nextLine().toUpperCase();
		} while (!Arrays.asList(validBloodTypes).contains(bloodType));
		//keyboard.close();
		return bloodType;
	}

	public static String readSpecialty () {
		String prompt = "Παρακαλώ εισάγετε την ειδικότητα: ";
		String specialty;
		Scanner keyboard = new Scanner(System.in);
		do {
			System.out.printf(prompt);
			prompt = "Μη αποδεκτή μορφή, παρακαλώ ξαναδοκιμάστε: ";
			specialty = keyboard.nextLine().toUpperCase();
		} while (!specialty.chars().allMatch(Character::isLetter));
		//keyboard.close();
		return specialty;
	}

	public static String readOfficeNumber () {
		String prompt = "Παρακαλώ εισάγετε τον αριθμό γραφείου: ";
		String officeNumber;
		Scanner keyboard = new Scanner(System.in);
		do {
			System.out.printf(prompt);
			prompt = "Μη αποδεκτή μορφή, παρακαλώ ξαναδοκιμάστε: ";
			officeNumber = keyboard.nextLine();
		} while (!officeNumber.chars().allMatch(Character::isDigit) || (officeNumber.length() > 3));
		//keyboard.close();
		return officeNumber;
	}

	public static String readBirthdate () {
		String prompt = "Παρακαλώ εισάγετε ημερομηνία γέννησης (πχ για 4 Δεκεμβρίου 1990, εισάγετε 04/12/1990): ";
		String birthdate = "";
		LocalDate lds;
		DateTimeFormatter dTF = DateTimeFormatter.ofPattern("dd/MM/uuuu");
		String errorPrompt = "";
		Scanner keyboard = new Scanner(System.in);
		while (true) {
			try {
				errorPrompt = "Μη αποδεκτή μορφή, παρακαλώ ξαναδοκιμάστε: ";
				System.out.printf(prompt);

				birthdate = keyboard.nextLine();
				lds = LocalDate.parse(birthdate, dTF);
				boolean flag = lds.isAfter(LocalDate.now());

				if (!flag) {
					break;
				} else {
					errorPrompt = "Η ημερομηνία γέννησης δε μπορεί να είναι μεταγενέστερη της σημερινής."
							+ "\nΠατήστε Enter για να εισάγετε νέα. ";
					throw new DateTimeException("Invalid"); 
				}
			} catch (DateTimeException e) {
				System.out.printf(errorPrompt);
				keyboard.nextLine(); // skip the invalid token
			}
		}
		//keyboard.close();
		return birthdate;

	}

}


