package exceptions;



    public class MedicoNotFoundException extends RuntimeException {
        private static final long serialVersionUID = 1L;
        public MedicoNotFoundException(String mensaje) {
            super(mensaje);
        }
        
        public MedicoNotFoundException(int id) {
            super("No existe el médico con el id: " + id);
        }
    }
