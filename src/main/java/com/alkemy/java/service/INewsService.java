package com.alkemy.java.service;

import com.alkemy.java.dto.NewsRequestDto;
import com.alkemy.java.dto.NewsResponseDto;

public interface INewsService {

    NewsResponseDto createNews(NewsRequestDto news);

}
