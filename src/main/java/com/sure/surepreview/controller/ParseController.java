package com.sure.surepreview.controller;

import com.sure.surepreview.dto.PreviewDto;
import com.sure.surepreview.service.ParseLinkService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("api/v1")
@CrossOrigin(origins = "http://localhost:8082", maxAge = 3600)
@RequiredArgsConstructor
public class ParseController {

    private final ParseLinkService parseLinkService;

    @GetMapping("/get")
    @ResponseBody
    public PreviewDto parseLink(@RequestParam String link){
        return parseLinkService.parseLink(link);
    }
}
