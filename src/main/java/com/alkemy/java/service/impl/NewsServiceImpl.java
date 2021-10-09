package com.alkemy.java.service.impl;

import com.alkemy.java.dto.NewsRequestDto;
import com.alkemy.java.dto.NewsResponseDto;
import com.alkemy.java.exception.BadRequestException;
import com.alkemy.java.model.News;
import com.alkemy.java.repository.CategoryRepository;
import com.alkemy.java.repository.NewsRepository;
import com.alkemy.java.service.INewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Locale;

@Service
public class NewsServiceImpl implements INewsService {

    @Autowired
    NewsRepository newsRepository;
    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    MessageSource messageSource;

    @Override
    public NewsResponseDto save(NewsRequestDto newsDto) {

        if(newsRepository.findByName(newsDto.getName()) != null)
            throw new BadRequestException(messageSource.getMessage("error.news.already.exist", null, Locale.getDefault()));

        if (!categoryRepository.existsById((long) newsDto.getCategoryId()))
            throw new BadRequestException(messageSource.getMessage("error.category.doesnt.exist", null, Locale.getDefault()));

        News newsToSave = NewsRequestDto.dtoToNews(newsDto);

        newsToSave.setCreateDate(new Date());
        newsToSave.setUpdateDate(new Date());
        newsToSave.setDeleted(false);

        newsToSave.setCategory(categoryRepository.findById((long) newsDto.getCategoryId()).get());

        News news = newsRepository.save(newsToSave);

        return NewsResponseDto.newsToDto(news);
    }

}
