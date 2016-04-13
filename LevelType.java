import java.awt.Graphics2D;

public interface LevelType {
  
  void draw(Graphics2D g);
  void update(double delta);
  void hasDirectionChanged(double delta);
  void changeDirectionAllEnemys(double delta);
  void reset();
  
}