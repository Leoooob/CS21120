package uk.ac.aber.dcs.leh28.cs21120.assignment1;

import java.util.ArrayList;

import uk.ac.aber.dcs.bpt.cs21120.assignment1.*;

public class BubbleElimination implements IManager{
	int i = -1, size = -1;
	boolean currentlyPlaying = false;
	String player1, player2, matchWinner, matchLoser;
	
	ArrayList<String> myCompetitors;
	MyQueue pQueue = new MyQueue();	//list of ALL players
	ArrayList<Object> a = new ArrayList<Object>();	//list of all CURRENT players

	public void setPlayers(ArrayList<String> players) {
		this.myCompetitors = players;

		for (String c: myCompetitors) {
			pQueue.addToQueue(c);
		}

		if (a.size() < 1) {
			a.add(pQueue.takeFromQueue());

			i++;
			size++;
		}
	}

	public boolean hasNextMatch() {
		if (pQueue.lengthOfQueue() > 1) {
			return true;
		}
		else {
			return false;
		}
	}

	//player 2 is newcomer, player 1 is parent
	public Match nextMatch() throws NoNextMatchException {
		if (currentlyPlaying && i != 0) {
			player1 = (String) a.get(i - 1);
		}
		else {
			a.add(pQueue.takeFromQueue());

			size++;
			i = size;
			currentlyPlaying = true;

			player2 = (String) a.get(size);
			player1 = (String) a.get(size - 1);
		}
		Match m = new Match(player1, player2);
		return m;
	}

	public void setMatchWinner(boolean player1won) {
		if (player1won) {
			currentlyPlaying = false;
			matchWinner = player1;
			matchLoser = player2;
		}
		else {
			currentlyPlaying = true;
			a.set(i - 1, player2);
			a.set(i, player1);

			i--;
			matchWinner = player1;
			matchLoser = player2;
		}
	}

	public String getPosition(int n) {
		if ( hasNextMatch() || currentlyPlaying ) {	//if there is another match, do not set winner or loser
			return null;
		}
		else if (n == 0) {
			return matchWinner;
		}
		else if (n == 1){
			return matchLoser;
		}
		else {
			return "N/A";
		}
	}

}