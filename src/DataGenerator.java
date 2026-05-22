import java.util.ArrayList;

import edu.princeton.cs.algs4.StdRandom;

public class DataGenerator {

    public static ArrayList < Song > generateDataBase ( int n , long seed )
    {
StdRandom.setSeed(seed);

int numArtists= n/50;

if (numArtists==0) numArtists=1;

String [] artists = new String[numArtists];
for (int i = 0; i < numArtists; i++) {
    artists[i] = "Artista_"+i;
}
 
String [] genres ={"Pop","Rock","Jazz","Electronic","Classical","Hip Hop"};

    ArrayList <Song> dataBase= new ArrayList<>(n);

    for(int id=1; id <= n; id++){
        String title = "Song_" + id;
    }

    
    String artist= artists [StdRandom.uniformInt(artists.length)];
 
    String genre= genres [StdRandom.uniformInt(genres.length)];

    int year = StdRandom.uniformInt(1970,2026);

    int plays= StdRandom.uniformInt(0, 10000000);
//ola
    dataBase.add(new Song(id, title,artist, genre, year, plays));
}
    }



