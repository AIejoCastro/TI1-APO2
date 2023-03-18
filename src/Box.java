public class Box {

    //Datos
    private int numberOfBox;
    private Box previous;
    private Box next;
    private Player player1;
    private  Player player2;
    private Player player3;
    private Stair stair;
    private Snake snake;

    public Box(int numberOfBox) {
        this.numberOfBox = numberOfBox;
    }

    public int getNumberOfBox() {
        return numberOfBox;
    }

    public void setNumberOfBox(int numberOfBox) {
        this.numberOfBox = numberOfBox;
    }

    public Box getPrevious() {
        return previous;
    }

    public void setPrevious(Box previous) {
        this.previous = previous;
    }

    public Box getNext() {
        return next;
    }

    public void setNext(Box next) {
        this.next = next;
    }

    public Player getPlayer1() {
        return player1;
    }

    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }

    public Player getPlayer3() {
        return player3;
    }

    public void setPlayer3(Player player3) {
        this.player3 = player3;
    }

    public Stair getStair() {
        return stair;
    }

    public void setStair(Stair stair) {
        this.stair = stair;
    }

    public Snake getSnake() {
        return snake;
    }

    public void setSnake(Snake snake) {
        this.snake = snake;
    }

}
