import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CellMouseAdapter extends MouseAdapter{
    private Cell parent;

    public CellMouseAdapter (Cell parent) {
        super();
        this.parent = parent;
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        changeCell();
    }

    private void changeCell() {
        if (parent.alive) {
            parent.deactivate();
        }
        else {
            parent.activate();
        }
    }
}
