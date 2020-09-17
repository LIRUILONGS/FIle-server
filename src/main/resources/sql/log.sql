

CREATE TABLE `operate_log` (
  `id` varchar(32) DEFAULT NULL,
  `user_id` varchar(32) DEFAULT NULL,
  `module` varchar(255) DEFAULT NULL,
  `method` varchar(255) DEFAULT NULL,
  `ip` varchar(50) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `class_name` varchar(255) DEFAULT NULL,
  `user_name` varchar(32) DEFAULT NULL,
  `oper_type` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL
);