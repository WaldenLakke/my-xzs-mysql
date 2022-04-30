package com.yza.xzs.configuration.spring.security;

import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

/**
 * 登录参数的序列化
 */
public class RestLoginAuthenticationFilter extends AbstractAuthenticationProcessingFilter {
    private static final Logger logger= LoggerFactory.getLogger(RestLoginAuthenticationFilter.class);

    public RestLoginAuthenticationFilter() {
        super(new AntPathRequestMatcher("/api/user/login", "POST"));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws AuthenticationException, IOException, ServletException {
        UsernamePasswordAuthenticationToken token;
        try(InputStream is= httpServletRequest.getInputStream()){
            AuthenticationBean authenticationBean = JsonUtil.toJsonObject(is, AuthenticationBean.class);
            httpServletRequest.setAttribute(TokenBasedRememberMeServices.DEFAULT_PARAMETER, authenticationBean.isRemember());
            token = new UsernamePasswordAuthenticationToken(authenticationBean.getUserName(), authenticationBean.getPassword());
        }
        catch (IOException e){
            logger.error(e.getMessage(),e);
            token=new UsernamePasswordAuthenticationToken("","");
        }
        setDetails(httpServletRequest,token);
        return this.getAuthenticationManager().authenticate(token);
    }

    public void setDetails(HttpServletRequest httpServletRequest,UsernamePasswordAuthenticationToken token){
        token.setDetails(authenticationDetailsSource.buildDetails(httpServletRequest));
    }

    /**
     * Sets user details service.
     *
     * @param userDetailsService the user details service
     */
    void setUserDetailsService(UserDetailsService userDetailsService) {
        RestTokenBasedRememberMeServices tokenBasedRememberMeServices = new RestTokenBasedRememberMeServices(CookieConfig.getName(), userDetailsService);
        tokenBasedRememberMeServices.setTokenValiditySeconds(CookieConfig.getInterval());
        setRememberMeServices(tokenBasedRememberMeServices);
    }
}
