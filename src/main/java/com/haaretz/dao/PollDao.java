package com.haaretz.dao;

import com.haaretz.entities.Poll;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.PagingAndSortingRepository;

import javax.transaction.Transactional;
import java.util.Collection;

/**
 * Created by elia.grady on 08/01/2017.
 */
@Transactional
public interface PollDao extends PagingAndSortingRepository<Poll, Long> {

  @Modifying
  Poll save(Poll poll);

  Poll findOne(long id);

  Poll findOneByPollContentId(String pollContentId);

  Iterable<Poll> findAll(Sort sort);

  Page<Poll> findAll(Pageable pageable);

  Collection<Poll> findAll();
}
