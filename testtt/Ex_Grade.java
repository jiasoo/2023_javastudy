package testtt;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

public class Ex_Grade extends JPanel implements ActionListener {
	JTextField name, kor, eng, math;
	JButton jb1, jb2, jb3;
	JTextArea jta;
	Homework_Main main;
	public Ex_Grade() {
		
		JPanel jp1 = new JPanel();
		name = new JTextField(10);
		jp1.add(new JLabel("이름"));
		jp1.add(name);

		JPanel jp2 = new JPanel();
		kor = new JTextField(10);
		eng = new JTextField(10);
		math = new JTextField(10);
		jp2.add(new JLabel("국 어"));
		jp2.add(kor);
		jp2.add(new JLabel("영 어"));
		jp2.add(eng);
		jp2.add(new JLabel("수 학"));
		jp2.add(math);

		jta = new JTextArea(5,20);
		jta.setLineWrap(true);
		jta.setEditable(false);
		JScrollPane jsp = new JScrollPane(jta, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

		JPanel jp3 = new JPanel();
		jb1 = new JButton("계 산");
		jb2 = new JButton("종 료");
		jb3 = new JButton("초 기 화");
		jp3.add(jb1);
		jp3.add(jb2);
		jp3.add(jb3);

		JPanel jp4 = new JPanel();
		jp4.setLayout(new BorderLayout());
		jp4.add(jp2, BorderLayout.NORTH);
		jp4.add(jsp, BorderLayout.CENTER);
		jp4.add(jp3, BorderLayout.SOUTH);

		add(jp1, BorderLayout.NORTH);
		add(jp4, BorderLayout.CENTER);

		

		jb1.addActionListener(this);
		jb2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JButton obj = (JButton)e.getSource();
				if(obj == jb2) {
					System.exit(0);
				}
				
			}
		});
		jb3.addActionListener(this);
	}

	public Ex_Grade(Homework_Main homework_Main) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		int sum = Integer.parseInt(kor.getText())+Integer.parseInt(eng.getText())+Integer.parseInt(math.getText());
		double avg = (int)(sum/3.0*10)/10.0;
		String hak = "";
		if(avg>=90) {
			hak = "A";
		}else if(avg>=80) {
			hak = "B";
		}else if(avg>=70) {
			hak = "C";
		}else {
			hak = "F";
		}
		JButton obj = (JButton)e.getSource();
		if(obj == jb1) {
			jta.append(("이름 : " + name.getText())+ "\n");
			jta.append("총점 : " + String.valueOf(sum) + "\n");
			jta.append("평균 : " + String.valueOf(avg) + "\n");
			jta.append("학점 : " + hak + "\n");
		}else if(obj == jb3) {
			name.setText("");
			kor.setText("");
			eng.setText("");
			math.setText("");
			jta.setText("");
			
		}
	}
//	public static void main(String[] args) {
//		new Ex_Grade();
//	}

}
