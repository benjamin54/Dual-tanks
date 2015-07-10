import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;
import java.lang.String;

class Constants {
	
	//SIZE VIEW
	protected static final int X_VIEW = 900; //regler taille fenetre jeu
	protected static final int Y_VIEW = 500;

	//SIZE SCALE
	public static final double X_MAX = 10000;
	public static final double Y_MAX = 10000;
	
	//HEIGHT Obus
	public static final float r = 60;
	
	//BACKGROUND COLORS
	public static final Color BLUE_COLOR = new Color(0,0,255);
	public static final Color RED_COLOR = new Color(255,0,0);
	public static final Color[] ACTIONS_COLOR = {BLUE_COLOR,RED_COLOR};
		
	//BACKGROUND COLORS
//	public static final Color BACKGROUND_COLOR = new Color(34,139,34); //forest green
}

public class Main {
	
	//LOCAL LIST
	public static List<Tank> myTank = new ArrayList<Tank>();
	public static List<Cannon> myCannon = new ArrayList<Cannon>();
	public static List<Ground> myRect = new ArrayList<Ground>();
	public static List<Obus> myObus = new ArrayList<Obus>();
	public static List<Obus> myMissiles = new ArrayList<Obus>();
	public static List<Obus> myMines = new ArrayList<Obus>();
	
	//GLOBAL VARIABLES
	public static boolean endOfGame=Tank.endGame();

	//GETTERS DES LISTES
	public static List<Obus> getListObus() {
		return myObus;
	}

	public static List<Tank> getListTank() {
		return myTank;
	}
	public static List<Obus> getListMissiles() {
		return myMissiles;
	}
	//MAIN FUNCTION
	public static void main(String[] args) throws InterruptedException{ //notre fonction principale
		
		while (true) {
			
			initGame();//on initialise la fenêtre du jeu
			generateGround();//on génère le terrain dans des tableaux
			Menu.navigation();//on affiche le menu de navigation
			
			while (!endOfGame) { //TANT QUE LA PARTIE N'EST PAS FINIE
				
//				StdDraw.clear(Constants.BACKGROUND_COLOR);
				StdDraw.picture(Constants.X_MAX/2, Constants.Y_MAX/2, "./src/ciel.png");
				for (int i = 0; i < Menu.numberOfPlayers;i++) {
					runPlayer(i);//actions de chaque joueur (déplacement, Obus, mine)
				}
				endOfGame=Tank.endGame();//on vérifie si il reste des joueurs
				showGround();// (on fait varier le x de chaque rectangle)
				
				controlTanks();//gérer les joueurs (collisions, vie, scores)
				controlObus();//gérer les Obus (déplacement, collisions)
				controlMines();//gérer les Mines (déplacement, collisions)
				controlMissiles();//gérer les Missiles (déplacement, collisions)
				
				StdDraw.show(10);//durée d'affichage de la vue (cadence)
				Thread.sleep(10);//pause pendant l'affichage
			}
			endGame();//quand un joueur est mort (affichage gagnant)
			clearGame();//effacer toutes les données du jeu pour recommencer une partie
		}
	}
	
	protected static void initGame() {
		StdDraw.clear();
		StdDraw.setCanvasSize(Constants.X_VIEW, Constants.Y_VIEW);
		StdDraw.setXscale(0, Constants.X_MAX);
		StdDraw.setYscale(0, Constants.Y_MAX);
	}
	
	protected static void generateGround() {
		Ground.setNumberOfRect(200);
			
		Ground.generateTerrain();
		myRect = Ground.getListGround();
		
	}

