package io.github.sinri.drydock.lesson.navy.fighter.phc;

import io.github.sinri.keel.web.http.prehandler.PreHandlerChain;

public class OpenPHC extends PreHandlerChain {
    public OpenPHC() {
        super();

        this.authenticationHandlers.add(OpenAuthenticationHandler.getInstance());
    }
}
