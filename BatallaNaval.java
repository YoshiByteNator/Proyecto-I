 /**
 * @class BatallaNaval
 * @brief Implementa la lógica principal del juego Batalla Naval para dos jugadores.
 * @details Gestiona los tableros de ambos jugadores, disparos, y estado del juego.
 *          Utiliza matrices de caracteres para representar barcos ('B'), aciertos ('X'),
 *          fallos ('!') y espacios vacíos ('-').
  */
import java.util.InputMismatchException;
import java.util.Scanner;

public class BatallaNaval {
    private char[][] tableroJugador1;
    private char[][] tableroJugador2;
    private char[][] tableroDisparos1;
    private char[][] tableroDisparos2;
    private int tamanoTablero = 5;
    private final char barco = 'B';
    private final char vacio = '-';
    private final char acierto = 'X';
    private final char fallo = '!';
    Scanner input = new Scanner(System.in);

    /**
     * @brief Constructor de la clase BatallaNaval.
     * @details Inicializa los tableros de ambos jugadores y los tableros de disparos
     *          con el tamaño predeterminado (5x5). Todos los espacios se llenan como vacíos.
     */
    public BatallaNaval() {
        this.tableroJugador1 = new char[tamanoTablero][tamanoTablero];
        this.tableroJugador2 = new char[tamanoTablero][tamanoTablero];
        this.tableroDisparos1 = new char[tamanoTablero][tamanoTablero];
        this.tableroDisparos2 = new char[tamanoTablero][tamanoTablero];
      
    }

