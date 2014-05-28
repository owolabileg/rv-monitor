package com.runtimeverification.rvmonitor.logicrepository.ere;

import com.runtimeverification.rvmonitor.logicrepository.plugins.ere.Empty;
import com.runtimeverification.rvmonitor.logicrepository.plugins.ere.Epsilon;
import com.runtimeverification.rvmonitor.logicrepository.plugins.ere.ERE;
import com.runtimeverification.rvmonitor.logicrepository.plugins.ere.EREType;
import com.runtimeverification.rvmonitor.logicrepository.plugins.ere.Negation;
import com.runtimeverification.rvmonitor.logicrepository.plugins.ere.Symbol;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test the Negation ERE operator.
 * @author A. Cody Schuffelen
 */
public class NegationTest {
	
	/**
	 * Test that negations constructed from the same elements are equal.
	 */
	@Test
	public void testEquality() {
		Symbol symOne = Symbol.get("one");
		
		ERE one = Negation.get(symOne);
		ERE one_again = Negation.get(symOne);
		
		assertEquals(one, one_again);
		assertEquals(0, one.compareTo(one_again));
	}
	
	/**
	 * Test that negations from different elements are inequal.
	 */
	@Test
	public void testInequality() {
		Symbol symbol = Symbol.get("one");
		Epsilon epsilon = Epsilon.get();
		
		ERE one = Negation.get(symbol);
		ERE two = Negation.get(epsilon);
		
		assertFalse(one.equals(two));
		assertFalse(0 == one.compareTo(two));
	}
	
	/**
	 * Test that double negations are simplified into nothing.
	 */
	@Test
	public void testSimplification() {
		Symbol symbol = Symbol.get("one");
		
		ERE negation = Negation.get(symbol);
		ERE doubleNegation = Negation.get(negation);
		
		assertEquals(symbol, doubleNegation);
	}
	
	/**
	 * Test that negations contain epsilons if and only if the negated member doesn't.
	 */
	@Test
	public void testContainsEpsilon() {
		Empty empty = Empty.get();
		Epsilon epsilon = Epsilon.get();
		
		ERE negateEmpty = Negation.get(empty);
		ERE negateEpsilon = Negation.get(epsilon);
		
		assertTrue(negateEmpty.containsEpsilon());
		assertFalse(negateEpsilon.containsEpsilon());
	}
	
	/**
	 * Test that the element inside the negation gets derived.
	 */
	@Test
	public void testDerive() {
		Empty empty = Empty.get();
		Epsilon epsilon = Epsilon.get();
		
		ERE negateEmpty = Negation.get(empty);
		ERE negateEpsilon = Negation.get(epsilon);
		
		ERE derived = negateEpsilon.derive(Symbol.get("test"));
		
		assertEquals(negateEmpty, derived);
	}
	
	/**
	 * Test that negations copy and are equal.
	 */
	@Test
	public void testCopy() {
		Empty empty = Empty.get();
		
		ERE negation = Negation.get(empty);
		ERE copy = negation.copy();
		assertEquals(negation, copy);
	}
	
	/**
	 * Test that negations return the correct ERE type.
	 */
	@Test
	public void testEREType() {
		Epsilon epsilon = Epsilon.get();
		
		ERE negation = Negation.get(epsilon);
		assertEquals(EREType.NEG, negation.getEREType());
	}
	
	/**
	 * Test that negations convert to strings containing their child members.
	 */
	@Test
	public void testString() {
		Epsilon epsilon = Epsilon.get();
		
		ERE negation = Negation.get(epsilon);
		assertEquals("~(epsilon)", negation.toString());
	}
}