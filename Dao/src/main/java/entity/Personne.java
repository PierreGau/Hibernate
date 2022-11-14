package entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "personne")
@Table(name = "personne")
public class Personne 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	@Column(name = "prenom")
	private String prenom;
	@Column(name = "nom")
	private String nom;
	@Column(name = "age")
	private int age;
	
	
	
	public Personne() {
	}
	public Personne(String prenom, String nom, int age) {
		this.prenom = prenom;
		this.nom = nom;
		this.age = age;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	@Override
	public String toString() {
		return "Personne [id=" + id + ", prenom=" + prenom + ", nom=" + nom + ", age=" + age + "]";
	}
	
	
}
