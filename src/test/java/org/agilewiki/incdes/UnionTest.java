package org.agilewiki.incdes;

import junit.framework.TestCase;
import org.agilewiki.pactor.Mailbox;

public class UnionTest extends TestCase {
    public void test() throws Exception {
        FactoryLocator factoryLocator = IncDesFactories.createFactoryLocator();
        try {
            IncDesFactories.registerUnionFactory(factoryLocator, "siUnion", IncDesFactories.PASTRING, "siUnion");
            Union siu1 = (Union) factoryLocator.newJid("siUnion");
            assertNull(siu1.getValue());
            Mailbox mailbox = factoryLocator.getMailboxFactory().createMailbox();
            Union siu2 = (Union) siu1.copy(mailbox);
            assertNull(siu2.getValue());
            siu2.setValue(IncDesFactories.PASTRING);
            PAString sj2 = (PAString) siu2.getValue();
            assertNotNull(sj2);
            Union siu3 = (Union) siu2.copy(mailbox);
            PAString sj3 = (PAString) siu3.getValue();
            assertNotNull(sj3);
        } finally {
            factoryLocator.close();
        }
    }
}
