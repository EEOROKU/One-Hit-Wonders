package upei2910.assignment2.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.lang.Math;

public class Calc {
    /**
     * Four array list to stare the number of similarities in each decade
     */
    ArrayList<HashMap> seventies = new ArrayList<>();
    ArrayList<HashMap> eighties = new ArrayList<>();
    ArrayList<HashMap> nineties = new ArrayList<>();
    ArrayList<HashMap> twoThousand = new ArrayList<>();

    /**
     * lscore and score both have the same function and that is to take in the lyrics and split them up to find the ratio in which the words exist in the sentences and return that in a hashmap
     * @param lyrics
     * @return hashmap containing the word and ratio
     */
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

    /**
     * decScore sorts the songs into categories based on decade
     * @param y an arraylist of songsLyric values
     * loop through the arraylist
     *          using if statements sort them by year
     *              call fort lyricsList from the record and pass it into score which returns a hashmap
     *              add that to the right arraylist
     */
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

    /**
     * 4 array list for storing the values of the comparison
     */
    ArrayList<Double> sev = new ArrayList<>();
    ArrayList<Double> eig = new ArrayList<>();
    ArrayList<Double> nin = new ArrayList<>();
    ArrayList<Double> tt = new ArrayList<>();

    /**
     * finish has a hashmap passed to it
     * @param y1 a hashmap or the lyrics to be compared to
     *           runs a for loop to run through each decade's hashmap and calculate the cosine similarity for each line
     */
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

    /**
     * Four variables that store the larges values in all the decades
     */
    double mostSimilarSeven = 0;
    double mostSimilarEight = 0;
    double mostSimilarNine = 0;
    double mostSimilarTwoT = 0;

    /**
     * largest runs and loops through the arraylists to find the largest number and assign it to the proper variable
     */
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
