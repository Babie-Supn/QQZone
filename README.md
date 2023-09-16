java.io.NotSerializableException\
 报该错误的类，需要在该类实现序列化接口\
即implements Serializable  \
1)、需要把内存中的对象状态数据保存到一个文件或者数据库中的时候，这个场景是比较常见的，例如我们利用mybatis框架编写持久层insert对象数据到数据库中时;\
2)、网络通信时需要用套接字在网络中传送对象时，如我们使用RPC协议进行网络通信时;