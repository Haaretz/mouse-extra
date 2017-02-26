package com.haaretz.controller;

import com.haaretz.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by elia.grady on 04/01/2017.
 */

@RestController
@RequestMapping("/report")
public class ReportController {

  @Autowired
  private ReportService reportService;




  @RequestMapping(value = "/{id:.+}", method = RequestMethod.GET)
  @ResponseBody
  public String pollReportAsJson(@PathVariable("id") String id, HttpServletResponse response) {
    Object o = reportService.getReportForPollContentId(id);
    response.setContentType("application/json; charset=utf-8");
    return o.toString();
  }

//  @RequestMapping(value = "/{id:.+}", method = RequestMethod.GET)
//  @ResponseBody
//  public String pollReportAsCSV(@PathVariable("id") String id, HttpServletResponse response) {
//    Object o = reportService.getReportForPollContentId(id);
//    response.setContentType("text/plain; charset=utf-8");
//    response.setContentType("text/plain; charset=utf-8");
//    String data = "a,b,c\n1,2,3\n3,4,5";
//    return o.toString();
//  }



}
