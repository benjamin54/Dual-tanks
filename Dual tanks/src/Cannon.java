import java.awt.Color;

import java.util.ArrayList;
import java.util.List;


public class Cannon {
	
	//GLOBAL LIST
	public static List<Tank> myTank = new ArrayList<Tank>();
	public static List<Obus> myObus = new ArrayList<Obus>();
	public static List<Ground> myRectangle = new ArrayList<Ground>();
	public static List<Obus> myMines =new ArrayList<Obus>();
	public static List<Cannon> myCannon =new ArrayList<Cannon>();
	
	//CLASS PROPERTIES
	int x, y, itterator;
	public static int dx;
	double theta, thetaaccum;
	double thetadif;
	
	public Cannon(int x, int y, double theta, int itterator){
		this.x=x;
		this.y=y;
		this.itterator = itterator;
		this.theta = theta;
	}
	
	//DRAW Cannons
		public void cannons() { // affiche les cannons
			for (int i = 0; i < myCannon.size(); i++) {
				StdDraw.picture(myTank.get(i).getX(), myTank.get(i).getY()+200, "./src/cannon.png", 600, 600);
			}
			
		}
}
