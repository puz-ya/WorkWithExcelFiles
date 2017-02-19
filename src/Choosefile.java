
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Delete "-" and Change "," to "." in all files in a folder
 * Created by Puzino on 20.04.2015.
 */

public class ChooseFile extends JFrame {

		private JTextField
				mFilename = new JTextField(),
				mDir = new JTextField();

		private JButton
				mOpen = new JButton("Open"),
				mSave = new JButton("Save");

		public ChooseFile() {

			//set main panel + 2 buttons
			JPanel p = new JPanel();
			mOpen.addActionListener(new OpenL());
			p.add(mOpen);
			mSave.addActionListener(new SaveL());
			p.add(mSave);

			//set new container with that panel inside (buttons at the bottom (South))
			Container cp = getContentPane();
			cp.add(p, BorderLayout.SOUTH);

			//textfield not editable
			mDir.setEditable(false);
			mFilename.setEditable(false);

			//new panel 2 rows, 1 column
			p = new JPanel();
			p.setLayout(new GridLayout(2, 1));

			//add textfields into 2 rows
			p.add(mFilename);
			p.add(mDir);

			//added to the final container (textfields from top (North))
			cp.add(p, BorderLayout.NORTH);
		}

		private class OpenL implements ActionListener {
			public void actionPerformed(ActionEvent e) {

				JFileChooser c = new JFileChooser();
				// Demonstrate "Open" dialog:
				int rVal = c.showOpenDialog(ChooseFile.this);

				if (rVal == JFileChooser.APPROVE_OPTION) {
					mFilename.setText(c.getSelectedFile().getName());
					mDir.setText(c.getCurrentDirectory().toString());
				}

				if (rVal == JFileChooser.CANCEL_OPTION) {
					mFilename.setText("You pressed cancel");
					mDir.setText("");
				}
			}
		}

		private class SaveL implements ActionListener {
			public void actionPerformed(ActionEvent e) {

				dostuff(mDir.getText());

			}
		}

		public static void main(String[] args) {
			JFrame frame = new ChooseFile();
			int width = 250;
			int height = 150;

			frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

			frame.setSize(width, height);
			frame.setVisible(true);
		}

		public String getFileName(){
			return mFilename.getText();
		}

		public String getDirPath(){
			return mDir.getText();
		}

	private void dostuff(String dirName){

		//String dirName = "D:\\work\\05\\Gleeble 2015.03.05 al-ni\\06 two material\\6 Ni 0.1\\test";
		//String dirName = choose.getDirPath();
		File dir = new File(dirName);

		if(dir.isDirectory()){
			//get list of files
			String sFiles[] = dir.list();

			for(String sTmp : sFiles){
				File f=new File(dirName + "\\" + sTmp);//полный путь к файлу

				if(f.isDirectory()) {
					System.out.println(sTmp + " not a file!");
				}else{

					//each file to update
					try {
						FileReader fr = new FileReader(f.getPath());
						BufferedReader br = new BufferedReader(fr);

						FileWriter writer = new FileWriter(f.getPath()+"YD", true);
						BufferedWriter bw = new BufferedWriter(writer);

						String str;
						while ((str=br.readLine())!=null){
							str = str.replace(",", ".");
							str = str.replace("-", "");
							bw.write(str + "\r\n");
						}

						br.close();
						bw.close();

					} catch (FileNotFoundException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}else
			System.out.println(dirName+ " not a catalog!");
	}

}