import java.time.LocalTime;

public class test {
	public static void main(String args[]) {
		LocalTime morning = LocalTime.parse("09:30");
		LocalTime check = LocalTime.parse("10:30");
		System.out.println(morning.isBefore(check));
	}
}
