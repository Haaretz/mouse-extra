package com.haaretz.controller;

import com.haaretz.dao.UserDao;
import com.haaretz.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * Created by elia.grady on 08/01/2017.
 */
@RestController
@RequestMapping("/users")
public class UserController {

  // Private fields

  @Autowired
  private UserDao userDao;



  /**
   * POST /add  --> Create a new user and save it in the database.
   */
  @RequestMapping(value = "/add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
  public void add(@RequestBody User user) {
    userDao.save(user);
  }

  /**
   * GET /delete  --> Delete the user having the passed id.
   */
  @RequestMapping("/delete")
  @ResponseBody
  public String delete(long id) {
    try {
      User user = new User(id);
      userDao.delete(user);
    }
    catch (Exception ex) {
      return "Error deleting the user:" + ex.toString();
    }
    return "User successfully deleted!";
  }

  /**
   * GET /get-by-email  --> Return the id for the user having the passed
   * email.
   */
  @RequestMapping("/get-by-email")
  @ResponseBody
  public String getByEmail(String email) {
    String userId;
    try {
      User user = userDao.findByEmail(email);
      userId = String.valueOf(user.getId());
    }
    catch (Exception ex) {
      userId = "";
      return "User not found";
    }
    return "The user id is: " + userId;
  }

  /**
   * GET /update  --> Update the email and the name for the user in the
   * database having the passed id.
   */
  @RequestMapping("/update")
  @ResponseBody
  public String updateUser(long id, String email, String name) {
    try {
      User user = userDao.findOne(id);
      user.setEmail(email);
      user.setName(name);
      userDao.save(user);
    }
    catch (Exception ex) {
      return "Error updating the user: " + ex.toString();
    }
    return "User successfully updated!";
  }


}
