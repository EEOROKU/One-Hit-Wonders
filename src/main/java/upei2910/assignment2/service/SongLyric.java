package upei2910.assignment2.service;

import java.util.ArrayList;

public record SongLyric(int rank, String song, String artist, int year, String lyricsList) {
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
