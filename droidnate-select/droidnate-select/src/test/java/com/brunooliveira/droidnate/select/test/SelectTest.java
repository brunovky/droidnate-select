package com.brunooliveira.droidnate.select.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.brunooliveira.droidnate.select.Select;
import com.brunooliveira.droidnate.select.clause.Clause;
import com.brunooliveira.droidnate.select.clause.Or;
import com.brunooliveira.droidnate.select.clause.Order;
import com.brunooliveira.droidnate.select.enums.LikeType;

public class SelectTest {
	
	@Test
	public void testClauseEq() {
		Select select = Select.from(User.class)
				.add(Clause.eq("name", "Bruno"))
				.list();
		assertEquals("SELECT * FROM User WHERE name = 'Bruno'", select.getSql());
	}
	
	@Test
	public void testClauseLikeLeftSide() {
		Select select = Select.from(User.class)
				.add(Clause.like("name", "b", LikeType.LEFT_SIDE))
				.list();
		assertEquals("SELECT * FROM User WHERE name LIKE '%b'", select.getSql());
	}
	
	@Test
	public void testClauseLikeRightSide() {
		Select select = Select.from(User.class)
				.add(Clause.like("name", "b", LikeType.RIGHT_SIDE))
				.list();
		assertEquals("SELECT * FROM User WHERE name LIKE 'b%'", select.getSql());
	}
	
	@Test
	public void testClauseLikeBothSides() {
		Select select = Select.from(User.class)
				.add(Clause.like("name", "b", LikeType.BOTH_SIDES))
				.list();
		assertEquals("SELECT * FROM User WHERE name LIKE '%b%'", select.getSql());
	}
	
	@Test
	public void testClauseGt() {
		Select select = Select.from(User.class)
				.add(Clause.gt("idade", 20))
				.list();
		assertEquals("SELECT * FROM User WHERE idade > 20", select.getSql());
	}
	
	@Test
	public void testClauseGe() {
		Select select = Select.from(User.class)
				.add(Clause.ge("idade", 20))
				.list();
		assertEquals("SELECT * FROM User WHERE idade >= 20", select.getSql());
	}
	
	@Test
	public void testClauseLt() {
		Select select = Select.from(User.class)
				.add(Clause.lt("idade", 40))
				.list();
		assertEquals("SELECT * FROM User WHERE idade < 40", select.getSql());
	}
	
	@Test
	public void testClauseLe() {
		Select select = Select.from(User.class)
				.add(Clause.le("idade", 40))
				.list();
		assertEquals("SELECT * FROM User WHERE idade <= 40", select.getSql());
	}
	
	@Test
	public void testClauseIn() {
		Select select = Select.from(User.class)
				.add(Clause.in("name", "Bruno", "Henrique", "Oliveira"))
				.list();
		assertEquals("SELECT * FROM User WHERE name IN ('Bruno','Henrique','Oliveira')", select.getSql());
	}
	
	@Test
	public void testClauseBetween() {
		Select select = Select.from(User.class)
				.add(Clause.between("name", "A", "B"))
				.list();
		assertEquals("SELECT * FROM User WHERE name BETWEEN 'A' AND 'B'", select.getSql());
	}
	
	@Test
	public void testClauseIsNull() {
		Select select = Select.from(User.class)
				.add(Clause.isNull("name"))
				.list();
		assertEquals("SELECT * FROM User WHERE name IS NULL", select.getSql());
	}
	
	@Test
	public void testClauseIsNotNull() {
		Select select = Select.from(User.class)
				.add(Clause.isNotNull("name"))
				.list();
		assertEquals("SELECT * FROM User WHERE name IS NOT NULL", select.getSql());
	}
	
	@Test
	public void testDistinct() {
		Select select = Select.from(User.class)
				.add(Clause.like("name", "B", LikeType.RIGHT_SIDE))
				.distinct()
				.list();
		assertEquals("SELECT DISTINCT * FROM User WHERE name LIKE 'B%'", select.getSql());
	}
	
	@Test
	public void testOrderAsc() {
		Select select = Select.from(User.class)
				.addOrder(Order.asc("name"))
				.list();
		assertEquals("SELECT * FROM User ORDER BY name", select.getSql());
	}
	
	@Test
	public void testOrderDesc() {
		Select select = Select.from(User.class)
				.addOrder(Order.desc("name"))
				.list();
		assertEquals("SELECT * FROM User ORDER BY name DESC", select.getSql());
	}
	
	@Test
	public void testOr() {
		Or or = Select.or(Clause.like("name", "A", LikeType.RIGHT_SIDE),
				Clause.like("name", "B", LikeType.RIGHT_SIDE));
		Select select = Select.from(User.class)
				.add(or)
				.list();
		assertEquals("SELECT * FROM User WHERE name LIKE 'A%' OR name LIKE 'B%'", select.getSql());
	}
	
	@Test
	public void testJoin() {
		Select select = Select.from(User.class, "u")
				.join(Order.class, "id", "user_id", "o")
				.add(Clause.eq("u.name", "Bruno"))
				.add(Clause.eq("o.price", 100))
				.list();
		assertEquals("SELECT * FROM User u INNER JOIN Order o ON u.id = o.user_id WHERE u.name = 'Bruno' AND o.price = 100", select.getSql());
	}

}