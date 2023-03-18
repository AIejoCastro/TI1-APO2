import java.util.Random;

public class ControllerGame {

    BSTScore bst=new BSTScore();
    public static void imprimirNumeros(int num) {
        if (num == 0) {
            return;
        }
        imprimirNumeros(num - 1);
        System.out.print(num + ", ");
    }

    public void sout(){
        System.out.println("---------------------");
        System.out.println("TOP");
        imprimirNumeros(bst.size());
        System.out.println("\n"); bst.reverseInOrder(); // concatenamos los números con el resultado de inOrder
        System.out.println("---------------------");
    }


    Random rand = new Random();
    Board board = new Board();
    Score score = new Score();

    /**
     * nombre: createBoard
     * Crea el tablero, recibe el numero de columnas y filas para saber cuantos nodos se crean
     * @param row Contiene el numero de filas digitadas por el usuario
     * @param column Contiene el numero de columnas digitadas por el usuario
     */
    public void createBoard(int row, int column) {
        int tam = row * column;

        for (int i = 1; i <= tam; i++) {
            board.addBox(new Box(i));
        }
    }

    public void createStairs(int stairs, int row, int column) {
        Stair stair;
        for (int i = 1; i <= stairs; i++) {
            int randomNumber1 = rand.nextInt(row * column) + 1;
            int randomNumber2 = rand.nextInt(row * column) + 1;
            while (searchBox(randomNumber1).getNext() == null || searchBox(randomNumber1).getPrevious() == null ||
                    searchBox(randomNumber1).getStair() != null || searchBox(randomNumber1).getSnake() != null ||
                    searchBox(randomNumber2).getNext() == null || searchBox(randomNumber2).getPrevious() == null ||
                    searchBox(randomNumber2).getStair() != null || searchBox(randomNumber2).getSnake() != null || randomNumber1 == randomNumber2) {
                randomNumber1 = rand.nextInt(row * column) + 1;
                randomNumber2 = rand.nextInt(row * column) + 1;
            }
            stair = new Stair(i);
            if (randomNumber1 > randomNumber2) {
                stair.setStart(searchBox(randomNumber2));
                stair.setEnd(searchBox(randomNumber1));
                searchBox(randomNumber1).setStair(stair);
                searchBox(randomNumber2).setStair(stair);
            } else {
                stair.setStart(searchBox(randomNumber1));
                stair.setEnd(searchBox(randomNumber2));
                searchBox(randomNumber1).setStair(stair);
                searchBox(randomNumber2).setStair(stair);
            }
        }
    }

    public void createSnakes(int snakes, int row, int column) {
        Snake snake;
        char z = (char) (snakes + 64);
        char c;
        for (c = 'A'; c <= z; c++) {
            int randomNumber1 = rand.nextInt(row * column) + 1;
            int randomNumber2 = rand.nextInt(row * column) + 1;
            while (searchBox(randomNumber1).getNext() == null || searchBox(randomNumber1).getPrevious() == null ||
                    searchBox(randomNumber1).getStair() != null || searchBox(randomNumber1).getSnake() != null ||
                    searchBox(randomNumber2).getNext() == null || searchBox(randomNumber2).getPrevious() == null ||
                    searchBox(randomNumber2).getStair() != null || searchBox(randomNumber2).getSnake() != null || randomNumber1 == randomNumber2) {
                randomNumber1 = rand.nextInt(row * column) + 1;
                randomNumber2 = rand.nextInt(row * column) + 1;
            }
            snake = new Snake(c);
            if (randomNumber1 > randomNumber2) {
                snake.setStart(searchBox(randomNumber1));
                snake.setEnd(searchBox(randomNumber2));
                searchBox(randomNumber1).setSnake(snake);
                searchBox(randomNumber2).setSnake(snake);
            } else {
                snake.setStart(searchBox(randomNumber2));
                snake.setEnd(searchBox(randomNumber1));
                searchBox(randomNumber1).setSnake(snake);
                searchBox(randomNumber2).setSnake(snake);
            }
        }
    }

    /**
     * nombre: printGame
     * Es necesario para imprimir tener estos parametros ya que con las filas y columnas se hara una debida impresión
     * @param row contiene el numero de filas digitadas por el usuario
     * @param column Contiene el numero de columnas digitadas por el usuario
     */
    public void printGame(int row, int column) {
        board.printBoard(row, column);
    }

    public void printSnakesAndLadder(int row, int column) {
        board.printSnakesAndLadders(row, column);
    }

    /**
     * name: addPlayerToGame
     * Se hace el llamado al tablero que tiene un get de la primera casilla para agregar los jugadores al comenzar la partida
     * @param player1 Contiene el jugador 1 con su respectiva skin (String)
     * @param player2 Contiene el jugador 2 con su respectiva skin (String)
     */
    public void addPlayerToGame(Player player1, Player player2,  Player player3) {
        board.getStart().setPlayer1(player1);
        board.getStart().setPlayer2(player2);
        board.getStart().setPlayer3(player3);
    }

    public Box searchBox(int boxToFind) {
        return searchBox(board.getStart(), boxToFind);
    }

    private Box searchBox(Box actualBox, int boxTofind) {
        if (actualBox == null) {
            return board.getEnd();
        }
        if (actualBox.getNumberOfBox() == boxTofind) {
            return actualBox;
        }
        return searchBox(actualBox.getNext(), boxTofind);
    }

