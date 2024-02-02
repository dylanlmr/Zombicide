package zombicide;

import java.util.List;
import java.util.Random;

public class City {
    private final Area[][] areas;
    private final Random random;
    private SpawnStreet spawnStreet;

    /**
     * Constructs a new City object with the specified width and height.
     *
     * @param width  the width of the city
     * @param height the height of the city
     */
    public City(int width, int height) {
        this.areas = new Area[height][width];
        this.random = new Random();
        initCity();
    }

    /**
     * Initializes the city by splitting the areas.
     */
    private void initCity() {
        Position topLeftPos = new Position(0, 0);
        Position bottomRightPos = new Position(getWidth() - 1, getHeight() - 1);
        splitAreas(topLeftPos, bottomRightPos);
    }

    /**
     * Creates a spawn street at the given position.
     *
     * @param p the position of the spawn street
     */
    private void createSpawnStreet(Position p) {
        int x = p.getX();
        int y = p.getY();
        this.spawnStreet = new SpawnStreet(x, y);
        this.areas[y][x] = this.spawnStreet;
    }

    /**
     * Generates a random position between the given bounds.
     *
     * @param pos1 the first position
     * @param pos2 the second position
     * @return a random position between the bounds
     */
    private Position getRandomPosBetweenBounds(Position pos1, Position pos2) {
        int x = random.nextInt(pos1.getX() + 2, pos2.getX() - 1);
        int y = random.nextInt(pos1.getY() + 2, pos2.getY() - 1);
        return new Position(x, y);
    }

    /**
     * Splits the areas of the city recursively.
     *
     * @param topLeftPos      the top left position of the area
     * @param bottomRightPos  the bottom right position of the area
     */
    private void splitAreas(Position topLeftPos, Position bottomRightPos) {
        Position crossroadPos = getRandomPosBetweenBounds(topLeftPos, bottomRightPos);

        if (this.spawnStreet == null) {
        	createSpawnStreet(crossroadPos);
        	createManholes(crossroadPos, topLeftPos, bottomRightPos);
        }

        createStreets(crossroadPos, topLeftPos, bottomRightPos);

        List<Position[]> areasPositions = getSplittedPositions(crossroadPos, topLeftPos, bottomRightPos);

        for (Position[] positions : areasPositions) {
            Position newTopLeftPos = positions[0];
            Position newBottomRightPos = positions[1];

            if (isAreaSplittable(newTopLeftPos, newBottomRightPos)) {
                splitAreas(newTopLeftPos, newBottomRightPos);
            }
        }
    }

    /**
     * Creates manhole streets at the extremities of the principal crossroad's streets.
     *
     * This method places manhole streets at specific positions within the areas array,
     * defining the boundaries of the streets that intersect at the given crossroad position.
     *
     * @param crossroadPos The position of the principal crossroad where streets intersect.
     * @param topLeftPos The position representing the top-left corner of the area to be considered.
     * @param bottomRightPos The position representing the bottom-right corner of the area to be considered.
     */
    private void createManholes(Position crossroadPos, Position topLeftPos, Position bottomRightPos) {
        this.areas[0][crossroadPos.getX()] = 
        		new ManholeStreet(crossroadPos.getX(), 0);
        
        this.areas[crossroadPos.getY()][bottomRightPos.getX()] =
                new ManholeStreet(bottomRightPos.getX(), crossroadPos.getY());

        this.areas[bottomRightPos.getY()][crossroadPos.getX()] =
                new ManholeStreet(crossroadPos.getX(), bottomRightPos.getY());
        
        this.areas[crossroadPos.getY()][0] = 
        		new ManholeStreet(0, crossroadPos.getY());
    }


	/**
     * Gets the positions of the areas after splitting.
     *
     * @param crossroadPos     the position of the crossroad
     * @param topLeftPos       the top left position of the area
     * @param bottomRightPos   the bottom right position of the area
     * @return a list of positions representing the splitted areas
     */
    private List<Position[]> getSplittedPositions(Position crossroadPos, Position topLeftPos, Position bottomRightPos) {
        Position[] topLeftArea = {
                topLeftPos,
                new Position(crossroadPos.getX() - 1, crossroadPos.getY() - 1)
        };
        Position[] topRightArea = {
                new Position(crossroadPos.getX() + 1, topLeftPos.getY()),
                new Position(bottomRightPos.getX(), crossroadPos.getY() - 1)
        };
        Position[] bottomLeftArea = {
                new Position(topLeftPos.getX(), crossroadPos.getY() + 1),
                new Position(crossroadPos.getX() - 1, bottomRightPos.getY())
        };
        Position[] bottomRightArea = {
                new Position(crossroadPos.getX() + 1, crossroadPos.getY() + 1),
                bottomRightPos
        };
        return List.of(topLeftArea, topRightArea, bottomLeftArea, bottomRightArea);
    }

    /**
     * Creates streets based on the crossroad position and the area bounds.
     *
     * @param crossRoadPos     the position of the crossroad
     * @param topLeftPos       the top left position of the area
     * @param bottomRightPos   the bottom right position of the area
     */
    private void createStreets(Position crossRoadPos, Position topLeftPos, Position bottomRightPos) {
        int width = getAreasWidth(topLeftPos, bottomRightPos);
        int height = getAreasHeight(topLeftPos, bottomRightPos);

        int cX = crossRoadPos.getX();
        int cY = crossRoadPos.getY();

        int tX = topLeftPos.getX();
        int tY = topLeftPos.getY();

        for (int i = 0; i < width; i++) {
            if (this.areas[cY][tX + i] == null)
                this.areas[cY][tX + i] = new Street(tX + i, cY);
        }

        for (int i = 0; i < height; i++) {
            if (this.areas[tY + i][cX] == null)
                this.areas[tY + i][cX] = new Street(cX, tY + i);
        }
    }

    /**
     * Checks if an area is splittable based on its size.
     *
     * @param pos1 the top left position of the area
     * @param pos2 the bottom right position of the area
     * @return true if the area is splittable, false otherwise
     */
    private boolean isAreaSplittable(Position pos1, Position pos2) {
        return getAreasWidth(pos1, pos2) >= 5 && getAreasHeight(pos1, pos2) >= 5;
    }

    /**
     * Displays the city by printing the areas.
     */
    public void display() {
        for (int i = 0; i < getHeight(); i++) {
            System.out.println();
            for (int j = 0; j < getWidth(); j++) {
                if (this.areas[i][j] == null) {
                    Room r = new Room(j, i);
                    this.areas[i][j] = r;
                }
                this.areas[i][j].display();
            }
        }
    }

    /**
     * Gets the width of the areas based on the given positions.
     *
     * @param pos1 the top left position of the area
     * @param pos2 the bottom right position of the area
     * @return the width of the areas
     */
    private int getAreasWidth(Position pos1, Position pos2) {
        return pos2.getX() - pos1.getX() + 1;
    }

    /**
     * Gets the height of the areas based on the given positions.
     *
     * @param pos1 the top left position of the area
     * @param pos2 the bottom right position of the area
     * @return the height of the areas
     */
    private int getAreasHeight(Position pos1, Position pos2) {
        return pos2.getY() - pos1.getY() + 1;
    }

    /**
     * Gets the width of the city.
     *
     * @return the width of the city
     */
    public int getWidth() {
        return this.areas[0].length;
    }

    /**
     * Gets the height of the city.
     *
     * @return the height of the city
     */
    public int getHeight() {
        return this.areas.length;
    }
}
