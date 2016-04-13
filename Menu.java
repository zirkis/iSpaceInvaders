import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.Canvas;

public class Menu implements StateMachineType, KeyListener {
  
  private BufferedImage menuSprite;
  
  public Menu (){
    try{
      URL url = this.getClass().getResource("/Menu.png");
      menuSprite = ImageIO.read(url);
    }catch(IOException e){};
  }
  
  public void keyPressed(KeyEvent e){
    int key = e.getKeyCode();
    if(key == KeyEvent.VK_Q){
      System.exit(0);
    }
    if (key == KeyEvent.VK_SPACE){
      Display.state.setState((byte) 0);
    }
  }
  
  public void keyReleased(KeyEvent e){

  }
  
  public void keyTyped(KeyEvent e){
    
  }
  
  
  
  public void update(double delta){
    
  }
  
  public void draw(Graphics2D g){
    g.drawImage(menuSprite,(int) 5,(int) 5, 800, 600, null);

    
  }
  
  public void init(Canvas canvas){
    canvas.addKeyListener(this);
  }
}
  