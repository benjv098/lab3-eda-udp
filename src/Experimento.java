import java.util.ArrayList;
import edu.princeton.cs.algs4.Out;
import edu.princeton.cs.algs4.StopwatchCPU;

public class Experimento {
    
    // --- EXPERIMENTO 1 ---
    public static void experimento1() {
        int[] tamanos = {1024, 2048, 4096, 8192, 16384, 32768};
        String[] algoritmos = {"insertionSort", "selectionSort", "mergeSort", "quickSort"};

        for (int n : tamanos) {
            Out archivoCSV = new Out("sort_" + n + ".csv");
            archivoCSV.println("instancia,insertionSort,selectionSort,mergeSort,quickSort");

            for (int i = 0; i < 100; i++) {
                long semilla = n + i;
                ArrayList<Song> baseOriginal = DataGenerator.generateDataBase(n, semilla);
                double[] tiempos = new double[4];

                for (int a = 0; a < algoritmos.length; a++) {
                    String algoritmo = algoritmos[a];

                    ArrayList<Song> copiaLista = new ArrayList<>(baseOriginal);
                    SongDataBase db = new SongDataBase(copiaLista);

                    StopwatchCPU cronometro = new StopwatchCPU();
                    db.ordenarPorAlgoritmo(algoritmo, "plays"); 
                    double tiempoEjecucion = cronometro.elapsedTime();

                    tiempos[a] = tiempoEjecucion;
                }

                archivoCSV.printf("%d,%.6f,%.6f,%.6f,%.6f\n", 
                    i, tiempos[0], tiempos[1], tiempos[2], tiempos[3]);
            }
            
            archivoCSV.close();
        } 
    }

    // --- EXPERIMENTO 2 ---
    public static void experimento2() {
        int[] tamanos = {1024, 2048, 4096, 8192, 16384, 32768};

        for (int n : tamanos) {
            Out archivoCSV = new Out("search_" + n + ".csv");
            archivoCSV.println("instancia,artista,t_lineal,t_binaria");

            String[] artistasBusqueda = {
                "Artista_0",                          
                "Artista_" + (n / 200),               
                "Artista_" + (n / 100),               
                "Artista_" + ((3 * n) / 200),         
                "Artista_" + ((n / 50) - 1)           
            };

            for (int i = 0; i < 100; i++) {
                long semilla = n + i;
                ArrayList<Song> baseOriginal = DataGenerator.generateDataBase(n, semilla);

                for (String artista : artistasBusqueda) {
                    
                    // A) busqueda lineal
                    SongDataBase dbLineal = new SongDataBase(new ArrayList<>(baseOriginal));
                    
                    StopwatchCPU cronoLineal = new StopwatchCPU();
                    for (int r = 0; r < 1000; r++) {
                        dbLineal.sequentialSearch(artista); 
                    }
                    double t_lineal = cronoLineal.elapsedTime();

                    // B)busqueda binaria
                    ArrayList<Song> copiaBinaria = new ArrayList<>(baseOriginal);
                    SongDataBase dbBinaria = new SongDataBase(copiaBinaria);
                    
                    dbBinaria.ordenarPorAlgoritmo("quickSort", "artist"); 

                    StopwatchCPU cronoBinaria = new StopwatchCPU();
                    for (int r = 0; r < 1000; r++) {
                        dbBinaria.binarySearch(artista);
                    }
                    double t_binaria = cronoBinaria.elapsedTime();

                    archivoCSV.printf("%d,%s,%.6f,%.6f\n", i, artista, t_lineal, t_binaria);
                }
            }
            archivoCSV.close();
        } 
    } 

    // --- MAIN ---
    public static void main(String[] args) {
        experimento1();
        experimento2();
    } 
} 