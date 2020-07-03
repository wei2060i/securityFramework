## 权限校验注解说明
1、实现接口com.security.securityframework.sky.service.ISkySecurity  
2、采用token登陆认证  
3、@SkyPassLogin--->用在不需要登录的方法  
4、@SkyRoleSecurity-->角色校验 
* roleListNeed 角色白名单,拥有所有角色的人,可以访问  
* roleListOnlyOne  角色白名单,拥有任一角色的人,可以访问
* roleListExclude  角色黑名单,拥有其中一个,就不能访问  
5、@SkyPermissionSecurity 功能同角色。
