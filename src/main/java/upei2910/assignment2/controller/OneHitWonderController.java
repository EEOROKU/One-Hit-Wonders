package upei2910.assignment2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import upei2910.assignment2.service.OneHitWonderService;
import upei2910.assignment2.service.QuartileScore;

import javax.annotation.PostConstruct;
import java.io.IOException;

/**
 * Class to sit between the frontend html files and the
 * data processing backend
 */
@Controller
public class OneHitWonderController {

    @Autowired
    OneHitWonderService ohwService;

    /**
     * Load the data
     */
    @PostConstruct
    public void init() {
        try {
            ohwService.loadData("billboard100_1970-2009.csv");
        }catch(IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Generate the lyrics input page
     * @return the name of the index html file
     */
    @GetMapping("/lyrics")
    public String getLyrics(Model model) {
        model.addAttribute("lyrics", new Lyrics());
        return "lyrics";
    }


    /**
     *
     */
    @PostMapping("/lyrics")
    public String lyricsSubmit(@ModelAttribute Lyrics lyrics, Model model)  {

        String strLyrics = lyrics.getContent();
        QuartileScore[] bestMatches = ohwService.bestMatches(strLyrics);

        if(bestMatches.length < 4) {
            System.err.println("Expecting 4 matches returned");
        }
        model.addAttribute("lyrics", strLyrics);
        model.addAttribute("q1970", bestMatches[0].quartile());
        model.addAttribute("q1980", bestMatches[1].quartile());
        model.addAttribute("q1990", bestMatches[2].quartile());
        model.addAttribute("q2000", bestMatches[3].quartile());

        model.addAttribute("s1970", bestMatches[0].score());
        model.addAttribute("s1980", bestMatches[1].score());
        model.addAttribute("s1990", bestMatches[2].score());
        model.addAttribute("s2000", bestMatches[3].score());

        return "results";
    }

}