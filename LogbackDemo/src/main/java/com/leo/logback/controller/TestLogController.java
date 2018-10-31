package com.leo.logback.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.logging.Logger;

@Controller
public class TestLogController {

    Logger logger=Logger.getLogger("TestLogController");
    @GetMapping("/logInfo")
    @ResponseBody
    public String testLog(){
        for (int i=0;i<1000;i++)
            logger.info(String.valueOf(i));
        return "999";
    }

    @GetMapping("/logWarn")
    @ResponseBody
    public String testLogWarn(){
        for (int i=0;i<1000;i++)
            logger.warning(String.valueOf(i));
        return "999";
    }

    @GetMapping("/logError")
    @ResponseBody
    public String testLogError(){
        for (int i=0;i<1000;i++)
            logger.severe(String.valueOf(i));
        return "999";
    }
}