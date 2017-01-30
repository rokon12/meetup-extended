package com.bazlur.meetup.extended.web;

import com.bazlur.meetup.extended.integration.meetup.Meetup;
import com.bazlur.meetup.extended.integration.meetup.MeetupClient;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author Bazlur Rahman Rokon
 * @since 1/30/17.
 */
@Controller
public class NewsController {
    private final MeetupClient meetupClient;

    public NewsController(MeetupClient meetupClient) {
        this.meetupClient = meetupClient;
    }

    @GetMapping("/news")
    @ResponseBody
    public List<Meetup> home(Model model) {

        List<Meetup> recentNews = meetupClient.getRecentNews();
        //model.addAttribute("meetups", meetupClient.getRecentNews());
        return recentNews;
    }
}
