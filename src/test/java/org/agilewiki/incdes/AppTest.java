package org.agilewiki.incdes;

import junit.framework.TestCase;

public class AppTest extends TestCase {
    public void test1() throws Exception {
        FactoryLocator factoryLocator = IncDesFactories.createFactoryLocator();
        try {
            User.register(factoryLocator);
            User user1 = (User) factoryLocator.newJid("user");
            user1.PAName().setValue("Joe");
            user1.PAAge().setValue(42);
            user1.PALocation().setValue("Boston");
            User user2 = (User) user1.getDurable().copy(null);
            assertEquals("Joe", user2.PAName().getValue());
            assertEquals(42, (int) user2.PAAge().getValue());
            assertEquals("Boston", user2.PALocation().getValue());
        } finally {
            factoryLocator.close();
        }
    }
}

class User extends AppBase {
    static void register(final FactoryLocator _factoryLocator) throws Exception {
        _factoryLocator.registerJidFactory(new AppFactory("user",
                IncDesFactories.PASTRING, IncDesFactories.PAINTEGER, IncDesFactories.PASTRING) {
            @Override
            protected App instantiateActor() {
                return new User();
            }
        });
    }

    static int NAME = 0;
    static int AGE = 1;
    static int LOCATION = 2;

    PAString PAName() throws Exception {
        return (PAString) getDurable()._iGet(NAME);
    }

    PAInteger PAAge() throws Exception {
        return (PAInteger) getDurable()._iGet(AGE);
    }

    PAString PALocation() throws Exception {
        return (PAString) getDurable()._iGet(LOCATION);
    }
}