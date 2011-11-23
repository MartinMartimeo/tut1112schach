/*
 * This script comes without any warranty or support.
 * Use on own risk and fun.
 */

package ui;

import game.Field;
import game.Game;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author Severin <MartinMartimeo> Orth <martin@martimeo.de>
 */
public class Handling {


    static Game game = new Game();
    static Field[] fields = new Field[64];

    public static void doInit(View view) {
        game.initGame();
    }

    public static void setField(int iX, int iY, JButton field) {
        fields[iX+iY*8-9] = new Field(iX, iY, field);
    }

    public static void doClick(JButton field) {

        Field context = null;

        // Search Field
        for (Field item : fields) {
            if (item.getButton() == field) {
                context = item;
                break;
            }
        }

        // Not Found
        if (context == null)
            return;

        game.fieldClicked(context);
        
    }

}
