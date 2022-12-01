package aaa.tavern.service.utils;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.ApplicationEvent;

import aaa.tavern.entity.Player;

public class OnRegistrationCompleteEvent extends ApplicationEvent {
    private String appUrl;
    private Locale locale;
    private Player player;

    public OnRegistrationCompleteEvent(Player player, HttpServletRequest request) {
        super(player);
        
        this.player = player;
        this.locale = request.getLocale();
        this.appUrl = request.getContextPath();
    }

    public String getAppUrl() {
        return appUrl;
    }

    public void setAppUrl(String appUrl) {
        this.appUrl = appUrl;
    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
