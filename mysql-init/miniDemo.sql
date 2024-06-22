-- MySQL dump 10.13  Distrib 8.2.0, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: db_xzz_blog
-- ------------------------------------------------------
-- Server version	8.0.33

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `tb_blog_01`
--
create database db_xzz_blog;

use db_xzz_blog;

DROP TABLE IF EXISTS `tb_blog_01`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_blog_01` (
  `id` bigint NOT NULL COMMENT 'id',
  `title` varchar(256) NOT NULL COMMENT '标题',
  `content` longtext NOT NULL COMMENT '内容',
  `summary` varchar(1024) NOT NULL COMMENT '摘要',
  `category_id` bigint NOT NULL COMMENT '分类',
  `thumbnail` varchar(256) NOT NULL COMMENT '缩略图',
  `is_top` char(1) NOT NULL DEFAULT '0' COMMENT '是否置顶0=否1=是',
  `status` char(1) NOT NULL DEFAULT '1' COMMENT '是否草稿0=已发布1=草稿',
  `view_count` bigint NOT NULL DEFAULT '0' COMMENT '浏览量',
  `is_comment` char(1) NOT NULL DEFAULT '1' COMMENT '是否允许评论',
  `create_by` bigint NOT NULL COMMENT '作者',
  `create_time` timestamp NOT NULL COMMENT '创建时间',
  `update_by` bigint NOT NULL COMMENT '更新者',
  `update_time` timestamp NOT NULL COMMENT '更新时间',
  `del_flag` int NOT NULL DEFAULT '0' COMMENT '是否删除0=否',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_blog_01`
--

