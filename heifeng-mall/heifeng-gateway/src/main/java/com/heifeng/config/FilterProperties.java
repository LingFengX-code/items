package com.heifeng.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

//白名单
@ConfigurationProperties(prefix = "heifeng.filter")
public class FilterProperties {

    private List<String> allowPaths;

    public List<String> getAllowPaths() {
        return allowPaths;
    }

    public void setAllowPaths(List<String> allowPaths) {
        this.allowPaths = allowPaths;
    }
}
