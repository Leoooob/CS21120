package uk.ac.aber.dcs.leh28.cs21120.assignment1.Testing;

import org.junit.*;

import java.util.ArrayList;

import static org.junit.Assert.*;
import uk.ac.aber.dcs.leh28.cs21120.assignment1.BubbleElimination;
import uk.ac.aber.dcs.leh28.cs21120.assignment1.MyQueue;

public class BubbleEliminationTest{
	private BubbleElimination myBracket;

	@SuppressWarnings("unused")	//suppresses the unused error on pQueue and a

	@Before	//before each test this setup is performed, making each test discrete
	public void setEntrantList() {
		myBracket = new BubbleElimination();

		MyQueue pQueue = new MyQueue();
		ArrayList<Object> a = new ArrayList<Object>();
		ArrayList<String> myCompetitors= new ArrayList<String>();

		myCompetitors.add("Leon");
		myCompetitors.add("Ali");
		myCompetitors.add("Jug");
		myCompetitors.add("Pan");
		
		myBracket.setPlayers(myCompetitors);
	}

	@Test
	public void testIsThereAMatch() {
		assertEquals("There should be a match ready to happen", true, myBracket.hasNextMatch());
	}

	@Test
	public void testLeonAliMatch() {
		assertEquals("The 1st player should be Leon", "Leon", myBracket.nextMatch().getPlayer1());
	}

	@Test
	public void testAliLeonMatch(){
		assertEquals("The 2rd player should be Ali", "Ali", myBracket.nextMatch().getPlayer2());
	}

	@Test
	public void testIsThereAnotherMatch(){
		myBracket.nextMatch();
		myBracket.setMatchWinner(true);
		assertEquals("There is another match to play", true, myBracket.hasNextMatch());
	}

	@Test
	public void testIsThereAWinnerYet() {
		myBracket.nextMatch();
		myBracket.setMatchWinner(true);
		assertEquals("No one is the winner, yet!", null, myBracket.getPosition(0));
	}

	@Test
	public void testIsThereARunnerUpYet() {
		myBracket.nextMatch();
		myBracket.setMatchWinner(true);
		assertEquals("No one is the winner, yet!", null, myBracket.getPosition(1));
	}

	@Test
	public void testIfAliLostDoesHePlayNext() {
		myBracket.nextMatch();
		myBracket.setMatchWinner(true);
		myBracket.nextMatch();
		assertEquals("Ali lost, therefore should play again", "Ali", myBracket.nextMatch().getPlayer1());
	}

	@Test
	public void testIfAliLostDoesHePlayJugNext() {
		myBracket.nextMatch();
		myBracket.setMatchWinner(true);
		myBracket.nextMatch();
		assertEquals("Ali lost, therefore should play Jug next", "Jug", myBracket.nextMatch().getPlayer2());
	}

	@Test
	public void testDoesTheTournamentEnd() {
		myBracket.nextMatch();
		myBracket.setMatchWinner(true);
		myBracket.nextMatch();
		myBracket.setMatchWinner(true);
		myBracket.nextMatch();
		myBracket.setMatchWinner(true);
		assertEquals("Play all matches, there should be none left", false, myBracket.hasNextMatch());
	}

	@Test
	public void testCanYouWinTheTournament() {
		myBracket.nextMatch();
		myBracket.setMatchWinner(true);
		myBracket.nextMatch();
		myBracket.setMatchWinner(true);
		myBracket.nextMatch();
		myBracket.setMatchWinner(true);
		assertEquals("Tournament winner is Jug", "Jug", myBracket.getPosition(0));
	}

	@Test
	public void testisThereARunnerUp() {
		myBracket.nextMatch();
		myBracket.setMatchWinner(true);
		myBracket.nextMatch();
		myBracket.setMatchWinner(true);
		myBracket.nextMatch();
		myBracket.setMatchWinner(true);
		assertEquals("The runner-up is ", "Pan", myBracket.getPosition(1));
	}

}
