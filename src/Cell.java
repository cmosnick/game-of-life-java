import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.util.Random;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class Cell extends JPanel {

    public int row;
    public int column;
    public boolean alive = false;
    Random rand = new Random();

    private static Color borderColor = new Color(105,105,105);
    private static Color deactivatedColor = Color.gray;
    private static Color activatedColor = new Color(50,205,50);

    public Cell(int row, int column) {
        this.row= row;
        this.column = column;

        setBorder(new LineBorder(this.borderColor, 1));   // Set cell's border
        setBackground(this.deactivatedColor);

        // Add Mouse Listener
        addMouseListener(new CellMouseAdapter(this));
    }

    public void updateStatus(int numAliveNeighbors) {

        // Cell is dead
        if (! this.alive) {
            if(numAliveNeighbors == 3) {
                // Enough neighbors to spawn
                this.activate();
            }
            else{
                // Add some random spawning in there
                if (rand.nextInt(200) == 5) {
                    this.activate();
                }
            }
        }
        else {
            // Cell is alive
            if(numAliveNeighbors < 2) {
                // Die of solitude
                this.deactivate();
            }
            else if (numAliveNeighbors >= 4) {
                // Die of overpopulation
                this.deactivate();
            }
        }
    }

    public void activate() {
        this.alive = true;
        this.setBackground(this.activatedColor);
    }

    public void deactivate() {
        this.alive = false;
        this.setBackground(this.deactivatedColor);
    }
}
