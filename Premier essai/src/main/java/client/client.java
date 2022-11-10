package client;

import org.hibernate.Session;
import org.hibernate.Transaction;

import entities.Chat;
import entities.student;
import utils.hibernateUtils;

public class client
{
	static Session session;
	public static void main(String[] args)
	{
		//crée et stocke la sesssion
		session = hibernateUtils.getSessionFactory().openSession();

		addChat(new Chat(4, "moon", true));
		
		
		session.close();
	}
	
	/** Ajoute un student à la db school */
	public static void addStudent(student etudiant)
	{
		//commence et stoque la transaction
		Transaction transaction = session.beginTransaction();
		try
		{
			//Sauvegarde un étudiant en base de données
			session.save(etudiant);
			transaction.commit();
		}
		catch(Exception e)
		{
			//rollback si il y a un pb lors de la sauvegarde
			System.out.println("Erreur lors de l'ajout à la base de  données : " + e.getMessage());
			transaction.rollback();
		}	
	}
	
	/** Ajoute un student à la db school */
	public static void addChat(Chat a)
	{
		//commence et stoque la transaction
		Transaction transaction = session.beginTransaction();
		try
		{
			//Sauvegarde un étudiant en base de données
			session.save(a);
			transaction.commit();
		}
		catch(Exception e)
		{
			//rollback si il y a un pb lors de la sauvegarde
			System.out.println("Erreur lors de l'ajout à la base de  données : " + e.getMessage());
			transaction.rollback();
		}	
	}
}
