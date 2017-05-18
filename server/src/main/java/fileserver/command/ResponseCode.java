package fileserver.command;

// TODO fill with others commands. cristiano
public enum ResponseCode {
	
	COMMAND_NOT_FOUND {
		@Override
		public String toString() {
			return "error: command not found";
		}

		@Override
		public int getResponseCode() {
			return 0;
		}
	},
	
	REGISTERED {
		@Override
		public String toString() {
			return getResponseCode() + "registered";
		}

		@Override
		public int getResponseCode() {
			return 1;
		}
	},
	
	ERROR_USER_EXISTS {
		@Override
		public String toString() {
			return getResponseCode() + "error: user exists";
		}

		@Override
		public int getResponseCode() {
			return 2;
		}
	};
	
	public abstract int getResponseCode();
	
	public abstract String toString();

}
