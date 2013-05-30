package edu.ucsb.cs56.projects.games.pacman;

import java.awt.Event;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JComponent;

public class PacPlayer extends Character{
    private final int pacanimdelay = 2;
    private final int pacmananimcount = 4;
    private final int pacmanspeed = 6;
    private Image pacman1, pacman2up, pacman2left, pacman2right, pacman2down,
		pacman3up, pacman3down, pacman3left, pacman3right, pacman4up,
		pacman4down, pacman4left, pacman4right;
    int pacanimcount = pacanimdelay;
    int pacanimdir = 1;
    int pacmananimpos = 0;
    
    public PacPlayer(int x, int y){
    	super(x,y);
    	speed = pacmanspeed;
    }

    /**
	 * Load game sprites from images folder
     */
	@Override
	public void loadImages() {
	    try {
	        pacman1 = ImageIO.read(getClass().getResource("pacpix/pacman.png"));
	        pacman2up = ImageIO.read(getClass().getResource("pacpix/up1.png"));
	        pacman3up = ImageIO.read(getClass().getResource("pacpix/up2.png"));
	        pacman4up = ImageIO.read(getClass().getResource("pacpix/up3.png"));
	        pacman2down = ImageIO.read(getClass().getResource("pacpix/down1.png"));
	        pacman3down = ImageIO.read(getClass().getResource("pacpix/down2.png")); 
	        pacman4down = ImageIO.read(getClass().getResource("pacpix/down3.png"));
	        pacman2left = ImageIO.read(getClass().getResource("pacpix/left1.png"));
	        pacman3left = ImageIO.read(getClass().getResource("pacpix/left2.png"));
	        pacman4left = ImageIO.read(getClass().getResource("pacpix/left3.png"));
	        pacman2right = ImageIO.read(getClass().getResource("pacpix/right1.png"));
	        pacman3right = ImageIO.read(getClass().getResource("pacpix/right2.png"));
	        pacman4right = ImageIO.read(getClass().getResource("pacpix/right3.png"));
	    } 
        catch (IOException e) {
	        e.printStackTrace();
	    }
	}
	
    /**
	 * Calls the appropriate draw method for the direction Pacman is facing
	 * @param g a Graphics object
	 */
	@Override
    public void draw(Graphics2D g, JComponent canvas){
        doAnim();
        if (viewdx == -1)
            drawPacManLeft(g, canvas);
        else if (viewdx == 1)
            drawPacManRight(g, canvas);
        else if (viewdy == -1)
            drawPacManUp(g, canvas);
        else
            drawPacManDown(g, canvas);
    }
    

    /**
	 * Calls the appropriate draw method for the direction Pacman is facing
	 * @param g2d a Graphics2D object
	 * @param canvas A Jcomponent object to be drawn on
	 */
    public void drawPacMan(Graphics2D g2d, JComponent canvas) {
        if (viewdx == -1)
            drawPacManLeft(g2d, canvas);
        else if (viewdx == 1)
            drawPacManRight(g2d, canvas);
        else if (viewdy == -1)
            drawPacManUp(g2d, canvas);
        else
            drawPacManDown(g2d, canvas);
    }
    
    /**
	 * Draws Pacman facing up
	 * @param g2d a Graphics2D object
	 * @param canvas A JComponent object to be drawn on
	 */
    public void drawPacManUp(Graphics2D g2d, JComponent canvas) {
        switch (pacmananimpos) {
	        case 1:
	            g2d.drawImage(pacman2up, x + 1, y + 1, canvas);
	            break;
	        case 2:
	            g2d.drawImage(pacman3up, x + 1, y + 1, canvas);
	            break;
	        case 3:
	            g2d.drawImage(pacman4up, x + 1, y + 1, canvas);
	            break;
	        default:
	            g2d.drawImage(pacman1, x + 1, y + 1, canvas);
	            break;
        }
    }


    /**
	 * Draws Pacman facing down
	 * @param g2d a Graphics2D object
	 * @param canvas A JComponent object to be drawn on
	 */
    public void drawPacManDown(Graphics2D g2d, JComponent canvas) {
        switch (pacmananimpos) {
	        case 1:
	            g2d.drawImage(pacman2down, x + 1, y + 1, canvas);
	            break;
	        case 2:
	            g2d.drawImage(pacman3down, x + 1, y + 1, canvas);
	            break;
	        case 3:
	            g2d.drawImage(pacman4down, x + 1, y + 1, canvas);
	            break;
	        default:
	            g2d.drawImage(pacman1, x + 1, y + 1, canvas);
	            break;
        }
    }

    /**
	 * Draws Pacman facing left
	 * @param g2d a Graphics2D object
	 * @param canvas A JComponent object to be drawn on
	 */
    public void drawPacManLeft(Graphics2D g2d, JComponent canvas) {
        switch (pacmananimpos) {
        case 1:
            g2d.drawImage(pacman2left, x + 1, y + 1, canvas);
            break;
        case 2:
            g2d.drawImage(pacman3left, x + 1, y + 1, canvas);
            break;
        case 3:
            g2d.drawImage(pacman4left, x + 1, y + 1, canvas);
            break;
        default:
            g2d.drawImage(pacman1, x + 1, y + 1, canvas);
            break;
        }
    }

    /**
	 * Draws Pacman facing right
	 * @param g2d a Graphics2D object
	 * @param canvas A JComponent object to be drawn on
	 */
    public void drawPacManRight(Graphics2D g2d, JComponent canvas) {
        switch (pacmananimpos) {
	        case 1:
	            g2d.drawImage(pacman2right, x + 1, y + 1, canvas);
	            break;
	        case 2:
	            g2d.drawImage(pacman3right, x + 1, y + 1, canvas);
	            break;
	        case 3:
	            g2d.drawImage(pacman4right, x + 1, y + 1, canvas);
	            break;
	        default:
	            g2d.drawImage(pacman1, x + 1, y + 1, canvas);
	            break;
        }
    }
    
    /**
	 * Animates the Pacman sprite's direction as well as mouth opening and closing
     */
    public void doAnim() {
        pacanimcount--;
        if (pacanimcount <= 0) {
            pacanimcount = pacanimdelay;
            pacmananimpos = pacmananimpos + pacanimdir;
            if (pacmananimpos == (pacmananimcount - 1) || pacmananimpos == 0)
                pacanimdir = -pacanimdir;
        }
    }
    
    /**
	 * Class that handles key presses for game controls
     */
    class PlayerAdapter extends KeyAdapter {
        public void keyPressed(KeyEvent e) {

          int key = e.getKeyCode();

          switch (key){
	          case KeyEvent.VK_LEFT:
	            reqdx=-1;
	            reqdy=0;
	            break;
	          case KeyEvent.VK_RIGHT:
	            reqdx=1;
	            reqdy=0;
	          	break;
	          case KeyEvent.VK_UP:
	            reqdx=0;
	            reqdy=-1;
	            break;
	          case KeyEvent.VK_DOWN:
	            reqdx=0;
	            reqdy=1;
	            break;
	          default: break;
          }
        }
        
    	/**
         * Detects when a key is released
         * @param e a KeyEvent
    	 */
          public void keyReleased(KeyEvent e) {
              int key = e.getKeyCode();

              if (key == Event.LEFT || key == Event.RIGHT || 
                 key == Event.UP ||  key == Event.DOWN)
              {
                reqdx=0;
                reqdy=0;
              }
          }
      }
}