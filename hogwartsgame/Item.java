package hogwartsgame;

// Item class represents an item in Hogwarts
public class Item {
    private String name; // name of the item
    private String description; // description of the item

    // constructor
    public Item(String name, String description) {
        this.name = name;
        this.description = description;
    }

    // getters
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
