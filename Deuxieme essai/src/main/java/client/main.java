package client;

import org.hibernate.Session;
import org.hibernate.Transaction;

import entity.Player;
import util.HibernateUtil;

public class main {

	public static void main(String[] args) {

		Player p1 = Player.getFromDb(1);
		//p1.setMana(100);
		//p1.setUsername("José");
		//p1.update();
		
		Player p2 = Player.getFromDb(2);
		//p2.heal(50);
		
		Player p3 = Player.getFromDb(3);
		p3.setHp(100);
		p3.saveOrUpdate();
		
		p1.AttackPlayer(p2,50);
		
		Player.deleteAll();
	}
}
