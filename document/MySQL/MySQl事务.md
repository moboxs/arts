###事务可靠性模型
Atomicity:原子性，一次事务中的操作要么全部成功要么全部失败；

Consistency：一致性，跨表、跨行、跨事务，数据库始终保持一致状态；

Isolation：隔离性，可见性，保护事务不会互相干扰，包含4种隔离级别。

Durability：持久性，事务提交成功后不会丢失数据。

### 锁

    表级锁 (myisam)
    1、意向锁
    2、共享意向锁
    3、排他意向锁
    4、insert意向锁
    
    行级锁 （innodb）
    1、记录锁，锁定的是索引记录
    2、间隙锁
    3、临键锁
    4、谓词锁

    死锁：
    - 阻塞与互相等待
    - 增删改、锁定读
    - 死锁检测与自动回滚
    - 锁粒度
    

### 事务
    
    读未提交：READ UNCOMMITTED，很少使用，不能保证一致性，脏读 幻读
    读已提交：READ COMMITTED，RC，每次查询都会设置和读取自己新的快照，不可重复读 幻读
    可重复读：REPEATABLE READ （mysql Innodb默认隔离级别），使用事务在第一次读取时创建的快照，多版本技术，
        锁：使用唯一索引的唯一查询条件时，只锁定查找到的索引记录，不锁定间隙，其他查询条件，会锁定扫描到的索引范围，
        通过间隙锁或临建锁来阻止其他会话在这个范围中插入值。
    串行化：SERIALIZBLE
    

### undo log: 撤销日志，事务回滚
    保证事务的原子性
    用于事务回滚，一致性读，崩溃恢复
    记录事务回滚时所需的撤销操作
    一条insert语句，对应一条delete的undo log
    每个update语句，对应一条相反的update的undo log

    保存：
    system tablespace (mysql5.7默认)
    undo tablespace (MySQL8.0默认)

    回滚段
    
### redo log:重做日志，确保事务的持久性，
    确保事务的持久性，防止事务提交后数据未刷新到磁盘就掉电或崩溃。
    事务执行过程中写入redo log，记录事务对数据页做了哪些修改。
    提升性能：WAL（Write-Ahead Logging）技术，先写日志，再写磁盘。
    日志文件：ib_logfile0，ib_logfile1
    日志缓冲：innodb_log_buffer_size
    强刷：fsync()
    