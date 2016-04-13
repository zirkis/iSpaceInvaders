import java.awt.Canvas;
import java.awt.Graphics2D;
import java.awt.Color;



public class GameScreen implements StateMachineType{
  
  public Player player;
  private Level level;
  
  public static int SCORE = 0;
  
  public GameScreen(){
    player = new Player(400,500,75,75);
    level = new Level(player);
  }
  
  public void update(double delta){
    player.update(delta);
    level.update(delta);
  }
  
  public void draw(Graphics2D g){
    g.setColor(Color.white);
    g.drawString("Score: " + SCORE, 5, 15);
    
    player.draw(g);
    level.draw(g);
  }
  
  public void init(Canvas canvas){
    canvas.addKeyListener(player);
    canvas.addKeyListener(level);
  }
  
  public static int getSCORE(){
    return SCORE;
  }
  
  public static void resetSCORE(){
    SCORE = 0;
  }
  

}