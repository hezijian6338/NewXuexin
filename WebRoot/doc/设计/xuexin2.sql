prompt PL/SQL Developer import file
prompt Created on 2016年2月25日 by Administrator
set feedback off
set define off
prompt Creating TB_SYS_AUTHORITY...
create table TB_SYS_AUTHORITY
(
  AUTHORITY_ID   VARCHAR2(32) not null,
  PARENT_ID      VARCHAR2(32),
  PARENT_IDS     VARCHAR2(1000),
  AUTHORITY_NAME VARCHAR2(64) not null,
  AUTHORITY_TYPE NUMBER not null,
  MODULE_NAME    VARCHAR2(64),
  URL            VARCHAR2(200),
  OPERATION      VARCHAR2(32),
  MENU_NO        VARCHAR2(32),
  MEMO           VARCHAR2(200)
)
;
comment on column TB_SYS_AUTHORITY.AUTHORITY_ID
  is '权限ID';
comment on column TB_SYS_AUTHORITY.PARENT_ID
  is '父级权限ID';
comment on column TB_SYS_AUTHORITY.PARENT_IDS
  is '所有父级权限ID';
comment on column TB_SYS_AUTHORITY.AUTHORITY_NAME
  is '权限名称';
comment on column TB_SYS_AUTHORITY.AUTHORITY_TYPE
  is '权限类型 0-菜单 1-新增 2-修改 3-查询 4-删除 5-导出 6-导入 7-授权';
comment on column TB_SYS_AUTHORITY.MODULE_NAME
  is '模块名称';
comment on column TB_SYS_AUTHORITY.URL
  is '访问地址';
comment on column TB_SYS_AUTHORITY.OPERATION
  is '操作';
comment on column TB_SYS_AUTHORITY.MENU_NO
  is '菜单排序号';
comment on column TB_SYS_AUTHORITY.MEMO
  is '备注';
alter table TB_SYS_AUTHORITY
  add primary key (AUTHORITY_ID);

prompt Creating TB_SYS_LOGIN_LOG...
create table TB_SYS_LOGIN_LOG
(
  LOGIC_ID   VARCHAR2(32) not null,
  EMPLOY_NO  VARCHAR2(32),
  LOGIN_TIME DATE,
  LOGIN_IP   VARCHAR2(100),
  IF_SUCCESS NUMBER
)
;
comment on column TB_SYS_LOGIN_LOG.LOGIC_ID
  is '逻辑ID';
comment on column TB_SYS_LOGIN_LOG.EMPLOY_NO
  is '用户账号';
comment on column TB_SYS_LOGIN_LOG.LOGIN_TIME
  is '登入时间';
comment on column TB_SYS_LOGIN_LOG.LOGIN_IP
  is '客户端IP';
comment on column TB_SYS_LOGIN_LOG.IF_SUCCESS
  is '是否成功';
alter table TB_SYS_LOGIN_LOG
  add primary key (LOGIC_ID);

prompt Creating TB_SYS_ORGANIZATION...
create table TB_SYS_ORGANIZATION
(
  ORG_ID      VARCHAR2(32) not null,
  ORG_NAME    VARCHAR2(128),
  PARENT_ID   VARCHAR2(32),
  PARENT_IDS  VARCHAR2(1000),
  ADDRESS     VARCHAR2(128),
  POST_CODE   VARCHAR2(16),
  CONTACT_MAN VARCHAR2(32),
  TELL        VARCHAR2(16),
  FAX         VARCHAR2(16),
  EMAIL       VARCHAR2(32),
  STATUS      VARCHAR2(16)
)
;
comment on column TB_SYS_ORGANIZATION.ORG_ID
  is '标识ID';
comment on column TB_SYS_ORGANIZATION.ORG_NAME
  is '机构名称';
comment on column TB_SYS_ORGANIZATION.PARENT_ID
  is '父级机构ID';
comment on column TB_SYS_ORGANIZATION.PARENT_IDS
  is '所有父级机构ID';
comment on column TB_SYS_ORGANIZATION.ADDRESS
  is '详细地址';
comment on column TB_SYS_ORGANIZATION.POST_CODE
  is '邮编';
comment on column TB_SYS_ORGANIZATION.CONTACT_MAN
  is '联系人（负责人）';
comment on column TB_SYS_ORGANIZATION.TELL
  is '联系电话';
comment on column TB_SYS_ORGANIZATION.FAX
  is '传真';
comment on column TB_SYS_ORGANIZATION.EMAIL
  is 'Email';
alter table TB_SYS_ORGANIZATION
  add primary key (ORG_ID);

prompt Creating TB_SYS_ROLE...
create table TB_SYS_ROLE
(
  ROLE_ID     VARCHAR2(32) not null,
  ROLE_NAME   VARCHAR2(64),
  CREATE_TIME DATE,
  ROLE_NO     VARCHAR2(32),
  MEMO        VARCHAR2(200)
)
;
comment on column TB_SYS_ROLE.ROLE_ID
  is '主键ID';
comment on column TB_SYS_ROLE.ROLE_NAME
  is '角色名称';
comment on column TB_SYS_ROLE.CREATE_TIME
  is '创建时间';
comment on column TB_SYS_ROLE.ROLE_NO
  is '排序号';
comment on column TB_SYS_ROLE.MEMO
  is '备注';
alter table TB_SYS_ROLE
  add primary key (ROLE_ID);

prompt Creating TB_SYS_ROLE_AUTHORITY...
create table TB_SYS_ROLE_AUTHORITY
(
  ID           VARCHAR2(32) not null,
  AUTHORITY_ID VARCHAR2(32),
  ROLE_ID      VARCHAR2(32)
)
;
comment on column TB_SYS_ROLE_AUTHORITY.ID
  is '主键标识';
comment on column TB_SYS_ROLE_AUTHORITY.AUTHORITY_ID
  is '权限ID';
