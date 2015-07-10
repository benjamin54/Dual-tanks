

import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

class TankConstants {
	
	public static final HashMap<String, Integer> FIRST_PLAYER_CONTROLS = new HashMap<String, Integer>();
	public static final HashMap<String, Integer> SECOND_PLAYER_CONTROLS = new HashMap<String, Integer>();
	public static final HashMap<String, Integer>[] PLAYERS_CONTROLS = new HashMap[2];
	static {
		//FIRST PLAYER CONTROLS
		FIRST_PLAYER_CONTROLS.put("right",KeyEvent.VK_RIGHT);
		FIRST_PLAYER_CONTROLS.put("left",KeyEvent.VK_LEFT);
		FIRST_PLAYER_CONTROLS.put("missile",KeyEvent.VK_NUMPAD2);
		FIRST_PLAYER_CONTROLS.put("down",KeyEvent.VK_NUMPAD1);
		FIRST_PLAYER_CONTROLS.put("Obus",KeyEvent.VK_SPACE);
		FIRST_PLAYER_CONTROLS.put("mine",KeyEvent.VK_NUMPAD3);
		
		//SECOND PLAYER CONTROLS
		SECOND_PLAYER_CONTROLS.put("right", KeyEvent.VK_F);
		SECOND_PLAYER_CONTROLS.put("left", KeyEvent.VK_S);
		SECOND_PLAYER_CONTROLS.put("missile", KeyEvent.VK_Z);
		SECOND_PLAYER_CONTROLS.put("down", KeyEvent.VK_W);
		SECOND_PLAYER_CONTROLS.put("mine", KeyEvent.VK_A);
		SECOND_PLAYER_CONTROLS.put("Obus", KeyEvent.VK_Q);

		PLAYERS_CONTROLS[0] = FIRST_PLAYER_CONTROLS;
		PLAYERS_CONTROLS[1] = SECOND_PLAYER_CONTROLS;
	}
	
	//RECT Tank
	public static final double X_Tank = 1400;
	public static final double Y_Tank = 1500;
	public static final double DISTANCE_Tank = 7600;
	public static final double SPACE_MINE_Obus = 500;
	public static final int AMOUNT_OF_LIFE = 10;
	public static final int STOCK_MINE = 2;
	public static final int STOCK_MISSILES = 1;
	
	//Tank COLORS
	protected static final Color COLOR_FIRST_Tank = new Color(0,0,255);//bleu
	protected static final Color COLOR_SECOND_Tank = new Color(255,0,0);//rouge
	protected static final Color[] Tank_COLORS = {COLOR_FIRST_Tank,COLOR_SECOND_Tank};
}

public class Tank {
	
	//GLOBAL LIST
	public static List<Tank> myTank = new ArrayList<Tank>();
	public static List<Obus> myObus = new ArrayList<Obus>();
	public static List<Ground> myRectangle = new ArrayList<Ground>();
	public static List<Obus> myMines =new ArrayList<Obus>();
	public static List<Obus> myMissiles = new ArrayList<Obus>();
	public static List<Cannon> myCannon =new ArrayList<Cannon>();
	
	//CLASS PROPERTIES
	protected double x;
	protected double y;
	protected int id;
	protected int stockMine;
	protected int stockMissile;
	protected int life;
	protected boolean isMined;
	protected boolean isObusd;
	protected boolean isMissiled;
	

	//CLASS INITIALIZER
	Tank(double px, double py,int pId) { 
		this.x = px;
		this.y = py;
		this.id = pId;
		this.life = TankConstants.AMOUNT_OF_LIFE;
		this.stockMine = TankConstants.STOCK_MINE;
		this.stockMissile=TankConstants.STOCK_MISSILES;
		this.isMined = false;
		this.isObusd = false;
		this.isMissiled = false;
	}

	//GETTERS
	public double getX() {// retourne la position en x du tank
		return x;
	}

	public double getY() { // retourne la position en y du tank
		return y;
	}

	public int getLife() { // retourne le nombre de vie 
		return life;
	}

	public int getId() { // retourne l'ID
		return id;
	}
	
	public boolean getIsMined() { // retourne vrai si le tank lâche une mine, faux sinon
		return isMined;
	}
	
	public boolean getIsObusd() { // retourne vrai si le tank tire un Obus, faux sinon
		return isObusd;
	}
	public boolean getIsMissiled() { // retourne vrai si le tank tire un missile vertical, faux sinon
		return isMissiled;
	}
	
