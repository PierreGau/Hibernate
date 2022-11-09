package entities;

public class student {
	private int id;
	private String nom, prenom;

	public student() {

	}

	public student(int id, String firstName, String lastName) {
		super();
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

	public String getFirstName() {
		return nom;
	}

	public void setFirstName(String firstName) {
		this.nom = firstName;
	}

	public String getLastName() {
		return prenom;
	}

	public void setLastName(String lastName) {
		this.prenom = lastName;
	}

	@Override
	public String toString() {
		return "student {id=" + id + ", firstName=" + nom + ", lastName=" + prenom + "}";
	}
}
