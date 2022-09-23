# Whale

玩具`ORM2.0`预定

-------------------------

TODO List:

- [ ] 日志库
    - [ ] 颜色
    - [ ] 位置
    - [ ] 时间
    - [ ] 等级
- [ ] 反射获取元信息
    - [x] 编写注解
    - [x] 解析注解
    - [x] 生成TupleList
    - [ ] 从TupleList生成对象
- [ ] 方言
    - [ ] 统一接口设计
    - [ ] Sqlite 方言
    - [ ] MySQL 方言
- [ ] 转换元数据至具体SQL
    - [ ] 转换规则
    - [ ] 调用方言接口交互数据库
- [ ] hook
    - [ ] 埋下挂载点
    - [ ] 实现注册
- [ ] 事务
    - [ ] 嗯调

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