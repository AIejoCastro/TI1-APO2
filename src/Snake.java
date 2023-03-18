public class Snake {

    private char name;
    private Box start;
    private Box end;

    public Snake(char name) {
        this.name = name;
    }

    public char getName() {
        return name;
    }

    public void setName(char name) {
        this.name = name;
    }

    public Box getStart() {
        return start;
    }

    public void setStart(Box start) {
        this.start = start;
    }

    public Box getEnd() {
        return end;
    }

    public void setEnd(Box end) {
        this.end = end;
    }
}
