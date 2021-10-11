
package com.alkemy.java.service.impl;

import com.alkemy.java.exception.ResourceNotFoundException;
import com.alkemy.java.model.News;
import com.alkemy.java.repository.NewsRepository;
import com.alkemy.java.service.INewsService;
import java.util.Locale;
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
    
    private News findById(Long id){
       return newsRepository.findById(id).orElse(null);
    }
    
}
