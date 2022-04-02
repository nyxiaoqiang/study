CREATE TABLE user (
    id int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
    username varchar(64) DEFAULT NULL COMMENT '用户名',
    password varchar(64) DEFAULT NULL COMMENT '密码',
    create_time timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
PRIMARY KEY (id)) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';

insert into user values(1,"李雪强","123456",now(),now());
insert into user values(2,"普通玩家1","1234561",now(),now());
insert into user values(3,"普通玩家2","1234562",now(),now());
insert into user values(4,"vip玩家1","vip1234561",now(),now());
insert into user values(5,"vip玩家2","vip1234562",now(),now());
insert into user values(6,"vip玩家3","vip1234563",now(),now());

CREATE TABLE role (
    id int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
    role_name varchar(64) DEFAULT NULL COMMENT '角色',
    create_time timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
PRIMARY KEY (id)) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色表';

insert into role values(1,'admin',now(),now()),(2,'normal',now(),now()),(3,'vip',now(),now());

CREATE TABLE perm(
    id int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
    perm_name varchar(64) DEFAULT NULL COMMENT '权限名',
    create_time timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
PRIMARY KEY (id)) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='权限表';

insert into perm values(1,'/page/normal',now(),now()),(2,'/page/vip',now(),now()),(3,'/page/admin',now(),now())


CREATE TABLE user_role(
    id int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
    user_id int(11) DEFAULT NULL COMMENT '用户id',
    role_id int(11) DEFAULT NULL COMMENT '角色id',
    create_time timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
PRIMARY KEY (id)) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户角色表';

insert into user_role values(1,1,1,now(),now()),(2,2,2,now(),now()),(3,3,3,now(),now())

CREATE TABLE role_perm(
    id int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
    role_id int(11) DEFAULT NULL COMMENT '角色id',
    perm_id int(11) DEFAULT NULL COMMENT '权限id',
    create_time timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
PRIMARY KEY (id)) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色权限表';

insert into role_perm values(1,1,1,now(),now()),(2,1,2,now(),now()),(3,1,3,now(),now()),(4,2,2,now(),now()),(5,2,3,now(),now()),(6,3,3,now(),now());