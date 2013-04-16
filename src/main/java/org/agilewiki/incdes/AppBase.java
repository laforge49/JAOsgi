package org.agilewiki.incdes;

import org.agilewiki.pactor.Actor;
import org.agilewiki.pactor.Mailbox;
import org.agilewiki.pautil.Ancestor;

public class AppBase implements PASerializable {
    private Durable durable;

    public void setDurable(final Durable _durable) {
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
    public boolean sameMailbox(final Actor _other) {
        return durable.sameMailbox(_other);
    }

    @Override
    public Ancestor getParent() {
        return durable.getParent();
    }
}
