package fileserver.parserentry;

public abstract class InputParser {

	private InputParser() {
	}

	/**
	 * format line and return message formated
	 * 
	 * @param line
	 *            line to be formated
	 * @return formated message, or null in case of error
	 */
	public static String format(String line) {

		String data = "";

		try {
			String[] fields = line.split("[\\s]+");
			
			// TODO implement all protocols. carlos
			
			if (fields[0].equals("login")) {
				data += "login\n";
				data += "user:" + fields[1] + "\n";
				data += "password:" + getPassword();
			}

			else if (fields[0].equals("register")) {
				// TODO implement. carlos
			}

			else {
				return null;
			}
		} catch (Exception e) {
			return null;
		}
		
		System.out.println("-------------------\n"+data+"\n----------------------");

		return data;
	}

	private static String getPassword() {
		// TODO Auto-generated method stub. carlos
		return null;
	}

	public static void main(String[] args) {
		String line = "";

		String[] fields = line.split("[\\s]+");
		for (String string : fields) {
			System.out.println(string);
		}
	}

}
