package com.haaretz.service;

import com.haaretz.dao.UserDao;
import com.haaretz.dao.UserSubmissionDao;
import com.haaretz.entities.User;
import com.haaretz.entities.UserFormSubmission;
import com.haaretz.entities.UserSubmission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * Created by elia.grady on 04/01/2017.
 */

@Service
public class UserSubmissionService {

  @Autowired
  private UserSubmissionDao userSubmissionDao;

  @Autowired
  private UserDao userDao;


  public Collection<UserSubmission> getAllUserSubmissions() {
    return userSubmissionDao.findAll();
  }


  public Collection<UserSubmission> getAllUserSubmissionsBySort(Sort sort) {
    return userSubmissionDao.findAll(sort);
  }

  public UserSubmission getUserSubmissionByPollContentId(String pollContentId) {
    return userSubmissionDao.findOneByPollContentId(pollContentId);
  }

  public void addUserSubmission(UserSubmission userSubmission) {
    userSubmissionDao.save(userSubmission);
  }


  public void addUserSubmission(UserFormSubmission userSubmissionForm) {
    UserSubmission userSubmission = userSubmissionForm.getUserSubmission();
    User searchedByPhone = userDao.findByPhoneNumber(userSubmissionForm.getCleanPhoneNumber());
    User user;
    if(searchedByPhone != null) {
      user = searchedByPhone;
      user.setId(searchedByPhone.getId());
      user.setName(userSubmissionForm.getExtraDetailName());
      user.setNewsletterSub(userSubmissionForm.hasSubscribedToNewsletter());
      user.setPerksSub(userSubmissionForm.hasSubscribedToPerks());
      user.setAddressLineCity(userSubmissionForm.getExtraDetailCity());
      user.setAddressLineStreet(userSubmissionForm.getExtraDetailAddress());
      user.setEmail(userSubmissionForm.getExtraDetailMail());
      userDao.save(user);
      userSubmission.setUserId(searchedByPhone.getId());
    }
    else {
      user = new User();
      user.setName(userSubmissionForm.getExtraDetailName());
      user.setPhoneNumber(userSubmissionForm.getCleanPhoneNumber());
      user.setNewsletterSub(userSubmissionForm.hasSubscribedToNewsletter());
      user.setPerksSub(userSubmissionForm.hasSubscribedToPerks());
      user.setAddressLineCity(userSubmissionForm.getExtraDetailCity());
      user.setAddressLineStreet(userSubmissionForm.getExtraDetailAddress());
      user.setEmail(userSubmissionForm.getExtraDetailMail());
      userDao.save(user);
      userSubmission.setUserId(user.getId());
    }
    userSubmissionDao.save(userSubmission);
  }

  public boolean markUserSubmissionAsLoss(long id) {
    return markUserSubmission(id, false);
  }

  public boolean markUserSubmissionAsWin(long id) {
    return markUserSubmission(id, true);
  }


  public boolean markUserSubmission(long id, boolean value) {
    boolean success = false;
    UserSubmission userSubmission = userSubmissionDao.findOneById(id);
    if(userSubmission != null) {
      userSubmission.setWin(value);
      userSubmissionDao.save(userSubmission);
      success = true;
    }
    return success;
  }
}
