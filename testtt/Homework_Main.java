package testtt;

import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Homework_Main extends JFrame{
	JPanel pg1;
	CardLayout cardLayout;
	public Homework_Main() {
		super("카드레이아웃");
		
		cardLayout = new CardLayout();
		pg1 = new JPanel(cardLayout);
//		pg1.setLayout(cardLayout);
		
		Ex_tttt tttt = new Ex_tttt(cardLayout, pg1);
		Ex_Grade grade = new Ex_Grade();
		EX_Calc calc = new EX_Calc(this);
		Ex_Country country = new Ex_Country(this);
		
		pg1.add("tttt", tttt);
		pg1.add("grade", grade);
		pg1.add("calc", calc);
		pg1.add("country", country);
		
		
		add(pg1);
		
		
		
		setLocationRelativeTo(null);
		setSize(500,300);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		
	}
	public static void main(String[] args) {
		new Homework_Main();
		System.out.println("dd");
	}
}
