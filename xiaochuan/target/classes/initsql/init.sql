CREATE TABLE `access` (
                          `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
                          `gmt_create` datetime NOT NULL COMMENT '创建时间',
                          `gmt_modified` datetime NOT NULL COMMENT '修改时间',
                          `is_removed` tinyint(4) NOT NULL COMMENT '是否被删除',
                          `connector_key` varchar(50) DEFAULT NULL COMMENT '连接器ID',
                          `access_key` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
                          `access_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'id，系统自动生成, 8855ecda66eb7e691471ae',
                          `accessInfo` text NOT NULL COMMENT '元数据, json格式',
                          `extensions` text COMMENT '扩展字段',
                          PRIMARY KEY (`id`) USING BTREE,
                          UNIQUE KEY `asset_key` (`access_key`)
) ENGINE=InnoDB AUTO_INCREMENT=5754 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='access表';