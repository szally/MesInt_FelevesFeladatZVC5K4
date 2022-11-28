public class Place {
    private int x;
    private int y;

    public Place(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        return "Place coordinates: " +
                "x = " + x +
                ", y = " + y;
    }
}
