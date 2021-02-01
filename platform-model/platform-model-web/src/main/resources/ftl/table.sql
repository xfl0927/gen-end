-- ----------------------------
-- 数据库地址: 192.168.1.235
-- 数据库名称: db_test
-- 数据库账号: admin
-- 数据库密码: npQsd8a
-- ----------------------------

-- ----------------------------
-- Table structure for drink
-- ----------------------------
DROP TABLE IF EXISTS `drink`;
CREATE TABLE drink(
  id bigint NOT NULL  AUTO_INCREMENT PRIMARY KEY  COMMENT '主键' ,
  tenant_id bigint COMMENT '租户ID' ,
  group_id bigint COMMENT '集团ID' ,
  org_id bigint COMMENT '机构ID' ,
  dept_id bigint COMMENT '部门ID' ,
  drink_name varchar(50) COMMENT '名称' ,
  create_id bigint COMMENT '创建人ID' ,
  create_name varchar(20) COMMENT '创建人' ,
  create_time timestamp COMMENT '创建时间' ,
  modify_id bigint COMMENT '修改人ID' ,
  modify_name varchar(20) COMMENT '修改人' ,
  modify_time timestamp COMMENT '修改时间' ,
  extension text COMMENT '扩展字段' 
);

