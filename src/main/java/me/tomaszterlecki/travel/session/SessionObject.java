package me.tomaszterlecki.travel.session;

import me.tomaszterlecki.travel.model.User;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
public class SessionObject {
    private User user = null;

    public SessionObject() {
    }

    public SessionObject(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    public boolean isLogged() {
        return this.user != null;
    }
}
