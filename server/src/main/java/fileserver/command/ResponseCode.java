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
			return "registered";
		}

		@Override
		public int getResponseCode() {
			return 1;
		}
	};
	
	public abstract int getResponseCode();
	
	public abstract String toString();

}
