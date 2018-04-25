public class Shape {
	private static int count;
	private int id, countOfPoints;
	private String name;

	public Shape() {
		id = count++;
	}

	public Shape(String name, int countOfPoints) {
		id = count++;
		this.name = name;
		this.countOfPoints = countOfPoints;
	}

	public int getId() {
		return id;
	}

	public int getCount() {
		return count;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCountOfPoints() {
		return countOfPoints;
	}

	public void setCountOfPoints(int countOfPoints) {
		this.countOfPoints = countOfPoints;
	}

	@Override
	public String toString() {
		return name;
	}
}
