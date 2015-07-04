package uk.ac.aber.dcs.leh28.cs21120.assignment1.Testing;

import org.junit.*;

import java.util.ArrayList;

import static org.junit.Assert.*;
import uk.ac.aber.dcs.leh28.cs21120.assignment1.DoubleElimination;

public class DoubleEliminationTest{
	private DoubleElimination myBracket;

	@Before
	public void setEntrantList() {
		myBracket = new DoubleElimination();
		ArrayList<String> myCompetitors = new ArrayList<String>();
		myCompetitors.add("Leon");
		myCompetitors.add("Ali");
		myCompetitors.add("Jug");
		myCompetitors.add("Pan");
		myCompetitors.add("Pot");
		myCompetitors.add("Bowl");
		
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
	public void testAliLeonMatch() {
		assertEquals("The 2rd player should be Ali", "Ali", myBracket.nextMatch().getPlayer2());
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
		myBracket.nextMatch();
		myBracket.setMatchWinner(true);
		assertEquals("No one is the winner, yet!", null, myBracket.getPosition(1));
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
		myBracket.nextMatch();
		myBracket.setMatchWinner(true);
		myBracket.nextMatch();
		myBracket.setMatchWinner(true);
		assertEquals("Leon wins, therefore should play again", "Leon", myBracket.nextMatch().getPlayer1());
	}

	@Test
	public void testIfAliLosesDoesHeChangeBracket() {
		myBracket.nextMatch();
		myBracket.setMatchWinner(true);
		myBracket.nextMatch();
		myBracket.setMatchWinner(true);
		myBracket.nextMatch();
		assertEquals("Ali is now in the losers bracket", "Ali", myBracket.nextMatch().getPlayer1());
	}

	@Test
	public void testDoesAliPlayPan() {
		myBracket.nextMatch();
		myBracket.setMatchWinner(true);
		myBracket.nextMatch();
		myBracket.setMatchWinner(true);
		myBracket.nextMatch();
		assertEquals("Ali's opponent in this round is Pan", "Pan", myBracket.nextMatch().getPlayer2());
	}

	@Test
	public void testDoesTheTournamentEnd() {
		myBracket.nextMatch();
		myBracket.setMatchWinner(true);
		myBracket.nextMatch();
		myBracket.setMatchWinner(true);
		myBracket.nextMatch();
		myBracket.setMatchWinner(true);
		myBracket.nextMatch();
		myBracket.setMatchWinner(true);
		myBracket.nextMatch();
		myBracket.setMatchWinner(true);
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
		myBracket.nextMatch();
		myBracket.setMatchWinner(true);
		myBracket.nextMatch();
		myBracket.setMatchWinner(true);
		myBracket.nextMatch();
		myBracket.setMatchWinner(true);
		myBracket.nextMatch();
		myBracket.setMatchWinner(true);
		myBracket.nextMatch();
		myBracket.setMatchWinner(true);
		assertEquals("Tournament winner is Pot", "Pot", myBracket.getPosition(0));
	}

}
