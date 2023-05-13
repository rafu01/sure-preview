package com.sure.surepreview.service;

import com.sure.surepreview.dto.PreviewDto;

import java.io.IOException;

public interface ParseLinkService {

    PreviewDto parseLink(String link);
}
