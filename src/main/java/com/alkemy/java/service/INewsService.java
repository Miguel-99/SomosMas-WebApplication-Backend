
package com.alkemy.java.service;


import com.alkemy.java.dto.NewsDto;

public interface INewsService {
        
    void deleteNews(Long id);
    NewsDto updateNews(Long id, NewsDto newsDto);
}
