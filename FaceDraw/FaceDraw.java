import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// TODO: Fix bug that reverts to original panel on resize

public class FaceDraw {

	private static JFrame frame = new JFrame("FaceDraw");

	public static void main(String[] args) {
		System.out.println("Starting FaceDraw...\n");

		frame.setLayout(new BorderLayout()); // Redundant, but I'm keeping it
		frame.setBounds(400, 100, 690, 690);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JButton button = new JButton("Generate Faces!");

		// Generates new set of faces
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.add(new FacePanel(frame.getWidth(), frame.getHeight()), BorderLayout.CENTER);
				frame.setVisible(true);
			}
		});

		frame.add(button, BorderLayout.SOUTH);
		frame.setVisible(true);

	}

}
