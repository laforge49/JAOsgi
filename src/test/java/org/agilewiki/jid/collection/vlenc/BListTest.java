package org.agilewiki.jid.collection.vlenc;

import junit.framework.TestCase;
import org.agilewiki.incdes.impl.collection.vlenc.BList;
import org.agilewiki.jid.factory.JAFactoryLocator;
import org.agilewiki.jid.factory.JidFactories;
import org.agilewiki.jid.jaosgi.JABundleContext;
import org.agilewiki.incdes.impl.scalar.flens.PAIntegerImpl;
import org.agilewiki.incdes.impl.scalar.vlens.PAStringImpl;

public class BListTest extends TestCase {
    public void test1() throws Exception {
        JAFactoryLocator factoryLocator = JidFactories.createNoOsgiFactoryLocator(1);
        JABundleContext jaBundleContext = JABundleContext.get(factoryLocator);
        try {
            BList stringList1 = (BList) factoryLocator.newJid(JidFactories.STRING_BLIST_JID_TYPE);
            stringList1.iAdd(0);
            stringList1.iAdd(1);
            stringList1.iAdd(2);
            PAStringImpl sj0 = (PAStringImpl) stringList1.iGet(0);
            PAStringImpl sj1 = (PAStringImpl) stringList1.iGet(1);
            PAStringImpl sj2 = (PAStringImpl) stringList1.iGet(2);
            sj0.setValue("a");
            sj1.setValue("b");
            sj2.setValue("c");
            BList stringList2 = (BList) stringList1.copyJID(factoryLocator.getMailbox());
            PAStringImpl s0 = (PAStringImpl) stringList2.iGet(0);
            PAStringImpl s1 = (PAStringImpl) stringList2.iGet(1);
            PAStringImpl s2 = (PAStringImpl) stringList2.iGet(2);
            assertEquals("a", s0.getValue());
            assertEquals("b", s1.getValue());
            assertEquals("c", s2.getValue());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jaBundleContext.stop(0);
        }
    }

    public void test2() throws Exception {
        JAFactoryLocator factoryLocator = JidFactories.createNoOsgiFactoryLocator(1);
        JABundleContext jaBundleContext = JABundleContext.get(factoryLocator);
        try {
            BList intList1 = (BList) factoryLocator.newJid(JidFactories.INTEGER_BLIST_JID_TYPE);
            int i = 0;
            while (i < 28) {
                intList1.iAdd(i);
                PAIntegerImpl ij0 = (PAIntegerImpl) intList1.iGet(i);
                ij0.setValue(i);
                i += 1;
            }
            i = 0;
            while (i < 28) {
                PAIntegerImpl ij = (PAIntegerImpl) intList1.iGet(i);
                assertEquals(i, (int) ij.getValue());
                i += 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jaBundleContext.stop(0);
        }
    }

    public void test3() throws Exception {
        JAFactoryLocator factoryLocator = JidFactories.createNoOsgiFactoryLocator(1);
        JABundleContext jaBundleContext = JABundleContext.get(factoryLocator);
        try {
            BList intList1 = (BList) factoryLocator.newJid(JidFactories.INTEGER_BLIST_JID_TYPE);
            int i = 0;
            while (i < 41) {
                intList1.iAdd(-1);
                PAIntegerImpl ij0 = (PAIntegerImpl) intList1.iGet(-1);
                ij0.setValue(i);
                i += 1;
            }
            i = 0;
            while (i < 41) {
                PAIntegerImpl ij = (PAIntegerImpl) intList1.iGet(i);
                assertEquals(i, (int) ij.getValue());
                i += 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jaBundleContext.stop(0);
        }
    }

    public void test4() throws Exception {
        JAFactoryLocator factoryLocator = JidFactories.createNoOsgiFactoryLocator(1);
        JABundleContext jaBundleContext = JABundleContext.get(factoryLocator);
        try {
            BList intList1 = (BList) factoryLocator.newJid(JidFactories.INTEGER_BLIST_JID_TYPE);
            int i = 0;
            while (i < 391) {
                intList1.iAdd(-1);
                PAIntegerImpl ij0 = (PAIntegerImpl) intList1.iGet(-1);
                ij0.setValue(i);
                i += 1;
            }
            i = 0;
            while (i < 391) {
                PAIntegerImpl ij = (PAIntegerImpl) intList1.iGet(i);
                assertEquals(i, (int) ij.getValue());
                i += 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jaBundleContext.stop(0);
        }
    }

    public void test5() throws Exception {
        JAFactoryLocator factoryLocator = JidFactories.createNoOsgiFactoryLocator(1);
        JABundleContext jaBundleContext = JABundleContext.get(factoryLocator);
        try {
            BList intList1 = (BList) factoryLocator.newJid(JidFactories.INTEGER_BLIST_JID_TYPE);
            int i = 0;
            while (i < 10000) {
                intList1.iAdd(-1);
                PAIntegerImpl ij0 = (PAIntegerImpl) intList1.iGet(-1);
                ij0.setValue(i);
                i += 1;
            }
            i = 0;
            while (i < 10000) {
                PAIntegerImpl ij = (PAIntegerImpl) intList1.iGet(i);
                assertEquals(i, (int) ij.getValue());
                i += 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jaBundleContext.stop(0);
        }
    }

    public void test6() throws Exception {
        JAFactoryLocator factoryLocator = JidFactories.createNoOsgiFactoryLocator(1);
        JABundleContext jaBundleContext = JABundleContext.get(factoryLocator);
        try {
            BList intList1 = (BList) factoryLocator.newJid(JidFactories.INTEGER_BLIST_JID_TYPE);
            int i = 0;
            while (i < 10000) {
                intList1.iAdd(-1);
                PAIntegerImpl ij0 = (PAIntegerImpl) intList1.iGet(-1);
                ij0.setValue(i);
                i += 1;
            }
            i = 0;
            while (i < 10000) {
                intList1.iRemove(-1);
                i += 1;
            }
            assertTrue(intList1.isLeaf());
            assertEquals(0, intList1.size());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jaBundleContext.stop(0);
        }
    }
}
