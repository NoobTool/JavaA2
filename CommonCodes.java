import java.util.Scanner;

public class CommonCodes {
	public int inputInt() {
		Scanner scan = new Scanner(System.in);
		return scan.nextInt();
	}
	
	public String inputString() {
		Scanner scan = new Scanner(System.in);
		return scan.nextLine();
	}
	
	public char inputChar() {
		Scanner scan = new Scanner(System.in);
		return scan.next().charAt(0);
	}
}
