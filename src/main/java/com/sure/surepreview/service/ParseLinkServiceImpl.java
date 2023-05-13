package com.sure.surepreview.service;


import lombok.SneakyThrows;
import org.jsoup.Jsoup;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ParseLinkServiceImpl implements ParseLinkService{

    @Override
    @SneakyThrows
    public String parseLink(String link){
        return Jsoup.connect(link)
                .followRedirects(true)
                .get().title();
    }

}
