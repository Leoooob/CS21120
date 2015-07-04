package uk.ac.aber.dcs.leh28.cs21120.assignment1.Testing;

import org.junit.*;
import java.util.ArrayList;

import static org.junit.Assert.*;
import uk.ac.aber.dcs.leh28.cs21120.assignment1.SingleElimination;

public class SingleEliminationTest{
	private SingleElimination myBracket;

	@Before	//before each test this setup is performed, making each test discrete
	public void setEntrantList() {
		myBracket = new SingleElimination();
		ArrayList<String> myCompetitors = new ArrayList<String>();
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
	public void testDidLeonWin(){
		myBracket.nextMatch();
		myBracket.setMatchWinner(true);
		assertEquals("The winner of the played match was Leon", "Leon", myBracket.getPosition(0));
	}

	@Test
	public void testIsThereAnotherMatch(){
		myBracket.nextMatch();
		myBracket.setMatchWinner(true);
		assertEquals("There is another match to play", true, myBracket.hasNextMatch());
	}

	@Test
	public void testIfLeonWinsDoesHePlayAgain() {
		myBracket.nextMatch();
		myBracket.setMatchWinner(true);
		myBracket.nextMatch();
		myBracket.setMatchWinner(true);
		assertEquals("Leon wins, therefore should play again", "Leon", myBracket.nextMatch().getPlayer1());
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
		assertEquals("Tournament winner is Leon", "Leon", myBracket.getPosition(0));
	}

	@Test
	public void testisThereARunnerUp() {
		myBracket.nextMatch();
		myBracket.setMatchWinner(true);
		assertEquals("No runner-up because this is Single Elim", "N/A", myBracket.getPosition(1));
	}

}