    /**
     * name movePlayer
     * Es un metodo recursivo para mover al jugador de posicion en el tablero
     * @param player Es el jugador 1 o 2 o 3
     * @param positionsToMove Son las posiciones para mover (Resultado del dado)
     */
    public boolean movePlayer(Player player, int positionsToMove) {
        int moving = 0;
        Box box;
        String playerName = player.getName();
        switch (playerName) {
            case "*":
                long inicio=player.getStartScore();
                moving = player.getPosition() + positionsToMove;
                if (searchBox(moving).getSnake() != null) {
                    if (searchBox(moving).getSnake().getStart().getNumberOfBox() == moving) {
                        moving = searchBox(moving).getSnake().getEnd().getNumberOfBox();
                    }
                }
                if (searchBox(moving).getStair() != null) {
                    if (searchBox(moving).getStair().getStart().getNumberOfBox() == moving) {
                        moving = searchBox(moving).getStair().getEnd().getNumberOfBox();
                    }
                }
                box = searchBox(player.getPosition());
                box.setPlayer1(null);
                if (moving >= board.getEnd().getNumberOfBox()) {
                    board.getEnd().setPlayer1(player);
                    long fin=System.currentTimeMillis();
                    double duracion =  ((fin - inicio)/1000.0);
                    /* la puntuación de un jugador en un juego, y lo hace de la siguiente manera:Llama al método playerScore(duracion) con un argumento duracion, que probablemente es la duración del juego en segundos.
                    Toma el resultado devuelto por el método playerScore y lo convierte a una cadena usando el método String.valueOf.
                    Toma la cadena resultante y la convierte a un entero utilizando el método Integer.parseInt.
                    Establece la puntuación del jugador utilizando el valor entero resultante de la conversión, usando el método setScore en el objeto player.
                    Por lo tanto, en resumen, la línea de código establece la puntuación del jugador en el juego usando la puntuación calculada por el método playerScore para la duración del juego proporcionada.*/
                    player.setScore(Integer.parseInt(String.valueOf(score.playerScore(duracion))));
                    double scoreFinal= score.playerScore(duracion);
                    Node node1 = new Node(scoreFinal,playerName);

                    bst.insert(node1);
                    return true;
                }
                box = searchBox(moving);
                box.setPlayer1(player);
                player.setPosition(moving);
                break;
            case "!":
                long inicio2=player.getStartScore();
                moving = player.getPosition() + positionsToMove;
                if (searchBox(moving).getSnake() != null) {
                    if (searchBox(moving).getSnake().getStart().getNumberOfBox() == moving) {
                        moving = searchBox(moving).getSnake().getEnd().getNumberOfBox();
                    }
                }
                if (searchBox(moving).getStair() != null) {
                    if (searchBox(moving).getStair().getStart().getNumberOfBox() == moving) {
                        moving = searchBox(moving).getStair().getEnd().getNumberOfBox();
                    }
                }
                box = searchBox(player.getPosition());
                box.setPlayer2(null);
                if (moving >= board.getEnd().getNumberOfBox()) {
                    board.getEnd().setPlayer2(player);
                    long fin2=System.currentTimeMillis();
                    double duracion2 = ((fin2-inicio2)/1000.0);;

                    player.setScore(Integer.parseInt(String.valueOf(score.playerScore(duracion2))));
                    double scoreFinal= score.playerScore(duracion2);
                    Node node2 = new Node(scoreFinal,playerName);

                    bst.insert(node2);
                    return true;
                }
                box = searchBox(moving);
                box.setPlayer2(player);
                player.setPosition(moving);
                break;
            case "%":
                long inicio3=player.getStartScore();
                moving = player.getPosition() + positionsToMove;
                if (searchBox(moving).getSnake() != null) {
                    if (searchBox(moving).getSnake().getStart().getNumberOfBox() == moving) {
                        moving = searchBox(moving).getSnake().getEnd().getNumberOfBox();
                    }
                }
                if (searchBox(moving).getStair() != null) {
                    if (searchBox(moving).getStair().getStart().getNumberOfBox() == moving) {
                        moving = searchBox(moving).getStair().getEnd().getNumberOfBox();
                    }
                }
                box = searchBox(player.getPosition());
                box.setPlayer3(null);
                if (moving >= board.getEnd().getNumberOfBox()) {
                    board.getEnd().setPlayer3(player);
                    long fin3 =System.currentTimeMillis();
                    double duracion3 = ((fin3-inicio3)/1000.0);;

                    player.setScore(Integer.parseInt(String.valueOf(score.playerScore(duracion3))));
                    double scoreFinal= score.playerScore(duracion3);
                    Node node3 = new Node(scoreFinal,playerName);

                    bst.insert(node3);
                    return true;
                }
                box = searchBox(moving);
                box.setPlayer3(player);
                player.setPosition(moving);
                break;
        }
        return false;
    }


    public long timeStart(){

        return System.currentTimeMillis();
    }
    public void resetAll() {
        resetAll(board.getStart());
    }

    private void resetAll(Box current) {
        if (current == null) {
            return;
        }
        if(current.getNext() == null && current.getPrevious() == null) {
            board.setStart(null);
            board.setEnd(null);
            return;
        }
        if (current.getPrevious() == null) {
            current.getNext().setPrevious(null);
            board.setStart(current.getNext());
            resetAll(board.getStart());
        }
    }
}