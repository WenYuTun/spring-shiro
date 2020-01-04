package com.mycode.springshiro.config;



import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.SessionKey;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.session.mgt.WebSessionKey;

import javax.servlet.ServletRequest;
import java.io.Serializable;

/**
 * @author wenyutun
 * @description: 自定义session管理器
 * @date: 2019/8/10
 * @version: 1.0
 */
public class CustomerWebSessionManager extends DefaultWebSessionManager {

    @Override
    protected Session retrieveSession(SessionKey sessionKey) throws UnknownSessionException {
        Serializable sessionId = getSessionId(sessionKey);
        ServletRequest request = null;

        if (sessionKey instanceof WebSessionKey) {
            request = ((WebSessionKey) sessionKey).getServletRequest();
        }

        if (request != null && sessionId != null && request.getAttribute(sessionId.toString()) != null) {
            return (Session) request.getAttribute(sessionId.toString());
        } else {
            Session session = super.retrieveSession(sessionKey);
            if (request != null && sessionId != null) {
                request.setAttribute(sessionId.toString(), session);
            }
            return session;
        }
    }

}
