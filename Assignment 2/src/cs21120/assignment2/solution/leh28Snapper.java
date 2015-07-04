/**
 * <h1>My ISnapper implementation!</h1> My implementation of the ISnapper
 * interface. It will allow the user to click on an image to set a seed node,
 * then it will find the shortest weighted path to where you drag the mouse
 * on the image.
 * <p>
 * What I found hardest about this assignment was wrapping my head around what
 * we were actually meant to do. Actually doing it was relatively straight
 * forward. I had issues with trying to create a separate function that would
 * check adjacent edges (called getEdges), I have included the code I wrote for
 * it, thought it does not work and is commented out at the bottom of the source
 * complete with javadoc comments of it's own.
 * <p>
 * Some of this code is derived from pseudocode found on our Blackboard forum,
 * and the pseudocode described in our assignment brief. Specifically the
 * inner-class Edge was heavily influenced by these examples.
 * 
 * @author Leon Hassan
 * @version 1.0
 * @since 2015-04-05
 */
package cs21120.assignment2.solution;

import java.awt.Point;
import java.util.LinkedList;
import java.util.concurrent.PriorityBlockingQueue;

import cs21120.assignment2.*;

public class leh28Snapper implements ISnapper {
	private int img_X;
	private int img_Y;
	boolean[][] visited;
	public Point[][] map;
	private FloatImage image[];
	private int point_X, point_Y;
	PriorityBlockingQueue<Edge> myEdges;
	int[] map_x = { 1, 1, 1, 0, -1, -1, -1, 0 };
	int[] map_y = { 1, 0, -1, -1, -1, 0, 1, 1 };

	/**
	 * Initialises the priority queue for the program
	 */
	public leh28Snapper() {
		myEdges = new PriorityBlockingQueue<Edge>();
	}

	/**
	 * mouse_click calls this method. It begins the map building process and
	 * sets initial data eg. the seed, weights between different nodes etc
	 * 
	 * @param x
	 *            The position of the seed point on the x-axis.
	 * @param y
	 *            The position of the seed point on the y-axis.
	 * @param img
	 *            The recording of the heigh, width and weight of the image.
	 */
	public void setSeed(int x, int y, FloatImage[] img) {
		img_X = img[0].getWidth();
		img_Y = img[0].getHeight();
		image = img;
		point_X = x;
		point_Y = y;
		map = new Point[img_X][img_Y];
		visited = new boolean[img_X][img_Y];
		for (int i = 0; i < 8; i++) {
			int tempX = point_X + map_x[i];
			int tempY = point_Y + map_y[i];
			int counter = 0;
			if (i % 2 != 0) {
				counter++;
			}
			if (tempX > 0 && tempY > 0) {
				if (tempX < img_X && tempY < img_Y) {
					Point start = new Point(point_X, point_Y);
					Point end = new Point(tempX, tempY);
					float weight = img[counter].get_nocheck(tempX, tempY);
					Edge e = new Edge(start, end, weight);
					myEdges.add(e);
				}
			}
		}

		visited[point_X][point_Y] = true;
		Thread myThread = new Thread(new Runnable() {
			public void run() {
				buildMap();
			}
		});
		myThread.start();
	}

	/**
	 * mouse_drag calls this method, and draws the path of nodes created in the
	 * priority queue (in setSeed). The return is a linkedlist containing the
	 * points on this path.
	 * 
	 * @param x
	 *            The position of the mouse on the x-axis
	 * @param y
	 *            The position of the mouse on the y-axis
	 * @return The shortest path from the seed to the current mouse position in
	 *         the form of a linkedlist of points.
	 */
	public LinkedList<Point> getPath(int x, int y) {
		if (x > img_X || y > img_Y) {
			return null;
		}
		if (x < 0 || y < 0) {
			return null;
		} else {
			Point p = new Point(x, y);
			LinkedList<Point> myPath = new LinkedList<Point>();
			while (p != null) {
				myPath.add(p);
				p = map[p.x][p.y];

			}
			return myPath;
		}
	}

	/**
	 * Builds the map, using the points stored in the priority queue
	 * (var:myEdges), built in setSeed
	 */
	private void buildMap() {
		while (!myEdges.isEmpty()) {
			Edge newEdge = myEdges.poll();
			if (!visited[newEdge.end.x][newEdge.end.y]) {
				visited[newEdge.end.x][newEdge.end.y] = true;
				map[newEdge.end.x][newEdge.end.y] = newEdge.start;
				for (int i = 0; i < 8; i++) {
					int tempX = newEdge.end.x + map_x[i];
					int tempY = newEdge.end.y + map_y[i];
					int counter = 0;
					if (i % 2 != 0) {
						counter++;
					}
					if (tempX < img_X && tempY < img_Y && tempX > 0
							&& tempY > 0) {
						if (newEdge.end.x < img_X && newEdge.end.y < img_Y
								&& newEdge.end.x > 0 && newEdge.end.y > 0) {
							Point start = new Point(newEdge.end.x,
									newEdge.end.y);
							Point end = new Point(tempX, tempY);
							float weight = image[counter].get_nocheck(tempX,
									tempY);
							Edge finalEdge = new Edge(start, end, weight);
							myEdges.add(finalEdge);
						}
					}
				}
			}
		}
	}

	/**
	 * An edge is two nodes on a graph, where each node is a pixel on the image.
	 * Each node has eight edges, to it's eight adjacent tiles, not all of them
	 * will be in bounds every time.
	 * 
	 * This class is based on the class found in the Simple Graph examples
	 */
	private class Edge implements Comparable<Object> {
		public Point start;
		public Point end;
		public float weight;

		/**
		 * Takes the initial data provided in the edge definition and assigns it
		 * to the relevant attributes within the class.
		 * 
		 * @param start
		 *            The origin node of the edge
		 * @param end
		 *            The end node of the edge
		 * @param weight
		 *            The weight of the edge
		 */
		public Edge(Point start, Point end, float weight) {
			this.start = start;
			this.end = end;
			this.weight = weight;
		}

		/**
		 * Compares the weights of two edges and returns a value that reflects
		 * the comparison: greater, lesser or equal
		 * 
		 * @param other
		 *            The compared edge
		 * @return int 
		 *            An integer representation of the difference between the
		 *            two edges
		 */
		public int compareTo(Object o) {
			Edge e = (Edge) o;
			if (e.weight > weight)
				return -1;
			else if (e.weight < weight)
				return 1;
			return 0;
		}
	}

	/**
	 * Grabs the edges from the seed to the current point before adding them to
	 * the priority queue
	 * 
	 * @param start
	 *            The seed for generating this edge
	 * @param totalWeight
	 *            The current weight of the path (up to this new edge being
	 *            generated)
	 */
	/*
	public void getEdges(Point start, float totalWeight) {
	Point dir[] = new Point[8];
	int dircount = 0, new_X, new_Y;
	while (dircount > 8) {
		for (int i = -1; i < 1; i++) {
			for (int j = -1; j < 1; j++) {
				newX = (start.x + i);
				newY = (start.y + j);
				dir[dircount] = new Point(new_X,new_Y);
			}
		}
	}

	int counter = 0;
	for (int k = 0; k > 8; k++) {
		if (k % 2 != 0) {
			counter++;
		}
		if (dir[counter].x > 0 && dir[counter].y > 0) {
			if (dir[counter].x < map[0].length && dir[counter].y < map[0].length) {
				Edge edge = new Edge(start, dir[counter], image[k].get_nocheck(dir[counter].x, dir[counter].y) + totalWeight);
				myEdges.add(edge);
			}
		}
	}
}*/

}