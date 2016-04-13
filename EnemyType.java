import java.awt.Graphics2D;
import java.util.ArrayList;
import java.awt.Rectangle;

public interface EnemyType {
   
  void draw(Graphics2D g);
  void update(double delta, Player player);
  void changeDirection(double delta);
  
  Rectangle getRect();
  
  boolean deathScene();
  boolean collide(int i, Player player, ArrayList<EnemyType> enemys);
  boolean isOutofBounds();
  
}