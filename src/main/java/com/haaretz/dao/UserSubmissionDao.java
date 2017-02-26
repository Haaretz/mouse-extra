package com.haaretz.dao;

import com.haaretz.entities.UserSubmission;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.PagingAndSortingRepository;

import javax.transaction.Transactional;
import java.util.Collection;

/**
 * Created by elia.grady on 04/01/2017.
 */

@Transactional
public interface UserSubmissionDao extends PagingAndSortingRepository<UserSubmission, Long> {

  @Modifying
  UserSubmission save(UserSubmission userSubmission);

  Collection<UserSubmission> findAll();

  Collection<UserSubmission> findAll(Sort sort);

  UserSubmission findOneByPollContentId(String pollContentId);

  Collection<UserSubmission> findAllByPollContentId(String pollContentId);
}
