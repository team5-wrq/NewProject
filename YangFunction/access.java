package main;

import java.awt.*;
import javax.swing.*;

public class access 
{
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable() 
		{
			
			@Override
			public void run() 
			{
				ActionFrame frame = new ActionFrame();
				frame.setTitle("test");
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			}
		});
	}
}
