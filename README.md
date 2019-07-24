# LOL Project
LOL项目使用Java开发，可以帮助你轻松快速集成市面上主流支付、消息模块，可直接用于生产环境。

已接入支付渠道：支付宝、微信、银联  
已接入消息渠道：微信公众号、短信通道

## Architecture
```
lol
├── doc
├── lib-util                        基础包-工具类
├── notice                          
│   ├── notice-component-sms        通知-短信
│   └── notice-component-wechat     通知-微信
└── pay
    ├── pay-component-alipay        支付-支付宝
    ├── pay-component-unionpay      支付-银联
    └── pay-component-wechatpay     支付-微信
```
详细信息请参考[关于LOL项目](./doc/关于LOL项目规范.md)

各项目下`README.md`有详细说明文档，如需集成，请仔细查看

## Features
- 可插拔、可定制，方便快速集成
- 详细注解，二次开发方便

## Getting started
我将以如何集成银联支付组件为例，其它基础包参考即可
```bash
git clone https://github.com/jifuwei/lol.git
cd lol

mvn clean install
```

### Maven dependency
银联支付只需要依赖如下`component`包即可
```xml
<dependencies>
    <dependency>
        <groupId>com.github</groupId>
        <artifactId>pay-component-unionpay</artifactId>
        <version>${latest.version}</version>
    </dependency>
</dependencies>
```

### Create a unionpay product config bean
```java
@Configuration
public class UnionpayConfiguration {
    
    @Bean
    public UnionpayConfig unionpayConfig() {
        return UnionpayConfig.builder()
                .version(UnionpayConstant.VERSION_5_1_0)
                .encoding(UnionpayConstant.UTF_8_ENCODING)
                // 此处省略必备配置 具体参照字段注解
                .build();
    }
    
    @Bean
    public CacheUnionpayProductFactory unionpayProductFactory(
            @Qualifier("unionpayConfig") UnionpayConfig unionpayConfig) {
        return new CacheUnionpayProductFactory(unionpayConfig);
    }
}


@Service
public class UnionpayGatewayService {
    @Autowired
    IUnionPayProductFactory unionpayProductFactory;
    
    public void gatewayConsume() {
        // 具体使用哪种产品，见方法注释
        IUnionpayGatewayClient gatewayClient = (IUnionpayGatewayClient) 
            unionPayProductFactory.produce(UnionpayProductEnum.GATEWAY.name());
        
        // 具体字段规则，参考各个model字段定义及api规则
        GatewayConsumeReq gatewayConsumeReq = GatewayConsumeReq.of(config)
                        .orderId(SimpleOrderIdGenerator.get(UnionpayProductEnum.GATEWAY.name()))
                        .txnTime(DateTime.now().toString(DEFAULT_DATE_TIME_FORMAT))
                        .txnAmt("10000000")
                        .frontUrl("http://www.lol.com/gateway/frontback")
                        .backUrl("http://www.lol.com/gateway/callback")
                        .payTimeout(new SimpleDateFormat(DEFAULT_DATE_TIME_FORMAT)
                                .format(new Date().getTime() + 15 * 60 * 1000))
                        .build();
        
        FormReq formReq = gatewayClient.consume(gatewayConsumeReq);
    }
}
```

## Building
如果你想尝试最新的功能，可以使用如下命令构建
注意：jdk需要 1.8+
分支：master

```bash
mvn clean install
```

## Contact
- 邮件列表
    - 咕咕鸡：1256427371@qq.com
- Bugs: Issues

## License
LOL Project is under the MIT license. See the [LICENSE](./LICENSE) file for details.