public class Board {

    private Box start;
    private Box end;


    public Box getEnd() {
        return end;
    }

    public void setEnd(Box end) {
        this.end = end;
    }

    public Box getStart() {
        return start;
    }

    public void setStart(Box start) {
        this.start = start;
    }

    public void addBox(Box box) {
        if (start == null) {
            setStart(box);
            setEnd(box);
        } else {
            end.setNext(box);
            box.setPrevious(end);
            end = box;
        }
    }

    private void addBox(Box current, Box box) {
        if (current.getNext() == null) {
            current.setNext(box);
            return;
        }
        addBox(current.getNext(), box);
    }

    public void printBoard(int row, int column) {
        printBoard(end, row, column);
    }

    private void printBoard(Box current, int row, int column) {
        if (current != null && row > 0) {
            if (current.getNumberOfBox() % (column * 2) == 0) {
                System.out.println();
                current = printBoardRow(current, column);
                printBoard(current, row - 1, column);
            } else {
                current = printBoardRowInverted(current, column);
                printBoard(current, row - 1, column);
            }
        }
    }

    private Box printBoardRow(Box current, int column) {
        Box last = null;
        if (current != null && column > 0) {
            String players = seePlayers(current);
            String message = " [" + current.getNumberOfBox() + players + "]";
            System.out.print(message);
            last = printBoardRow(current.getPrevious(), column - 1);
        }
        if (last == null) {
            last = current;
        }
        return last;
    }

    private Box printBoardRowInverted(Box current, int column) {
        Box last = null;
        if (current != null && column > 0) {
            last = printBoardRowInverted(current.getPrevious(), column - 1);
            String players = seePlayers(current);
            String message = " [" + current.getNumberOfBox() + players + "]";
            System.out.print(message);
        } else {
            System.out.println();
        }
        if (last == null) {
            last = current;
        }
        return last;
    }

    private String seePlayers(Box current) {
        String players = "";
        if (current.getPlayer1() != null) {
            players = players + current.getPlayer1().getName();
        }
        if (current.getPlayer2() != null) {
            players = players + current.getPlayer2().getName();
        }
        if (current.getPlayer3() != null) {
            players = players + current.getPlayer3().getName();
        }
        return players;
    }

    public void printSnakesAndLadders(int rows, int column) {
        printSnakesAndLadders(end, rows, column);
    }

    private void printSnakesAndLadders(Box current, int row, int column) {
        if (current != null && row > 0) {
            if (current.getNumberOfBox() % (column * 2) == 0) {
                System.out.println();
                current = printSnakesAndLaddersBoardRow(current, column);
                printSnakesAndLadders(current, row - 1, column);
            } else {
                current = printSnakesAndLaddersRowInverted(current, column);
                printSnakesAndLadders(current, row - 1, column);
            }
        }
    }

    private Box printSnakesAndLaddersBoardRow(Box current, int columnCount) {
        Box last = null;
        String message = "";
        if (current != null && columnCount > 0) {
            if (current.getSnake() != null && current.getStair() == null) {
                message = " [" + current.getSnake().getName() + "]";
            } else if (current.getStair() != null && current.getSnake() == null) {
                message = " [" + current.getStair().getName() + "]";
            } else if (current.getSnake() == null && current.getStair() == null) {
                message = " [ ]";
            }
            System.out.print(message);
            last = printSnakesAndLaddersBoardRow(current.getPrevious(), columnCount - 1);
        }
        if (last == null) {
            last = current;
        }
        return last;
    }

    private Box printSnakesAndLaddersRowInverted(Box current, int columnCount) {
        Box last = null;
        String message = "";
        if (current != null && columnCount > 0) {
            last = printSnakesAndLaddersRowInverted(current.getPrevious(), columnCount - 1);
            if (current.getSnake() != null && current.getStair() == null) {
                message = " [" + current.getSnake().getName() + "]";
            } else if (current.getStair() != null && current.getSnake() == null) {
                message = " [" + current.getStair().getName() + "]";
            } else if (current.getSnake() == null && current.getStair() == null) {
                message = " [ ]";
            }
            System.out.print(message);

        } else {
            System.out.println();
        }
        if (last == null) {
            last = current;
        }
        return last;
    }
}