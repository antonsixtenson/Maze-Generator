import java.awt.*;
import javax.swing.*;

public class DrawMaze extends JPanel  {
    Node[][] maze;
    int width;
    int x;
    int height;
    int y;

    public DrawMaze(int width, int height, Node[][] maze){
        this.maze = maze;
        this.width = width*30;
        this.x = width;
        this.height = height*30;
        this.y = height;
    }

    public void paintComponent(Graphics g){
        /*
        Outer walls
         */
        for(int i = 0; i < width; i++){
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, width+10, 10);
            g.fillRect(0, height, width+10, 10);
        }
        for(int i = 0; i < height; i++){
            g.setColor(Color.BLACK);
            g.fillRect(width, 0, 10, height+10);
            g.fillRect(0, 0, 10, height+10);
        }
        /*
        All nodes/cells & their walls
         */
        for(int i = 0; i < x; i++){
            for(int j = 0; j < y; j++){
                if (maze[i][j].isVisited()) {
                    g.setColor(Color.CYAN);
                } else {
                    g.setColor(Color.YELLOW);
                }
                g.fillRect((maze[i][j].x)*30+5, (maze[i][j].y)*30+5, 30, 30);
                if(maze[i][j].hasSouthWall()){
                    int x = maze[i][j].x*30;
                    int y = (maze[i][j].y)*30+30;
                    g.setColor(Color.BLACK);
                    g.fillRect(x, y, 35, 5);
                }
                if(maze[i][j].hasEastWall()){
                    int x = (maze[i][j].x)*30+30;
                    int y = maze[i][j].y*30;
                    g.setColor(Color.BLACK);
                    g.fillRect(x, y, 5, 35);
                }
            }
        }
    }

    public void showMaze() {
        JFrame f = new JFrame("Sixtensons Maze Visualizer");
        f.add(this);
        f.setSize(width, height);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }

}