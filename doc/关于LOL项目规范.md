## 项目规范

> 该文档还未拍板发版，目前处于待审核ing...

## 项目结构

目录树：
```
.
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

## 命名规则

### 二方包
- 【必须】基础包命名必须以`lib`开头作为前缀，**不允许依赖任何第三方包**
- 【必须】新建项目命名风格保持为 [模组]-[包类型]-[功能]，例如`notice-component-sms`
如模组下预估有多个项目应提升模组为父节点，包类型可分为`component`或者`lib`，其中`compoent`类型意为业务集中包，
可依赖第三方包，`lib`类型则**不允许依赖任何三方包**

- 【必须】项目内部文件夹命名风格保持为 `com.github.lol.[模组].[包类型].[功能]`，例如`com.github.lol.notice.component.sms`
- 【必须】新建项目版本默认为`<version>0.0.1-SNAPSHOT</version>`，后续按需发版修改版本
- 【必须】二方包应在具体项目提供`README.md`，详细说明如何集成测试和使用
- 【仁见智见】模组下各子项目共享包命名 [模组]-core，例如`notice-core`
- 【仁见智见】二方包提供服务应尽可能做到**灵活可配（提供默认配置）**& **实例工厂**，应充分为依赖者着想，
如：提供集成Spring等主流框架集成功能，SPI扩展等，一切以灵活，可配置，可覆盖优先

### 服务包

项目树：
```
.
└── pay-integration-payment                       
    ├── pay-integration-payment-api         聚合支付api
    └── pay-integration-payment-service     聚合支付service
```
- 【必须】服务包应提供`**-api` & `**-service`，前者为通信双方依赖必备的`interface`, `model`, `constant`, `enum`，
后者为服务提供者源码实现，**对外输出接口应依赖`api`的`interface`**
- 【必须】`api`包中`interface`命名规范为`**Client`，**服务与服务间调用只能依赖`Client`，而不能直接集成实现类**，方便服务拆分


## 服务调用

考虑如下场景：<br>
项目初始服务群体不大，无需拆分服务，可合并多个`Service`包到一个聚合服务中打包成`war`部署单台服务，
待线上流量逐渐增大或部分模组服务更新太过频繁时，需要拆分出服务，以便单独维护，**如何快速拆分？**

- 【必须】聚合服务内[模组服务]相互调用时，只能依赖`**Client`接口，**不予许直接创建任何`Service`包中类实例**

- 【必须】聚合服务内应在项目目录最顶层新建文件夹`dependency`下创建类命名`DependencyService`，所有的依赖`Service`的`Client`构造**只允许在内部完成**，
提供对应`Client`的`Get`方法，**聚合服务内部只能使用`dependencyService.get****Client()`执行相应的服务调用**，方便后续拆分服务