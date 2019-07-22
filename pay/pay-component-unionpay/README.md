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

public void test() {
    // 具体使用哪种产品，见方法注释
    IUnionpayQRCodeClient qrCodeClient = (IUnionpayGatewayClient) 
    unionPayProductFactory.produce(UnionpayProductEnum.GATEWAY.name());
}
```


**测试用例：**  
测试类中详细的测试了每个产品的方法，集成时可参考，各字段含义可直接查询官方文档

### 注意事项
- [如何申请银联注册号？](../../doc/支付/银联-测试账号申请.md)

- 如何集成产品测试权限？  
测试api返回无权限等错误信息时，测试请注意，应关注当前测试的是那款产品，然后前往【商户测试中心】-【我的产品】-【未测试】，
在列表中选择需要的测试产品，点击【开始测试】，约等待20分钟后，再次尝试即可授权