
import java.util.ArrayList;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Bruger
 */
public class Player     
{
    private Room currentRoom;
    private Room lastRoom;
    
    private List<Item> itemsHeld;
    
    private List<Room> PreviouslyVisitedRooms;
    
    public Player() 
    {
        itemsHeld = new ArrayList<>();
        PreviouslyVisitedRooms = new ArrayList<>();
    }

    
    public void setCurrentRoom(Room currentRoom) 
    {
        this.currentRoom = currentRoom;
    }

    public void setLastRoom(Room lastRoom) 
    {
        this.lastRoom = lastRoom;
    }

    public Room getCurrentRoom() 
    {
        return currentRoom;
    }
    
    public Room getLastRoom() 
    {
        return lastRoom;
    }

    public List<Room> getPreviouslyVisitedRooms() 
    {
        return PreviouslyVisitedRooms;
    }

    public List<Item> getItemsHeld() {
        return itemsHeld;
    }
    
    public void pickUpItem(Item item)
    {
        itemsHeld.add(item);
    }
    
    public Item getItem(String itemString)
    {
        Item itemOb = null;
        
        for (int i = 0; i < itemsHeld.size(); i++) 
        {
            if(itemsHeld.get(i).getName().equals(itemString)) 
            {
                itemOb = itemsHeld.get(i);
            }
        }
        return itemOb;
    }
    
    public void removeItem(String item)
    {
        for (int i = 0; i < itemsHeld.size(); i++) 
        {
            if(itemsHeld.get(i).getName().equals(item)) 
            {
                itemsHeld.remove(i);
                
            }
        }    
    }
    
    public void dropItem(Item itemToDrop)
    {
        for (int i = 0; i < itemsHeld.size(); i++)
        {
            if (itemsHeld.get(i).equals(itemToDrop)) 
            {
                itemsHeld.remove(i);
                break;
            }
            System.out.println("No such item");
            return;
        }
        System.out.println("Item dropped");
    }
}
