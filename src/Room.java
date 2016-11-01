
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 * Class Room - a room in an adventure game.
 *
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 *
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  The exits are labelled north, 
 * east, south, west.  For each direction, the room stores a reference
 * to the neighboring room, or null if there is no exit in that direction.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2011.07.31
 */
public class Room 
{
    private String description;
    private HashMap<String, Room> exits;
    private List<Item> itemsList;
    
    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room(String description, Item... items) 
    {
        itemsList = new ArrayList<>();
        for (Item item : items) 
        {
           addItemInRoom(item);
        }
        
        this.description = description;
        exits = new HashMap<String, Room>();        
    }
    
    public void addItemInRoom(Item item)
    {
        itemsList.add(item);
    }
    public String getItemsInRoomAsString()
    {
        return "placeholder text lul";
    }
    /**
     * Define the exits of this room.  Every direction either leads
     * to another room or is null (no exit there).
     * @param north The north exit.
     * @param east The east east.
     * @param south The south exit.
     * @param west The west exit.
     */
    public void setExits(String direction, Room neighbour) 
    {
        exits.put(direction, neighbour);
    }
    
    public Room getExit(String direction)
    {
        return exits.get(direction);
    }
    
    /**
     * Return a description of the room's exits.
     * for example: "Exits: north west".
     * @return A descritpion of the available exits 
     */
    public String getExitString()
    {
        String exitString = "Exits: ";
        Set<String> keys = exits.keySet();
        
        for(String exit : keys) 
        {
            exitString += " " + exit;
        }
        return exitString;
    }
    /**
     * @return The description of the room.
     */
    public String getDescription()
    {
        return description;
    }
    
    /**
     * Return a long description of  this room, of the form:
     *      You are in the kitchen.
     *      Exits: north west.
     * @return A description of the room, including exits haHAA.
     */
    public String getLongDescription()
    {
        return "You are " + description + ". \n" + getExitString();
    }
}
