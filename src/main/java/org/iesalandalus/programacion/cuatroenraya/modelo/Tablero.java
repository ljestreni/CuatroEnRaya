package org.iesalandalus.programacion.cuatroenraya.modelo;

public class Tablero {
    public static final int FILAS = 6;
    public static final int COLUMNAS = 7;
    public static final int FICHAS_IGUALES_CONSECUTIVAS_NECESARIAS = 4;

    Casilla [][] tablero = new Casilla[6][7];

    public Tablero() {
        tablero = new Casilla[FILAS][COLUMNAS];
        for (int i = 0; i < FILAS; i++) {
            for (int j = 0; j < COLUMNAS; j++) {
                tablero[i][j] = new Casilla();
            }
        }
    }

    private boolean columnaVacia(int columna) {
        return !tablero[0][columna].estaOcupada();
    }

    public boolean estaVacio() {
            for (int columna = 0; columna < COLUMNAS; columna++) {
                if (!columnaVacia(columna)) {
                    return false;
                }
            }
        return true;
    }

    private boolean columnaLlena(int columna) {
        return tablero[FILAS - 1][columna].estaOcupada();
    }

    public boolean estaLleno() {
        for (int i = 0; i < FILAS; i++) {
            for (int columna = 0; columna < COLUMNAS; columna++) {
                if (!columnaLlena(columna)) {
                    return false;
                }
            }
        }
        return true;
    }

    private void comprobarFicha(Ficha ficha) {
        if (ficha == null) {
            throw new CuatroEnRayaExcepcion("Ficha no válida.");
        }
    }

    public void comprobarColumna(int columna) {
        if (columna < 0 || columna >= COLUMNAS) {
            throw new IllegalArgumentException("Columna incorrecta.");
        }
    }

    private int getPrimeraFilaVacia(int columna) {
        comprobarColumna(columna);

        for (int fila = FILAS - 1; fila >= 0; fila--) {
            if (!tablero[fila][columna].estaOcupada()) {
                return fila;
            }
        }
        throw new CuatroEnRayaExcepcion("Columna llena.");
    }

    private boolean objetivoAlcanzado (int fichasIgualesConsecutivas) {
        return fichasIgualesConsecutivas >= FICHAS_IGUALES_CONSECUTIVAS_NECESARIAS;
    }

    private boolean comprobarHorizontal(int fila, Ficha ficha) {
        int fichasIgualesConsecutivas = 0;

        for (int columna = 0; columna < COLUMNAS; columna++) {
            if (tablero[fila][columna].getFicha() == ficha) {
                fichasIgualesConsecutivas++;
                if (objetivoAlcanzado(fichasIgualesConsecutivas)) {
                    return true;
                }
            } else {
                fichasIgualesConsecutivas = 0;
            }
        }
        return false;
    }

    private boolean comprobarVertical(int columna, Ficha ficha) {
        int fichasIgualesConsecutivas = 0;
        for (int fila = 0; fila < FILAS; fila++) {
            if (tablero[fila][columna].getFicha() == ficha) {
                fichasIgualesConsecutivas++;
                if (objetivoAlcanzado(fichasIgualesConsecutivas)) {
                    return true;
                }
            } else {
                fichasIgualesConsecutivas = 0;
            }
        }
        return false;
    }

    private int menor(int numero1, int numero2) {
        return (numero1 < numero2) ? numero1 : numero2;
    }

    private boolean comprobarDiagonalNE(int fila, int columna, Ficha ficha) {
        comprobarFicha(ficha);
        comprobarColumna(columna);

        if (fila < 0 || fila >= FILAS) {
            throw new IllegalArgumentException("La fila indicada no es válida.");
        }

        int desplazamiento = menor(fila, columna);
        int filaInicial = fila - desplazamiento;
        int columnaInicial = columna - desplazamiento;
        int fichasIgualesConsecutivas = 0;

        while (filaInicial < FILAS && columnaInicial < COLUMNAS) {
            if (tablero[filaInicial][columnaInicial].getFicha() == ficha) {
                fichasIgualesConsecutivas++;
                if (objetivoAlcanzado(fichasIgualesConsecutivas)) {
                    return true;
                }
            } else {
                fichasIgualesConsecutivas = 0;
            }
            filaInicial++;
            columnaInicial++;
        }

        return false;
    }

    private boolean comprobarDiagonalNO(int fila, int columna, Ficha ficha) {
        comprobarFicha(ficha);
        comprobarColumna(columna);

        if (fila < 0 || fila >= FILAS) {
            throw new CuatroEnRayaExcepcion("La fila indicada no es válida.");
        }

        int desplazamiento = menor(fila, COLUMNAS - 1 - columna);
        int filaInicial = fila - desplazamiento;
        int columnaInicial = columna + desplazamiento;
        int fichasIgualesConsecutivas = 0;

        while (filaInicial < FILAS && columnaInicial >= 0) {
            if (tablero[filaInicial][columnaInicial].getFicha() == ficha) {
                fichasIgualesConsecutivas++;
                if (objetivoAlcanzado(fichasIgualesConsecutivas)) {
                    return true;
                }
            } else {
                fichasIgualesConsecutivas = 0;
            }
            filaInicial++;
            columnaInicial--;
        }

        return false;
    }

    private boolean comprobarTirada(int fila, int columna) {
        Ficha ficha = tablero[fila][columna].getFicha();

        return comprobarHorizontal(fila, ficha) || comprobarVertical(columna, ficha) ||
                comprobarDiagonalNE(fila, columna, ficha) || comprobarDiagonalNO(fila, columna, ficha);
    }

    public boolean introducirFicha(int columna, Ficha ficha) {
        if (ficha == null) {
            throw new NullPointerException("La ficha no puede ser nula.");
        }
        comprobarColumna(columna);
        comprobarFicha(ficha);
        int fila = getPrimeraFilaVacia(columna);
        if (fila == -1) {
            throw new CuatroEnRayaExcepcion("La fila en la columna " + columna + " está llena.");
        }
        tablero[fila][columna].setFicha(ficha);
        return comprobarTirada(fila, columna);
    }


    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        for (int fila = 0; fila < FILAS; fila++) {
            sb.append("|");
            for (int columna = 0; columna < COLUMNAS; columna++) {
                sb.append(tablero[fila][columna] == null ? " " : tablero[fila][columna]);
            }
            sb.append("|\n");
        }
        sb.append(" -------\n");
        return sb.toString();
    }

}
