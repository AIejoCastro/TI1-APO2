import java.util.Random;

public class Player {

    private String name;
    private int position;
    private int score;
    private long startScore=assignStartScore();

    public Player(String name, int position, int score) {
        this.name = name;
        this.position=position;
        this.score= score;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public long getStartScore() {
        return startScore;
    }

    public void setStartScore(long startScore) {
        this.startScore = startScore;
    }

    /**
     * name: rollDie
     * Es un random para simular el dado
     * @return Retorna el resultado del dado
     */
    public int  rollDie() {
        int roll = random.nextInt(6)+1; ;
        return roll;
    }

    Random random = new Random();

    public String getName() {
        return name;
    }

    public long assignStartScore(){

        return System.currentTimeMillis();
    }


}

