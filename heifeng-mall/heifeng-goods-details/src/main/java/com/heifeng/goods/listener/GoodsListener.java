package com.heifeng.goods.listener;

import com.heifeng.goods.service.GoodsHtmlService;
import com.heifeng.goods.service.GoodsService;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GoodsListener {
    @Autowired
    private GoodsService GoodsService;
    @Autowired
    private GoodsHtmlService goodsHtmlService;

    /**
     * 处理insert和update的消息
     *
     * @param id
     * @throws Exception
     */
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "HEIFENG.ITEM.SAVE.QUEUE", durable = "true"),
            exchange = @Exchange(
                    value = "HEIFENG.ITEM.EXCHANGE",
                    ignoreDeclarationExceptions = "true",
                    type = ExchangeTypes.TOPIC),
            key = {"item.insert", "item.update"}))
    public void save(Long id) throws Exception {
        if (id == null) {
            return;
        }
        // 创建或更新索引
        this.goodsHtmlService.createHtml(id);
    }
}
