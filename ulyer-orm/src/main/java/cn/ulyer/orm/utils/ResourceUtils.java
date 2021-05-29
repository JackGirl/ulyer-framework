package cn.ulyer.orm.utils;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;

import java.io.*;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class ResourceUtils {


    public static void loadClassFromFile(String packName,  Set<Class<?>> jarClasses) throws ClassNotFoundException, IOException {
        URL url = getResourceUrl(packName.replace(".", "/"));
        if(url==null){
            return;
        }
        String protocol = url.getProtocol();
        if (StrUtil.equals(protocol, "jar")) {
            JarFile jarFile = ((JarURLConnection) url.openConnection()).getJarFile();
            Enumeration<JarEntry> jarEntryEnumeration = jarFile.entries();
            while (jarEntryEnumeration.hasMoreElements()) {
                loadClassFromJarFile(packName, jarEntryEnumeration.nextElement(), jarClasses);
            }
        }
        File file = new File(url.getPath());
        if (!file.exists()) {
            return;
        }
        File[] files = file.listFiles();
        for (File item : files) {
            String fileName = item.getName();
            if (item.isDirectory()) {
                loadClassFromFile(packName + "." + fileName, jarClasses);
                continue;
            }
            if (FileUtil.pathEndsWith(item, "class")) {
                fileName = fileName.replace(".class", "");
                jarClasses.add(Class.forName(packName + "." + fileName));
            }
        }
    }


    public static void loadClassFromJarFile(String packName, JarEntry jarEntry, Set<Class<?>> jarClasses) throws ClassNotFoundException {
        String name = jarEntry.getName();
        if (name.startsWith(packName.replace(".", "/"))) {
            if (name.endsWith(".class")) {
                name = name.replace("/", ".").replace(".class", "");
                String packageName = name.substring(name.indexOf(packName));
                jarClasses.add(Class.forName(packageName));
            }
        }
    }

    public static List<InputStream> loadFileFromDir(File mapperLocationDir) throws FileNotFoundException {
        List<InputStream> files = new LinkedList<>();
        File[] mapperFiles = mapperLocationDir.listFiles();
        for (File mapperFile : mapperFiles) {
            if(mapperFile.isDirectory()){
                files.addAll(loadFileFromDir(mapperFile));
            }
                files.add(new FileInputStream(mapperFile));
        }
        return files;
    }

    public static List<InputStream> loadInputStreamFromURL(String path,String fileExt) throws IOException {
        URL url =  getResourceUrl(path);
        if(url==null){
            return new LinkedList<>();
        }
        List<InputStream> streams = new LinkedList<>();
        if (StrUtil.equals(url.getProtocol(), "jar")) {
            URLConnection connection = url.openConnection();
            JarFile jarFile = ((JarURLConnection) url.openConnection()).getJarFile();
            Enumeration<JarEntry> jarEntryEnumeration = jarFile.entries();
            while (jarEntryEnumeration.hasMoreElements()) {
                JarEntry entry = jarEntryEnumeration.nextElement();
               if( StrUtil.startWithIgnoreEquals(entry.getName(),path)){
                   if(entry.isDirectory()){
                       continue;
                   }
                   if(StrUtil.isNotBlank(fileExt)){
                       if(!StrUtil.endWithIgnoreCase(entry.getName(),fileExt)){
                           continue;
                       }
                   }
                   streams.add(getResourceUrl(entry.getName()).openStream());

               }
            }
        }else{
            return loadFileFromDir(new File(getResourceUrl(path).getPath()));
        }
        return streams;
    }


    public static URL getResourceUrl(String path){
        return Thread.currentThread().getContextClassLoader().getResource(path);
    }


}
