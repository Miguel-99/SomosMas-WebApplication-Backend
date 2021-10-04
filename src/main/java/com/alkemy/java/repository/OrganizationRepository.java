package com.alkemy.java.repository;

import com.alkemy.java.model.Organization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface OrganizationRepository extends JpaRepository<Organization, Long> {

    @Modifying
    @Query(value = "update organizations set linkedin_url = ?1, facebook_url = ?2, instagram_url = ?3 where id = ?4", nativeQuery = true)
    @Transactional
    void setContactInfoById(String linkedinUrl, String facebookUrl, String instagramUrl,Long id);

}
