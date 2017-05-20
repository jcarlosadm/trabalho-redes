package fileserver.command;

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

	LOGGED_IN {
		@Override
		public int getResponseCode() {
			return 3;
		}

		@Override
		public String toString() {
			return getResponseCode() + " logged in";
		}
	},
	
	USER_NOT_EXISTS {
		@Override
		public int getResponseCode() {
			return 4;
		}

		@Override
		public String toString() {
			return getResponseCode() + " error: user not exists";
		}
	},
	
	WRONG_PASSWORD {
		@Override
		public int getResponseCode() {
			return 5;
		}

		@Override
		public String toString() {
			return getResponseCode() + " error: wrong password";
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
	
	FILE_UPLOADED_SUCCESSFULLY {
		@Override
		public int getResponseCode() {
			return 7;
		}

		@Override
		public String toString() {
			return getResponseCode() + " file uploaded successfully";
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
	
	PATH_SUCCESSFULLY_CREATED {
		@Override
		public int getResponseCode() {
			return 10;
		}

		@Override
		public String toString() {
			return getResponseCode() + " path successfully created";
		}
	},
	
	FILE_SYSTEM_TREE {
		@Override
		public int getResponseCode() {
			return 11;
		}

		@Override
		public String toString() {
			return getResponseCode() + " file system tree";
		}
	},
	
	DELETE_SUCCESSFUL {
		@Override
		public int getResponseCode() {
			return 12;
		}

		@Override
		public String toString() {
			return getResponseCode() + " delete successful";
		}
	},
	
	LOGOUT_SUCCESSFUL {
		@Override
		public int getResponseCode() {
			return 13;
		}

		@Override
		public String toString() {
			return getResponseCode() + " logout successful";
		}
	},

	PUBLIC_KEY {
		@Override
		public int getResponseCode() {
			return 14;
		}

		@Override
		public String toString() {
			return getResponseCode() + " public key";
		}
	};

	public abstract int getResponseCode();

	public abstract String toString();

}
