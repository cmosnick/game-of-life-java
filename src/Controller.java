
import javax.swing.*;
import java.awt.GridLayout;
import java.awt.Container;

public class Controller extends JFrame{
    public int rows = 50;
    public int columns = 80;
    private Cell[][] cells = new Cell[this.rows][this.columns];
    private GridLayout gameBoard = new GridLayout(this.rows, this.columns);


    public Controller(String name){
        super(name);
        setResizable(false);

        initFrame();
        play(3);
    }

    private void addComponentsToPane(final Container pane) {

        final JPanel jpanel = new JPanel();
        // Add cells to grid
        for (int i = 0 ; i < this.columns ; i++) {
            for (int j = 0 ; j < this.rows ; j++) {
                jpanel.add(cells[j][i] = new Cell(i, j));
            }
        }
        jpanel.setLayout(this.gameBoard);

        pane.add(jpanel);
    }

    private void initFrame() {

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Set up the content pane
        this.addComponentsToPane(this.getContentPane());
        //Display the window
        this.pack();
        this.setVisible(true);
    }

    private void play(double seconds) {

        try {
            while (true) {
                // Make copy of old state of cells
                boolean[][] cellStates = new boolean[this.rows][this.columns];
                for(int i = 0 ; i < this.columns ; i++) {
                    for (int j=0 ; j < this.rows ; j++) {
                        cellStates[j][i] = cells[j][i].alive;
                    }
                }
                // Iterate through cells and perform logic
                for(int i = 0 ; i < this.columns ; i++) {
                    for (int j=0 ; j < this.rows ; j++) {
                        // Perform logic on cell and those around it to determine if alive
                        int neighbors = this.getNumAliveNeighbors(cellStates, j, i);
                        this.cells[j][i].updateStatus(neighbors);
                    }
                }

                Thread.sleep(1000 * (long)seconds);
            }
        }
        catch (InterruptedException e) {
            System.out.println(e.getMessage().toString());
        }
    }

    private int getNumAliveNeighbors(boolean[][] cellStates, int cellRow, int cellColumn) {
        int numAliveNeighbors = 0;

        boolean hasTop = false;
        boolean hasBottom = false;
        boolean hasLeft = false;
        boolean hasRight = false;

        if(cellRow > 0) {
            hasTop = true;
        }
        if(cellRow < this.rows -1) {
            hasBottom = true;
        }
        if(cellColumn > 0) {
            hasLeft = true;
        }
        if(cellColumn < this.columns - 1) {
            hasRight = true;
        }

        // Top row
        if(hasTop) {
            // Upper left
            if (hasLeft) {
                if(cellStates[cellRow - 1][cellColumn - 1]) {
                    numAliveNeighbors++;
                }

            }
            // Top
            if(cellStates[cellRow - 1][cellColumn]) {
                numAliveNeighbors++;
            }
            //Upper right
            if (hasRight) {
                if (cellStates[cellRow - 1][cellColumn + 1]) {
                    numAliveNeighbors++;
                }
            }
        }

        // Left
        if(hasLeft) {
            if(cellStates[cellRow][cellColumn - 1]) {
                numAliveNeighbors++;
            }
        }

        // Right
        if(hasRight) {
            if (cellStates[cellRow][cellColumn + 1]) {
                numAliveNeighbors++;
            }
        }


        // Bottom row
        if(hasBottom) {
            // Lower left
            if (hasLeft) {
                if (cellStates[cellRow + 1][cellColumn - 1]) {
                    numAliveNeighbors++;
                }
            }
            // Bottom
            if(cellStates[cellRow + 1][cellColumn]) {
                numAliveNeighbors++;
            }
            // Lower right
            if (hasRight) {
                if (cellStates[cellRow + 1][cellColumn + 1]) {
                    numAliveNeighbors++;
                }
            }
        }

        return numAliveNeighbors;
    }
}
