package hogwartsgame;

import java.util.List;
import hogwartsgame.Player;

// HogwartsGame class represents the main game logic
public class HogwartsGame {
    private Player player; // player object
    private List<Room> rooms; // list of rooms in Hogwarts
    private List<Item> items; // list of items in Hogwarts

    // constructor
    public HogwartsGame() {
        // load rooms and items from .csv file

        // create player object and start in the main hall
        player = new Player(/* main hall room object */);
    }

    // method to run the game
    public void run() {
        // display welcome message and main hall description
        
        while (/* game is not over */) {
            // display move options to the player
            // read player input and move player to the selected room
            
            // check if the room is occupied and/or has an item
            // display appropriate message and give player option to interact
            
            // check for random room chance and move player to a random room if necessary
        }
        
        // display end game message
    }
}
