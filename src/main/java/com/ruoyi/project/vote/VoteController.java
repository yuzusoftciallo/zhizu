package com.ruoyi.project.vote;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class VoteController {

    @RequestMapping("/vote")
    public String vote(String vote){
        return "vote";
    }
}
