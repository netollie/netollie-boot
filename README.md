# Netollie-Boot
## Starters
### netollie-boot-starter-js
> 在pom.xml中引入
```xml
<dependency>
    <groupId>com.netollie</groupId>
    <artifactId>netollie-boot-starter-js</artifactId>
</dependency>
```
> 配置application.yml
```yaml
netollie:
  js-engine:
      base-packages: "com.netollie.boot.starter.demo.engine.js"
```
> 自定义interface
```java
@JsEngine
public interface StringEngine {
    @JsTemplate("escape(value)")
    String escape(@JsVariable("value") String value);
}
```