comment on column TB_SYS_ROLE_AUTHORITY.ROLE_ID
  is '主键ID';
alter table TB_SYS_ROLE_AUTHORITY
  add primary key (ID);
alter table TB_SYS_ROLE_AUTHORITY
  add foreign key (AUTHORITY_ID)
  references TB_SYS_AUTHORITY (AUTHORITY_ID);
alter table TB_SYS_ROLE_AUTHORITY
  add foreign key (ROLE_ID)
  references TB_SYS_ROLE (ROLE_ID);

prompt Creating TB_SYS_USER...
create table TB_SYS_USER
(
  USER_ID     VARCHAR2(32) not null,
  ORG_ID      VARCHAR2(32),
  EMPLOY_NO   VARCHAR2(10),
  EMPLOY_NAME VARCHAR2(64),
  CREATE_TIME DATE,
  SEX         NUMBER,
  TELL        VARCHAR2(20),
  STATUS      NUMBER,
  ADDRESS     VARCHAR2(200),
  EMAIL       VARCHAR2(50),
  PASSWORD    VARCHAR2(50),
  USER_TYPE   VARCHAR2(2)
)
;
comment on column TB_SYS_USER.USER_ID
  is '标识ID';
comment on column TB_SYS_USER.ORG_ID
  is '标识ID';
comment on column TB_SYS_USER.EMPLOY_NO
  is '用户编号';
comment on column TB_SYS_USER.EMPLOY_NAME
  is '用户名称';
comment on column TB_SYS_USER.CREATE_TIME
  is '创建时间';
comment on column TB_SYS_USER.SEX
  is '性别 0-男 1-女';
comment on column TB_SYS_USER.TELL
  is '联系电话';
comment on column TB_SYS_USER.STATUS
  is '状态 0-启用 1-禁用';
comment on column TB_SYS_USER.ADDRESS
  is '联系地址';
comment on column TB_SYS_USER.EMAIL
  is '电子邮箱';
comment on column TB_SYS_USER.PASSWORD
  is '密码';
comment on column TB_SYS_USER.USER_TYPE
  is '用户类型';
alter table TB_SYS_USER
  add primary key (USER_ID);
alter table TB_SYS_USER
  add foreign key (ORG_ID)
  references TB_SYS_ORGANIZATION (ORG_ID);

prompt Creating TB_SYS_USER_ROLE...
create table TB_SYS_USER_ROLE
(
  ID      VARCHAR2(32) not null,
  USER_ID VARCHAR2(32),
  ROLE_ID VARCHAR2(32)
)
;
comment on table TB_SYS_USER_ROLE
  is '一个用户可以有多个角色';
comment on column TB_SYS_USER_ROLE.ID
  is '主键标识ID';
comment on column TB_SYS_USER_ROLE.USER_ID
  is '标识ID';
comment on column TB_SYS_USER_ROLE.ROLE_ID
  is '主键ID';
alter table TB_SYS_USER_ROLE
  add primary key (ID);
alter table TB_SYS_USER_ROLE
  add foreign key (ROLE_ID)
  references TB_SYS_ROLE (ROLE_ID);
alter table TB_SYS_USER_ROLE
  add foreign key (USER_ID)
  references TB_SYS_USER (USER_ID);

