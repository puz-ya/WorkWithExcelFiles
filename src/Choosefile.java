
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Created by YDeathYLORD on 20.04.2015.
 */
public class Choosefile extends JFrame {

		private JTextField
				filename = new JTextField(),
				dir = new JTextField();

		private JButton
				open = new JButton("Open"),
				save = new JButton("Save");

		public Choosefile() {

			//set main panel + 2 buttons
			JPanel p = new JPanel();
			open.addActionListener(new OpenL());
			p.add(open);
			save.addActionListener(new SaveL());
			p.add(save);

			//set new container with that panel inside (buttons at the bottom (South))
			Container cp = getContentPane();
			cp.add(p, BorderLayout.SOUTH);

			//textfield not editable
			dir.setEditable(false);
			filename.setEditable(false);

			//new panel 2 rows, 1 column
			p = new JPanel();
			p.setLayout(new GridLayout(2, 1));

			//add textfields into 2 rows
			p.add(filename);
			p.add(dir);

			//added to the final container (textfields from top (North))
			cp.add(p, BorderLayout.NORTH);
		}

		class OpenL implements ActionListener {
			public void actionPerformed(ActionEvent e) {

				JFileChooser c = new JFileChooser();
				// Demonstrate "Open" dialog:
				int rVal = c.showOpenDialog(Choosefile.this);
				if (rVal == JFileChooser.APPROVE_OPTION) {
					filename.setText(c.getSelectedFile().getName());
					dir.setText(c.getCurrentDirectory().toString());
				}
				if (rVal == JFileChooser.CANCEL_OPTION) {
					filename.setText("You pressed cancel");
					dir.setText("");
				}
			}
		}

		class SaveL implements ActionListener {
			public void actionPerformed(ActionEvent e) {

				main m = new main();
				m.dostuff(dir.getText());

				/*JFileChooser c = new JFileChooser();
				// Demonstrate "Save" dialog:
				int rVal = c.showSaveDialog(Choosefile.this);
				if (rVal == JFileChooser.APPROVE_OPTION) {
					filename.setText(c.getSelectedFile().getName());
					dir.setText(c.getCurrentDirectory().toString());
				}
				if (rVal == JFileChooser.CANCEL_OPTION) {
					filename.setText("You pressed cancel");
					dir.setText("");
				} */
			}
		}

		public static void main(String[] args) {
			JFrame frame = new Choosefile();
			int width = 250;
			int height = 150;

			frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

			frame.setSize(width, height);
			frame.setVisible(true);
		}

		public String getFileName(){
			return filename.getText();
		}

		public String getDirPath(){
			return dir.getText();
		}
	}