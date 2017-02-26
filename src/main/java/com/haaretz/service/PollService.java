package com.haaretz.service;

import com.haaretz.dao.PollDao;
import com.haaretz.entities.Poll;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by elia.grady on 04/01/2017.
 */

@Service
public class PollService {


  static {

  }

  @Autowired
  private PollDao pollDao;


  public void addPoll(Poll poll) {
    this.updatePoll(poll);
  }


  public void updatePoll(Poll poll) {
    Poll searchedByContentId = pollDao.findOneByPollContentId(poll.getPollContentId());
    if(searchedByContentId != null) {
      //updates
      //log.info("updating poll with contentId: "+ searchedByContentId.getPollContentId());
      poll.setId(searchedByContentId.getId());
    }
    pollDao.save(poll);
  }

  public Poll getPollById(String contentId) {
    return pollDao.findOneByPollContentId(contentId);
  }

  public Collection<Poll> getAllPolls() {
    Iterable iterable = pollDao.findAll();
    List<Poll> targetCollection = new ArrayList<>();
    CollectionUtils.addAll(targetCollection, iterable.iterator());
    return targetCollection;
  }

  public Collection<Poll> getLatestPolls() {
    Sort latestByDate = new Sort("modified");
    Iterable iterable = pollDao.findAll(latestByDate);
    List<Poll> targetCollection = new ArrayList<>();
    CollectionUtils.addAll(targetCollection, iterable.iterator());
    return targetCollection;
  }
}
