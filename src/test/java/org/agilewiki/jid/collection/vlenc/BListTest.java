package org.agilewiki.jid.collection.vlenc;

import junit.framework.TestCase;

public class BListTest extends TestCase {
    public void test1() throws Exception {
    /*
        JAFactoryLocator factoryLocator = JidFactories.createNoOsgiFactoryLocator(1);
        JABundleContext jaBundleContext = JABundleContext.getJABundleContext(factoryLocator);
        try {
            BListJid stringList1 = (BListJid) factoryLocator.newActor(JidFactories.STRING_BLIST_JID_TYPE);
            stringList1.iAdd(0);
            stringList1.iAdd(1);
            stringList1.iAdd(2);
            StringJid sj0 = (StringJid) stringList1.iGet(0);
            StringJid sj1 = (StringJid) stringList1.iGet(1);
            StringJid sj2 = (StringJid) stringList1.iGet(2);
            sj0.setValue("a");
            sj1.setValue("b");
            sj2.setValue("c");
            BListJid stringList2 = (BListJid) stringList1.copyJID(factoryLocator.getMailbox());
            StringJid s0 = (StringJid) stringList2.iGet(0);
            StringJid s1 = (StringJid) stringList2.iGet(1);
            StringJid s2 = (StringJid) stringList2.iGet(2);
            assertEquals("a", s0.getValue());
            assertEquals("b", s1.getValue());
            assertEquals("c", s2.getValue());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jaBundleContext.stop(0);
        }
        */
    }

    /*
    public void test2() throws Exception {
        JAFactoryLocator factoryLocator = JidFactories.createNoOsgiFactoryLocator(1);
        JABundleContext jaBundleContext = JABundleContext.getJABundleContext(factoryLocator);
        try {
            BListJid intList1 = (BListJid) factoryLocator.newActor(JidFactories.INTEGER_BLIST_JID_TYPE);
            int i = 0;
            while (i < 28) {
                intList1.iAdd(i);
                IntegerJid ij0 = (IntegerJid) intList1.iGet(i);
                ij0.setValue(i);
                i += 1;
            }
            i = 0;
            while (i < 28) {
                IntegerJid ij = (IntegerJid) intList1.iGet(i);
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
        JABundleContext jaBundleContext = JABundleContext.getJABundleContext(factoryLocator);
        try {
            BListJid intList1 = (BListJid) factoryLocator.newActor(JidFactories.INTEGER_BLIST_JID_TYPE);
            int i = 0;
            while (i < 41) {
                intList1.iAdd(-1);
                IntegerJid ij0 = (IntegerJid) intList1.iGet(-1);
                ij0.setValue(i);
                i += 1;
            }
            i = 0;
            while (i < 41) {
                IntegerJid ij = (IntegerJid) intList1.iGet(i);
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
        JABundleContext jaBundleContext = JABundleContext.getJABundleContext(factoryLocator);
        try {
            BListJid intList1 = (BListJid) factoryLocator.newActor(JidFactories.INTEGER_BLIST_JID_TYPE);
            int i = 0;
            while (i < 391) {
                intList1.iAdd(-1);
                IntegerJid ij0 = (IntegerJid) intList1.iGet(-1);
                ij0.setValue(i);
                i += 1;
            }
            i = 0;
            while (i < 391) {
                IntegerJid ij = (IntegerJid) intList1.iGet(i);
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
        JABundleContext jaBundleContext = JABundleContext.getJABundleContext(factoryLocator);
        try {
            BListJid intList1 = (BListJid) factoryLocator.newActor(JidFactories.INTEGER_BLIST_JID_TYPE);
            int i = 0;
            while (i < 10000) {
                intList1.iAdd(-1);
                IntegerJid ij0 = (IntegerJid) intList1.iGet(-1);
                ij0.setValue(i);
                i += 1;
            }
            i = 0;
            while (i < 10000) {
                IntegerJid ij = (IntegerJid) intList1.iGet(i);
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
        JABundleContext jaBundleContext = JABundleContext.getJABundleContext(factoryLocator);
        try {
            BListJid intList1 = (BListJid) factoryLocator.newActor(JidFactories.INTEGER_BLIST_JID_TYPE);
            int i = 0;
            while (i < 10000) {
                intList1.iAdd(-1);
                IntegerJid ij0 = (IntegerJid) intList1.iGet(-1);
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
    */
}
