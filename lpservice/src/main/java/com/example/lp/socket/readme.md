Socket方式通信
Service服务端Socket
步骤一：
 建立一个服务器 Socket

常见的一个服务器 Socket 类是 ServerSocket，ServerSocket 类常用三个方法：binder 、accept、close。
bind 方法为 ServerSocket 绑定 IP 地址和端口号，
并开始监听该端口；accept 方法为 ServerSocket 接收请求并返回一个Socket 对象，
accept 方法调用之后将一直阻塞，直到有请求达到；close 方法关闭一个 ServerSocket 对象。
初始化是一般需要设定 IP 地址和端口号

步骤二：
通过监听获取一个用于通信的 Socket 对象

这一步直接通过上述 accept 方法的执行就可实现

步骤三： 
在一个新线程中，通过对 Socket 对象进行封装，分别得到输入、输出流的引用对象，通过这两个对象向 Client 端发送或者从 Client 端接收数据，进而实现 Socket 通信。

一般选择在循环中读取 Client 发送过来的信息，并作出对应的处理，比如反馈 Client 端：自己已成功收到相应的消息。

步骤四： 
在适当的时机关闭 Socket 连接


Client 客户端

步骤一： 
初始化 Socket 对象

客户端一般选择在一个新的线程中，初始化一个 Socket 对象，
初始化是需要设置 IP 和端口号，以帮助低层网络路由找到相应的服务端进程。

步骤二：
获取与 Server 端通信的引用

此步和 Server 端建立连接后的步骤类似：
根据步骤一中获取的 Socket 对象，进行封装，得到相应的输入、输出流对象，这些输入、输出流对象就是和 Server 端进行通信的引用。

步骤三： 
通过步骤二中得到的引用，循环的读取（在新线程中） Server 端发送过来的消息，并做相应的处理

步骤四：
在合适的时机关闭与 Server 端的 Socket 连接

