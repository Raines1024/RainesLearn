##Mac安装并运行Zookeeper
### 一、zookeeper下载地址：选择的是 3.4.14
http://mirror.bit.edu.cn/apache/zookeeper/

### 二、解压下载得到的ZooKeeper压缩包，发现有bin，conf，lib等目录。
      bin目录中存放有运行脚本；
      conf目录中存放有配置文件；
      lib目录”中存放有运行所需要第三方库。
  
### 三、单机配置
#### 编辑配置文件
在conf目录下，新建一个名为zoo.cfg的文件，其中内容如下：

    # 服务器与客户端之间交互的基本时间单元（ms） 
    tickTime=2000   
    # zookeeper所能接受的客户端数量 
    initLimit=10  
    # 服务器和客户端之间请求和应答之间的时间间隔 
    syncLimit=5
    # zookeeper中使用的基本时间单位, 毫秒值.
    tickTime=2000
    # 数据目录. 可以是任意目录.
    dataDir=/tmp/zookeeper/data
    # log目录, 同样可以是任意目录. 如果没有设置该参数, 将使用和#dataDir相同的设置.
    dataLogDir=/tmp/zookeeper/log
    # t监听client连接的端口号.
    clientPort=2181

### 四、运行zookeeper
进入解压目录(zookeeper-3.4.14)，执行以下命令：

    ## 启动ZooKeeper
    ./bin/zkServer.sh start
    ## 停止ZooKeeper
    ./bin/zkServer.sh stop
