public class Score  {
    Player player;
    BSTScore bstScore;
    public int playerScore(double t){

        double puntaje = (600 - t) / 6.0; // Calcula el puntaje usando la fórmula

        if (puntaje < 0) {
            puntaje = 0; // Si el puntaje es negativo, se establece en

        }

        return (int) puntaje;
    }

    /**
     * metodo utilizado para imprimir el puntaje  de los jugadores haciendo un bucle hasta que imprima
     * todos los puntajes
     * @param player
     */


}