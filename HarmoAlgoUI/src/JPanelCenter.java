import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class JPanelCenter extends JPanel{
	
	private CarnetAdresses carnet;
	
	public JPanelCenter(CarnetAdresses carnet) {
		this.carnet = carnet;
	}
	
	public void ajouter() {
		this.removeAll();
		this.setLayout(new GridLayout(4,2));
		this.add(new JLabel("Nom"), 0);
		this.add(new JTextField(), 1);
		this.add(new JLabel("Prénom"), 2);
		this.add(new JTextField(), 3);
		this.add(new JLabel("Adresse"), 4);
		this.add(new JTextField(), 5);
		this.add(new JButton("Valider"), 6);
		this.revalidate();
		this.repaint();
	}
	
	public void supprimer() {
		this.removeAll();
		this.setLayout(new GridLayout(2,2));
		this.add(new JLabel("Index"), 0);
		this.add(new JTextField(), 1);
		this.add(new JButton("Valider"), 6, 2);
		this.revalidate();
		this.repaint();
	}
	
	public void rechercher() {
		this.removeAll();
		this.setLayout(new GridLayout(2,2));
		this.add(new JLabel("Recherche"), 0);
		this.add(new JTextField(), 1);
		this.add(new JButton("Valider"), 6, 2);
		this.revalidate();
		this.repaint();
	}
	
	public void trier() {
		this.removeAll();
		this.setLayout(new GridLayout(1,1));
		this.add(new JLabel("Carnet d'adresses trié !"), 0);
		this.revalidate();
		this.repaint();
	}
	
	public void charger() {
		this.removeAll();
		this.setLayout(new GridLayout(1,1));
		JFileChooser jfilechooser = new JFileChooser();
		jfilechooser.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println(e);
			}
		});
		this.add(jfilechooser, 0);
		this.revalidate();
		this.repaint();
	}
	
	public void sauvegarder() {
		this.removeAll();
		this.revalidate();
		this.repaint();
	}
	
}
