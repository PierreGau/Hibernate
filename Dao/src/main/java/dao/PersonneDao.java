package dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import entity.Personne;
import util.HibernateUtil;
public class PersonneDao 
{
	public static void persist(Personne p) {
		Session session = HibernateUtil.getSessionfactory().openSession();
		Transaction t = session.beginTransaction();

		try {
			session.persist(p);
			t.commit();
		} catch (Exception e) {
			System.out.println("Erreur lors de l'insertion de Personne : " + e.getMessage());
			t.rollback();
		}
		session.close();
	}
	
	

	public static void update(Personne p) {
		Session session = HibernateUtil.getSessionfactory().openSession();
		Transaction t = session.beginTransaction();

		try {
			session.update(p);
			t.commit();
		} catch (Exception e) {
			System.out.println("Erreur lors de l'update de Personne : " + e.getMessage());
			t.rollback();
		}
		session.close();
	}
	
	public void saveOrUpdate(Personne p)
	{
		Session session = HibernateUtil.getSessionfactory().openSession();
		Transaction t = session.beginTransaction();

		try {
			session.saveOrUpdate(p);
			t.commit();
		} catch (Exception e) {
			t.rollback();			
			System.out.println("Erreur lors du saveOrUpdate de Personne : " + e.getMessage());
		}
		session.close();
	}
	
	public static Personne get(int id) {
		Session session = HibernateUtil.getSessionfactory().openSession();
		Transaction t = session.beginTransaction();
		try {
			Personne p = session.get(Personne.class, id);
			t.commit();
			session.close();
			return p;
		} catch (Exception e) {
			t.rollback();
			System.err.println("Erreur lors de la récupération de personne dans la DATABASE à l'id " + id + " : " + e.getMessage());
			session.close();
			return null;
		}
	}
	
	public static void delete(Personne p) {
		Session session = HibernateUtil.getSessionfactory().openSession();
		Transaction t = session.beginTransaction();

		try {
			Personne temp = session.get(Personne.class, p.getId());
			session.delete(temp);
			t.commit();	
		} catch (Exception e) {
			t.rollback();
			System.out.println("Erreur lors de la suppression de personne" + p.getId() + " : " + e.getMessage());
		}
		session.close();
	}
	
	public static void deleteAll() {
		Session session = HibernateUtil.getSessionfactory().openSession();
		Transaction t = session.beginTransaction();

		try {
			List<Integer> ids = session.createQuery("SELECT id FROM personne").list();
			if (!ids.isEmpty())
			{
				for(int i = 0; i < ids.size(); i++)
				{
					Personne p = session.get(Personne.class, ids.get(i));
					session.delete(p);
				}			
				t.commit();	
			}
			else
				System.out.println("liste des id vide");
				
		} catch (Exception e) {
			t.rollback();
			System.out.println("Erreur lors de la suppression des personnes : " + e.getMessage());
		}
		session.close();
	}
}
