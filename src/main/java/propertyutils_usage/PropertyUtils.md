# PropertyUtils类 说明

本文档基于[官方文档](https://commons.apache.org/proper/commons-beanutils/javadocs/v1.9.4/apidocs/org/apache/commons/beanutils/package-summary.html#standard)编写，提炼出PropertyUtils
类的`使用场景`和`设计理念`

### 背景

java官方组件对使用 `获取器(getter())` 获取 `beans` 内的属性值有着很好的支持，但是当遇到非常复杂的场景，而这些场景实际上又不需要程序员在编码阶段知道自己需要访问那些 beans 、需要修改或访问那些属性。
针对这个问题，java给出了类似 `java.beans.Introspector` 自省类，他们能在 `runtime阶段` 审查一个类并且定位某个属性的getter 和 setter；再加上java的反射机制，使得程序可以在 `runtime阶段` 动态的
调用这些函数，但是使用这些API太复杂，将不必要的底层细节暴露在程序员面前，因此 `BeanUtils` 中的一些API就是为了简化动态地设置和获取 beans 中的属性，不管是你想调用哪个 beans，还是访问 beans中哪个
属性，这些都在 `runtime阶段`动态决定，而不是在程序员编码的阶段。

而在 `BeanUtils` 中，是 `PropertyUtils`类 提供满足这些需求的API；在详细了解 API 的使用细节之前，有必要了解一些概念：

### 属性的分类与访问

JavaBean 所支持的属性大致可以分为三类，他们有些被 `标准 JavaBeans`所支持，有些则在 `BeanUtils` 中单独提供支持:

- `简单属性(Simple)`: 只有一个值被访问(`retrieved`)或被修改(`modified`)的属性，有两种可能：
  - 与 `int`变量一样是Java原生数据类型变量
  - App中类的对象，或是App引入的库实例化出的对象
- `有序属性(Indexed)`: 有序属性存储了一个有序对象容器，有序对象可以使用下标访问；有序属性是JavaBeans的扩展属性，`BeanUtils`包允许任何底层数据类型实现 `java.util.List`的容器对象作为有序属性
- `键值属性(Mapped)`: 是标准JavaBeans的扩展API, `BeanUtils`包允许任何底层数据类型实现 `java.util.Map`接口的键值容器作为键值属性

**例程**
- [Basic/Nested Property Access](./_1_basic_property_access)

### 动态 Beans (DynaBeans)

`PropertyUtils` 中的 API 提供动态的属性访问而不用修改 Beans 中的任何代码，而当你打算将一群经过动态计算生成的属性值封装为 JavaBeans, 但是
又没有设计这样的类来表示这些属性时，就涉及到 `动态属性访问` 的另一个使用场景了。 伴随着 `动态属性绑定` 节省了不去为了那些动态生成的属性额外
创建、维护一个类的力气，它还意味着程序员可以处理动态生成属性的场景了，例如当处理将执行DQL语句后产生的 `ResultSet` 内容封装成 Beans 时。

为了满足以上需求，`BeanUtils`包 提供了 `DynaBean` 接口， 此接口必须由实现此接口内部函数的 Bean 类实现。

**例程**
- [Dynamic Beans (DynaBeans)](./_2_dynamic_beans_usage)

## 例程目录

- [Basic/Nested Property Access](./_1_basic_property_access): 展示基本的属性访问
- [Dynamic Beans (DynaBeans)](./_2_dynamic_beans_usage)

