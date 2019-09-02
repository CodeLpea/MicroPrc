###MVP模式开发
优势：

离了视图逻辑和业务逻辑，降低了耦合。
Activity只处理生命周期的任务，代码变得更加简洁。

视图逻辑和业务逻辑分别抽象到了View和Presenter的接口中，提高代码的阅读性。

Presenter被抽象成接口，可以有多种具体的实现，所以方便进行单元测试。

把业务逻辑抽到Presenter中去，避免后台线程引用着Activity导致Activity的资源无法被系统回收从而引起内存
泄露和OOM。


ui：

view层，Activity，Fragment等。

presenter：

P层，负责连接V层和M层,中心管理器

model：

M层，负责数据库,网络请求,服务等业务操作

