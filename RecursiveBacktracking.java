import java.util.*;

public class RecursiveBacktracking {
    Stack<Node> stack = new Stack<>();
    int w;
    int h;
    int numVisited;

    /*
    2d-array of Nodes for storing the maze
     */
    Node[][] maze;

    public RecursiveBacktracking(int w, int h){
        this.w = w;
        this.h = h;
        maze = new Node[w][h];
        /*
        Fill the maze with nodes
         */
        for(int i = 0; i < w; i++){
            for(int j = 0; j < h; j++){
                maze[i][j] = new Node(i, j);
            }
        }
        /*
        Start position (0, 0)
         */
        stack.push(maze[0][0]);
        maze[0][0].setVisited();
        numVisited = 1;
    }

    public void genMaze()  {
        Random rand = new Random();
        while(numVisited < w*h){
            // Create list of unvisited neighbours
            ArrayList<Node> neighbours = new ArrayList<>();
            //Look North
            if(stack.peek().y > 0) {
                if (!(maze[(stack.peek().x)][(stack.peek().y - 1)].isVisited())) {
                    neighbours.add(maze[stack.peek().x][stack.peek().y - 1]);
                }
            }
            // Look East
            if(stack.peek().x < w-1) {
                if (!(maze[(stack.peek().x + 1)][(stack.peek().y)].isVisited())) {
                    neighbours.add(maze[(stack.peek().x + 1)][stack.peek().y]);
                }
            }
            // Look South
            if(stack.peek().y < h-1) {
                if (!(maze[(stack.peek().x)][(stack.peek().y + 1)].isVisited())) {
                    neighbours.add(maze[stack.peek().x][stack.peek().y + 1]);
                }
            }
            // Look West
            if(stack.peek().x > 0) {
                if (!(maze[(stack.peek().x - 1)][(stack.peek().y)].isVisited())) {
                    neighbours.add(maze[(stack.peek().x - 1)][stack.peek().y]);
                }
            }

            if(!neighbours.isEmpty()){
                //Random neighbour
                Node next = neighbours.get(rand.nextInt(neighbours.size()));

                /*
                Set path between neighbour and current node
                 */

                //Passage North
                int x = stack.peek().x;
                int y = stack.peek().y;
                if(stack.peek().y > next.y){
                    maze[x][y].addPassage(1);
                    maze[next.x][next.y].addPassage(4);
                }
                //Passage East
                if(stack.peek().x > next.x){
                    maze[x][y].addPassage(8);
                    maze[next.x][next.y].addPassage(2);
                }
                //Passage South
                if(stack.peek().y < next.y){
                    maze[x][y].addPassage(4);
                    maze[next.x][next.y].addPassage(1);
                }
                //Passage West
                if(stack.peek().x < next.x){
                    maze[x][y].addPassage(2);
                    maze[next.x][next.y].addPassage(8);
                }
                // New position
                stack.push(next);
                maze[next.x][next.y].setVisited();
                numVisited++;

                /*
                If we do not have any unvisited neighbours, backtrack.
                 */
            } else {
                stack.pop();
            }
        }
    }

    public void graphMaze() {
        DrawMaze dm = new DrawMaze(w, h, maze);
        dm.showMaze();
    }

    public static void main(String[] args)  {
        int width = 20;
        int height = 20;
        RecursiveBacktracking rb = new RecursiveBacktracking(width, height);
        rb.genMaze();
        rb.graphMaze();
    }

}

