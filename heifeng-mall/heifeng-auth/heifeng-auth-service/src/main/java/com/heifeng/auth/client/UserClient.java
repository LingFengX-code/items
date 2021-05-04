package com.heifeng.auth.client;

import com.heifeng.user.registry.api.UserApi;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "heifeng-user-registry-service")
public interface UserClient extends UserApi {
}
