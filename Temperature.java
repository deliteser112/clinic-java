import java.time.LocalDateTime;

public class Temperature implements Measurable {
	private float temperature;
	private final static String units = "o C";
	private Patient p;
	private String indication;

	
	public Temperature(Patient p, String qty) {
		temperature = Float.parseFloat(qty);
		this.p = p;
		indication = "Κανονική θερμοκρασία";
	}
	public String result() { 
		/*
		 * https://en.wikipedia.org/wiki/Human_body_temperature#Normal
		 */
		if (temperature < 36.5) {
			indication = "Υποθερμία";
		} else if (temperature > 37.5) {
			indication = "Πυρετός";
		}
		return indication;
	}
	public String toString() {
		return p.getAmka().toString() + ", " + temperature + " " + units + ", " + result() + ", "  + LocalDateTime.now() + "\n";
	}
	}
