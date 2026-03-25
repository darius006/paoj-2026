package com.pao.laboratory05.playlist;

import java.util.Comparator;

public class SongDurationComparator implements Comparator<Song> {
  public int compare(Song s1, Song s2) {
    Integer d1 = s1.durationSeconds(), d2 = s2.durationSeconds();
    return d1.compareTo(d2);
  }
}
