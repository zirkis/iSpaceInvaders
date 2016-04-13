import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;

public class Player implements KeyListener{
  
  private final double speed = 5.0d;
  
  private BufferedImage pSprite;
  private Rectangle rect;
  private double xPos, yPos;
  private int width, height;
    
  
  private boolean left = false, right = false, shoot = false;
  
  public Weapons playerWeapons;
  
  public Player(double xPos, double yPos, int width, int height){
    this.xPos = xPos;
    this.yPos = yPos;
    this.width = width;
    this.height = height;
    
    rect = new Rectangle((int) xPos, (int) yPos, width, height);
    
    try{
      URL url = this.getClass().getResource("/player.png");
      pSprite = ImageIO.read(url);
    }catch(IOException e){};
    
    playerWeapons = new Weapons();
  }
  
  public void draw (Graphics2D g){
    g.drawImage(pSprite,(int) xPos,(int) yPos, width, height, null);
    playerWeapons.draw(g);
  }
  
  public void update (double delta){
    if (right && !left && xPos < Display.WIDTH-width){
      xPos += speed * delta;
      rect.x = (int) xPos;
    }if(!right && left && xPos >10){
      xPos -= speed * delta;
      rect.x = (int) xPos;
    }
    playerWeapons.update(delta);
    
    if(shoot){
      playerWeapons.shootBullet(xPos,yPos,5,5);
    }
  }
  
  public void keyPressed(KeyEvent e){
    int key = e.getKeyCode();
    if(key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT){
      right = true;
    }else if (key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT){
      left = true;
    }
    if (key == KeyEvent.VK_SPACE){
      shoot = true;
    }
    if (key == KeyEvent.VK_Q){
       System.exit(0);
    }
    if (key == KeyEvent.VK_N){
       ;
    }
  }
  
  public void keyReleased(KeyEvent e){
    int key = e.getKeyCode();
    if(key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT){
      right = false;
    }else if (key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT){
      left = false;
    }
    if (key == KeyEvent.VK_SPACE){
      shoot = false;
    }
  }
  
  public void keyTyped(KeyEvent e){
    
  }
  
  public Rectangle getRect(){
    return rect;
  }
}
