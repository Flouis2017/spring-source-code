### IOC（Inversion of Control）：控制反转

**概念**：打破上层依赖下层的传统设计，把底层类作为参数传递给上层类，实现上层对下层的“控制”。



IOC、DI、DL的关系：

IOC是一种思想，而DI（Dependency Injection - 依赖注入）和 DL（Dependency Lookup - 依赖查找）是对这种思想的实现。EJB就是利用DL实现IOC，DL相对于DI是一种更为主动的方式，它通过调用框架预留的方法来获取对象，获取时需要提供相关的配置文件路径。DL已经被抛弃，因为它需要程序员自己调用API进行查找资源和组装对象，具有侵入性。DI是Spring实现IOC的方式，也是当今IOC的主流实现。



依赖注入的方式：

* Setter —— 使用setter方法
* Interface —— 使用接口方式
* Constructor —— 使用构造方式
* Annotation —— 使用注解方式，也叫声明式注入，如：@Autowired、@Resource



IOC容器的优势：

* 避免在各处使用new来创建类，并且可以做到统一维护
* 创建实例的时候不需要了解其中的细节



IOC容器两大核心接口：

* BeanFactory：Spring的“心脏”。它就是Spring IOC的真面目，Spring通过BeanFactory来实例化、装配和管理Bean。
* ApplicationContext：Spring的“躯体”，ApplicationContext由BeanFactory派生而来，提供了更多面向实际应用的功能。

二者区别：

* BeanFactory是Spring内部使用的接口，而ApplicationContext是面向使用Spring的开发者的接口，即BeanFactory对内，ApplicationContext对外。
* BeanFactory延迟加载Bean，而ApplicationContext相反，它在IOC容器启动的时，一次性加载所有Bean。
* ApplicationContext从BeanFactory派生而来,BeanFactory的功能ApplicationContext都有。
* BeanFactory只能管理单例（Singleton）Bean的生命周期，它不能管理原型（Prototype）Bean的生命周期，因为原型Bean实例创建出来后就传给了客户端，IOC容器失去它们的引用。



Spring Bean 作用域：

* Singleton：Spring的默认作用域，IOC容器中只有唯一的Bean实例
* Prototype：只要有getBean请求，IOC容器都会创建一个Bean实例，并把该实例返回给调用者
* Request：会为每一个HTTP请求创建一个Bean实例
* Session：会为每一个Session创建一个Bean实例







