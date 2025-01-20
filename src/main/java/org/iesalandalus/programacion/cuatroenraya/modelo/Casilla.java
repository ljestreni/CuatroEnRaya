package org.iesalandalus.programacion.cuatroenraya.modelo;

import java.util.Objects;

public class Casilla {
    private Ficha ficha;

    public Casilla() {
        this.ficha = null;
    }

    public Ficha getFicha() {
        return ficha;
    }

    public void setFicha(Ficha ficha) throws CuatroEnRayaExcepcion {
        if (this.ficha != null) {
            throw new CuatroEnRayaExcepcion("La casilla ya está ocupada");
        }
        this.ficha = Objects.requireNonNull(ficha, "La ficha no puede ser nula");
    }

    public boolean estaOcupada() {
        return ficha != null;
    }

    @Override
    public String toString() {
        return String.format("%s", (ficha != null) ? ficha.toString() : " ");
    }
}
