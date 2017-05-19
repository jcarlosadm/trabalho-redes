package fileserver.parserentry;

public class Protocol {
	
	public static void execute(String command) {
		
		System.out.println("-------------------\n"+command+"\n------------------------");

		try {
			int cod = Integer.parseInt(command.substring(0, command.indexOf(" ")));
			
			System.out.println(command.substring(command.indexOf(" ")+1, command.indexOf("\n")));
			
			switch(cod) {
			case 9:
				getFile(getData(command));
				break;
			case 11:
				System.out.println(getData(command));
				break;
			case 14:
				setServerKey(getData(command));
				break;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void setServerKey(String data) {
		// TODO Auto-generated method stub. carlos
	}

	private static String getData(String command) {
		// TODO Auto-generated method stub. cristiano
		return null;
	}

	private static void getFile(String command) {
		// TODO Auto-generated method stub. carlos
		
	}

}
