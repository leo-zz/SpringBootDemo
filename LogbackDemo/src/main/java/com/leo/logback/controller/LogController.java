package com.leo.logback.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.logging.Logger;


@Controller
public class LogController {

    Logger logger=Logger.getLogger("LogController");
    @GetMapping("/log")
    @ResponseBody
    public String testLog(){
        for (int i=0;i<1000;i++)
            logger.info(String.valueOf(i));
        return "999";
    }
}
