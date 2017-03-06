package com.haaretz.dao;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.haaretz.HibernateUtil;
import com.haaretz.entities.Poll;
import com.haaretz.entities.User;
import com.haaretz.entities.UserFormSubmission;
import com.haaretz.entities.UserSubmission;
import org.hibernate.*;
import org.hibernate.type.BooleanType;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by elia.grady on 04/01/2017.
 */

@Service
public class ReportDao {

  /*
  public Object getReportForPollCustom(String pollContentId) {

    SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    Session session = sessionFactory.getCurrentSession();

    Transaction tx = session.beginTransaction();
    TypedQuery<ReportLine> query = session.createQuery("" +
      " FROM Poll polls" +
      " INNER JOIN UserSubmission submissions" +
      " INNER JOIN User users" +
      " ON polls.pollContentId = submissions.pollContentId " +
      " AND submissions.userId = users.id " +
      " WHERE polls.pollContentId = :pollContentId", ReportLine.class);

    query.setParameter("pollContentId", pollContentId);
    List list = query.getResultList();
    final GsonBuilder gsonBuilder = new GsonBuilder();
    Type listType = new TypeToken<List<Object[]>>() {}.getType();
    gsonBuilder.registerTypeAdapter(listType, new ReportLineSerializer());
    gsonBuilder.setPrettyPrinting();
    final Gson gson = gsonBuilder.create();
    String jsonString1 = gson.toJson(list, listType);
    String jsonString = gson.toJson(list);
    return jsonString;
  }

 */


  public Object getReportForPoll(String pollContentId) {
    SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    Session session = sessionFactory.getCurrentSession();

    Transaction tx = session.beginTransaction();
    SQLQuery sqlQuery = session.createSQLQuery("SELECT polls.poll_content_id, polls.question," +
      " polls.correct_answer, polls.answer1, polls.answer2, polls.answer3, polls.answer4, users.name," +
      " users.email, users.phone_number, users.address_line_street, users.address_line_city, users.newsletter_sub," +
      " users.perks_sub, submissions.id as sub_id, submissions.submission_date, submissions.answer_id, submissions.is_win, submissions.user_id" +
      " FROM polls INNER JOIN submissions INNER JOIN users ON polls.poll_content_id = submissions.poll_content_id" +
      " AND submissions.user_id = users.id WHERE (SELECT COUNT(id)=1 FROM submissions WHERE submissions.user_id = users.id and submissions.poll_content_id=polls.poll_content_id) AND polls.poll_content_id=:pollContentId");

    sqlQuery.setString("pollContentId", pollContentId);
    List list = sqlQuery.list();
    ArrayList<ReportLine> reportLines = new ArrayList<>(list.size());
    for(Object o: list) {
      reportLines.add(new ReportLine(o));
    }
    final GsonBuilder gsonBuilder = new GsonBuilder();
    Type listType = new TypeToken<List<ReportLine>>() {}.getType();
    gsonBuilder.registerTypeAdapter(ReportLine.class, new ReportLineSerializer());
    gsonBuilder.setPrettyPrinting();
    final Gson gson = gsonBuilder.create();
    String jsonString = gson.toJson(reportLines);
    return jsonString;
  }







  private void test() {
// Prep work
    SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    Session session = sessionFactory.getCurrentSession();

    //HQL example - Get All Employees
//    Transaction tx = session.beginTransaction();
//    Query query = session.createQuery("from Employee");
//    List<Employee> empList = query.list();
//    for(Employee emp : empList){
//      System.out.println("List of Employees::"+emp.getId()+","+emp.getAddress().getCity());
//    }

    //HQL example - Get Employee with id
//    query = session.createQuery("from Employee where id= :id");
//    query.setLong("id", 3);
//    Employee emp = (Employee) query.uniqueResult();
//    System.out.println("Employee Name="+emp.getName()+", City="+emp.getAddress().getCity());

    //HQL pagination example
//    query = session.createQuery("from Employee");
//    query.setFirstResult(0); //starts with 0
//    query.setFetchSize(2);
//    empList = query.list();
//    for(Employee emp4 : empList){
//      System.out.println("Paginated Employees::"+emp4.getId()+","+emp4.getAddress().getCity());
//    }

    //HQL Update Employee
//    query = session.createQuery("update Employee set name= :name where id= :id");
//    query.setParameter("name", "Pankaj Kumar");
//    query.setLong("id", 1);
//    int result = query.executeUpdate();
//    System.out.println("Employee Update Status="+result);

    //HQL Delete Employee, we need to take care of foreign key constraints too
//    query = session.createQuery("delete from Address where id= :id");
//    query.setLong("id", 4);
//    result = query.executeUpdate();
//    System.out.println("Address Delete Status="+result);

//    query = session.createQuery("delete from Employee where id= :id");
//    query.setLong("id", 4);
//    result = query.executeUpdate();
//    System.out.println("Employee Delete Status="+result);

    //HQL Aggregate function examples
//    query = session.createQuery("select sum(salary) from Employee");
//    double sumSalary = (Double) query.uniqueResult();
//    System.out.println("Sum of all Salaries= "+sumSalary);

    //HQL join examples
//    query = session.createQuery("select e.name, a.city from Employee e "
//      + "INNER JOIN e.address a");
//    List<Object[]> list = query.list();
//    for(Object[] arr : list){
//      System.out.println(Arrays.toString(arr));
//    }

    //HQL group by and like example
//    query = session.createQuery("select e.name, sum(e.salary), count(e)"
//      + " from Employee e where e.name like '%i%' group by e.name");
//    List<Object[]> groupList = query.list();
//    for(Object[] arr : groupList){
//      System.out.println(Arrays.toString(arr));
//    }

    //HQL order by example
//    query = session.createQuery("from Employee e order by e.id desc");
//    empList = query.list();
//    for(Employee emp3 : empList){
//      System.out.println("ID Desc Order Employee::"+emp3.getId()+","+emp3.getAddress().getCity());
//    }

    //rolling back to save the test data
//    tx.rollback();

    //closing hibernate resources
    sessionFactory.close();
  }


