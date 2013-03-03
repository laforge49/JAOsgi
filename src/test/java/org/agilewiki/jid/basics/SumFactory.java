package org.agilewiki.jid.basics;

import org.agilewiki.jaosgi.JidFactories;
import org.agilewiki.jid.collection.vlenc.ListJidFactory;

public class SumFactory extends ListJidFactory {
    public SumFactory(String actorType) {
        super(actorType, JidFactories.INTEGER_JID_TYPE);
    }

    @Override
    protected Sum instantiateActor()
            throws Exception {
        Sum sum = new Sum();
        return sum;
    }
}
