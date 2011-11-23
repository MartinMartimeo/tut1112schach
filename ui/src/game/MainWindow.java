/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;

/**
 *
 * @author robert
 */
public class MainWindow extends JFrame{
	
	Field[][] fields = new Field[8][8];
	Game game;
	
	public MainWindow(Game game){
		super();
		this.game = game;
		this.setPreferredSize(new Dimension(400,400));
		this.setMinimumSize(new Dimension(400,400));
		this.setMaximumSize(new Dimension(400,400));
		this.setSize(400,400);
		this.setResizable(false);
		this.setLayout(new GridLayout(8, 8));
		for (int i = 0; i< 64; i++){
			Field temp = new Field(i%8, i/8);
			temp.setSize(50,50);
			temp.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					Game.instance.fieldClicked((Field) e.getSource());
				}
			});
			this.add(temp);
			this.fields[i/8][i%8] = temp;
		}
		this.setVisible(true);
		
	}
	
}
