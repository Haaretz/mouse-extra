package com.haaretz.entities;

import java.io.Serializable;

/**
 * Created by elia.grady on 04/01/2017.
 */
public class UserFormSubmission implements Serializable {

  public UserFormSubmission() {
  }

  private String pollContentId;
  private String extraAnswer;
  private String extraDetailName;
  private String extraDetailPhonePrefix;
  private String extraDetailPhone;
  private String extraDetailMail;
  private String extraDetailAddress;
  private String extraDetailCity;
  private String extraFormRegulationsCB;
  private String extraFormNewsletterCB;
  private String extraFormPerksCB;

  public UserSubmission getUserSubmission() {
    UserSubmission userSubmission = new UserSubmission();
    userSubmission.setPollContentId(this.pollContentId);
    userSubmission.setAnswerId(Character.getNumericValue(this.extraAnswer.charAt(8)));
//    user.setNewsletterSub(this.extraFormNewsletterCB != null && this.extraFormNewsletterCB.equals("on"));
//    user.setPerksSub(this.extraFormPerksCB != null && this.extraFormPerksCB.equals("on"));
    return userSubmission;
  }


  public String getCleanPhoneNumber() {
    return this.extraDetailPhonePrefix.replaceAll("-","").replaceAll(" ","")+this.extraDetailPhone.replaceAll("-","").replaceAll(" ","");
  }

  public boolean hasSubscribedToNewsletter() {
    return this.extraFormNewsletterCB != null && this.extraFormNewsletterCB.equals("on");
  }

  public boolean hasSubscribedToPerks() {
    return this.extraFormPerksCB != null && this.extraFormPerksCB.equals("on");
  }



  public String getPollContentId() {
    return pollContentId;
  }

  public void setPollContentId(String pollContentId) {
    this.pollContentId = pollContentId;
  }

  public String getExtraAnswer() {
    return extraAnswer;
  }

  public void setExtraAnswer(String extraAnswer) {
    this.extraAnswer = extraAnswer;
  }

  public String getExtraDetailName() {
    return extraDetailName;
  }

  public void setExtraDetailName(String extraDetailName) {
    this.extraDetailName = extraDetailName;
  }

  public String getExtraDetailPhonePrefix() {
    return extraDetailPhonePrefix;
  }

  public void setExtraDetailPhonePrefix(String extraDetailPhonePrefix) {
    this.extraDetailPhonePrefix = extraDetailPhonePrefix;
  }

  public String getExtraDetailPhone() {
    return extraDetailPhone;
  }

  public void setExtraDetailPhone(String extraDetailPhone) {
    this.extraDetailPhone = extraDetailPhone;
  }

  public String getExtraDetailMail() {
    return extraDetailMail;
  }

  public void setExtraDetailMail(String extraDetailMail) {
    this.extraDetailMail = extraDetailMail;
  }

  public String getExtraDetailAddress() {
    return extraDetailAddress;
  }

  public void setExtraDetailAddress(String extraDetailAddress) {
    this.extraDetailAddress = extraDetailAddress;
  }

  public String getExtraDetailCity() {
    return extraDetailCity;
  }

  public void setExtraDetailCity(String extraDetailCity) {
    this.extraDetailCity = extraDetailCity;
  }

  public String getExtraFormRegulationsCB() {
    return extraFormRegulationsCB;
  }

  public void setExtraFormRegulationsCB(String extraFormRegulationsCB) {
    this.extraFormRegulationsCB = extraFormRegulationsCB;
  }

  public String getExtraFormNewsletterCB() {
    return extraFormNewsletterCB;
  }

  public void setExtraFormNewsletterCB(String extraFormNewsletterCB) {
    this.extraFormNewsletterCB = extraFormNewsletterCB;
  }

  public String getExtraFormPerksCB() {
    return extraFormPerksCB;
  }

  public void setExtraFormPerksCB(String extraFormPerksCB) {
    this.extraFormPerksCB = extraFormPerksCB;
  }
}
