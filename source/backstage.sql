/*
 Navicat Premium Data Transfer

 Source Server         : 测试
 Source Server Type    : MySQL
 Source Server Version : 50738
 Source Host           : localhost:3306
 Source Schema         : backstage

 Target Server Type    : MySQL
 Target Server Version : 50738
 File Encoding         : 65001

 Date: 22/10/2022 14:57:45
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for crontab
-- ----------------------------
DROP TABLE IF EXISTS `crontab`;
CREATE TABLE `crontab` (
  `id` bigint(20) NOT NULL,
  `name` varchar(20) NOT NULL COMMENT '任务名称',
  `class_name` varchar(200) NOT NULL COMMENT '类名',
  `cron` varchar(100) NOT NULL COMMENT 'cron表达式',
  `status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '任务状态\n0-未启动\n1-启动',
  `remarks` varchar(200) DEFAULT NULL COMMENT '备注',
  `create_time` bigint(13) NOT NULL COMMENT '创建时间',
  `update_time` bigint(13) DEFAULT NULL COMMENT '修改时间',
  `is_deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除\n0-未删除\n1-删除',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `idx_delete` (`is_deleted`),
  KEY `idx_name` (`name`),
  KEY `idx_status` (`status`),
  KEY `idx_create` (`create_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='定时任务';

-- ----------------------------
-- Records of crontab
-- ----------------------------
BEGIN;
INSERT INTO `crontab` VALUES (106763734457778177, '测试任务', 'com.tuccicode.backstage.app.crontab.job.TestJob', '*/5 * * * * ?', 0, '测试使用', 1666420857861, 1666421110374, 0);
COMMIT;

