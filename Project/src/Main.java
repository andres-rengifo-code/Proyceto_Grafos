import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=============================");
        System.out.println("    PROYECTO DE GRAFOS :D    ");
        System.out.println("=============================");
        System.out.println("Ingresa el nombre para el grafo: ");
        String graphName = scanner.nextLine();

        Graph myGraph = new Graph(graphName);

        boolean exit = false;
        while (!exit) {
            System.out.println("\n----------------------");
            System.out.println("     MENÚ PRINCIPAL     ");
            System.out.println("------------------------");
            System.out.println("1. Cargar garfo manualmente");
            System.out.println("2. Cargar grafo desde archivo de texto (.txt / .csv)");
            System.out.println("3. Mostrar grafo actual");
            System.out.println("4. Calcular el grado");
            System.out.println("5. Verificar grafo");
            System.out.println("6. Salir");
            System.out.println("Selecciona una de las opciones");

            int option = scanner.nextInt();
            switch (option) {
                case 1:
                    System.out.println("\n--- Creación Manual del Grafo ---");
                    myGraph.CreateGraphManual();
                    break;
                case 2:
                    System.out.println("\n--- Cargar desde Archivo ---");
                    System.out.print("Ingresa el nombre del archivo:  ");
                    String path = scanner.next();

                    myGraph.InsertTextFile(path);
                    break;

                case 3:
                    System.out.println("\n--- Estructura del Grafo Actual ---");
                    myGraph.PrintGraph();
                    break;

                case 4:
                    System.out.println("\n--- Calcular Grado ---");
                    System.out.print("Ingresa el nombre del nodo a consultar: ");
                    String nodo = scanner.next();
                    int grado = myGraph.calculateDegree(nodo);
                    System.out.println("El grado del nodo '" + nodo + "' es: " + grado);
                    break;

                case 6:
                    System.out.println("\n¡Gracias por usar el programa de grafos! Saliendo...");
                    exit = true;
                    break;

                default:
                    System.out.println("\n[Error] Opción no válida. Intente de nuevo.");
                    break;


            }

        }

        scanner.close();
    }
}