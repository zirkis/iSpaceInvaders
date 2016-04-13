import java.awt.Graphics2D;
import java.util.ArrayList;
import java.awt.event.KeyEvent;
import java.awt.Color;
import java.awt.event.KeyListener;

public class Level implements LevelType, KeyListener {
  
  private Player player;
  private ArrayList<EnemyType> enemies = new ArrayList<EnemyType>();
  private boolean lose = false ;
  private boolean win = false ;

  
  public Level(Player player){
    this.player = player;
    for(int y = 0; y < 5; y++){
      for(int x = 0; x < 10; x++){
        EnemyType e = new Enemy(150 + (x*40), 25 + (y*40), 1, 3);
        enemies.add(e);
      }
    }
  }
  
  public void draw(Graphics2D g){
    if(enemies == null)
      enemies = new ArrayList<EnemyType>();
    
    for(int i =0; i < enemies.size(); i++){
      enemies.get(i).draw(g);
    }
    
    if (enemies.isEmpty()){
      g.setColor(Color.yellow);
      g.drawString("YOU WON", 380, 300);
      g.drawString("Press N to continue", 360, 350);
      win = true;
    }
    
    if(lose){
      g.setColor(Color.red);
      g.drawString("YOU LOSE", 380, 300);
      g.drawString("Score: " + GameScreen.getSCORE(), 375, 325);
      g.drawString("Press N to restart", 360, 350);
      
    }
 
  }
  
  public void update(double delta){
    if(enemies == null)
      return;
    
    for(int i=0; i < enemies.size();i++)
      enemies.get(i).update(delta, player);
    for(int i=0; i < enemies.size();i++)
      enemies.get(i).collide(i, player, enemies);
    for(int i=0; i < enemies.size();i++){
      if(enemies.get(i).getRect().intersects(player.getRect()))
           lose = true;
    }
    hasDirectionChanged(delta);

       
  }

  
  public void hasDirectionChanged(double delta){
    if(enemies == null)
      return;
    
     for(int i=0; i < enemies.size();i++){
       if(enemies.get(i).isOutofBounds()){
         changeDirectionAllEnemys(delta);
       }
     }
  }
  
  public void changeDirectionAllEnemys(double delta){
    for(int i =0; i < enemies.size(); i++){
      enemies.get(i).changeDirection(delta);
    }
  }

  

  
  public void reset(){
    enemies = new ArrayList<EnemyType>();
    for(int y = 0; y < 5; y++){
      for(int x = 0; x < 10; x++){
        EnemyType e = new Enemy(150 + (x*40), 25 + (y*40), 1, 3);
        enemies.add(e);
      }
    }
    
  }
  
  public void keyPressed(KeyEvent e){
    int key = e.getKeyCode();
    if(key == KeyEvent.VK_N){
      if(lose){
        lose = false;
        GameScreen.resetSCORE();
        reset();
      }
      else if(win){
        win = false;
        reset();
      }
      else{
        GameScreen.resetSCORE();
        reset();
      }      
    }
  }
  
  public void keyReleased(KeyEvent e){

  }
  
  public void keyTyped(KeyEvent e){
    
  }
  

  
}