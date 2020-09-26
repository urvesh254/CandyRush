import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class CandyRush extends JPanel implements Runnable
{
	private Thread t;
	private Ball ball;
	public static int score=0;
	public static int life=5;
	private Main main;
	JLabel boul;
	Rectangle r;
	JLabel labelScore,labelLife[];
	LinkedList<Integer> xCoordinate;

	// Initialize all variable.
	public CandyRush(Main main){
		this.main=main;

		//Storing coordinates.
		xCoordinate = new LinkedList<>();

		setLayout(null); // layout
		// addKeyListener(this);
		setBackground(Color.white);
		setSize(1200, 700); 		//frame size
		r=getBounds();

		//Boul label
		boul = new JLabel(new ImageIcon("images\\Boul.png"));
		boul.setBounds(5,getBounds().height-116,200,80);
		add(boul);

		//life labels
		labelLife = new JLabel[5];
		for(int i=0;i<5;i++){
			labelLife[i] = new JLabel(new ImageIcon("images\\heart.png"));
			labelLife[i].setBounds(i*40,2,30,30);
			add(labelLife[i]);
		}

		//Score Label
		labelScore = new JLabel("7");
		labelScore.setBounds(getBounds().width-35,2,150,30);
		labelScore.setFont(new Font("Arial",Font.BOLD,32));
		add(labelScore);

		//Balls Thread
		t = new Thread(this,"Ball");
	}

	// Ball dropping start from main.
	public void start(){
		t.start();
	}

	// Ball dropping
	public void run(){
		try{
			while(life>0){
				ball = new Ball(this);
				add(ball);
				Thread.sleep(3000);
			}
		}catch(Exception ex){
			ex.printStackTrace();
			JOptionPane.showMessageDialog(null,"Something Wrong in CandyRush Class.");
		}
		Ball.service.shutdownNow();
		JOptionPane.showMessageDialog(null,"Your Score : "+score);
		main.t.start();
	}
}