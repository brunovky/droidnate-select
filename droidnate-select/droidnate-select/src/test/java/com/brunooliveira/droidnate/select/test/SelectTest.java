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
		Select select = Select.all()
				.from("User")
				.where(Clause.eq("name", "Bruno"))
				.list();
		assertEquals("SELECT * FROM User WHERE name = 'Bruno'", select.getSql());
		System.out.println(select.getSql());
	}
	
	@Test
	public void testClauseLikeLeftSide() {
		Select select = Select.all()
				.from("User")
				.where(Clause.like("name", "b", LikeType.LEFT_SIDE))
				.list();
		assertEquals("SELECT * FROM User WHERE name LIKE '%b'", select.getSql());
		System.out.println(select.getSql());
	}
	
	@Test
	public void testClauseLikeRightSide() {
		Select select = Select.all()
				.from("User")
				.where(Clause.like("name", "b", LikeType.RIGHT_SIDE))
				.list();
		assertEquals("SELECT * FROM User WHERE name LIKE 'b%'", select.getSql());
		System.out.println(select.getSql());
	}
	
	@Test
	public void testClauseLikeBothSides() {
		Select select = Select.all()
				.from("User")
				.where(Clause.like("name", "b", LikeType.BOTH_SIDES))
				.list();
		assertEquals("SELECT * FROM User WHERE name LIKE '%b%'", select.getSql());
		System.out.println(select.getSql());
	}
	
	@Test
	public void testClauseGt() {
		Select select = Select.all()
				.from("User")
				.where(Clause.gt("age", 20))
				.list();
		assertEquals("SELECT * FROM User WHERE age > 20", select.getSql());
		System.out.println(select.getSql());
	}
	
	@Test
	public void testClauseGe() {
		Select select = Select.all()
				.from("User")
				.where(Clause.ge("age", 20))
				.list();
		assertEquals("SELECT * FROM User WHERE age >= 20", select.getSql());
		System.out.println(select.getSql());
	}
	
	@Test
	public void testClauseLt() {
		Select select = Select.all()
				.from("User")
				.where(Clause.lt("age", 40))
				.list();
		assertEquals("SELECT * FROM User WHERE age < 40", select.getSql());
		System.out.println(select.getSql());
	}
	
	@Test
	public void testClauseLe() {
		Select select = Select.all()
				.from("User")
				.where(Clause.le("age", 40))
				.list();
		assertEquals("SELECT * FROM User WHERE age <= 40", select.getSql());
		System.out.println(select.getSql());
	}
	
	@Test
	public void testClauseIn() {
		Select select = Select.all()
				.from("User")
				.where(Clause.in("name", "Bruno", "Henrique", "Oliveira"))
				.list();
		assertEquals("SELECT * FROM User WHERE name IN ('Bruno','Henrique','Oliveira')", select.getSql());
		System.out.println(select.getSql());
	}
	
	@Test
	public void testClauseBetween() {
		Select select = Select.all()
				.from("User")
				.where(Clause.between("name", "A", "B"))
				.list();
		assertEquals("SELECT * FROM User WHERE name BETWEEN 'A' AND 'B'", select.getSql());
		System.out.println(select.getSql());
	}
	
	@Test
	public void testClauseIsNull() {
		Select select = Select.all()
				.from("User")
				.where(Clause.isNull("name"))
				.list();
		assertEquals("SELECT * FROM User WHERE name IS NULL", select.getSql());
		System.out.println(select.getSql());
	}
	
	@Test
	public void testClauseIsNotNull() {
		Select select = Select.all()
				.from("User")
				.where(Clause.isNotNull("name"))
				.list();
		assertEquals("SELECT * FROM User WHERE name IS NOT NULL", select.getSql());
		System.out.println(select.getSql());
	}
	
	@Test
	public void testDistinct() {
		Select select = Select.all()
				.from("User")
				.where(Clause.like("name", "B", LikeType.RIGHT_SIDE))
				.distinct()
				.list();
		assertEquals("SELECT DISTINCT * FROM User WHERE name LIKE 'B%'", select.getSql());
		System.out.println(select.getSql());
	}
	
	@Test
	public void testLimit() {
		Select select = Select.all()
				.from("User")
				.where(Clause.like("name", "B", LikeType.RIGHT_SIDE))
				.limit(10)
				.list();
		assertEquals("SELECT * FROM User WHERE name LIKE 'B%' LIMIT 10", select.getSql());
		System.out.println(select.getSql());
	}
	
	@Test
	public void testOrderAsc() {
		Select select = Select.all()
				.from("User")
				.orderBy(Order.asc("name"))
				.list();
		assertEquals("SELECT * FROM User ORDER BY name", select.getSql());
		System.out.println(select.getSql());
	}
	
	@Test
	public void testOrderDesc() {
		Select select = Select.all()
				.from("User")
				.orderBy(Order.desc("name"))
				.list();
		assertEquals("SELECT * FROM User ORDER BY name DESC", select.getSql());
		System.out.println(select.getSql());
	}
	
	@Test
	public void testUnique() {
		Select select = Select.all()
				.from("User")
				.unique();
		assertEquals("SELECT * FROM User LIMIT 1", select.getSql());
		System.out.println(select.getSql());
	}
	
	@Test
	public void testList() {
		Select select = Select.all()
				.from("User")
				.list();
		assertEquals("SELECT * FROM User", select.getSql());
		System.out.println(select.getSql());
	}
	
	@Test
	public void testColumns() {
		Select select = Select.columns("name", "age")
				.from("User")
				.list();
		assertEquals("SELECT name,age FROM User", select.getSql());
		System.out.println(select.getSql());
	}
	
	@Test
	public void testColumnsWithJoin() {
		Select select = Select.columns("u.name", "o.price")
				.from("User", "u")
				.join("Orders", "id", "user_id", "o")
				.list();
		assertEquals("SELECT u.name,o.price FROM User u INNER JOIN Orders o ON u.id = o.user_id", select.getSql());
		System.out.println(select.getSql());
	}
	
	@Test
	public void testOr() {
		Or or = Select.or(Clause.like("name", "A", LikeType.RIGHT_SIDE),
				Clause.like("name", "B", LikeType.RIGHT_SIDE));
		Select select = Select.all()
				.from("User")
				.where(or)
				.list();
		assertEquals("SELECT * FROM User WHERE name LIKE 'A%' OR name LIKE 'B%'", select.getSql());
		System.out.println(select.getSql());
	}
	
	@Test
	public void testJoin() {
		Select select = Select.all()
				.from("User", "u")
				.join("Orders", "id", "user_id", "o")
				.where(Clause.eq("u.name", "Bruno"))
				.where(Clause.eq("o.price", 100))
				.list();
		assertEquals("SELECT * FROM User u INNER JOIN Orders o ON u.id = o.user_id WHERE u.name = 'Bruno' AND o.price = 100", select.getSql());
		System.out.println(select.getSql());
	}

}