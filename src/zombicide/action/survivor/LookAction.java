package zombicide.action.survivor;

import zombicide.action.Action;
import zombicide.actor.survivor.Survivor;
import zombicide.city.area.Area;
import zombicide.util.Direction;

public class LookAction implements Action<Survivor> {

    public void doSomething(Survivor survivor){
        rummage(survivor);
    }

    private void rummage(Survivor survivor) {
        Area a = survivor.getArea();
        a.displayActors();
        for (Direction d : Direction.values()) {
            System.out.println("The door " + d.name() + " is " + (a.getDoor(d).isOpen()? "open" : "closed"));
        }
    }
}
