package guiProject;

import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Gui extends JFrame{
	
	JButton undo, redo, options, close, full, minimize;
	JTextArea area;
	JScrollPane scroll;
	
	public Gui(){
		
		JFrame frame1 = new JFrame("mainframe");
		
		JPanel panel1 = new JPanel();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel1.setLayout(new GridBagLayout());
		
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		//setSize(1300, 700);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel1.setLayout(new GridBagLayout());
		
		setExtendedState(JFrame.MAXIMIZED_BOTH); //Makes it full size
		setUndecorated(true);//makes the JFrame borderless
		setResizable(true);
		setVisible(true);
	
		frame1.getContentPane().add( panel1 );
		
		undo =  new JButton("undo");
		redo =  new JButton("redo");
		options =  new JButton("options");
		close =  new JButton("close");
		full =  new JButton("full");
		minimize =  new JButton("minimize");
		area =  new JTextArea(595 , 842);
		scroll = new JScrollPane(area);
		
		undo.addActionListener(this);
		redo.addActionListener(this);
		options.addActionListener(this);
		close.addActionListener(this);
		full.addActionListener(this);
		minimize.addActionListener(this);
		
	}
	
	public static void main(String[] args){
		Gui gui = new Gui();
	}
	
}
