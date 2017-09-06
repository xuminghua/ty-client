-- phpMyAdmin SQL Dump
-- version 4.6.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: 2017-08-23 02:32:40
-- 服务器版本： 5.7.14
-- PHP Version: 5.6.25

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `ty_db`
--

-- --------------------------------------------------------

--
-- 表的结构 `common_carrousel`
--

DROP TABLE IF EXISTS `common_carrousel`;
CREATE TABLE `common_carrousel` (
  `id` int(11) NOT NULL COMMENT '主键（自增）',
  `object_id` bigint(20) NOT NULL COMMENT '游记/商品/专题/免费资格/信任度信息 id',
  `image_id` bigint(20) NOT NULL COMMENT '图片id',
  `title` varchar(255) NOT NULL COMMENT '标题',
  `memo` varchar(500) DEFAULT NULL COMMENT '描述',
  `type` tinyint(1) NOT NULL DEFAULT '0' COMMENT '类型： 0：游记 1：商品 2：专题 3：免费资格 4：信息度',
  `status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '状态： 0：无效 1：有效',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `tmp_intvalue` int(11) DEFAULT NULL COMMENT '备用',
  `tmp1_charvalue` varchar(255) DEFAULT NULL COMMENT '备用',
  `tmp2_charvalue` varchar(255) DEFAULT NULL COMMENT '备用'
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- 表的结构 `common_comment`
--

DROP TABLE IF EXISTS `common_comment`;
CREATE TABLE `common_comment` (
  `id` bigint(20) NOT NULL COMMENT '主键（自增）',
  `content` varchar(1000) NOT NULL COMMENT '反馈信息内容',
  `reply` varchar(1000) DEFAULT NULL COMMENT '回复信息内容',
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `reply_user_id` bigint(20) DEFAULT NULL COMMENT '回复用户id',
  `reply_time` datetime DEFAULT NULL COMMENT '回复时间',
  `link` varchar(255) DEFAULT NULL COMMENT '产品链接',
  `status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '状态 0：待处理 1：处理中 2：处理完成',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `tmp_intvalue` int(11) DEFAULT NULL COMMENT '备用',
  `tmp1_charvalue` varchar(255) DEFAULT NULL COMMENT '备用',
  `tmp2_charvalue` varchar(255) DEFAULT NULL COMMENT '备用'
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- 表的结构 `common_rolling`
--

DROP TABLE IF EXISTS `common_rolling`;
CREATE TABLE `common_rolling` (
  `id` bigint(20) NOT NULL COMMENT '主键（自增）',
  `content` varchar(500) DEFAULT NULL COMMENT '播报内容',
  `object_id` bigint(20) DEFAULT NULL COMMENT '游记/商品/免费卷id',
  `type` tinyint(1) NOT NULL DEFAULT '0' COMMENT '类型 0：游记 1：商品 2：免费卷',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `tmp_intvalue` int(11) DEFAULT NULL COMMENT '备用',
  `tmp1_charvalue` varchar(255) DEFAULT NULL COMMENT '备用',
  `tmp2_charvalue` varchar(255) DEFAULT NULL COMMENT '备用'
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- 表的结构 `common_trust`
--

DROP TABLE IF EXISTS `common_trust`;
CREATE TABLE `common_trust` (
  `id` int(11) NOT NULL COMMENT '主键（自增',
  `uuid` varchar(36) NOT NULL COMMENT '唯一id',
  `img_id` bigint(20) NOT NULL COMMENT '图片id',
  `content` varchar(500) DEFAULT NULL COMMENT '内容',
  `status` tinyint(1) NOT NULL DEFAULT '1' COMMENT '状态： 0：无效 1：有效',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `tmp_intvalue` int(11) DEFAULT NULL COMMENT '备用',
  `tmp1_charvalue` varchar(255) DEFAULT NULL COMMENT '备用',
  `tmp2_charvalue` varchar(255) DEFAULT NULL COMMENT '备用'
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- 表的结构 `image_info`
--

DROP TABLE IF EXISTS `image_info`;
CREATE TABLE `image_info` (
  `id` bigint(20) NOT NULL COMMENT '主键（自增）',
  `object_id` bigint(20) NOT NULL COMMENT '(游记，商品，专题，商户）id',
  `image_url` varchar(255) NOT NULL COMMENT '图片地址',
  `type` tinyint(1) NOT NULL DEFAULT '0' COMMENT '类型： 0：游记 1：商品 2：专题 3：商户 4：免费资格 5：信任度',
  `order_num` tinyint(1) NOT NULL DEFAULT '0' COMMENT '序号',
  `main_page` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否是主图 0：不是主图 1：是主图',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `tmp_intvalue` int(11) DEFAULT NULL COMMENT '备用',
  `tmp1_charvalue` varchar(255) DEFAULT NULL COMMENT '备用',
  `tmp2_charvalue` varchar(255) DEFAULT NULL COMMENT '备用'
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- 转存表中的数据 `image_info`
--

INSERT INTO `image_info` (`id`, `object_id`, `image_url`, `type`, `order_num`, `main_page`, `create_time`, `tmp_intvalue`, `tmp1_charvalue`, `tmp2_charvalue`) VALUES
(1, 1, '1', 0, 0, 1, '2017-07-14 00:00:00', NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- 表的结构 `merchant_info`
--

DROP TABLE IF EXISTS `merchant_info`;
CREATE TABLE `merchant_info` (
  `id` bigint(20) NOT NULL COMMENT '主键（自增',
  `name` varchar(255) NOT NULL COMMENT '商户名称',
  `address` varchar(255) DEFAULT NULL COMMENT '商户地址',
  `address_point` varchar(255) DEFAULT NULL COMMENT '经纬度地址信息',
  `city` varchar(30) DEFAULT NULL COMMENT '城市',
  `info` text COMMENT '商户描述信息',
  `phone` varchar(20) DEFAULT NULL COMMENT '内部联系电话',
  `service_phone` varchar(20) DEFAULT NULL COMMENT '客服电话',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `tmp_intvalue` int(11) DEFAULT NULL COMMENT '备用',
  `tmp1_charvalue` varchar(255) DEFAULT NULL COMMENT '备用',
  `tmp2_charvalue` int(255) DEFAULT NULL COMMENT '备用'
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- 表的结构 `merchant_user`
--

DROP TABLE IF EXISTS `merchant_user`;
CREATE TABLE `merchant_user` (
  `id` bigint(20) NOT NULL COMMENT '主键（自增）',
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `user_name` varchar(255) DEFAULT NULL COMMENT '用户名称',
  `phone` varchar(30) DEFAULT NULL COMMENT '手机号',
  `password` varchar(255) DEFAULT NULL COMMENT '密码',
  `merchant_id` bigint(20) NOT NULL COMMENT '商户id',
  `latest_login_time` datetime DEFAULT NULL COMMENT '最后一次登录时间',
  `create_time` datetime NOT NULL COMMENT '创建用户时间',
  `tmp_intvalue` int(11) DEFAULT NULL COMMENT '备用',
  `tmp1_charvalue` varchar(255) DEFAULT NULL COMMENT '备用',
  `tmp2_charvalue` varchar(255) DEFAULT NULL COMMENT '备用'
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- 表的结构 `product_comment`
--

DROP TABLE IF EXISTS `product_comment`;
CREATE TABLE `product_comment` (
  `id` bigint(20) NOT NULL COMMENT '主键（自增）',
  `object_id` bigint(20) NOT NULL COMMENT '游记ID（商品ID）',
  `type` tinyint(1) NOT NULL DEFAULT '0' COMMENT '类型： 0：游记 1：商品',
  `comment` varchar(400) DEFAULT NULL COMMENT '评论内容',
  `author_id` bigint(20) NOT NULL COMMENT '发布评论的作者ID',
  `attitude` tinyint(1) NOT NULL COMMENT '0：踩 1：赞',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `tmp_intvalue` int(11) DEFAULT NULL COMMENT '备用',
  `tmp1_charvalue` varchar(255) DEFAULT NULL COMMENT '备用',
  `tmp2_charvalue` varchar(255) DEFAULT NULL COMMENT '备用'
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- 表的结构 `product_coupon`
--

DROP TABLE IF EXISTS `product_coupon`;
CREATE TABLE `product_coupon` (
  `id` bigint(20) NOT NULL COMMENT '主键（自增）',
  `uuid` varchar(36) NOT NULL COMMENT '唯一id',
  `title` varchar(255) NOT NULL COMMENT '标题',
  `type` tinyint(1) DEFAULT NULL COMMENT '免费卷类型',
  `info` varchar(1000) DEFAULT NULL COMMENT '描述信息',
  `product_id` bigint(20) DEFAULT NULL COMMENT '商品ID(为空则不限商量)',
  `total` int(11) NOT NULL COMMENT '总数量',
  `reserved` int(11) NOT NULL COMMENT '剩余数量',
  `end_time` datetime NOT NULL COMMENT '秒杀截止时间',
  `exchange_deadline` datetime NOT NULL COMMENT '兑换截止时间',
  `use_deadline` datetime NOT NULL COMMENT '使用截止时间',
  `status` tinyint(1) NOT NULL DEFAULT '1' COMMENT '免费卷状态： 0：有效 1：无效',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `tmp_intvalue` int(11) DEFAULT NULL COMMENT '备用',
  `tmp1_charvalue` varchar(255) DEFAULT NULL COMMENT '备用',
  `tmp2_charvalue` varchar(255) DEFAULT NULL COMMENT '备用'
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- 表的结构 `product_info`
--

DROP TABLE IF EXISTS `product_info`;
CREATE TABLE `product_info` (
  `id` bigint(20) NOT NULL COMMENT '主键（自增）',
  `uuid` varchar(36) NOT NULL COMMENT '唯一id',
  `name` varchar(255) NOT NULL COMMENT '商品名称',
  `title` varchar(255) DEFAULT NULL COMMENT '简要标题',
  `content` text COMMENT '商品描述信息',
  `merchant_id` bigint(20) DEFAULT NULL COMMENT '商户id',
  `price` decimal(10,0) NOT NULL COMMENT '价格',
  `discount` decimal(10,0) DEFAULT NULL COMMENT '折扣价格',
  `is_top` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否置顶： 0：否 1：是',
  `is_self` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否自营： 0：是 1：否',
  `audit_status` tinyint(1) NOT NULL COMMENT '审核状态: 0：待审核 1：审核通过 2：审核不通过',
  `ages` varchar(10) DEFAULT NULL COMMENT '1-1或3-10',
  `link` varchar(255) DEFAULT NULL COMMENT '链接',
  `likes` bigint(20) NOT NULL DEFAULT '0' COMMENT '点赞量',
  `views` bigint(20) NOT NULL DEFAULT '0',
  `address_point` varchar(255) DEFAULT NULL COMMENT '经纬度地址信息',
  `address` varchar(255) DEFAULT NULL COMMENT '地址信息',
  `city` varchar(30) DEFAULT NULL COMMENT '城市',
  `type` tinyint(1) NOT NULL DEFAULT '0' COMMENT '商品类型： 0：用户上传 1：自有 2:第三方',
  `status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '商品状态： 0：正常 1：促销 2：秒杀 3：下架',
  `start_time` datetime DEFAULT NULL COMMENT '活动开始时间',
  `end_time` datetime DEFAULT NULL COMMENT '活动结束时间',
  `expiry_time` datetime DEFAULT NULL COMMENT '下架时间',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `tmp_intvalue` int(11) DEFAULT NULL COMMENT '备用',
  `tmp1_charvalue` varchar(255) DEFAULT NULL COMMENT '备用',
  `tmp2_charvalue` varchar(255) DEFAULT NULL COMMENT '备用'
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- 表的结构 `product_reply`
--

DROP TABLE IF EXISTS `product_reply`;
CREATE TABLE `product_reply` (
  `id` bigint(20) NOT NULL COMMENT '主键（自增）',
  `comment_id` bigint(20) NOT NULL COMMENT '评论id',
  `reply` varchar(400) NOT NULL COMMENT '回复内容',
  `author_id` bigint(20) NOT NULL COMMENT '发布评论的帐户id',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `tmp_intvalue` int(11) DEFAULT NULL COMMENT '备用',
  `tmp1_charvalue` varchar(255) DEFAULT NULL COMMENT '备用',
  `tmp2_charvalue` varchar(255) DEFAULT NULL COMMENT '备用'
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- 表的结构 `subject_info`
--

DROP TABLE IF EXISTS `subject_info`;
CREATE TABLE `subject_info` (
  `id` bigint(20) NOT NULL COMMENT '主键（自增',
  `uuid` varchar(36) NOT NULL COMMENT '唯一id',
  `name` varchar(255) NOT NULL COMMENT '专题名称',
  `title` varchar(255) DEFAULT NULL COMMENT '简要标题',
  `content` text NOT NULL COMMENT '专题内容',
  `is_top` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否置顶： 0：否 1：是',
  `likes` bigint(20) NOT NULL DEFAULT '0' COMMENT '点赞量',
  `author_id` bigint(20) NOT NULL COMMENT '作者ID(小编)',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `tmp_intvalue` int(11) DEFAULT NULL COMMENT '备用',
  `tmp1_charvalue` varchar(255) DEFAULT NULL COMMENT '备用',
  `tmp2_charvalue` varchar(255) DEFAULT NULL COMMENT '备用'
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- 表的结构 `subject_product`
--

DROP TABLE IF EXISTS `subject_product`;
CREATE TABLE `subject_product` (
  `id` bigint(20) NOT NULL COMMENT '主键（自增）',
  `subject_id` bigint(20) NOT NULL COMMENT '专题id',
  `object_id` bigint(20) NOT NULL COMMENT '产品或游记id',
  `type` tinyint(1) NOT NULL DEFAULT '0' COMMENT '0：',
  `order_num` tinyint(1) NOT NULL DEFAULT '0' COMMENT '序号',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `tmp_intvalue` int(11) DEFAULT NULL COMMENT '备用',
  `tmp1_charvalue` varchar(255) DEFAULT NULL COMMENT '备用',
  `tmp2_charvalue` varchar(255) DEFAULT NULL COMMENT '备用'
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- 表的结构 `travel_info`
--

DROP TABLE IF EXISTS `travel_info`;
CREATE TABLE `travel_info` (
  `id` bigint(20) NOT NULL COMMENT '主键（自增）',
  `uuid` varchar(36) NOT NULL COMMENT '唯一id',
  `title` varchar(255) NOT NULL COMMENT '标题',
  `product_id` bigint(20) DEFAULT NULL COMMENT '商品ID',
  `content` text COMMENT '游记内容',
  `author_id` bigint(20) NOT NULL COMMENT '作者id',
  `views` bigint(20) NOT NULL DEFAULT '0' COMMENT '浏览量',
  `likes` bigint(20) NOT NULL DEFAULT '0' COMMENT '点赞量',
  `type` tinyint(1) NOT NULL DEFAULT '1' COMMENT '游记类型： 0：私人 1：公开',
  `status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '游记状态： 0：草稿 1：待审核 2：审核通过',
  `is_top` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否置顶： 0：否 1：是',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `tmp_intvalue` int(11) DEFAULT NULL COMMENT '备用',
  `tmp1_charvalue` varchar(255) DEFAULT NULL COMMENT '备用',
  `tmp2_charvalue` varchar(255) DEFAULT NULL COMMENT '备用'
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- 转存表中的数据 `travel_info`
--

INSERT INTO `travel_info` (`id`, `uuid`, `title`, `product_id`, `content`, `author_id`, `views`, `likes`, `type`, `status`, `is_top`, `create_time`, `tmp_intvalue`, `tmp1_charvalue`, `tmp2_charvalue`) VALUES
(1, '', '1', 1, '1', 2, 1, 3, 1, 2, 1, '2017-07-07 00:00:00', 1, '11', NULL),
(2, '', '2', 2, '2', 2, 2, 2, 1, 2, 2, '2017-07-07 00:00:00', 2, NULL, NULL);

-- --------------------------------------------------------

--
-- 表的结构 `travel_question`
--

DROP TABLE IF EXISTS `travel_question`;
CREATE TABLE `travel_question` (
  `id` bigint(20) NOT NULL COMMENT '主键（自增）',
  `travel_id` bigint(20) NOT NULL COMMENT '游记id',
  `ask_user_id` bigint(20) NOT NULL COMMENT '提问用户id',
  `question` varchar(500) NOT NULL COMMENT '问题',
  `answer` varchar(500) DEFAULT NULL COMMENT '回答',
  `answer_user_id` bigint(20) DEFAULT NULL COMMENT '回答用户id',
  `anser_time` datetime DEFAULT NULL COMMENT '回答时间',
  `create_time` datetime NOT NULL COMMENT '创建时间即提问时间',
  `status` smallint(6) NOT NULL COMMENT '状态： 0：未回答 1：已回答',
  `tmp_intvalue` int(11) DEFAULT NULL COMMENT '备用',
  `tmp1_charvalue` int(11) DEFAULT NULL COMMENT '备用',
  `tmp2_charvalue` int(11) DEFAULT NULL COMMENT '备用'
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- 表的结构 `user_blacklist`
--

DROP TABLE IF EXISTS `user_blacklist`;
CREATE TABLE `user_blacklist` (
  `id` bigint(20) NOT NULL COMMENT '主键（自增）',
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `reason` tinyint(1) NOT NULL COMMENT '加入黑名单原因 0： 1：',
  `status` tinyint(1) NOT NULL DEFAULT '1' COMMENT '状态： 0：无效 1：有效',
  `remove_time` datetime NOT NULL COMMENT '移出黑名单时间',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `tmp_intvalue` int(11) DEFAULT NULL COMMENT '备用',
  `tmp1_charvalue` varchar(255) DEFAULT NULL COMMENT '备用',
  `tmp2_charvalue` varchar(255) DEFAULT NULL COMMENT '备用'
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- 表的结构 `user_coupon`
--

DROP TABLE IF EXISTS `user_coupon`;
CREATE TABLE `user_coupon` (
  `id` bigint(20) NOT NULL COMMENT '主键（自增）',
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `coupon_id` bigint(20) NOT NULL COMMENT '免费卷id',
  `product_id` bigint(20) DEFAULT NULL COMMENT '商品ID(为空则不限商量)',
  `exchange_deadline` datetime NOT NULL COMMENT '兑换截止时间',
  `use_deadline` datetime NOT NULL COMMENT '使用截止时间',
  `status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '免费卷状态： 0：未使用 1：已使用',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `tmp_intvalue` int(11) DEFAULT NULL COMMENT '备用',
  `tmp1_charvalue` varchar(255) DEFAULT NULL COMMENT '备用',
  `tmp2_charvalue` varchar(255) DEFAULT NULL COMMENT '备用'
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- 表的结构 `user_favorite`
--

DROP TABLE IF EXISTS `user_favorite`;
CREATE TABLE `user_favorite` (
  `id` bigint(20) NOT NULL COMMENT '主键（自增）',
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `object_id` bigint(20) NOT NULL COMMENT '游记或商品ID',
  `type` tinyint(1) NOT NULL DEFAULT '0' COMMENT '类型： 0：游记 1：商品 2：专题',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `tmp_intvalue` int(11) DEFAULT NULL COMMENT '备用',
  `tmp1_charvalue` varchar(255) DEFAULT NULL COMMENT '备用',
  `tmp2_charvalue` varchar(255) DEFAULT NULL COMMENT '备用'
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- 转存表中的数据 `user_favorite`
--

INSERT INTO `user_favorite` (`id`, `user_id`, `object_id`, `type`, `create_time`, `tmp_intvalue`, `tmp1_charvalue`, `tmp2_charvalue`) VALUES
(1, 1, 1, 0, '2017-07-17 17:33:26', NULL, NULL, NULL),
(2, 1, 2, 2, '2017-08-04 15:18:37', NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- 表的结构 `user_friend`
--

DROP TABLE IF EXISTS `user_friend`;
CREATE TABLE `user_friend` (
  `id` int(11) NOT NULL COMMENT '主键(自增)',
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `friend_id` bigint(20) NOT NULL COMMENT '朋友ID',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `tmp_intvalue` int(11) DEFAULT NULL,
  `tmp1_charvalue` varchar(255) DEFAULT NULL,
  `tmp2_charvalue` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- 转存表中的数据 `user_friend`
--

INSERT INTO `user_friend` (`id`, `user_id`, `friend_id`, `create_time`, `tmp_intvalue`, `tmp1_charvalue`, `tmp2_charvalue`) VALUES
(1, 1, 2, '2017-07-17 00:00:00', NULL, NULL, NULL),
(2, 1, 4, '2017-07-17 00:00:00', NULL, NULL, NULL),
(3, 1, 5, '2017-07-17 00:00:00', NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- 表的结构 `user_friend_apply`
--

DROP TABLE IF EXISTS `user_friend_apply`;
CREATE TABLE `user_friend_apply` (
  `id` bigint(20) NOT NULL COMMENT '主键（自增）',
  `user_id` bigint(20) NOT NULL COMMENT '申请用户id',
  `friend_id` bigint(20) NOT NULL COMMENT '被申请用户id',
  `message` varchar(500) DEFAULT NULL COMMENT '申请或驳回信息',
  `status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '状态 0：申请中 1：申请通过 2：拒绝',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `tmp_intvalue` int(11) DEFAULT NULL COMMENT '备用',
  `tmp1_charvalue` varchar(255) DEFAULT NULL COMMENT '备用',
  `tmp2_charvalue` varchar(255) DEFAULT NULL COMMENT '备用'
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- 表的结构 `user_friend_travel`
--

DROP TABLE IF EXISTS `user_friend_travel`;
CREATE TABLE `user_friend_travel` (
  `id` bigint(20) NOT NULL COMMENT '主键（自增）',
  `travel_id` bigint(20) NOT NULL COMMENT '游记id',
  `travel_user_id` bigint(20) NOT NULL COMMENT '游记作者id',
  `friend_id` bigint(20) NOT NULL COMMENT '朋友id',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `tmp_intvalue` int(11) DEFAULT NULL COMMENT '备用',
  `tmp1_charvalue` varchar(255) DEFAULT NULL COMMENT '备用',
  `tmp2_charvalue` varchar(255) DEFAULT NULL COMMENT '备用'
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- 表的结构 `user_info`
--

DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info` (
  `id` bigint(20) NOT NULL COMMENT '主键（自增）',
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `user_name` varchar(255) DEFAULT NULL COMMENT '用户名称',
  `phone` varchar(30) DEFAULT NULL COMMENT '手机号',
  `password` varchar(50) DEFAULT NULL COMMENT '密码',
  `icon` varchar(255) DEFAULT NULL COMMENT '头像url',
  `mail` varchar(50) DEFAULT NULL COMMENT '邮箱地址',
  `baby_gender` varchar(5) DEFAULT NULL COMMENT '性别：male,femal',
  `baby_birth` tinyint(1) DEFAULT NULL COMMENT '年龄',
  `weichat` varchar(255) DEFAULT NULL COMMENT '微信的唯一帐号',
  `weibo` varchar(255) DEFAULT NULL COMMENT '微博唯一帐号',
  `qq` varchar(255) DEFAULT NULL COMMENT 'qq唯一帐号',
  `experience` int(11) DEFAULT NULL COMMENT '经验值',
  `money` int(11) DEFAULT NULL COMMENT '金币',
  `point` int(11) DEFAULT NULL COMMENT '积分',
  `latest_login_time` datetime DEFAULT NULL COMMENT '最后一次登录时间',
  `create_time` datetime NOT NULL COMMENT '用户创建时间',
  `tmp_intvalue` int(11) DEFAULT NULL COMMENT '备用',
  `tmp1_charvalue` varchar(255) DEFAULT NULL COMMENT '备用',
  `tmp2_charvalue` varchar(255) DEFAULT NULL COMMENT '备用'
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- 转存表中的数据 `user_info`
--

INSERT INTO `user_info` (`id`, `user_id`, `user_name`, `phone`, `password`, `icon`, `mail`, `baby_gender`, `baby_birth`, `weichat`, `weibo`, `qq`, `experience`, `money`, `point`, `latest_login_time`, `create_time`, `tmp_intvalue`, `tmp1_charvalue`, `tmp2_charvalue`) VALUES
(1, 1, '1', NULL, '202cb962ac59075b964b07152d234b70', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2017-01-01 00:00:00', NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- 表的结构 `user_message`
--

DROP TABLE IF EXISTS `user_message`;
CREATE TABLE `user_message` (
  `id` bigint(20) NOT NULL COMMENT '主键（自增）',
  `message` varchar(400) NOT NULL COMMENT '消息内容',
  `type` tinyint(1) NOT NULL COMMENT '消息类型： 0：系统消息 1：用户消息等',
  `user_id` bigint(20) DEFAULT NULL COMMENT '接收消息用户ID',
  `status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '消息状态: 0:未发送 1：已发未读 2：已发已读',
  `create_time` datetime NOT NULL COMMENT '消息创建时间',
  `tmp_intvalue` int(11) DEFAULT NULL COMMENT '备用',
  `tmp1_charvalue` varchar(255) DEFAULT NULL COMMENT '备用',
  `tmp2_charvalue` varchar(255) DEFAULT NULL COMMENT '备用'
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `common_carrousel`
--
ALTER TABLE `common_carrousel`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `common_comment`
--
ALTER TABLE `common_comment`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `common_rolling`
--
ALTER TABLE `common_rolling`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `common_trust`
--
ALTER TABLE `common_trust`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `image_info`
--
ALTER TABLE `image_info`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `merchant_info`
--
ALTER TABLE `merchant_info`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `merchant_user`
--
ALTER TABLE `merchant_user`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `product_comment`
--
ALTER TABLE `product_comment`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `product_coupon`
--
ALTER TABLE `product_coupon`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `product_info`
--
ALTER TABLE `product_info`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `product_reply`
--
ALTER TABLE `product_reply`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `subject_info`
--
ALTER TABLE `subject_info`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `subject_product`
--
ALTER TABLE `subject_product`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `travel_info`
--
ALTER TABLE `travel_info`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `travel_question`
--
ALTER TABLE `travel_question`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `user_blacklist`
--
ALTER TABLE `user_blacklist`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `user_coupon`
--
ALTER TABLE `user_coupon`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `user_favorite`
--
ALTER TABLE `user_favorite`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `user_friend`
--
ALTER TABLE `user_friend`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `user_friend_apply`
--
ALTER TABLE `user_friend_apply`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `user_friend_travel`
--
ALTER TABLE `user_friend_travel`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `user_info`
--
ALTER TABLE `user_info`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `user_message`
--
ALTER TABLE `user_message`
  ADD PRIMARY KEY (`id`);

--
-- 在导出的表使用AUTO_INCREMENT
--

--
-- 使用表AUTO_INCREMENT `common_carrousel`
--
ALTER TABLE `common_carrousel`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键（自增）';
--
-- 使用表AUTO_INCREMENT `common_comment`
--
ALTER TABLE `common_comment`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键（自增）';
--
-- 使用表AUTO_INCREMENT `common_rolling`
--
ALTER TABLE `common_rolling`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键（自增）';
--
-- 使用表AUTO_INCREMENT `common_trust`
--
ALTER TABLE `common_trust`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键（自增';
--
-- 使用表AUTO_INCREMENT `image_info`
--
ALTER TABLE `image_info`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键（自增）', AUTO_INCREMENT=2;
--
-- 使用表AUTO_INCREMENT `merchant_info`
--
ALTER TABLE `merchant_info`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键（自增';
--
-- 使用表AUTO_INCREMENT `merchant_user`
--
ALTER TABLE `merchant_user`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键（自增）';
--
-- 使用表AUTO_INCREMENT `product_comment`
--
ALTER TABLE `product_comment`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键（自增）';
--
-- 使用表AUTO_INCREMENT `product_coupon`
--
ALTER TABLE `product_coupon`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键（自增）';
--
-- 使用表AUTO_INCREMENT `product_info`
--
ALTER TABLE `product_info`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键（自增）';
--
-- 使用表AUTO_INCREMENT `product_reply`
--
ALTER TABLE `product_reply`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键（自增）';
--
-- 使用表AUTO_INCREMENT `subject_info`
--
ALTER TABLE `subject_info`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键（自增';
--
-- 使用表AUTO_INCREMENT `subject_product`
--
ALTER TABLE `subject_product`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键（自增）';
--
-- 使用表AUTO_INCREMENT `travel_info`
--
ALTER TABLE `travel_info`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键（自增）', AUTO_INCREMENT=3;
--
-- 使用表AUTO_INCREMENT `travel_question`
--
ALTER TABLE `travel_question`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键（自增）';
--
-- 使用表AUTO_INCREMENT `user_blacklist`
--
ALTER TABLE `user_blacklist`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键（自增）';
--
-- 使用表AUTO_INCREMENT `user_coupon`
--
ALTER TABLE `user_coupon`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键（自增）';
--
-- 使用表AUTO_INCREMENT `user_favorite`
--
ALTER TABLE `user_favorite`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键（自增）', AUTO_INCREMENT=3;
--
-- 使用表AUTO_INCREMENT `user_friend`
--
ALTER TABLE `user_friend`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键(自增)', AUTO_INCREMENT=4;
--
-- 使用表AUTO_INCREMENT `user_friend_apply`
--
ALTER TABLE `user_friend_apply`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键（自增）';
--
-- 使用表AUTO_INCREMENT `user_friend_travel`
--
ALTER TABLE `user_friend_travel`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键（自增）';
--
-- 使用表AUTO_INCREMENT `user_info`
--
ALTER TABLE `user_info`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键（自增）', AUTO_INCREMENT=2;
--
-- 使用表AUTO_INCREMENT `user_message`
--
ALTER TABLE `user_message`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键（自增）';
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
