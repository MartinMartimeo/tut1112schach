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
public class Field {

    private int iPosX;
    private int iPosY;
    private JButton bButton;

    public Field(int iPosX, int iPosY, JButton bButton) {
        this.iPosX = iPosX;
        this.iPosY = iPosY;
        this.bButton = bButton;
    }

    public int getPosX() {
        return iPosX;
    }

    public int getPosY() {
        return iPosY;
    }

    public void setText(String sText) {
        bButton.setText(sText);
    }

    public JButton getButton() {
        return bButton;
    }



}
