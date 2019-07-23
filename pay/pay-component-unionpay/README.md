## 银联支付组件

### 项目进度

目前支持产品

|产品|进度|测试|说明|
|----|----|----|----|
|在线网关支付|100%|100%||
|二维码支付|100%|||

### 组件集成

**快速集成：**
```java
UnionpayConfig config = new UnionpayConfig();

new CacheUnionpayProductFactory(config).produce(UnionpayProductEnum.GATEWAY.name())
```

- 工厂类建议是**单例**，减少不必要的资源浪费
- `CacheUnionpayProductFactory`默认有**缓存功能**，可通过设置`classCacheEnabled`值关闭，当然依赖方也可自行扩展`IUnionPayProductFactory`

**与Spring集成（此处展示java config版本配置，xml参考集成即可）**
```java
@Bean
public UnionpayConfig unionpayConfig() {
    return UnionpayConfig.builder()
            .version(UnionpayConstant.VERSION_5_1_0)
            .encoding(UnionpayConstant.UTF_8_ENCODING)
            // 此处省略必备配置 具体参照字段注解
            .build();
}

public CacheUnionpayProductFactory unionpayProductFactory(
        @Qualifier("unionpayConfig") UnionpayConfig unionpayConfig) {
    return new CacheUnionpayProductFactory(unionpayConfig);
}
```

服务调用
```java
@Autowired
IUnionPayProductFactory unionpayProductFactory;

public void gatewayConsume() {
    // 具体使用哪种产品，见方法注释
    IUnionpayGatewayClient gatewayClient = (IUnionpayGatewayClient) 
        unionPayProductFactory.produce(UnionpayProductEnum.GATEWAY.name());
    
    // 具体字段规则，参考各个model字段定义及api规则
    GatewayConsumeReq gatewayConsumeReq = GatewayConsumeReq.of(config)
                    .orderId("jfw123456799")
                    .txnTime("20190722100412")
                    .txnAmt("10000000")
                    .frontUrl("http://www.lol.com/gateway/frontback")
                    .backUrl("http://www.lol.com/gateway/callback")
                    .payTimeout(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date().getTime() + 15 * 60 * 1000))
                    .build();
    
    FormReq formReq = gatewayClient.consume(gatewayConsumeReq);
}
```


**测试用例：**  

**集成测试前，请详细阅读[注意事项](#注意事项)** 

 `com.github.lol.pay.component.unionpay.product.*.impl`路径下详细的测试了每个产品的方法，
集成时可参考，各字段含义可直接参考注释或查询官方文档

其中，在线网关支付-测试类`UnionpayGatewayServiceTest`**注解最为详细，可参照此处集成测试**，其它的产品大同小异

### 注意事项
- [如何申请银联注册号？](../../doc/支付/银联-测试账号申请.md)

- 如何集成产品测试权限？  
测试api返回无权限等错误信息时，测试请注意，应关注当前测试的是那款产品，然后前往【商户测试中心】-【我的产品】-【未测试】，
在列表中选择需要的测试产品，点击【开始测试】，约等待20分钟后，再次尝试即可授权