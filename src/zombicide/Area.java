package zombicide;

public class Area {
	
	private int posX;
	private int posY;
	private List<Survivor> survivors;
	private List<Zombie> zombies;
	private int noise;
	
	/**
	 * Constructor of area
	 * @param posX - int - the position X of this Cell
	 * @param posY - int - the position Y of this Cell
	 */
	public Area(int posX, int posY) {
		this.posX = posX;
		this.posY = posY;
		this.survivors = new ArrayList<Survivor>();
		this.zombies = new Arraylist<Zombie>();
		this.noise = 0;
	}
}
