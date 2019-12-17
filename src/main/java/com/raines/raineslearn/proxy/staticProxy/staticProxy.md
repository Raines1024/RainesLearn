所谓静态代理，就是代理类是由程序员自己编写的，在编译期就确定好了的。

该包下是一个简单的静态的代理模式的实现。代理模式中的所有角色（代理对象、目标对象、目标对象的接口）等都是在编译期就确定好的。

静态代理的用途 控制真实对象的访问权限 通过代理对象控制对真实对象的使用权限。

避免创建大对象 通过使用一个代理小对象来代表一个真实的大对象，可以减少系统资源的消耗，对系统进行优化并提高运行速度。

增强真实对象的功能 这个比较简单，通过代理可以在调用真实对象的方法的前后增加额外功能。