import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

public class JPanelCenter extends JPanel{
	
	private CarnetAdresses carnet;
	private JPanelEast jp_east;
	
	public JPanelCenter(CarnetAdresses carnet, JPanelEast jp_east) {
		this.carnet = carnet;
		this.jp_east = jp_east;
	}
	
	public void ajouter() {
		JLabel jl_nom = new JLabel("Nom");
		JLabel jl_prenom = new JLabel("Prénom");
		JLabel jl_adresse = new JLabel("Adresse");
		JTextField jtf_nom = new JTextField();
		JTextField jtf_prenom = new JTextField();
		JTextField jtf_adresse = new JTextField();
		JButton jb_valider = new JButton("Valider");
		
		SpringLayout sp_layout = new SpringLayout();
		this.removeAll();
		
		this.add(jl_nom);
		this.add(jl_prenom);
		this.add(jl_adresse);
		this.add(jtf_nom);
		this.add(jtf_prenom);
		this.add(jtf_adresse);
		this.add(jb_valider);
		
		sp_layout.putConstraint(SpringLayout.WEST, jl_nom, 5, SpringLayout.EAST, jtf_nom);
		sp_layout.putConstraint(SpringLayout.WEST, jl_prenom, 5, SpringLayout.EAST, jtf_prenom);
		sp_layout.putConstraint(SpringLayout.WEST, jl_adresse, 5, SpringLayout.EAST, jtf_adresse);
		
		this.revalidate();
		this.repaint();
	}
	
	public void supprimer() {
		this.removeAll();
		this.add(new JLabel("Index"), 0);
		this.add(new JTextField(), 1);
		this.add(new JButton("Valider"), 6, 2);
		this.revalidate();
		this.repaint();
	}
	
	public void rechercher() {
		this.removeAll();
		this.add(new JLabel("Recherche"), 0);
		this.add(new JTextField(), 1);
		this.add(new JButton("Valider"), 6, 2);
		this.revalidate();
		this.repaint();
	}
	
	public void trier() {
		this.removeAll();
		this.add(new JLabel("Carnet d'adresses trié !"), 0);
		this.revalidate();
		this.repaint();
	}
	
	public void charger() {
		this.removeAll();
		this.setLayout(new GridLayout(1,1));
		JFileChooser jfilechooser = new JFileChooser();
		jfilechooser.addActionListener(new ChoiceActionListener(this.jp_east, jfilechooser));
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

class ChoiceActionListener implements ActionListener {
	
	private JPanelEast jp_east;
	private JFileChooser jf_chooser;

	public ChoiceActionListener(JPanelEast jp_east, JFileChooser jf_chooser) {
		this.jp_east = jp_east;
		this.jf_chooser = jf_chooser;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String filename = this.jf_chooser.getSelectedFile().getPath();
		Main.carnet = Main.charger(filename);
		this.jp_east.refresh();
	}
	
}
