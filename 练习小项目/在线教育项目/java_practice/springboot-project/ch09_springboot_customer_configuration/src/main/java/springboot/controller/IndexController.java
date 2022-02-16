package springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import springboot.config.School;

@Controller
public class IndexController {

    @Autowired
    private School school;

    @RequestMapping("/say")
    @ResponseBody
    public String say(){
        return school.toString();
    }

}
