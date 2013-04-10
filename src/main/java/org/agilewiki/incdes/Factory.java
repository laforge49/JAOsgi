package org.agilewiki.incdes;

import org.agilewiki.pactor.Mailbox;
import org.agilewiki.pautil.Ancestor;

public interface Factory {
    String getType();

    IncDes newActor(Mailbox mailbox, Ancestor parent)
            throws Exception;
}
