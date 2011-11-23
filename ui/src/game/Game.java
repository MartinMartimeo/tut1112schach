/*
 * This script comes without any warranty or support.
 * Use on own risk and fun.
 */
package game;

/**
 *
 * @author Severin <MartinMartimeo> Orth <martin@martimeo.de>
 */
public class Game {

	public static Game instance;
	
	MainWindow main;
	
	public Game(){
		Game.instance = this;
		main = new MainWindow(this);
	}

	public void initGame() {
	}

	public void fieldClicked(Field field) {
		System.out.println(field+" clicked!");
	}
	
	public static void main(String[] args) {
		Game game = new Game();
	}
}
