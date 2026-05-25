import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Graph {


     String name;
     List<String[]> edges = new ArrayList<>();
     Set<String> vertices = new HashSet<>();

    public Graph(String name ) {
        this.name = name;
    }

    public void addEdges(String source, String target , int weight) {
        edges.add(new String[]{ source,target, String.valueOf(weight)});
        vertices.add(source);
        vertices.add(target);
    }

    public void CreateGraphManual(){
        boolean stop = false;
        Scanner keyboard = new Scanner(System.in);
        while (!stop){
            System.out.print("Enter the source : ");
            String s = keyboard.next();

            System.out.print("Enter the target : ");
            String t = keyboard.next();

            System.out.print("Enter the weight : ");
            int w = keyboard.nextInt();

            addEdges( s, t, w);

            System.out.print("Do you want to add another adge ? (yes/not): ");
            String cont = keyboard.next();
            if (!cont.equals("yes")){
                stop = true;
            }
        }
    }

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
    public void PrintGraph(){
        System.out.println("Name : " + name);
        for (String[] e : edges){
            System.out.println(e[0]+"->"+e[1]+"("+e[2]+")");
        }
    }


    public int  calculateDegree(String nodo){
        int grado =0 ;
        for (String[] e : edges)
            if(e[0].equals(nodo) || e[1].equals(nodo)){
                grado++;
            }
        return grado;
    }

    public boolean tipGraph(){

        Set<String> unions = new HashSet<>();

        for (String[] e : edges){

            if(e[0].equals(e[1]))return false;

            String edge_a = e[0]+"-"+e[1];
            String edge_b = e[1]+"-"+e[0];

            if(unions.contains(edge_a)||unions.contains(edge_b)) return false;

            unions.add(edge_a);

        }

        return true;
    }

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


}
