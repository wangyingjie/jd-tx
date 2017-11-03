CREATE TABLE `svc_booking_order_001` (
  `id` bigint(20) NOT NULL COMMENT '主键id',
  `booking_id` bigint(20) DEFAULT NULL COMMENT '预约记录id',
  `root_order_id` bigint(20) DEFAULT NULL COMMENT '顶层父单号',
  `direct_order_id` bigint(20) DEFAULT NULL COMMENT '直接父单号',
  `erp_order_id` bigint(20) DEFAULT NULL COMMENT '实物erp订单号',
  `sku_info` varchar(512) DEFAULT NULL COMMENT '商品sku json :skuId:,qty:',
  `order_fee` int(11) DEFAULT NULL COMMENT '订单金额',
  `deliver_status` tinyint(4) DEFAULT NULL COMMENT '妥投状态(0:未妥投, 1:已妥投)',
  `orb_status` tinyint(4) DEFAULT NULL COMMENT '快退状态(0待受理、1受理成功、2取消成功、3取消失败)',
  `afs_status` tinyint(4) DEFAULT NULL COMMENT '售后服务单状态(0待创建、1创建成功、2取消成功[待用户确认])',
  `generated_time` datetime DEFAULT NULL COMMENT '生成时间',
  `features` varchar(512) DEFAULT NULL COMMENT '特征字段',
  `created` datetime DEFAULT NULL COMMENT '创建时间',
  `modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `yn` tinyint(4) DEFAULT '1' COMMENT '是否有效(0:无效, 1:有效)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='实物子订单表';

CREATE TABLE `svc_booking_order_002` (
  `id` bigint(20) NOT NULL COMMENT '主键id',
  `booking_id` bigint(20) DEFAULT NULL COMMENT '预约记录id',
  `root_order_id` bigint(20) DEFAULT NULL COMMENT '顶层父单号',
  `direct_order_id` bigint(20) DEFAULT NULL COMMENT '直接父单号',
  `erp_order_id` bigint(20) DEFAULT NULL COMMENT '实物erp订单号',
  `sku_info` varchar(512) DEFAULT NULL COMMENT '商品sku json :skuId:,qty:',
  `order_fee` int(11) DEFAULT NULL COMMENT '订单金额',
  `deliver_status` tinyint(4) DEFAULT NULL COMMENT '妥投状态(0:未妥投, 1:已妥投)',
  `orb_status` tinyint(4) DEFAULT NULL COMMENT '快退状态(0待受理、1受理成功、2取消成功、3取消失败)',
  `afs_status` tinyint(4) DEFAULT NULL COMMENT '售后服务单状态(0待创建、1创建成功、2取消成功[待用户确认])',
  `generated_time` datetime DEFAULT NULL COMMENT '生成时间',
  `features` varchar(512) DEFAULT NULL COMMENT '特征字段',
  `created` datetime DEFAULT NULL COMMENT '创建时间',
  `modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `yn` tinyint(4) DEFAULT '1' COMMENT '是否有效(0:无效, 1:有效)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='实物子订单表';