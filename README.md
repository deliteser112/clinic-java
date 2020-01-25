# ClinicJava
Example clinic management system written in java

<p>The main class is in <em>Clinic0_1.java</em> . This project is a simple clinic informational system with the capability to manage patients, doctors
 and specific measurements with the appropriate messages for extreme values.</p>
 
 <p>It utilizes the class <em>Person</em>, with a register number (AMKA), name and surname, and the extensions <em>Doctor.java</em>
 and <em>Patient.java</em> .
 The measurements (<em>RespiratoryRate, Pulses, Temperature and BloodPressure</em>) are implementations of the interface <em>Measurable</em>.
 Entry-validation was implemented regarding the data of a new entry in the main class. The program starts by reading the *.csv files
and if not present, it creates them and after a proper exit from the program, it overwrites them with the entries loaded as
 <em>ArrayLists</em>.</p>
 
 <p>The strings are hard-coded in Greek. Exit is available from the main menu only</p>
