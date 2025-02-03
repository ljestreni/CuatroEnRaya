package org.iesalandalus.programacion.cuatroenraya.modelo;

import org.iesalandalus.programacion.utilidades.Entrada;

public class CuatroEnRaya {

    public static final int FILAS = 6;
    public static final int COLUMNAS = 7;
    public static final int FICHAS_IGUALES_CONSECUTIVAS_NECESARIAS = 4;

    private Jugador[] jugadores = new Jugador[2];
    private Tablero tablero = new Tablero();

    public CuatroEnRaya(Jugador jugador1, Jugador jugador2) {
        if (jugador1 == null || jugador2 == null) {
            throw new IllegalArgumentException("Los jugadores no pueden ser nulos.");
        }
        this.jugadores[0] = jugador1;
        this.jugadores[1] = jugador2;
    }

    public void jugar() {
        int turno = 0;
        boolean ganador = false;

        while (!tablero.estaLleno() && !ganador) {
            Jugador jugadorActual = jugadores[turno % 2];
            System.out.println("Turno de " + jugadorActual.toString());

            System.out.print("Introduce la columna (0-6): ");
            int columna = Entrada.entero();
            try {
                tablero.comprobarColumna(columna);
                ganador = tablero.introducirFicha(columna, jugadorActual.colorFichas());
            } catch (IllegalArgumentException | CuatroEnRayaExcepcion e) {
                System.out.println(e.getMessage());
                continue;
            }

            System.out.println(tablero.toString());

            if (ganador) {
                System.out.println("¡" + jugadorActual + " ha ganado!");
                return;
            }

            turno++;
        }

        if (!ganador) {
            System.out.println("El juego ha terminado. No hay más casillas disponibles.");
        }
    }

    public boolean tirar(Jugador jugador, int columna) {
        try {
            return tablero.introducirFicha(columna, jugador.colorFichas());
        } catch (CuatroEnRayaExcepcion e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
