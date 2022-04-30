package com.yza.xzs.configuration.spring.property;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * 系统配置类，包含密钥、可以忽略安全直接访问的url
 */
@ConfigurationProperties(prefix = "system")
public class SystemConfig {
    private PasswordKeyConfig pwdKey;
    private List<String> securityIgnoreUrls;


    public PasswordKeyConfig getPwdKey() {
        return pwdKey;
    }

    public void setPwdKey(PasswordKeyConfig pwdKey) {
        this.pwdKey = pwdKey;
    }

    public List<String> getSecurityIgnoreUrls() {
        return securityIgnoreUrls;
    }

    public void setSecurityIgnoreUrls(List<String> securityIgnoreUrls) {
        this.securityIgnoreUrls = securityIgnoreUrls;
    }
}
}
