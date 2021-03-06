package com.haaretz.entities;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by elia.grady on 04/01/2017.
 */
@Entity
@Table(name= "submissions")
public class UserSubmission implements Persistable {
  // An autogenerated id (unique for each user in the db)
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  public UserSubmission() {
  }

  private long userId;

  @NotNull
  private String pollContentId;
  @NotNull
  private int answerId;

  @NotNull
  private boolean isWin = false; // Win Marker

  @CreationTimestamp
  private Date submissionDate;


  public Long getId() {
    return id;
  }

  @Override
  public boolean isNew() {
    return true;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public long getUser() {
    return this.userId;
  }

  public void setUserId(long userId) {
    this.userId = userId;
  }

  public String getPollContentId() {
    return pollContentId;
  }

  public void setPollContentId(String pollContentId) {
    this.pollContentId = pollContentId;
  }

  public int getAnswerId() {
    return answerId;
  }

  public void setAnswerId(int answerId) {
    this.answerId = answerId;
  }

  public Date getSubmissionDate() {
    return submissionDate;
  }

  public void setSubmissionDate(Date submissionDate) {
    this.submissionDate = submissionDate;
  }

  public boolean isWin() {
    return isWin;
  }

  public void setWin(boolean win) {
    isWin = win;
  }
}
