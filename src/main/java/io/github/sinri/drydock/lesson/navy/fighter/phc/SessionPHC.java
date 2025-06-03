package io.github.sinri.drydock.lesson.navy.fighter.phc;

import io.github.sinri.keel.web.http.prehandler.PreHandlerChain;

public class SessionPHC extends PreHandlerChain {
    public SessionPHC() {
        super();
        this.authenticationHandlers.add(SessionAuthenticationHandler.getInstance());
    }
}
