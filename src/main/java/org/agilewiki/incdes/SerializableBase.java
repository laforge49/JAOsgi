package org.agilewiki.incdes;

import org.agilewiki.incdes.impl.DurableImpl;
import org.agilewiki.pactor.Actor;
import org.agilewiki.pactor.Mailbox;
import org.agilewiki.pautil.Ancestor;

public class SerializableBase implements PASerializable {
    private DurableImpl durableImpl;

    public void setDurable(final DurableImpl _durableImpl) {
        durableImpl = _durableImpl;
    }

    @Override
    public DurableImpl getDurable() {
        return durableImpl;
    }

    @Override
    public Mailbox getMailbox() {
        return durableImpl.getMailbox();
    }

    @Override
    public boolean sameMailbox(final Actor _other) {
        return durableImpl.sameMailbox(_other);
    }

    @Override
    public Ancestor getParent() {
        return durableImpl.getParent();
    }
}
