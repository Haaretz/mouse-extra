package com.haaretz.service;

import com.haaretz.dao.PollDao;
import com.haaretz.dao.ReportDao;
import com.haaretz.entities.Poll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by elia.grady on 15/01/2017.
 */
@Service
public class ReportService {
  @Autowired
  private PollDao pollDao;
  @Autowired
  private ReportDao reportDao;

  public Object getReportForPollContentId(String pollContentId) {
    Poll poll = pollDao.findOneByPollContentId(pollContentId);
    Object userSubmissions = null;
    if(poll != null) {
      userSubmissions = reportDao.getReportForPoll(pollContentId);
    }

    return userSubmissions;
  }
}
