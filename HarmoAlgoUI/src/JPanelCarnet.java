import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class JPanelCarnet extends JPanel{
	
	public JPanelCarnet() {
		
		this.removeAll();

		this.setLayout(new GridLayout(Main.carnet.size()+1,3));
		
		System.out.println(Main.carnet);
		
		this.add(new JLabel("Nom"));
		this.add(new JLabel("Prénom"));
		this.add(new JLabel("Adresse"));
		
		
		int i = 1;
		
		for(Personne personne : Main.carnet.getPersonnes()) {
			this.add(new JLabel(personne.getNom()));
			this.add(new JLabel(personne.getPrenom()));
			this.add(new JLabel(personne.getAdresse()));
			i++;
		}
		
		this.revalidate();
		this.repaint();
		
	}

}