	//SETTERS
	public void setLife() { // retire une vie
		myTank = Main.getListTank();
		life = getLife() - 1;
		StdDraw.clear(Constants.ACTIONS_COLOR[this.id]);
	}
	
	public void setX(double dead) { // permet un changement de position en x
		x -= dead;
	}
	
	public void setY(double gravity) { // permet un changement de position en y
		y -= gravity;
	}
	
	public void setIsMined(boolean mined) {
		isMined = mined;
	}
	
	public void setIsObusd(boolean Obusd) {
		isObusd = Obusd;
	}
	public void setIsMissiled(boolean Missiled) {
		isMissiled = Missiled;
	}

	//DRAW TankS
//	public void paint(int i) { // affiche le tank
//		StdDraw.setPenColor(TankConstants.Tank_COLORS[i-1]);
//		//StdDraw.filledRectangle(x, y, 280, 200); //rectangle de la couleur du tank
//	}
	
	public void controlLife() { // affiche les vies des joueurs

		myTank = Main.getListTank();
		for (int k = 0; k < myTank.size(); k++) {
			if(myTank.get(k).life>0){ // si le joueur a encore des vies
				for (int g = 0; g < myTank.get(k).life; g++) { 
					if(myTank.get(k).getLife()!=0) {
						StdDraw.setPenColor(new Color(94,218,146));
						StdDraw.filledSquare(900+k*3000+g*220,Constants.Y_MAX-260,100); // on affiche la vie des joueurs
					}
				}
			}else{ // sinon on affiche une tête de mort
				StdDraw.picture(500+k*3000+1250, Constants.Y_MAX, "./src/tete-de-mort_design.png", 300, 500);
				
			}

		}
	}
	
	//fin du jeu
	public static boolean endGame(){ // si 1 ou tous les joueur ont perdu, c'est la fin du jeu
		int numberOfPlayersDead=0;
		for (int k = 0; k < myTank.size(); k++) { 
			if(myTank.get(k).life<1){ // si un joueur est mort on incrémente la variable numberOfPlayersDead
				numberOfPlayersDead++;
			}
		}
		if (myTank.size()>0 && numberOfPlayersDead >= 1){ // si le nombre de morts est de 1 ou plusc'est la fin du jeu
			return true;
		}
		return false;
	}

	//barre de vie
	public void barre() { // barre encadrant la barre de vie et affichage 
		myTank = Main.getListTank();
		for (int k = 0; k != myTank.size(); k++) {
			StdDraw.setPenColor(TankConstants.Tank_COLORS[k]);
			StdDraw.filledRectangle(655+k*3000+1250,Constants.Y_MAX,1250,500);
			StdDraw.setPenColor(Color.WHITE);
			StdDraw.setFont(new Font("Dialog", Font.PLAIN, 20));
		}
	}
	//bordures
	public void border() { // définit des bordures autour du terrain pour qu'aucun joueur ne puisse sortir de la fênetre
		if (y <= 0) {
			y = 100;
		} else if (x > Constants.X_MAX) {
			x = Constants.X_MAX - 200;
		} else if (x <= 50) {
			x = 200;
		} else if (y > Constants.Y_MAX) {
			y = Constants.Y_MAX - 100;
		}	
	}

//	public void top() { // déplacement vers le haut
//		y  += 75;
//	}
//	public void bottom() { // déplacement vers le bas
//		y -= 75;
//	}
	public void left() { // déplacement vers la gauche
		x -= 40;
	}
	public void right() { // déplacement vers la droite
		x += 40;
	}
	
	public static void controlPlayer(int pId) { // gère le déplacement des joueurs en mode normal et en inversion des commandes
		

//			if (StdDraw.isKeyPressed(TankConstants.PLAYERS_CONTROLS[pId].get("up")))
//				(myTank.get(pId)).top();
			if (StdDraw.isKeyPressed(TankConstants.PLAYERS_CONTROLS[pId].get("left")))
				(myTank.get(pId)).left();
			if (StdDraw.isKeyPressed(TankConstants.PLAYERS_CONTROLS[pId].get("right")))
				(myTank.get(pId)).right();
//			if (StdDraw.isKeyPressed(TankConstants.PLAYERS_CONTROLS[pId].get("down")))
//				(myTank.get(pId)).bottom();
		
	}

