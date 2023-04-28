public class Node {
/**
 * Clase Nodo sacada de el git hub de el profesor domiciano
 * */
    private double value;
    private String namePlayer;

    private Node right;
    private Node left;

    public String getNamePlayer() {
        return namePlayer;
    }

    public void setNamePlayer(String namePlayer) {
        this.namePlayer = namePlayer;
    }

    public Node(double value, String namePlayer) {
        this.value = value;
        this.namePlayer=namePlayer;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    @Override
    public String toString(){
        return "valor: " + value;
    }

}