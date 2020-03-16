## 利用java反射调用私有方法、访问私有属性

利用反射，首先是Class对象的获取，之后是Method和Field对象的获取。以Method为例，从文档中可以看到：  
getMethod()方法返回的是public的Method对象，而getDeclaredMethod()返回的Method对象可以是非public的。Field的方法同理。  
访问私有属性和方法，在使用前要通过AccessibleObject类（Constructor、 Field和Method类的基类）中的setAccessible()方法来抑制Java访问权限的检查。  