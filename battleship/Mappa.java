package battleship;

import java.util.LinkedList;
import java.util.Random;

public class Mappa {
	public static final int DIM_MAPPA = 10;
	private final char NULLA = '0', NAVE = 'X', ACQUA = 'A', COLPITO = 'C';
	private char[][] mappa;
	private LinkedList<Nave> listaNavi;

	public Mappa() {
		listaNavi = new LinkedList<Nave>();
		mappa = new char[DIM_MAPPA][DIM_MAPPA];
		for (int i = 0; i < DIM_MAPPA; i++)
			for (int j = 0; j < DIM_MAPPA; j++)
				mappa[i][j] = NULLA;
	}

	public void riempiMappaRandom() {
		clear();
		Random r = new Random();
		inserisciNaveRandom(r, 4);
		inserisciNaveRandom(r, 3);
		inserisciNaveRandom(r, 3);
		inserisciNaveRandom(r, 2);
		inserisciNaveRandom(r, 2);
		inserisciNaveRandom(r, 2);
		inserisciNaveRandom(r, 1);
		inserisciNaveRandom(r, 1);
		inserisciNaveRandom(r, 1);
		inserisciNaveRandom(r, 1);
	}

	private void clear() {
		for (int i = 0; i < DIM_MAPPA; i++)
			for (int j = 0; j < DIM_MAPPA; j++)
				mappa[i][j] = NULLA;

	}

	public boolean inserisciNave(int x, int y, int dim, int dir) {
		if (dir == 1 && x + dim > DIM_MAPPA) {
			return false;
		} // verticale
		if (dir == 0 && y + dim > DIM_MAPPA) {
			return false;
		} // orizzontale
		boolean inserito;

		if (dir == 0)
			inserito = verificaOrizzontale(x, y, dim);
		else
			inserito = verificaVerticale(x, y, dim);

		if (!inserito)
			return false;
		if (dir == 0) {
			Nave n = new Nave(x, y, x, y + dim - 1);
			listaNavi.add(n);
		} else {
			Nave n = new Nave(x, y, x + dim - 1, y);
			listaNavi.add(n);
		}
		for (int i = 0; i < dim; i++) {
			if (dir == 0) {
				mappa[x][y + i] = NAVE;
			} else
				mappa[x + i][y] = NAVE;
		}
		return true;
	}

	public int[] inserisciNaveRandom(Random random, int dimensione) {
		boolean inserito;
		int[] dati = new int[4];
		int direzione, riga, colonna;
		do {
			inserito = true;
			direzione = random.nextInt(2); // 0=Orizzontale, 1=Verticale
			if (direzione == 0) {
				colonna = random.nextInt(DIM_MAPPA - dimensione + 1);
				riga = random.nextInt(DIM_MAPPA);
			} else {
				colonna = random.nextInt(DIM_MAPPA);
				riga = random.nextInt(DIM_MAPPA - dimensione + 1);
			}
			if (direzione == 0)
				inserito = verificaOrizzontale(riga, colonna, dimensione);
			else
				inserito = verificaVerticale(riga, colonna, dimensione);
		} while (!inserito);
		if (direzione == 0) {
			Nave n = new Nave(riga, colonna, riga, colonna + dimensione - 1);
			listaNavi.add(n);
		} else {
			Nave n = new Nave(riga, colonna, riga + dimensione - 1, colonna);
			listaNavi.add(n);
		}
		for (int i = 0; i < dimensione; i++) {
			if (direzione == 0) {
				mappa[riga][colonna + i] = NAVE;
			} else
				mappa[riga + i][colonna] = NAVE;
		}
		dati[0] = riga;
		dati[1] = colonna;
		dati[2] = dimensione;
		dati[3] = direzione;
		return dati;
	}

	public boolean verificaVerticale(int riga, int colonna, int dimensione) {
		if (riga != 0)
			if (mappa[riga - 1][colonna] == NAVE)
				return false;
		if (riga != DIM_MAPPA - dimensione)// la nave finisce sul bordo
			if (mappa[riga + dimensione][colonna] == NAVE)
				return false;
		for (int i = 0; i < dimensione; i++) {
			if (colonna != 0)
				if (mappa[riga + i][colonna - 1] == NAVE)
					return false;
			if (colonna != DIM_MAPPA - 1)
				if (mappa[riga + i][colonna + 1] == NAVE)
					return false;
			if (mappa[riga + i][colonna] == NAVE)
				return false;
		}
		return true;
	}

	public boolean verificaOrizzontale(int riga, int colonna, int dimensione) {
		if (colonna != 0)
			if (mappa[riga][colonna - 1] == NAVE)
				return false;
		if (colonna != DIM_MAPPA - dimensione)
			if (mappa[riga][colonna + dimensione] == NAVE)
				return false;
		for (int i = 0; i < dimensione; i++) {
			if (riga != 0)
				if (mappa[riga - 1][colonna + i] == NAVE)
					return false;
			if (riga != DIM_MAPPA - 1)
				if (mappa[riga + 1][colonna + i] == NAVE)
					return false;
			if (mappa[riga][colonna + i] == NAVE)
				return false;
		}
		return true;
	}

	public boolean colpisci(Posizione p) {
		int riga = p.getCoordX();
		int colonna = p.getCoordY();
		if (mappa[riga][colonna] == NAVE) {
			mappa[riga][colonna] = COLPITO;
			return true;
		}
		mappa[riga][colonna] = ACQUA;
		return false;
	}

	public Nave affondato(Posizione p) {
		int riga = p.getCoordX();
		int col = p.getCoordY();
		Nave nave = null;
		for (int i = 0; i < listaNavi.size(); i++) {
			if (listaNavi.get(i).uguale(riga, col)) {
				nave = listaNavi.get(i);
				break;
			}
		}
		for (int i = nave.getXin(); i <= nave.getXfin(); i++) {
			for (int j = nave.getYin(); j <= nave.getYfin(); j++) {
				if (mappa[i][j] != COLPITO) {
					return null;
				}
			}
		}
		listaNavi.remove(nave);
		return nave;
	}

	public void setAcqua(Posizione p) {
		mappa[p.getCoordX()][p.getCoordY()] = ACQUA;
	}

	public boolean acqua(Posizione p) {
		return mappa[p.getCoordX()][p.getCoordY()] == ACQUA;
	}

	public boolean colpito(Posizione p) {
		return mappa[p.getCoordX()][p.getCoordY()] == COLPITO;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < DIM_MAPPA; i++) {
			for (int j = 0; j < DIM_MAPPA; j++) {
				sb.append(mappa[i][j] + " ");
			}
			sb.append("\n");
		}
		return sb.toString();
	}

	// metodo che permette di ricevere la lista di navi dell avversario
	public void setAdvShips(LinkedList<int[]> advShips) {
		listaNavi.clear();
		for (int[] a : advShips) {
			inserisciNave(a[0], a[1], a[2], a[3]);
			System.out.println("sto inserendo" + a[0] + a[1] + a[2] + a[3]);
		}
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++)
				System.out.print(mappa[i][j]);
			System.out.println("");
		}
	}
}
