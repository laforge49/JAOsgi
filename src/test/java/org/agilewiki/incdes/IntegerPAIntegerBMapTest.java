package org.agilewiki.incdes;

import junit.framework.TestCase;

public class IntegerPAIntegerBMapTest extends TestCase {
    public void test1() throws Exception {
        FactoryLocator factoryLocator = IncDesFactories.createFactoryLocator();
        Context jaBundleContext = Context.get(factoryLocator);
        try {
            PAMap<Integer, PAInteger> m = (PAMap) factoryLocator.
                    newJid(IncDesFactories.INTEGER_PAINTEGER_BMAP);
            m.kMake(0);
            m.kMake(1);
            m.kMake(2);
            PAInteger sj0 = m.kGet(0);
            PAInteger sj1 = m.kGet(1);
            PAInteger sj2 = m.kGet(2);
            sj0.setValue(0);
            sj1.setValue(1);
            sj2.setValue(2);
            PAMap<Integer, PAInteger> n = (PAMap) m.copy(factoryLocator.getMailbox());
            PAInteger s0 = n.kGet(0);
            PAInteger s1 = n.kGet(1);
            PAInteger s2 = n.kGet(2);
            assertEquals(0, (int) s0.getValue());
            assertEquals(1, (int) s1.getValue());
            assertEquals(2, (int) s2.getValue());
        } finally {
            jaBundleContext.stop(0);
        }
    }

    public void test2() throws Exception {
        FactoryLocator factoryLocator = IncDesFactories.createFactoryLocator();
        Context jaBundleContext = Context.get(factoryLocator);
        try {
            PAMap<Integer, PAInteger> m = (PAMap) factoryLocator.
                    newJid(IncDesFactories.INTEGER_PAINTEGER_BMAP);
            int i = 0;
            while (i < 28) {
                m.kMake(i);
                PAInteger ij0 = m.kGet(i);
                ij0.setValue(i);
                i += 1;
            }
            assertEquals(28, m.size());
            i = 0;
            while (i < 28) {
                PAInteger ij = m.kGet(i);
                assertEquals(i, (int) ij.getValue());
                i += 1;
            }
        } finally {
            jaBundleContext.stop(0);
        }
    }

    public void test3() throws Exception {
        FactoryLocator factoryLocator = IncDesFactories.createFactoryLocator();
        Context jaBundleContext = Context.get(factoryLocator);
        try {
            PAMap<Integer, PAInteger> m = (PAMap) factoryLocator.
                    newJid(IncDesFactories.INTEGER_PAINTEGER_BMAP);
            int i = 0;
            while (i < 41) {
                m.kMake(i);
                PAInteger ij0 = m.kGet(i);
                ij0.setValue(i);
                i += 1;
            }
            i = 0;
            while (i < 41) {
                PAInteger ij = m.kGet(i);
                assertEquals(i, (int) ij.getValue());
                i += 1;
            }
            assertEquals(41, m.size());
        } finally {
            jaBundleContext.stop(0);
        }
    }

    public void test4() throws Exception {
        FactoryLocator factoryLocator = IncDesFactories.createFactoryLocator();
        Context jaBundleContext = Context.get(factoryLocator);
        try {
            PAMap<Integer, PAInteger> m = (PAMap) factoryLocator.
                    newJid(IncDesFactories.INTEGER_PAINTEGER_BMAP);
            int i = 0;
            while (i < 391) {
                m.kMake(i);
                PAInteger ij0 = m.kGet(i);
                ij0.setValue(i);
                i += 1;
            }
            assertEquals(391, m.size());
            i = 0;
            while (i < 391) {
                PAInteger ij = m.kGet(i);
                assertEquals(i, (int) ij.getValue());
                i += 1;
            }
        } finally {
            jaBundleContext.stop(0);
        }
    }

    public void test5() throws Exception {
        FactoryLocator factoryLocator = IncDesFactories.createFactoryLocator();
        Context jaBundleContext = Context.get(factoryLocator);
        try {
            PAMap<Integer, PAInteger> m = (PAMap) factoryLocator.
                    newJid(IncDesFactories.INTEGER_PAINTEGER_BMAP);
            int i = 0;
            while (i < 10000) {
                m.kMake(i);
                PAInteger ij0 = m.kGet(i);
                ij0.setValue(i);
                i += 1;
            }
            i = 0;
            while (i < 10000) {
                PAInteger ij = m.kGet(i);
                assertEquals(i, (int) ij.getValue());
                i += 1;
            }
            assertEquals(10000, m.size());
        } finally {
            jaBundleContext.stop(0);
        }
    }

    public void test6() throws Exception {
        FactoryLocator factoryLocator = IncDesFactories.createFactoryLocator();
        Context jaBundleContext = Context.get(factoryLocator);
        try {
            PAMap<Integer, PAInteger> m = (PAMap) factoryLocator.
                    newJid(IncDesFactories.INTEGER_PAINTEGER_BMAP);
            int i = 0;
            while (i < 10000) {
                m.kMake(i);
                PAInteger ij0 = m.kGet(i);
                ij0.setValue(i);
                i += 1;
            }
            i = 0;
            while (i < 10000) {
                m.iRemove(0);
                i += 1;
            }
            assertEquals(0, m.size());
        } finally {
            jaBundleContext.stop(0);
        }
    }

    public void test7() throws Exception {
        FactoryLocator factoryLocator = IncDesFactories.createFactoryLocator();
        Context jaBundleContext = Context.get(factoryLocator);
        try {
            PAMap<Integer, PAInteger> m = (PAMap) factoryLocator.
                    newJid(IncDesFactories.INTEGER_PAINTEGER_BMAP);
            int i = 0;
            while (i < 10000) {
                m.kMake(i);
                PAInteger ij0 = m.kGet(i);
                ij0.setValue(i);
                i += 1;
            }
            i = 0;
            while (i < 10000) {
                m.iRemove(-1);
                i += 1;
            }
            assertEquals(0, m.size());
        } finally {
            jaBundleContext.stop(0);
        }
    }

    public void test8() throws Exception {
        FactoryLocator factoryLocator = IncDesFactories.createFactoryLocator();
        Context jaBundleContext = Context.get(factoryLocator);
        try {
            PAMap<Integer, PAInteger> m = (PAMap) factoryLocator.
                    newJid(IncDesFactories.INTEGER_PAINTEGER_BMAP);
            int i = 0;
            while (i < 10000) {
                m.kMake(i);
                PAInteger ij0 = m.kGet(i);
                ij0.setValue(i);
                i += 1;
            }
            i = 0;
            while (i < 10000) {
                m.kRemove(i);
                i += 1;
            }
            assertEquals(0, m.size());
        } finally {
            jaBundleContext.stop(0);
        }
    }

    public void test9() throws Exception {
        FactoryLocator factoryLocator = IncDesFactories.createFactoryLocator();
        Context jaBundleContext = Context.get(factoryLocator);
        try {
            PAMap<Integer, PAInteger> m = (PAMap) factoryLocator.
                    newJid(IncDesFactories.INTEGER_PAINTEGER_BMAP);
            int i = 0;
            while (i < 10000) {
                m.kMake(i);
                PAInteger ij0 = m.kGet(i);
                ij0.setValue(i);
                i += 1;
            }
            i = 0;
            while (i < 10000) {
                m.kRemove(9999 - i);
                i += 1;
            }
            assertEquals(0, m.size());
        } finally {
            jaBundleContext.stop(0);
        }
    }
}
