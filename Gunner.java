import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Rectangle;

public class Gunner extends WeaponsType {
  
  private Rectangle bullet;
  private final double speed = 2.5d;
  
  public Gunner (double xPos, double yPos, int width, int height){
    this.setxPos(xPos);
    this.setyPos(yPos);
    this.setWidth(width);
    this.setHeight(height);
    
    this.bullet = new Rectangle((int) getxPos(), (int) getyPos(), getWidth(), getHeight());
    
  }
  
  public void draw(Graphics2D g){
    if(bullet == null)
      return;
    
    g.setColor(Color.BLUE);
    g.fill(bullet);
  }
  
  public void update(double delta){
    if(bullet == null)
      return;
    
    this.setyPos(getyPos() - (delta * speed));
    bullet.y = (int) this.getyPos();
    isOutofBounds();
  }
  
  public boolean collisionRect(Rectangle rect) {
    if(this.bullet == null)
      return false;
    
    if(bullet.intersects(rect)){
      this.bullet = null;
      return true;
    }
    
    return false;
    
  }
  
  public boolean collisionPoly(Polygon poly){
    return false;
  }
  
  public boolean destroy() {
    if(bullet == null)
      return true;
    
    return false;
  }
  
  protected void isOutofBounds() {
    if(this.bullet == null)
      return;
    
    if(bullet.y < 0 || bullet.y > Display.HEIGHT || bullet.x < 0 || bullet.x > Display.WIDTH){
      bullet = null;
    }
  }
  
}