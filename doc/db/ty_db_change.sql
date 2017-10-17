--
-- Database: `ty_db`
--
-- --------------------------------------------------------

--
-- 20170809
--
--
-- 修改原因：添加default值,以及修改is_self, is_top为boolean(tinyint(4)改为tinyint(1))
--

ALTER TABLE `product_info` CHANGE `is_self` `is_self` TINYINT(1) NOT NULL DEFAULT '0' COMMENT '是否非自营商品： 0：否 1：是', CHANGE `is_top` `is_top` TINYINT(1) NOT NULL DEFAULT '0' COMMENT '是否置顶： 0：否 1：是', CHANGE `audit_status` `audit_status` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '审核状态: 0：待审核 1：审核通过 2：审核不通过';
--
-- 20170821
--
--
-- 修改原因：product_coupon添加pick_start_time和exchange_price，即秒杀开始时间和兑换所需的金币数;修改end_time为pick_end_time;修改total，reserved，exchange_deadline，use_deadline为可null
--
ALTER TABLE `product_coupon` ADD `pick_start_time` DATETIME NULL COMMENT '秒杀开始时间' AFTER `use_deadline`, ADD `exchange_price` TINYINT NULL DEFAULT '0' COMMENT '兑换所需的金币数' AFTER `pick_start_time`;
ALTER TABLE `product_coupon` CHANGE `end_time` `pick_end_time` DATETIME NULL COMMENT '秒杀截止时间';
ALTER TABLE `product_coupon` CHANGE `total` `total` INT(11) NOT NULL DEFAULT '0' COMMENT '总数量', CHANGE `reserved` `reserved` INT(11) NOT NULL DEFAULT '0' COMMENT '剩余数量', CHANGE `exchange_deadline` `exchange_deadline` DATETIME NULL COMMENT '兑换截止时间', CHANGE `use_deadline` `use_deadline` DATETIME NULL COMMENT '使用截止时间';

--
-- 20170906
--
--
-- 修改原因：价格类型的decimal都没有规定小数位数，改为decimal(10,2);
-- product_coupon的status改为和其他table一致的定义方式：免费卷状态： 0：无效 1：有效; 
-- product_coupon 的type由boolean改为int
-- user_blacklist的reason由boolean改为int;reason,remove_time可以为null
--
--
ALTER TABLE `product_info` CHANGE `price` `price` DECIMAL(10,2) NOT NULL COMMENT '价格', CHANGE `discount` `discount` DECIMAL(10,2) NULL DEFAULT NULL COMMENT '折扣价格';
ALTER TABLE `product_coupon` CHANGE `status` `status` TINYINT(1) NOT NULL DEFAULT '0' COMMENT '免费卷状态： 0：无效 1：有效';
ALTER TABLE `product_coupon` CHANGE `type` `type` TINYINT(4) NULL DEFAULT NULL COMMENT '免费卷类型';
ALTER TABLE `user_blacklist` CHANGE `reason` `reason` TINYINT(4) NULL DEFAULT '0' COMMENT '加入黑名单原因 0： 1：', CHANGE `remove_time` `remove_time` DATETIME NULL COMMENT '移出黑名单时间';
--
-- 20170910
--
--
-- 修改原因：status应该是int，不是boolean
-- 
ALTER TABLE `user_coupon` CHANGE `status` `status` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '免费卷状态： 0：未使用 1：已使用';


--
-- 20171015
--
--
-- 修改原因：免费卷状态修改定义：0：未使用 1：已使用 2: 处理中 3: 已过期；
-- 添加一个field abstract,用于在我的优惠券列表中展示 使用说明摘要(如需要满足什么条件).
-- 
ALTER TABLE `user_coupon` CHANGE `status` `status` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '免费卷状态： 0：未使用 1：已使用  2：处理中 3：已过期';
ALTER TABLE `product_coupon` ADD `abstract` VARCHAR(50) NOT NULL DEFAULT '500元以下单价商品,在本平台消费后凭券返还实际费用' COMMENT '使用说明摘要' AFTER `title`;

--
-- 20171017
--
--
-- 修改原因：修改image_info的tiny_int(1)到tiny_int(4)；
-- 
ALTER TABLE `image_info` CHANGE `order_num` `order_num` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '序号', CHANGE `main_page` `main_page` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '是否是主图 0：不是主图 1：是主图', CHANGE `type` `type` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '类型： 0：游记 1：商品 2：专题 3：商户 4：免费资格 5：信任度';

