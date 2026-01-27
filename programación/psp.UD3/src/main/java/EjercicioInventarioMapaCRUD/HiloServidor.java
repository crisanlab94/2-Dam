package EjercicioInventarioMapaCRUD;

import java.io.*;
import java.net.*;

public class HiloServidor extends Thread {

	private Socket socket;
	private InventarioCompartido compartido;

	public HiloServidor(Socket socket, InventarioCompartido compartido) {
		this.socket = socket;
		this.compartido = compartido;
	}

	public void run() {
		try {
			BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);

			boolean seguir = true;

			while (seguir) {

				String linea = entrada.readLine();

				if (linea == null) {
					seguir = false;
				} else {

					// ============================
					// READ
					// ============================
					if (linea.startsWith("LEER:")) {
						String prod = linea.substring(5);
						salida.println(compartido.leer(prod));

					} else {

						// ============================
						// CREATE
						// ============================
						if (linea.startsWith("CREAR:")) {
							String datos = linea.substring(6);
							String[] partes = datos.split(":");

							if (partes.length == 2) {
								String prod = partes[0];
								int cant = Integer.parseInt(partes[1]);
								salida.println(compartido.crear(prod, cant));
							} else {
								salida.println("Formato incorrecto.");
							}

						} else {

							// ============================
							// UPDATE
							// ============================
							if (linea.startsWith("ACTUALIZAR:")) {
								String datos = linea.substring(11);
								String[] partes = datos.split(":");

								if (partes.length == 2) {
									String prod = partes[0];
									int cant = Integer.parseInt(partes[1]);
									salida.println(compartido.actualizar(prod, cant));
								} else {
									salida.println("Formato incorrecto.");
								}

							} else {

								// ============================
								// DELETE
								// ============================
								if (linea.startsWith("ELIMINAR:")) {
									String prod = linea.substring(9);
									salida.println(compartido.eliminar(prod));

								} else {

									// ============================
									// LISTAR
									// ============================
									if (linea.equals("LISTAR")) {
										salida.println(compartido.listar());

									} else {

										// ============================
										// SALIR
										// ============================
										if (linea.equals("EXIT")) {
											seguir = false;
										} else {
											salida.println("Comando no reconocido.");
										}
									}
								}
							}
						}
					}
				}
			}

			socket.close();

		} catch (IOException e) {
			System.err.println("Error en hilo cliente: " + e.getMessage());
		}
	}
}
