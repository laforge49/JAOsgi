package org.agilewiki.incdes;

import org.agilewiki.pactor.Actor;
import org.agilewiki.pactor.Mailbox;
import org.agilewiki.pautil.Ancestor;

public class AppBase implements App {
    private Durable durable;

    @Override
    public void setDurable(final Durable _durable) {
        if (durable != null)
            throw new IllegalStateException("durable already set");
        durable = _durable;
    }

    @Override
    public Durable getDurable() {
        return durable;
    }

    @Override
    public Mailbox getMailbox() {
        return durable.getMailbox();
    }

    @Override
    public Ancestor getParent() {
        return durable.getParent();
    }
}
