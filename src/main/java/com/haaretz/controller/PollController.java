package com.haaretz.controller;

import com.haaretz.entities.Poll;
import com.haaretz.service.PollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * Created by elia.grady on 04/01/2017.
 */

@RestController
@RequestMapping("/polls")
public class PollController {

  @Autowired
  private PollService pollService;

  @RequestMapping(value = "/add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
  public void addPoll(@RequestBody Poll poll) throws Exception {
    pollService.addPoll(poll);
  }

  @RequestMapping(value = "/update", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
  public void updatePoll(@RequestBody Poll poll) throws Exception {
    pollService.updatePoll(poll);
  }

  @RequestMapping(value = "/get/{id:.+}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  public Poll getPollByContentId(@PathVariable(value = "id") String contentId) {
    return pollService.getPollById(contentId);
  }

  @RequestMapping(value = "/getall", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  public Collection<Poll> getAllPolls() {
    return pollService.getAllPolls();
  }

  @RequestMapping(value = "/getlatest", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  public Collection<Poll> getLatestPolls() {
    return pollService.getLatestPolls();
  }

}
