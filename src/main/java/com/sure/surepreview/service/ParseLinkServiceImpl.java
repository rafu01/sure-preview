package com.sure.surepreview.service;


import com.sure.surepreview.dto.PreviewDto;
import lombok.SneakyThrows;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

@Service
public class ParseLinkServiceImpl implements ParseLinkService{

    @Override
    @SneakyThrows
    public PreviewDto parseLink(String link){
        var document = Jsoup.connect(link)
                .followRedirects(true).get();
        return PreviewDto.builder()
                .title(getTitle(document))
                .description(getDescription(document))
                .image(getImage(document))
                .build();
    }

    private String getTitle(Document document){
        return document.title();
    }

    private String  getImage(Document document){
        return document.select("meta[property=og:image]").first().attr("content");
    }

    private String  getDescription(Document document){
        return document.select("meta[property=og:description]").first().attr("content");
    }
}
