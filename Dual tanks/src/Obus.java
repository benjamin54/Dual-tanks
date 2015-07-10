
import java.util.ArrayList;
import java.util.List;

class ObusConstants {
	public static final double SPEED_Obus = 120;
}

public class Obus {

	public static List<Tank> myTank =new ArrayList<Tank>();

	//CLASS PROPERTIES
	protected double xObus;
	protected double yObus;
	protected double vxObus;
	protected double vyObus;
	protected double rObus;
	protected int player;
	protected boolean life;
	
	//CLASS INITIALIZER
	Obus(double xObus, double yObus, double rObus, double vxObus, double vyObus,int pPlayer,boolean pLife) {
		this.xObus = xObus;
		this.yObus = yObus;
		this.rObus=rObus;
		this.vxObus = vxObus;
		this.vyObus = vyObus;
		this.player=pPlayer;
		this.life=pLife;
	}
	
	//GETTERS
	public double getYObus(){ // retourne la position en y du Obus
		return yObus;
	}
	
	public double getXObus(){ // retourne la position en x du Obus
		return xObus;
	}
	public double getVYObus(){ // retourne la vitesse en y du Obus
		return vyObus;
	}
	
	public double getVXObus(){ // retourne la vitesse en x du Obus
		return vxObus;
	}

	public int getPlayerObus(){ // retourne l'ID du joueur qui tire un Obus
		return player;
	}
	
	//SETTERS
	public void setXObus(double setXObus){ // déplacement du Obus
		xObus=setXObus+xObus;
	}
	public void setYObus(double setYObus){ // déplacement du Obus
		yObus=setYObus+yObus;
	}

	public void Obus(double timestep,double gravity) { 		//afficher les Obus

		StdDraw.setPenColor(Constants.ACTIONS_COLOR[this.player]);
		
		if (getPlayerObus()==0){ //obus joueur 1
			//StdDraw.filledCircle(xObus, yObus, rObus); //dessine l'obus
			xObus=xObus+ ObusConstants.SPEED_Obus;
			//xObus=xObus + vxObus*timestep;
			yObus=yObus -0.5*gravity*timestep*timestep + vyObus*timestep;
			//StdDraw.show(80);
		}
		else if (getPlayerObus()==1){ //obus joueur 2

			xObus=xObus - ObusConstants.SPEED_Obus;
			yObus=yObus -0.5*gravity*timestep*timestep + vyObus*timestep;

		}
		vyObus=vyObus + gravity/timestep;
	}

//	public void Missile(double timestep,double gravity) { 		//afficher les Obus
//
//		StdDraw.setPenColor(Constants.ACTIONS_COLOR[this.player]);
//			//StdDraw.filledCircle(xObus, yObus, rObus); //dessine l'obus
//			xObus=xObus + ObusConstants.SPEED_Obus;
//			//xObus=xObus + vxObus*timestep;
//			yObus=yObus -0.5*gravity*timestep*timestep + vyObus*timestep;
//			//StdDraw.show(80);
//
//		vyObus=vyObus + gravity/timestep;
//
//	}
	public void Mines() { 
		//afficher les mines
	}

	public void Missile(double timestep, double gravity) {
		myTank=Main.getListTank();	
		// affiche missiles
		StdDraw.setPenColor(Constants.ACTIONS_COLOR[this.player]);
		
		if (getPlayerObus()==0){ //obus joueur 1
			
			StdDraw.setPenColor(Constants.ACTIONS_COLOR[this.player]);
			StdDraw.filledCircle(xObus, yObus, rObus); //dessine l'obus
			for(int i=0;i < myTank.size();i++){
				if (yObus<=myTank.get(i).getY()+6200){
					xObus=xObus+ ObusConstants.SPEED_Obus;
					//xObus=xObus + vxObus*timestep;
					yObus=yObus -0.5*gravity*timestep*timestep + vyObus*timestep;
					vyObus=vyObus + gravity/timestep;
				}
				else{
					xObus=xObus;
					yObus=yObus -0.5*gravity*timestep*timestep + vyObus*timestep;
					vyObus = -800;
				}
			}
		}
		else if (getPlayerObus()==1){ //obus joueur 2
			StdDraw.setPenColor(Constants.ACTIONS_COLOR[this.player]);
			StdDraw.filledCircle(xObus, yObus, rObus); //dessine l'obus
			for(int i=0;i < myTank.size();i++){
				if (yObus<=myTank.get(i).getY()+6200){
					xObus=xObus - ObusConstants.SPEED_Obus;
					yObus=yObus -0.5*gravity*timestep*timestep + vyObus*timestep;
					vyObus=vyObus + gravity/timestep;
				}
				else{
					xObus=xObus;
					yObus=yObus -0.5*gravity*timestep*timestep + vyObus*timestep;
					vyObus = -800;
				}
			}
		}
		vyObus=vyObus + gravity/timestep;
	}
}


//ceci pose des mines
//public void Obus(double timestep,double gravity) { 		//afficher les Obus
//
//	StdDraw.setPenColor(Constants.ACTIONS_COLOR[this.player]);
//	
//	vyObus=vyObus + gravity/timestep;
//	for(int k=0;k<myTank.size();k++){
//		if(k==0){
//			//StdDraw.filledCircle(xObus, yObus, rObus); //dessine l'obus
//			xObus=xObus + ObusConstants.SPEED_Obus;
//			//xObus=xObus + vxObus*timestep;
//			yObus=yObus -0.5*gravity*timestep*timestep + vyObus*timestep;
//			//StdDraw.show(80);
//		}
//		else if (k==1){
//			//StdDraw.filledCircle(xObus, yObus, rObus); //dessine l'obus
//			xObus=xObus - ObusConstants.SPEED_Obus;
//			//xObus=xObus + vxObus*timestep;
//			yObus=yObus -0.5*gravity*timestep*timestep + vyObus*timestep;
//			//StdDraw.show(80);
//		}
//	}
//}