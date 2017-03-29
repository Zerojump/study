package com.cmy.strategy;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.pam.AbstractAuthenticationStrategy;
import org.apache.shiro.util.CollectionUtils;

/**
 * @author chenmingyi
 * @version 1.0
 * @ DATE:3/15/2017
 */
public class AtLeastTwoSuccessStrategy extends AbstractAuthenticationStrategy {
    @Override
    public AuthenticationInfo afterAllAttempts(AuthenticationToken token, AuthenticationInfo aggregate) throws AuthenticationException {
        if (aggregate != null && !CollectionUtils.isEmpty(aggregate.getPrincipals()) && aggregate.getPrincipals().getRealmNames().size() < 2) {
            throw new AuthenticationException("AtLeastTwoSuccess");
        }
        return aggregate;
    }
}
