package upei2910.assignment2.service;

import java.util.ArrayList;

/**
 * Record to track the following:
 * @param rank
 * @param song
 * @param artist
 * @param year
 * @param lyricsList the lyrics
 */
public record SongLyric(int rank, String song, String artist, int year, String lyricsList) {
    /**
     * the method below sorts a line of string to find the lyrics while removing the quotes from the beginning and end of the code.
     * the quote removal is done after splitting the line by a comma
     * after the third member of the array you split the first word and put it back together without the quote and repeat for the last word.
     * @param s the line of string passed in
     * @return a record that tracks the rank, song, artist, year, lyrics
     */
    public static SongLyric toSongLyric(String s){
        String[] sArr = s.split(",");
        String x = "";
        for(int i = 0;i<sArr.length;i++){
            if (i>3){
                if (i==4){
                    String[] fix = sArr[i].split("");
                    for(int i1 =0;i1< fix.length;i1++){
                        if (i1>0){
                            x = x + fix[i1];
                        }
                    }
                } else if (i == sArr.length-1) {
                    String[] fix = sArr[i].split("");
                    x = x + ",";
                    for(int i1 =0;i1< fix.length;i1++){
                        if (i1< fix.length-1){
                            x = x + fix[i1];
                        }
                    }
                } else {
                    x = x + "," + sArr[i];
                }
            }
        }
        return new SongLyric(Integer.parseInt(sArr[0]),sArr[1],sArr[2],Integer.parseInt(sArr[3]),x);
    }
}
