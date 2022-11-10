package entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.Table;

@Entity(name="student")
@Table(name="student")
public class student {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;	
	@Column(name="nom")
	private String nom;	
	@Column(name="prenom")
	private String prenom;

	public student() {

	}

	public student(int id, String firstName, String lastName) {
		this.id = id;
		this.nom = firstName;
		this.prenom = lastName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	@Override
	public String toString() {
		return "student [id=" + id + ", firstName=" + nom + ", lastName=" + prenom + "]";
	}
}
