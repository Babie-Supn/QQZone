java.io.NotSerializableException\
 报该错误的类，需要在该类实现序列化接口\
即implements Serializable  \
1)、需要把内存中的对象状态数据保存到一个文件或者数据库中的时候，这个场景是比较常见的，例如我们利用mybatis框架编写持久层insert对象数据到数据库中时;\
2)、网络通信时需要用套接字在网络中传送对象时，如我们使用RPC协议进行网络通信时;\

   删除回复\
1)如果有相关联的主人回复，需要先删除主人回复\
2)删除回复\
 Cannot delete or update a parent row: a foreign key constraint fails\
   ('qqzonedb'.'q_host_reply',CONSTRAINT 'FK_host_reply' FOREIGN KEY('reply') PEFERENCES 'q_reply' ('id'))\
 我们在删除回复表的记录时，发现删除失败，原因是：在主人回复表中仍然有记录引用待删除的回复这条记录\
如果需要删除子表数据，需要先删除子表数据\
    删除日志\
1）删除日志，首先需要考虑是否有关联的回复\
2）删除回复，首先需要考虑是否有关联的主人回复\
3）另外，如果不是自己的空间，则不能删除日志\



