package com.haaretz.entities;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by elia.grady on 08/01/2017.
 */

@Entity
@Table(name= "polls")
public class Poll implements Serializable {



  // An autogenerated id (unique for each user in the db)
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  @Basic
  private String pollDescription;
  @NotEmpty
  private String pollContentId;
  @NotEmpty
  private String pollTitle;
  @NotEmpty
  private String pollTagline;
  @NotEmpty
  private String question;
  @NotEmpty
  private String answer1;
  @NotEmpty
  private String answer2;
  @NotEmpty
  private String answer3;
  @NotEmpty
  private String answer4;
  @NotNull
  private int correctAnswer;

  @NotNull
  @Temporal(TemporalType.DATE)
  private Date startDate;

  @NotNull
  @Temporal(TemporalType.DATE)
  private Date endDate;


  @CreationTimestamp
  private Date created;

  @UpdateTimestamp
  private Date modified;

  public Poll() {

  }

  public Poll(String pollContentId, String question, String answer1, String answer2, String answer3, String answer4, int correctAnswer, Date startDate, Date endDate) {
    this.pollContentId = pollContentId;
    this.question = question;
    this.answer1 = answer1;
    this.answer2 = answer2;
    this.answer3 = answer3;
    this.answer4 = answer4;
    this.correctAnswer = correctAnswer;
    this.startDate = startDate;
    this.endDate = endDate;
  }


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getPollContentId() {
    return pollContentId;
  }

  public void setPollId(String pollContentId) {
    this.pollContentId = pollContentId;
  }

  public String getQuestion() {
    return question;
  }

  public void setQuestion(String question) {
    this.question = question;
  }

  public String getAnswer1() {
    return answer1;
  }

  public void setAnswer1(String answer1) {
    this.answer1 = answer1;
  }

  public String getAnswer2() {
    return answer2;
  }

  public void setAnswer2(String answer2) {
    this.answer2 = answer2;
  }

  public String getAnswer3() {
    return answer3;
  }

  public void setAnswer3(String answer3) {
    this.answer3 = answer3;
  }

  public String getAnswer4() {
    return answer4;
  }

  public void setAnswer4(String answer4) {
    this.answer4 = answer4;
  }

  public int getCorrectAnswer() {
    return correctAnswer;
  }

  public void setCorrectAnswer(int correctAnswer) {
    this.correctAnswer = correctAnswer;
  }

  public Date getStartDate() {
    return startDate;
  }

  public void setStartDate(Date startDate) {
    this.startDate = startDate;
  }

  public Date getEndDate() {
    return endDate;
  }

  public void setEndDate(Date endDate) {
    this.endDate = endDate;
  }

  public Date getCreated() {
    return created;
  }

  public void setCreated(Date created) {
    this.created = created;
  }

  public Date getModified() {
    return modified;
  }

  public void setModified(Date modified) {
    this.modified = modified;
  }

  public String getPollDescription() {
    return pollDescription;
  }

  public void setPollDescription(String pollDescription) {
    this.pollDescription = pollDescription;
  }

  public void setPollContentId(String pollContentId) {
    this.pollContentId = pollContentId;
  }

  public String getPollTagline() {
    return pollTagline;
  }

  public void setPollTagline(String pollTagline) {
    this.pollTagline = pollTagline;
  }

  public String getPollTitle() {
    return pollTitle;
  }

  public void setPollTitle(String pollTitle) {
    this.pollTitle = pollTitle;
  }

}
