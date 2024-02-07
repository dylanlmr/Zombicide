package zombicide;

/**
 * Abstract class representing an actor in the game.
 */
public abstract class Actor {
    /**
     * The life points of the actor.
     */
    protected int lifePoints;

    /**
     * The action points of the actor.
     */
    protected int actionPoints;

    /**
     * Retrieves the life points of the actor.
     *
     * @return the life points of the actor
     */
    public int getLifePoints() {
        return lifePoints;
    }

    /**
     * Sets the life points of the actor.
     *
     * @param lifePoints the life points to set
     */
    public void setLifePoints(int lifePoints) {
        this.lifePoints = lifePoints;
    }

    /**
     * Retrieves the action points of the actor.
     *
     * @return the action points of the actor
     */
    public int getActionPoints() {
        return actionPoints;
    }

    /**
     * Sets the action points of the actor.
     *
     * @param actionPoints the action points to set
     */
    public void setActionPoints(int actionPoints) {
        this.actionPoints = actionPoints;
    }

    /**
     * Moves the player.
     * (To be implemented by subclasses)
     */
    public void movePlayer() {
    	
    }

    /**
     * Performs an attack.
     * (To be implemented by subclasses)
     */
    public void attack() {
    	
    }
}

