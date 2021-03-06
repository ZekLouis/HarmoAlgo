import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class JPanelWest extends JPanel{
	
	private CarnetAdresses carnet;
	private JButton jb_ajouter = new JButton("Ajouter");
	private JButton jb_supprimer = new JButton("Supprimer");
	private JButton jb_recherche = new JButton("Rechercher");
	private JButton jb_charger = new JButton("Charger");
	private JButton jb_sauvegarder = new JButton("Sauvegarder");
	private JButton jb_trier = new JButton("Trier");
	private JButton jb_quitter = new JButton("Quitter");
	private JLabel jb_titre = new JLabel("Actions",SwingConstants.CENTER);
	private JPanelCenter jp_center;
	private JPanelEast jp_east;
	
	public JPanelWest(CarnetAdresses carnet, JPanelCenter jp_center, JPanelEast jp_east) {
		this.carnet = carnet;
		this.jp_center = jp_center;
		this.jp_east = jp_east;
		
		this.setLayout(new GridLayout(8,1));
		this.add(jb_titre, 0);
		this.add(jb_ajouter, 1);
		this.add(jb_supprimer, 2);
		this.add(jb_recherche, 3);
		this.add(jb_trier, 4);
		this.add(jb_charger, 5);
		this.add(jb_sauvegarder, 6);
		this.add(jb_quitter, 7);
		
		jb_quitter.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					carnet.sauvegarder();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				System.exit(0);
			}
		});
		
		jb_ajouter.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				jp_center.ajouter();
				jp_east.refresh();
			}
		});
		
		jb_supprimer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				jp_center.supprimer();
				jp_east.refresh();
			}
		});
		
		jb_recherche.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				jp_center.rechercher();
				jp_east.refresh();
			}
		});
		
		jb_trier.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				jp_center.trier();
				jp_east.refresh();
			}
		});
		
		jb_charger.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				jp_center.charger();
				jp_east.refresh();
			}
		});
		
		jb_sauvegarder.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				jp_center.sauvegarder();
				jp_east.refresh();
			}
		});
		
	}
	

}
