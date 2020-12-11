package com.ibox.state.service.zk.client.impl;

import com.ibox.state.service.zk.StateListener;
import com.ibox.state.service.zk.client.AbstractZookeeperClient;
import org.I0Itec.zkclient.IZkStateListener;
import org.I0Itec.zkclient.ZkClient;
import org.apache.zookeeper.Watcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ZkClientZookeeperClient extends AbstractZookeeperClient {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final ZkClient client;

    private volatile Watcher.Event.KeeperState state = Watcher.Event.KeeperState.SyncConnected;

    public ZkClientZookeeperClient(String hosts, int sessionTimeout, int connTimeout) {
        super(hosts);
        /**
         *  hosts 服务列表
         * sessionTimeout 心跳检测周期
         * connTimeout 连接超时时间
         */
        client = new ZkClient(hosts, sessionTimeout, connTimeout);

        client.subscribeStateChanges(new IZkStateListener() {
            public void handleStateChanged(Watcher.Event.KeeperState keeperState) throws Exception {
                ZkClientZookeeperClient.this.state = keeperState;
                if (state == Watcher.Event.KeeperState.Disconnected) {
                    stateChanged(StateListener.DISCONNECTED);
                } else if (state == Watcher.Event.KeeperState.SyncConnected) {
                    stateChanged(StateListener.CONNECTED);
                }
            }
            public void handleNewSession() throws Exception {
                stateChanged(StateListener.RECONNECTED);
            }

            public void handleSessionEstablishmentError(Throwable throwable) throws Exception {

            }
        });
    }



}
