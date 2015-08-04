package edu.gatech.seclass;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class MyStringTest {

	private MyString mystring;

	@Before
	public void setUp() throws Exception {
		mystring = new MyString();
	}

	@After
	public void tearDown() throws Exception {
		mystring = null;
	}

	@Test
	public void testGetVowels1() {
		mystring.setString("This is my string. It includes three numbers: 1 2 3");
		assertEquals("iiiIiueeeue", mystring.getVowels());
	}

	@Test
	public void testGetVowels2(){
		mystring.setString("Here's another string %*#(@_+");
		assertEquals("eeaoei", mystring.getVowels());
	}

	@Test
	public void testGetVowels3() {
		mystring.setString("AEfkdaljfdEOJPdsakfsd<DSDFSKE.($)@");
		assertEquals("AEaEOaE", mystring.getVowels());
	}

	@Test
	public void testNumberOfVowels1() {
		mystring.setString("yello");
		assertEquals(2, mystring.numberOfVowels());
	}

	@Test
	public void testNumberOfVowels2() {
		mystring.setString("");
		assertEquals(0, mystring.numberOfVowels());
	}

	@Test
	public void testNumberOfVowels3() {
		mystring.setString("Here's another string %*#(@_+");
		assertEquals(6, mystring.numberOfVowels());
	}

	@Test
	public void testGetCharacter1() {
		mystring.setString("This is my string. It includes three numbers: 1 2 3");
		assertTrue('n' == mystring.getCharacter(16));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testGetCharacter2() {
		mystring.setString("test string");
		mystring.getCharacter(-3);
	}

	@Test(expected = IllegalIndexException.class)
	public void testGetCharacter3() {
		mystring.setString("test string");
		mystring.getCharacter(99);
	}

	@Test(expected = IllegalIndexException.class)
	public void testFlipCaseInSubstring1() {
		mystring.setString("abcde");
		mystring.flipCaseInSubstring(1, 10);
	}

	@Test
	public void testFlipCaseInSubstring2() {
		mystring.setString("aBcDE");
		mystring.flipCaseInSubstring(3, 5);
		assertEquals("aBCde", mystring.getString());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testFlipCaseInSubstring3() {
		mystring.setString("abcde");
		mystring.flipCaseInSubstring(0, 1);
	}
}
