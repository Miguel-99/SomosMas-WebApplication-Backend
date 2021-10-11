
package com.alkemy.java.service.impl;

import com.alkemy.java.dto.NewsDtoResponse;
import com.alkemy.java.exception.ResourceNotFoundException;
import com.alkemy.java.model.News;
import com.alkemy.java.repository.NewsRepository;
import com.alkemy.java.service.INewsService;
import java.util.Locale;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

@Service
public class NewsServiceImpl implements INewsService {
    
    @Autowired
    NewsRepository newsRepository;
    
    @Autowired
    MessageSource messageSource;
    
    @Value ("error.news.service.dont.exist")
    String messageDontExist;

    @Override
    public void deleteNews(Long id) {
        News news = newsRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(messageSource.getMessage(messageDontExist, null, Locale.getDefault())));
        newsRepository.delete(news);
    }

    @Override
    public NewsDtoResponse findById(Long id) throws ResourceNotFoundException {
        News news = newsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(messageSource.getMessage(messageDontExist, null, Locale.getDefault())));
        return NewsDtoResponse.newsToDto(news);
    }
    
}
