package client;

import dao.PersonneDao;
import entity.Personne;

public class main {

	public static void main(String[] args) {
		
		Personne p =new Personne("bob", "dylan", 35);
		PersonneDao.persist(p);
		p.setNom("josé");
		PersonneDao.update(p);
		PersonneDao.delete(p);
	}

}
