package projectGame;

import java.util.List;
import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static GamingPlatformFacade platform = new GamingPlatformFacade();

    public static void main(String[] args) {
        showMainMenu();
    }

    private static void showMainMenu() {
        while (true) {
            System.out.println("\n=== PLATAFORMA DE VIDEOJUEGOS ===");
            System.out.println("1. Registrar jugador");
            System.out.println("2. Conectar a servidor");
            System.out.println("3. Jugar");
            System.out.println("4. Ver rankings");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");

            int option = readInt(1, 5);

            switch (option) {
                case 1:
                    registerPlayer();
                    break;
                case 2:
                    connectToServerOption();
                    break;
                case 3:
                    gameMenu();
                    break;
                case 4:
                    viewRankings();
                    break;
                case 5:
                    System.out.println("¡Gracias por usar la plataforma!");
                    return;
            }
        }
    }

    private static void registerPlayer() {
        
        System.out.println("\n--- REGISTRO DE JUGADOR ---");
        System.out.print("Ingrese ID de jugador: ");
        String id = scanner.nextLine();
        System.out.print("Ingrese nombre de jugador: ");
        String name = scanner.nextLine();

        if (platform.registerPlayer(id, name)) {
            System.out.println("¡Jugador registrado con éxito!");
        } else {
            System.out.println("Error: El ID ya está en uso.");
        }
    }

    private static void connectToServerOption() {
        
        System.out.println("\n--- CONEXIÓN A SERVIDOR ---");
        System.out.print("Ingrese su ID de jugador: ");
        String id = scanner.nextLine();

        List<String> servers = platform.getAvailableServers();
        System.out.println("\nServidores disponibles:");
        for (int i = 0; i < servers.size(); i++) {
            System.out.println((i + 1) + ". " + servers.get(i));
        }

        System.out.print("Seleccione un servidor (1-" + servers.size() + "): ");
        int selection = readInt(1, servers.size());

        if (platform.connectToServer(id, servers.get(selection - 1))) {
            System.out.println("¡Conectado al servidor con éxito!");
        } else {
            System.out.println("Error: ID no válido o servidor no disponible.");
        }
    }

    private static void gameMenu() {
        
        System.out.println("\n--- MENÚ DE JUEGOS ---");
        System.out.print("Ingrese su ID de jugador: ");
        String id = scanner.nextLine();

        List<String> games = platform.getAvailableGames();
        System.out.println("\nJuegos disponibles:");
        for (int i = 0; i < games.size(); i++) {
            System.out.println((i + 1) + ". " + games.get(i));
        }

        System.out.print("Seleccione un juego (1-" + games.size() + "): ");
        int selection = readInt(1, games.size());

        System.out.println("\n1. Iniciar partida");
        System.out.println("2. Finalizar partida");
        System.out.print("Seleccione opción: ");
        int action = readInt(1, 2);

        if (action == 1) {
            if (platform.startGameSession(id, games.get(selection - 1))) {
                System.out.println("¡Partida iniciada con éxito!");
            } else {
                System.out.println("Error: No se pudo iniciar la partida.");
            }
        } else {
            System.out.print("Ingrese su puntaje: ");
            int score = readInt(0, Integer.MAX_VALUE);
            if (platform.endGameSession(id, score)) {
                System.out.println("¡Partida finalizada con éxito!");
            } else {
                System.out.println("Error: No tienes partidas activas.");
            }
        }
    }

    private static void viewRankings() {
        
        System.out.println("\n--- RANKINGS ---");
        List<String> games = platform.getAvailableGames();

        for (String game : games) {
            System.out.println("\n" + game + ":");
            List<PlayerScore> ranking = platform.getRanking(game);
            if (ranking.isEmpty()) {
                System.out.println("No hay puntajes registrados");
            } else {
                for (int i = 0; i < ranking.size(); i++) {
                    System.out.println((i + 1) + ". " + ranking.get(i));
                }
            }
        }
    }

    private static int readInt(int min, int max) {
        
        while (true) {
            try {
                int value = Integer.parseInt(scanner.nextLine());
                if (value >= min && value <= max) {
                    return value;
                }
                System.out.print("Por favor ingrese un valor entre " + min + " y " + max + ": ");
            } catch (NumberFormatException e) {
                System.out.print("Entrada inválida. Por favor ingrese un número: ");
            }
        }
    }
}
