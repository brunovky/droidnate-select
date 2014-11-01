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
				.where(Clause.eq("name", "Bruno"))
				.list();
		assertEquals("SELECT * FROM User WHERE name = 'Bruno'", select.getSql());
		System.out.println(select.getSql());
	}
	
	@Test
	public void testClauseLikeLeftSide() {
		Select select = Select.from(User.class)
				.where(Clause.like("name", "b", LikeType.LEFT_SIDE))
				.list();
		assertEquals("SELECT * FROM User WHERE name LIKE '%b'", select.getSql());
		System.out.println(select.getSql());
	}
	
	@Test
	public void testClauseLikeRightSide() {
		Select select = Select.from(User.class)
				.where(Clause.like("name", "b", LikeType.RIGHT_SIDE))
				.list();
		assertEquals("SELECT * FROM User WHERE name LIKE 'b%'", select.getSql());
		System.out.println(select.getSql());
	}
	
	@Test
	public void testClauseLikeBothSides() {
		Select select = Select.from(User.class)
				.where(Clause.like("name", "b", LikeType.BOTH_SIDES))
				.list();
		assertEquals("SELECT * FROM User WHERE name LIKE '%b%'", select.getSql());
		System.out.println(select.getSql());
	}
	
	@Test
	public void testClauseGt() {
		Select select = Select.from(User.class)
				.where(Clause.gt("age", 20))
				.list();
		assertEquals("SELECT * FROM User WHERE age > 20", select.getSql());
		System.out.println(select.getSql());
	}
	
	@Test
	public void testClauseGe() {
		Select select = Select.from(User.class)
				.where(Clause.ge("age", 20))
				.list();
		assertEquals("SELECT * FROM User WHERE age >= 20", select.getSql());
		System.out.println(select.getSql());
	}
	
	@Test
	public void testClauseLt() {
		Select select = Select.from(User.class)
				.where(Clause.lt("age", 40))
				.list();
		assertEquals("SELECT * FROM User WHERE age < 40", select.getSql());
		System.out.println(select.getSql());
	}
	
	@Test
	public void testClauseLe() {
		Select select = Select.from(User.class)
				.where(Clause.le("age", 40))
				.list();
		assertEquals("SELECT * FROM User WHERE age <= 40", select.getSql());
		System.out.println(select.getSql());
	}
	
	@Test
	public void testClauseIn() {
		Select select = Select.from(User.class)
				.where(Clause.in("name", "Bruno", "Henrique", "Oliveira"))
				.list();
		assertEquals("SELECT * FROM User WHERE name IN ('Bruno','Henrique','Oliveira')", select.getSql());
		System.out.println(select.getSql());
	}
	
	@Test
	public void testClauseBetween() {
		Select select = Select.from(User.class)
				.where(Clause.between("name", "A", "B"))
				.list();
		assertEquals("SELECT * FROM User WHERE name BETWEEN 'A' AND 'B'", select.getSql());
		System.out.println(select.getSql());
	}
	
	@Test
	public void testClauseIsNull() {
		Select select = Select.from(User.class)
				.where(Clause.isNull("name"))
				.list();
		assertEquals("SELECT * FROM User WHERE name IS NULL", select.getSql());
		System.out.println(select.getSql());
	}
	
	@Test
	public void testClauseIsNotNull() {
		Select select = Select.from(User.class)
				.where(Clause.isNotNull("name"))
				.list();
		assertEquals("SELECT * FROM User WHERE name IS NOT NULL", select.getSql());
		System.out.println(select.getSql());
	}
	
	@Test
	public void testDistinct() {
		Select select = Select.from(User.class)
				.where(Clause.like("name", "B", LikeType.RIGHT_SIDE))
				.distinct()
				.list();
		assertEquals("SELECT DISTINCT * FROM User WHERE name LIKE 'B%'", select.getSql());
		System.out.println(select.getSql());
	}
	
	@Test
	public void testOrderAsc() {
		Select select = Select.from(User.class)
				.orderBy(Order.asc("name"))
				.list();
		assertEquals("SELECT * FROM User ORDER BY name", select.getSql());
		System.out.println(select.getSql());
	}
	
	@Test
	public void testOrderDesc() {
		Select select = Select.from(User.class)
				.orderBy(Order.desc("name"))
				.list();
		assertEquals("SELECT * FROM User ORDER BY name DESC", select.getSql());
		System.out.println(select.getSql());
	}
	
	@Test
	public void testOr() {
		Or or = Select.or(Clause.like("name", "A", LikeType.RIGHT_SIDE),
				Clause.like("name", "B", LikeType.RIGHT_SIDE));
		Select select = Select.from(User.class)
				.where(or)
				.list();
		assertEquals("SELECT * FROM User WHERE name LIKE 'A%' OR name LIKE 'B%'", select.getSql());
		System.out.println(select.getSql());
	}
	
	@Test
	public void testJoin() {
		Select select = Select.from(User.class, "u")
				.join(Orders.class, "id", "user_id", "o")
				.where(Clause.eq("u.name", "Bruno"))
				.where(Clause.eq("o.price", 100))
				.list();
		assertEquals("SELECT * FROM User u INNER JOIN Orders o ON u.id = o.user_id WHERE u.name = 'Bruno' AND o.price = 100", select.getSql());
		System.out.println(select.getSql());
	}

}