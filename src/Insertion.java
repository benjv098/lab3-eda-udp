import java.util.ArrayList;
import java.util.Comparator;


public class Insertion {
    
    // This class should not be instantiated.
    private Insertion() { }

    /**
     * Rearranges the array in ascending order, using a comparator.
     * @param a the array
     * @param comparator the comparator specifying the order
     */
    
    //el cambio que se realizo para este metodo fue cambiar el tipo de dato del arreglo a ArrayList<Song> 
    //y agregar un parametro de tipo Comparator<Song> para poder comparar los objetos Song segun el atributo que se desee ordenar
    public static void sort(ArrayList<Song> songs, Comparator<Song> comparator) {
        int n = songs.size();
        for (int i = 1; i < n; i++) {
            for (int j = i; j > 0 && less(songs.get(j), songs.get(j-1), comparator); j--) {
                exch(songs, j, j-1);
            }
        }
    }

    // se reemplaza el 'less' original por este:
    private static boolean less(Song v, Song w, Comparator<Song> comparator) {
        return comparator.compare(v, w) < 0;
    }

    // se reemplaza el 'exch' original por este:
    private static void exch(ArrayList<Song> songs, int i, int j) {
        Song swap = songs.get(i);
        songs.set(i, songs.get(j));
        songs.set(j, swap);
    }
}