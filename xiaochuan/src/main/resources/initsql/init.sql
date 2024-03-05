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



