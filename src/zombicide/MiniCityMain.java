package zombicide;

public class MiniCityMain {

	public static void main(String[] args) {
		 City aCity = new City(20, 20);
		 Area r = aCity.getAreas()[0][0];
		 r.getDoor(DoorDirection.RIGHT).open();
		 Area r1 = aCity.getAreas()[0][4];
		 r1.getDoor(DoorDirection.DOWN).open();
//		 Area r2 = aCity.getAreas()[2][6];
//		 r2.getDoor(DoorDirection.DOWN).open();
		 Area r3 = aCity.getAreas()[0][1];
		 r3.getDoor(DoorDirection.DOWN).open();
		 aCity.display();
	}
}

