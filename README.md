# Whale

玩具`ORM2.0`预定

-------------------------

TODO List:

- [x] 日志库
    - [x] 颜色
    - [x] 时间
    - [x] 等级
- [ ] 反射获取元信息
    - [x] 编写注解
    - [x] 解析注解
    - [x] 生成TupleList
    - [ ] 从TupleList生成对象
- [ ] 方言
    - [x] 统一接口设计
    - [ ] 注册到全局
    - [ ] Sqlite 方言
        - [x] 生成 `CREATE` 语句
        - [ ] 生成 `INSERT` 语句
        - [ ] 生成 `QUERY` 语句
        - [ ] 生成 `UPDATE` 语句
        - [ ] 生成 `DELETE` 语句
    - [ ] MySQL 方言
- [ ] 转换元数据至具体SQL
    - [x] CreateOperator
    - [ ] InsertOperator
    - [ ] QueryOperator
    - [ ] DeleteOperator
    - [ ] JoinOperator
    - [ ] 编写 Condition 以生成查询子句
- [ ] hook
    - [ ] 埋下挂载点
    - [ ] 实现注册
- [ ] 事务
    - [ ] 嗯调
- [x] 连接
    - [x] 稍微封装一下JDBC

-----------------------------
对于一条语句

完整的流程应该如下

```text
       
                                                                               hook
                                                                                |
                                                                                |
                                                                                |
                      reflect                                                   |
data class object   ==========>  List[Tuple](ColumnIndex, ColumnValue) =======================>  Dialect
                  table meta data                                                                  ||
                                                                                     JDBC          ||
                                                                                      |            ||
                                                                                      |            \/
List[Tuple](ColumnIndex, ColumnValue) <===== JDBC Iterator <==========   execute  <=======     SQL sentence
            ||
            ||------------- hook
            ||  
            ||  invoke
            ||
            \/
     data class object



```