  private class ReportLine {

    private long sub_id;
    private String pollContentId;
    private String question;
    private int correctAnswer;
    private String answer1;
    private String answer2;
    private String answer3;
    private String answer4;
    private String name;
    private String email;
    private String phoneNumber;
    private String addressLineStreet;
    private String addressLineCity;
    private boolean newsletterSub;
    private boolean perksSub;
    private String submissionDate;
    private int answerId;
    private boolean isWin;
    private long userId;

    public ReportLine() {
    }

    public ReportLine(Object obj) {
      if(obj instanceof Object[]) {
        Object[] o = (Object[]) obj;
        int i = 0;
        this.pollContentId = (String) o[i];i++;
        this.question = (String) o[i];i++;
        this.correctAnswer = (int) o[i];i++;
        this.answer1 = (String) o[i];i++;
        this.answer2 = (String) o[i];i++;
        this.answer3 = (String) o[i];i++;
        this.answer4 = (String) o[i];i++;
        this.name = (String) o[i];i++;
        this.email = (String) o[i];i++;
        this.phoneNumber = (String) o[i];i++;
        this.addressLineStreet = (String) o[i];i++;
        this.addressLineCity = (String) o[i];i++;
        this.newsletterSub = (boolean) o[i];i++;
        this.perksSub = (boolean) o[i];i++;
        this.sub_id = ((BigInteger) o[i]).longValueExact();i++;
        this.submissionDate = ((Timestamp) o[i]).toString();i++;
        this.answerId = (int) o[i];i++;
        this.isWin = (boolean) o[i];i++;
        this.userId = ((BigInteger) o[i]).longValueExact();
      }
    }

    public String getPollContentId() {
      return pollContentId;
    }

    public void setPollContentId(String pollContentId) {
      this.pollContentId = pollContentId;
    }

    public String getQuestion() {
      return question;
    }

    public void setQuestion(String question) {
      this.question = question;
    }

    public int getCorrectAnswer() {
      return correctAnswer;
    }

    public void setCorrectAnswer(int correctAnswer) {
      this.correctAnswer = correctAnswer;
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

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }

    public String getEmail() {
      return email;
    }

    public void setEmail(String email) {
      this.email = email;
    }

    public String getPhoneNumber() {
      return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
      this.phoneNumber = phoneNumber;
    }

    public String getAddressLineStreet() {
      return addressLineStreet;
    }

    public void setAddressLineStreet(String addressLineStreet) {
      this.addressLineStreet = addressLineStreet;
    }

    public String getAddressLineCity() {
      return addressLineCity;
    }

    public void setAddressLineCity(String addressLineCity) {
      this.addressLineCity = addressLineCity;
    }

    public boolean isNewsletterSub() {
      return newsletterSub;
    }

    public void setNewsletterSub(boolean newsletterSub) {
      this.newsletterSub = newsletterSub;
    }

    public boolean isPerksSub() {
      return perksSub;
    }

    public void setPerksSub(boolean perksSub) {
      this.perksSub = perksSub;
    }

    public String getSubmissionDate() {
      return submissionDate;
    }

    public void setSubmissionDate(String submissionDate) {
      this.submissionDate = submissionDate;
    }

    public int getAnswerId() {
      return answerId;
    }

    public void setAnswerId(int answerId) {
      this.answerId = answerId;
    }

    public boolean isWin() {
      return isWin;
    }

    public void setWin(boolean win) {
      isWin = win;
    }

    public long getUserId() {
      return userId;
    }

    public void setUserId(long userId) {
      this.userId = userId;
    }

    public long getSubmissionId() {
      return sub_id;
    }

    public void setSub_id(long sub_id) {
      this.sub_id = sub_id;
    }
  }

  private class ReportLineSerializer implements JsonSerializer<ReportLine> {

