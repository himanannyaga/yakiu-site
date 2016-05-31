package com.myapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by m on 2016/06/01.
 */
@Controller
public class MainController {
    //angular2のroot機能使うために全てをindexに
    @RequestMapping({"/**"})
    public String index() {
        return "index";
    }
}
