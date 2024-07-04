package Playlist_Inner_Class;

public class Song {
    
    private String title;
    private double duration;


    public Song(String title, double duration) {
        this.title = title;
        this.duration = duration;
    }

    @Override
    public String toString() {
        return  title + " : " +  duration;
    }

    public String getTitle() {
        return title;
    }

}
