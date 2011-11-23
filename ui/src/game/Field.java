/*
 * This script comes without any warranty or support.
 * Use on own risk and fun.
 */
package game;

import javax.swing.JButton;

/**
 *
 * @author Severin <MartinMartimeo> Orth <martin@martimeo.de>
 */
public class Field extends JButton {

	private int iPosX;
	private int iPosY;

	public Field(int iPosX, int iPosY) {
		super();
		this.iPosX = iPosX;
		this.iPosY = iPosY;
	}

	public int getPosX() {
		return iPosX;
	}

	public int getPosY() {
		return iPosY;
	}

	@Override
	public String toString() {
		return "Field{" + "iPosX=" + iPosX + ", iPosY=" + iPosY + '}';
	}
}
