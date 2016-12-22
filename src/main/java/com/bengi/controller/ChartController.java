package com.bengi.controller;

import com.bengi.model.User;
import com.bengi.repository.ParticipantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.nio.file.AccessDeniedException;
import java.util.Collection;

/**
 * Created by edward on 16/12/20.
 */


public class ChartController {
    @Autowired
    ParticipantRepository participantRepository;

    @RequestMapping(value = "/chart", method = RequestMethod.GET)
    public String chartPage(HttpServletRequest request, Model model) throws AccessDeniedException {
        if (request.getSession().getAttribute("user") == null){
            throw new AccessDeniedException("login please");
        }
        User user = (User)request.getSession().getAttribute("user");
        model.addAttribute("username", user.getUsername());
        return "chart";
    }

    @SubscribeMapping("/chat.participants")
    public Collection<User> retrieveParticipants() {
        return participantRepository.getActiveSessions().values();
    }
}
