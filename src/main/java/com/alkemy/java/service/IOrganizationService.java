package com.alkemy.java.service;

import com.alkemy.java.dto.OrganizationDto;

public interface IOrganizationService {
    OrganizationDto findById(Long id);
}
