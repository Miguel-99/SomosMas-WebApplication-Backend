package com.alkemy.java.service.impl;


import com.alkemy.java.dto.SlideRequestDto;
import com.alkemy.java.dto.SlideResponseCreateDto;
import com.alkemy.java.exception.BadRequestException;
import com.alkemy.java.model.Organization;
import com.alkemy.java.repository.OrganizationRepository;
import java.util.Date;
import com.alkemy.java.dto.SlideDto;
import com.alkemy.java.dto.SlideResponseDto;
import com.alkemy.java.exception.ResourceNotFoundException;
import com.alkemy.java.model.Slide;
import com.alkemy.java.repository.SlideRepository;
import com.alkemy.java.service.IFileService;
import com.alkemy.java.service.ISlideService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import org.springframework.web.multipart.MultipartFile;



@Service
public class SlideServiceImpl implements ISlideService {

    @Autowired
    private SlideRepository slideRepository;

    @Autowired
    private OrganizationRepository organizationRepository;

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private IFileService fileService;

    @Value("error.user.notFoundID")
    private String idNotFound;

    @Value("error.organization.dont.exist")
    private String errorOrganizationDontExist;

    @Value("error.slide.notFound")
    private String resourceNotFound;

    @Value("error.slide.text.notNull")
    private String textNotEmpty;

    @Value("error.slide.organization.notNull")
    private String organizationNotEmpty;

    @Override
    public SlideResponseCreateDto createSlide(SlideRequestDto slideRequest, MultipartFile file) throws Exception {

        validSlideRequest(slideRequest);

        Slide slide = mapToEntity(slideRequest);

        slide.setCreateDate(new Date());
        slide.setLastUpdate(new Date());
        slide.setNumberOrder(slideRequest.getNumberOrder());
        slide.setOrganizationId(mapper.map(slideRequest.getOrganizationDto(), Organization.class));
        slide.setImageUrl(fileService.uploadFile(file));

        slideRepository.save(slide);

        return mapToDto(slide);

    }

    @Override
    public List<SlideResponseDto> getAllSlide() {

        List<Slide> slides = slideRepository.findAll();
        return slides.stream().map(slide -> mapper.map(slide, SlideResponseDto.class)).collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {

        Slide slide = slideRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(messageSource.getMessage(idNotFound, null, Locale.getDefault())));
        slideRepository.delete(slide);

    }

    @Override
    @Transactional(readOnly = true)
    public SlideResponseDto getById(Long id) {
        Slide slide = slideRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(messageSource.getMessage(idNotFound, null, Locale.getDefault())));
        return mapper.map(slide, SlideResponseDto.class);
    }

    @Override
    public SlideDto updateSlide(Long id, SlideDto slideDto) {
        Slide updatedSlide = slideRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(messageSource.getMessage(idNotFound, null, Locale.getDefault())));

        updatedSlide.setImageUrl(slideDto.getImageUrl());
        updatedSlide.setText(slideDto.getText());
        updatedSlide.setNumberOrder(slideDto.getNumberOrder());
        updatedSlide.setOrganizationId(slideDto.getOrganization());

        slideRepository.save(updatedSlide);
        return SlideDto.slideToDto(updatedSlide);

    }

    private void validSlideRequest(SlideRequestDto slideRequest) {

        if (slideRequest.getText() == null || slideRequest.getText().isEmpty()) {
            throw new BadRequestException(messageSource.getMessage(textNotEmpty, null, Locale.getDefault()));
        }

        if (slideRequest.getOrganizationDto() == null) {
            throw new BadRequestException(messageSource.getMessage(organizationNotEmpty, null, Locale.getDefault()));
        }

        if (!organizationRepository.existsById(slideRequest.getOrganizationDto().getId())) {
            throw new BadRequestException(messageSource.getMessage(errorOrganizationDontExist, null, Locale.getDefault()));
        }

        if (slideRequest.getNumberOrder() == null || slideRepository.findByNumberOrder(slideRequest.getNumberOrder()) != null) {

            slideRequest.setNumberOrder(slideRepository.maxOrder() + 1);
        }
    }

    private SlideResponseCreateDto mapToDto(Slide slide) {
        return mapper.map(slide, SlideResponseCreateDto.class);
    }

    private Slide mapToEntity(SlideRequestDto categoryRequest) {

        return mapper.map(categoryRequest, Slide.class);

    }
}
