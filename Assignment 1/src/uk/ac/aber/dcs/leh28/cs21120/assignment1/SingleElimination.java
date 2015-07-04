package uk.ac.aber.dcs.leh28.cs21120.assignment1;

import java.util.ArrayList;

import uk.ac.aber.dcs.bpt.cs21120.assignment1.*;

public class SingleElimination implements IManager {
	MyQueue pQueue = new MyQueue();
	ArrayList<String> myCompetitors;
	String player1, player2, matchWinner;
	
	public void setPlayers(ArrayList<String> players) {
		this.myCompetitors = players;

		for (String c: myCompetitors) {
			pQueue.addToQueue(c);
		}

		System.out.println(myCompetitors);
		System.out.println(pQueue.lengthOfQueue());
		
		if ( (pQueue.lengthOfQueue() % 2) != 0 ) {	//check the complete number of entrants
			System.out.println("WARNING: You do not have an even number of entrants!\n");
		}	//prints a warning if there is an odd number of entrants
	}

	public boolean hasNextMatch() {
		if (pQueue.lengthOfQueue() > 1) {
			return true;
		}
		else { 
			return false;
		}
	}

	public Match nextMatch() throws NoNextMatchException {
		if (!hasNextMatch()) {
			throw new NoNextMatchException("No Matches Remaining.");
		}

		else {
			player1 = pQueue.frontOfQueue().toString();
			pQueue.takeFromQueue();

			player2 = pQueue.frontOfQueue().toString();
			pQueue.takeFromQueue();

			Match m = new Match(player1,player2);
			return m;
		}
	}

	public void setMatchWinner(boolean player1) {
		if (player1) {
			pQueue.addToQueue(this.player1);
			matchWinner = this.player1;
			
		}
		else {
			pQueue.addToQueue(this.player2);
			matchWinner = player2;
		}
	}

	public String getPosition(int n) {
		if (n==0) {
			return matchWinner;
		}
		else {
			return "N/A";
		}
	
	}

}
