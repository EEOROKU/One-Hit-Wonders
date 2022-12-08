package upei2910.assignment2.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.lang.Math;

public class Calc {
    ArrayList<HashMap> seventies = new ArrayList<>();
    ArrayList<HashMap> eighties = new ArrayList<>();
    ArrayList<HashMap> nineties = new ArrayList<>();
    ArrayList<HashMap> twoThousand = new ArrayList<>();
    public HashMap<String, Double> lscore(String lyrics){
        String[] arLyrics = lyrics.split(" ");
        HashMap<String,Double> word = new HashMap<String,Double>();
        for(String x:arLyrics){
            if(!word.containsKey(x)){
                word.put(x,1.0);
            }
            else {
                double c =word.get(x)+1.0;
                word.put(x,c);
            }
        }
        for(Map.Entry<String,Double> x:word.entrySet()){
            word.put(x.getKey(), x.getValue()/arLyrics.length);
        }
        return word;
    }
    public HashMap<String, Double> score(String lyrics){
        String[] arLyrics = lyrics.split(",");
        HashMap<String,Double> word = new HashMap<String,Double>();
        for(String x:arLyrics){
            if(!word.containsKey(x)){
                word.put(x,1.0);
            }
            else {
                double c =word.get(x)+1.0;
                word.put(x,c);
            }
        }
        for(Map.Entry<String,Double> x:word.entrySet()){
            word.put(x.getKey(), x.getValue()/arLyrics.length);
        }
        return word;
    }
    public void decScore(ArrayList<SongLyric> y){
        for (SongLyric x:y){
                if (x.year() < 1981) {
                    seventies.add(score(x.lyricsList()));
                } else if (x.year() < 1991) {
                    eighties.add(score(x.lyricsList()));
                } else if (x.year()< 2001) {
                    nineties.add(score(x.lyricsList()));
                } else {
                    twoThousand.add(score(x.lyricsList()));
                }
            }
    }
    ArrayList<Double> sev = new ArrayList<>();
    ArrayList<Double> eig = new ArrayList<>();
    ArrayList<Double> nin = new ArrayList<>();
    ArrayList<Double> tt = new ArrayList<>();

    public void finish(HashMap<String,Double> y1){
        double T = 0;
        double b1 = 0;
        double b2 = 0;

        for (HashMap<String,Double> se: seventies){
            for (HashMap.Entry<String,Double> x : se.entrySet()){
                String key = x.getKey();
                if(y1.containsKey(key)){
                    T += y1.get(key)*x.getValue();
                    b1 += x.getValue()*x.getValue();
                    b2 += y1.get(key)*y1.get(key);
                }
            }
            double ans = T/(Math.sqrt(b1)*Math.sqrt(b2));
            sev.add(ans);
            T = 0; b1 = 0; b2 = 0;
        }
        for (HashMap<String,Double> se: eighties){
            for (HashMap.Entry<String,Double> x : se.entrySet()){
                String key = x.getKey();
                if(y1.containsKey(key)){
                    T += y1.get(key)*x.getValue();
                    b1 += x.getValue()*x.getValue();
                    b2 += y1.get(key)*y1.get(key);
                }
            }
            double ans = T/(Math.sqrt(b1)*Math.sqrt(b2));
            eig.add(ans);
            T = 0; b1 = 0; b2 = 0;
        }
        for (HashMap<String,Double> se: nineties){
            for (HashMap.Entry<String,Double> x : se.entrySet()){
                String key = x.getKey();
                if(y1.containsKey(key)){
                    T += y1.get(key)*x.getValue();
                    b1 += x.getValue()*x.getValue();
                    b2 += y1.get(key)*y1.get(key);
                }
            }
            nin.add(T/(Math.sqrt(b1)*Math.sqrt(b2)));
            T = 0; b1 = 0; b2 = 0;
        }
        for (HashMap<String,Double> se: twoThousand){
            for (HashMap.Entry<String,Double> x : se.entrySet()){
                String key = x.getKey();
                if(y1.containsKey(key)){
                    T += y1.get(key)*x.getValue();
                    b1 += x.getValue()*x.getValue();
                    b2 += y1.get(key)*y1.get(key);
                }
            }
            tt.add(T/(Math.sqrt(b1)*Math.sqrt(b2)));
            T = 0; b1 = 0; b2 = 0;
        }
    }
    double mostSimilarSeven = 0;
    double mostSimilarEight = 0;
    double mostSimilarNine = 0;
    double mostSimilarTwoT = 0;
    public void largest(){
        for (double x : sev){
            if (x>mostSimilarSeven){
                mostSimilarSeven = x;
            }
        }
        for (double x : eig){
            if (x>mostSimilarEight){
                mostSimilarEight = x;
            }
        }
        for (double x : nin){
            if (x>mostSimilarNine){
                mostSimilarNine = x;
            }
        }
        for (double x : tt){
            if (x>mostSimilarTwoT){
                mostSimilarTwoT= x;
            }
        }
    }
}
