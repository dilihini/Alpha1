/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.util;

/**
 *
 * @author NLiyanage
 */
public abstract class List {

    protected Listable[] list; // Array to hold this list’s elements
    protected int numItems; // Number of elements on this list
    protected int currentPos; // Current position for iteration

    public List(int maxItems) // Instantiates and returns a reference to an empty list object
    // with room for maxItems elements
    {
        numItems = 0;
        list = new Listable[maxItems];
    }

    public boolean isFull() // Returns whether this list is full
    {
        return (list.length == numItems);
    }

    public int lengthIs() // Returns the number of elements on this list
    {
        return numItems;
    }

    public abstract boolean isThere(Listable item);
// Returns true if an element with the same key as item is on this list;
// otherwise, returns false

    public abstract Listable retrieve(Listable item);
// Returns a copy of the list element with the same key as item

    public abstract void insert(Listable item);
// Adds a copy of item to this list

    public abstract void delete(Listable item);
// Deletes the element with the same key as item from this list

    public void reset() // Initializes current position for an iteration through this lis
    {
        currentPos = 0;
    }

    public Listable getNextItem() {
        Listable next = list[currentPos];
        if (currentPos == numItems - 1) {
            currentPos = 0;
        } else {
            currentPos++;
        }
        return next.copy();
    }
}
