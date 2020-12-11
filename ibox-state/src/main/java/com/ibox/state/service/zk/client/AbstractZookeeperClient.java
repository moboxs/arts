package com.ibox.state.service.zk.client;

import com.ibox.state.service.zk.StateListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

public class AbstractZookeeperClient<T> implements ZookeeperClient{

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final Set<StateListener> stateListeners = new CopyOnWriteArraySet<StateListener>();


    /**
     * zk hosts
     */
    private final String hosts;

    public AbstractZookeeperClient(String hosts) {
        this.hosts = hosts;
    }

    protected void stateChanged(int state) {
        logger.debug("state changed : " + state);
        for (StateListener sessionListener : getStateListeners()) {
            sessionListener.stateChanged(state);
        }
    }

    public Set<StateListener> getStateListeners() {
        return stateListeners;
    }


}
