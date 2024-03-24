import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;

public class Pulses implements Measurable{
	private int bpm;
	private final static String units = "Χτύποι/Λεπτό";
	private Patient p;
	private LocalDate firstDate, secondDate;
	private int years, months, weeks;
	private String indication = "Φυσιολογικοί παλμοί";


	
	public Pulses(Patient p, String qty) {
		bpm = Integer.parseInt(qty);
		this.p = p;
	}

	@Override
	public String result() {

		firstDate = LocalDate.parse(p.getBirthDate(), dTF);
		secondDate = ld; //Current Date
		Period period = Period.between(firstDate, secondDate); //Patient's age
		this.years = period.getYears();
		this.months = period.getMonths();
		this.weeks = (int) period.getDays()/7;
		/* https://en.wikipedia.org/wiki/Heart_rate#Resting_heart_rate */
		if (months <= 3) {

			if (bpm < 99) {
				indication = "Μειωμένοι Παλμοί";
			} else if (bpm <= 149){
				indication = "Φυσιολογικοί παλμοί";
			} else {
				indication = "Αυξημένοι παλμοί";
			}

		} else if (months < 6) {

			if (bpm < 89) {
				indication = "Μειωμένοι Παλμοία";
			} else if (bpm <= 119){
				indication = "Φυσιολογικοί παλμοί";
			} else {
				indication = "Αυξημένοι παλμοί";
			}

		} else if (years < 1) {

			if (bpm < 79) {
				indication = "Μειωμένοι Παλμοία";
			} else if (bpm <= 119){
				indication = "Φυσιολογικοί παλμοί";
			} else {
				indication = "Αυξημένοι παλμοί";
			}

		} else if (years < 10) {

			if (bpm < 69) {
				indication = "Μειωμένοι Παλμοία";
			} else if (bpm <= 129){
				indication = "Φυσιολογικοί παλμοί";
			} else {
				indication = "Αυξημένοι παλμοί";
			}

		} else if (years < 10) {

			if (bpm < 17) {
				indication = "Μειωμένοι Παλμοία";
			} else if (bpm <= 23){
				indication = "Φυσιολογικοί παλμοί";
			} else {
				indication = "Αυξημένοι παλμοί";
			}

			
		} else {
			
			if (bpm < 59) {
				indication = "Μειωμένοι Παλμοία";
			} else if (bpm <= 99){
				indication = "Φυσιολογικοί παλμοί";
			} else {
				indication = "Αυξημένοι παλμοί";
			}
			
		}
		return indication;
	}

	public String toString() {
		return p.getAmka().toString() + ", " + bpm + " " + units + ", " + result() +  ", "+LocalDateTime.now() + "\n"; 
	}
}

