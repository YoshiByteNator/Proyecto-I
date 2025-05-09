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

    // Constructor
    public BatallaNaval() {
        this.tableroJugador1 = new char[tamanoTablero][tamanoTablero];
        this.tableroJugador2 = new char[tamanoTablero][tamanoTablero];
        this.tableroDisparos1 = new char[tamanoTablero][tamanoTablero];
        this.tableroDisparos2 = new char[tamanoTablero][tamanoTablero];
      
    }

    // Método para inicializar un tablero específico
    private void inicializarTablero(char[][] tablero) {
        for(int i = 0; i < tamanoTablero; i++) {
            for(int j = 0; j < tamanoTablero; j++) {
                tablero[i][j] = vacio;
            }
        }
    }
        // Método para colocar barcos en el tablero de un jugador
    public void colocarBarcos(int jugador) {
        char[][] tablero;
    
        if(jugador == 1) {
            tablero = tableroJugador1;
        } else {
            tablero = tableroJugador2;
        }
    
        inicializarTablero(tablero);
        System.out.println("\nJugador " + jugador + ", coloca tus barcos:");

        int barcosColocados = 0;
        int barcosTotales = 3; // Podemos cambiar este número según queramos
        while(barcosColocados < barcosTotales) {
                
            System.out.println("Barco " + (barcosColocados + 1) + " de " + barcosTotales);
            System.out.print("Ingrese fila (0-" + (tamanoTablero-1) + "): ");
            int fila = input.nextInt();
                
            System.out.print("Ingrese columna (0-" + (tamanoTablero-1) + "): ");
            int columna = input.nextInt();
    
            if(fila >= 0 && fila < tamanoTablero && columna >= 0 && columna < tamanoTablero) {
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
    
    public void imprimirTableroPropio(char[][] tablero) {
        System.out.print("  ");
        for(int i = 0; i < tamanoTablero; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
    
        for(int i = 0; i < tamanoTablero; i++) {
            System.out.print(i + " ");
            for(int j = 0; j < tamanoTablero; j++) {
                // Mostrar barcos (B), aciertos (X), fallos (!) y vacíos (-)
                System.out.print(tablero[i][j] + " ");
            }
            System.out.println();
        }
    }

    // Método para imprimir el tablero de disparos (ocultando los barcos del oponente)
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
                if(tableroDisparos[i][j] == acierto || tableroDisparos[i][j] == fallo) {
                    System.out.print(tableroDisparos[i][j] + " ");
                } else {
                    System.out.print(vacio + " ");
                }
            }
            System.out.println();
        }
    }
    public boolean realizarDisparo(int jugadorDisparando) {
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
        // Método para verificar si un jugador ha ganado
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