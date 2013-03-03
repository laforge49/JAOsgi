package org.agilewiki.jid.basics;

import org.agilewiki.jactor.RP;
import org.agilewiki.jactor.factory.ActorFactory;
import org.agilewiki.jactor.lpc.JLPCActor;
import org.agilewiki.jaosgi.FactoryLocator;
import org.agilewiki.jid.scalar.vlens.string.StringJid;

public class Greeter extends StringJid implements Main {

    public static void register(FactoryLocator factoryLocator, String greeting) throws Exception {
        factoryLocator.registerActorFactory(new GreeterFactory("greeter", greeting));
    }

    private static class GreeterFactory extends ActorFactory {
        private String greeting;

        public GreeterFactory(String actorType, String greeting) {
            super(actorType);
            this.greeting = greeting;
        }

        @Override
        protected JLPCActor instantiateActor() throws Exception {
            Greeter greeter = new Greeter();
            greeter.greeting = greeting;
            return greeter;
        }
    }
    public String greeting;

    @Override
    public void processRequest(Proc request, RP rp) throws Exception {
        if (greeting == null)
            throw new IllegalStateException("greeting is null");
        System.out.println(greeting + " " + getValue());
        rp.processResponse(null);
    }
}
