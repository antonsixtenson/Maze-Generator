public class Node {
    /*
    Node position
     */
    int x;
    int y;
    /*
    Integer to store data about or node in. If it has been visited, or where it has passages to
    adjacent nodes.
     */
    int data;

    public Node(int x, int y){
        this.x = x;
        this.y = y;
        data = 0;
    }

    /*
    Add passage:
    1st-bit set to 1 = have passage to north
    2nd-bit set to 1 = have passage east
    4th-bit set to 1 = have passage south
    8th-bit set to 1 = have passage west
    */

    public void addPassage(int dir){
        data = data | dir;
    }

    /*
    Set 16th-bit to 1
     */
    public void setVisited(){
        data = data | 16;
    }

    /*
    Check if 16th-bit is set to 1
     */
    public boolean isVisited(){
        return (data & 16) == 16;
    }

    /*
    Check if 4th-bit is set to 1
     */
    public boolean hasSouthWall(){
        return (data & 4) != 4;
    }

    /*
    Check if 2nd-bit is set to 1
     */
    public boolean hasEastWall(){
        return (data & 2) != 2;
    }

}
