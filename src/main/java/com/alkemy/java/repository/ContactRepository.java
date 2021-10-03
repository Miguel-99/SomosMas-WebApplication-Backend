package com.alkemy.java.repository;

import com.alkemy.java.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository <Contact,Long> {

}
