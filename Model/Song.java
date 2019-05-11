/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.*;
import java.nio.file.Files;
import java.time.Year;
import java.util.Arrays;
import java.util.Objects;

/**
 *
 * @author smavros
 */
public class Song {
    
    private int id;
    private String artist;
    private String title;
    private String lyrics;
    private String album;
    private byte[] mp3file;
    private Year releaseyear; //read the docs https://docs.oracle.com/javase/8/docs/api/java/time/Year.html

    public Song() {
    }
    public Song(String artist, String title, String lyrics, String album, Year releaseyear) {
        this.artist = artist;
        this.title = title;
        this.lyrics = lyrics;
        this.album = album;
        this.releaseyear = releaseyear;
    }

    public Song(String artist, String title, String lyrics, String album, Year releaseyear, byte[] mp3file) {
    }


    public int getId() {
        return id;
    }

    
    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLyrics() {
        return lyrics;
    }

    public void setLyrics(String lyrics) {
        this.lyrics = lyrics;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public byte[] getMp3file() {
        return mp3file;
    }

     public void setMp3file(byte[] mp3file) {

        this.mp3file = mp3file; };

    public void setMP3FileFromPath(String path) {
        byte[] bFile = new byte[0];
        try {
            bFile = Files.readAllBytes(new File(path).toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.setMp3file(bFile);
    };

    public void getSongMp3ToPath(String path){
        String concatPath=path+"\\"+getId()+"-"+getArtist()+"-"+getTitle()+".mp3";
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(concatPath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            Objects.requireNonNull(fos).write(this.getMp3file());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NullPointerException npe){
            npe.printStackTrace();
        }
    }

    public Year getReleaseyear() {
        return releaseyear;
    }

    public void setReleaseyear(Year releaseyear) {
        this.releaseyear = releaseyear;
    }

    @Override
    public String toString() {
        return "Song{" +
                "id=" + id +
                ", artist='" + artist + '\'' +
                ", title='" + title + '\'' +
                ", lyrics='" + lyrics + '\'' +
                ", album='" + album + '\'' +
                ", releaseyear=" + releaseyear +
                '}';
    }

    public void setID(int id) { this.id =id; }
}
