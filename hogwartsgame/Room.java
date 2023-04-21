
package hogwartsgame;

// Room class represents a room in Hogwarts
public class Room {
    private String description; // description of the room
    private int number; // room number
    private double occupiedChance; // chance of room being occupied by someone
    private double itemChance; // chance of room having an item

    // constructor
    public Room(String description, int number, double occupiedChance, double itemChance) {
        this.description = description;
        this.number = number;
        this.occupiedChance = occupiedChance;
        this.itemChance = itemChance;
    }

    // getters
    public String getDescription() {
        return description;
    }

    public int getNumber() {
        return number;
    }

    public double getOccupiedChance() {
        return occupiedChance;
    }

    public double getItemChance() {
        return itemChance;
    }
}


