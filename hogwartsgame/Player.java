package hogwartsgame;

// Player class represents the player in the game
public class Player {
    private Room currentRoom; // current room of the player

    // constructor
    public Player(Room currentRoom) {
        this.currentRoom = currentRoom;
    }

    // getters and setters
    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }
}
