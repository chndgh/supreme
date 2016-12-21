package com.bengi.repository;

import com.bengi.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by edward on 16/12/20.
 */
@Service("participantRepository")
public class ParticipantRepository {

    private Map<String, User> activeSessions = new ConcurrentHashMap<>();

    public void add(String sessionId, User event) {
        activeSessions.put(sessionId, event);
    }

    public User getParticipant(String sessionId) {
        return activeSessions.get(sessionId);
    }

    public void removeParticipant(String sessionId) {
        activeSessions.remove(sessionId);
    }

    public Map<String, User> getActiveSessions() {
        return activeSessions;
    }

    public void setActiveSessions(Map<String, User> activeSessions) {
        this.activeSessions = activeSessions;
    }
}
