package battleship;

public class Nave {

	private int xin, yin;
	private int xfin, yfin;

	public Nave(int xin, int yin, int xfin, int yfin) {
		this.xin = xin;
		this.yin = yin;
		this.xfin = xfin;
		this.yfin = yfin;
	}

	public int getXin() {
		return xin;
	}

	public int getYin() {
		return yin;
	}

	public int getXfin() {
		return xfin;
	}

	public int getYfin() {
		return yfin;
	}

	public boolean uguale(int x, int y) {
		if (x <= xfin && x >= xin && y <= yfin && y >= yin) {
			return true;
		}
		return false;

	}

	public String toString() {
		return xin + "-" + yin + "  " + xfin + "-" + yfin;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Nave other = (Nave) obj;
		if (xfin != other.xfin)
			return false;
		if (xin != other.xin)
			return false;
		if (yfin != other.yfin)
			return false;
		if (yin != other.yin)
			return false;
		return true;
	}

}
