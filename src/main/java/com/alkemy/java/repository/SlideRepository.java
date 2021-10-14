package com.alkemy.java.repository;

import com.alkemy.java.dto.SlideResponseDto;
import com.alkemy.java.model.Slide;
import javassist.NotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SlideRepository extends JpaRepository<SlideResponseDto, Long> {


    SlideResponseDto getById(Long id);
}
