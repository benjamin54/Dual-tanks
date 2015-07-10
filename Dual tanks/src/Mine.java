

public class Mine extends Obus{
	
	Mine(double xObus, double yObus, double rObus, double vxObus, double vyObus, int pPlayer, boolean pLife) {
		super(xObus, yObus, rObus, vxObus, vyObus, pPlayer, pLife);
	}
	
	public void Mines(){ // afficher les mines
		StdDraw.setPenColor(Constants.ACTIONS_COLOR[this.player]);
		StdDraw.filledSquare(xObus,yObus,80);
	}

}
