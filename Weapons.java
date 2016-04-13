import java.awt.Graphics2D;
import java.util.ArrayList;

public class Weapons {
  
  private Timer timer;
  public ArrayList<WeaponsType> weapons = new ArrayList<WeaponsType>();
  
  public Weapons(){
    timer = new Timer();
  }
  
  public void draw(Graphics2D g){
    for(int i=0; i < weapons.size(); i++){
      weapons.get(i).draw(g);
    }
  }
  
  public void update(double delta){
    for(int i=0; i < weapons.size(); i++){
      weapons.get(i).update(delta);
      if(weapons.get(i).destroy())
        weapons.remove(i);
    }
  }
  
  public void shootBullet(double xPos, double yPos, int width, int height){
    if(timer.timerEvent(250) && weapons.size() < 4)
      weapons.add(new Gunner(xPos + 35, yPos + 21, width, height + 7));
  }
}
    