    @Override
    public JsonElement serialize(ReportLine reportLine, Type type, JsonSerializationContext jsonSerializationContext) {
      final JsonObject jsonObject = new JsonObject();
//      int i = 0;
//      jsonObject.addProperty("pollContentId", (String) reportLine[i]);i++;
//      jsonObject.addProperty("question", (String) reportLine[i]);i++;
//      jsonObject.addProperty("correctAnswer", (Number) reportLine[i]);i++;
//      jsonObject.addProperty("answer1", (String) reportLine[i]);i++;
//      jsonObject.addProperty("answer2", (String) reportLine[i]);i++;
//      jsonObject.addProperty("answer3", (String) reportLine[i]);i++;
//      jsonObject.addProperty("answer4", (String) reportLine[i]);i++;
//      jsonObject.addProperty("name", (String) reportLine[i]);i++;
//      jsonObject.addProperty("email", (String) reportLine[i]);i++;
//      jsonObject.addProperty("phoneNumber", (String) reportLine[i]);i++;
//      jsonObject.addProperty("addressLineStreet", (String) reportLine[i]);i++;
//      jsonObject.addProperty("addressLineCity", (String) reportLine[i]);i++;
//      jsonObject.addProperty("newsletterSub", (Boolean) reportLine[i]);i++;
//      jsonObject.addProperty("perksSub", (Boolean) reportLine[i]);i++;
//      jsonObject.addProperty("submissionDate", (String) reportLine[i]);i++;
//      jsonObject.addProperty("answerId", (Number) reportLine[i]);i++;
//      jsonObject.addProperty("isWin", (Boolean) reportLine[i]);i++;
//      jsonObject.addProperty("userId", (Number) reportLine[i]);i++;


      jsonObject.addProperty("pollContentId", reportLine.getPollContentId());
      jsonObject.addProperty("question", reportLine.getQuestion());
      jsonObject.addProperty("correctAnswer", reportLine.getCorrectAnswer());
      jsonObject.addProperty("answer1", reportLine.getAnswer1());
      jsonObject.addProperty("answer2", reportLine.getAnswer2());
      jsonObject.addProperty("answer3", reportLine.getAnswer3());
      jsonObject.addProperty("answer4", reportLine.getAnswer4());
      jsonObject.addProperty("name", reportLine.getName());
      jsonObject.addProperty("email", reportLine.getEmail());
      jsonObject.addProperty("phoneNumber", reportLine.getPhoneNumber());
      jsonObject.addProperty("addressLineStreet", reportLine.getAddressLineStreet());
      jsonObject.addProperty("addressLineCity", reportLine.getAddressLineCity());
      jsonObject.addProperty("newsletterSub", reportLine.isNewsletterSub());
      jsonObject.addProperty("perksSub", reportLine.isPerksSub());
      jsonObject.addProperty("sub_id", reportLine.getSubmissionId());
      jsonObject.addProperty("submissionDate", reportLine.getSubmissionDate());
      jsonObject.addProperty("answerId", reportLine.getAnswerId());
      jsonObject.addProperty("isWin", reportLine.isWin());
      jsonObject.addProperty("userId", reportLine.getUserId());

      return jsonObject;
    }
  }
}


//  public List findSubmissionsWithPollContentId(String pollContentId) {
//    return em
////      .createQuery("select polls.poll_content_id, polls.question, polls.correct_answer, polls.answer1, polls.answer2, polls.answer3, polls.answer4, users.name, users.email, users.phone_number, users.address_line_street, users.address_line_city, users.newsletter_sub, users.perks_sub, submissions.submission_date, submissions.answer_id, submissions.is_win, submissions.user_id from polls inner join submissions inner join users on polls.poll_content_id = submissions.poll_content_id AND submissions.user_id = users.id where polls.poll_content_id=:pollContentId")
//      .createQuery("SELECT polls.poll_content_id, polls.question, polls.correct_answer, polls.answer1, polls.answer2, polls.answer3, polls.answer4, users.name, users.email, users.phone_number, users.address_line_street, users.address_line_city, users.newsletter_sub, users.perks_sub, submissions.submission_date, submissions.answer_id, submissions.is_win, submissions.user_id FROM polls INNER JOIN submissions INNER JOIN users ON polls.poll_content_id = submissions.poll_content_id AND submissions.user_id = users.id WHERE polls.poll_content_id=:pollContentId")
//      .setParameter("pollContentId", pollContentId)
////      .setMaxResults(10)
//      .getResultList();
//  }
//
//
////  @NamedQuery(
////    name = "getSubmissionsByPollContentId",
////    query = ""
////  )
//
//  @Query(value = "SELECT polls.poll_content_id, polls.question, polls.correct_answer, polls.answer1, polls.answer2, polls.answer3, polls.answer4, users.name, users.email, users.phone_number, users.address_line_street, users.address_line_city, users.newsletter_sub, users.perks_sub, submissions.submission_date, submissions.answer_id, submissions.is_win, submissions.user_id FROM Poll INNER JOIN UserSubmission INNER JOIN User ON polls.poll_content_id = submissions.poll_content_id AND submissions.user_id = users.id WHERE polls.poll_content_id=:pollContentId")
//  Collection<ReportLine> findAllByCustomQueryAndStream(String pollContentId);

//  ReportLine findOneByPollContentId(String pollContentId);

