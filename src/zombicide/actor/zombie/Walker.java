package zombicide.actor.zombie;

import zombicide.actor.Zombie;

public class Walker extends Zombie {
	
	public Walker() {
		this.attackPoints = 1;
		this.isStrong = false;
	}
	
	public boolean getIsStrong() {
		return this.isStrong;
	}
	
	public int getAttackPoints() {
		return this.attackPoints;
	}

}
