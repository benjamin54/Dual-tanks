
public class Cratere {
	protected double xCratere;
	protected double yCratere;
	protected double rayon;
	
	Cratere(double xCratere, double yCratere, double rayon){
		this.xCratere=xCratere;
		this.xCratere=xCratere;
		this.xCratere=xCratere;
	}
	
	public void cratere(){ // afficher les mines
//		StdDraw.setPenColor(Constants.BACKGROUND_COLOR);
		StdDraw.filledCircle(xCratere,yCratere,rayon);
	}
}
