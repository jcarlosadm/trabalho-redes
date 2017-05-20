package fileserver.util;

public abstract class PrintData {

	private static final String LINE = "------------------------------------";

	private PrintData() {
	}

	public static void print(String title, String data) {
		System.out.println(title + "\n" + LINE + "\n" + data + "\n" + LINE + "\n");
	}

}
