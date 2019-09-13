# ShiroLibrary图书馆权限管理系统
## 权限划分
图书馆权限管理系统分为三个权限：<br/>
* admin权限<br/>
* literature_user权限<br/>
* electronics_user权限<br/>
## 权限作用
* admin权限：用户的增删改查<br/>
* literature_user权限：文学类图书的增删改查<br/>
* electronics_user权限：电子类图书的增删改查<br/>
## 用到技术
* Spring
* SpringMVC
* Shiro
## 数据库创建
三张表分别是：users  roles  roles_permission<br/>
> users<br/>
>> id<br/>
>> username<br/>
>> password<br/>

> roles<br/>
>> user_name<br/>
>> role_name<br/>

> roles_permission<br/>
>> id<br/>
>> role_name<br/>
>> permission<br/>
