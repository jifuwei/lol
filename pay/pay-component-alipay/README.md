## 支付宝支付组件

### 项目进度

目前支持产品

|产品|进度|测试|说明|
|----|----|----|----|
|当面付|100%|||
|APP支付|100%|||
|手机网站支付|100%|||
|电脑网站支付|100%|||

### 组件集成

**0.依赖**
```xml
<dependencies>
    <dependency>
        <groupId>com.github</groupId>
        <artifactId>pay-component-alipay</artifactId>
        <version>${latest.version}</version>
    </dependency>
</dependencies>
```

**1.快速集成：**
```java
public class App {
    public static void main(String[] args) {
        AlipayConfig config = new AlipayConfig();
        
        new CacheAlipayProductFactory(config).produce(AlipayProductEnum.F2F.name());
    }
}
```

- 工厂类建议是**单例**，减少不必要的资源浪费
- `CacheAlipayProductFactory`默认有**缓存功能**，可通过设置`classCacheEnabled`值关闭，当然依赖方也可自行扩展`IProductFactory`

**2.Spring集成**

此处展示java config版本配置，xml参考集成即可

```java
@Configuration
public class AlipayConfiguration {
    
    @Bean
    public AlipayConfig alipayConfig() {
        return new AlipayConfig("https://openapi.alipay.com/gateway.do",
                   "APP_ID", "APP_PRIVATE_KEY", "json", ENCODING_UTF_8,
                   "ALIPAY_PUBLIC_KEY", SIGN_TYPE_RSA2);
    }
    
    @Bean
    public CacheAlipayProductFactory alipayProductFactory(
            @Qualifier("alipayConfig") AlipayConfig alipayConfig) {
        return new CacheAlipayProductFactory(alipayConfig);
    }
}
```

```java
@Service
public class AlipayF2FService {
    @Autowired
    IProductFactory alipayProductFactory;
    
    public void pay() {
        // 具体使用哪种产品，见方法注释
        IAlipayF2FClient f2FClient = (IAlipayF2FClient) 
            alipayProductFactory.produce(AlipayProductEnum.F2F.name());
        
        // 具体字段规则，参考各个model字段定义及api规则
        PayBizContentReq req = PayBizContentReq.builder()
                        .outTradeNo(SimpleOrderIdGenerator.get(AlipayProductEnum.F2F.name()))
                        .scene(SCENE_BAR_CODE)
                        .authCode("28763443825664394")
                        .subject("Iphone XS Max 128G")
                        .storeId("NJ_001")
                        .totalAmount("12388.23")
                        .timeoutExpress("2m")
                        .build();
        
        f2FClient.pay(req);
    }
}
```


**3.测试用例：**  

**集成测试前，请详细阅读[注意事项](#注意事项)** 

 `com.github.lol.pay.component.alipay.product.*.impl`路径下详细的测试了每个产品的方法，
集成时可参考，各字段含义可直接参考注释或查询官方文档

其中，在线网关支付-测试类`AlipayF2FServiceTest`**注解最为详细，可参照此处集成测试**，其它的产品大同小异

### 注意事项