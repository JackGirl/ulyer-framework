# ulyer-framework

#### 介绍
自己实现一遍spring  mybatis mvc  viewResolver  权限 仅仅使用hutool-core lombok、servlet-api 依赖

#### 软件架构
Factory->sqlSession->executor


#### 安装教程

1.建表 user
2.修改orm.yml
3.运行下方测试    


#### 测试

```java
  OrmConfiguration configuration = ResourceConfigurationLoader.loadConfiguration("orm.yml");
        OrmFactory factory = new DefaultOrmFactory(configuration);
        factory.setDataSource(dataSource());
        UserMapper mapper = factory.getMapper(UserMapper.class);
        User user = mapper.getById("1");
        System.out.println(user);
        SqlSession sqlSession = factory.createSqlSession();
        User u = new User();
        u.setName("orm 插入测试");
        u.setId("222");
        sqlSession.insert(UserMapper.class.getName()+"."+"saveUser",u);
        List<Map> users = mapper.listUser();
        System.out.println(users);
```