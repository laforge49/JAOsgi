package org.agilewiki.incdes;

import org.agilewiki.pactor.Mailbox;
import org.agilewiki.pautil.Ancestor;
import org.agilewiki.pautil.Named;

public interface Factory extends Named {
    IncDes newActor(final Mailbox _mailbox, final Ancestor _parent)
            throws Exception;
}
