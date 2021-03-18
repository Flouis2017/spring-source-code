### IOC（Inversion of Control）控制反转

**概念**：打破上层依赖下层的传统设计，把底层类作为参数传递给上层类，实现上层对下层的“控制”。

**说明**：传统设计——高层Bean需要依赖底层Bean，如果要new一个高层Bean需要从最底层的Bean开始new，以“车”为例：整车 _依赖_  车身 _依赖_  底盘 _依赖_  轮胎，所以要实例化整车，必须从轮胎开始实例化，一旦轮胎需要改动，那么底盘、车身、整车就都得跟着改，违背软件开发的开闭原则。IOC将“造车”过程进行反转，轮胎 _注入_  底盘 _注入_  车身 _注入_  整车，这时候除了整车是最终产物，其他都是作为组件进行注入，每个组件都可以单独改动而不影响其他组件，比如这时候轮胎需要改动，就不需要再对底盘、车身、整车全部改一遍。还有一个比较象形的比喻：把类看成一个房子，传统设计的程序员在房子中，房子里有什么才对外提供什么。控制反转的程序员在房子外，需要什么就去房子里拿什么。



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



### AOP（Aspect Oriented Programming）面向切面编程

**概念**：关注点分离——不同的问题交给不同的程序去解决

**说明**：企业开发中除了业务代码之外往往还需要编写事务管理、缓存、日志等等通用功能的代码。这些通用功能代码的实现，对应的就是所谓的切面（Aspect），业务代码和切面代码分开后，架构将变得高内聚低耦合——这个过程称为**解耦**。同时为了确保功能的完整性，切面最终需要被合并到业务中，这个过程称为**织入**。



AOP的三种织入方式：

* 编译时织入：需要特殊的Java编译器，如AspectJ
* 类加载时织入：需要特殊的Java编译器，如AspectJ和AspectWerkz
* 运行时织入：Spring采用的方式，通过动态代理的方式（JDK动态代理和Cglib动态代理）



AOP关键注解：

* @Aspect+@Component：声明一个类为切面类——项目启动后让IOC容器对这个切面类实例化
* @Pointcut：切入点——对哪些方法进行切入，支持正则表达式
* Advice：五种切入方式——定义切面的代码如何织入到目标方法
  * 前置通知：@Before
  * 后置通知：@AfterReturning
  * 异常通知：@AfterThrowing
  * 最终通知：After
  * 环绕通知：Around



### SpringMVC 工作流程

<img src="screenshot/springmvc_workflow.jpg"/>

SpringMVC运行原理：

1. 客户端请求发送至DispatchServlet
2. 由DispatchServlet查询HandlerMapping，找到处理请求的Controller
3. DispatchServlet将请求发送给Controller
4. Controller调用业务层处理请求，返回ModelAndView
5. DispatchServlet查询视图解析器（ViewResolver）找到对应的视图（View）
6. 视图负责将最终结果展示到客户端