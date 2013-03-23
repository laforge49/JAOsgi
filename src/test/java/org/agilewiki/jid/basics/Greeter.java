package org.agilewiki.jid.basics;

import org.agilewiki.jactor.ResponseProcessor;
import org.agilewiki.jactor.lpc.JLPCActor;
import org.agilewiki.jid.factory.ActorFactory;
import org.agilewiki.jid.factory.FactoryLocator;
import org.agilewiki.jid.scalar.vlens.StringJid;

public class Greeter extends StringJid implements Main {

    public static void register(FactoryLocator factoryLocator, String actorType, String greeting) throws Exception {
        factoryLocator.registerJidFactory(new GreeterFactory(actorType, greeting));
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
    public void processRequest(Proc request, ResponseProcessor rp) throws Exception {
        if (greeting == null)
            throw new IllegalStateException("greeting is null");
        System.out.println(greeting + " " + getValue());
        rp.processResponse(null);
    }
}
