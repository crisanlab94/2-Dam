package acceso.myshop.controllers;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Response {
	public static final int NO_ERROR = 0;
	public static final int NOT_FOUND = 101;
	public static final String NO_MESSAGE = "";

	private Error error;  

	@Data
	@AllArgsConstructor(access = AccessLevel.PRIVATE)
	static class Error {
		public Error(int noError, String noMessage) {
			// TODO Auto-generated constructor stub
		}
		private long errorCode;
		private String message;
		public long getErrorCode() {
			return errorCode;
		}
		public void setErrorCode(long errorCode) {
			this.errorCode = errorCode;
		}
		public String getMessage() {
			return message;
		}
		public void setMessage(String message) {
			this.message = message;
		}
		
		
	}
	
	
	public Response(Error error) {
		super();
		this.error = error;
	}
	
	

	public Error getError() {
		return error;
	}



	public void setError(Error error) {
		this.error = error;
	}



	public static int getNoError() {
		return NO_ERROR;
	}



	public static int getNotFound() {
		return NOT_FOUND;
	}



	public static String getNoMessage() {
		return NO_MESSAGE;
	}



	public static Response noErrorResponse() {
		return new Response(new Error(NO_ERROR, NO_MESSAGE));
	}

	public static Response errorResonse(int errorCode, String errorMessage) {
		return new Response(new Error(errorCode, errorMessage));
	}
}