	protected static void runPlayer(int pId) {
		if(myTank.get(pId).getLife()>0){
			Tank.controlPlayer(pId);
		}
		//si on selec l'obus de base
		if (!StdDraw.isKeyPressed(TankConstants.PLAYERS_CONTROLS[pId].get("Obus"))) {
			myTank.get(pId).setIsObusd(false);
		}
		
		if (myTank.get(pId).getIsObusd() == false && StdDraw.isKeyPressed(TankConstants.PLAYERS_CONTROLS[pId].get("Obus"))) {
			myTank.get(pId).setIsObusd(true);
					double vyObus =350;
					double vxObus = 120;
					myObus.add(new Obus(myTank.get(pId).getX(),
							myTank.get(pId).getY(), Constants.r, vxObus, vyObus, myTank
							.get(pId).getId(),true));
			
		}
		//si on selec le missile vertical
		if (!StdDraw.isKeyPressed(TankConstants.PLAYERS_CONTROLS[pId].get("missile"))) {
			myTank.get(pId).setIsMissiled(false);
		}
		if (myTank.get(pId).getIsMissiled() == false && StdDraw.isKeyPressed(TankConstants.PLAYERS_CONTROLS[pId].get("missile"))) {
			if(myTank.get(pId).stockMissile>0){
				myTank.get(pId).setIsMissiled(true);
				double vyObus =600;
				double vxObus = 0;
				myMissiles.add(new Obus(myTank.get(pId).getX(),
						myTank.get(pId).getY(), Constants.r, vxObus, vyObus, myTank
						.get(pId).getId(),true));
				myTank.get(pId).stockMissile--;
			}
		}
		else {
			myTank.get(pId).setIsMissiled(false);
		}
		
		//si on selec la mine
		if (!StdDraw.isKeyPressed(TankConstants.PLAYERS_CONTROLS[pId].get("mine"))) {
			myTank.get(pId).setIsMined(false);
		}
		
		if (myTank.get(pId).getIsMined() == false && StdDraw.isKeyPressed(TankConstants.PLAYERS_CONTROLS[pId].get("mine"))) {
			if(myTank.get(pId).stockMine>0){
				myTank.get(pId).setIsMined(true);
				double vxObus = 0;
				double vyObus = 0;
				myMines.add(new Mine(myTank.get(pId).getX(),
						myTank.get(pId).getY(),0,vxObus, vyObus,myTank
						.get(pId).getId(), true));
				myTank.get(pId).stockMine--;
			}
		}
		else {
			myTank.get(pId).setIsMined(false);
		}
	}
	
	protected static void showGround() {
		for (int i = 0; i < myRect.size(); i++) {
			myRect.get(i).show();
			if (i % 2 == 0) {
				myRect.get(i).contactBottom();// contactBottom avec le sol
			} 
//				else {
//				myRect.get(i).contactTop();// contactTop avec le haut
//			}
			if (myRect.get(0).getXGround() > 7000) {
				StdDraw.setPenColor(Color.BLACK);
				StdDraw.setFont(new Font("Dialog", Font.PLAIN, 20));
					StdDraw.text(Constants.X_MAX/2, Constants.Y_MAX/2, "Player vs player");
			}
			
		}
	}
	
	protected static void resetGround() {
		myRect.clear();
		Ground.myRect.clear();
		generateGround();
	}
	
	protected static void controlTanks() {
		for (int i = 0; i < myTank.size(); i++) {
//			(myTank.get(i)).paint(i+1);
			if(myTank.get(i).getLife()>0){
				(myTank.get(i)).border();
			}else{
				(myTank.get(i)).setX(100);
			}
			if(myTank.get(i).getX()<-500){
				(myTank.get(i)).setX(100000000);
			}
			(myTank.get(i)).barre();
			myTank.get(i).controlLife();
			(myTank.get(i)).contactObusTank();
			(myTank.get(i)).contactMineTank();
			(myTank.get(i)).contactMissileTank();
		}
	}

	protected static void controlObus() {
		for (int i = 0; i < myObus.size(); i++) {
			if(myObus.get(i).life)
				(myObus.get(i)).Obus(0.7,-10); //methode Obus(timestep,gravity) 
			else 
				(myObus.get(i)).setXObus(20000);	
		}
	}
	protected static void controlMines() {
		for (int i = 0; i < myMines.size(); i++) {
			if(myMines.get(i).life)
				(myMines.get(i)).Mines(); //methode Mines() 
			else 
				(myMines.get(i)).setXObus(20000);	
		}
	}
	protected static void controlMissiles() {
		for (int i = 0; i < myMissiles.size(); i++) {
			if(myMissiles.get(i).life)
				(myMissiles.get(i)).Missile(0.7,-10.0); //methode Missile() 
			else 
				(myMissiles.get(i)).setXObus(20000);	
		}
	}
	
	protected static void endGame() {    // methode fin du jeu
		int winner=myTank.get(0).id;
		for (int i = 1; i < myTank.size(); i++) {
			if(myTank.get(i).life==0){
				winner=myTank.get(i).id;
			}
			else if (winner ==0){ //correction temporaire cas joueur 2 gagnant
				winner=winner+2;
			}
		}
		
		//affichage du gagnant
		StdDraw.clear();
		StdDraw.clear(Color.WHITE);
		StdDraw.setPenColor(Color.BLACK);
		StdDraw.text(5000, 6000, "Le joueur "+winner+" est vainqueur");
		StdDraw.rectangle(Constants.X_MAX/2, Constants.Y_MAX/2, 6000, 2000);
		StdDraw.show(5000);
	}
	//retour menu accueil
	protected static void clearGame() {
		Menu.navigation=true;
		Menu.home=true;
		Menu.numberOfPlayers=0;
		myObus.clear();
		myTank.clear();
		myRect.clear();
		myMines.clear();
		myMissiles.clear();
		endOfGame = Tank.endGame();
	}

}
