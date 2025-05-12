import java.util.Scanner;

/**
 * @class MenuPrincipal
 * @briefM Menú principal para seleccionar entre los juegos disponibles.
 * @details Ofrece opciones para jugar Ahorcado, Batalla Naval o salir del sistema.
 *          Maneja la navegación entre juegos y la salida del programa.
 */
public class MenuPrincipal {
      /**
     * @brief Método principal
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean seguir = true;

        while (seguir) {
            System.out.println("\n=== Menu Principal ===");
            System.out.println("1. Jugar Ahorcado");
            System.out.println("2. Jugar Batalla Naval");
            System.out.println("3. Salir");
            System.out.print("Seleccione (1-3): ");
            String opcion = scanner.nextLine();

            switch (opcion) {
                case "1":
                    Ahorcado.main(new String[]{});
                    break;
                case "2":
                    BatallaNaval.main(new String[]{});
                    break;
                case "3":
                    seguir = false;
                    System.out.println("¡Gracias por jugar!");
                    continue;
                default:
                    System.out.println("Opción inválida.");
            }

            // Preguntar si vuelve al menú o sale
            if (seguir) {
                System.out.print("\n¿Volver al menú principal? (si/no): ");
                String respuesta = scanner.nextLine().trim().toLowerCase();
                seguir = respuesta.equals("si") || respuesta.equals("s");                
                if (!seguir) {
                    System.out.println("¡Chao, gracias por jugar!");
                }
            }
        }
        scanner.close();
    }
}
