import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.Container;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

class MosaicFrame extends JFrame implements ActionListener {

    private ArrayList<Tile> tiles; // stores unaliased tiles
    
    // Defining dimensions for grid
    private int rows = 12;
    private int columns = 12;
    private int numTiles = rows * columns;

    public MosaicFrame() {
        setBounds(200,0,800,800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        JPanel buttonPanel = new JPanel();
        contentPane.add(buttonPanel, BorderLayout.SOUTH);

        JButton randomize = new JButton("Randomize");
        buttonPanel.add(randomize);
        randomize.addActionListener(this);

        JPanel tilePanel = new JPanel();
        contentPane.add(tilePanel, BorderLayout.CENTER);
        tilePanel.setLayout(new GridLayout(rows,columns));

        tiles = new ArrayList<Tile>();
        for(int i=0; i<numTiles; i++) {
            Tile tile = new Tile();
            tiles.add(tile);
            tilePanel.add(tile);
        }
        
    }

    // Sets random values of tiles/faces to then be repainted accordingly
    public void actionPerformed(ActionEvent e) {
        for(Tile t : tiles) {
            t.SetRandomValue();
        }
        repaint();
    }
}
