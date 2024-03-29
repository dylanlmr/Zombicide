package zombicide.item;

import zombicide.action.survivor.DoorAction;
import zombicide.actor.survivor.Survivor;

public class MasterKey extends Item {
	private Survivor survivor;

	public MasterKey(){
		super();
		this.canOpen = true;
	}
	public String toString(){
		return "master key";
	}

	public void use() {
		DoorAction d = new DoorAction();
		d.doSomething(survivor);
	}
}
