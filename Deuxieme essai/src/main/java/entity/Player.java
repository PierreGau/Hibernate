package entity;

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
	}

	public int getMana() {
		return mana;
	}

	public void setMana(int mana) {
		this.mana = mana;
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
	
	public void persist()
	{
		Session session = HibernateUtil.getSessionfactory().openSession();
		Transaction t = session.beginTransaction();
		
		try
		{
			session.persist(this);
			t.commit();
		}
		catch(Exception e)
		{
			System.out.println("Erreur lors de l'insertion de Player : " + e.getMessage());
			t.rollback();
		}
		session.close();
	}
	
	public void Update()
	{
		Session session = HibernateUtil.getSessionfactory().openSession();
		Transaction t = session.beginTransaction();
		
		try
		{
			session.update(this);
			t.commit();
		}
		catch(Exception e)
		{
			System.out.println("Erreur lors de l'insertion de Player : " + e.getMessage());
			t.rollback();
		}
		session.close();
	}
	
	public static Player getFromDb(int id)
	{
		Session session = HibernateUtil.getSessionfactory().openSession();
		Transaction t = session.beginTransaction();
		Player p;
		try
		{
			p = session.get(Player.class, id);
		}
		catch(Throwable ex)
		{
			t.rollback();
			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
		
		return p;
	}
	
	public void AttackPlayer(Player cible)
	{				
		if(mana>10)
		{
			Session session = HibernateUtil.getSessionfactory().openSession();
			Transaction t = session.beginTransaction();
			this.mana -= 10;
			cible.takeDamages(10);
			try
			{
				session.update(this);
				session.update(cible);
				t.commit();
				System.out.println(this.username +" a attaque " + cible.getUsername());
			}
			catch(Exception e)
			{
				System.out.println("Erreur lors de l'insertion de Player : " + e.getMessage());
				t.rollback();
			}
			session.close();
			
			
		}
		else
		{
			System.out.println("bah alors, il est ou ton mana ?");
		}
	}
	
	public void AttackPlayer(int id)
	{		
		
		if(mana>10)
		{
			Session session = HibernateUtil.getSessionfactory().openSession();
			Transaction t = session.beginTransaction();
			this.mana -= 10;
			try
			{				
				session.update(this);
				session.update(session.get(Player.class, id));
				t.commit();
			}
			catch(Exception e)
			{
				System.out.println("Erreur lors de l'insertion de Player : " + e.getMessage());
				t.rollback();
			}
			session.close();
		}
	}
	
	public void takeDamages(int damages)
	{
		this.hp -= damages;
	}

}
