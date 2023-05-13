package com.sure.surepreview.service;

import com.sure.surepreview.dto.PreviewDto;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ParseLinkServiceImpl implements ParseLinkService{

    @Override
    public PreviewDto parseLink(String link){
        try {
            var document = Jsoup.connect(link)
                    .userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/104.0.5112.79 Safari/537.36")
                    .followRedirects(true).get();
            return PreviewDto.builder()
                    .title(getTitle(document))
                    .description(getDescription(document))
                    .image(getImage(document))
                    .build();
        }
        catch (Exception exception){
            log.error("Error Fetching Url : {}", link);
        }
        return PreviewDto.builder().build();
    }

    private String getTitle(Document document){
        try {
            return document.title();
        }
        catch (NullPointerException exception){
            return getTitleFromOgTag(document);
        }
    }

    private String getTitleFromOgTag(Document document){
        try {
            return document.select("meta[property=og:title]").first().attr("content");
        }
        catch (NullPointerException exception){
            log.error("Could Not Get Parse Title From: {}", document.location());
            return "";
        }
    }

    private String getDescription(Document document){
        try {
            return getDescriptionFromMeta(document);
        }
        catch (NullPointerException exception){
            return getDescriptionFromOgTag(document);
        }
    }

    private String  getDescriptionFromOgTag(Document document){
        try {
            return document.select("meta[property=og:description]").first().attr("content");
        }
        catch (NullPointerException exception){
            log.error("Could Not Get Parse Description From: {}", document.location());
            return "";
        }
    }

    private String getDescriptionFromMeta(Document document){
        return document.select("meta[name=description]").first().attr("content");
    }

    private String getImage(Document document){
        try {
            return getImageFromOgTag(document);
        }
        catch (NullPointerException exception){
            log.error("Could Not Get Parse Image From: {}", document.location());
            return "";
        }
    }

    private String  getImageFromOgTag(Document document){
        return document.select("meta[property=og:image]").first().attr("content");
    }

}
