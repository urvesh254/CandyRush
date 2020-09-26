import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.concurrent.*;

public class Ball extends JLabel implements Runnable
{
	private Thread t;
	private CandyRush cr;
	private Random rand;
	private static int diff=10;
	private static int scoreLen=10;
	static ExecutorService service = Executors.newFixedThreadPool(1);

	public Ball(CandyRush cr){
		this.cr=cr;
		
		t = new Thread(this);
		rand = new Random();
		setIcon(new ImageIcon("images\\"+rand.nextInt(10)+".png"));
		t.start();
	}

	public void run(){
		int x=rand.nextInt(cr.getBounds().width-100)+20;
		int y=35;
		int boulX,boulY;
		Rectangle r=cr.getBounds();
		boolean f=true,lifeF=true;
		try{
			while(cr.life>0 && getY()<=r.height-20){
				setBounds(x,y,48,48);
				boulX=cr.boul.getX();
				boulY=cr.boul.getY();
				if(y+48>=boulY && y+48<=boulY+10 && x+12>boulX && boulX+200>x+36){
					cr.score++;
					if(scoreLen==cr.score){
						cr.labelScore.setBounds(cr.labelScore.getX()-20,cr.labelScore.getY(),150,30);
						scoreLen*=10;
					}
					cr.labelScore.setText(cr.score+"");
					lifeF=false;
					break;
				}
				if(f && y+48>r.height-300){
					f=false;
					cr.xCoordinate.addFirst(x+24);
					service.execute(new Automate(cr));
				}
				Thread.sleep(100);
				y+=diff;
			}
			if(lifeF){
				cr.life--;
				cr.remove(cr.labelLife[cr.life]);
			}
			cr.remove(this);
			cr.validate();
			cr.repaint();
		}catch(Exception ex){
			// ex.printStackTrace();
			JOptionPane.showMessageDialog(null,"Something Wrong in Ball Class.");
		}
	}
}