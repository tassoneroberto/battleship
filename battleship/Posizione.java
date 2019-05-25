package battleship;

public class Posizione {
	private int coordX,coordY;

	public int getCoordX() {
		return coordX;
	}

	public int getCoordY() {
		return coordY;
	}

	public Posizione(int coordX, int coordY) {
		this.coordX = coordX;
		this.coordY = coordY;
	}

	public Posizione(Posizione p){
		this.coordX = p.coordX;
		this.coordY = p.coordY;
	}
	
	public void sposta(char dove){
		switch(dove){
			case 'N':
				coordX--;
				break;
			case 'S':
				coordX++;
				break;
			case 'O':
				coordY--;
				break;
			case 'E':
				coordY++;
				break;
		}
	}
	
	public String toString(){
		char Y=(char)(coordY+65);
		return ""+(coordX+1)+" "+Y;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Posizione other = (Posizione) obj;
		if (coordX != other.coordX)
			return false;
		if (coordY != other.coordY)
			return false;
		return true;
	}
	
	public boolean fuoriMappa(){
		if(coordX>=Mappa.DIM_MAPPA||coordY>=Mappa.DIM_MAPPA||coordX<0||coordY<0)
			return true;
		return false;
	}
	
	
}
