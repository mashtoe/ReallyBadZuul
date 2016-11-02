/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Bruger
 */
public class Item 
{
    private String name;
    private String description;
    private int weight;
    /**
     * Constructor
     * @param name
     * @param description
     * @param weight 
     */
    public Item(String name, String description, int weight)
    {
        this.name = name;
        this.description = description;
        this.weight = weight;
    }
    /**
     * Returns the name of the item
     * @return name of the item as string
     */
    public String getName()
    {
        return name;
    }
    /**
     * Returns the items description
     * @return a discritpion of the item as string
     */
    public String getDescription()
    {
        return description;
    }
    
}
