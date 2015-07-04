package uk.ac.aber.dcs.leh28.cs21120.assignment1;

import java.util.ArrayList;

import uk.ac.aber.dcs.bpt.cs21120.assignment1.IManager;
import uk.ac.aber.dcs.bpt.cs21120.assignment1.Match;
import uk.ac.aber.dcs.bpt.cs21120.assignment1.NoNextMatchException;

public class DoubleElimination implements IManager {
	Boolean areWeWinners = true;
	MyQueue lQueue = new MyQueue();
	MyQueue wQueue = new MyQueue();
	ArrayList<String> myCompetitors;
	String player1, player2, matchWinner, matchLoser;

	public void setPlayers(ArrayList<String> players) {
		this.myCompetitors = players;

		System.out.println(myCompetitors);
		for (String c: myCompetitors) {
			wQueue.addToQueue(c);
		}
		System.out.println(wQueue.lengthOfQueue());
		
		if ( (wQueue.lengthOfQueue() % 2) != 0 ) {
			System.out.println("WARNING: You do not have an even number of entrants!\n");
		}
	}

	public boolean hasNextMatch() {
		if (wQueue.lengthOfQueue() > 0 | lQueue.lengthOfQueue() > 0) {
			if (wQueue.lengthOfQueue() == 1 && lQueue.lengthOfQueue() == 1) {
				return false;
			}
			else {
				return true;
			}
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
			if (wQueue.lengthOfQueue() == 1 && lQueue.lengthOfQueue() == 1) {	//if (wQueue.lengthOfQueue() == 2 && lQueue.lengthOfQueue() == 0)
				player1 = wQueue.frontOfQueue().toString();
				wQueue.takeFromQueue();

				player2 = lQueue.frontOfQueue().toString();
				lQueue.takeFromQueue();

				areWeWinners = null;
				
				System.out.println("\nFinals:");
				Match m = new Match(player1,player2);
				return m;
			}
			else if (wQueue.lengthOfQueue() > lQueue.lengthOfQueue()) {
				player1 = wQueue.frontOfQueue().toString();
				wQueue.takeFromQueue();

				player2 = wQueue.frontOfQueue().toString();
				wQueue.takeFromQueue();

				areWeWinners = true;
				
				System.out.println("\nWinners bracket:");
				Match m = new Match(player1,player2);
				return m;
			}
			else if (lQueue.lengthOfQueue() != 1) {
				//if this is NOT the last person in the losers queue
				player1 = lQueue.frontOfQueue().toString();
				lQueue.takeFromQueue();

				player2 = lQueue.frontOfQueue().toString();
				lQueue.takeFromQueue();

				areWeWinners = false;
				System.out.println("\nLosers bracket:");
				Match m = new Match(player1,player2);
				return m;
			}
			else {
				player1 = lQueue.frontOfQueue().toString();
				lQueue.takeFromQueue();
				throw new NoNextMatchException("End of losers bracket");
			}
		}
	}

	public void setMatchWinner(boolean player1) {
		if (player1) {
			matchWinner = this.player1;
			matchLoser = this.player2;

			if (areWeWinners) {	//if this IS the winners bracket
				wQueue.addToQueue(this.player1);
				lQueue.addToQueue(this.player2);
			}
			else {	//if this is NOT the winners bracket
				if (lQueue.lengthOfQueue() == 0) {
					return;
				}
				lQueue.addToQueue(this.player1);
			}
			//printQueueLengths();
		}
			//check which queue they are in and addToQueue the winner of that
		else {
			matchWinner = this.player2;
			matchLoser = this.player1;

			if (areWeWinners) {
				wQueue.addToQueue(this.player2);
				lQueue.addToQueue(this.player1);
			}
			else {
				if (lQueue.lengthOfQueue() == 0) {
					return;
				}
				lQueue.addToQueue(this.player2);
			}
			//printQueueLengths();
		}
	}

	/*public void printQueueLengths() {
		int winnerl = wQueue.lengthOfQueue(); int loserl = lQueue.lengthOfQueue();
		System.out.println("\n\nwinner length: " + winnerl);
		System.out.println("loser length: " + loserl + "\n");
	}*/

	public String getPosition(int n) {
		if(hasNextMatch()) {	//if there is another match, do not set winner or loser
			return null;
		}
		else if (n == 0) {
			return matchWinner;
		}
		else if (n == 1){
			return matchLoser;
		}
		else {
			return "N/A";	//if this appears in double elim, something went very wrong
		}
	}

}
