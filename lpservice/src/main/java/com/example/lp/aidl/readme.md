AIDL简介：
AIDL（Android Interface Definition Language）是Android接口定义语言的意思；
它可以用于让某个Service与多个应用程序组件之间进行跨进程通信，从而可以实现多个应用程序共享同一个Service的功能。
实际上实现跨进程之间通信的有很多，比如广播，Content Provider；
但是AIDL的优势在于速度快(系统底层直接是共享内存)，性能稳，效率高，一般进程间通信就用它。
既然是跨进程，那必须的有两个应用，一个是service端，一个是client端，然后实现客户端从服务端获取数据。

在建立服务端之前，首先要确定需要跨进程传递的信息和方法，因此先定义个.aidl类
类似于接口的定义，右键选择NEW-AIDl，最在java平级目录下新建一个aidl类。其中包含了要通信的方法和信息类型。
例如：
interface IMyAidlInterface {

   String getString();
}

服务端：AidlService
相比于一般的service通信方式，使用onBind方法返回的是自定义的Bindr的；
跨进程通信时通过onBind方法，返回的就是IMyAidlInterface.Stub的实现。
借此，客户端就可以通过aidl中的方法调用service对应的信息和方法，完成通信。

客户端：AidlActivity
与一般的绑定流程相似，实例化ServiceConnection，然后将ibinder通过xxx.stub.asInterface(iBinder)
来转换为所需要的aidl，然后借此来与跨进程service的通信和方法调用。
