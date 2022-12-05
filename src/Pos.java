class Pos {

    private String color;
    private int x, y, maxSize;
    private boolean choosable;

    public Pos(String color, int x, int y, int maxSize) {
        this.color = color;
        this.x = x;
        this.y = y;
        this.maxSize = maxSize;
        this.choosable = false;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getMaxSize() {
        return maxSize;
    }

    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
    }

    public void setChoosable(boolean choosable) {
        this.choosable = choosable;
    }

    public boolean getChoosable() {
        return choosable;
    }

}