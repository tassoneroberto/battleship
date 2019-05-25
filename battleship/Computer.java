package battleship;

import java.util.LinkedList;
import java.util.Random;

public class Computer {
	private LinkedList<Posizione> listaColpi;
	private Random r;
	private int colpito;
	private LinkedList<String> possibilita;
	private Posizione ultimoColpo;
	private String direzione;
	private Mappa plMap;
	private Posizione primoColpito;// posizione in cui colpisco per la priam
									// volta la nave

	public Computer(Mappa mappaAvversario) {
		listaColpi = new LinkedList<Posizione>();
		this.plMap = mappaAvversario;
		for (int i = 0; i < Mappa.DIM_MAPPA; i++) {
			for (int j = 0; j < Mappa.DIM_MAPPA; j++) {
				Posizione p = new Posizione(i, j);
				listaColpi.add(p);// Inizializza colpi possibili
			}
		}
		r = new Random();
		colpito = 0;
	}

	public Report mioTurno() {

		Report rep = new Report();
		if (colpito == 0) {
			boolean colpo = sparaRandom();
			rep.setP(ultimoColpo);
			rep.setColpita(colpo);
			Nave affondata;
			if (colpo) {
				colpito++;
				affondata = plMap.affondato(ultimoColpo);
				if (affondata != null) {
					rep.setAffondata(true);
					rimuoviContorni(affondata);
					colpito = 0;
					direzione = null;
				} else {
					primoColpito = ultimoColpo;
					possibilita = new LinkedList<String>();
					inizializzaLista();
				}
			}
			return rep;
		} // Sparo randomaticamente
		if (colpito == 1) {
			boolean colpo = sparaMirato1();
			Nave affondata;
			rep.setP(ultimoColpo);
			rep.setColpita(colpo);
			rep.setAffondata(false);
			if (colpo) {
				colpito++;
				possibilita = null;
				affondata = plMap.affondato(ultimoColpo);
				if (affondata != null) {
					rep.setAffondata(true);
					rimuoviContorni(affondata);
					colpito = 0;
					direzione = null;
				}
			}
			return rep;
		}
		if (colpito >= 2) {
			boolean colpo = sparaMirato2();
			Nave affondata;
			rep.setP(ultimoColpo);
			rep.setColpita(colpo);
			rep.setAffondata(false);
			if (colpo) {
				colpito++;
				affondata = plMap.affondato(ultimoColpo);
				if (affondata != null) {
					rep.setAffondata(true);
					rimuoviContorni(affondata);
					colpito = 0;
					direzione = null;
				}
			} else {
				invertiDirezione();
			}
			return rep;
		}
		return null;// irragiungibile
	}

	private boolean sparaRandom() {
		int tiro = r.nextInt(listaColpi.size());
		Posizione p = listaColpi.remove(tiro);
		ultimoColpo = p;
		boolean colpo = plMap.colpisci(p);
		return colpo;
	}

	private boolean sparaMirato1() {
		boolean errore = true;
		Posizione p = null;
		do {
			int tiro = r.nextInt(possibilita.size());
			String dove = possibilita.remove(tiro);
			p = new Posizione(primoColpito);
			p.sposta(dove.charAt(0));
			direzione = dove;
			if (!plMap.acqua(p)) {
				listaColpi.remove(p);
				errore = false;
			}
		} while (errore);// verifica che non si cerchi di sparare su una
							// posizione già colpita
		ultimoColpo = p;
		return plMap.colpisci(p);
	}

	private boolean sparaMirato2() {
		boolean colpibile = false;
		Posizione p = new Posizione(ultimoColpo);
		do {
			p.sposta(direzione.charAt(0));

			if (p.fuoriMappa() || plMap.acqua(p)) {
				invertiDirezione();
			} else {
				if (!plMap.colpito(p)) {
					colpibile = true;
				}

			}
		} while (!colpibile);
		listaColpi.remove(p);
		ultimoColpo = p;
		return plMap.colpisci(p);
	}

	//

	private void rimuoviContorni(Nave affondata) {
		int Xin = affondata.getXin();
		int Xfin = affondata.getXfin();
		int Yin = affondata.getYin();
		int Yfin = affondata.getYfin();
		if (Xin == Xfin) {// orizzontale
			if (Yin != 0) {
				Posizione p = new Posizione(Xin, Yin - 1);
				if (!plMap.acqua(p)) {
					listaColpi.remove(p);
					plMap.setAcqua(p);

				}
			}
			if (Yfin != Mappa.DIM_MAPPA - 1) {
				Posizione p = new Posizione(Xin, Yfin + 1);
				if (!plMap.acqua(p)) {
					listaColpi.remove(p);
					plMap.setAcqua(p);
				}
			}
			if (Xin != 0) {
				for (int i = 0; i <= Yfin - Yin; i++) {
					Posizione p = new Posizione(Xin - 1, Yin + i);
					if (!plMap.acqua(p)) {
						listaColpi.remove(p);
						plMap.setAcqua(p);
					}
				}

			}
			if (Xin != Mappa.DIM_MAPPA - 1) {
				for (int i = 0; i <= Yfin - Yin; i++) {
					Posizione p = new Posizione(Xin + 1, Yin + i);
					if (!plMap.acqua(p)) {
						listaColpi.remove(p);
						plMap.setAcqua(p);
					}
				}
			}
		} else {// verticale
			if (Xin != 0) {
				Posizione p = new Posizione(Xin - 1, Yin);
				if (!plMap.acqua(p)) {
					listaColpi.remove(p);
					plMap.setAcqua(p);
				}
			}
			if (Xfin != Mappa.DIM_MAPPA - 1) {
				Posizione p = new Posizione(Xfin + 1, Yin);
				if (!plMap.acqua(p)) {
					listaColpi.remove(p);
					plMap.setAcqua(p);
				}
			}
			if (Yin != 0) {
				for (int i = 0; i <= Xfin - Xin; i++) {
					Posizione p = new Posizione(Xin + i, Yin - 1);
					if (!plMap.acqua(p)) {
						listaColpi.remove(p);
						plMap.setAcqua(p);
					}
				}

			}
			if (Yfin != Mappa.DIM_MAPPA - 1) {
				for (int i = 0; i <= Xfin - Xin; i++) {
					Posizione p = new Posizione(Xin + i, Yin + 1);
					if (!plMap.acqua(p)) {
						listaColpi.remove(p);
						plMap.setAcqua(p);
					}
				}
			}
		}
	}

	private void inizializzaLista() {
		if (ultimoColpo.getCoordX() != 0) {
			possibilita.add("N");
		}
		if (ultimoColpo.getCoordX() != Mappa.DIM_MAPPA - 1) {
			possibilita.add("S");
		}
		if (ultimoColpo.getCoordY() != 0) {
			possibilita.add("O");
		}
		if (ultimoColpo.getCoordY() != Mappa.DIM_MAPPA - 1) {
			possibilita.add("E");
		}
	}

	private void invertiDirezione() {
		if (direzione.equals("N")) {
			direzione = "S";
		} else if (direzione.equals("S")) {
			direzione = "N";
		} else if (direzione.equals("E")) {
			direzione = "O";
		} else if (direzione.equals("O")) {
			direzione = "E";
		}
	}

}