-- ----------------------------
-- Table structure for crontab_log
-- ----------------------------
DROP TABLE IF EXISTS `crontab_log`;
CREATE TABLE `crontab_log` (
  `id` bigint(20) NOT NULL,
  `crontab_id` bigint(20) NOT NULL COMMENT '定时任务id',
  `status` int(1) NOT NULL DEFAULT '0' COMMENT '运行状态\n0-运行中\n1-成功\n2-失败',
  `message` text COMMENT '运行信息',
  `start_time` bigint(13) NOT NULL COMMENT '开始时间',
  `run_time` bigint(13) DEFAULT NULL COMMENT '运行时间',
  `create_time` bigint(13) NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `idx_crontab` (`crontab_id`),
  KEY `idx_create` (`create_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of crontab_log
-- ----------------------------
BEGIN;
INSERT INTO `crontab_log` VALUES (106764690423545856, 106763734457778177, 1, NULL, 1666421085763, 106, 1666421085779);
COMMIT;

-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept` (
  `id` bigint(20) NOT NULL,
  `name` varchar(20) NOT NULL COMMENT '部门名称',
  `pid` bigint(20) NOT NULL DEFAULT '0' COMMENT '父级id，0是顶级部门',
  `seq` int(2) NOT NULL DEFAULT '0' COMMENT '排序',
  `manager` varchar(20) NOT NULL COMMENT '部门管理人',
  `manager_phone` varchar(11) NOT NULL COMMENT '管理人手机',
  `create_time` bigint(13) NOT NULL COMMENT '创建时间',
  `update_time` bigint(13) DEFAULT NULL COMMENT '修改时间',
  `is_deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除\n0-未删除\n1-删除',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `idx_name` (`name`) USING BTREE,
  KEY `idx_delete` (`is_deleted`) USING BTREE,
  KEY `idx_create` (`create_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='部门表，一个用户只能绑定一个部门';

-- ----------------------------
-- Records of sys_dept
-- ----------------------------
BEGIN;
INSERT INTO `sys_dept` VALUES (24169813928574976, '总部', 0, 0, 'Tucci', '13333333333', 1644560698135, NULL, 0);
COMMIT;

-- ----------------------------
-- Table structure for sys_login_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_login_log`;
CREATE TABLE `sys_login_log` (
  `id` bigint(20) NOT NULL,
  `username` varchar(20) NOT NULL COMMENT '登录账号',
  `os` varchar(20) NOT NULL COMMENT '操作系统',
  `browser` varchar(20) NOT NULL COMMENT '浏览器',
  `ip` varchar(15) NOT NULL COMMENT 'ip地址',
  `status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '登录状态\n0-失败\n1-成功',
  `message` varchar(1000) DEFAULT NULL COMMENT '信息',
  `create_time` bigint(13) NOT NULL COMMENT '登录时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `idx_username` (`username`) USING BTREE,
  KEY `idx_ip` (`ip`) USING BTREE,
  KEY `idx_status` (`status`) USING BTREE,
  KEY `idx_create` (`create_time`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='登录日志';

-- ----------------------------
-- Records of sys_login_log
-- ----------------------------
BEGIN;
INSERT INTO `sys_login_log` VALUES (106742595085926401, 'admin', 'Mac OS X', 'Chrome 9', '127.0.0.1', 1, '', 1666415817833);
INSERT INTO `sys_login_log` VALUES (106766913731821568, 'admin', 'Mac OS X', 'Chrome 9', '127.0.0.1', 0, '账号锁定', 1666421615867);
INSERT INTO `sys_login_log` VALUES (106766978483486721, 'admin', 'Mac OS X', 'Chrome 9', '127.0.0.1', 1, '', 1666421631304);
INSERT INTO `sys_login_log` VALUES (106767179445174272, 'admin', 'Mac OS X', 'Chrome 9', '127.0.0.1', 0, '验证码错误', 1666421679211);
INSERT INTO `sys_login_log` VALUES (106767770598768641, 'admin', 'Mac OS X', 'Chrome 9', '127.0.0.1', 1, '', 1666421820144);
COMMIT;

-- ----------------------------
-- Table structure for sys_operate_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_operate_log`;
CREATE TABLE `sys_operate_log` (
  `id` bigint(20) NOT NULL,
  `username` varchar(20) NOT NULL COMMENT '操作人账号',
  `ip` varchar(15) NOT NULL COMMENT '用户ip',
  `url` varchar(1000) NOT NULL COMMENT '请求URL',
  `method` varchar(1000) NOT NULL COMMENT '执行方法',
  `params` text COMMENT '参数',
  `result` text COMMENT '执行成功后的返回信息',
  `description` varchar(20) NOT NULL COMMENT '描述',
  `error_message` text COMMENT '执行失败后的异常信息',
  `status` tinyint(1) NOT NULL COMMENT '执行状态\n0-失败\n1-成功',
  `create_time` bigint(13) NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `idx_status` (`status`) USING BTREE,
  KEY `idx_username` (`username`) USING BTREE,
  KEY `idx_ip` (`ip`) USING BTREE,
  KEY `idx_create` (`create_time`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of sys_operate_log
-- ----------------------------
BEGIN;
INSERT INTO `sys_operate_log` VALUES (106764794157072385, 'admin', '127.0.0.1', 'http://localhost:9000/crontab/status', 'com.tuccicode.backstage.app.crontab.controller.CrontabController.updateStatus', '{\"id\":106763734457778177,\"status\":false}', '{\"code\":0,\"status\":true}', '编辑定时任务状态', NULL, 1, 1666421110511);
INSERT INTO `sys_operate_log` VALUES (106766448889692161, 'admin', '127.0.0.1', 'http://localhost:9000/sys/user', 'com.tuccicode.backstage.app.system.controller.SysUserController.update', '{\"nickname\":\"管理员\",\"phone\":\"13333333332\",\"uid\":1}', '{\"code\":0,\"status\":true}', '修改用户', NULL, 1, 1666421505038);
INSERT INTO `sys_operate_log` VALUES (106766477633257472, 'admin', '127.0.0.1', 'http://localhost:9000/sys/user', 'com.tuccicode.backstage.app.system.controller.SysUserController.update', '{\"nickname\":\"管理员\",\"phone\":\"13333333333\",\"uid\":1}', '{\"code\":0,\"status\":true}', '修改用户', NULL, 1, 1666421511890);
INSERT INTO `sys_operate_log` VALUES (106766488509087744, 'admin', '127.0.0.1', 'http://localhost:9000/sys/user/lock', 'com.tuccicode.backstage.app.system.controller.SysUserController.updateLock', '{\"isLock\":true,\"uid\":1}', '{\"code\":0,\"status\":true}', '修改用户锁定状态', NULL, 1, 1666421514485);
INSERT INTO `sys_operate_log` VALUES (106767006174281729, 'admin', '127.0.0.1', 'http://localhost:9000/sys/user/lock', 'com.tuccicode.backstage.app.system.controller.SysUserController.updateLock', '{\"isLock\":true,\"uid\":1}', '{\"code\":0,\"status\":true}', '修改用户锁定状态', NULL, 1, 1666421637906);
INSERT INTO `sys_operate_log` VALUES (106767795122864129, 'admin', '127.0.0.1', 'http://localhost:9000/sys/user/lock', 'com.tuccicode.backstage.app.system.controller.SysUserController.updateLock', '{\"isLock\":true,\"uid\":1}', NULL, '修改用户锁定状态', '参数错误', 0, 1666421826001);
INSERT INTO `sys_operate_log` VALUES (106767900118876161, 'admin', '127.0.0.1', 'http://localhost:9000/sys/user/password', 'com.tuccicode.backstage.app.system.controller.SysUserController.updatePassword', '{\"password\":\"123456\",\"uid\":1}', NULL, '修改用户密码', '参数错误', 0, 1666421851039);
COMMIT;

-- ----------------------------
-- Table structure for sys_res
-- ----------------------------
DROP TABLE IF EXISTS `sys_res`;
CREATE TABLE `sys_res` (
  `id` bigint(20) NOT NULL,
  `name` varchar(20) NOT NULL COMMENT '资源名称',
  `type` int(1) NOT NULL COMMENT '类型\n1-菜单\n2-权限',
  `url` varchar(100) DEFAULT NULL COMMENT 'url',
  `pid` bigint(20) NOT NULL DEFAULT '0' COMMENT '父级id，0是顶级目录',
  `res_char` varchar(50) DEFAULT NULL COMMENT '资源字符',
  `seq` int(2) NOT NULL DEFAULT '0' COMMENT '排序',
  `create_time` bigint(13) NOT NULL COMMENT '创建时间',
  `update_time` bigint(13) DEFAULT NULL COMMENT '修改时间',
  `is_deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除\n0-未删除\n1-删除',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `idx_type` (`type`) USING BTREE,
  KEY `idx_delete` (`is_deleted`) USING BTREE,
  KEY `idx_name` (`name`) USING BTREE,
  KEY `idx_create` (`create_time`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='资源';

-- ----------------------------
-- Records of sys_res
-- ----------------------------
BEGIN;
INSERT INTO `sys_res` VALUES (28174904473944064, '定时任务', 1, '/monitor/crontab', 113329647516647424, NULL, 0, 1645515586131, NULL, 0);
INSERT INTO `sys_res` VALUES (28175098825408512, '定时任务查询', 2, NULL, 28174904473944064, 'crontab:list', 0, 1645515632468, NULL, 0);
INSERT INTO `sys_res` VALUES (28516794918502401, '定时任务添加', 2, NULL, 28174904473944064, 'crontab:create', 0, 1645597099165, NULL, 0);
INSERT INTO `sys_res` VALUES (28516877999276032, '定时任务修改', 2, NULL, 28174904473944064, 'crontab:update', 0, 1645597118973, NULL, 0);
INSERT INTO `sys_res` VALUES (28516935251525632, '定时任务删除', 2, NULL, 28174904473944064, 'crontab:delete', 0, 1645597132623, NULL, 0);
INSERT INTO `sys_res` VALUES (28517012728709120, '定时任务状态', 2, NULL, 28174904473944064, 'crontab:update:status', 0, 1645597151095, NULL, 0);
INSERT INTO `sys_res` VALUES (28517103883517953, '定时任务执行', 2, NULL, 28174904473944064, 'crontab:start', 0, 1645597172828, NULL, 0);
INSERT INTO `sys_res` VALUES (112063924660076544, '系统管理', 1, NULL, 0, NULL, 98, 1610292596000, NULL, 0);
INSERT INTO `sys_res` VALUES (112064419969630208, '资源管理', 1, '/sys/res', 112063924660076544, NULL, 2, 1610292714000, NULL, 0);
INSERT INTO `sys_res` VALUES (112064548923506688, '角色管理', 1, '/sys/role', 112063924660076544, NULL, 1, 1610292745000, NULL, 0);
INSERT INTO `sys_res` VALUES (112065408583860224, '用户管理', 1, '/sys/user', 112063924660076544, NULL, 0, 1610292949000, NULL, 0);
INSERT INTO `sys_res` VALUES (112304735788204032, '资源查询', 2, NULL, 112064419969630208, 'sys:res:tree', 0, 1610350010000, NULL, 0);
INSERT INTO `sys_res` VALUES (112304837034508288, '资源添加', 2, NULL, 112064419969630208, 'sys:res:create', 0, 1610350034000, NULL, 0);
INSERT INTO `sys_res` VALUES (112304921109331968, '资源修改', 2, NULL, 112064419969630208, 'sys:res:update', 0, 1610350054000, NULL, 0);
INSERT INTO `sys_res` VALUES (112305002013261824, '资源删除', 2, NULL, 112064419969630208, 'sys:res:delete', 0, 1610350073000, NULL, 0);
INSERT INTO `sys_res` VALUES (112766904753455104, '部门管理', 1, '/sys/dept', 112063924660076544, NULL, 3, 1610460199683, NULL, 0);
INSERT INTO `sys_res` VALUES (113296483343663104, '用户查询', 2, NULL, 112065408583860224, 'sys:user:list', 0, 1610586461058, NULL, 0);
INSERT INTO `sys_res` VALUES (113296609537687552, '用户添加', 2, NULL, 112065408583860224, 'sys:user:create', 0, 1610586491144, NULL, 0);
INSERT INTO `sys_res` VALUES (113296742077693952, '用户修改', 2, NULL, 112065408583860224, 'sys:user:update', 0, 1610586522744, NULL, 0);
INSERT INTO `sys_res` VALUES (113296815033417728, '用户删除', 2, NULL, 112065408583860224, 'sys:user:delete', 0, 1610586540138, NULL, 0);
INSERT INTO `sys_res` VALUES (113296905349365760, '用户锁定', 2, NULL, 112065408583860224, 'sys:user:update:lock', 0, 1610586561671, NULL, 0);
INSERT INTO `sys_res` VALUES (113297095770767360, '密码修改', 2, NULL, 112065408583860224, 'sys:user:update:password', 0, 1610586607071, NULL, 0);
INSERT INTO `sys_res` VALUES (113297279363842048, '角色查询', 2, NULL, 112064548923506688, 'sys:role:list', 0, 1610586650843, NULL, 0);
INSERT INTO `sys_res` VALUES (113297348314005504, '角色添加', 2, NULL, 112064548923506688, 'sys:role:create', 0, 1610586667282, NULL, 0);
INSERT INTO `sys_res` VALUES (113297416005877760, '角色修改', 2, NULL, 112064548923506688, 'sys:role:update', 0, 1610586683421, NULL, 0);
INSERT INTO `sys_res` VALUES (113297479004323840, '角色删除', 2, NULL, 112064548923506688, 'sys:role:delete', 0, 1610586698441, NULL, 0);
INSERT INTO `sys_res` VALUES (113297703277953024, '部门查询', 2, NULL, 112766904753455104, 'sys:dept:tree', 0, 1610586751912, NULL, 0);
INSERT INTO `sys_res` VALUES (113297768126087168, '部门添加', 2, NULL, 112766904753455104, 'sys:dept:create', 0, 1610586767373, NULL, 0);
INSERT INTO `sys_res` VALUES (113297841698373632, '部门修改', 2, NULL, 112766904753455104, 'sys:dept:update', 0, 1610586784914, NULL, 0);
INSERT INTO `sys_res` VALUES (113297895578402816, '部门删除', 2, NULL, 112766904753455104, 'sys:dept:delete', 0, 1610586797761, NULL, 0);
INSERT INTO `sys_res` VALUES (113326873903104000, '日志管理', 1, NULL, 0, NULL, 99, 1610593706732, NULL, 0);
INSERT INTO `sys_res` VALUES (113327217995415552, '登陆日志', 1, '/log/login', 113326873903104000, NULL, 0, 1610593788770, NULL, 0);
INSERT INTO `sys_res` VALUES (113328033569439744, '操作日志', 1, '/log/operate', 113326873903104000, NULL, 0, 1610593983218, NULL, 0);
INSERT INTO `sys_res` VALUES (113329345681948672, '登录日志查询', 2, NULL, 113327217995415552, 'log:login:list', 0, 1610594296049, NULL, 0);
INSERT INTO `sys_res` VALUES (113329497700302848, '操作日志查询', 2, NULL, 113328033569439744, 'log:operate:list', 0, 1610594332293, NULL, 0);
INSERT INTO `sys_res` VALUES (113329647516647424, '监控管理', 1, NULL, 0, NULL, 97, 1610594368012, NULL, 0);
INSERT INTO `sys_res` VALUES (113329855000477696, 'Druid监控', 1, '/monitor/druid', 113329647516647424, NULL, 0, 1610594417480, NULL, 0);
INSERT INTO `sys_res` VALUES (113329976727568384, 'Druid监控查询', 2, NULL, 113329855000477696, 'monitor:druid:view', 0, 1610594446502, NULL, 0);
COMMIT;

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` bigint(20) NOT NULL,
  `role_char` varchar(20) DEFAULT NULL COMMENT '角色字符',
  `name` varchar(20) NOT NULL COMMENT '角色名',
  `remarks` varchar(200) DEFAULT NULL COMMENT '备注',
  `create_time` bigint(13) NOT NULL COMMENT '创建时间',
  `update_time` bigint(13) DEFAULT NULL COMMENT '修改时间',
  `is_deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除\n0-未删除\n1-删除',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `idx_name` (`name`) USING BTREE,
  KEY `idx_delete` (`is_deleted`) USING BTREE,
  KEY `idx_create` (`create_time`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
BEGIN;
INSERT INTO `sys_role` VALUES (112650695508754432, 'root', '系统管理员', '', 1610432493240, NULL, 0);
COMMIT;

-- ----------------------------
-- Table structure for sys_role_res
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_res`;
CREATE TABLE `sys_role_res` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) NOT NULL COMMENT '角色id',
  `res_id` bigint(20) NOT NULL COMMENT '资源id',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uk_role_res` (`role_id`,`res_id`) USING BTREE,
  KEY `idx_res_id` (`res_id`) USING BTREE,
  KEY `idx_role_id` (`role_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1298 DEFAULT CHARSET=utf8mb4 COMMENT='角色与资源关联表';

-- ----------------------------
-- Records of sys_role_res
-- ----------------------------
BEGIN;
INSERT INTO `sys_role_res` VALUES (1204, 112650695508754432, 28174904473944064);
INSERT INTO `sys_role_res` VALUES (1205, 112650695508754432, 28175098825408512);
INSERT INTO `sys_role_res` VALUES (1206, 112650695508754432, 28516794918502401);
INSERT INTO `sys_role_res` VALUES (1207, 112650695508754432, 28516877999276032);
INSERT INTO `sys_role_res` VALUES (1208, 112650695508754432, 28516935251525632);
INSERT INTO `sys_role_res` VALUES (1209, 112650695508754432, 28517012728709120);
INSERT INTO `sys_role_res` VALUES (1210, 112650695508754432, 28517103883517953);
INSERT INTO `sys_role_res` VALUES (1213, 112650695508754432, 112063924660076544);
INSERT INTO `sys_role_res` VALUES (1226, 112650695508754432, 112064419969630208);
INSERT INTO `sys_role_res` VALUES (1221, 112650695508754432, 112064548923506688);
INSERT INTO `sys_role_res` VALUES (1214, 112650695508754432, 112065408583860224);
INSERT INTO `sys_role_res` VALUES (1227, 112650695508754432, 112304735788204032);
INSERT INTO `sys_role_res` VALUES (1228, 112650695508754432, 112304837034508288);
INSERT INTO `sys_role_res` VALUES (1229, 112650695508754432, 112304921109331968);
INSERT INTO `sys_role_res` VALUES (1230, 112650695508754432, 112305002013261824);
INSERT INTO `sys_role_res` VALUES (1231, 112650695508754432, 112766904753455104);
INSERT INTO `sys_role_res` VALUES (1215, 112650695508754432, 113296483343663104);
INSERT INTO `sys_role_res` VALUES (1216, 112650695508754432, 113296609537687552);
INSERT INTO `sys_role_res` VALUES (1217, 112650695508754432, 113296742077693952);
INSERT INTO `sys_role_res` VALUES (1218, 112650695508754432, 113296815033417728);
INSERT INTO `sys_role_res` VALUES (1219, 112650695508754432, 113296905349365760);
INSERT INTO `sys_role_res` VALUES (1220, 112650695508754432, 113297095770767360);
INSERT INTO `sys_role_res` VALUES (1222, 112650695508754432, 113297279363842048);
INSERT INTO `sys_role_res` VALUES (1223, 112650695508754432, 113297348314005504);
INSERT INTO `sys_role_res` VALUES (1224, 112650695508754432, 113297416005877760);
INSERT INTO `sys_role_res` VALUES (1225, 112650695508754432, 113297479004323840);
INSERT INTO `sys_role_res` VALUES (1232, 112650695508754432, 113297703277953024);
INSERT INTO `sys_role_res` VALUES (1233, 112650695508754432, 113297768126087168);
INSERT INTO `sys_role_res` VALUES (1234, 112650695508754432, 113297841698373632);
INSERT INTO `sys_role_res` VALUES (1235, 112650695508754432, 113297895578402816);
INSERT INTO `sys_role_res` VALUES (1236, 112650695508754432, 113326873903104000);
INSERT INTO `sys_role_res` VALUES (1237, 112650695508754432, 113327217995415552);
INSERT INTO `sys_role_res` VALUES (1239, 112650695508754432, 113328033569439744);
INSERT INTO `sys_role_res` VALUES (1238, 112650695508754432, 113329345681948672);
INSERT INTO `sys_role_res` VALUES (1240, 112650695508754432, 113329497700302848);
INSERT INTO `sys_role_res` VALUES (1203, 112650695508754432, 113329647516647424);
INSERT INTO `sys_role_res` VALUES (1211, 112650695508754432, 113329855000477696);
INSERT INTO `sys_role_res` VALUES (1212, 112650695508754432, 113329976727568384);
COMMIT;

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `uid` bigint(20) NOT NULL,
  `username` varchar(20) NOT NULL COMMENT '账号',
  `password` varchar(60) NOT NULL COMMENT '密码',
  `phone` varchar(11) DEFAULT NULL COMMENT '手机',
  `email` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `nickname` varchar(20) DEFAULT NULL COMMENT '昵称',
  `remarks` varchar(200) DEFAULT NULL COMMENT '备注',
  `dept_id` bigint(20) DEFAULT NULL COMMENT '所属部门id',
  `version` bigint(20) NOT NULL DEFAULT '0' COMMENT '版本号',
  `is_lock` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否锁定\n0-未锁定\n1-锁定',
  `create_time` bigint(13) NOT NULL COMMENT '创建时间',
  `update_time` bigint(13) DEFAULT NULL COMMENT '修改时间',
  `is_deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除\n0-未删除\n1-删除',
  PRIMARY KEY (`uid`) USING BTREE,
  KEY `idx_dept` (`dept_id`) USING BTREE,
  KEY `idx_lock` (`is_lock`) USING BTREE,
  KEY `idx_deleted` (`is_deleted`) USING BTREE,
  KEY `idx_delete` (`username`) USING BTREE,
  KEY `idx_phone` (`phone`) USING BTREE,
  KEY `idx_create` (`create_time`),
  KEY `idx_email` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户信息';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
BEGIN;
INSERT INTO `sys_user` VALUES (1, 'admin', '$2a$10$6tUNVXwuiG6/QLA20y1U.evtvCS5Gv.nccmvhGgAEGNAi3Xu4wUsm', '13333333333', NULL, '管理员', NULL, NULL, 0, 0, 1, 1666421637752, 0);
COMMIT;

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `uid` bigint(20) NOT NULL COMMENT '用户id',
  `role_id` bigint(20) NOT NULL COMMENT '角色id',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uk_uid_role_id` (`uid`,`role_id`) USING BTREE,
  KEY `uk_uid_role` (`uid`) USING BTREE,
  KEY `idx_role_id` (`role_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COMMENT='用户关联的角色';

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
BEGIN;
INSERT INTO `sys_user_role` VALUES (1, 1, 112650695508754432);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
