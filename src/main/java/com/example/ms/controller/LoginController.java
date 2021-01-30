package com.example.ms.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpRequest;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.ms.GlobalProperties;
import com.example.ms.MyAppProperties;

@Controller
public class LoginController {
	
	@Autowired
	private GlobalProperties globalProp;
	
	@Autowired
	private MyAppProperties myapp;
	
	@Value("${user.fname}")
	private String username;
	
	private static int count = 0;
	
	@RequestMapping(value = "/error", method = RequestMethod.GET, produces= MediaType.TEXT_HTML_VALUE)
	public void error(HttpServletRequest req, HttpServletResponse res, Exception exc) {
		Object status = req.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
		String errmsg = "error";
		if(status != null) {
			System.out.println("status: " + status);
			Object errorUri = req.getAttribute(RequestDispatcher.ERROR_REQUEST_URI);
			System.out.println("errorUri " + errorUri + " " + req.getContextPath());
			Integer errorCode = (Integer)status;
			if(errorCode == 403 && errorUri.equals(req.getContextPath() + "/")) {
				System.out.println("nok");
				errmsg = "You are not entitled";
				res.setStatus(200);
				try {
					res.getOutputStream().write(errmsg.getBytes());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		/*res.setStatus(200);
		try {
			res.getOutputStream().write(errmsg.getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}
	
	/*@RequestMapping(value = "/", method = RequestMethod.GET)
    public String showLoginPage(ModelMap model) {
        model.put("name", "Manish");
        return "welcome";
    }*/

	@PostMapping(value = "/")
    public String showLoginPage(HttpServletRequest req) {
        //model.put("name", "Manish");
        System.out.println("POST");
        return "welcome2";
    }
	
	@GetMapping(value = "/welcome")
    public String showWelcome(HttpServletRequest req) {
        //model.put("name", "Manish");
        System.out.println("Get");
        count = 0;
        return "welcome2";
    }
	
	@GetMapping(value = "/")
    public String showLoginPage2(ModelMap model, HttpServletRequest req) {
		System.out.println("***************** " + req.getAttribute("attr1"));
		System.out.println(myapp.getRefreshRate() + " " + count);
        model.put("name", "Manish");
        if(count==0) {
        	req.setAttribute("attr1", true);
        } else {
        	req.setAttribute("attr1", false);
        }
        ++count;
        
        req.setAttribute("attr2", "test");
        return "welcome";
    }
	
	
}
