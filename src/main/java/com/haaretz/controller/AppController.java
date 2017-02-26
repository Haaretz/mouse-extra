package com.haaretz.controller;

import org.apache.commons.collections.FastTreeMap;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;
import java.util.Random;

/**
 * Created by elia.grady on 04/01/2017.
 */

@Controller
@RequestMapping("/")
public class AppController {


  @RequestMapping(value = "/", method = RequestMethod.GET)
  public String homepage() {
    return "index";
  }

  @RequestMapping(value = "/test", method = RequestMethod.GET)
  public String test(Model model) {
    addFormsToModel(model);
    return "test";
  }

  @RequestMapping(value = "/admin", method = RequestMethod.GET)
  public String admin(Model model) {
    return "admin";
  }

  private void addFormsToModel(Model model) {
    Map<Integer, Map<String, String>> mockPolls = getMockPolls();
    Random generator = new Random();
    int bound = mockPolls.keySet().size();
    int i = generator.nextInt(bound) + 1;

    Map<String, String> exampleFormAttributesMap = mockPolls.get(i);
    model.addAllAttributes(exampleFormAttributesMap);
  }

  /**
   * generic Spring MVC error handler
   *
   * @param e - the exception
   * @return a view name
   * @link http://static.springsource.org/spring/docs/3.2.x/spring-framework-reference/html/mvc.html#mvc-ann-exceptionhandler
   */
  @ExceptionHandler(Exception.class)
  public ModelAndView exceptionHandler(Exception e) {
    ModelAndView mv = new ModelAndView("error");
    StringBuilder stackTrace = new StringBuilder();
    renderStackTrace(e, stackTrace);
    mv.addObject("exceptionMessage", e.getMessage());
    mv.addObject("exceptionStackTrace", stackTrace.toString());
    return mv;
  }

  public void renderStackTrace(Throwable e, StringBuilder stackTrace) {
    for (StackTraceElement stackTraceElement : e.getStackTrace()) {
      stackTrace.append("<div class=\"stack-trace\">").append(stackTraceElement.toString()).append("</div>");
    }
    if (e.getCause() != null && e.getCause() != e) {
      stackTrace.append("<div class=\"caused-by\">").append("caused by ").append(e.getCause().getClass()).append(" - ").append(e.getCause().getMessage()).append("</div>");
      renderStackTrace(e.getCause(), stackTrace);
    }
  }


