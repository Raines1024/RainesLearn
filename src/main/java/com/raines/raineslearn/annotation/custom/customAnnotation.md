## Java自定义注解基础知识

### 介绍
注解相当于一种标记，在程序中加了注解就等于为程序打上了某种标记，没加，则等于没有某种标记，以后，javac编译器，开发工具和其他程序可以用反射来了解你的类及各种元素上有无何种标记，看你有什么标记，就去干相应的事。标记可以加在包，类，字段，方法，方法的参数以及局部变量上。  

#### Java中提供了四种元注解，专门负责注解其他的注解，分别如下:  
- @Retention元注解，表示需要在什么级别保存该注释信息（生命周期）。可选的RetentionPoicy参数包括：  
RetentionPolicy.SOURCE: 停留在java源文件，编译器被丢掉   
RetentionPolicy.CLASS：停留在class文件中，但会被VM丢弃（默认）  
RetentionPolicy.RUNTIME：内存中的字节码，VM将在运行时也保留注解，因此可以通过反射机制读取注解的信息   
- @Target元注解，默认值为任何元素，表示该注解用于什么地方。可用的ElementType参数包括   
ElementType.CONSTRUCTOR: 构造器声明  
ElementType.FIELD: 成员变量、对象、属性（包括enum实例）  
ElementType.LOCAL_VARIABLE: 局部变量声明  
ElementType.METHOD: 方法声明  
ElementType.PACKAGE: 包声明   
ElementType.PARAMETER: 参数声明   
ElementType.TYPE: 类、接口（包括注解类型)或enum声明  
- @Documented注解将注解包含在JavaDoc中  
- @Inheried注解允许子类继承父类中的注解  

### 注解种类
java的自定义注解可以分为三类：没有任何元素的注解，有一个元素的注解和有多个元素的注解。  
1. Marker注解  
这类注解没有任何元素，此类注解仅仅是一个标示。  
2. 单值注解  
只接受单值类型，数据成员使用单词value指定。指定成员的语法与声明方法类似。  
但是如果数据成员不使用value定义，新定义如下所示：  


    public @interface Good{
       String description();
    }
    
现在，需要使用下面的注解方式
    
    @Good(description="this good")
注意：数据成员使用默认名称value时候，我们只指定了目标字符串，而省略了成员名称，这次我们需要显示拼写出数据成员的名称description，如果不这么做，编译器将会在编译过程中产生错误。  
3. 多值注解   

### 设置默认值  
java允许为任何数据成员指定默认值，这可以使用default关键字来完成。  
当使用默认值注解的时候，target成员可以不指定，除非想为target设置不同的值。  

### 注解的定义规则   
定义一个注解还是很简单的，需要遵照以下几个规则就可以了：  
（1）注解声明以@interface开设，随后是注解的名称。  
（2）为了创建注解的参数，需要使用参数的类型声明方法：  
方法声明不应包含任何参数；  
方法声明不应包含任何throws子句；  
方法的返回类型应该为：基本类型，字符串，类，枚举，上述类型的数组。  