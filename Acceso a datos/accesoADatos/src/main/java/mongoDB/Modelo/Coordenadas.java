package mongoDB.Modelo;

public class Coordenadas {
	private double lat;
	private double lon;
	
	
	public Coordenadas(double lat, double lon) {
		super();
		this.lat = lat;
		this.lon = lon;
	}


	public double getLat() {
		return lat;
	}


	public void setLat(double lat) {
		this.lat = lat;
	}


	public double getLon() {
		return lon;
	}


	public void setLon(double lon) {
		this.lon = lon;
	}


	@Override
	public String toString() {
		return "Coordenadas [lat=" + lat + ", lon=" + lon + "]";
	}
	
	
	
}
