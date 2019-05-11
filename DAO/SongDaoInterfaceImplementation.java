/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DbUtils.DbUtils;
import Model.Song;

import javax.sound.midi.Soundbank;
import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

public class SongDaoInterfaceImplementation implements SongDaoInterface{


    @Override
    public List<Song> getAllSongs() {
        List<Song> songs = new ArrayList<>();
        Connection con = DbUtils.getConnection();
        String sql = "SELECT id,artist,title,lyrics,album,releaseyear,mp3file FROM song";
        PreparedStatement pst = null;
        try {
            pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Song song = new Song();
                song.setID(rs.getInt("id"));
                song.setArtist(rs.getString("artist"));
                song.setTitle(rs.getString("title"));
                song.setLyrics(rs.getString("lyrics"));
                song.setAlbum(rs.getString("album"));
                song.setReleaseyear(Year.of(rs.getInt("releaseyear")));
                song.setMp3file(getMp3ByteArrayFromFileStream(rs.getBinaryStream("mp3file")));
                System.out.println("\n"+song.toString()+"\n");
                songs.add(song);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbUtils.closeConnection(con);
            DbUtils.closePStatement(pst);
        }

        return songs;
    }

    @Override
    public List<Song> getSongsByTitle(String title) {
        List<Song> songs = new ArrayList<>();
        Connection con = DbUtils.getConnection();
        String sql = "SELECT artist,title,lyrics,album,releaseyear FROM song where title=?";
        PreparedStatement pst = null;
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1,title);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Song song = new Song();
                song.setArtist(rs.getString("artist"));
                song.setTitle(rs.getString("title"));
                song.setLyrics(rs.getString("lyrics"));
                song.setAlbum(rs.getString("album"));
                song.setReleaseyear(Year.of(rs.getInt("releaseyear")));
//                System.out.println("\n"+song.toString()+"\n");
                for (Song sonsg:songs){
                    System.out.println(sonsg.toString());

                }
                songs.add(song);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbUtils.closeConnection(con);
            DbUtils.closePStatement(pst);
        }

        return songs;
    }

    @Override
    public List<Song> getSongsByArtist(String artist) {
        List<Song> songs = new ArrayList<>();
        Connection con = DbUtils.getConnection();
        String sql = "SELECT artist,title,lyrics,album,releaseyear FROM song where artist=?";
        PreparedStatement pst = null;
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1,artist);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Song song = new Song();
                song.setArtist(rs.getString("artist"));
                song.setTitle(rs.getString("title"));
                song.setLyrics(rs.getString("lyrics"));
                song.setAlbum(rs.getString("album"));
                song.setReleaseyear(Year.of(rs.getInt("releaseyear")));
//                System.out.println("\n"+song.toString()+"\n");
                for (Song sonsg:songs){
                    System.out.println(sonsg.toString());

                }
                songs.add(song);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbUtils.closeConnection(con);
            DbUtils.closePStatement(pst);
        }

        return songs;
    }

    @Override
    public Song getSongById(int id) {
        String sql = "SELECT FROM song where id =?";
        Connection con = DbUtils.getConnection();
        Song song = null;
        PreparedStatement pst = null;
        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            int sid =rs.getInt("id");
            String artist = rs.getString("artist");
            String title = rs.getString("title");
            String lyrics = rs.getString("lyrics");
            String album = rs.getString("album");
            Year releaseyear = Year.of(rs.getInt("releaseyear"));
            byte[] mp3file = getMp3ByteArrayFromFileStream(rs.getBinaryStream("mp3file"));
            song = new Song(artist, title, lyrics, album, releaseyear,mp3file);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbUtils.closeConnection(con);
            DbUtils.closePStatement(pst);
        }
        return song;
    }

    @Override
    public boolean insertSong(Song song) {
        String sql = "INSERT INTO song(artist,title,lyrics,album,releaseyear,mp3file) VALUES (?,?,?,?,?,?)";
        Connection con = DbUtils.getConnection();
        PreparedStatement pst = null;
        int result =0;
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1,song.getArtist());
            pst.setString(2,song.getTitle());
            pst.setString(3,song.getLyrics());
            pst.setString(4,song.getAlbum());
            pst.setString(5,song.getReleaseyear().toString());
            pst.setBinaryStream(6,getMp3fileSteam(song.getMp3file()));
            result = pst.executeUpdate();
            pst.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DbUtils.closeConnection(con);
            DbUtils.closePStatement(pst);
        }
        return result>0 ? true : false;
    }

    @Override
    public boolean deleteSong(int id) {
        int songID = id;
        String sql = "DELETE FROM song where id =?";
        Connection con = DbUtils.getConnection();
        PreparedStatement pst = null;
        int result = 0;
        try {
             pst = con.prepareStatement(sql);
            pst.setInt(1,songID);
            result = pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DbUtils.closeConnection(con);
            DbUtils.closePStatement(pst);
        }
        System.out.println("Executing deletion of song with ID Nr. "+id);
        return result > 0 ? true : false;

    }

    @Override
    public void updateSong(Song song) {
        int songID=song.getId();
        String sql = "UPDATE song SET artist=?,title=?,lyrics=?,album=?,releaseyear=?,mp3file=? where id =?";
        Connection con = DbUtils.getConnection();
        PreparedStatement pst = null;
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1,song.getArtist());
            pst.setString(2,song.getTitle());
            pst.setString(3,song.getLyrics());
            pst.setString(4,song.getAlbum());
            pst.setString(5,song.getReleaseyear().toString());
            pst.setBinaryStream(6,getMp3fileSteam(song.getMp3file()));
            pst.setInt(7,songID);

            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DbUtils.closeConnection(con);
            DbUtils.closePStatement(pst);
        }
    }
    public InputStream getMp3fileSteam(byte[] bArrFile){
        ByteArrayInputStream bis = new ByteArrayInputStream(bArrFile);
        return bis;
    }
    public byte[] getMp3ByteArrayFromFileStream(InputStream mp3Stream){
        ByteArrayOutputStream bos= new ByteArrayOutputStream();

        byte[] buffer = new byte[1024];
        int len=0;

        // read bytes from the input stream and store them in buffer
        while (true) {
            try {
                if (!((len = mp3Stream.read(buffer)) != -1)) break;
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            // write output stream
            bos.write(buffer, 0, len);
        }

        return bos.toByteArray();
    };






//    public byte[] getFileFromPath(String path) {
//        byte[] bFile = new byte[0];
//        try {
//            bFile = Files.readAllBytes(new File(path).toPath());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return bFile;
//    };

}
