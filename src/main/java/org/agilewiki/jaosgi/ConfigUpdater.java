package org.agilewiki.jaosgi;

import org.agilewiki.jactor.JAMailboxFactory;
import org.agilewiki.jactor.MailboxFactory;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.cm.ConfigurationException;
import org.osgi.service.cm.ManagedService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;

public final class ConfigUpdater implements ManagedService {
    final Logger logger = LoggerFactory.getLogger(ConfigUpdater.class);
    private List<ServiceRegistration> registrations;
    private int threadCount = 0;
    private BundleContext context;

    public ConfigUpdater(List<ServiceRegistration> registrations, BundleContext context) {
        this.registrations = registrations;
        this.context = context;
    }

    @Override
    public void updated(Dictionary config) throws ConfigurationException {
        if (config == null)
            return;
        if (threadCount > 0) //no changes allowed
            return;
        String tc = (String) config.get("threadCount");
        try {
            threadCount = Integer.valueOf(tc);
        } catch(Exception ex) {
            throw new ConfigurationException("threadCount", "not an int: "+tc, ex);
        }
        logger.info("threadCount: " + threadCount);
        MailboxFactory mailboxFactory = JAMailboxFactory.newMailboxFactory(threadCount);
        registrations.add(context.registerService(
                MailboxFactory.class.getName(),
                mailboxFactory,
                new Hashtable<String, Object>()));
        //todo
    }
}
