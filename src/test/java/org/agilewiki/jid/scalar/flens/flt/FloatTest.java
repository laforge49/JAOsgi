package org.agilewiki.jid.scalar.flens.flt;

import junit.framework.TestCase;
import org.agilewiki.incdes.PAFactories;
import org.agilewiki.incdes.PABundleContext;
import org.agilewiki.incdes.impl.scalar.flens.PAFloatImpl;
import org.agilewiki.incdes.impl.scalar.vlens.BoxImpl;
import org.agilewiki.jactor.old.Actor;
import org.agilewiki.jactor.old.JAFuture;
import org.agilewiki.jid.CopyJID;
import org.agilewiki.jid.GetSerializedLength;
import org.agilewiki.jid.ResolvePathname;
import org.agilewiki.incdes.impl.factory.FactoryLocatorImpl;
import org.agilewiki.jid.scalar.vlens.actor.SetActor;

public class FloatTest extends TestCase {
    public void test() throws Exception {
        FactoryLocatorImpl factoryLocator = PAFactories.createFactoryLocator(1);
        PABundleContext jaBundleContext = PABundleContext.get(factoryLocator);
        try {
            JAFuture future = new JAFuture();
            PAFloatImpl float1 = (PAFloatImpl) factoryLocator.newJid(PAFactories.FLOAT_JID_TYPE);
            PAFloatImpl float2 = (PAFloatImpl) (new CopyJID()).send(future, float1);
            (new SetFloat(1.0f)).send(future, float2);
            Actor float3 = (new CopyJID()).send(future, float2);

            int sl = GetSerializedLength.req.send(future, float1);
            assertEquals(4, sl);
            sl = GetSerializedLength.req.send(future, float2);
            assertEquals(4, sl);
            sl = GetSerializedLength.req.send(future, float3);
            assertEquals(4, sl);

            float v = GetFloat.req.send(future, float1);
            assertEquals(0.f, v);
            v = GetFloat.req.send(future, float2);
            assertEquals(1.f, v);
            v = GetFloat.req.send(future, float3);
            assertEquals(1.f, v);

            BoxImpl jidJid1 = BoxImpl.create(factoryLocator, null, null);
            SetActor sjvf = new SetActor(PAFactories.FLOAT_JID_TYPE);
            sjvf.send(future, jidJid1);
            PAFloatImpl rpa = (PAFloatImpl) (new ResolvePathname("0")).send(future, jidJid1);
            v = GetFloat.req.send(future, rpa);
            assertEquals(0.f, v);
            (new SetFloat(-1.0f)).send(future, rpa);
            rpa = (PAFloatImpl) (new ResolvePathname("0")).send(future, jidJid1);
            v = GetFloat.req.send(future, rpa);
            assertEquals(-1.f, v);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jaBundleContext.stop(0);
        }
    }
}
