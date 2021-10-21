
package com.alkemy.java.util;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class UtilApp {
  
    private String makePaginationLink(HttpServletRequest request, int page) {

        return String.format("%s?page=%d", request.getRequestURI(), page);

    }
    
    public Map<String,String > linksPagination (HttpServletRequest request, Page<?>list){
        
           Map<String, String> links = new HashMap<>();

        if (!list.isFirst()) {
            links.put("prev", makePaginationLink(request, list.getNumber() - 1));
        }

        if (!list.isLast()) {
            links.put("next", makePaginationLink(request, list.getNumber() + 1));
        }
        return links;
    }
    
         
}
