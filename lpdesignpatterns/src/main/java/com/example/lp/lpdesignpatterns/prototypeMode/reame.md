原型模式

定义：用原型实例指定创建对象的种类，并通过拷贝这些原型，创建新的对象

使用方法：需要使用原型模式clone的对象必须实现Cloneable接口并重写clone方法，将super.clone。

优点：

实在内存中二进制流的拷贝，

在多数复杂或者大内存消耗的对象创建中，

要比直接new一个对象性能更好。

缺点：

直接拷贝对象，构造函数是不会执行的，

存在潜在问题，在使用中需要考虑到，

还有要区分浅引用与深引用，避免修改引用造成问题。