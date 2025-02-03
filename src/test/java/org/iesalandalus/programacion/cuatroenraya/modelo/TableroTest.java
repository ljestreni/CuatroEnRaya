package org.iesalandalus.programacion.cuatroenraya.modelo;

import org.iesalandalus.programacion.cuatroenraya.vista.Consola;

public class CuatroEnRaya {

	public static final int NUMERO_JUGADORES = 2;
	private Jugador[] jugadores;
	private Tablero tablero;

	public CuatroEnRaya(Jugador jugador1, Jugador jugador2) {
		if (jugador1 == null || jugador2 == null) {
			throw new IllegalArgumentException("Los jugadores no pueden ser nulos.");
		}

		jugadores = new Jugador[NUMERO_JUGADORES];
		jugadores[0] = jugador1;
		jugadores[1] = jugador2;
		tablero = new Tablero();
	}

	public boolean tirar(Jugador jugador) {
		int columna;
		boolean jugadaCorrecta = false;
		boolean ganador = false;

		while (!jugadaCorrecta && !ganador) {
			columna = Consola.pedirColumna();
			try {
				jugadaCorrecta = tablero.introducirFicha(columna, jugador.getFicha());
				ganador = tablero.comprobarTirada(columna, jugador.getFicha());
			} catch (CuatroEnRayaExcepcion e) {
				System.out.println("Columna llena. Intenta otra vez.");
			}
		}

		return ganador;
	}

	public void jugar() {
		int turno = 0;
		boolean ganador = false;

		while (!tablero.estaLleno() && !ganador) {
			Jugador jugadorActual = jugadores[turno % NUMERO_JUGADORES];
			System.out.println("Es el turno de " + jugadorActual.getNombre());
			ganador = tirar(jugadorActual);

			if (ganador) {
				System.out.println("¡Ha ganado " + jugadorActual.getNombre() + "!");
			} else {
				turno++;
			}
		}

		if (!ganador) {
			System.out.println("El juego ha terminado. No hay más casillas libres.");
		}
	}
}
