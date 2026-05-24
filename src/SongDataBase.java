import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.ArrayList;

class SongDataBase {
    private ArrayList<Song> songs;

    public SongDataBase() {
        this.songs = new ArrayList<>();
    }
    //constructor para recibir una lista de canciones
    public SongDataBase(ArrayList<Song> songs) {
        this.songs = songs;
    }

    public void addSong(Song song) {
        songs.add(song);
    }

    public List<Song> getSongs() {
        return songs;
    }

    public void ordenarPorAlgoritmo(String algoritmo, String atributo){
        
        Comparator<Song> comparador;
        
        if("artist".equals(atributo)){
            // Se usa lambda en lugar de method reference porque Song::getArtist
            // daba error de compilacion
            comparador = Comparator.comparing(song -> song.getArtist());
        }else if("genre".equals(atributo)){
            comparador = Comparator.comparing(Song::getGenre);
        }else if("year".equals(atributo)){
            comparador = Comparator.comparingInt(Song::getYear);
        }else{
            comparador = Comparator.comparingLong(Song::getPlays);
        }

        if(algoritmo.equals("mergeSort")){
            Merge.sort(songs, comparador);
        }
        else if(algoritmo.equals("quickSort")){
            Quick.sort(songs, comparador);
        }else if(algoritmo.equals("insertionSort")){
            Insertion.sort(songs, comparador);
        }else if(algoritmo.equals("selectionSort")){
            Selection.sort(songs, comparador);
        }else{
            Collections.sort(songs, comparador);
        }
    }


        public ArrayList<Song> sequentialSearch(String artist) {
            ArrayList<Song> lista = new ArrayList<>();
            for (Song s : songs) {
                if (s.getArtist().equals(artist)) {
                    lista.add(s);
                }
            }
            return lista;
        }

        public ArrayList<Song> binarySearch(String artist) {
            ArrayList<Song> lista = new ArrayList<>();
            int lo = 0;
            int hi = songs.size();
            while (lo < hi) {
                int mid = lo + (hi - lo) / 2;
                int cmp = songs.get(mid).getArtist().compareTo(artist);
                if (cmp < 0) {
                    lo = mid + 1;
                } else {
                    hi = mid;
                }
            }
            int idx = lo;
            while (idx < songs.size() && songs.get(idx).getArtist().equals(artist)) {
                lista.add(songs.get(idx));
                idx++;
            }
            return lista;
        }

}