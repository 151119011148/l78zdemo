CREATE TABLE `xiaochuan`.`user`  (
                                         `id` int(0) NOT NULL AUTO_INCREMENT,
                                         `user_id` varchar(64),
                                         `name` varchar(64),
                                         `password` varchar(64),
                                         `gmt_create` datetime NOT NULL COMMENT '创建时间',
                                         `gmt_modified` datetime NOT NULL COMMENT '修改时间',
                                         `is_removed` tinyint(4) NOT NULL COMMENT '是否被删除',
                                         PRIMARY KEY (`id`)
);
INSERT INTO `user` VALUES (1, 'UserID_123456', 'admin', 'admin', '2024-03-06 10:52:48', '2024-03-06 10:52:50', 0);



CREATE TABLE `xiaochuan`.`category`  (
                                         `id` int(0) NOT NULL AUTO_INCREMENT,
                                         `category_id` varchar(64),
                                         `parent_id` varchar(64),
                                         `level` varchar(64),
                                         `name` varchar(64),
                                         `desc` varchar(256),
                                         `gmt_create` datetime NOT NULL COMMENT '创建时间',
                                         `gmt_modified` datetime NOT NULL COMMENT '修改时间',
                                         `is_removed` tinyint(4) NOT NULL COMMENT '是否被删除',
                                         PRIMARY KEY (`id`)
);


CREATE TABLE product (
                         `id` int(0) NOT NULL AUTO_INCREMENT,
                         product_id VARCHAR(64),
                         model VARCHAR(10),
                         title VARCHAR(64),
                         place_of_origin VARCHAR(64),
                         drive_wheel VARCHAR(10),
                         finishing VARCHAR(10),
                         material VARCHAR(20),
                         diameter VARCHAR(20),
                         brand_name VARCHAR(20),
                         width VARCHAR(20),
                         car_make VARCHAR(20),
                         available_size VARCHAR(20),
                         available_size_detail VARCHAR(512),
                         available_finish VARCHAR(20),
                         img_list VARCHAR(512),
                         product_description VARCHAR(512),
                         supply_capacity VARCHAR(512),
                         offset_range VARCHAR(20),
                         pcd VARCHAR(20),
                         center_bore VARCHAR(20),
                         specifications TEXT,
                         category_id VARCHAR(32),

                         related_category_id VARCHAR(512),
                         `gmt_create` datetime NOT NULL COMMENT '创建时间',
                         `gmt_modified` datetime NOT NULL COMMENT '修改时间',
                         `is_removed` tinyint(4) NOT NULL COMMENT '是否被删除',
);

CREATE TABLE `xiaochuan`.`basket`  (
                                     `id` int(0) NOT NULL AUTO_INCREMENT,
                                     `visit_id` varchar(64),
                                     `product_ids` varchar(512),
                                     `gmt_create` datetime NOT NULL COMMENT '创建时间',
                                     `gmt_modified` datetime NOT NULL COMMENT '修改时间',
                                     `is_removed` tinyint(4) NOT NULL COMMENT '是否被删除',
                                     PRIMARY KEY (`id`)
);

