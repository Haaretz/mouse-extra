package com.haaretz.dao;

import com.haaretz.entities.User;
import org.springframework.data.repository.PagingAndSortingRepository;

import javax.transaction.Transactional;

/**
 * Created by elia.grady on 08/01/2017.
 */
@Transactional
public interface UserDao extends PagingAndSortingRepository<User, Long> {

  @Override
  User save(User user);

  /**
   * This method will find an User instance in the database by its email.
   * Note that this method is not implemented and its working code will be
   * automagically generated from its signature by Spring Data JPA.
   */
  User findByEmail(String email);

  User findByPhoneNumber(String phoneNumber);
}
