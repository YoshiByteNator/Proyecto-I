/**
 * @class Ahorcado
 * @brief Implementa el juego del ahorcado para dos jugadores.
 * @details El Jugador 1 ingresa una palabra secreta y el Jugador 2 intenta adivinarla
 *          letra por letra, con un máximo de 6 intentos fallidos.
 */
import java.util.Scanner;

public class Ahorcado {
     /**
     * @brief Punto de entrada principal del juego.
     
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
             for (int ronda = 1; ronda <= 2; ronda++) {
            String jugadorPregunta = (ronda == 1) ? "Jugador 1" : "Jugador 2";
            String jugadorAdivina = (ronda == 1) ? "Jugador 2" : "Jugador 1";

            System.out.println("\n=== Turno de " + jugadorPregunta + " para escribir la palabra secreta ===");
            System.out.print(jugadorPregunta + ", ingrese su palabra secreta: ");
            String palabraSecreta = scanner.nextLine().toLowerCase(); // Convertir a minúscula para evitar problemas

            // Limpiar la pantalla 
            for (int i = 0; i < 50; i++) System.out.println();
        
            // Inicializar  arreglo de progreso con guiones bajos
            char[] progreso = new char[palabraSecreta.length()];
            for (int i = 0; i < progreso.length; i++) {
                progreso[i] = '_';
            }

            // Mostrar el estado inicial de la palabra a adivinar
            System.out.print("Palabra a adivinar: ");
            for (char letra : progreso) {
                System.out.print(letra + " ");
            }
            System.out.println();

            // Adivinar letras, maximo de 6 intentos
            int intentos = 6;
            boolean palabraAdivinada = false;

            // SE repite mientras queden intentos y  letras por adivinar
            while (intentos > 0 && !palabraAdivinada) {
                System.out.println("Intentos restantes: " + intentos);
                System.out.print(jugadorAdivina + ", ingresa una letra: ");
                char letraIngresada = scanner.nextLine().toLowerCase().charAt(0); // Convertir a minúscula e ingresar solo el primer carácter

                boolean acierto = false;
                //Verifica cada posición de la palabra secreta
                for (int i = 0; i < palabraSecreta.length(); i++) {
                    if (palabraSecreta.charAt(i) == letraIngresada && progreso[i] == '_') {
                        // Si la letra está en la palabra secreta y no ha sido adivinada, se actualiza el progreso
                        progreso[i] = letraIngresada;
                        acierto = true;
                    } else if (palabraSecreta.charAt(i) == letraIngresada && progreso[i] != '_') {
                        acierto = true;
                    } // Si la letra ya fue adivinada, no se cuenta como intento         
                }

                if (!acierto)  // Si la letra no está en la palabra secreta, se resta un intento
                {
                    intentos--;
                    System.out.println("Incorrecto! Pierdes un intento.");
                } else {
                    System.out.println("Correcto!");
                }

                // Mostrar progreso tras el intento
                System.out.print("Palabra: ");
                for (char l : progreso) {
                    System.out.print(l + " ");
                }
                System.out.println();

                // Verifica si ya no quedan guiones bajos
                palabraAdivinada = true;
                for (char l : progreso) {
                    if (l == '_') {
                        palabraAdivinada = false;
                        break;
                    }
                }
            }

            // Mensaje de victoria o derrota
            if (palabraAdivinada) {
                System.out.println(" (:  Felicidades! " + jugadorAdivina +  " Has adivinado la palabra: " + palabraSecreta);
            } else {
                System.out.println(" ):  Te  quedaste sin intentos! La palabra era: " + palabraSecreta);
            }
        }
    }
}
