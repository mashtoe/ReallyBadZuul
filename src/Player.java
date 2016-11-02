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

    public Player() 
    {
        
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
    
}
