package com.heifeng.goods.client;

import com.heifeng.item.api.BrandApi;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "heifeng-item-service")
public interface BrandClient extends BrandApi {
}
