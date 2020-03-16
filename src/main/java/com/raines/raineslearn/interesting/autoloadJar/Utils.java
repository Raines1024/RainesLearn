package com.raines.raineslearn.interesting.autoloadJar;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * Java有个比较傻的地方，没有提供从程序目录加载jar包的功能，包括class-path等的配置，全部基于【相对于当前工作目录路径】和【绝对路径】两种，并没有【相对于程序路径】。
 * 网上找了一些资料，东拼西凑把这个功能实现了。在需要的时候调用即可。
 */
public class Utils {
    public static String getApplicationFolder() {
        String path = Utils.class.getProtectionDomain().getCodeSource().getLocation().getPath();
        return new File(path).getParent();
    }

    public static void loadJarsFromAppFolder(String sub_folder) throws Exception {
        String path = getApplicationFolder() + File.pathSeparator + sub_folder;
        File f = new File(path);
        if (f.isDirectory()) {
            for (File subf : f.listFiles()) {
                if (subf.isFile()) {
                    loadJarFile(subf);
                }
            }
        } else {
            loadJarFile(f);
        }
    }

    public static void loadJarFile(File path) throws Exception {
        URL url = path.toURI().toURL();
        URLClassLoader classLoader = (URLClassLoader) ClassLoader.getSystemClassLoader();
        Method method = URLClassLoader.class.getDeclaredMethod("addURL", URL.class);
        method.setAccessible(true);
        method.invoke(classLoader, url);
    }
}
