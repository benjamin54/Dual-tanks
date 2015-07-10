import java.util.ArrayList;
import java.util.List;

class MenuConstants {
	
	//FRAME START BUTTON
	protected static final double X_START_BUTTON = 130*(10000/900);
	protected static final double Y_START_BUTTON = 157*(10000/500);
	protected static final double WIDTH_START_BUTTON = 193*(10000/900);
	protected static final double HEIGHT_START_BUTTON = 71*(10000/500);
		
	//FRAME INSTRUCTION BUTTON
	protected static final double X_HELP_BUTTON = 563*(10000/900);
	protected static final double Y_HELP_BUTTON = 157*(10000/500);
	protected static final double WIDTH_HELP_BUTTON = 192*(10000/900);
	protected static final double HEIGHT_HELP_BUTTON = 71*(10000/500);
		
	//FRAME BACK BUTTON
	protected static final double X_BACK_BUTTON = 30*(10000/900);
	protected static final double Y_BACK_BUTTON = 415*(10000/500);
	protected static final double WIDTH_BACK_BUTTON = 80*(10000/900);
	protected static final double HEIGHT_BACK_BUTTON = 55*(10000/500);
		
	//FRAME ONE PLAYER
//	
//	protected static final double X_ONE_PLAYER = 35*(10000/900);
//	protected static final double Y_ONE_PLAYER = 265*(10000/500);
//	protected static final double WIDTH_ONE_PLAYER = 260*(10000/900);
//	protected static final double HEIGHT_ONE_PLAYER = 90*(10000/500);
//		
	//FRAME TWO PLAYERS
	protected static final double X_TWO_PLAYERS = 320*(10000/900);
	protected static final double Y_TWO_PLAYERS = 265*(10000/500);
	protected static final double WIDTH_TWO_PLAYERS = 260*(10000/900);
	protected static final double HEIGHT_TWO_PLAYERS = 90*(10000/500);

}

public class Menu {
	
	//LOCAL VARIABLES
	public static boolean players=false;
	public static boolean instructions=false;
	public static boolean home=true;
	public static boolean navigation=true;
	
	//GLOBAL VARIABLES
	public static int numberOfPlayers;
	public static List<Tank> myTank = new ArrayList<Tank>();
	
	public static void navigation() {
		
		myTank = Main.getListTank();

		while(navigation) //tant qu'on est dans la navigation du menu
		{
			StdDraw.show();
			while(home) //tant qu'on est sur la home du menu
			{
				StdDraw.picture(Constants.X_MAX/2, Constants.Y_MAX/2, "./src/menu_template.png");        
				if(MenuConstants.X_START_BUTTON < StdDraw.mouseX() && StdDraw.mouseX() < MenuConstants.X_START_BUTTON+MenuConstants.WIDTH_START_BUTTON && MenuConstants.Y_START_BUTTON < StdDraw.mouseY() && StdDraw.mouseY() < MenuConstants.Y_START_BUTTON+MenuConstants.HEIGHT_START_BUTTON)
				{
					//si on clique sur le bouton start
					if(StdDraw.mousePressed()){
						players=true;
						home=false;
						instructions=false;
					}
				}
				else if(MenuConstants.X_HELP_BUTTON < StdDraw.mouseX() && StdDraw.mouseX() < MenuConstants.X_HELP_BUTTON+MenuConstants.WIDTH_HELP_BUTTON && MenuConstants.Y_HELP_BUTTON < StdDraw.mouseY() && StdDraw.mouseY() < MenuConstants.Y_HELP_BUTTON+MenuConstants.HEIGHT_HELP_BUTTON)
				{
					//si on clique sur le bouton instructions
					if(StdDraw.mousePressed()){
						instructions=true;
						players=false;
						home=false;
					}
				}
			}
			
			while(instructions) //tant qu'on est sur la vue des instructions
			{
				StdDraw.picture(Constants.X_MAX/2, Constants.Y_MAX/2, "./src/menu_instructions1.png");        
				if(MenuConstants.X_BACK_BUTTON < StdDraw.mouseX() && StdDraw.mouseX() < MenuConstants.X_BACK_BUTTON+MenuConstants.WIDTH_BACK_BUTTON && MenuConstants.Y_BACK_BUTTON < StdDraw.mouseY() && StdDraw.mouseY() < MenuConstants.Y_BACK_BUTTON+MenuConstants.HEIGHT_BACK_BUTTON)
				{
					//si on clique sur le bouton retour
					if(StdDraw.mousePressed()){
						home=true;
						instructions=false;
					}
				}
			}
			
			while(players) //tant qu'on est sur la vue de selection du nombre de joueurs
			{
				StdDraw.picture(Constants.X_MAX/2, Constants.Y_MAX/2, "./src/menu_start.png");
//				if(MenuConstants.X_ONE_PLAYER < StdDraw.mouseX() && StdDraw.mouseX() < MenuConstants.X_ONE_PLAYER+MenuConstants.WIDTH_ONE_PLAYER && MenuConstants.Y_ONE_PLAYER < StdDraw.mouseY() && StdDraw.mouseY() < MenuConstants.Y_ONE_PLAYER+MenuConstants.HEIGHT_ONE_PLAYER)
//				{
//					//si on clique sur le bouton 1 joueur
//					if(StdDraw.mousePressed()){
//						numberOfPlayers=1;
//						for(int i=0; i<numberOfPlayers; i++) { // i sera l'identifiant du vaisseau
//							myTank.add(new Tank(TankConstants.X_Tank, TankConstants.Y_Tank+i*TankConstants.DISTANCE_Tank, i));
//						}
//						players=false;
//						navigation=false;
//					}
//				}
				if(MenuConstants.X_TWO_PLAYERS < StdDraw.mouseX() && StdDraw.mouseX() < MenuConstants.X_TWO_PLAYERS+MenuConstants.WIDTH_TWO_PLAYERS && MenuConstants.Y_TWO_PLAYERS < StdDraw.mouseY() && StdDraw.mouseY() < MenuConstants.Y_TWO_PLAYERS+MenuConstants.HEIGHT_TWO_PLAYERS)
				{
					//si on clique sur le bouton 2 joueurs
					if(StdDraw.mousePressed()){
						numberOfPlayers=2;
						for(int i=0; i<numberOfPlayers; i++) { // i sera l'identifiant du vaisseau
							myTank.add(new Tank(TankConstants.X_Tank+i*TankConstants.DISTANCE_Tank, TankConstants.Y_Tank, i));
						}
						players=false;
						navigation=false;
					}
				}
				else if(MenuConstants.X_HELP_BUTTON < StdDraw.mouseX() && StdDraw.mouseX() < MenuConstants.X_HELP_BUTTON+MenuConstants.WIDTH_HELP_BUTTON && MenuConstants.Y_HELP_BUTTON < StdDraw.mouseY() && StdDraw.mouseY() < MenuConstants.Y_HELP_BUTTON+MenuConstants.HEIGHT_HELP_BUTTON)
				{
					//si on clique sur le bouton instructions
					if(StdDraw.mousePressed()){
						home=false;
						players=false;
						instructions=true;
					}
				}
			}
		}
	}
}