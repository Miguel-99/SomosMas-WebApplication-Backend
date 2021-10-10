
package com.alkemy.java.service.impl;

import com.alkemy.java.dto.NewsDto;
import com.alkemy.java.exception.ResourceNotFoundException;
import com.alkemy.java.model.News;
import com.alkemy.java.repository.NewsRepository;
import com.alkemy.java.service.INewsService;
import java.util.Date;
import java.util.Locale;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

/**
 *
 * @author Mariela
 */
@Service
public class NewsServiceImpl implements INewsService{
    
    @Autowired
    NewsRepository newsRepository;
    
    @Autowired
    MessageSource messageSource;

    @Autowired
    ModelMapper modelMapper;

    @Value ("error.news.service.dont.exist")
     String messageDontExist;

    @Override
    public void deleteNews(Long id) {
        News news = findById(id);
        
        if (news == null) {
            throw new ResourceNotFoundException(messageSource.getMessage(messageDontExist, null, Locale.getDefault()));
        }
        
        newsRepository.deleteById(id);
    }

    @Override
    public NewsDto updateNews(Long id, NewsDto newsDto) {
        News news = newsRepository.findById(id).orElseThrow( () ->
                new ResourceNotFoundException(messageSource.getMessage(messageDontExist, null, Locale.getDefault())));

        if (newsDto.getCategory() != null)
            news.setCategory(newsDto.getCategory());
        if (newsDto.getContent() != null)
            news.setContent(newsDto.getContent());
        if (newsDto.getName() != null)
            news.setName(newsDto.getName());
        if (newsDto.getImage() != null)
            news.setImage(newsDto.getImage());
        news.setUpdateDate(new Date());

        news = newsRepository.save(news);
        return modelMapper.map(news, NewsDto.class);
    }

    private News findById(Long id){
       return newsRepository.findById(id).orElse(null);
    }
    
}
