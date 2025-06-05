# 数据库表设计

* 本项目使用 MySQL 数据库.
* 本项目所有表均在 schema `drydock_lession` 下。
* 本项目所使用的表，每个表表通过自动抽象机制（
  `io.github.sinri.drydock.lesson.test.MySQLTableClassGenerator`）生成的对应的类（即
  [io.github.sinri.keel.integration.mysql.result.row.AbstractTableRow](https://github.com/sinri/Keel/blob/p4.0/src/main/java/io/github/sinri/keel/integration/mysql/result/row/AbstractTableRow.java
  ) 的实现类），均在 package `io.github.sinri.drydock.lesson.entity.table` 下。