package zombicide;

import org.junit.jupiter.api.BeforeEach;
import zombicide.actor.survivor.Survivor;
import zombicide.role.Fighter;
import zombicide.role.Lucky;
import zombicide.role.Snooper;
import zombicide.role.Healer;
import zombicide.city.City;

public class RoleTest {
    private Survivor survivor;
    private Fighter fighter;
    private Lucky lucky;
    private Snooper snooper;
    private Healer healer;
    private City city;

    @BeforeEach
    public void before(){
        this.city = new zombicide.city.City(5,5);
        this.survivor = new Survivor(this.city);
        this.fighter = new Fighter();
        this.snooper = new Snooper();
        this.lucky = new Lucky();
        this.healer = new Healer();
    }

}
