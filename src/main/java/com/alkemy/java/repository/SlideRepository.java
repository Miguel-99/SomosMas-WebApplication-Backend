package com.alkemy.java.repository;


import com.alkemy.java.model.Slide;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SlideRepository extends JpaRepository<Slide, Long> {

   
    @Query(value = "SELECT MAX(s.numberOrder) FROM Slide s")
    int maxOrder();
    
    Slide findByNumberOrder(int numberOrder);

    Slide getById(Long id);

}
