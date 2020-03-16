package com.raines.raineslearn.annotation.custom.project.classAnnotation;

//调用注解并赋值
@MyAnnotation(metaAnnotation = @MetaAnnotation(birthday = "我的出生日期为1995-8-88"), color = "red", array = {23, 26})
public class AnnotationTest {

    public static void main(String[] args) {
        // 检查类AnnotationTest是否含有@MyAnnotation注解
        if (AnnotationTest.class.isAnnotationPresent(MyAnnotation.class)) {
            // 若存在就获取注解
            MyAnnotation annotation = (MyAnnotation) AnnotationTest.class.getAnnotation(MyAnnotation.class);
            System.out.println(annotation);
            // 获取注解属性
            System.out.println(annotation.color());
            System.out.println(annotation.value());
            // 数组
            int[] arrs = annotation.array();
            for (int arr : arrs) {
                System.out.println(arr);
            }
            // 枚举
            Gender gender = annotation.gender();
            System.out.println("性别为：" + gender);
            // 获取注解属性
            MetaAnnotation meta = annotation.metaAnnotation();
            System.out.println(meta.birthday());
        }
    }

}
