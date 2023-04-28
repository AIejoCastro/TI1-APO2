/**
 * La clase Score representa el puntaje de un jugador.
 */
public class Score {
    Player player; // Objeto jugador
    BSTScore bstScore; // Objeto árbol de puntajes

    /**
     * Calcula el puntaje del jugador utilizando la fórmula (600 - t) / 6.0.
     * Si el puntaje es negativo, se establece en cero.
     * @param t el tiempo en segundos que tardó el jugador en completar la tarea
     * @return el puntaje del jugador como un valor entero
     */
    public int playerScore(double t){

        double puntaje = (600 - t) / 6.0; // Calcula el puntaje usando la fórmula

        if (puntaje < 0) {
            puntaje = 0; // Si el puntaje es negativo, se establece en

        }

        return (int) puntaje;
    }

}