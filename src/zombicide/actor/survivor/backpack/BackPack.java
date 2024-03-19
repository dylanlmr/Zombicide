package zombicide.actor.survivor.backpack;

import zombicide.actor.survivor.Survivor;
import zombicide.item.Item;

import java.util.ArrayList;
import java.util.List;

/**
 * Class representing a backpack that can hold items.
 */
public class BackPack {

    /**
     * List to store items in the backpack.
     */
    private final List<Item> items;

    /**
     * Constructor for the BackPack class. Initializes an empty list of items.
     */
    public BackPack() {
        this.items = new ArrayList<>();
    }

    /**
     * Adds the specified item to the backpack if the backpack is not full.
     *
     * @param item The item to be added to the backpack.
     */
    public void addItem(Item item) {
        if (canBeAdded()) {
            this.items.add(item);
        }
    }

    /**
     * Removes the specified item from the backpack if the backpack is not empty.
     *
     * @param item The item to be removed from the backpack.
     */
    public void removeItem(Item item) {
        if (!this.items.isEmpty()) {
            this.items.remove(item);
        }
    }

    /**
     * Returns the list of items currently held or stored by the survivor.
     *
     * @return A List of Item objects representing the items held or stored by the survivor.
     */
    public List<Item> getItems() {
        return items;
    }

    /**
     * Swaps an item between the Survivor's backpack and the current room.
     * Removes the specified item from the backpack and adds it to the room,
     * while removing the specified item from the room and adding it to the backpack.
     *
     * @param fromRoom The item to swap from the current room to the backpack.
     * @param fromBp The item to swap from the backpack to the current room.
     */
    public void swapItemsRoomBp(Item fromRoom, Item fromBp) {
        removeItem(fromBp);
        addItem(fromRoom);
        Survivor s = fromBp.getSurvivor();
        fromBp.setSurvivor(null);
        fromRoom.setSurvivor(s);
    }


    public void swapItemsHandBp(Item fromHand , Item fromBp){
        removeItem(fromBp);
        addItem(fromHand);
        Survivor s = fromHand.getSurvivor();
        s.setHandleItem(fromBp);
    }

    public void takeAItem(Item bpItem) {
        removeItem(bpItem);
        Survivor s = bpItem.getSurvivor();
        s.setHandleItem(bpItem);
    }

    public boolean canBeAdded(){
        return this.items.size() < 5;
    }


}
