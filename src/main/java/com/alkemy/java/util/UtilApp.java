
package com.alkemy.java.util;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

@Service
public class UtilApp {
  
    public String makePaginationLink(HttpServletRequest request, int page) {

        return String.format("%s?page=%d", request.getRequestURI(), page);

    }
    
}
