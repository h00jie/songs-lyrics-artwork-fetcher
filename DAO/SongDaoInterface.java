/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Song;

import java.util.List;

public interface SongDaoInterface {
    
    public List<Song> getAllSongs();
    public List<Song> getSongsByTitle(String title);
    public List<Song> getSongsByArtist(String artist);
    public Song getSongById(int id);
    public boolean insertSong(Song song);
    public boolean deleteSong(int id);
    public void updateSong(Song song);
    
}
