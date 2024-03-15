package zombicide;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import zombicide.actor.survivor.Survivor;
import zombicide.actor.zombie.Zombie;
import zombicide.action.survivor.NoiseAction;
import zombicide.actor.zombie.Abomination;
import zombicide.actor.zombie.Balaise;
import zombicide.actor.zombie.Runner;
import zombicide.actor.zombie.Walker;
import zombicide.area.Area;
import zombicide.area.room.Room;
import zombicide.backpack.BackPack;
import zombicide.city.City;
import zombicide.item.Item;
import zombicide.item.weapon.*;
import zombicide.role.Fighter;
import zombicide.role.Lucky;
import zombicide.role.Role;

import java.util.List;

import java.util.ArrayList;
import java.util.Arrays;


public class ActorTest {
    private Survivor survivor;
    private Zombie balaise;
    private Zombie runner;
    private Zombie walker;
    private Zombie abomination;
    private Item pistol;
    private BackPack backPack;
    private Area room;
    private NoiseAction noiseAction;
    private Fighter fighter;
    private Lucky lucky;
    private City city;

    @BeforeEach
    public void before(){
        this.city = new City(5,5);
        this.survivor = new Survivor(this.city);
        this.balaise = new Balaise(this.city);
        this.abomination = new Abomination(this.city);
        this.walker = new Walker(this.city);
        this.runner = new Runner(this.city);
        this.pistol = new Pistol();
        this.backPack = new BackPack();
        this.room = new Room(6,7);
        this.noiseAction = new NoiseAction(this.survivor);
        this.fighter = new Fighter();
        this.lucky = new Lucky();
    }

    @Test
    void testActorInitialization(){
        assertNotNull(balaise);
        assertNotNull(abomination);
        assertNotNull(walker);
        assertNotNull(runner);
        assertNotNull(survivor);
    }

    @Test
    void testGetZombieAttack(){
        assertEquals(balaise.getAttackPoints(), 2);
        assertEquals(abomination.getAttackPoints(), 3);
        assertEquals(walker.getAttackPoints(), 1);
        assertEquals(runner.getAttackPoints(), 1);
    }

    @Test
    void testGetIsStrong(){
        assertTrue(balaise.getIsStrong());
        assertTrue(abomination.getIsStrong());
        assertFalse(walker.getIsStrong());
        assertFalse(runner.getIsStrong());
    }

    @Test
    void testHandleItem(){
        survivor.handleItem(pistol);
        assertEquals(pistol, survivor.getHandleItem());
    }

    @Test
    void testSkillPoints(){
        assertEquals(survivor.getSkillPoints(), 0);
        survivor.increaseSkillPoints();
        assertEquals(survivor.getSkillPoints(), 1);
    }

    @Test
    void testLevelReached(){
        assertFalse(survivor.isLevelReached());
        for(int i = 0; i < 3; i++){
            survivor.increaseSkillPoints();
        }
        assertEquals(survivor.getSkillPoints(), 3);
        assertTrue(survivor.isLevelReached());
        for(int i = 0; i < 4; i++){
            survivor.increaseSkillPoints();
        }
        assertEquals(survivor.getSkillPoints(), 7);
        assertTrue(survivor.isLevelReached());
        for(int i = 0; i < 4; i++){
            survivor.increaseSkillPoints();
        }
        assertEquals(survivor.getSkillPoints(), 11);
        assertTrue(survivor.isLevelReached());
    }

    @Test
    void testGetLifePointsOfActor(){
        assertEquals(survivor.getLifePoints(), 5);
        assertEquals(abomination.getLifePoints(), 6);
    }

    @Test
    void testAddLifePointsOfActor(){
        assertEquals(survivor.getLifePoints(), 5);
        survivor.addLifePoints(1);
        assertEquals(survivor.getLifePoints(), 6);
    }

    @Test
    void testGetActionPointsForActor(){
        assertEquals(survivor.getActionPoints(), 3);
    }

    @Test
    void testSetActionPointsForActor(){
        assertEquals(survivor.getActionPoints(), 3);
        survivor.setActionPoints(5);
        assertEquals(survivor.getActionPoints(), 5);
    }

    @Test
    void testSetAndGetAreaOfActor(){
        survivor.setArea(room);
        assertEquals(survivor.getArea().getY(), room.getY());
        assertEquals(survivor.getArea().getX(), room.getX());
    }

    @Test
    void testMakeNoiseFromSurvivor(){
        survivor.setArea(room);
        noiseAction.setSurvivor(survivor);
        noiseAction.setLifePointsToAdd(1);
        assertEquals(0, room.getNoise());
        noiseAction.doSomething();
        assertEquals(1, room.getNoise());
    }

    @Test
    void testGetBackpackOfSurvivor(){
        assertNotNull(survivor.getBackpack());
    }

    @Test
    void testIfSurvivorHasRoles(){
        assertFalse(survivor.hasRoles());
        survivor.getRoles().add(fighter);
        assertTrue(survivor.hasRoles());
    }

    @Test
    void testGetRolesOfSurvivor(){
        List<Role> Roles = Arrays.asList(fighter, lucky);
        survivor.getRoles().addAll(Roles);
        assertEquals(Roles, survivor.getRoles());
        assertEquals(Roles.size(), survivor.getRoles().size());
        assertTrue(survivor.getRoles().containsAll(Roles));
    }

}
