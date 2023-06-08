package testtt;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;import day20.com.ict.edu3.Homework0524_Main;

public class EX_Calc extends JPanel implements ActionListener,ItemListener{
	JTextField jtf1, jtf2;
	JRadioButton jrb1, jrb2, jrb3, jrb4;
	JButton jb1, jb2, jb3;
	ButtonGroup bg;
	JTextArea jta;
	String cal = "";
	int result = 0;
	Homework_Main main , asdf;
	public EX_Calc(Homework_Main main) {
		
		this.main = main;
		
		JPanel jp = new JPanel();
		
		jtf1 = new JTextField(5);
		jtf2 = new JTextField(5);
		
		jrb1 = new JRadioButton("+");
		jrb2 = new JRadioButton("-");
		jrb3 = new JRadioButton("*");
		jrb4 = new JRadioButton("/");
		
		bg = new ButtonGroup();
		bg.add(jrb1);
		bg.add(jrb2);
		bg.add(jrb3);
		bg.add(jrb4);
		jp.add(new JLabel("수1:"));
		jp.add(jtf1);
		jp.add(new JLabel("수2:"));
		jp.add(jtf2);
		jp.add(new JLabel("연산자:"));
		jp.add(jrb1);
		jp.add(jrb2);
		jp.add(jrb3);
		jp.add(jrb4);
		add(jp, BorderLayout.NORTH);
		
		jta = new JTextArea(5,20);
		jta.setLineWrap(true);
		JScrollPane jsp = new JScrollPane(jta,
				ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		// 결과출력에는 편집이 불가
		jta.setEditable(false);
		add(jsp, BorderLayout.CENTER);
		
		JPanel jp2 = new JPanel();
		jb1 = new JButton("계산");
		jb2 = new JButton("뒤로가기");
		jb3 = new JButton("초기화");
		
		jp2.add(jb1);
		jp2.add(jb2);
		jp2.add(jb3);
		add(jp2, BorderLayout.SOUTH);
		
	
		
		jrb1.addItemListener(this);
		jrb2.addItemListener(this);
		jrb3.addItemListener(this);
		jrb4.addItemListener(this);
		
		jb1.addActionListener(this);
		jb2.addActionListener(this);
		jb3.addActionListener(this);
		
	}
	@Override //라디오
	public void itemStateChanged(ItemEvent e) {
		JRadioButton obj = (JRadioButton)e.getSource();
		if(e.getStateChange() == e.SELECTED) {
			cal = obj.getText();
		}
		
	}
	@Override//버튼
	public void actionPerformed(ActionEvent e) {
		JButton obj = (JButton)e.getSource();
		if(obj == jb1) {
			if(cal.equals("+")) {
				result = Integer.parseInt(jtf1.getText()) + Integer.parseInt(jtf2.getText());
				jta.append(String.valueOf(result + "\n"));
			}else if(cal.equals("-")) {
				result = Integer.parseInt(jtf1.getText()) - Integer.parseInt(jtf2.getText());
			}else if(cal.equals("*")) {
				result = Integer.parseInt(jtf1.getText()) * Integer.parseInt(jtf2.getText());
			}else if(cal.equals("/")) {
				result = Integer.parseInt(jtf1.getText()) / Integer.parseInt(jtf2.getText());
			}
			
		}else if(obj == jb2) {
			jb2.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					main.cardLayout.show(main.pg1,"tttt" );
					
				}
			});
		}else if(obj == jb3) {
			bg.clearSelection();
			jtf1.setText("");
			jtf2.setText("");
			jta.setText("");
		}
	}
//	public static void main(String[] args) {
//		new EX_Calc() ;
//	}
}
