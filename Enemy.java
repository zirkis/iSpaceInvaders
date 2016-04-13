import java.awt.Graphics2D;
import java.util.ArrayList;
import java.awt.Rectangle;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class Enemy implements EnemyType {
  
  public double speed = 1.0d;
  
  private Rectangle rect;
  private SpriteAnimation enemySprite;
  
  
  
  public Enemy(double xPos, double yPos, int rows, int columns){
    enemySprite = new SpriteAnimation(xPos, yPos, rows, columns, 300, "/Invaders.png");
    enemySprite.setWidth(25);
    enemySprite.setHeight(25);
    enemySprite.setLimit(2);

    
    this.setRect(new Rectangle((int) enemySprite.getxPos(), (int) enemySprite.getyPos(), enemySprite.getWidth(), enemySprite.getHeight()));
    enemySprite.setLoop(true);

  }
  
  public void draw(Graphics2D g){
    enemySprite.draw(g);
  }
  
  public void update(double delta, Player player){
    enemySprite.update(delta);
    
    enemySprite.setxPos(enemySprite.getxPos() - (delta * speed));
    this.getRect().x = (int) enemySprite.getxPos();
    
    
  }
  
  public void changeDirection(double delta){
    speed *= -1.15d;
    enemySprite.setxPos(enemySprite.getxPos() - (delta * speed));
    this.getRect().x = (int) enemySprite.getxPos();
    
    enemySprite.setyPos(enemySprite.getyPos() + (delta * 15));
    this.getRect().y = (int) enemySprite.getyPos();
  }
  
  public boolean deathScene(){
    if(!enemySprite.isPlay())
      return false;
    if(enemySprite.isSpriteAnimDestroyed()){
      return true;
    }
    
    return false;
      
  }
  
  public boolean collide(int i, Player player, ArrayList<EnemyType> enemys){
    if(enemySprite.isPlay()){
      if(enemys.get(i).deathScene()){
        enemys.remove(i);
      }
      return false;
    }
    for(int w = 0; w < player.playerWeapons.weapons.size(); w++){
      if(enemys != null && player.playerWeapons.weapons.get(w).collisionRect(((Enemy) enemys.get(i)).getRect())){
        enemySprite.resetLimit();
        enemySprite.setAnimationSpeed(120);
        enemySprite.PlayerAnimation(true,true);
        GameScreen.SCORE += 15;
        return true;
      }
    }
      return false;      
    }
    
  public boolean isOutofBounds(){
    if (rect.x > 0 && rect.x < Display.WIDTH - rect.width)
      return false;
   return true; 
  }
  
  public Rectangle getRect(){
   return rect; 
  }
  
  public void setRect(Rectangle rect){
    this.rect = rect;
  }
  

}