package sn.estm.entites;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name="journal")

public class Journal implements Serializable  {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(length=25)
	@NotEmpty(message="Code obligatoire")
	private String codejournal;
	
	@NotEmpty(message="Nom obligatoire")
	private String nom;
    @OneToMany(mappedBy="journal",cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	private List<Entree> listeentree = new ArrayList();
	
	
	
	public Journal()
	{
		
	}



	public String getNom() {
		return nom;
	}



	public void setNom(String nom) {
		this.nom = nom;
	}



	public List<Entree> getListeentree() {
		return listeentree;
	}



	public void setListeentree(List<Entree> listeentree) {
		this.listeentree = listeentree;
	}



	public void supprimerEntree(Entree entree) {
		// TODO Auto-generated method stub
		
	}



	public void ajouterEntree(Entree entree) {
		// TODO Auto-generated method stub
		
	}

}
