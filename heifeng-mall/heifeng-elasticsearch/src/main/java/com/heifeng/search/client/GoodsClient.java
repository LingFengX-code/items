package com.heifeng.search.client;

import com.heifeng.item.api.GoodsApi;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "heifeng-item-service")
public interface GoodsClient extends GoodsApi {}
