package upei2910.assignment2.service;

import org.apache.tomcat.util.http.fileupload.impl.IOFileUploadException;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

//Testcases associated with the nothing service
class OneHitWonderServiceTest {

    @Test
    void pokerFaceQuartileScoreTest() {
        OneHitWonderService ohw = new OneHitWonderService();
        try {
            ohw.loadData("sample.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }

        QuartileScore[] qs = ohw.bestMatches("poker face");

        //1970's face face face is the only lyric appearing 3 times
        assertEquals(1.0,qs[0].score(),0.0001,"expecting poker face to match with max value");
        assertEquals(2, qs[0].quartile(),"expecting 2nd quartile");
    }

    @Test
    void runninQuartileScoreTest() {
        OneHitWonderService ohw = new OneHitWonderService();
        try {
            ohw.loadData("sample.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }

        QuartileScore[] qs = ohw.bestMatches("runnin");
        assertEquals(0.9363,qs[1].score(),0.0001,"expecting runnin to match with strong value");
    }
}