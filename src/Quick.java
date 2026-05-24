import java.util.ArrayList;
import java.util.Comparator;
import edu.princeton.cs.algs4.StdRandom;
public class Quick {
    
    // This class should not be instantiated.
    private Quick() { }

   public static void sort(ArrayList<Song> songs, Comparator<Song> comparator) {
        int n = songs.size();
        
        //adaptado para arraylist
        for (int i = 0; i < n; i++) {
            int r = StdRandom.uniform(i, n); //esta obsoleto, pero no cambia nada
            exch(songs, i, r); 
        }
        
        //se le pasa el coomparador a la función recursiva
        sort(songs, 0, n - 1, comparator);
    }

    private static void sort(ArrayList<Song> songs, int lo, int hi, Comparator<Song> comparator) {
        if (hi <= lo) return;
        
        
        int j = partition(songs, lo, hi, comparator);
        
        
        sort(songs, lo, j - 1, comparator);
        sort(songs, j + 1, hi, comparator);
    }

    // partition the subarray a[lo..hi] so that a[lo..j-1] <= a[j] <= a[j+1..hi]
    // and return the index j.
    private static int partition(ArrayList<Song> songs, int lo, int hi, Comparator<Song> comparator) {
        int i = lo;
        int j = hi + 1;
        Song v = songs.get(lo);
        while (true) {

            // find item on lo to swap
            while (less(comparator, songs.get(++i), v)) {
                if (i == hi) break;
            }

            // find item on hi to swap
            while (less(comparator, v, songs.get(--j))) {
                if (j == lo) break;      // redundant since a[lo] acts as sentinel
            }

            // check if pointers cross
            if (i >= j) break;

            exch(songs, i, j);
        }

        // put partitioning item v at a[j]
        exch(songs, lo, j);

        // now, a[lo .. j-1] <= a[j] <= a[j+1 .. hi]
        return j;
    }


   /***************************************************************************
    *  Helper sorting functions.
    ***************************************************************************/

    // is v < w ?
    private static boolean less(Comparator<Song> comparator, Song v, Song w) {
        if (v == w) return false;   // optimization when reference equals
        return comparator.compare(v, w) < 0;
    }

    // exchange a[i] and a[j]
    private static void exch(ArrayList<Song> songs, int i, int j) {
        Song swap = songs.get(i);
        songs.set(i, songs.get(j));
        songs.set(j, swap);
    }
}