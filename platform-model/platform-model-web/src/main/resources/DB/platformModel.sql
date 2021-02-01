-- ----------------------------
-- 数据库地址: 192.168.1.235
-- 数据库名称: db_test
-- 数据库账号: admin
-- 数据库密码: npQsd8a
-- ----------------------------

DROP TABLE IF EXISTS `drink`;
CREATE TABLE `drink` (
    `id` bigint(20) AUTO_INCREMENT COMMENT '主键ID',
    `tenant_id` bigint(20) COMMENT '租户ID',
    `group_id` bigint(20) COMMENT '集团ID',
    `org_id` bigint(20) COMMENT '机构ID',
    `dept_id` bigint(20) COMMENT '部门ID',
    `drink_name` varchar(50) COMMENT '名称',
    `create_id` bigint(20) COMMENT '创建人id',
    `create_name` varchar(20) COMMENT '创建人【最大长度20】',
    `create_time` timestamp COMMENT '创建时间【yyyy-MM-dd HH:mm:ss】',
    `modify_id` bigint(20) COMMENT '修改人id',
    `modify_name` varchar(20) COMMENT '修改人【最大长度20】',
    `modify_time` timestamp COMMENT '修改时间【yyyy-MM-dd HH:mm:ss】',
    `extension` text COMMENT '扩展字段',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

