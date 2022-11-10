package entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name="chat")
@Table(name="chat")
public class Chat
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	int id;	
	@Column(name="nbPattes")
	int nbPattes;	
	@Column(name="nom")
	String nom;	
	@Column(name="vaccination")
	boolean vaccination;
	
	public Chat() {
	}
	
	/** nbPattes, nom, vaccination*/
	public Chat(int nbPattes, String nom, boolean vaccination) {
		super();
		this.nbPattes = nbPattes;
		this.nom = nom;
		this.vaccination = vaccination;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getNbPattes() {
		return nbPattes;
	}
	public void setNbPattes(int nbPattes) {
		this.nbPattes = nbPattes;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public boolean isVaccination() {
		return vaccination;
	}
	public void setVaccination(boolean vaccination) {
		this.vaccination = vaccination;
	}

	@Override
	public String toString() {
		return "Chat [id=" + id + ", nbPattes=" + nbPattes + ", nom=" + nom + ", vaccination=" + vaccination + "]";
	}	
}
