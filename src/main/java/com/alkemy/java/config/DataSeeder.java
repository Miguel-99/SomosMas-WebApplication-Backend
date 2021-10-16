package com.alkemy.java.config;

import com.alkemy.java.model.*;
import com.alkemy.java.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Configuration
public class DataSeeder {

    private static final Logger log = LoggerFactory.getLogger(DataSeeder.class);

    @Bean
    CommandLineRunner initDatabase(ActivityRepository activityRepository,
            CategoryRepository categoryRepository,
            CommentRepository commentRepository,
            MemberRepository memberRepository,
            NewsRepository newsRepository,
            OrganizationRepository organizationRepository,
            RoleRepository roleRepository,
            SlideRepository slideRepository,
            TestimonialRepository testimonialRepository,
            UserRepository userRepository) {

        return args -> {
            Role roleUser = new Role("ROLE_USER", "Soy un usuario", new Date(), new Date());
            Role roleAdmin = new Role("ROLE_ADMIN", "Soy un admin", new Date(), new Date());
            List<Role> rolesList = new ArrayList<>();
            Collections.addAll(rolesList, roleUser, roleAdmin);
            saveRoles(rolesList, roleRepository);


            User userUser = new User("Giovanni", "Giorgio", "lacuentadelpueblo@gmail.com", "123456789", "foto.url", roleRepository.getByName("ROLE_USER"), new Date(), new Date());
            User userAdmin = new User("Ricardo", "Fort", "maiami@gmail.com", "123456789", "foto.url", roleRepository.getByName("ROLE_ADMIN"), new Date(), new Date());
            List<User> userList = new ArrayList<>();
            Collections.addAll(userList , userUser, userAdmin);
            saveUsers(userList, userRepository);

        };
    }

    void saveRoles(List<Role> roles, RoleRepository roleRepository){
        for (Role role: roles){
            if(!roleRepository.existsByName(role.getName())){
                roleRepository.save(role);
            }
        }
    }

    void saveUsers(List<User> users, UserRepository userRepository){
        for (User user: users){
            if(!userRepository.existsByEmail(user.getEmail())){
                userRepository.save(user);
            }
        }
    }
}
