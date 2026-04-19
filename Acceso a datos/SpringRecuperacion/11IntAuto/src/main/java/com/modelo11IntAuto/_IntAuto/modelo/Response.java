package com.modelo11IntAuto._IntAuto.modelo;



/**
 * Clase para devolver respuestas estructuradas en el controlador o manejar errores.
 * Va en el paquete de modelos según tus instrucciones.
 */
public class Response {  
    public static final int NO_ERROR = 0;
    public static final int NOT_FOUND = 101;
    public static final String NO_MESSAGE = "";

    private Error error;

    // Clase interna para el detalle del error
    public static class Error {
        private long errorCode;
        private String message;

        public Error(long errorCode, String message) {
            this.errorCode = errorCode;
            this.message = message;
        }
        // Getters
        public long getErrorCode() { return errorCode; }
        public String getMessage() { return message; }
    }

    public Response(Error error) {
        this.error = error;
    }

    // Métodos estáticos para crear respuestas rápidamente
    public static Response noErrorResponse() {
        return new Response(new Error(NO_ERROR, NO_MESSAGE));
    }

    public static Response errorResonse(int errorCode, String errorMessage) {
        return new Response(new Error(errorCode, errorMessage));
    }

    public Error getError() { return error; }
}