prompt Disabling triggers for TB_SYS_AUTHORITY...
alter table TB_SYS_AUTHORITY disable all triggers;
prompt Disabling triggers for TB_SYS_LOGIN_LOG...
alter table TB_SYS_LOGIN_LOG disable all triggers;
prompt Disabling triggers for TB_SYS_ORGANIZATION...
alter table TB_SYS_ORGANIZATION disable all triggers;
prompt Disabling triggers for TB_SYS_ROLE...
alter table TB_SYS_ROLE disable all triggers;
prompt Disabling triggers for TB_SYS_ROLE_AUTHORITY...
alter table TB_SYS_ROLE_AUTHORITY disable all triggers;
prompt Disabling triggers for TB_SYS_USER...
alter table TB_SYS_USER disable all triggers;
prompt Disabling triggers for TB_SYS_USER_ROLE...
alter table TB_SYS_USER_ROLE disable all triggers;
prompt Disabling foreign key constraints for TB_SYS_ROLE_AUTHORITY...
alter table TB_SYS_ROLE_AUTHORITY disable constraint SYS_C006452;
alter table TB_SYS_ROLE_AUTHORITY disable constraint SYS_C006453;
prompt Disabling foreign key constraints for TB_SYS_USER...
alter table TB_SYS_USER disable constraint SYS_C006449;
prompt Disabling foreign key constraints for TB_SYS_USER_ROLE...
alter table TB_SYS_USER_ROLE disable constraint SYS_C006450;
alter table TB_SYS_USER_ROLE disable constraint SYS_C006451;
prompt Loading TB_SYS_AUTHORITY...
insert into TB_SYS_AUTHORITY (AUTHORITY_ID, PARENT_ID, PARENT_IDS, AUTHORITY_NAME, AUTHORITY_TYPE, MODULE_NAME, URL, OPERATION, MENU_NO, MEMO)
values ('402890c652ff1de20152ff68a42b0043', '402890c652ff1de20152ff635bcd0013', 'defaultpid,1397032583255590314,05abae991c544b30b82efbc3434a3cbf,402890c652ff1de20152ff635bcd0013', '组织机构下拉列表', 3, '用户管理', 'sys/organizationAction_getOrgTree.action', null, '2233', null);
insert into TB_SYS_AUTHORITY (AUTHORITY_ID, PARENT_ID, PARENT_IDS, AUTHORITY_NAME, AUTHORITY_TYPE, MODULE_NAME, URL, OPERATION, MENU_NO, MEMO)
values ('402890c652ff1de20152ff69b8e60055', '402890c652ff1de20152ff635bcd0013', 'defaultpid,1397032583255590314,05abae991c544b30b82efbc3434a3cbf,402890c652ff1de20152ff635bcd0013', '角色下拉列表', 3, '用户管理', 'sys/roleAction_getRoles.action', null, '2234', null);
insert into TB_SYS_AUTHORITY (AUTHORITY_ID, PARENT_ID, PARENT_IDS, AUTHORITY_NAME, AUTHORITY_TYPE, MODULE_NAME, URL, OPERATION, MENU_NO, MEMO)
values ('402890c652ff1de20152ff738c70007c', '402890c652fd77e10152fdda7c740016', 'defaultpid,1397032583255590314,05abae991c544b30b82efbc3434a3cbf,402890c652fd77e10152fdda7c740016', '组织机构下拉列表', 3, '用户管理', 'sys/organizationAction_getOrgTree.action', null, '2222', null);
insert into TB_SYS_AUTHORITY (AUTHORITY_ID, PARENT_ID, PARENT_IDS, AUTHORITY_NAME, AUTHORITY_TYPE, MODULE_NAME, URL, OPERATION, MENU_NO, MEMO)
values ('402890c652ff1de20152ff7489ae00a7', '402890c652fd77e10152fdda7c740016', 'defaultpid,1397032583255590314,05abae991c544b30b82efbc3434a3cbf,402890c652fd77e10152fdda7c740016', '角色下拉列表', 3, '用户管理', 'sys/roleAction_getRoles.action', null, '2223', null);
insert into TB_SYS_AUTHORITY (AUTHORITY_ID, PARENT_ID, PARENT_IDS, AUTHORITY_NAME, AUTHORITY_TYPE, MODULE_NAME, URL, OPERATION, MENU_NO, MEMO)
values ('1397032583255590314', 'defaultpid', 'defaultpid', '系统管理', 0, '系统管理', '#', null, '1000', null);
insert into TB_SYS_AUTHORITY (AUTHORITY_ID, PARENT_ID, PARENT_IDS, AUTHORITY_NAME, AUTHORITY_TYPE, MODULE_NAME, URL, OPERATION, MENU_NO, MEMO)
values ('402890dc52f9efce0152f9efd1a60001', '1397032583255590314', 'defaultpid,1397032583255590314', '权限信息', 0, '权限信息', 'sys/authorityAction_index.action', null, '2400', null);
insert into TB_SYS_AUTHORITY (AUTHORITY_ID, PARENT_ID, PARENT_IDS, AUTHORITY_NAME, AUTHORITY_TYPE, MODULE_NAME, URL, OPERATION, MENU_NO, MEMO)
values ('402890fb53022bab015302331885005e', '0ddea4847c6f40a2a089e0c3a52aed2f', 'defaultpid,1397032583255590314,0ddea4847c6f40a2a089e0c3a52aed2f', '删除', 4, '角色管理', 'sys/roleAction_deleteRole.action', null, '22002', null);
insert into TB_SYS_AUTHORITY (AUTHORITY_ID, PARENT_ID, PARENT_IDS, AUTHORITY_NAME, AUTHORITY_TYPE, MODULE_NAME, URL, OPERATION, MENU_NO, MEMO)
values ('402890fb53022bab01530239c5c60077', '0ddea4847c6f40a2a089e0c3a52aed2f', 'defaultpid,1397032583255590314,0ddea4847c6f40a2a089e0c3a52aed2f', '新增入口', 1, '角色管理', 'sys/roleAction_viewAdd.action', null, '22003', null);
insert into TB_SYS_AUTHORITY (AUTHORITY_ID, PARENT_ID, PARENT_IDS, AUTHORITY_NAME, AUTHORITY_TYPE, MODULE_NAME, URL, OPERATION, MENU_NO, MEMO)
values ('402890fb53022bab0153024435eb00e0', '0ddea4847c6f40a2a089e0c3a52aed2f', 'defaultpid,1397032583255590314,0ddea4847c6f40a2a089e0c3a52aed2f', '修改入口', 2, '角色管理', 'sys/roleAction_viewEdit.action', null, '22004', null);
insert into TB_SYS_AUTHORITY (AUTHORITY_ID, PARENT_ID, PARENT_IDS, AUTHORITY_NAME, AUTHORITY_TYPE, MODULE_NAME, URL, OPERATION, MENU_NO, MEMO)
values ('402890fb53022bab0153027ac1430191', '402890fb53022bab015302766e74016f', 'defaultpid,1397032583255590314,0ddea4847c6f40a2a089e0c3a52aed2f,402890fb53022bab015302766e74016f', '设置角色用户', 7, '角色管理', 'sys/userRoleAction_saveUserRole.action', null, '220061', null);
insert into TB_SYS_AUTHORITY (AUTHORITY_ID, PARENT_ID, PARENT_IDS, AUTHORITY_NAME, AUTHORITY_TYPE, MODULE_NAME, URL, OPERATION, MENU_NO, MEMO)
values ('402890fb53022bab01530283cb810295', '402890fb53022bab015302766e74016f', 'defaultpid,1397032583255590314,0ddea4847c6f40a2a089e0c3a52aed2f,402890fb53022bab015302766e74016f', '加载用户列表', 3, '角色管理', 'sys/userAction_getUserList.action', null, '220063', null);
insert into TB_SYS_AUTHORITY (AUTHORITY_ID, PARENT_ID, PARENT_IDS, AUTHORITY_NAME, AUTHORITY_TYPE, MODULE_NAME, URL, OPERATION, MENU_NO, MEMO)
values ('402890fb53022bab01530285e85b02b7', '402890fb53022bab015302766e74016f', 'defaultpid,1397032583255590314,0ddea4847c6f40a2a089e0c3a52aed2f,402890fb53022bab015302766e74016f', '加载权限树列表', 3, '角色管理', 'sys/authorityAction_getAuthorityTree.action', null, '220064', null);
insert into TB_SYS_AUTHORITY (AUTHORITY_ID, PARENT_ID, PARENT_IDS, AUTHORITY_NAME, AUTHORITY_TYPE, MODULE_NAME, URL, OPERATION, MENU_NO, MEMO)
values ('402890fb53022bab01530286f50302da', '402890fb53022bab015302766e74016f', 'defaultpid,1397032583255590314,0ddea4847c6f40a2a089e0c3a52aed2f,402890fb53022bab015302766e74016f', '获取已有权限', 3, '角色管理', 'sys/roleAuthorityAction_getAuthoritiesByRoleId.action', null, '220065', null);
insert into TB_SYS_AUTHORITY (AUTHORITY_ID, PARENT_ID, PARENT_IDS, AUTHORITY_NAME, AUTHORITY_TYPE, MODULE_NAME, URL, OPERATION, MENU_NO, MEMO)
values ('402890fb53022bab01530289fdec038c', '402890fb53022bab015302766e74016f', 'defaultpid,1397032583255590314,0ddea4847c6f40a2a089e0c3a52aed2f,402890fb53022bab015302766e74016f', '组织机构下拉列表', 3, '角色管理', 'sys/organizationAction_getOrgTree.action', null, '220066', null);
insert into TB_SYS_AUTHORITY (AUTHORITY_ID, PARENT_ID, PARENT_IDS, AUTHORITY_NAME, AUTHORITY_TYPE, MODULE_NAME, URL, OPERATION, MENU_NO, MEMO)
values ('05abae991c544b30b82efbc3434a3cbf', '1397032583255590314', 'defaultpid,1397032583255590314', '用户管理', 0, '用户管理', 'sys/userAction_index.action', null, '2100', null);
insert into TB_SYS_AUTHORITY (AUTHORITY_ID, PARENT_ID, PARENT_IDS, AUTHORITY_NAME, AUTHORITY_TYPE, MODULE_NAME, URL, OPERATION, MENU_NO, MEMO)
values ('0ddea4847c6f40a2a089e0c3a52aed2f', '1397032583255590314', 'defaultpid,1397032583255590314', '角色管理', 0, '角色管理', 'sys/roleAction_index.action', null, '2200', null);
insert into TB_SYS_AUTHORITY (AUTHORITY_ID, PARENT_ID, PARENT_IDS, AUTHORITY_NAME, AUTHORITY_TYPE, MODULE_NAME, URL, OPERATION, MENU_NO, MEMO)
values ('12d0657555684a1696257fffa3e6dace', '1397032583255590314', 'defaultpid,1397032583255590314', '组织机构', 0, '组织机构', 'sys/organizationAction_index.action', null, '2300', null);
insert into TB_SYS_AUTHORITY (AUTHORITY_ID, PARENT_ID, PARENT_IDS, AUTHORITY_NAME, AUTHORITY_TYPE, MODULE_NAME, URL, OPERATION, MENU_NO, MEMO)
values ('402890dc52f9efce0152f9efd18d0000', '402890dc52f9efce0152f9efd1a60001', 'defaultpid,1397032583255590314,402890dc52f9efce0152f9efd1a60001', '查询', 3, '权限信息', 'sys/authorityAction_getList.action', null, '2410', null);
insert into TB_SYS_AUTHORITY (AUTHORITY_ID, PARENT_ID, PARENT_IDS, AUTHORITY_NAME, AUTHORITY_TYPE, MODULE_NAME, URL, OPERATION, MENU_NO, MEMO)
values ('402890dc52f9efce0152f9efd1a80002', '402890dc52f9efce0152f9efd1a60001', 'defaultpid,1397032583255590314,402890dc52f9efce0152f9efd1a60001', '新增入口', 1, '权限信息', 'sys/authorityAction_viewAdd.action', null, '2420', null);
insert into TB_SYS_AUTHORITY (AUTHORITY_ID, PARENT_ID, PARENT_IDS, AUTHORITY_NAME, AUTHORITY_TYPE, MODULE_NAME, URL, OPERATION, MENU_NO, MEMO)
values ('402890dc52f9efce0152f9efd1aa0003', '402890dc52f9efce0152f9efd1a80002', 'defaultpid,1397032583255590314,402890dc52f9efce0152f9efd1a60001,402890dc52f9efce0152f9efd1a80002', '新增', 1, '权限信息', 'sys/authorityAction_saveAuthority.action', null, '2421', null);
insert into TB_SYS_AUTHORITY (AUTHORITY_ID, PARENT_ID, PARENT_IDS, AUTHORITY_NAME, AUTHORITY_TYPE, MODULE_NAME, URL, OPERATION, MENU_NO, MEMO)
values ('402890dc52f9efce0152f9efd1ad0004', '402890dc52f9efce0152f9efd1a60001', 'defaultpid,1397032583255590314,402890dc52f9efce0152f9efd1a60001,402890dc52f9efce0152f9efd1a80002', '修改入口', 2, '权限信息', 'sys/authorityAction_viewEdit.action', null, '2430', null);
insert into TB_SYS_AUTHORITY (AUTHORITY_ID, PARENT_ID, PARENT_IDS, AUTHORITY_NAME, AUTHORITY_TYPE, MODULE_NAME, URL, OPERATION, MENU_NO, MEMO)
values ('402890dc52fa33e10152fa3946e10009', '402890dc52f9efce0152f9efd1ad0004', 'defaultpid,1397032583255590314,402890dc52f9efce0152f9efd1a60001,402890dc52f9efce0152f9efd1a80002,402890dc52f9efce0152f9efd1ad0004', '修改', 2, '权限信息', 'sys/authorityAction_updateAuthority.action', null, '2431', null);
insert into TB_SYS_AUTHORITY (AUTHORITY_ID, PARENT_ID, PARENT_IDS, AUTHORITY_NAME, AUTHORITY_TYPE, MODULE_NAME, URL, OPERATION, MENU_NO, MEMO)
values ('402890c652fd77e10152fdd8cd18000a', '05abae991c544b30b82efbc3434a3cbf', 'defaultpid,1397032583255590314,05abae991c544b30b82efbc3434a3cbf', '查询', 3, '用户管理', 'sys/userAction_getUserList.action', null, '2210', null);
insert into TB_SYS_AUTHORITY (AUTHORITY_ID, PARENT_ID, PARENT_IDS, AUTHORITY_NAME, AUTHORITY_TYPE, MODULE_NAME, URL, OPERATION, MENU_NO, MEMO)
values ('402890c652fd77e10152fdda7c740016', '05abae991c544b30b82efbc3434a3cbf', 'defaultpid,1397032583255590314,05abae991c544b30b82efbc3434a3cbf', '新增入口', 1, '用户管理', 'sys/userAction_viewAdd.action', null, '2220', null);
insert into TB_SYS_AUTHORITY (AUTHORITY_ID, PARENT_ID, PARENT_IDS, AUTHORITY_NAME, AUTHORITY_TYPE, MODULE_NAME, URL, OPERATION, MENU_NO, MEMO)
values ('402890fb53022bab0153023106910016', '0ddea4847c6f40a2a089e0c3a52aed2f', 'defaultpid,1397032583255590314,0ddea4847c6f40a2a089e0c3a52aed2f', '查询', 3, '角色管理', 'sys/roleAction_getRoleList.action', null, '22001', null);
insert into TB_SYS_AUTHORITY (AUTHORITY_ID, PARENT_ID, PARENT_IDS, AUTHORITY_NAME, AUTHORITY_TYPE, MODULE_NAME, URL, OPERATION, MENU_NO, MEMO)
values ('402890fb53022bab0153027ebb6c01ee', '402890fb53022bab015302766e74016f', 'defaultpid,1397032583255590314,0ddea4847c6f40a2a089e0c3a52aed2f,402890fb53022bab015302766e74016f', '设置角色权限', 7, '用户管理', 'sys/roleAuthorityAction_saveRoleAuthority.action', null, '220062', null);
insert into TB_SYS_AUTHORITY (AUTHORITY_ID, PARENT_ID, PARENT_IDS, AUTHORITY_NAME, AUTHORITY_TYPE, MODULE_NAME, URL, OPERATION, MENU_NO, MEMO)
values ('402890c652fd77e10152fddbd2ff0023', '402890c652fd77e10152fdda7c740016', 'defaultpid,1397032583255590314,05abae991c544b30b82efbc3434a3cbf,402890c652fd77e10152fdda7c740016', '新增', 1, '用户管理', 'sys/userAction_save.action', null, '2221', null);
insert into TB_SYS_AUTHORITY (AUTHORITY_ID, PARENT_ID, PARENT_IDS, AUTHORITY_NAME, AUTHORITY_TYPE, MODULE_NAME, URL, OPERATION, MENU_NO, MEMO)
values ('402890c652ff1de20152ff635bcd0013', '05abae991c544b30b82efbc3434a3cbf', 'defaultpid,1397032583255590314,05abae991c544b30b82efbc3434a3cbf', '修改入口', 2, '用户管理', 'sys/userAction_viewEdit.action', null, '2230', null);
insert into TB_SYS_AUTHORITY (AUTHORITY_ID, PARENT_ID, PARENT_IDS, AUTHORITY_NAME, AUTHORITY_TYPE, MODULE_NAME, URL, OPERATION, MENU_NO, MEMO)
values ('402890c652ff1de20152ff64afd90022', '402890c652ff1de20152ff635bcd0013', 'defaultpid,1397032583255590314,05abae991c544b30b82efbc3434a3cbf,402890c652ff1de20152ff635bcd0013', '修改', 2, '用户管理', 'sys/userAction_edit.action', null, '2231', null);
insert into TB_SYS_AUTHORITY (AUTHORITY_ID, PARENT_ID, PARENT_IDS, AUTHORITY_NAME, AUTHORITY_TYPE, MODULE_NAME, URL, OPERATION, MENU_NO, MEMO)
values ('402890c652ff1de20152ff65b1000032', '402890c652ff1de20152ff635bcd0013', 'defaultpid,1397032583255590314,05abae991c544b30b82efbc3434a3cbf,402890c652ff1de20152ff635bcd0013', '加载历史数据', 3, '用户管理', 'sys/userAction_getUser.action', null, '2231', null);
insert into TB_SYS_AUTHORITY (AUTHORITY_ID, PARENT_ID, PARENT_IDS, AUTHORITY_NAME, AUTHORITY_TYPE, MODULE_NAME, URL, OPERATION, MENU_NO, MEMO)
values ('402890c652ff1de20152ff6be6bd0068', '05abae991c544b30b82efbc3434a3cbf', 'defaultpid,1397032583255590314,05abae991c544b30b82efbc3434a3cbf', '删除', 4, '用户管理', 'sys/userAction_delete.action', null, '2240', null);
insert into TB_SYS_AUTHORITY (AUTHORITY_ID, PARENT_ID, PARENT_IDS, AUTHORITY_NAME, AUTHORITY_TYPE, MODULE_NAME, URL, OPERATION, MENU_NO, MEMO)
values ('402890c652ff1de20152ff74895d0091', '402890c652fd77e10152fdda7c740016', 'defaultpid,1397032583255590314,05abae991c544b30b82efbc3434a3cbf,402890c652fd77e10152fdda7c740016', '角色下拉列表', 3, '用户管理', 'sys/roleAction_getRoles.action', null, '2223', null);
insert into TB_SYS_AUTHORITY (AUTHORITY_ID, PARENT_ID, PARENT_IDS, AUTHORITY_NAME, AUTHORITY_TYPE, MODULE_NAME, URL, OPERATION, MENU_NO, MEMO)
values ('402890fb53022bab015302737b8a0134', '402890fb53022bab0153024435eb00e0', 'defaultpid,1397032583255590314,0ddea4847c6f40a2a089e0c3a52aed2f,402890fb53022bab0153024435eb00e0', '修改', 2, '角色管理', 'sys/roleAction_updateRole.action', null, '220041', null);
insert into TB_SYS_AUTHORITY (AUTHORITY_ID, PARENT_ID, PARENT_IDS, AUTHORITY_NAME, AUTHORITY_TYPE, MODULE_NAME, URL, OPERATION, MENU_NO, MEMO)
values ('402890fb53022bab0153023b297b00c5', '402890fb53022bab01530239c5c60077', 'defaultpid,1397032583255590314,0ddea4847c6f40a2a089e0c3a52aed2f,402890fb53022bab01530239c5c60077', '新增', 1, '角色管理', 'sys/roleAction_saveRole.action', null, '220031', null);
insert into TB_SYS_AUTHORITY (AUTHORITY_ID, PARENT_ID, PARENT_IDS, AUTHORITY_NAME, AUTHORITY_TYPE, MODULE_NAME, URL, OPERATION, MENU_NO, MEMO)
values ('402890fb53022bab015302766e74016f', '0ddea4847c6f40a2a089e0c3a52aed2f', 'defaultpid,1397032583255590314,0ddea4847c6f40a2a089e0c3a52aed2f', '授权入口', 7, '角色管理', 'sys/roleAction_viewTab.action', null, '22005', null);
insert into TB_SYS_AUTHORITY (AUTHORITY_ID, PARENT_ID, PARENT_IDS, AUTHORITY_NAME, AUTHORITY_TYPE, MODULE_NAME, URL, OPERATION, MENU_NO, MEMO)
values ('402890fb53022bab01530274f2780151', '402890fb53022bab0153024435eb00e0', 'defaultpid,1397032583255590314,0ddea4847c6f40a2a089e0c3a52aed2f,402890fb53022bab0153024435eb00e0', '加载角色数据', 3, '角色管理', 'sys/roleAction_getRole.action', null, '220042', null);
commit;
prompt 36 records loaded
prompt Loading TB_SYS_LOGIN_LOG...
prompt Table is empty
prompt Loading TB_SYS_ORGANIZATION...
insert into TB_SYS_ORGANIZATION (ORG_ID, ORG_NAME, PARENT_ID, PARENT_IDS, ADDRESS, POST_CODE, CONTACT_MAN, TELL, FAX, EMAIL, STATUS)
values ('402890d55303f01a01530409ba840025', '机车学院', '402890dc52fa42b80152fa4504530048', 'defaultpid,402890dc52fa42b80152fa4504530048', '111', '1111', 'xx', '34343', '111111', '1912813835@qq.com', '1');
insert into TB_SYS_ORGANIZATION (ORG_ID, ORG_NAME, PARENT_ID, PARENT_IDS, ADDRESS, POST_CODE, CONTACT_MAN, TELL, FAX, EMAIL, STATUS)
values ('402890dc52fa42b80152fa4504530048', '北京理工大学珠海学院', 'defaultpid', 'defaultpid', '广东省珠海市唐家湾金凤路6号', '000000', '董事长', '000000000000', '00000', '111@139.com', '1');
insert into TB_SYS_ORGANIZATION (ORG_ID, ORG_NAME, PARENT_ID, PARENT_IDS, ADDRESS, POST_CODE, CONTACT_MAN, TELL, FAX, EMAIL, STATUS)
values ('402890dc52fa33e10152fa3946e10009', '计算机学院', '402890dc52fa42b80152fa4504530048', 'defaultpid,402890dc52fa42b80152fa4504530048', '知行楼', '000000', '院长', '00000000', '00000', '111@163.com', '1');
commit;
prompt 3 records loaded
prompt Loading TB_SYS_ROLE...
insert into TB_SYS_ROLE (ROLE_ID, ROLE_NAME, CREATE_TIME, ROLE_NO, MEMO)
values ('402890c652ff1de20152ff7ce2c600be', '总辅导员', to_date('21-02-2016', 'dd-mm-yyyy'), '1002', '总辅');
insert into TB_SYS_ROLE (ROLE_ID, ROLE_NAME, CREATE_TIME, ROLE_NO, MEMO)
values ('402890c652ff1de20152ff7e46b800bf', '教师', to_date('21-02-2016', 'dd-mm-yyyy'), '1003', '普通教职工');
insert into TB_SYS_ROLE (ROLE_ID, ROLE_NAME, CREATE_TIME, ROLE_NO, MEMO)
values ('adminroleid', '管理员', to_date('18-02-2016', 'dd-mm-yyyy'), '1001', '管理系统');
commit;
prompt 3 records loaded
prompt Loading TB_SYS_ROLE_AUTHORITY...
insert into TB_SYS_ROLE_AUTHORITY (ID, AUTHORITY_ID, ROLE_ID)
values ('402890d553048f460153048f4a590000', '402890c652ff1de20152ff68a42b0043', 'adminroleid');
insert into TB_SYS_ROLE_AUTHORITY (ID, AUTHORITY_ID, ROLE_ID)
values ('402890d553048f460153048f4a770001', '402890c652ff1de20152ff69b8e60055', 'adminroleid');
insert into TB_SYS_ROLE_AUTHORITY (ID, AUTHORITY_ID, ROLE_ID)
values ('402890d553048f460153048f4a7b0002', '402890c652ff1de20152ff738c70007c', 'adminroleid');
insert into TB_SYS_ROLE_AUTHORITY (ID, AUTHORITY_ID, ROLE_ID)
values ('402890d553048f460153048f4a7e0003', '402890c652ff1de20152ff7489ae00a7', 'adminroleid');
insert into TB_SYS_ROLE_AUTHORITY (ID, AUTHORITY_ID, ROLE_ID)
values ('402890d553048f460153048f4a820004', '1397032583255590314', 'adminroleid');
insert into TB_SYS_ROLE_AUTHORITY (ID, AUTHORITY_ID, ROLE_ID)
values ('402890d553048f460153048f4a860005', '402890dc52f9efce0152f9efd1a60001', 'adminroleid');
insert into TB_SYS_ROLE_AUTHORITY (ID, AUTHORITY_ID, ROLE_ID)
values ('402890d553048f460153048f4a890006', '402890fb53022bab015302331885005e', 'adminroleid');
insert into TB_SYS_ROLE_AUTHORITY (ID, AUTHORITY_ID, ROLE_ID)
values ('402890d553048f460153048f4a8d0007', '402890fb53022bab01530239c5c60077', 'adminroleid');
insert into TB_SYS_ROLE_AUTHORITY (ID, AUTHORITY_ID, ROLE_ID)
values ('402890d553048f460153048f4a910008', '402890fb53022bab0153024435eb00e0', 'adminroleid');
insert into TB_SYS_ROLE_AUTHORITY (ID, AUTHORITY_ID, ROLE_ID)
values ('402890d553048f460153048f4a950009', '402890fb53022bab0153027ac1430191', 'adminroleid');
insert into TB_SYS_ROLE_AUTHORITY (ID, AUTHORITY_ID, ROLE_ID)
values ('402890d553048f460153048f4a99000a', '402890fb53022bab01530283cb810295', 'adminroleid');
insert into TB_SYS_ROLE_AUTHORITY (ID, AUTHORITY_ID, ROLE_ID)
values ('402890d553048f460153048f4a9c000b', '402890fb53022bab01530285e85b02b7', 'adminroleid');
insert into TB_SYS_ROLE_AUTHORITY (ID, AUTHORITY_ID, ROLE_ID)
values ('402890d553048f460153048f4a9f000c', '402890fb53022bab01530286f50302da', 'adminroleid');
insert into TB_SYS_ROLE_AUTHORITY (ID, AUTHORITY_ID, ROLE_ID)
values ('402890d553048f460153048f4aa3000d', '402890fb53022bab01530289fdec038c', 'adminroleid');
insert into TB_SYS_ROLE_AUTHORITY (ID, AUTHORITY_ID, ROLE_ID)
values ('402890d553048f460153048f4aae000e', '05abae991c544b30b82efbc3434a3cbf', 'adminroleid');
insert into TB_SYS_ROLE_AUTHORITY (ID, AUTHORITY_ID, ROLE_ID)
values ('402890d553048f460153048f4ab1000f', '0ddea4847c6f40a2a089e0c3a52aed2f', 'adminroleid');
insert into TB_SYS_ROLE_AUTHORITY (ID, AUTHORITY_ID, ROLE_ID)
values ('402890d553048f460153048f4ab60010', '12d0657555684a1696257fffa3e6dace', 'adminroleid');
insert into TB_SYS_ROLE_AUTHORITY (ID, AUTHORITY_ID, ROLE_ID)
values ('402890d553048f460153048f4ab90011', '402890dc52f9efce0152f9efd18d0000', 'adminroleid');
insert into TB_SYS_ROLE_AUTHORITY (ID, AUTHORITY_ID, ROLE_ID)
values ('402890d553048f460153048f4abc0012', '402890dc52f9efce0152f9efd1a80002', 'adminroleid');
insert into TB_SYS_ROLE_AUTHORITY (ID, AUTHORITY_ID, ROLE_ID)
values ('402890d553048f460153048f4abf0013', '402890dc52f9efce0152f9efd1aa0003', 'adminroleid');
insert into TB_SYS_ROLE_AUTHORITY (ID, AUTHORITY_ID, ROLE_ID)
values ('402890d553048f460153048f4ac30014', '402890dc52f9efce0152f9efd1ad0004', 'adminroleid');
insert into TB_SYS_ROLE_AUTHORITY (ID, AUTHORITY_ID, ROLE_ID)
values ('402890d553048f460153048f4ac60015', '402890dc52fa33e10152fa3946e10009', 'adminroleid');
insert into TB_SYS_ROLE_AUTHORITY (ID, AUTHORITY_ID, ROLE_ID)
values ('402890d553048f460153048f4ac90016', '402890c652fd77e10152fdd8cd18000a', 'adminroleid');
insert into TB_SYS_ROLE_AUTHORITY (ID, AUTHORITY_ID, ROLE_ID)
values ('402890d553048f460153048f4ace0017', '402890c652fd77e10152fdda7c740016', 'adminroleid');
insert into TB_SYS_ROLE_AUTHORITY (ID, AUTHORITY_ID, ROLE_ID)
values ('402890d553048f460153048f4ad30018', '402890fb53022bab0153023106910016', 'adminroleid');
insert into TB_SYS_ROLE_AUTHORITY (ID, AUTHORITY_ID, ROLE_ID)
values ('402890d553048f460153048f4ad70019', '402890fb53022bab0153027ebb6c01ee', 'adminroleid');
insert into TB_SYS_ROLE_AUTHORITY (ID, AUTHORITY_ID, ROLE_ID)
values ('402890d553048f460153048f4ada001a', '402890c652fd77e10152fddbd2ff0023', 'adminroleid');
insert into TB_SYS_ROLE_AUTHORITY (ID, AUTHORITY_ID, ROLE_ID)
values ('402890d553048f460153048f4add001b', '402890c652ff1de20152ff635bcd0013', 'adminroleid');
insert into TB_SYS_ROLE_AUTHORITY (ID, AUTHORITY_ID, ROLE_ID)
values ('402890d553048f460153048f4ae2001c', '402890c652ff1de20152ff64afd90022', 'adminroleid');
insert into TB_SYS_ROLE_AUTHORITY (ID, AUTHORITY_ID, ROLE_ID)
values ('402890d553048f460153048f4ae5001d', '402890c652ff1de20152ff65b1000032', 'adminroleid');
insert into TB_SYS_ROLE_AUTHORITY (ID, AUTHORITY_ID, ROLE_ID)
values ('402890d553048f460153048f4ae9001e', '402890c652ff1de20152ff6be6bd0068', 'adminroleid');
insert into TB_SYS_ROLE_AUTHORITY (ID, AUTHORITY_ID, ROLE_ID)
values ('402890d553048f460153048f4aed001f', '402890c652ff1de20152ff74895d0091', 'adminroleid');
insert into TB_SYS_ROLE_AUTHORITY (ID, AUTHORITY_ID, ROLE_ID)
values ('402890d553048f460153048f4af00020', '402890fb53022bab015302737b8a0134', 'adminroleid');
insert into TB_SYS_ROLE_AUTHORITY (ID, AUTHORITY_ID, ROLE_ID)
values ('402890d553048f460153048f4af40021', '402890fb53022bab0153023b297b00c5', 'adminroleid');
insert into TB_SYS_ROLE_AUTHORITY (ID, AUTHORITY_ID, ROLE_ID)
values ('402890d553048f460153048f4af70022', '402890fb53022bab015302766e74016f', 'adminroleid');
insert into TB_SYS_ROLE_AUTHORITY (ID, AUTHORITY_ID, ROLE_ID)
values ('402890d553048f460153048f4afa0023', '402890fb53022bab01530274f2780151', 'adminroleid');
insert into TB_SYS_ROLE_AUTHORITY (ID, AUTHORITY_ID, ROLE_ID)
values ('402890c652ff1de20152ff9e948000c2', '1397032583255590314', '402890c652ff1de20152ff7e46b800bf');
insert into TB_SYS_ROLE_AUTHORITY (ID, AUTHORITY_ID, ROLE_ID)
values ('402890c652ff1de20152ff9e948100c3', '05abae991c544b30b82efbc3434a3cbf', '402890c652ff1de20152ff7e46b800bf');
insert into TB_SYS_ROLE_AUTHORITY (ID, AUTHORITY_ID, ROLE_ID)
values ('402890c652ff1de20152ff9e948300c4', '402890c652fd77e10152fdd8cd18000a', '402890c652ff1de20152ff7e46b800bf');
commit;
prompt 39 records loaded
prompt Loading TB_SYS_USER...
insert into TB_SYS_USER (USER_ID, ORG_ID, EMPLOY_NO, EMPLOY_NAME, CREATE_TIME, SEX, TELL, STATUS, ADDRESS, EMAIL, PASSWORD, USER_TYPE)
values ('adminuserid', '402890dc52fa33e10152fa3946e10009', 'admin', '管理员', to_date('20-02-2016', 'dd-mm-yyyy'), 0, null, 1, '北京', '111@qq.com', '123456', '1');
insert into TB_SYS_USER (USER_ID, ORG_ID, EMPLOY_NO, EMPLOY_NAME, CREATE_TIME, SEX, TELL, STATUS, ADDRESS, EMAIL, PASSWORD, USER_TYPE)
values ('402890c652ff1de20152ff5623f50011', '402890dc52fa33e10152fa3946e10009', '10011', '魏老师', to_date('20-02-2016', 'dd-mm-yyyy'), 0, '17333333333', 1, '北理工', '1912813835@qq.com', '123456', '1');
commit;
prompt 2 records loaded
prompt Loading TB_SYS_USER_ROLE...
insert into TB_SYS_USER_ROLE (ID, USER_ID, ROLE_ID)
values ('402890c652ff1de20152ff2d3023000d', 'adminuserid', 'adminroleid');
insert into TB_SYS_USER_ROLE (ID, USER_ID, ROLE_ID)
values ('402890c652ff1de20152ff9d188e00c1', '402890c652ff1de20152ff5623f50011', '402890c652ff1de20152ff7e46b800bf');
insert into TB_SYS_USER_ROLE (ID, USER_ID, ROLE_ID)
values ('402890c652ff1de20152ff2d3025000e', 'adminuserid', 'adminroleid');
insert into TB_SYS_USER_ROLE (ID, USER_ID, ROLE_ID)
values ('402890c652ff1de20152ff5623f70012', '402890c652ff1de20152ff5623f50011', 'adminroleid');
commit;
prompt 4 records loaded
prompt Enabling foreign key constraints for TB_SYS_ROLE_AUTHORITY...
alter table TB_SYS_ROLE_AUTHORITY enable constraint SYS_C006452;
alter table TB_SYS_ROLE_AUTHORITY enable constraint SYS_C006453;
prompt Enabling foreign key constraints for TB_SYS_USER...
alter table TB_SYS_USER enable constraint SYS_C006449;
prompt Enabling foreign key constraints for TB_SYS_USER_ROLE...
alter table TB_SYS_USER_ROLE enable constraint SYS_C006450;
alter table TB_SYS_USER_ROLE enable constraint SYS_C006451;
prompt Enabling triggers for TB_SYS_AUTHORITY...
alter table TB_SYS_AUTHORITY enable all triggers;
prompt Enabling triggers for TB_SYS_LOGIN_LOG...
alter table TB_SYS_LOGIN_LOG enable all triggers;
prompt Enabling triggers for TB_SYS_ORGANIZATION...
alter table TB_SYS_ORGANIZATION enable all triggers;
prompt Enabling triggers for TB_SYS_ROLE...
alter table TB_SYS_ROLE enable all triggers;
prompt Enabling triggers for TB_SYS_ROLE_AUTHORITY...
alter table TB_SYS_ROLE_AUTHORITY enable all triggers;
prompt Enabling triggers for TB_SYS_USER...
alter table TB_SYS_USER enable all triggers;
prompt Enabling triggers for TB_SYS_USER_ROLE...
alter table TB_SYS_USER_ROLE enable all triggers;
set feedback on
set define on
prompt Done.
