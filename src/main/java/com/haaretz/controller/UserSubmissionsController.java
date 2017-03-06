package com.haaretz.controller;

import com.haaretz.entities.UserFormSubmission;
import com.haaretz.entities.UserSubmission;
import com.haaretz.service.UserSubmissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * Created by elia.grady on 04/01/2017.
 */

@RestController
@RequestMapping("/submissions")
public class UserSubmissionsController {

  @Autowired
  private UserSubmissionService userSubmissionService;


  @RequestMapping(method = RequestMethod.GET)
  public Collection<UserSubmission> getAllUserSubmissions() {
    return userSubmissionService.getAllUserSubmissions();
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  public UserSubmission getUserSubmissionByPollContentId(@PathVariable("id") String id) {
    return userSubmissionService.getUserSubmissionByPollContentId(id);
  }

  @RequestMapping(value = "/add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
  public void addPoll(@RequestBody UserSubmission userSubmission) {
    userSubmissionService.addUserSubmission(userSubmission);
  }

  @RequestMapping(value = "/submit", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
  public void addPollFormFromJson(@RequestBody UserFormSubmission userFormSubmission) {
    userSubmissionService.addUserSubmission(userFormSubmission);
  }

  @RequestMapping(value = "/submit", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
  public void addPollForm(@ModelAttribute UserFormSubmission userFormSubmission) {
    userSubmissionService.addUserSubmission(userFormSubmission.getUserSubmission());
  }

  @RequestMapping(value = "mark-win/{id}", method = RequestMethod.GET)
  public boolean updateUserSubmission(@PathVariable("id") String id) {
    return userSubmissionService.markUserSubmissionAsWin(Long.parseLong(id));
  }

  @RequestMapping(value = "mark-loss/{id}", method = RequestMethod.GET)
  public boolean updateUserSubmissionLoss(@PathVariable("id") String id) {
    return userSubmissionService.markUserSubmissionAsLoss(Long.parseLong(id));
  }



}
