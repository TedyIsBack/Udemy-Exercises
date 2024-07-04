package Playlist_Inner_Class;

import java.util.ArrayList;
import java.util.LinkedList;

public class Album {
    private String name,artist;
    private SongList songs;

    public Album(String name, String artist) {
        this.name = name;
        this.artist = artist;
        songs = new SongList();
    }

    public boolean addSong(String title, double duration){
        Song song = new Song(title,duration);
        if(songs.findSong(title) == null){
            songs.add(song);
            return true;
        }
        return false;
    }
    //addToPlayList(int trackNumber, LinkedList<Playlist_Inner_Class.Song> playList)
    public boolean addToPlayList(int trackNumber, LinkedList<Song> playList ){
        int index = trackNumber - 1;
        if(songs.songs.size() >= index && index >= 0){
            playList.add(songs.findSong(index));
            return true;
        }
        System.out.println("There is not track " + trackNumber);
        return false;
    }
    public boolean addToPlayList(String title, LinkedList<Song> playList) {
        Song song = songs.findSong(title);
        if (song != null) {
            playList.add(song);
            return true;
        } else {
            System.out.println("The song " + title + " is not in this album");
            return false;
        }
    }
    public static class SongList {
        private ArrayList<Song> songs;

        private SongList() {
            this.songs = new ArrayList<>();
        }

        private boolean add(Song song){
            if(songs.contains(song)){
                return false;
            }
            songs.add(song);
            return true;
        }

        private Song findSong(String songName){
            for (Song song : songs){
                if(song.getTitle().equals(songName)){
                    return song;
                }
            }
            return null;
        }

        private Song findSong(int trackNumber){
            return songs.get(trackNumber);
        }
    }
}
