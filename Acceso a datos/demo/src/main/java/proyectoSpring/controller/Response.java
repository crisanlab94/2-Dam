package proyectoSpring.controller;

public class Response {
    public static final int NO_ERROR = 0;
    public static final int NOT_FOUND = 101;
    public static final String NO_MESSAGE = "";

    private Error error;

   
    public static class Error {
        private long errorCode;
        private String message;

    
        public Error(long errorCode, String message) {
            this.errorCode = errorCode;
            this.message = message;
        }

     
        public long getErrorCode() { return errorCode; }
        public void setErrorCode(long errorCode) { this.errorCode = errorCode; }
        public String getMessage() { return message; }
        public void setMessage(String message) { this.message = message; }
    }

   
    public Response() {}

    public Response(Error error) {
        this.error = error;
    }

  
    public Error getError() { return error; }
    public void setError(Error error) { this.error = error; }

    
    public static Response noErrorResponse() {
        return new Response(new Error(NO_ERROR, NO_MESSAGE));
    }

    public static Response errorResponse(int errorCode, String errorMessage) {
        return new Response(new Error(errorCode, errorMessage));
    }
}