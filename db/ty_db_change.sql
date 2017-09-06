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
-- 修改原因：价格类型的decimal都没有规定小数位数，改为decimal(10,2)
--
ALTER TABLE `product_info` CHANGE `price` `price` DECIMAL(10,2) NOT NULL COMMENT '价格', CHANGE `discount` `discount` DECIMAL(10,2) NULL DEFAULT NULL COMMENT '折扣价格';

