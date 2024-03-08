package zombicide;

public enum ActorDirection {

    UP(0,-1),
    RIGHT(1,0),
    DOWN(0,1),
    LEFT(-1,0);

    final int x;
    final int y;

    ActorDirection(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
