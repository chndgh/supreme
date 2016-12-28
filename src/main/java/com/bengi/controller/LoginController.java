package com.bengi.controller;

import com.bengi.model.User;
import com.bengi.repository.ParticipantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;

/**
 * Created by edward on 16/12/20.
 */
@Controller
public class LoginController {

    @Autowired private SimpMessagingTemplate messagingTemplate;
    @Autowired private ParticipantRepository participantRepository;

    private static final String LOGIN = "/app/chat.login";
    private static final String LOGOUT = "/app/chat.logout";

    @RequestMapping(value = "login", method= RequestMethod.POST)
    public String login(HttpServletRequest httpRequest, User user) throws ServletException {
        user.setTime(new Date());
        httpRequest.getSession().setAttribute("user", user);
        System.out.println("--------: " + httpRequest.getSession().getAttribute("user"));
        messagingTemplate.convertAndSend(LOGIN, user);
        for (Map.Entry<String, User> entry : participantRepository.getActiveSessions().entrySet()) {

            System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());

        }
        if(participantRepository.getActiveSessions().containsKey(httpRequest.getSession().getId())){
            messagingTemplate.convertAndSend(LOGOUT, participantRepository.getActiveSessions().get(httpRequest.getSession().getId()));
        }
        participantRepository.add(httpRequest.getSession().getId(), user);
        return "redirect:/chart";
    }

    @RequestMapping(value = "/login", method= RequestMethod.GET)
    public String getLogin(){
        return "login";
    }

    @RequestMapping(value = "/success")
    public String success(){
        return "success";
    }
}
