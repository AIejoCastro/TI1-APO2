import java.util.Scanner;

public class SnakesAndLadders {

    public static Scanner lector= new Scanner(System.in);
    public static ControllerGame objMain= new ControllerGame();
    public static Score score =new Score();



    public static void main(String[] args) {

        menu();

    }

    public static void menu() {

        int option=0;
        while (option!=3){
            System.out.println("Welcome to snakes and ladders" +
                    "\nSelect one option." +
                    "\n1. Play." +
                    "\n2. ScoreBoard." +
                    "\n3. Exit.");
            option=lector.nextInt();
            lector.nextLine();

            switch (option){

                //Inicia el juego, pide numero de columnas y filas
                case 1:
                    System.out.println("Type the number of rows");
                    int row= lector.nextInt();
                    lector.nextLine();
                    System.out.println("Type the number of columns");
                    int column=lector.nextInt();
                    lector.nextLine();
                    System.out.println("Type how many stairs you want in the game");
                    int stairs = lector.nextInt();
                    lector.nextLine();
                    System.out.println("Type how many snakes you want in the game");
                    int snakes = lector.nextInt();
                    lector.nextLine();

                    objMain.createBoard(row,column);
                    objMain.createStairs(stairs, row, column);
                    objMain.createSnakes(snakes, row, column);

                    //Crea los jugadores, para las pruebas pues son estos dos y cada uno inicia en la posicion uno del tablero
                    // ese "position" es un atributo que cambia cuando se mueve, analiza el move player del controller
                    // se mueve el jugador y se hace setPositionPlayer(actualbox.getId);
                    Player player1 = new Player("*",1,0);
                    Player player2 = new Player("!",1,0);
                    Player player3 = new Player("%",1,0);
                    player1.setPosition(1);
                    player2.setPosition(1);
                    player3.setPosition(1);
                    player1.setScore(0);
                    player2.setScore(0);
                    player3.setScore(0);
                    objMain.addPlayerToGame(player1,player2,player3);
                    int turn = 0;
                    boolean inGame = true;

                    while (inGame) {
                        System.out.println("This is the game board");
                        System.out.println("----------------------");
                        objMain.printGame(row, column);
                        //Con turn 0 el jugador uno es el que lleva el turno
                        if (turn == 0) {

                            System.out.println("\nPlayer turn: " + player1.getName() + " \n1. Roll dice.\n2. See ladders and snakes");
                            int optionPlayer1 = lector.nextInt();
                            lector.nextLine();

                            if (optionPlayer1 == 1) {
                                int resultDice=player1.rollDie();
                                System.out.println("----------------------");
                                System.out.println("The result of the dice is: " + resultDice);
                                System.out.println("----------------------");

                                boolean continue1 = objMain.movePlayer(player1, resultDice);
                                if(continue1){
                                    player1=null;
                                    player2=null;
                                    player3=null;
                                    objMain.printGame(row, column);
                                    System.out.println("\n----------------------");
                                    System.out.println("End game, player 1 win");
                                    System.out.println("----------------------");
                                    inGame=false;
                                    objMain.resetAll();
                                }
                                turn = 1;

                            } else if (optionPlayer1 == 2) {
                                System.out.println("These are ladders and snakes" +
                                        "\n----------------------");
                                objMain.printSnakesAndLadder(row, column);
                                System.out.println("\n----------------------");
                            }

                            //Con turn==1 es el jugador 2
                        } else if (turn == 1) {

                            System.out.println("\nPlayer turn: " + player2.getName() + " \n1. Roll dice.\n2. See ladders and snakes");
                            int optionPlayer2 = lector.nextInt();
                            lector.nextLine();

                            if (optionPlayer2 == 1) {
                                int resultDice2=player2.rollDie();
                                System.out.println("----------------------");
                                System.out.println("The result of the dice is: " + resultDice2);
                                System.out.println("----------------------");

                                boolean continue2=objMain.movePlayer(player2,resultDice2);
                                if(continue2){
                                    player1=null;
                                    player2=null;
                                    player3=null;
                                    objMain.printGame(row, column);
                                    System.out.println("\n----------------------");
                                    System.out.println("End game, player 2 win");
                                    System.out.println("----------------------");
                                    inGame=false;
                                    objMain.resetAll();
                                }
                                turn = 2;

                            } else if (optionPlayer2 == 2) {
                                System.out.println("These are ladders and snakes" +
                                        "\n----------------------");
                                objMain.printSnakesAndLadder(row, column);
                                System.out.println("\n----------------------");
                            }
                        }
                        else {

                            System.out.println("\nPlayer turn: " + player3.getName() + " \n1. Roll dice.\n2. See ladders and snakes");
                            int optionPlayer3 = lector.nextInt();
                            lector.nextLine();

                            if (optionPlayer3 == 1) {
                                int resultDice3=player3.rollDie();
                                System.out.println("----------------------");
                                System.out.println("The result of the dice is: " + resultDice3);
                                System.out.println("----------------------");

                                boolean continue3=objMain.movePlayer(player3,resultDice3);
                                if(continue3){
                                    player1=null;
                                    player2=null;
                                    player3=null;
                                    objMain.printGame(row, column);
                                    System.out.println("\n----------------------");
                                    System.out.println("End game, player 3 win");
                                    System.out.println("----------------------");
                                    inGame=false;
                                    objMain.resetAll();
                                }
                                turn = 0;

                            } else if (optionPlayer3 == 2 ) {
                                System.out.println("These are ladders and snakes" +
                                        "\n----------------------");
                                objMain.printSnakesAndLadder(row, column);
                                System.out.println("\n----------------------");
                            }
                        }
                    }
                    break;
                case 2:
                    objMain.sout();
                    break;

                case 3:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Option out of range");
                    break;
            }
        }
    }
}