package org.iesalandalus.programacion.cuatroenraya.vista;

import org.iesalandalus.programacion.cuatroenraya.modelo.Ficha;
import org.iesalandalus.programacion.cuatroenraya.modelo.Jugador;
import org.iesalandalus.programacion.utilidades.Entrada;

public class Consola {

    private Consola() {
    }

    public static String leerNombre() {
        String nombre;
        do {
            System.out.print("Introduce el nombre del jugador: ");
            nombre = Entrada.cadena().trim();
            if (nombre.isEmpty()) {
                System.out.println("El nombre no puede estar vacío.");
            }
        } while (nombre.isEmpty());
        return nombre;
    }

    public static Ficha elegirColorFichas() {
        int color;
        do {
            System.out.print("Elige el color de tus fichas (0-AZUL, 1-VERDE): ");
            color = Entrada.entero();
            if (color == 0) {
                return Ficha.AZUL;
            } else if (color == 1) {
                return Ficha.VERDE;
            } else {
                System.out.println("Opción no válida. Introduce 0 para AZUL o 1 para VERDE.");
            }
        } while (true);
    }

    public static Jugador leerJugador() {
        String nombre = leerNombre();
        Ficha ficha = elegirColorFichas();
        return new Jugador(nombre, ficha);
    }

    public static Jugador leerJugador(Ficha ficha) {
        String nombre = leerNombre();
        return new Jugador(nombre, ficha);
    }

    public static void mostrarTablero(String[][] tablero) {
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[i].length; j++) {
                System.out.print(tablero[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static int leerColumna(Jugador jugador) {
        int columna;
        do {
            System.out.printf("%s, introduce la columna en la que deseas introducir la ficha (0 - 6): ", jugador.nombre());
            columna = Entrada.entero();
            if (columna >= 0 && columna <= 6) {
                return columna;
            } else {
                System.out.println("Columna fuera de rango. Introduce un número entre 0 y 6.");
            }
        } while (true);
    }
}
