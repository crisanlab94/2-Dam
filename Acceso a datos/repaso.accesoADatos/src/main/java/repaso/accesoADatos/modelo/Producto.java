package repaso.accesoADatos.modelo;

import java.util.Objects;

public class Producto {
	 private int id;
	    private boolean enVenta;
	    private String nombre;
	    private double precio;
	    private int stock;
	    
		public Producto() {
			super();
		}

		public Producto(int id, boolean enVenta, String nombre, double precio, int stock) {
			super();
			this.id = id;
			this.enVenta = enVenta;
			this.nombre = nombre;
			this.precio = precio;
			this.stock = stock;
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public boolean isEnVenta() {
			return enVenta;
		}

		public void setEnVenta(boolean enVenta) {
			this.enVenta = enVenta;
		}

		public String getNombre() {
			return nombre;
		}

		public void setNombre(String nombre) {
			this.nombre = nombre;
		}

		public double getPrecio() {
			return precio;
		}

		public void setPrecio(double precio) {
			this.precio = precio;
		}

		public int getStock() {
			return stock;
		}

		public void setStock(int stock) {
			this.stock = stock;
		}

		@Override
		public int hashCode() {
			return Objects.hash(id);
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Producto other = (Producto) obj;
			return id == other.id;
		}

		@Override
		public String toString() {
			return "Producto [id=" + id + ", enVenta=" + enVenta + ", nombre=" + nombre + ", precio=" + precio
					+ ", stock=" + stock + "]";
		}
	    
	    

}
