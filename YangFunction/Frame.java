package main;

import java.awt.*;
import javax.swing.*;
import java.awt.EventQueue;

public class Frame {
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable(){
			public void run() {
				try {
					 MainFrame window = new MainFrame();
					 window.setVisible(true);
					 window.setSize(300, 200);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}	
		});
	}
}