  Map<Integer, Map<String, String>> getMockPolls() {
    Map<Integer, Map<String, String>> mock = new FastTreeMap();
    Map<String, String> poll1 =  new FastTreeMap();
    Map<String, String> poll2 =  new FastTreeMap();
    Map<String, String> poll3 =  new FastTreeMap();
    Map<String, String> poll4 =  new FastTreeMap();
    Map<String, String> poll5 =  new FastTreeMap();
    Map<String, String> poll6 =  new FastTreeMap();
    Map<String, String> poll7 =  new FastTreeMap();
    Map<String, String> poll8 =  new FastTreeMap();
    Map<String, String> poll9 =  new FastTreeMap();
    Map<String, String> poll10 = new FastTreeMap();
    Map<String, String> poll11 = new FastTreeMap();
    Map<String, String> poll12 = new FastTreeMap();
    Map<String, String> poll13 = new FastTreeMap();
    Map<String, String> poll14 = new FastTreeMap();
    Map<String, String> poll15 = new FastTreeMap();

    mock.put(1, poll1);
    mock.put(2, poll2);
    mock.put(3, poll3);
    mock.put(4, poll4);
    mock.put(5, poll5);
    mock.put(6, poll6);
    mock.put(7, poll7);
    mock.put(8, poll8);
    mock.put(9, poll9);
    mock.put(10, poll10);
    mock.put(11, poll11);
    mock.put(12, poll12);
    mock.put(13, poll13);
    mock.put(14, poll14);
    mock.put(15, poll15);


    poll1.put("title", "רוצים לזכות בכרטיס לסרט?");
    poll1.put("tagline", "ענו נכונה על השאלה ואולי תזכו");
    poll1.put("question","כמה עשורים מלווה אותנו קופיקו?");
    poll1.put("answer1","ארבעה");
    poll1.put("answer2","שלושה");
    poll1.put("answer3","חמישה");
    poll1.put("answer4","שישה");
    poll1.put("correctAnswer","4");
    poll1.put("pollContentId","7.1111");

    poll2.put("title", "רוצים לזכות בכרטיס לסרט?");
    poll2.put("tagline", "ענו נכונה על השאלה ואולי תזכו");
    poll2.put("question","כמה סרטים באורך מלא ביים אבישי סיון?");
    poll2.put("answer1","אחד");
    poll2.put("answer2","שניים");
    poll2.put("answer3","שלושה");
    poll2.put("answer4","עשרה");
    poll2.put("correctAnswer","3");
    poll2.put("pollContentId","7.1112");

    poll3.put("title", "רוצים לזכות בכרטיס לסרט?");
    poll3.put("tagline", "ענו נכונה על השאלה ואולי תזכו");
    poll3.put("question",    "מה היה שם הסינגל הראשון שהוציאה נגה ארז?");
    poll3.put("answer1","Crystal Castles");
    poll3.put("answer2","Pity");
    poll3.put("answer3","loud and quiet");
    poll3.put("answer4","Dance while you shoot");
    poll3.put("correctAnswer","4");
    poll3.put("pollContentId","7.1113");

    poll4.put("title", "רוצים לזכות בכרטיס לסרט?");
    poll4.put("tagline", "ענו נכונה על השאלה ואולי תזכו");
    poll4.put("question","מי הפיק את אלבומה האחרון של נורית גלרון?");
    poll4.put("answer1","אביב גפן");
    poll4.put("answer2","עופר מאירי");
    poll4.put("answer3","טונה");
    poll4.put("answer4","אוהד חיטמן");
    poll4.put("correctAnswer","2");
    poll4.put("pollContentId","7.1114");

    poll5.put("title", "רוצים לזכות בכרטיס לסרט?");
    poll5.put("tagline", "ענו נכונה על השאלה ואולי תזכו");
    poll5.put("question","עם איזה סרט נסע בראבא לטקס האוסקר?");
    poll5.put("answer1","\"זינוק בעלייה\"");
    poll5.put("answer2","\"הערת שוליים\"");
    poll5.put("answer3","\"מסע אלונקות\"");
    poll5.put("answer4","\"נישואים פיקטיביים\"");
    poll5.put("correctAnswer","2");
    poll5.put("pollContentId","7.1115");

    poll6.put("title", "רוצים לזכות בכרטיס לסרט?");
    poll6.put("tagline", "ענו נכונה על השאלה ואולי תזכו");
    poll6.put("question","היכן הוצג ראשונה הסרט \"לא פה, לא שם\"?");
    poll6.put("answer1","פסטיבל חיפה");
    poll6.put("answer2","פסטיבל טורנטו");
    poll6.put("answer3","פסטיבל סאן סבסטיאן");
    poll6.put("answer4","פסטיבל ברלין");
    poll6.put("correctAnswer","2");
    poll6.put("pollContentId","7.1116");

    poll7.put("title", "רוצים לזכות בכרטיס לסרט?");
    poll7.put("tagline", "ענו נכונה על השאלה ואולי תזכו");
    poll7.put("question","איזה שיר יבצע אולארצ'יק במופע?");
    poll7.put("answer1","\"מסטיק\"");
    poll7.put("answer2","\"ילד מזדקן\"");
    poll7.put("answer3","\"בן בסט\"");
    poll7.put("answer4","כל התשובות נכונות");
    poll7.put("correctAnswer","4");
    poll7.put("pollContentId","7.1117");

    poll8.put("title", "רוצים לזכות בכרטיס לסרט?");
    poll8.put("tagline", "ענו נכונה על השאלה ואולי תזכו");
    poll8.put("question","איזה שיר יבצעו השניים במופע?");
    poll8.put("answer1","\"עד אשר ישוב\"");
    poll8.put("answer2","\"עטוף ברחמים\"");
    poll8.put("answer3","\"מבול\"");
    poll8.put("answer4","כל התשובות נכונות");
    poll8.put("correctAnswer","4");
    poll8.put("pollContentId","7.1118");

    poll9.put("title", "רוצים לזכות בכרטיס לסרט?");
    poll9.put("tagline", "ענו נכונה על השאלה ואולי תזכו");
    poll9.put("question","כמה שירים יש באלבום \"השחור החדש\"?");
    poll9.put("answer1","חמישה");
    poll9.put("answer2","שבעה");
    poll9.put("answer3","תשעה");
    poll9.put("answer4","עשרה");
    poll9.put("correctAnswer","3");
    poll9.put("pollContentId","7.1119");

    poll10.put("title", "רוצים לזכות בכרטיס לסרט?");
    poll10.put("tagline", "ענו נכונה על השאלה ואולי תזכו");
    poll10.put("question","מי הפיק את אלבומה השני של איה זהבי פייגלין?");
    poll10.put("answer1","היא בעצמה");
    poll10.put("answer2","עדי רותם");
    poll10.put("answer3","הוד שריד");
    poll10.put("answer4","אריאל טוכמן");
    poll10.put("correctAnswer","4");
    poll10.put("pollContentId","7.1120");

    poll11.put("title", "רוצים לזכות בכרטיס לסרט?");
    poll11.put("tagline", "ענו נכונה על השאלה ואולי תזכו");
    poll11.put("question","לפני כמה שנים הוקם ההרכב?");
    poll11.put("answer1","עשר שנים");
    poll11.put("answer2","עשרים שנה");
    poll11.put("answer3","חמישים שנה");
    poll11.put("answer4","ארבעים שנה");
    poll11.put("correctAnswer","1");
    poll11.put("pollContentId","7.1121");

    poll12.put("title", "רוצים לזכות בכרטיס לסרט?");
    poll12.put("tagline", "ענו נכונה על השאלה ואולי תזכו");
    poll12.put("question","מי הפיק מוזיקלית את הסינגל החדש של קפה שחור חזק?");
    poll12.put("answer1","עמית שגיא");
    poll12.put("answer2","אורי הלמו");
    poll12.put("answer3","אילק סהלו");
    poll12.put("answer4","נצ'י נצ'");
    poll12.put("correctAnswer","1");
    poll12.put("pollContentId","7.1122");

    poll13.put("title", "רוצים לזכות בכרטיס לסרט?");
    poll13.put("tagline", "ענו נכונה על השאלה ואולי תזכו");
    poll13.put("question","נטלי אימברוליה מקדמת בימים אלה את נושא:");
    poll13.put("answer1","בריאות האישה באמריקה");
    poll13.put("answer2","בריאות האישה באסיה");
    poll13.put("answer3","בריאות האישה באפריקה");
    poll13.put("answer4","בריאות האישה באירופה");
    poll13.put("correctAnswer","3");
    poll13.put("pollContentId","7.1123");

    poll14.put("title", "רוצים לזכות בכרטיס לסרט?");
    poll14.put("tagline", "ענו נכונה על השאלה ואולי תזכו");
    poll14.put("question","כמה אלבומים יצאו לעידן רפאל חביב עד כה?");
    poll14.put("answer1","שישה");
    poll14.put("answer2","עשרה");
    poll14.put("answer3","ארבעה");
    poll14.put("answer4","שניים");
    poll14.put("correctAnswer","4");
    poll14.put("pollContentId","7.1124");

    poll15.put("title", "רוצים לזכות בכרטיס לסרט?");
    poll15.put("tagline", "ענו נכונה על השאלה ואולי תזכו");
    poll15.put("question","מי ביים את הסרט \"בגרות\"?");
    poll15.put("answer1","ראדו גבראה");
    poll15.put("answer2","כריסטיאן מונג'יו");
    poll15.put("answer3","אנדרי זינקה");
    poll15.put("answer4","הוראציו מלייאלה");
    poll15.put("correctAnswer","2");
    poll15.put("pollContentId","7.1125");


    return mock;
  }


}
