package com.alkemy.java.service.impl;

import com.alkemy.java.dto.ActivityDto;
import com.alkemy.java.exception.ConflictException;
import com.alkemy.java.repository.ActivityRepository;
import com.alkemy.java.service.IActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class ActivityServiceImpl implements IActivityService {

    @Autowired
    ActivityRepository activityRepository;

    @Autowired
    MessageSource messageSource;

    @Override
    public void createActivity(ActivityDto activityDto) {

        if(activityRepository.existsByName(activityDto.getName()))
            throw new ConflictException(messageSource.getMessage("error.activity.already.exist", null, Locale.getDefault()));

        activityRepository.save(ActivityDto.dtoToActivity(activityDto));
    }

}
