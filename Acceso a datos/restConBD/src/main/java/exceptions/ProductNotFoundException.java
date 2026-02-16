package exceptions;

public class ProductNotFoundException extends RuntimeException  {
    /**
	 * 
	 */
	private static final long serialVersionUID = 5030665213865363481L;

    public ProductNotFoundException() {
        super();
    }
      

    public ProductNotFoundException(String message) {
        super(message);
    }
 
    public ProductNotFoundException(long id) {
        super("Product not found: " + id);
    }
}

