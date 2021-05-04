package com.heifeng.goods.client;

import com.heifeng.item.api.CategoryApi;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "heifeng-item-service")
public interface CategoryClient extends CategoryApi {
}
