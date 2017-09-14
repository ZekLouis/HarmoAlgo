import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class JPanelCarnet extends JPanel{
	
	private CarnetAdresses carnet;
	
	public JPanelCarnet(CarnetAdresses carnet) {
		
		this.carnet = carnet;
		
		this.setLayout(new GridLayout(carnet.size()+1,3));
		
		this.add(new JLabel("Nom"));
		this.add(new JLabel("Prénom"));
		this.add(new JLabel("Adresse"));
		
		
		int i = 1;
		
		for(Personne personne : this.carnet.getPersonnes()) {
			this.add(new JLabel(personne.getNom()));
			this.add(new JLabel(personne.getPrenom()));
			this.add(new JLabel(personne.getAdresse()));
			i++;
		}
		
	}

}