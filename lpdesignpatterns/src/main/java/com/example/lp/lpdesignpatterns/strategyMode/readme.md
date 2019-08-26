策略模式：符合里氏替换原则，开闭原则，依赖倒置原则

同一个问题具有不同算法时，即仅仅是具体的实现细节不同时，如各种排序算法等等。

对客户隐藏具体策略(算法)的实现细节，彼此完全独立；提高算法的保密性与安全性。

一个类拥有很多行为
而又需要使用if-else或者switch语句来选择具体行为时。
使用策略模式把这些行为独立到具体的策略类中，可以避免多重选择的结构。

优点
策略类可以互相替换
由于策略类都实现同一个接口，因此他们能够互相替换。
耦合度低，方便扩展
增加一个新的策略只需要添加一个具体的策略类即可，基本不需要改变原有的代码，符合开闭原则。
避免使用多重条件选择语句（if-else或者switch）。

缺点
策略的增多会导致子类的也会变多
客户端必须知道所有的策略类，并自行决定使用哪一个策略类。



Android中的源码分析：
之前我们用的ListView时都需要设置一个Adapter，
而这个Adapter根据我们实际的需求可以用ArrayAdapter、SimpleAdapter等等，这里就运用到策略模式。