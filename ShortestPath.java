import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

//solution class
public class ShortestPath {
    
    //main function, gives test case and prints results
	 public static void main(String args[]){
		char[][] matrix = {
			{'S', '1', '1', 'D'},
			{'1', '1', '1', '1'},
			{'1', '1', '1', '1'},
			{'1', '1', '1', '1'}
		};
		
		Node dest = pathExists(matrix);
		ArrayList<String> path = findPath(dest, new ArrayList<String>());
		System.out.println(path);    
	}
    // main BFS function
	private static Node pathExists(char[][] matrix) {
		
		Node source = new Node(0, 0);
		Queue<Node> queue = new LinkedList<Node>();
		queue.add(source);

		int[][] visited = new int[matrix.length][matrix[0].length];
		
		while(!queue.isEmpty()) {
			Node poped = queue.poll();
			//move on if already visited
			if (visited[poped.x][poped.y] == 1){
				continue;
			}
			//mark visted
			visited[poped.x][poped.y] = 1;

			System.out.println("next round:");
			for (int[] li : visited){
				System.out.println(Arrays.toString(li));
			}
			
			
			if(matrix[poped.x][poped.y] == 'D' ) {
				return poped;
            }
            //if not the golden tile, add all permissable NSEW neighbors to queue
			else {
				
				List<Node> neighbourList = addNeighbours(poped, matrix, visited);
				queue.addAll(neighbourList);
			}
			
		}
		return new Node(0, 0);
	}
    
    //helper functions that looks NSEW and decides if those directions are passable (not walls) and not off the board
    //if they are permissable moves, add them to a neighbor list
	private static List<Node> addNeighbours(Node poped, char[][] matrix, int[][] visited) {
		
		List<Node> list = new LinkedList<Node>();
		
		if((poped.x-1 > 0 && poped.x-1 < matrix.length) && visited[poped.x-1][poped.y] != '1') {
			Node neighbor = new Node(poped.x-1, poped.y, poped);
            list.add(neighbor);
		}
		if((poped.x+1 > 0 && poped.x+1 < matrix.length) && visited[poped.x+1][poped.y] != '1') {
            Node neighbor = new Node(poped.x+1, poped.y, poped);
            list.add(neighbor);
		}
		if((poped.y-1 > 0 && poped.y-1 < matrix.length) && visited[poped.x][poped.y-1] != '1') {
            Node neighbor = new Node(poped.x, poped.y-1, poped);
            list.add(neighbor);
		}
		if((poped.y+1 > 0 && poped.y+1 < matrix.length) && visited[poped.x][poped.y+1] != '1') {
            Node neighbor = new Node(poped.x, poped.y+1, poped);
            list.add(neighbor);
		}		
		return list;
	}

	private static ArrayList<String> findPath(Node dest, ArrayList<String> directions){
		return directions;
	}
}



// Node class and constructor
//A constructor in Java is a special method that is used to initialize 
//objects. The constructor is called when an object of a class is created. 
//It can be used to set initial values for object attributes
class Node {
    int x;
    int y;
    Node prev;
    
    Node(int x, int y, Node prev) {
        this.x = x;
        this.y = y;
        this.prev = prev;
	}
	
	Node(int x, int y){
		this.x = x;
		this.y = y;
	}
}