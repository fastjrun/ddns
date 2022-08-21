-- ----------------------------
-- Table structure for ddns_sys
-- ----------------------------
DROP TABLE IF EXISTS `ddns_sys`;
CREATE TABLE `ddns_sys`
(
    prop_name  varchar(100) NOT NULL DEFAULT '' COMMENT '属性名',
    prop_value varchar(100) NOT NULL DEFAULT '' COMMENT '属性值',
    prop_desc  varchar(200) NOT NULL DEFAULT '' COMMENT '属性描述',
    PRIMARY KEY (prop_name)
) COMMENT ='系统配置表';

-- ----------------------------
-- Table structure for ddns_record
-- ----------------------------
DROP TABLE IF EXISTS `ddns_record`;
CREATE TABLE `ddns_record`
(
    `id`          bigint(20)  NOT NULL AUTO_INCREMENT COMMENT '用户 ID',
    `record`      varchar(50) NOT NULL COMMENT '用户名',
    `create_date` timestamp   NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `update_date` timestamp   NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`)
) COMMENT ='ddns记录表';