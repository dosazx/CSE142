// A class to represent a Husky. Huskies alternate between
// "^_^" and "@_@" with every move. They also change between 
// the colors white and purple with every move. The Husky
// hops if no one is in front of it, infects if an enemy is in 
// front or back of it. Otherwise, it turns left.

import java.util.*;
import java.awt.*;

public class Husky extends Critter {
    private int count;
    private Color color;
    
    // constructs a Husky
    public Husky() {
        this.count = 0;
        this.color = Color.WHITE;
    }
    
    // Returns the color white if it moves an even number of times,
    // otherwise it returns purple.
    public Color getColor() {
        if (count % 2 == 0) {
            return Color.WHITE;
        } else {
            return new Color(102, 0, 153);
        }
    }
    
    // Returns "^_^" if it moves an even number of times
    // Returns "@_@" otherwise.
    public String toString() {
        if (count % 2 == 0) {
            return "^_^";
        } else {
            return "@_@";
        }
    }
    
    // Returns HOP is no one is infront of Husky
    // Returns INFECT is enemy is in front or back of Husky
    // Returns left with all other cases
    // CritterInfo info: object used to retreive information
    // about critter surroundings
    public Action getMove(CritterInfo info) {
        count++;
        if (info.getFront() == Neighbor.EMPTY) {
            return Action.HOP;
        } else if (info.getFront() == Neighbor.OTHER) {
            return Action.INFECT;    
        } else if (info.getBack() == Neighbor.OTHER) {
            return Action.INFECT;
        } else {
            return Action.LEFT;
        }
    }
}
