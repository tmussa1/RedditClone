package com.mc.reddit;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;

@Controller
public class RedditController {

    @Autowired
    RedditRepository redditRepository;

    @RequestMapping("/")
    public String showHompage(Model model){
        model.addAttribute("reddits", redditRepository.findAllByOrderByDateDesc());
        return "index";
    }

    @RequestMapping("/add")
    public String addPost(Model model ){

        model.addAttribute("reddit", new Reddit());
        return "addPost";
    }

    @PostMapping("/add")
    public String addPost(@ModelAttribute Reddit reddit){
        if(reddit.getDate() == null) {
            reddit.setDate(LocalDateTime.now());
        }
        redditRepository.save(reddit);
        return "redirect:/";
    }

    @RequestMapping("/detailReddit/{id}")
    public String showDetails(@PathVariable("id") long id, Model model){
        model.addAttribute("reddit", redditRepository.findById(id).get());
        return "redditDetails";
    }

}
