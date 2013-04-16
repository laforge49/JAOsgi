package org.agilewiki.incdes;

import org.agilewiki.pactor.Actor;
import org.agilewiki.pactor.Mailbox;
import org.agilewiki.pautil.Ancestor;

public class SerializableBase implements PASerializable {
    private AppBase appBase;

    public void setAppBase(final AppBase _appBase) {
        appBase = _appBase;
    }

    @Override
    public AppBase getDurable() {
        return appBase;
    }

    @Override
    public Mailbox getMailbox() {
        return appBase.getMailbox();
    }

    @Override
    public boolean sameMailbox(final Actor _other) {
        return appBase.sameMailbox(_other);
    }

    @Override
    public Ancestor getParent() {
        return appBase.getParent();
    }
}
