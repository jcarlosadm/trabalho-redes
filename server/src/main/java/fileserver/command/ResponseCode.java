package fileserver.command;

// TODO fill with others commands. cristiano
public enum ResponseCode {
	
	COMMAND_NOT_FOUND {
		@Override
		public String toString() {
			return getResponseCode() + " error: command not found";
		}

		@Override
		public int getResponseCode() {
			return 0;
		}
	},
	
	REGISTERED {
		@Override
		public String toString() {
			return getResponseCode() + " registered";
		}

		@Override
		public int getResponseCode() {
			return 1;
		}
	},
	
	ERROR_USER_EXISTS {
		@Override
		public String toString() {
			return getResponseCode() + " error: user exists";
		}

		@Override
		public int getResponseCode() {
			return 2;
		}
	},
	
	FILE_UPLOADED_SUCCSESSFULLY {
		@Override
		public String toString() {
			return getResponseCode() + " file uploaded successfully";
		}

		@Override
		public int getResponseCode() {
			return 6;
		}
	},
	
	FILE_FOUND {
		@Override
		public String toString() {
			return getResponseCode() + " file found";
		}

		@Override
		public int getResponseCode() {
			return 9;
		}
	}, 
	
	PATH_DONT_EXISTS {
		@Override
		public int getResponseCode() {
			return 8;
		}

		@Override
		public String toString() {
			return getResponseCode() + " error: path don't exists";
		}
	};
	
	public abstract int getResponseCode();
	
	public abstract String toString();

}
