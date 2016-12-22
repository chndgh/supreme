package com.bengi.controller;

import com.bengi.model.User;
import com.bengi.repository.ParticipantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * Created by edward on 16/12/20.
 */

public class LoginController {
    @Autowired
    private SimpMessagingTemplate messagingTemplate;
    @Autowired
    private ParticipantRepository participantRepository;

    private static final String LOGIN = "/app/chat.login";
    private static final String LOGOUT = "/app/chat.logout";

    @RequestMapping(value = "login", method= RequestMethod.POST)
    public String login(HttpServletRequest httpRequest, User user) throws ServletException {
        user.setTime(new Date());
        httpRequest.getSession().setAttribute("user", user);
        messagingTemplate.convertAndSend(LOGIN, user);
        if(participantRepository.getActiveSessions().
                containsKey(httpRequest.getSession().getId())){
            messagingTemplate.convertAndSend(LOGOUT,
                    participantRepository.getActiveSessions().
                            get(httpRequest.getSession().getId()));
        }
        participantRepository.add(httpRequest.getSession().getId(), user);
        return "redirect:/chat";
    }
}
