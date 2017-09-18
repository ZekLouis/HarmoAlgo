package carnet.model;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Helper class to wrap a list of persons. This is used for saving the
 * list of persons to XML.
 * 
 * @author Marco Jakob
 */
@XmlRootElement(name = "personnes")
public class PersonnesWrapper {

    private List<Personne> personnes;

    @XmlElement(name = "personne")
    public List<Personne> getPersonnes() {
        return personnes;
    }

    public void setPersons(List<Personne> personnes) {
        this.personnes = personnes;
    }
}
