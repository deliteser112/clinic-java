import java.time.LocalDateTime;

public class BloodPressure implements Measurable{
	private int diastolic;
	private int systolic;
	private final static String units = "mmHg";
	private Patient p;
	private String indication = "Κανονική πίεση";

	
	public BloodPressure(Patient p, String qty) {
		systolic = Integer.parseInt(qty.split("/")[0]);
		diastolic = Integer.parseInt(qty.split("/")[1]);
		this.p = p;
	}

	@Override
	public String result() {
		if (diastolic >= 90 || systolic >= 140) {
			indication = "Πολύ υψηλή πίεση";
			
		} else if (diastolic >= 80 || systolic >= 120) {
			indication = "Πολύ υψηλή πίεση";
		}
		return indication;
	}

	public String toString() {
		return p.getAmka().toString() + ", " + systolic + "/" + diastolic + " " + units + ", " + result()+ ", " + LocalDateTime.now() + "\n";
	}
}
