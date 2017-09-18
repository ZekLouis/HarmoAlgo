package carnet.model;

import java.util.Comparator;

public class CompPersonne implements Comparator<Personne> {
	@Override
	public int compare(Personne o1, Personne o2) {
		int res = o1.getNom().compareTo(o2.getNom());
		if(res == 0) {
			return o1.getPrenom().compareTo(o2.getPrenom());
		} else {
			return res;
		}
	}
}