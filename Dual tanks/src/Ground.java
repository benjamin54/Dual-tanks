
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

class GroundConstants {
	//SIZE RECT
	protected static final double X_RECT = 60;
	protected static final double Y_RECT = 400;
	protected static final double WIDTH_RECT = 50;
		
	//INIT VALUE
	protected static final double MARGIN_GROUND =50;
		
	//RECT BACKGROUND
	protected static final Color DARK_GREEN_COLOR = new Color(0,100,0);
}

public class Ground {
	//GROUND PROPERTIES
	public static int numberOfRect;//nombre de rectangles
	
	//GLOBAL LIST
	public static List<Tank> myTank =new ArrayList<Tank>();
	public static List<Ground> myRect =new ArrayList<Ground>();
	public static List<Obus> myObus =new ArrayList<Obus>();
	public static List<Cannon> myCannon =new ArrayList<Cannon>();
	public static List<Obus> myMissiles = new ArrayList<Obus>();
	
	//CLASS PROPERTIES
	protected double xGround;
	protected double yGround;
	protected double widthGround;
	protected double heightGround;

	//CLASS INITIALIZER
	Ground(double xGround, double yGround, double widthGround, double heightGround) {
		this.xGround = xGround;
		this.yGround = yGround;
		this.widthGround = widthGround;
		this.heightGround = heightGround;
	}
	
	//GETTERS
	public static List<Ground> getListGround(){
		return myRect;
	}
	
	public double getXGround(){
		return xGround;
	}
	
	//SETTERS
	
	public static void setNumberOfRect(int number) {
		numberOfRect = number;
	}
	
	//FUNCTIONS
	public void show(){
		StdDraw.setPenColor(GroundConstants.DARK_GREEN_COLOR);
		StdDraw.filledRectangle(xGround, yGround, widthGround,heightGround);
	}
	
	protected static double[] randomTab(int values) {
		double[] tab = new double[numberOfRect];
		tab[0]=Math.random(); 
		int k=0;
		for(int i=1;i!=values;i++){
			double nb=Math.random();
			if(tab[i-1]<0.1){
				tab[i]=tab[i-1]+0.11*nb;
				k=0;
			}
			if(tab[i-1]>0.9){
				tab[i]=tab[i-1]-0.11*nb;
				k=1;
			}
			if(k==0){
				tab[i]=tab[i-1]+0.11*nb;
			}else if(k==1){
				tab[i]=tab[i-1]-0.11*nb;
			}
		}
		return tab;
	}
	
	public static void generateTerrain(){
		double tab1[]=randomTab(numberOfRect);
		myRect=Ground.getListGround();
		for(int i=0;i!=numberOfRect;i++){
				myRect.add(new Ground(0+i*GroundConstants.X_RECT, GroundConstants.Y_RECT, GroundConstants.WIDTH_RECT, 1500+-(tab1[i]*1000)));  //poser le terrain
			
		}
	}
	
	public void contactBottom()
	{
		myTank=Main.getListTank();	
		myObus=Main.getListObus();
		myMissiles=Main.getListMissiles();
		double middle = yGround+320+heightGround+GroundConstants.MARGIN_GROUND;
		for(int i=0;i < myObus.size();i++){
			StdDraw.picture(myObus.get(i).getXObus(), myObus.get(i).getYObus(), "./src/obus_shr.png", 250, 200); //dessine l'obus à chaque instant
			if(xGround+GroundConstants.MARGIN_GROUND>myObus.get(i).getXObus() && xGround-GroundConstants.MARGIN_GROUND<myObus.get(i).getXObus() && middle>myObus.get(i).getYObus()+200){  //empêche l'obus d'exploser tout de suite
				StdDraw.picture(myObus.get(i).getXObus(), myObus.get(i).getYObus(), "./src/boom.png", 800, 1000); //explosion quand l'obus touche le sol
				Cratere cratere1=new Cratere(myObus.get(i).getXObus(), myObus.get(i).getYObus(),200);
				cratere1.cratere();
				myObus.get(i).setXObus(100000);
			}
		}
		for(int i=0;i < myMissiles.size();i++){
			if(xGround+GroundConstants.MARGIN_GROUND>myMissiles.get(i).getXObus() && xGround-GroundConstants.MARGIN_GROUND<myMissiles.get(i).getXObus() && middle>myMissiles.get(i).getYObus()+200){
				StdDraw.picture(myMissiles.get(i).getXObus(), myMissiles.get(i).getYObus(), "./src/boom.png", 1000, 1200);
				myMissiles.get(i).setXObus(100000);
			}
		}
		for(int k=0;k<myTank.size();k++){
			if(k==0){
				StdDraw.picture(myTank.get(k).getX(), myTank.get(k).getY(), "./src/tank11.png", 700, 700);	//photo tank
				StdDraw.picture(myTank.get(k).getX()+225, myTank.get(k).getY()+80, "./src/cannon.png", 300, 100);  //photo cannon
				StdDraw.picture(myTank.get(k).getX()+800, myTank.get(k).getY()+1400, "./src/target_point.png", 150, 150);  //photo cible
			}
			else if (k==1){
				StdDraw.picture(myTank.get(k).getX(), myTank.get(k).getY(), "./src/tank22.png", 700, 700);
				StdDraw.picture(myTank.get(k).getX()-200, myTank.get(k).getY()+80, "./src/cannon2.png", 300, 100);
				StdDraw.picture(myTank.get(k).getX()-800, myTank.get(k).getY()+1400, "./src/target_point.png", 150, 150);  //photo cible
			}
			if(xGround+GroundConstants.MARGIN_GROUND>myTank.get(k).getX() && xGround-GroundConstants.MARGIN_GROUND<myTank.get(k).getX() && middle>myTank.get(k).getY()){
				myTank.get(k).setY(-60);
			}
			else if(xGround+GroundConstants.MARGIN_GROUND>myTank.get(k).getX() && xGround-GroundConstants.MARGIN_GROUND<myTank.get(k).getX() && middle<myTank.get(k).getY()){
				myTank.get(k).setY(60);
			}
		}
	}
	
//	public void contactTop()
//	{
//		myTank=Main.getListTank();
//		myObus=Main.getListObus();
//		double middle = yGround-heightGround-GroundConstants.MARGIN_GROUND;
//		for(int i=0;i<myObus.size();i++){
//			if(xGround+GroundConstants.MARGIN_GROUND>myObus.get(i).getXObus() && xGround-GroundConstants.MARGIN_GROUND<myObus.get(i).getXObus() && middle<myObus.get(i).getYObus()){
//				myObus.get(i).setXObus(100000);
//			}
//		}
//
//		for(int k=0;k<myTank.size();k++){
//			if(xGround+GroundConstants.MARGIN_GROUND>myTank.get(k).getX() && xGround-GroundConstants.MARGIN_GROUND<myTank.get(k).getX() && middle<myTank.get(k).getY()){
//				myTank.get(k).setY(300);
//
//			}
//		}
//	}
}
