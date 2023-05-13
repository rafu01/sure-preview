package com.sure.surepreview.controller;

import com.sure.surepreview.service.ParseLinkService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("api/v1")
@RequiredArgsConstructor
public class ParseController {

    private final ParseLinkService parseLinkService;

    @GetMapping("/get")
    @ResponseBody
    public String parseLink(@RequestParam String link){
        return parseLinkService.parseLink(link);
    }
}
