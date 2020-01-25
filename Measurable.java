import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
public interface Measurable {
	public static LocalDate ld = LocalDate.now();
	public static DateTimeFormatter dTF = DateTimeFormatter.ofPattern("dd/MM/uuuu");
	
	
	//public void enterMeasurement(Patient p, String qty);
	
	public String result();
}
