package com.raines.raineslearn.interesting.compileJava;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.util.Arrays;

import javax.tools.FileObject;
import javax.tools.ForwardingJavaFileManager;
import javax.tools.JavaCompiler;
import javax.tools.JavaCompiler.CompilationTask;
import javax.tools.JavaFileManager;
import javax.tools.JavaFileObject;
import javax.tools.SimpleJavaFileObject;
import javax.tools.ToolProvider;
import javax.tools.JavaFileObject.Kind;

/**
 * 1.基于java做的脚本语言（实际就是全自动把脚本转成java代码，然后编译执行，使用效果就如bash/python/php一样可以直接跑代码）
 * 2.基于java语法的模版引擎
 */
public class CompileJavaSourceInMemory {

    private static JavaCompiler javac = ToolProvider.getSystemJavaCompiler();

    public static void main(String[] args) throws Exception {

        if (javac == null) {
            throw new Exception("java compiler is not found");
        }

        String className = "wuguike.test";

        String code = "package wuguike; public class test { public test() { System.out.println(\"hello world\"); } }";

        URI virtualPath = URI.create("string:///" + className.replace('.', '/') + ".java");

        ByteArrayOutputStream os = new ByteArrayOutputStream();

        SimpleJavaFileObject fo = new SimpleJavaFileObject(virtualPath, Kind.SOURCE) {
            @Override
            public CharSequence getCharContent(boolean ignoreEncodingErrors) throws IOException {
                return code;
            }

            @Override
            public OutputStream openOutputStream() throws IOException {
                return os;
            }
        };

        ForwardingJavaFileManager<JavaFileManager> fm = new ForwardingJavaFileManager<JavaFileManager>(
                javac.getStandardFileManager(null, null, null)) {
            @Override
            public JavaFileObject getJavaFileForOutput(Location location, String className, Kind kind,
                                                       FileObject sibling) throws IOException {
                return fo;
            }
        };

        CompilationTask task = javac.getTask(null, fm, null, null, null, Arrays.asList(fo));

        if (task.call()) {
            ClassLoader cl = new ClassLoader() {
                @Override
                protected Class<?> findClass(String name) throws ClassNotFoundException {
                    byte[] byteCode = os.toByteArray();
                    return defineClass(name, byteCode, 0, byteCode.length);
                }
            };
            cl.loadClass(className).newInstance();
        } else {
            throw new Exception("compile faild");
        }
    }

}