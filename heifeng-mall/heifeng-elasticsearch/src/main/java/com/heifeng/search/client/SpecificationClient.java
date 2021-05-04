package com.heifeng.search.client;

import com.heifeng.item.api.SpecificationApi;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "heifeng-item-service")
public interface SpecificationClient extends SpecificationApi {
}
