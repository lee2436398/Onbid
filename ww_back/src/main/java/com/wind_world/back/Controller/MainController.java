package com.wind_world.back.Controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
@CrossOrigin(origins = { "*" }, maxAge = 6000)
@Controller
@RequestMapping("/select")



public class MainController {
   @RequestMapping("/mvmain")
   public String mvmain() {
      System.out.println("메인페이지로 이동");
      return "index";
   }
   
   @RequestMapping("/marker")
   public String mv3(Model m,HttpServletRequest request) {
      System.out.println("marker다음페이지");
      String jibun = request.getParameter("jibun");
      System.out.println(jibun);
      m.addAttribute("jibun",jibun);
      return "marker";
   }
}