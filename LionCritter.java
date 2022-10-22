// A class to represent a Lion. Lions are "L"s that randomly  
// alternate between red, green, and blue. The color chosen is used for 
// three moves, then another color is randomly chosen, then it repeats.
// of them, and turn left in all other cases.

import java.util.*;
import java.awt.*;

public class Lion extends Critter {
    private Random rand = new Random();
    private Color color;
    private int count;
    
    // Constructs a Lion
    public Lion() {
        count = 0;
        color = randomColor();
    }
    
    // Returns chosen color 
    public Color getColor() {
        return this.color; 
    }           
   
    // Returns "L"
    public String toString() {
        return "L";
    }
   
    // Chooses random color to use for three moves, then
    // repeats with another chosen color.
    // Returns INFECT if enemy is in front
    // Returns LEFT is wall is in front or to the RIGHT
    // Returns RIGHT if critter in front is same species
    // Returns HOP in all other cases
    // CritterInfo info: object used to retreive information
    // about critter surroundings
    public Action getMove(CritterInfo info) {
        count++;
        if (count % 3 == 0) {
            this.color = randomColor();
        }
        if (info.getFront() == Neighbor.OTHER) {
            return Action.INFECT;
        } else if (info.getFront() == Neighbor.WALL || info.getRight() == Neighbor.WALL) {
            return Action.LEFT;
        } else if (info.getFront() == Neighbor.SAME) {
            return Action.RIGHT;
        } else {
            return Action.HOP;
        }
    }

    // Randomly chooses a color
    // Returns RED if random number is 0
    // Returns BLUE if number is 1
    // Returns GREEN if number is 2
    public Color randomColor() {
        int num = rand.nextInt(3);       
        if (num == 0) {
            return Color.RED;
        } else if (num == 1) {
            return Color.BLUE;
        } else {
            return Color.GREEN;
        }                 
    }
}
