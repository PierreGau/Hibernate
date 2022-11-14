package entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.Session;
import org.hibernate.Transaction;

import util.HibernateUtil;

@Entity(name = "player")
@Table(name = "player")
public class Player {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	int id;
	@Column(name = "hp")
	int hp;
	@Column(name = "mana")
	int mana;
	@Column(name = "posX")
	double posX;
	@Column(name = "posY")
	double posY;
	@Column(name = "image")
	String image;
	@Column(name = "username")
	String username;

	protected Player() {
	}

	public Player(int hp, int mana, double posX, double posY, String image, String username) {
		super();
		this.hp = hp;
		this.mana = mana;
		this.posX = posX;
		this.posY = posY;
		this.image = image;
		this.username = username;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;

		if (this.hp <= 0)
		{
			System.out.println(this.username + " est décédé");
			this.hp = 0;
		}
	}

	public int getMana() {
		return mana;
	}

	public void setMana(int mana) {
		this.mana = mana;
		
		if (this.mana <0)
			this.mana = 0;
	}

	public double getPosX() {
		return posX;
	}

	public void setPosX(double posX) {
		this.posX = posX;
	}

	public double getPosY() {
		return posY;
	}

	public void setPosY(double posY) {
		this.posY = posY;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String toString() {
		return "Player [id=" + id + ", hp=" + hp + ", mana=" + mana + ", posX=" + posX + ", posY=" + posY + ", image="
				+ image + ", username=" + username + "]";
	}

	public void persist() {
		Session session = HibernateUtil.getSessionfactory().openSession();
		Transaction t = session.beginTransaction();

		try {
			session.persist(this);
			t.commit();
		} catch (Exception e) {
			System.out.println("Erreur lors de l'insertion de Player : " + e.getMessage());
			t.rollback();
		}
		session.close();
	}

	public void update() {
		Session session = HibernateUtil.getSessionfactory().openSession();
		Transaction t = session.beginTransaction();

		try {
			session.update(this);
			t.commit();
		} catch (Exception e) {
			System.out.println("Erreur lors de l'update de Player : " + e.getMessage());
			t.rollback();
		}
		session.close();
	}
	
	public void saveOrUpdate()
	{
		Session session = HibernateUtil.getSessionfactory().openSession();
		Transaction t = session.beginTransaction();

		try {
			session.saveOrUpdate(this);
			t.commit();
		} catch (Exception e) {
			t.rollback();			
			System.out.println("Erreur lors du saveOrUpdate de Player : " + e.getMessage());
		}
		session.close();
	}

	public static Player getFromDb(int id) {
		Session session = HibernateUtil.getSessionfactory().openSession();
		Transaction t = session.beginTransaction();
		try {
			Player p = session.get(Player.class, id);
			t.commit();
			session.close();
			return p;
		} catch (Exception e) {
			t.rollback();
			System.err.println("Erreur lors de la récupération de Player dans la DATABASE à l'id " + id + " : " + e.getMessage());
			session.close();
			return null;
		}
		
	}

	public void delete() {
		Session session = HibernateUtil.getSessionfactory().openSession();
		Transaction t = session.beginTransaction();

		try {
			Player p = session.get(Player.class, this.getId());
			session.delete(p);
			t.commit();	
		} catch (Exception e) {
			t.rollback();
			System.out.println("Erreur lors de la suppression du player" + id + " : " + e.getMessage());
		}
		session.close();
	}
	
	public static void deleteAll() {
		Session session = HibernateUtil.getSessionfactory().openSession();
		Transaction t = session.beginTransaction();

		try {
			List<Integer> ids = session.createQuery("SELECT id FROM player").list();
			if (!ids.isEmpty())
			{
				for(int i = 0; i < ids.size(); i++)
				{
					Player p = session.get(Player.class, ids.get(i));
					session.delete(p);
				}			
				t.commit();	
			}
			else
				System.out.println("liste des id vide");
				
		} catch (Exception e) {
			t.rollback();
			System.out.println("Erreur lors de la suppression des player : " + e.getMessage());
		}
		session.close();
	}


	public void AttackPlayer(Player cible) {
		if (mana > 10) {
			Session session = HibernateUtil.getSessionfactory().openSession();
			Transaction t = session.beginTransaction();
			try {
				this.setMana(this.getMana() - 10);
				cible.takeDamages(10);
				session.update(this);
				session.update(cible);
				t.commit();
				System.out.println(this.username + " a attaque " + cible.getUsername());
			} catch (Exception e) {
				System.out.println("Erreur lors de l'insertion de Player : " + e.getMessage());
				t.rollback();
			}
			session.close();
		} else {
			System.out.println("bah alors, il est ou ton mana ?");
		}
	}

	public void AttackPlayer(int id) {
		if (mana > 10) {
			Session session = HibernateUtil.getSessionfactory().openSession();
			Transaction t = session.beginTransaction();
			try {
				Player cible = session.get(Player.class, id);
				this.setMana(this.getMana() - 10);
				cible.takeDamages(10);
				session.update(this);
				session.update(cible);
				t.commit();
				System.out.println(this.username + " a attaque " + cible.getUsername());
			} catch (Exception e) {
				System.out.println("Erreur lors de l'insertion de Player : " + e.getMessage());
				t.rollback();
			}
			session.close();
		}
	}

	public void heal(int amount) {
		if (mana > 10) {
			Session session = HibernateUtil.getSessionfactory().openSession();
			Transaction t = session.beginTransaction();		
			try {
				this.consumeMana(10);
				gainHp(amount);
				session.update(this);
				t.commit();
				System.out.println(this.username + " s'est soigné " + amount + " hp.");
			} catch (Exception e) {
				System.out.println("Erreur lors de l'insertion de Player : " + e.getMessage());
				t.rollback();
			}
			session.close();
		} else {
			System.out.println("bah alors, il est ou ton mana ?");
		}
	}
	
	public void gainHp(int amount)
	{
		this.setHp(this.getHp() + amount);
	}
	
	public void consumeMana(int amount)
	{
		this.setMana(this.getMana() - amount);
	}

	public void AttackPlayer(Player cible, int damages) {
		if (mana >= 10) {
			Session session = HibernateUtil.getSessionfactory().openSession();
			Transaction t = session.beginTransaction();
			try {
				this.consumeMana(10);
				cible.takeDamages(damages);
				session.update(this);
				session.update(cible);
				t.commit();
				System.out.println(this.username + " a attaque " + cible.getUsername());
			} catch (Exception e) {
				System.out.println("Erreur lors de l'insertion de Player : " + e.getMessage());
				t.rollback();
			}
			session.close();

		} else {
			System.out.println("bah alors, il est ou ton mana ?");
		}
	}

	public void AttackPlayer(int id, int damages) {
		if (mana > 10) {
			Session session = HibernateUtil.getSessionfactory().openSession();
			Transaction t = session.beginTransaction();
			this.mana -= 10;
			try {
				Player cible = session.get(Player.class, id);
				cible.takeDamages(damages);
				session.update(this);
				session.update(cible);
				t.commit();
			} catch (Exception e) {
				System.out.println("Erreur lors de l'insertion de Player : " + e.getMessage());
				t.rollback();
			}
			session.close();
		}
	}

	public void takeDamages(int damages) {
		setHp(getHp() - damages);
	}

}
