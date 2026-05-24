import java.util.ArrayList;
import edu.princeton.cs.algs4.StdRandom;

public class DataGenerator {

    public static ArrayList<Song> generateDataBase(int n, long seed) {
        //semilla
        StdRandom.setSeed(seed);

        // arreglo de artistas
        int numArtists = n / 50;
        if (numArtists == 0){
            numArtists = 1; // por si n es muy pequeñom, para evitar división por cero y asegurar al menos un artista.
        } 

        String[] artists = new String[numArtists];
        for (int i = 0; i < numArtists; i++) {
            artists[i] = "Artista_" + i;
        }

        String[] genres = {"Pop", "Rock", "Jazz", "Electronic", "Classical", "Hip-Hop"};

        ArrayList<Song> dataBase = new ArrayList<>(n);

        
        for (int id = 1; id <= n; id++) {
            String title = "Song " + id; 
            String artist = artists[StdRandom.uniformInt(artists.length)];
            String genre = genres[StdRandom.uniformInt(genres.length)];

            int year = StdRandom.uniformInt(1970, 2027);
            int plays = StdRandom.uniformInt(0, 10000001);
            
            // se agrega a la db 
            dataBase.add(new Song(id, title, artist, genre, year, plays));
        }

        return dataBase;
    }
}