	public void contactObusTank() { // gère le contact entre les tanks et les Obus
		myTank = Main.getListTank();
		myObus = Main.getListObus();
		for (int i = 0; i != myObus.size(); i++) {
			for (int k = 0; k != myTank.size(); k++) {
				if (this.id != k) {
					if (ObusTouchedOtherPlayer(i,k)) { // si ObusTouchedOtherPlayer est vraie on retire une vie au joueur touché
						StdDraw.picture(myObus.get(i).getXObus(), myObus.get(i).getYObus(), "./src/boom.png", 800, 1000);
						myObus.get(i).life=false;
						myTank.get(k).setLife();
						myTank.get(k).setLife();
					}

				}
			}
		}
	}
	public void contactMissileTank() { // gère le contact entre les tanks et les Obus
		myTank = Main.getListTank();
		myMissiles = Main.myMissiles;
		for (int i = 0; i != myMissiles.size(); i++) {
			for (int k = 0; k != myTank.size(); k++) {
				if (this.id != k) {
					if (MissileTouchedOtherPlayer(i,k)) { // si ObusTouchedOtherPlayer est vraie on retire une vie au joueur touché
						myMissiles.get(i).life=false;
						StdDraw.picture(myObus.get(i).getXObus(), myObus.get(i).getYObus(), "./src/boom.png", 1000, 1200);
						myTank.get(k).setLife();
						myTank.get(k).setLife();
						myTank.get(k).setLife();
					}

				}
			}
		}
	}

	public void contactMineTank(){ // gère le contact entre les mines et les tanks
		myMines=Main.myMines;
		myTank = Main.myTank;
		for (int i = 0; i != myMines.size(); i++) {

			for (int k = 0; k != myTank.size(); k++) {
				if (this.id != k) {

					if (mineTouchedOtherPlayer(i,k)) // si mineTouchedOtherPlayer est vraie on retire une vie au joueur touché
					{
						myMines.get(i).life=false;
						StdDraw.picture(myObus.get(i).getXObus(), myObus.get(i).getYObus(), "./src/boom.png", 800, 1000);
						myTank.get(k).setLife();
						myTank.get(k).setLife(); //-2vies
					}
				}
			}
		}
	}
	
	public boolean mineTouchedOtherPlayer (int indexMine, int indexTank) { // retourne vrai si la mine touche un joueur, faux sinon
		if(myMines.get(indexMine).getXObus() < myTank.get(indexTank)
							.getX() + TankConstants.SPACE_MINE_Obus
							&& myMines.get(indexMine).getXObus() > myTank.get(
									indexTank).getX() - TankConstants.SPACE_MINE_Obus
									&& myMines.get(indexMine).getYObus() < myTank.get(
											indexTank).getY() + TankConstants.SPACE_MINE_Obus
											&& myMines.get(indexMine).getYObus() > myTank.get(
													indexTank).getY() - TankConstants.SPACE_MINE_Obus
													&& ((myMines.get(indexMine)).getPlayerObus()) == (this.id))
			return true;
		else
			return false;
	}

	public boolean ObusTouchedOtherPlayer (int indexObus, int indexTank) { // retourne vrai si le Obus touche un joueur, faux sinon
		if (myObus.get(indexObus).getXObus() < myTank.get(indexTank)
				.getX() + TankConstants.SPACE_MINE_Obus
				&& myObus.get(indexObus).getXObus() > myTank.get(
						indexTank).getX() - TankConstants.SPACE_MINE_Obus
						&& myObus.get(indexObus).getYObus() < myTank.get(
								indexTank).getY() + TankConstants.SPACE_MINE_Obus
								&& myObus.get(indexObus).getYObus() > myTank.get(
										indexTank).getY() - TankConstants.SPACE_MINE_Obus
										&& ((myObus.get(indexObus)).getPlayerObus()) == (this.id))
			return true;
		else
			return false;
	}
	public boolean MissileTouchedOtherPlayer (int indexMissile, int indexTank) { // retourne vrai si le Obus touche un joueur, faux sinon
		if (myMissiles.get(indexMissile).getXObus() < myTank.get(indexTank)
				.getX() + TankConstants.SPACE_MINE_Obus
				&& myMissiles.get(indexMissile).getXObus() > myTank.get(
						indexTank).getX() - TankConstants.SPACE_MINE_Obus
						&& myMissiles.get(indexMissile).getYObus() < myTank.get(
								indexTank).getY() + TankConstants.SPACE_MINE_Obus
								&& myMissiles.get(indexMissile).getYObus() > myTank.get(
										indexTank).getY() - TankConstants.SPACE_MINE_Obus
										&& ((myMissiles.get(indexMissile)).getPlayerObus()) == (this.id))
			return true;
		else
			return false;
	}

}