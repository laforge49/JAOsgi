package org.agilewiki.jid.factory.timing;

import org.agilewiki.jactor.lpc.JLPCActor;
import org.agilewiki.jid.factory.ActorFactory;

public class AFactory extends ActorFactory {

    public AFactory(String actorType) {
        super(actorType);
    }

    @Override
    protected JLPCActor instantiateActor() throws Exception {
        return new A();
    }
}
