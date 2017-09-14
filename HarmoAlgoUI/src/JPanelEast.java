import javax.swing.JPanel;
import javax.swing.SwingConstants;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;

public class JPanelEast extends JPanel{
	
	private CarnetAdresses carnet;
	private JLabel jl_title;
	private JPanelCarnet jp_carnet;
	
	public JPanelEast(CarnetAdresses carnet) {
		
		this.carnet = carnet;

		this.setLayout(new GridLayout(1,1));
		this.jp_carnet = new JPanelCarnet(carnet);
		//this.jl_title = new JLabel(carnet.getNom(), SwingConstants.CENTER);

		//this.add(jl_title, 0);
		this.add(jp_carnet, 0);
		
	}
	
}