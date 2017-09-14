import java.awt.GridLayout;

import javax.swing.JFrame;

public class UI extends JFrame{
	private CarnetAdresses carnet;
	
	public UI(CarnetAdresses carnet) {
		this.carnet = carnet;
		
		this.setTitle("Carnet d'adresses");
		this.setSize(1000,400);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new GridLayout(1,3));
		JPanelCenter jp_center = new JPanelCenter(carnet);
		
	    this.getContentPane().add(new JPanelWest(carnet, jp_center), 0);
		this.getContentPane().add(jp_center, 1);
	    this.getContentPane().add(new JPanelEast(carnet), 2);
	
		this.setVisible(true);
	}
}