import java.util.ArrayList;
import edu.princeton.cs.algs4.Out;
import edu.princeton.cs.algs4.StopwatchCPU;

public class Experimento {
    //a
    public static void experimento1() {
        // Implementación del experimento 1
         int[] tamanos = {1024, 2048, 4096, 8192, 16384, 32768};
        String[] algoritmos = {"insertionSort", "selectionSort", "mergeSort", "quickSort"};

        for (int n : tamanos) {
            // Se crea un archivo CSV independiente para cada tamaño 'n'
            Out archivoCSV = new Out("sort_" + n + ".csv");
            
            // Escribir la cabecera obligatoria del CSV
            archivoCSV.println("instancia,insertionSort,selectionSort,mergeSort,quickSort");

            for (int i = 0; i < 100; i++) {
                long semilla = n + i;
                
                // 1. Generar la base de datos original (desordenada)
                ArrayList<Song> baseOriginal = DataGenerator.generateDataBase(n, semilla);
                
                // Arreglo para guardar los 4 tiempos de esta instancia
                double[] tiempos = new double[4];

                for (int a = 0; a < algoritmos.length; a++) {
                    String algoritmo = algoritmos[a];

                    // REGLA: Crear copia independiente FUERA de la medición
                    ArrayList<Song> copiaLista = new ArrayList<>(baseOriginal);
                    SongDataBase db = new SongDataBase(copiaLista);

                    // 2. Medir exclusivamente el algoritmo de ordenamiento por "plays"
                    StopwatchCPU cronometro = new StopwatchCPU();
                    db.ordenarPorAlgoritmo(algoritmo, "plays"); 
                    double tiempoEjecucion = cronometro.elapsedTime();

                    tiempos[a] = tiempoEjecucion;
                }

                // 3. Guardar los resultados de la instancia en el CSV
                archivoCSV.printf("%d,%.6f,%.6f,%.6f,%.6f\n", 
                    i, tiempos[0], tiempos[1], tiempos[2], tiempos[3]);
            }
            
            archivoCSV.close();
            System.out.println("Finalizado tamaño n = " + n + ". Archivo guardado.");

    }

    public static void experimento2() {
        // Implementación del experimento 2
        // Tamaños definidos por el enunciado
        int[] tamanos = {1024, 2048, 4096, 8192, 16384, 32768};

        for (int n : tamanos) {
            // Crear archivo CSV para los resultados de búsqueda de este tamaño
            Out archivoCSV = new Out("search_" + n + ".csv");
            
            // Cabecera solicitada por el enunciado
            archivoCSV.println("instancia,artista,t_lineal,t_binaria");

            // Definir los nombres de los 5 artistas basados en sus índices fijos
            String[] artistasBusqueda = {
                "Artista_0",                          // Primero (0)
                "Artista_" + (n / 200),               // 25%
                "Artista_" + (n / 100),               // 50%
                "Artista_" + ((3 * n) / 200),         // 75%
                "Artista_" + ((n / 50) - 1)           // Último
            };

            for (int i = 0; i < 100; i++) {
                long semilla = n + i;
                
                // 1. Generar la base de datos original (desordenada)
                ArrayList<Song> baseOriginal = DataGenerator.generateDataBase(n, semilla);

                for (String artista : artistasBusqueda) {
                    
                    // --- A) BÚSQUEDA LINEAL ---
                    // Se trabaja sobre el objeto desordenado directamente
                    SongDataBase dbLineal = new SongDataBase(new ArrayList<>(baseOriginal));
                    
                    StopwatchCPU cronoLineal = new StopwatchCPU();
                    for (int r = 0; r < 1000; r++) {
                        dbLineal.sequentialSearch(artista); 
                    }
                    double t_lineal = cronoLineal.elapsedTime();

                    // --- B) BÚSQUEDA BINARIA ---
                    // REGLA: Clonar y ordenar por 'artist' ANTES de medir el tiempo de búsqueda
                    ArrayList<Song> copiaBinaria = new ArrayList<>(baseOriginal);
                    SongDataBase dbBinaria = new SongDataBase(copiaBinaria);
                    
                    // Se ordena utilizando un algoritmo rápido (ej: quickSort o mergeSort)
                    dbBinaria.ordenarPorAlgoritmo("quickSort", "artist"); 

                    // Iniciamos el cronómetro únicamente para las repeticiones de la búsqueda
                    StopwatchCPU cronoBinaria = new StopwatchCPU();
                    for (int r = 0; r < 1000; r++) {
                        dbBinaria.binarySearch(artista);
                    }
                    double t_binaria = cronoBinaria.elapsedTime();

                    // 3. Guardar los registros en el archivo CSV
                    archivoCSV.printf("%d,%s,%.6f,%.6f\n", i, artista, t_lineal, t_binaria);
                }
            }
            archivoCSV.close();
            System.out.println("Finalizado Experimento 2 para n = " + n);
        }
    }
    public static void main(String[] args) {
        //experimento 1: ordenamiento por "plays" con los algoritmos: insertionSort, selectionSort, mergeSort y quickSort
        experimento1();
        experimento2();
        }
    }
}
