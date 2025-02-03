package org.iesalandalus.programacion.cuatroenraya;

import org.iesalandalus.programacion.cuatroenraya.modelo.CuatroEnRaya;
import org.iesalandalus.programacion.cuatroenraya.modelo.Jugador;
import org.iesalandalus.programacion.cuatroenraya.modelo.Ficha;
import org.iesalandalus.programacion.cuatroenraya.modelo.Tablero;
import org.iesalandalus.programacion.cuatroenraya.vista.Consola;
import org.iesalandalus.programacion.utilidades.Entrada;

import static org.iesalandalus.programacion.cuatroenraya.vista.Consola.mostrarTablero;

public class Main {
	public static void main(String[] args) {
		System.out.println("Introduce los datos del PRIMER jugador");
		System.out.print("Introduce el nombre del jugador: ");
		String nombre1 = Entrada.cadena();
		System.out.print("Elige el color de tus fichas (0-AZUL, 1-VERDE): ");
		int color1 = Entrada.entero();
		Ficha ficha1 = (color1 == 0) ? Ficha.AZUL : Ficha.VERDE;

		System.out.println("Introduce los datos del SEGUNDO jugador");
		System.out.print("Introduce el nombre del jugador: ");
		String nombre2 = Entrada.cadena();
		Ficha ficha2 = (ficha1 == Ficha.AZUL) ? Ficha.VERDE : Ficha.AZUL;

		Jugador jugador1 = new Jugador(nombre1, ficha1);
		Jugador jugador2 = new Jugador(nombre2, ficha2);

		CuatroEnRaya juego = new CuatroEnRaya(jugador1, jugador2);
		juego.jugar();
	}

}