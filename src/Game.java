
import java.util.ArrayList;
import java.util.List;

/**
 *  This class is the main class of the "World of Zuul" application. 
 *  "World of Zuul" is a very simple, text based adventure game.  Users 
 *  can walk around some scenery. That's all. It should really be extended 
 *  to make it more interesting!
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2011.07.31
 */

public class Game 
{
    private Player player;
    private Parser parser;
    
    
    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        parser = new Parser();
        player = new Player();
        createRooms();
        
        
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
        Room one, two, three, four, five, cellar;
        
        
        // create the rooms
        one = new Room("in room one. The room to the west");
        two = new Room("in room two, the main room in the middle");
        three = new Room("in room three. The room to the north");
        four = new Room("in room four. The room to the south");
        five = new Room("in room five. The room to the east");
        cellar = new Room("stupid. hahaaa");
        
        
        // initialise room exits
        one.setExits("east", two);
        
        two.setExits("north", three);
        two.setExits("east", five);
        two.setExits("south", four);
        two.setExits("west", one);
        
        three.setExits("south", two);
        three.setExits("down", cellar);
        
        four.setExits("north", two);
        
        five.setExits("west", two);
        
        cellar.setExits("up", three);
        
        Item appleItem = new Item("Apple", "This is an apple", 2);
        Item Book = new Item("Book", "This is a book", 2);
        
        two.addItemInRoom(appleItem);
        two.addItemInRoom(Book);
        
        //currentRoom = one;  // start game outside
        player.setCurrentRoom(one);
        
                
    }

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.
                
        boolean finished = false;
        while (! finished) 
        {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
        System.out.println("Type 'help' if you need help.");
        System.out.println();
        
        //System.out.println(currentRoom.getLongDescription());
        System.out.println(player.getCurrentRoom().getLongDescription());
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        if(command.isUnknown()) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        String commandWord = command.getCommandWord();
        if (commandWord.equals("help")) {
            printHelp();
        }
        else if (commandWord.equals("go")) {
            goRoom(command);
        }
        else if (commandWord.equals("look")) 
        {
            look();
        }
        else if (commandWord.equals("helditems")) 
        {
            helditems();
        }
        else if (commandWord.equals("back")) 
        {
            back();
        }
        else if (commandWord.equals("pickup")) 
        {
            pickup(command);
        }
        else if (commandWord.equals("drop")) 
        {
            drop(command);
        }
        
        else if (commandWord.equals("quit")) {
            wantToQuit = quit(command);
        }
        

        return wantToQuit;
    }

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around in some unknown building.");
        System.out.println();
        System.out.println("Your command words are:");
        System.out.println(parser.getAllCommands());

    }
    /**
     * Method called when the player used the look command.
     * 
     */
    private void look()
    {
        System.out.println(player.getCurrentRoom().getLongDescription());
    }
    /**
     * Go to the room you were in prior to where you are now. 
     * The method can handle consecutive back commands all the way.
     */
    private void back()
    {
        if (player.getPreviouslyVisitedRooms().size() != 0) 
        {
            //currentRoom = PreviouslyVisitedRooms.get(PreviouslyVisitedRooms.size()-1);
            player.setCurrentRoom(player.getPreviouslyVisitedRooms().get(player.getPreviouslyVisitedRooms().size()-1));
            player.getPreviouslyVisitedRooms().remove(player.getPreviouslyVisitedRooms().size()-1);
            System.out.println(player.getCurrentRoom().getLongDescription());
        }
        else
        {
            System.out.println("Can't go back now.");
        }
        
    }
    /** 
     * Try to go in one direction. If there is an exit, enter
     * the new room, otherwise print an error message.
     */
    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) 
        {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = player.getCurrentRoom().getExit(direction);

        if (nextRoom == null) {
            System.out.println("There is no door!");
        }
        else {
            
            handleLastRoomStuff();  
            player.setCurrentRoom(nextRoom);
            
            System.out.println(player.getCurrentRoom().getLongDescription());
        }
    }
    
    private void pickup(Command command)
    {
        if (player.getCurrentRoom().getItemsList().size() > 0) 
        {
            if(!command.hasSecondWord()) 
            {
                // if there is no second word, we don't know what to pickup...
                System.out.println("Pick up what?");
                return;
            }
            String item = command.getSecondWord();
            Item itemObject = player.getCurrentRoom().getItem(item);
            if( itemObject == null) 
            {
                System.out.println("No such item in this room");
                return;
            }
            player.pickUpItem(itemObject);
            
            player.getCurrentRoom().removeItem(item);
            
            System.out.println("Succesfully picked up: " + item);           
        }
        else
        {
            System.out.println("No item in room to pick up");
        }        
    }
    private void drop(Command command)
    {
        if (player.getItemsHeld().size() > 0) 
        {
            if(!command.hasSecondWord()) 
            {
                // if there is no second word, we don't know what to pickup...
                System.out.println("Drop what?");
                return;
            }
            String item = command.getSecondWord();
            Item itemObject = player.getItem(item);
            if( itemObject == null) 
            {
                System.out.println("You are not holding a " + item);
                return;
            }
            player.getCurrentRoom().addItemInRoom(itemObject);
            player.removeItem(item);
            System.out.println("You dropped your " + item);
        }
        else
        {
            System.out.println("No item held to drop");
        }  
    }
    
    /**
     * Method called when you type eat in the console.
     * Prints "Eating some apples".
     */
    private void helditems()
    {
        String playerItems = "Items held:";
        for (int i = 0; i < player.getItemsHeld().size(); i++) 
        {
            playerItems += " " + player.getItemsHeld().get(i).getName();
        }
        System.out.println(playerItems);
    }
    
    
    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }
    
    private void handleLastRoomStuff()
    {
        //lastRoom = currentRoom;
        player.setLastRoom(player.getCurrentRoom());
        
        //PreviouslyVisitedRooms.add(lastRoom);
        player.getPreviouslyVisitedRooms().add(player.getLastRoom());

    }
}
