import DAO.SongDaoInterfaceImplementation;
import Model.Song;

import java.time.Year;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Year year = Year.parse("2019");

        Song song1 =new Song("Apashe Sway","Dragon Iron Fist Official Trailer Song","kurwa kurwa kurwa","Iron Fist",year);
        Song song2 =new Song("Hans Zimmer","The Dark Knight","kurwa kuraaaawa kurwa","The Dark Knight - Hans Zimmer  J. Newton Howard - LIVE",year);
        song1.setMP3FileFromPath("D:\\New folder\\Apashe Sway -Dragon Iron Fist Official Trailer Song .mp3");
        song2.setMP3FileFromPath("D:\\New folder\\The Dark Knight - Hans Zimmer  J. Newton Howard - LIVE.mp3");


        SongDaoInterfaceImplementation si = new SongDaoInterfaceImplementation();

//        si.insertSong(song1);
//        si.insertSong(song2);


        List<Song> soss = si.getAllSongs();
        System.out.println(soss.toString());
        for (Song sos:soss) {
            System.out.println(sos.toString());
//            sos.getSongMp3ToPath("D:");
        }
//    si.deleteSong(27);
//    si.deleteSong(1);
    }
}