   /**
 * @brief Inicializa un tablero del juego con valores vacíos.
 * @details Recorre todas las celdas del tablero y las establece como vacías ('-').
 *          Este método se usa para preparar tanto los tableros de barcos como los de disparos.
 * @param tablero Matriz de caracteres que representa el tablero a inicializar.
 *                Debe coincidir con el tamaño definido por `tamanoTablero`.

 */
    private void inicializarTablero(char[][] tablero) {
        for(int i = 0; i < tamanoTablero; i++) {
            for(int j = 0; j < tamanoTablero; j++) {
                tablero[i][j] = vacio;
            }
        }
    }
 /**
 * @brief Permite a un jugador colocar sus barcos en el tablero.
 * @details Solicita interactivamente las coordenadas para colocar cada barco,
 *          validando que las posiciones estén dentro del tablero y no estén ocupadas.
 * @param jugador Número del jugador (1 o 2) que está colocando los barcos.
 * @note El número total de barcos está fijado en 3 (modificable cambiando `barcosTotales`).
 *       Usa el objeto `input` de tipo Scanner para leer las coordenadas por consola.
 */
    public void colocarBarcos(int jugador) {
        char[][] tablero;
    
        if(jugador == 1) { //establece la paridad de tableros
            tablero = tableroJugador1;
        } else {
            tablero = tableroJugador2;
        }
    
        inicializarTablero(tablero);
        System.out.println("\nJugador " + jugador + ", coloca tus barcos:");

        int barcosColocados = 0;
        int barcosTotales = 3; // Podemos cambiar este número según queramos
        while(barcosColocados < barcosTotales) { //bucle para controlar la colocación de los barcos
                
            System.out.println("Barco " + (barcosColocados + 1) + " de " + barcosTotales);
            System.out.print("Ingrese fila (0-" + (tamanoTablero-1) + "): ");
            int fila = input.nextInt(); //fila de la matriz
                
            System.out.print("Ingrese columna (0-" + (tamanoTablero-1) + "): ");
            int columna = input.nextInt(); //columna de la matriz
    
            if(fila >= 0 && fila < tamanoTablero && columna >= 0 && columna < tamanoTablero) { //tamañoTablero es el tamaño de la matriz, en lugar de andar con el .length
                if(tablero[fila][columna] == vacio) {
                    tablero[fila][columna] = barco;
                    barcosColocados++;
                } else {
                    System.out.println("¡Ya hay un barco en esa posición!");
                }
            } else {
                System.out.println("¡Posición inválida!");
            }
        }
    }
    /**
 * @brief Imprime en consola el tablero de barcos de un jugador.
 * @details Muestra:
 *          - Encabezado con números de columnas
 *          - Filas numeradas
 *          - Estado de cada celda: Barcos ('B'), aciertos ('X'), fallos ('!') o vacíos ('-')
 * @param tablero Matriz de caracteres que representa el tablero a mostrar.
 *                Debe ser de tamaño `tamanoTablero x tamanoTablero`.
 * @note Diseñado específicamente para mostrar el tablero propio (con ubicación de barcos),
 *       no el de disparos del oponente.
 */
    public void imprimirTableroPropio(char[][] tablero) { //esto es para tener en consola el tablero propio de barcos
        System.out.print("  ");
        for(int i = 0; i < tamanoTablero; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
    
        for(int i = 0; i < tamanoTablero; i++) {
            System.out.print(i + " "); //esto para colocar numeros alas filas y columnas
            for(int j = 0; j < tamanoTablero; j++) {
                // se muestra barcos (B), aciertos (X), fallos (!) y vacíos (-) en el tablero
                System.out.print(tablero[i][j] + " ");
            }
            System.out.println();
        }
    }

   /**
 * @brief Imprime el tablero de disparos de un jugador ocultando el tablero de rival.
 * @details Muestra solo los disparos realizados (aciertos 'X' y fallos '!'), 
 *          ocultando la posición de los barcos no descubiertos del oponente.
 * @param jugador Número del jugador (1 o 2) cuyo tablero de disparos se imprimirá.
 * @note La visualización incluye:
 *       - Encabezado con números de columnas (0 a tamanoTablero-1)
 *       - Filas numeradas
 *       - Solo muestra disparos registrados (no la posición real de los barcos)
 */
    public void imprimirTableroDisparos(int jugador) {
        char[][] tableroDisparos;
        if(jugador == 1) {
            tableroDisparos = tableroDisparos1;
        } else {
            tableroDisparos = tableroDisparos2;
        }
    
        System.out.println("\nTablero de disparos del Jugador " + jugador + ":");
        System.out.print("  ");
        for(int i = 0; i < tamanoTablero; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        // estado del tablero de disparos del jugador actual al rival
        for(int i = 0; i < tamanoTablero; i++) {
            System.out.print(i + " ");
            for(int j = 0; j < tamanoTablero; j++) {
                if(tableroDisparos[i][j] == acierto || tableroDisparos[i][j] == fallo) { //registro del estado de las X o !
                    System.out.print(tableroDisparos[i][j] + " ");
                } else {
                    System.out.print(vacio + " ");
                }
            }
            System.out.println();
        }
    }
    /**
 * @brief Ejecuta el proceso de disparo para un jugador.
 * @details Permite a un jugador realizar un disparo, mostrando ambos tableros (propio y de disparos)
 *          y validando las coordenadas ingresadas. Actualiza el estado del juego según el resultado.
 * @param jugadorDisparando Número del jugador (1 o 2) que realiza el disparo.
 * @return `true` si el disparo fue válido y acertó, `false` si fue inválido o ya se había disparado allí.
 * @note Flujo del método:
 *       1. Muestra ambos tableros (propio y de disparos)
 *       2. Solicita coordenadas
 *       3. Valida posición
 *       4. Actualiza tableros y determina si el turno cambia
 */
    public boolean realizarDisparo(int jugadorDisparando) { //metodo para disparar
        char[][] tableroDisparos;
        char[][] tableroPropio;
        char[][] tableroOponente;
    
        if(jugadorDisparando == 1) {
            tableroDisparos = tableroDisparos1;
            tableroPropio = tableroJugador1;
            tableroOponente = tableroJugador2;
        } else {
            tableroDisparos = tableroDisparos2;
            tableroPropio = tableroJugador2;
            tableroOponente = tableroJugador1;
        }
        // Mostrar ambos tableros al inicio del turno
        System.out.println("\n=== TU TABLERO ===");
        imprimirTableroPropio(tableroPropio);
    
        System.out.println("\n=== TABLERO DEL OPONENTE ===");
        imprimirTableroDisparos(jugadorDisparando);

        
        System.out.println("\nJugador " + jugadorDisparando + ", es tu turno de disparar:");
        System.out.print("Ingrese fila (0-" + (tamanoTablero-1) + "): ");
        int fila = input.nextInt();
        
        System.out.print("Ingrese columna (0-" + (tamanoTablero-1) + "): ");
        int columna = input.nextInt();
        //  Para asegurarse el pase de turno de jugador o su continuación
        if(fila >= 0 && fila < tamanoTablero && columna >= 0 && columna < tamanoTablero) {
            if(tableroDisparos[fila][columna] == acierto || tableroDisparos[fila][columna] == fallo) {
                System.out.println("¡Ya has disparado a esta posición antes!");
                return false; // Disparo inválido, debe cambiar de jugador
            }
    
            if(tableroOponente[fila][columna] == barco) {
                System.out.println("¡Acertaste! Dispara otra vez.");
                tableroDisparos[fila][columna] = acierto;
                tableroOponente[fila][columna] = acierto;
                return true; // Acierto, el mismo jugador dispara otra vez
            } else {
                System.out.println("¡Fallaste!");
                tableroDisparos[fila][columna] = fallo;
                return false; // Fallo, cambia de jugador
            }
        } else {
            System.out.println("¡Posición inválida!");
            return false; // Disparo inválido, debe cambiar de jugador
        }
    }
    /**
 * @brief Verifica si un jugador ha ganado la partida.
 * @details Comprueba si todos los barcos del oponente han sido hundidos
 *          
 * @param jugador Número del jugador (1 o 2) cuyo estado de victoria se verifica.
 * @return `true` si el jugador ha hundido todos los barcos del oponente, `false` si aún quedan barcos.
 * @note Este método debe llamarse después de cada disparo válido para detectar
 *       el fin del juego inmediatamente.
 */
    public boolean verificarGanador(int jugador) {
        // Determinar que tablero revisar según el turno del jugador
        char[][] tableroOponente;
    
        if(jugador == 1) {
            tableroOponente = tableroJugador2;
        } else {
            tableroOponente = tableroJugador1;
        }

        // Verificar si quedan barcos en el tablero del oponente
        for(int i = 0; i < tamanoTablero; i++) {
            for(int j = 0; j < tamanoTablero; j++) {
                if(tableroOponente[i][j] == barco) {
                    return false; // Todavía hay barcos sin hundir
                }
            }
        }
        return true; // Todos los barcos han sido hundidos
    }
    /**
 * @brief Punto de entrada principal del juego Batalla Naval.
 * @details Controla el flujo completo del juego:
 *          1. Inicialización
 *          2. Fase de colocación de barcos
 *          3. Fase de disparos por turnos
 *          4. Detección de ganador
 */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
    
        System.out.println("¡BIENVENIDO A BATALLA NAVAL!");
    
        // Hacer la instancia del juego
        BatallaNaval juego = new BatallaNaval();
    
        // Etapa de colocación de barcos
        System.out.println("\n=== FASE DE COLOCACIÓN DE BARCOS ===");
    
        // Jugador 1 coloca sus barcos
        System.out.println("\nJugador 1, prepara tu flota:");
        juego.colocarBarcos(1);
    
        // Jugador 2 coloca sus barcos
        System.out.println("\nJugador 2, prepara tu flota:");
        juego.colocarBarcos(2);
    
        // Momento de disparos
        System.out.println("\n=== COMIENZA LA BATALLA ===");
        int jugadorActual = 1;
        boolean juegoTerminado = false;
    
        while(!juegoTerminado) {
            System.out.println("\n=================================");
            System.out.println("TURNO DEL JUGADOR " + jugadorActual);
            System.out.println("=================================");
    
            // Realizar disparo y obtener resultado
            boolean esAcierto = juego.realizarDisparo(jugadorActual);
    
            // Verificar si el jugador actual ha ganado
            if(juego.verificarGanador(jugadorActual)) {
                System.out.println("\n¡FELICIDADES JUGADOR " + jugadorActual + "!");
                System.out.println("¡HAS HUNDIDO TODOS LOS BARCOS ENEMIGOS!");
                System.out.println("¡HAS GANADO LA BATALLA NAVAL!");
                juegoTerminado = true;
            } 
            else {
                // Cambiar turno solo si no fue acierto
                if(esAcierto == false) {
                    System.out.println("\nPresiona ENTER para cambiar de jugador...");
                    scanner.nextLine(); // Limpiar buffer
                    scanner.nextLine(); 
                    if(jugadorActual == 1) {
                        jugadorActual = 2;
                    } 
                    else {
                        jugadorActual = 1;
                    }
                }
            }
        }
        System.out.println("\n¡JUEGO TERMINADO!");
    }
}