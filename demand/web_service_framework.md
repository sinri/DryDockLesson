# Web服务框架设计

本项目提供的所有Web服务（接口）分为三类，不设防（unfortified）接口, 公共（open）接口，和基于会话（session）管控的接口.

每一个接口均由一个类提供服务，该类应为`io.github.sinri.drydock.lesson.receptionist.AkagiReceptionist`的实现类。

## 不设防接口

不设防接口实现类应置于 package `io.github.sinri.drydock.lesson.receptionist.unfortified` 下（含其子package下），
并实现基类 `io.github.sinri.drydock.lesson.receptionist.unfortified.UnfortifiedAkagiReceptionist`.

不设防接口不进行鉴权。

## 公共接口

公共接口实现类应置于 package `io.github.sinri.drydock.lesson.receptionist.open` 下（含其子package下），
并实现基类 `io.github.sinri.drydock.lesson.receptionist.open.OpenAkagiReceptionist`.

公共接口开放给 `client_code` 和 `client_secret` 授权密钥对的持有者调用。
公共接口的鉴权方式为通过请求参数中的 `client_code`、`nonce`、`checksum` 三个值组合而成。
其中，`nonce`为任意字符串；`checksum`的计算方式如下：

```java
String clientCode;
String clientSecret;
String nonce;
Strign checksum = md5(clientCode + "@" + nonce + "@" + clientSecret);
// 上面的md5方法需要自行引用
```