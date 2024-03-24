import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;

public class RespiratoryRate implements Measurable{
	private int breathCycle;
	private final static String units = "Αναπνοές/Λεπτό";
	private Patient p;
	private LocalDate firstDate, secondDate;
	private int years, months, weeks;
	private String indication;



	public RespiratoryRate(Patient p, String qty) {
		breathCycle = Integer.parseInt(qty);
		this.p = p;
		this.indication = "Φυσιολογική αναπνοή";
	}

	@Override
	public String result() {

		firstDate = LocalDate.parse(p.getBirthDate(), dTF);
		secondDate = ld; //Current Date
		Period period = Period.between(firstDate, secondDate); //Patient's age
		this.years = period.getYears();
		this.months = period.getMonths();
		this.weeks = (int) period.getDays()/7;
		/* https://en.wikipedia.org/wiki/Respiratory_rate#Normal_range */
		if (weeks <= 6) {

			if (breathCycle < 30) {
				indication = "Βραδύπνοια";
			} else if (breathCycle <= 40){
				indication = "Φυσιολογική αναπνοή";
			} else {
				indication = "Ταχύπνοια";
			}

		} else if (months < 6) {

			if (breathCycle < 25) {
				indication = "Βραδύπνοια";
			} else if (breathCycle <= 40){
				indication = "Φυσιολογική αναπνοή";
			} else {
				indication = "Ταχύπνοια";
			}

		} else if (years < 3) {

			if (breathCycle < 20) {
				indication = "Βραδύπνοια";
			} else if (breathCycle <= 30){
				indication = "Φυσιολογική αναπνοή";
			} else {
				indication = "Ταχύπνοια";
			}

		} else if (years < 6) {

			if (breathCycle < 18) {
				indication = "Βραδύπνοια";
			} else if (breathCycle <= 25){
				indication = "Φυσιολογική αναπνοή";
			} else {
				indication = "Ταχύπνοια";
			}

		} else if (years < 10) {

			if (breathCycle < 17) {
				indication = "Βραδύπνοια";
			} else if (breathCycle <= 23){
				indication = "Φυσιολογική αναπνοή";
			} else {
				indication = "Ταχύπνοια";
			}

		} else if (years < 65) {

			if (breathCycle < 12) {
				indication = "Βραδύπνοια";
			} else if (breathCycle <= 18){
				indication = "Φυσιολογική αναπνοή";
			} else {
				indication = "Ταχύπνοια";
			}

		} else if (years < 80) {
			
			if (breathCycle < 12) {
				indication = "Βραδύπνοια";
			} else if (breathCycle <= 28){
				indication = "Φυσιολογική αναπνοή";
			} else {
				indication = "Ταχύπνοια";
			}
			
		} else {
			
			if (breathCycle < 10) {
				indication = "Βραδύπνοια";
			} else if (breathCycle <= 30){
				indication = "Φυσιολογική αναπνοή";
			} else {
				indication = "Ταχύπνοια";
			}
			
		}
		return indication;
	}

	public String toString() {
		return p.getAmka().toString() + ", " + breathCycle + " " + units + ", " + result() + ", " + LocalDateTime.now() + "\n"; 
	}
}
