package client;

import org.hibernate.Session;
import org.hibernate.Transaction;

import entity.Player;
import util.HibernateUtil;

public class main {

	public static void main(String[] args) {

		Player p = Player.getFromDb(1);
		p.persist();
		p.setUsername("José");
		p.Update();
		
		Player p1 = Player.getFromDb(2);
		p1.setHp(2500);
		p1.Update();
		
		p.AttackPlayer(p1);
	}
}
