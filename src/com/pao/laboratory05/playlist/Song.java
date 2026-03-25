package com.pao.laboratory05.playlist;

public record Song(String title, String artist, int durationSeconds) 
implements Comparable<Song> {
  public int compareTo(Song song) {
    return this.title().compareTo(song.title());
  }
}