package com.alkemy.java.service;

import com.alkemy.java.dto.ContactRequestDto;
import com.alkemy.java.dto.ContactResponseDto;

public interface IContactService {

    ContactResponseDto createContact (ContactRequestDto contactRequestDto);

}
