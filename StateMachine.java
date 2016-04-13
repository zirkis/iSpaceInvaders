import java.awt.Canvas;
import java.awt.Graphics2D;
import java.util.ArrayList;


public class StateMachine {
  
  private ArrayList<StateMachineType> states = new ArrayList<StateMachineType>();
  private Canvas canvas;
  private byte selectState = 0;
  
  public StateMachine(Canvas canvas){
    StateMachineType game = new GameScreen();
    StateMachineType menu = new Menu();
    states.add(game);
    states.add(menu);
    this.canvas = canvas;
  }
  
  public void draw(Graphics2D g){
    states.get(selectState).draw(g);
  }
  
  public void update(double delta){
    states.get(selectState).update(delta);
  }
  
  public void setState(byte i){
    for(int r=0; r<canvas.getKeyListeners().length; r++)
      canvas.removeKeyListener(canvas.getKeyListeners()[r]);
    selectState = i;
    states.get(selectState).init(canvas);
  }
  
  public byte getStates(){
    return selectState;
  }

  
}