LOCK TABLES `tb_blog_01` WRITE;
/*!40000 ALTER TABLE `tb_blog_01` DISABLE KEYS */;
INSERT INTO `tb_blog_01` VALUES (1,'如何学习编程','学习编程是一项重要的技能，它可以帮助你解决问题并创造新的东西。无论你是初学者还是有经验的开发人员，都可以从不同的资源中学习编程。','学习编程的重要性和方法',1,'thumbnail1.jpg','0','1',100,'1',1001,'2024-06-03 04:00:00',-1,'2024-06-17 05:11:43',1),(2,'Python入门指南','Python是一种流行的编程语言，易于学习和使用。本指南将帮助您了解Python的基础知识，并开始编写简单的程序。','Python编程入门指南',1,'thumbnail2.jpg','1','0',150,'1',1001,'2024-06-02 02:00:00',-1,'2024-06-17 05:11:41',1),(3,'机器学习实践','机器学习是人工智能的一个重要领域，它涉及让计算机从数据中学习模式并做出决策。本文将介绍机器学习的基本概念和实践应用。','机器学习的基本概念和实践',2,'thumbnail3.jpg','0','0',80,'1',1001,'2024-06-01 07:00:00',-1,'2024-06-17 05:11:38',1),(4,'Web开发入门','Web开发是构建网站和web应用程序的过程。本文将介绍web开发的基本概念和常用技术，帮助您快速入门web开发。','Web开发的基本概念和技术',3,'thumbnail4.jpg','0','0',120,'1',1001,'2024-05-31 01:00:00',-1,'2024-06-17 05:11:36',1),(5,'数据科学入门','数据科学涉及从数据中提取知识和见解，通常涉及数据分析、机器学习和统计学。本文将介绍数据科学的基本概念和常见工具。','数据科学的基本概念和工具',2,'thumbnail5.jpg','1','0',90,'1',1001,'2024-05-30 06:00:00',-1,'2024-06-17 05:11:34',1),(6,'人工智能发展历程','人工智能是模拟人类智能的机器，它涉及学习、推理和自我修复。本文将介绍人工智能的发展历程和未来趋势。','人工智能的发展历程和未来',2,'thumbnail6.jpg','0','0',110,'1',1001,'2024-05-29 03:00:00',-1,'2024-06-17 05:11:32',1),(7,'区块链技术解析','区块链是一种去中心化的分布式账本技术，用于记录交易和资产所有权。本文将介绍区块链的工作原理和应用场景。','区块链技术的原理和应用',3,'thumbnail7.jpg','1','0',130,'1',1001,'2024-05-28 08:00:00',-1,'2024-06-17 05:11:29',1),(8,'云计算入门指南','云计算是通过互联网提供计算服务，包括服务器、存储、数据库、网络等。本文将介绍云计算的基本概念和服务模型。','云计算的基本概念和服务模型',3,'thumbnail8.jpg','0','1',140,'1',1001,'2024-05-27 05:00:00',-1,'2024-06-17 05:11:26',1),(9,'移动应用开发指南','移动应用开发涉及创建应用程序，用于在移动设备上运行。本文将介绍移动应用开发的基本概念和常用技术。','移动应用开发的基本概念和技术',1,'thumbnail9.jpg','0','1',100,'1',1001,'2024-05-26 02:00:00',-1,'2024-06-17 05:11:22',1),(10,'大数据分析入门','大数据分析涉及处理和分析大规模数据集，通常涉及数据挖掘和机器学习技术。本文将介绍大数据分析的基本概念和工具。','大数据分析的基本概念和工具',2,'thumbnail10.jpg','1','0',120,'1',1001,'2024-05-25 07:00:00',1001,'2024-06-14 11:44:30',1),(11,'网络安全基础知识','网络安全涉及保护计算机系统和网络免受未经授权的访问、破坏或更改的威胁。本文将介绍网络安全的基本概念和常见攻击方式。','网络安全的基本概念和攻击方式',3,'thumbnail11.jpg','0','0',131,'1',1001,'2024-05-24 04:00:00',1001,'2024-06-14 11:46:32',1),(12,'人工智能伦理问题','人工智能的发展引发了一系列伦理和社会问题，例如隐私保护、自主权和就业。本文将介绍人工智能发展中的伦理问题和应对方法。','人工智能伦理问题及应对方法',2,'thumbnail12.jpg','1','1',140,'1',1001,'2024-05-23 01:00:00',-1,'2024-06-17 05:11:11',1),(13,'物联网技术解析','物联网是指通过互联网连接各种设备，实现设备间的信息交换和互操作。本文将介绍物联网的技术原理和应用场景。','物联网技术的原理和应用',3,'thumbnail13.jpg','0','0',150,'1',1001,'2024-05-22 06:00:00',-1,'2024-06-17 05:11:07',1),(14,'软件工程实践指南','软件工程涉及对软件的设计、开发、测试和维护，以及与团队合作和项目管理相关的实践。本文将介绍软件工程的实践指南。','软件工程的实践指南',1,'thumbnail14.jpg','1','1',160,'1',1001,'2024-05-21 03:00:00',-1,'2024-06-17 05:11:05',1),(15,'人工智能在医疗中的应用','人工智能技术在医疗领域的应用涉及疾病诊断、药物研发和个性化治疗。本文将介绍人工智能在医疗中的应用案例和前景。','人工智能在医疗中的应用案例和前景',2,'thumbnail15.jpg','0','1',170,'1',1001,'2024-05-20 08:00:00',-1,'2024-06-17 05:11:03',1),(16,'云原生技术解析','云原生技术是一种构建和运行应用程序的方法，它利用云计算的优势来提高应用程序的效率和可靠性。本文将介绍云原生技术的原理和实践。','云原生技术的原理和实践',3,'thumbnail16.jpg','1','1',180,'1',1001,'2024-05-19 05:00:00',-1,'2024-06-17 05:11:01',1),(17,'数据隐私保护指南','随着数据的广泛应用，数据隐私保护变得越来越重要。本文将介绍数据隐私保护的基本原则和常见保护方法。','数据隐私保护的基本原则和方法',2,'thumbnail17.jpg','0','0',190,'1',1001,'2024-05-18 02:00:00',-1,'2024-06-17 05:10:59',1),(18,'量子计算技术解析','量子计算是一种基于量子力学原理的计算技术，它具有在某些特定问题上比传统计算机更高效的潜力。本文将介绍量子计算的原理和应用前景。','量子计算的原理和应用前景',3,'thumbnail18.jpg','1','0',200,'1',1001,'2024-05-17 07:00:00',-1,'2024-06-17 05:10:57',1),(19,'深度学习实践指南','深度学习是机器学习的一个分支，它涉及使用人工神经网络来模拟和学习数据的高级抽象表示。本文将介绍深度学习的实践指南和应用案例。','深度学习的实践指南和应用案例',6,'thumbnail19.jpg','0','0',210,'1',1001,'2024-05-16 04:00:00',-1,'2024-06-17 05:10:54',1),(20,'自然语言处理技术解析','自然语言处理是人工智能的一个重要领域，它涉及让计算机理解、解释和生成人类语言。本文将介绍自然语言处理技术的原理和应用场景。','自然语言处理技术的原理和应用场景',2,'thumbnail20.jpg','1','0',220,'1',1001,'2024-05-15 01:00:00',-1,'2024-06-17 05:10:50',1),(1799754336688001024,'Markdown编辑器Vditor的基本使用以及在Vue3中使用','Vditor是一个Markdown编辑器组件（也支持富文本编辑器），可以嵌入到自己的Web应用中。此Markdown编辑器支持三种,（几乎没有一个Markdown编辑器同时支持这三种模式）：所见即所得WYSIWYG（富文本),即时渲染(类似于Typora),分屏预览,支持在原生JS中使用，同样支持目前主流的前端框架Vue、React、Svelte，还支持TS。','Vditor是一个Markdown编辑器组件（也支持富文本编辑器）',6,'http://','0','1',0,'1',1001,'2024-06-09 10:44:06',-1,'2024-06-17 05:10:47',1),(1799758683786215424,'test','','test',1,'http://','0','1',0,'1',1001,'2024-06-09 11:01:23',-1,'2024-06-17 05:10:44',1),(1799760105680437248,'test','# test\n\n😄\n\n```python\nprint(\'a\')\n```\n\n\n\n','test',1,'http://','0','0',0,'1',1001,'2024-06-09 11:07:02',-1,'2024-06-17 05:10:39',1),(1799782304600678400,'vue3的路由传参query、params以及动态路由传参','# query传参\n\n编程式导航 使用`router.push` 或者 `router.replace` 的时候，改为对象形式新增`query` 必须传入一个对象\n\n```javaScript\nimport { useRouter } from \'vue-router\';\n...\nconst router = useRouter()\nconst toDetail = (item: Item) => {\n    router.push({\n        path: \'/info\',\n        query: item\n    })\n}\n```\n\n* query 传参配置的是 path，而 params 传参配置的是name，且在 params中配置 path 无效\n* query传递的参数会显示在地址栏中，而params传参不会\n* query传参刷新页面数据不会消失，而params传参刷新页面数据回消失\n*   params可以使用动态传参，动态传参的数据会显示在地址栏中，且刷新页面不会消失，因此可以使用动态params传参，根据动态传递参数在传递页面获取数据，以防页面刷新数据消失\n\n\n','很多时候，我',1,'http://','0','0',0,'1',1001,'2024-06-09 12:35:20',-1,'2024-06-17 05:10:37',1),(1800360762653356032,'测试文章','👀️ \n','文章',1,'http://','0','0',0,'1',1800103115475709952,'2024-06-11 02:53:50',-1,'2024-06-17 05:10:35',1),(1802275999358910464,'测试','![屏幕截图 2024-01-21 140549.png](https://gzbucket-1326631670.cos.ap-guangzhou.myqcloud.com/1802275851199315968.png)\n\n','测试',6,'http://','0','0',0,'1',1001,'2024-06-16 09:44:24',-1,'2024-06-17 05:10:32',1),(1802573556253483008,'Rust语言学习路线','Rust 语言是一种高效、可靠的通用高级语言。其高效不仅限于开发效率，它的执行效率也是令人称赞的，是一种少有的兼顾开发效率和执行效率的语言。\n\nRust 语言由 Mozilla 开发，最早发布于 2014 年 9 月。Rust 的编译器是在 MIT License 和 Apache License 2.0 双重协议声明下的免费开源软件。截至目前( 2020 年 1 月)最新的编译器版本是 1.41.0。\n\nRust 官方在线工具: [https://play.rust-lang.org/](https://play.rust-lang.org/)。\n\nRust 系列文章内容由 **Sobin** 收集整理。\n\n---\n\n## Rust语言的特点\n\n* **高性能** - Rust 速度惊人且内存利用率极高。由于没有运行时和垃圾回收，它能够胜任对性能要求特别高的服务，可以在嵌入式设备上运行，还能轻松和其他语言集成。\n* **可靠性** - Rust 丰富的类型系统和所有权模型保证了内存安全和线程安全，让您在编译期就能够消除各种各样的错误。\n* **生产力** - Rust 拥有出色的文档、友好的编译器和清晰的错误提示信息， 还集成了一流的工具 —— 包管理器和构建工具， 智能地自动补全和类型检验的多编辑器支持， 以及自动格式化代码等等。\n\n','rust语言学习路线，跟着路线走，用最短的时间，达到最好的效果',1802572390895476736,'https://gzbucket-1326631670.cos.ap-guangzhou.myqcloud.com/1802573084339757056.png','0','0',0,'1',1001,'2024-06-17 05:26:41',-1,'2024-06-17 08:40:29',0);
/*!40000 ALTER TABLE `tb_blog_01` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_blog_category`
--

DROP TABLE IF EXISTS `tb_blog_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_blog_category` (
  `id` bigint NOT NULL COMMENT 'id',
  `name` varchar(50) NOT NULL COMMENT '分类名',
  `parent_id` bigint NOT NULL DEFAULT '0' COMMENT '父级id 没有所属父级则为0',
  `description` varchar(500) NOT NULL COMMENT '描述',
  `status` char(1) NOT NULL DEFAULT '0' COMMENT '状态0=正常1=禁用',
  `create_by` bigint NOT NULL COMMENT '创建者',
  `create_time` timestamp NOT NULL COMMENT '创建时间',
  `update_by` bigint NOT NULL COMMENT '更新人',
  `update_time` timestamp NOT NULL COMMENT '创建时间',
  `del_flag` int NOT NULL DEFAULT '0' COMMENT '是否删除0=否1=是',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_blog_category`
--

LOCK TABLES `tb_blog_category` WRITE;
/*!40000 ALTER TABLE `tb_blog_category` DISABLE KEYS */;
INSERT INTO `tb_blog_category` VALUES (1,'计算机',0,'计算机相关专业知识','0',1,'2024-06-03 11:30:00',1,'2024-06-03 11:30:00',0),(2,'外语',0,'外国语','0',1,'2024-06-03 11:31:00',1,'2024-06-03 11:31:00',0),(3,'电子科技',0,'电子，微电子，集成电路','0',1,'2024-06-03 11:32:00',1,'2024-06-03 11:32:00',0),(4,'考公',0,'考公考编','0',1,'2024-06-03 11:33:00',1,'2024-06-03 11:33:00',0),(5,'高考',0,'高考','0',1,'2024-06-03 11:34:00',1,'2024-06-03 11:34:00',0),(6,'日语',2,'日语学习','0',1,'2024-06-16 03:04:28',1,'2024-06-16 03:04:37',0),(1802572249052504064,'Java',1,'java框架，技术栈学习，学习路线','0',-1,'2024-06-17 05:21:29',-1,'2024-06-17 05:21:29',0),(1802572390895476736,'Rust',1,'Rust语言\n','0',-1,'2024-06-17 05:22:03',-1,'2024-06-17 05:22:03',0),(1802572501155340288,'英语',2,'考研英语，四六级\n','0',-1,'2024-06-17 05:22:29',-1,'2024-06-17 05:22:29',0);
/*!40000 ALTER TABLE `tb_blog_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_board`
--

DROP TABLE IF EXISTS `tb_board`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_board` (
  `id` bigint NOT NULL COMMENT 'id',
  `content` varchar(1000) NOT NULL COMMENT '内容',
  `create_time` timestamp NOT NULL COMMENT '创建时间',
  `create_by` bigint NOT NULL COMMENT '创建人',
  `update_time` timestamp NOT NULL COMMENT '更新时间',
  `update_by` bigint NOT NULL COMMENT '更新人',
  `del_flag` int NOT NULL DEFAULT '0' COMMENT '是否删除0=否1=是',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_board`
--

LOCK TABLES `tb_board` WRITE;
/*!40000 ALTER TABLE `tb_board` DISABLE KEYS */;
INSERT INTO `tb_board` VALUES (1,'关于公司年会的通知','2024-06-01 01:00:00',1001,'2024-06-01 01:00:00',1001,0),(2,'新产品发布计划','2024-06-02 02:00:00',1002,'2024-06-02 02:00:00',1002,0),(3,'项目进度更新','2024-06-03 03:00:00',1003,'2024-06-04 04:00:00',1004,0),(4,'员工培训安排','2024-06-05 06:00:00',1001,'2024-06-06 07:00:00',1005,0),(5,'办公设备维护通知','2024-06-07 08:00:00',1002,'2024-06-07 08:00:00',1002,1),(1802568873766064128,'网站上线','2024-06-17 05:08:05',-1,'2024-06-17 05:08:05',-1,0),(1802568974345474048,'网站上线','2024-06-17 05:08:28',-1,'2024-06-17 05:08:28',-1,0),(1802569132957274112,'网站上线','2024-06-17 05:09:06',-1,'2024-06-17 05:09:06',-1,0);
/*!40000 ALTER TABLE `tb_board` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_comment`
--

DROP TABLE IF EXISTS `tb_comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_comment` (
  `id` bigint NOT NULL COMMENT 'id',
  `type` char(1) NOT NULL COMMENT '类型：博客评论，友链评论',
  `article_id` bigint NOT NULL COMMENT '所属博客',
  `root_id` bigint NOT NULL DEFAULT '-1' COMMENT '区分是否为根评论',
  `content` varchar(1000) NOT NULL COMMENT '评论内容',
  `to_comment_user_id` bigint NOT NULL DEFAULT (-(1)) COMMENT '所属评论的用户，回复的目标用户',
  `to_comment_id` bigint NOT NULL DEFAULT (-(1)) COMMENT '所回复的评论',
  `create_by` bigint NOT NULL COMMENT '创建者',
  `create_time` timestamp NOT NULL COMMENT '创建时间',
  `update_by` bigint NOT NULL COMMENT '修改人',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '修改时间',
  `del_flag` int NOT NULL DEFAULT '0' COMMENT '是否删除0=否1=是',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_comment`
--

LOCK TABLES `tb_comment` WRITE;
/*!40000 ALTER TABLE `tb_comment` DISABLE KEYS */;
INSERT INTO `tb_comment` VALUES (10010,'0',1,-1,'test',-1,-1,1001,'2024-06-05 10:50:51',1001,'2024-06-05 10:54:43',0),(10011,'0',1,10010,'你好',1001,10010,1001,'2024-06-05 10:58:46',1001,'2024-06-05 10:58:58',0),(1798344898991104000,'0',1,-1,'写得真好',-1,-1,1001,'2024-06-05 13:23:30',1001,'2024-06-05 13:23:30',0),(1798360702373658624,'0',1,-1,'说得对',-1,-1,1001,'2024-06-05 14:26:20',1001,'2024-06-05 14:26:20',0),(1800331579508133888,'0',1,-1,'真的是这样吗\n',-1,-1,1800103115475709952,'2024-06-11 00:58:09',1800103115475709952,'2024-06-11 00:58:09',0),(1800332841041530880,'0',1,-1,'确实',-1,-1,1800103115475709952,'2024-06-11 01:02:53',1800103115475709952,'2024-06-11 01:02:53',0),(1800332970020573184,'0',1,-1,'测试评论',-1,-1,1800103115475709952,'2024-06-11 01:03:23',1800103115475709952,'2024-06-11 01:03:23',0),(1800333032515702784,'0',1,-1,'你好',-1,-1,1800103115475709952,'2024-06-11 01:03:38',1800103115475709952,'2024-06-11 01:03:38',0),(1800333811486035968,'0',1,-1,'评论',-1,-1,1800103115475709952,'2024-06-11 01:06:44',1800103115475709952,'2024-06-11 01:06:44',0),(1800386409471676416,'0',10,-1,'还行',-1,-1,1800103115475709952,'2024-06-11 04:35:44',1001,'2024-06-15 12:36:15',1),(1800386468875603968,'0',10,-1,'逆天',-1,-1,1800103115475709952,'2024-06-11 04:35:58',1800103115475709952,'2024-06-11 04:35:58',0),(1801986050822844416,'0',1799782304600678400,-1,'谢谢，帮大忙了',-1,-1,1800103115475709952,'2024-06-15 14:32:08',1800103115475709952,'2024-06-15 14:32:08',0),(1802216475663130624,'0',19,-1,'评论',-1,-1,1001,'2024-06-16 05:47:46',1001,'2024-06-16 05:47:46',0),(1802315479327526912,'0',1799760105680437248,-1,'评论',-1,-1,1001,'2024-06-16 12:21:10',1001,'2024-06-16 12:21:10',0),(1802320258023903232,'0',2,-1,'你好',-1,-1,1001,'2024-06-16 12:40:10',1001,'2024-06-16 12:40:10',0),(1802323399603777536,'0',1799782304600678400,-1,'你好',-1,-1,1001,'2024-06-16 12:52:39',1001,'2024-06-16 12:52:39',0),(1802623332206546944,'0',1802573556253483008,-1,'评论',-1,-1,1001,'2024-06-17 08:44:28',1001,'2024-06-17 08:44:28',0),(1802983766323486720,'0',1802573556253483008,-1,'测试',-1,-1,1800103115475709952,'2024-06-18 08:36:42',1800103115475709952,'2024-06-18 08:36:42',0);
/*!40000 ALTER TABLE `tb_comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_fans`
--

DROP TABLE IF EXISTS `tb_fans`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_fans` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_id` bigint NOT NULL COMMENT '用户id',
  `follower_id` bigint NOT NULL COMMENT '粉丝id',
  `create_time` timestamp NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_fans`
--

LOCK TABLES `tb_fans` WRITE;
/*!40000 ALTER TABLE `tb_fans` DISABLE KEYS */;
INSERT INTO `tb_fans` VALUES (9,1001,1800103115475709952,'2024-06-18 08:36:48');
/*!40000 ALTER TABLE `tb_fans` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_follow`
--

DROP TABLE IF EXISTS `tb_follow`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_follow` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_id` bigint NOT NULL COMMENT '用户id',
  `followed_id` bigint NOT NULL COMMENT '已关注的用户',
  `create_time` timestamp NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_follow`
--

LOCK TABLES `tb_follow` WRITE;
/*!40000 ALTER TABLE `tb_follow` DISABLE KEYS */;
INSERT INTO `tb_follow` VALUES (9,1800103115475709952,1001,'2024-06-18 08:36:48');
/*!40000 ALTER TABLE `tb_follow` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_link`
--

DROP TABLE IF EXISTS `tb_link`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_link` (
  `id` bigint NOT NULL COMMENT 'id',
  `name` varchar(256) NOT NULL COMMENT '名称',
  `logo` varchar(256) NOT NULL COMMENT '图标',
  `description` varchar(512) NOT NULL COMMENT '描述',
  `address` varchar(128) NOT NULL COMMENT '网站连接',
  `status` char(1) NOT NULL DEFAULT '2' COMMENT '状态0=通过1=不通过2=待审核',
  `create_by` bigint NOT NULL COMMENT '创建者·1',
  `create_time` timestamp NOT NULL COMMENT '创建时间',
  `update_by` bigint NOT NULL COMMENT '更新人',
  `update_time` timestamp NOT NULL COMMENT '更新时间',
  `del_flag` int NOT NULL DEFAULT '0' COMMENT '删除标志0=未删除1=已删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_link`
--

LOCK TABLES `tb_link` WRITE;
/*!40000 ALTER TABLE `tb_link` DISABLE KEYS */;
INSERT INTO `tb_link` VALUES (1,'博客园','https://example.com/technews_logo.png','Your source for the latest technology news','https://www.cnblogs.com/','0',1,'2024-06-03 11:30:00',1,'2024-06-03 11:30:00',1),(2,'博客园','https://example.com/codemaster_logo.png','Learn to code with our tutorials and resources','https://www.cnblogs.com/','0',1,'2024-06-03 11:31:00',1,'2024-06-03 11:31:00',0),(3,'掘金','https://example.com/healthyliving_logo.png','Tips for a healthy and balanced lifestyle','https://juejin.cn/','0',1,'2024-06-03 11:32:00',1,'2024-06-03 11:32:00',0);
/*!40000 ALTER TABLE `tb_link` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_menu`
--

DROP TABLE IF EXISTS `tb_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_menu` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'id',
  `menu_name` varchar(50) NOT NULL COMMENT '菜单名称',
  `parent_id` int NOT NULL DEFAULT '0' COMMENT '父级菜单，顶级菜单此字段为0',
  `order_num` int NOT NULL DEFAULT '0' COMMENT '优先级',
  `path` varchar(200) NOT NULL DEFAULT '' COMMENT '路由url',
  `component` varchar(255) NOT NULL COMMENT '组件路径',
  `is_frame` int NOT NULL DEFAULT '1' COMMENT '是否为内置',
  `menu_type` char(1) NOT NULL DEFAULT '' COMMENT '菜单类型：D目录,M菜单',
  `visible` char(1) NOT NULL DEFAULT '1' COMMENT '是否显示：0否1是',
  `status` char(1) NOT NULL DEFAULT '0' COMMENT '是否停用：0否1是',
  `perms` varchar(100) NOT NULL COMMENT '对应的接口地址',
  `icon` varchar(100) NOT NULL COMMENT '菜单图标',
  `create_by` bigint NOT NULL COMMENT '创建者',
  `create_time` timestamp NOT NULL COMMENT '创建时间',
  `update_by` bigint NOT NULL COMMENT '更新人',
  `update_time` timestamp NOT NULL COMMENT '更新时间',
  `del_flag` int NOT NULL DEFAULT '0' COMMENT '是否删除',
  `remark` varchar(500) NOT NULL DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_menu`
--

LOCK TABLES `tb_menu` WRITE;
/*!40000 ALTER TABLE `tb_menu` DISABLE KEYS */;
INSERT INTO `tb_menu` VALUES (1,'文章列表',0,1,'/article','../Article.vue',1,'M','1','0','/blog-service/article/articleList','menu',1001,'2024-06-13 03:56:06',1001,'2024-06-13 03:56:16',0,'菜单');
/*!40000 ALTER TABLE `tb_menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_role`
--

DROP TABLE IF EXISTS `tb_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_role` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'id',
  `role_name` varchar(20) NOT NULL COMMENT '角色名',
  `role_key` varchar(20) NOT NULL COMMENT '角色名',
  `role_sort` int NOT NULL COMMENT '分类',
  `status` char(1) NOT NULL DEFAULT '0' COMMENT '是否封禁',
  `del_flag` int NOT NULL DEFAULT '0' COMMENT '是否删除',
  `create_by` bigint NOT NULL COMMENT '创建者',
  `create_time` timestamp NOT NULL COMMENT '创建时间',
  `update_by` bigint NOT NULL COMMENT '更新人',
  `update_time` timestamp NOT NULL COMMENT '更新时间',
  `remark` varchar(50) NOT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_role`
--

LOCK TABLES `tb_role` WRITE;
/*!40000 ALTER TABLE `tb_role` DISABLE KEYS */;
INSERT INTO `tb_role` VALUES (1,'管理员','admin',1,'0',0,1001,'2024-06-13 03:58:01',1001,'2024-06-13 03:58:11','管理员');
/*!40000 ALTER TABLE `tb_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_role_menu`
--

DROP TABLE IF EXISTS `tb_role_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_role_menu` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'id',
  `role_id` int NOT NULL COMMENT '角色',
  `menu_id` int NOT NULL COMMENT '菜单',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_role_menu`
--

LOCK TABLES `tb_role_menu` WRITE;
/*!40000 ALTER TABLE `tb_role_menu` DISABLE KEYS */;
INSERT INTO `tb_role_menu` VALUES (1,1,1);
/*!40000 ALTER TABLE `tb_role_menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_sys_user`
--

DROP TABLE IF EXISTS `tb_sys_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_sys_user` (
  `id` bigint NOT NULL COMMENT 'id',
  `username` varchar(64) NOT NULL COMMENT '用户名',
  `nickname` varchar(64) NOT NULL COMMENT '昵称',
  `password` varchar(300) NOT NULL COMMENT '密码',
  `type` char(1) NOT NULL COMMENT '类型：0=普通用户1=管理员',
  `status` char(1) NOT NULL DEFAULT '0' COMMENT '状态：0=正常1=封禁',
  `email` varchar(64) NOT NULL COMMENT '邮箱',
  `phone` varchar(32) NOT NULL COMMENT '手机号',
  `gender` char(1) NOT NULL DEFAULT '0' COMMENT '性别：0=男1=女',
  `avatar` varchar(128) NOT NULL DEFAULT '' COMMENT '头像',
  `create_time` timestamp NOT NULL COMMENT '创建时间',
  `create_by` bigint NOT NULL COMMENT '创建人',
  `update_time` timestamp NOT NULL COMMENT '更新时间',
  `update_by` bigint NOT NULL COMMENT '更新人',
  `del_flag` int NOT NULL DEFAULT '0' COMMENT '0=未删除1=已删除',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`),
  UNIQUE KEY `nickname` (`nickname`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_sys_user`
--

LOCK TABLES `tb_sys_user` WRITE;
/*!40000 ALTER TABLE `tb_sys_user` DISABLE KEYS */;
INSERT INTO `tb_sys_user` VALUES (1001,'test123!','test','$2a$10$CFM6CAjNoNq/QdT9KOkVIeArAJqELjDAmVcqeWqoKkEsl3d9Uy8uu','1','0','1@qq.com','123456','','https://gzbucket-1326631670.cos.ap-guangzhou.myqcloud.com/1802352093051236352.jpg','2024-06-04 14:46:21',1001,'2024-06-16 14:46:49',1001,0),(1798910586432638976,'test123','test123','$2a$10$lfKBNZOa9W8xRm1e3cF3TuyCh90wl4muZHliYCuVMQzUrw2tHRo/.','0','0','270569619@qq.com','18718763656','0','','2024-06-07 02:51:21',-1,'2024-06-07 02:51:21',-1,0),(1800103115475709952,'admin123','admin123','$2a$10$Wt6IJ4UFtEUynnjEO8TQ9OwlIfYrLIXuYBuO64.h9E7.kTa.8Fj56','0','0','167091682@qq.com','12378945600','0','','2024-06-10 09:50:02',-1,'2024-06-10 09:50:02',-1,0),(1801450419080159232,'test123456','test123456','123456','0','0','1629@qq','12345678900','0','','2024-06-14 03:03:44',1001,'2024-06-14 03:03:44',1001,0),(1801451205524742144,'test12345689','test12345689','12345689','0','0','1629@qq.com','12345678901','0','','2024-06-14 03:06:51',1001,'2024-06-14 03:06:51',1001,0),(1801451568373981184,'1234567','1234567','1234567','0','0','123@qq.com','12345678902','0','','2024-06-14 03:08:18',1001,'2024-06-14 03:08:18',1001,1),(1803242682106019840,'雪乃sama','雪乃sama','$2a$10$saOTpTU1he9eTjO4ee6kuuFvGgEDIppMWa0dXjp/KAVrkDmeXWDDS','0','0','1670916827@qq.com','未知','0','','2024-06-19 01:45:33',-1,'2024-06-19 01:45:33',-1,0);
/*!40000 ALTER TABLE `tb_sys_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_tip`
--

DROP TABLE IF EXISTS `tb_tip`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_tip` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'id',
  `content` varchar(500) NOT NULL COMMENT '内容',
  `author` varchar(20) NOT NULL COMMENT '作者',
  `del_flag` int NOT NULL COMMENT '逻辑删除字段0=未删除1=已删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_tip`
--

LOCK TABLES `tb_tip` WRITE;
/*!40000 ALTER TABLE `tb_tip` DISABLE KEYS */;
INSERT INTO `tb_tip` VALUES (1,'少壮不努力，老大徒伤悲。','汉乐府',0),(2,'不积跬步，无以至千里；不积小流，无以成江海。','荀子',0),(3,'学而不思则罔，思而不学则殆。','孔子',0),(4,'有志者事竟成。','范晔',0),(5,'天行健，君子以自强不息。','《易经》',0),(6,'人生得意须尽欢，莫使金樽空对月。','李白',0),(7,'千里之行，始于足下。','老子',0),(8,'己所不欲，勿施于人。','孔子',0),(9,'知己知彼，百战不殆。','孙子',0),(10,'海纳百川，有容乃大；壁立千仞，无欲则刚。','林则徐',0),(11,'穷则独善其身，达则兼济天下。','孟子',0),(12,'明日复明日，明日何其多。','钱鹤滩',0),(13,'路漫漫其修远兮，吾将上下而求索。','屈原',0),(14,'生当作人杰，死亦为鬼雄。','李清照',0),(15,'天生我材必有用，千金散尽还复来。','李白',0),(16,'勿以恶小而为之，勿以善小而不为。','刘备',0),(17,'纸上得来终觉浅，绝知此事要躬行。','陆游',0),(18,'穷且益坚，不坠青云之志。','王勃',0),(19,'学然后知不足，教然后知困。','孔子',0),(20,'读万卷书，行万里路。','刘彝',0),(21,'我们唯一要恐惧的就是恐惧本身。','富兰克林·D·罗斯福',0),(22,'生存还是毁灭，这是一个值得思考的问题。','威廉·莎士比亚',0),(23,'我思故我在。','勒内·笛卡尔',0),(24,'未经审视的生活是不值得过的。','苏格拉底',0),(25,'最终，我们会记住的不是敌人的话语，而是朋友的沉默。','马丁·路德·金',0),(26,'实现明天理想的唯一障碍是今天的疑虑。','富兰克林·D·罗斯福',0),(27,'那些杀不死我们的，使我们更强大。','弗里德里希·尼采',0),(28,'生活就是当你忙着做其他计划时发生的事情。','约翰·列侬',0),(29,'千里之行，始于足下。','老子',0),(30,'无论你走得多慢，只要你不停止，前进的脚步就不会停止。','孔子',0),(31,'成功不是终点，失败也并非致命：继续前行的勇气才最重要。','温斯顿·丘吉尔',0),(32,'你错过的投篮百分之百。','韦恩·格雷茨基',0),(33,'在困难的中心蕴藏着机遇。','阿尔伯特·爱因斯坦',0),(34,'不要盯着钟表看；要做像它一样的人。继续走下去。','萨姆·莱文森',0),(35,'总是面向阳光——阴影就会落在你身后。','沃尔特·惠特曼',0);
/*!40000 ALTER TABLE `tb_tip` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_user_role`
--

DROP TABLE IF EXISTS `tb_user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_user_role` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_id` bigint NOT NULL COMMENT '账号',
  `role_id` int NOT NULL COMMENT '角色',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_user_role`
--

LOCK TABLES `tb_user_role` WRITE;
/*!40000 ALTER TABLE `tb_user_role` DISABLE KEYS */;
INSERT INTO `tb_user_role` VALUES (1,1001,1);
/*!40000 ALTER TABLE `tb_user_role` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-06-19 13:25:27
