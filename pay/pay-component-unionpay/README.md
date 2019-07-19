## 银联支付组件

### 参与者
@咕咕鸡

### 项目进度

目前支持产品

|产品|进度|测试|说明|
|----|----|----|----|
|在线网关支付|100%|100%||

### 组件集成

与Spring集成
```java
@Bean
public UnionpayConfig unionpayConfig() {
    return UnionpayConfig.builder()
            .version(UnionpayConstant.VERSION_5_1_0)
            .encoding(UnionpayConstant.UTF_8_ENCODING)
            // 此处省略必备配置 具体参照字段注解
            .build();
}

public SimpleCacheUnionpayProductFactory unionpayProductFactory(
        @Qualifier("unionpayConfig") UnionpayConfig unionpayConfig) {
    return new SimpleCacheUnionpayProductFactory(unionpayConfig);
}
```

服务调用
```java
@Autowired
IUnionPayProductFactory unionpayProductFactory;

public void test() {
    // 具体使用调用哪种产品，见方法注释
    unionpayProductFactory.produce(UnionpayGatewayService.class).backConsume(...)
}
```