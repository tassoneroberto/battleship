package battleship;

public class Report {
	private Posizione p;
	private boolean colpita;
	private boolean affondata;
	
	public Report(){
	}
	
	public Report(Posizione p, boolean colpita, boolean affondata) {
		this.p = p;
		this.colpita = colpita;
		this.affondata = affondata;
	}
	public Posizione getP() {
		return p;
	}
	public void setP(Posizione p) {
		this.p = p;
	}
	public boolean isColpita() {
		return colpita;
	}
	public void setColpita(boolean colpita) {
		this.colpita = colpita;
	}
	public boolean isAffondata() {
		return affondata;
	}
	public void setAffondata(boolean affondata) {
		this.affondata = affondata;
	}	
	public String toString(){
		return "coordinate:"+p+" colpito:"+colpita+" affondato:"+affondata;
	}
}
