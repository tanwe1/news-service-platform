package com.sdnware.news.shiro;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListenerAdapter;

/**
 * <p>Description: </p>
 *
 * @author Tanwei
 * @version 1.0
 * @createDate 2019/05/21 9:16
 * @see com.sdnware.news.shiro
 */
@Slf4j
public class ShiroSessionListener extends SessionListenerAdapter {

    @Override
    public void onStart(Session session) {
        log.info("session create: " + session.getId());
    }

    @Override
    public void onExpiration(Session session) {
        log.info("session expiration: " + session.getId());
    }

    @Override
    public void onStop(Session session) {
        log.info("session stop: " + session.getId());
    }
}
