package models;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Graph {

    /**
     * Nombre asignado al grafo.
     */
    String name;

    /**
     * Lista de aristas del grafo.
     * Cada arista se almacena como un arreglo de tres posiciones:
     * [origen, destino, peso].
     */
    public List<String[]> edges = new ArrayList<>();

    /**
     * Conjunto de vértices que pertenecen al grafo.
     */
    Set<String> vertices = new HashSet<>(); // Puntos o nodos donde se unen las aristas

    /**
     * Construye un nuevo grafo con el nombre especificado.
     *
     * @param name Nombre del grafo.
     */
    public Graph(String name ) {
        this.name = name;
    }

    /**
     * Agrega una arista al grafo junto con sus vértices.
     * Si los vértices no existen, se agregan automáticamente.
     *
     * @param source Vértice de origen.
     * @param target Vértice de destino.
     * @param weight Peso de la arista.
     */
    public void addEdges(String source, String target , int weight) {
        edges.add(new String[]{ source,target, String.valueOf(weight)});// Agrega aristas a la lista
        vertices.add(source); //Agrega vertices a la lista
        vertices.add(target); //Agrega vertices a la lista
    }


    /**
     * Carga un grafo desde un archivo de texto.
     * El archivo debe tener el siguiente formato:
     *
     * origen,destino,peso
     *
     * La primera línea del archivo se considera encabezado y es ignorada.
     *
     * @param path Ruta del archivo de texto.
     */
    public void InsertTextFile(String path) {
        try (BufferedReader read = new BufferedReader(new FileReader(path))) {
            String line;
            read.readLine();

            while ((line = read.readLine())!= null){
                String[] parts = line.split(",");
                String source = parts[0];
                String target = parts[1];
                int weight = Integer.parseInt(parts[2]);

                addEdges(source,target, weight);
            }

        } catch (IOException e) {
            System.out.println("error : " + e.getMessage());
        }

    }


    /**
     * Calcula el grado de un vértice.
     * El grado corresponde al número de aristas que inciden sobre él.
     *
     * @param nodo Vértice del cual se desea calcular el grado.
     * @return Grado del vértice.
     */
    public int  calculateDegree(String nodo){
        int grado =0 ;
        for (String[] e : edges)
            if(e[0].equals(nodo) || e[1].equals(nodo)){
                grado++;
            }
        return grado;
    }

    /**
     * Determina si el grafo es simple.
     * Un grafo simple no posee lazos ni aristas duplicadas.
     *
     * @return true si el grafo es simple; false en caso contrario.
     */
    public boolean tipGraph(){

        Set<String> unions = new HashSet<>();

        for (String[] e : edges){

            // Verifica que no existan lazos
            if(e[0].equals(e[1]))return false;

            String edge_a = e[0]+"-"+e[1];
            String edge_b = e[1]+"-"+e[0];

            // Verifica que no existan aristas repetidas
            if(unions.contains(edge_a)||unions.contains(edge_b)) return false;

            unions.add(edge_a);

        }

        return true;
    }

    /**
     * Determina si el grafo es completo.
     * Un grafo completo es aquel donde todos los vértices
     * están conectados entre sí.
     *
     * @return true si el grafo es completo; false en caso contrario.
     */
    public boolean isComplet(){
        int nodo_numbers = vertices.size();
        int max_edges = nodo_numbers*(nodo_numbers-1)/2;

        Set<String> unions = new HashSet<>();

        for (String[] e : edges){
            String edge_a = e[0]+"-"+e[1];
            String edge_b = e[1]+"-"+e[0];

            if(!unions.contains(edge_b)){
                unions.add(edge_a);
            }

        }

        return  unions.size() == max_edges;

    }

    /**
     * Verifica si el grafo tiene un camino de Euler
     *
     * Un grafo posee un camino de Euler si este es conexo
     * y tiene menos de dos vertices de grado impar
     *
     * @return true si existe un camino de Euler; false en caso contrario.
     */
    public boolean hasEulerPath(){

        if(!isConnected()){
            return false;
        }
        int oddVertices = 0;

        for (String verice : vertices){

            if(calculateDegree(verice) % 2 != 0){
                oddVertices++;
            }
        }

        return oddVertices <= 2;
    }


    /**
     * Verifica si el grafo tiene un circuito de Euler
     *
     * Un grafo posee un circuito de Euler si este es conexo
     * y todos sus vertices son de grado impar
     *
     * @return true si existe un circuito de Euler; false en caso contrario.
     */
    public boolean hasEulerCircuit(){

        if(!isConnected()){
            return false;
        }

        for (String verice : vertices){

            if(calculateDegree(verice) % 2 != 0){
                return false;
            }
        }
        return true;
    }

    /**
     * Determina si el grafo es un bosque
     *
     * Un bosque es un grafo sin ciclos. Formado por uno o varios arboles
     *
     * @return true si el grafo es un arbol, false en caso contrario
     */
    public boolean isforest(){

        Set<String> nodos = new HashSet<>();

        for (String vertice : vertices){
            if(!nodos.contains(vertice)){
                if(hasCycle(vertice,null,nodos)){
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * verifica si en el grafo es un ciclo
     *
     * @param verticeActual: vertice de inicio
     * @param VerticeAnterior : vertice anterios
     * @param nodos : lista de vertices
     * @return false si no tiene ciclos, true en caso contrario
     */
    public boolean hasCycle(String verticeActual, String VerticeAnterior, Set<String> nodos){
        nodos.add(verticeActual);
        for (String neighbor : getNeighbors(verticeActual)){
            if(!nodos.contains(neighbor)){
                if(hasCycle(neighbor,verticeActual, nodos)){
                    return true;
                }
            } else if (!neighbor.equals(VerticeAnterior)) {
                return true;
            }
        }
        return false;
    }


    /**
     * Verifica si un grafo es un arbol
     * (El grafo debe de ser conexo y no debe de tener ciclos)
     * para no tener ciclos dece cumplir :
     * Numero de aristas = Numero de vertices - 1
     * @return true si el grafo es un árbol; false en caso contrario.
     */
    public boolean isTree(){
        int verticesNum = vertices.size();
        int edgesNum = edges.size();
        if(edgesNum==verticesNum-1 && isConnected()){
            return true;
        }
        return false;
    }

    /**
     * Verifica si un grafo es conexo
     * recorriendo el grafo para ver si desde un punto de inicio
     * puedo llegar a cualquier otro nodo del grafo
     */
    public boolean isConnected() {


        if (vertices.isEmpty()) {
            return true;
        }

        Set<String> nodos = new HashSet<>();

        String start = vertices.iterator().next();

        traverseGraph(start , nodos);

        return nodos.size() == vertices.size();
    }

    /**
     * Extrae las conecciones de un vertice
     * @param vertice : vertice a evaluar
     * @return neighbors : las conecciones del vertice
     */
    public List<String> getNeighbors(String vertice) {
        List<String> neighbors = new ArrayList<>();

        for (String[] edge : edges ){

            if (edge[0].equals(vertice)) {
                neighbors.add(edge[1]);
            }

            if (edge[1].equals(vertice)) {
                neighbors.add(edge[0]);
            }
        }
        return neighbors;
    }

    /**
     * Metodo para recorrer un grafo
     */
    public void traverseGraph(String vertice, Set<String> nodos){

        nodos.add(vertice);

        for(String neighbor : getNeighbors(vertice)){

            if(!nodos.contains(neighbor)){
                traverseGraph(neighbor,nodos);
            }
        }
    }

    /**
     * Metodo encargado de retornar el nombre de el grafo
     */
    public String getName() {
        return name;
    }
}
