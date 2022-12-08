package upei2910.assignment2.service;

import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.function.Predicate;


/**
 * The backend class providing the data processing api service
 *
 * Reminder not to change the method headers for any of the existing methods
 * - but please change the implementation
 * - feel free to add methods and classes as required
 */
@Service
public class OneHitWonderService {
    private ArrayList<SongLyric> lyricList;
    /**
     * Declare new class and assign it to a variable
     */
    Calc calcX = new Calc();
    public OneHitWonderService() {
       //remember that loadData should be called externally from outside this method
        lyricList = new ArrayList<>();
    }

    /**
     * Load the data file
     * @param filename the filename for a file in the resources directory
     * @throws IOException if the file is not found
     */
    public void loadData(String filename) throws IOException {
        //feel free to use this line if you like
        File f = Helper.openResourceFile(filename);

        Scanner scan = new Scanner(f);
        while (scan.hasNextLine()){
            String s = scan.nextLine();
            String[] st = s.split(",");
            if (st.length != 5){
                lyricList.add(SongLyric.toSongLyric(s));
            }
        }
    }


    /**
     * For each decade determine if the lyrics are a best match
     * for a top 25 song, a song ranked 26 to 50, a song ranked 51 to 75
     * or a song ranked 76 to 100
     * determination is done using the cosine similarity score mentioned
     * in the instructions
     * pre: load data has been called
     * post: TBD
     * @param lyrics the lyrics to compare against the existing databases of songs
     * @return an array containing in the 0th index the QuartileScore (best matching quartile
     * and corresponding cosine simliarity score for the 1970s, in 1st index 1980s,
     * 2nd: 1990s, and 3rd 2000's
     */
    public QuartileScore[] bestMatches(String lyrics) {
        calcX.decScore(lyricList);
        HashMap<String, Double> x = calcX.lscore(lyrics);
        calcX.finish(x);
        calcX.largest();
        calcX.rank();
        QuartileScore[] scores =  new QuartileScore[4];

        for(int i = 0; i < 4; i++) {
            if (i==0) {
                scores[i] = new QuartileScore(calcX.rankp((int) calcX.mostSimilarSevenr), calcX.mostSimilarSeven);
            } else if (i==1) {
                scores[i] = new QuartileScore(calcX.rankp((int) calcX.mostSimilarEightr), calcX.mostSimilarEight);
            } else if (i==2) {
                scores[i] = new QuartileScore(calcX.rankp((int) calcX.mostSimilarNiner), calcX.mostSimilarNine);
            }else {
                scores[i] = new QuartileScore(calcX.rankp((int) calcX.mostSimilarTwoTr), calcX.mostSimilarTwoT);
            }

        }
        return scores;
    }

    public static void main(String[] args) throws IOException {
        //in case you want to try something and print easily
    }
}
