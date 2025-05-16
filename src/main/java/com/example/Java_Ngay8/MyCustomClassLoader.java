package com.example.Java_Ngay8;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class MyCustomClassLoader extends ClassLoader {

    private final Map<String, Class<?>> loadedClasses = new HashMap<>();
    private final String pluginsDir;

    public MyCustomClassLoader(String pluginsDir) {
        this.pluginsDir = pluginsDir;
    }

    // Lazy Loading: Chỉ load khi thực sự cần
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        if (loadedClasses.containsKey(name)) {
            return loadedClasses.get(name);
        }

        try {
            String classPath = pluginsDir + "/" + name.replace('.', '/') + ".class";
            Path path = Paths.get(classPath);
            if (Files.exists(path)) {
                byte[] classBytes = Files.readAllBytes(path);
                Class<?> clazz = defineClass(name, classBytes, 0, classBytes.length);
                loadedClasses.put(name, clazz);
                return clazz;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        throw new ClassNotFoundException("Class " + name + " not found.");
    }

    // Eager Loading: Load toàn bộ class từ thư mục plugins
    public void eagerLoadClasses() {
        File dir = new File(pluginsDir);
        if (!dir.exists()) {
            System.out.println("Plugins directory not found.");
            return;
        }

        File[] files = dir.listFiles((d, name) -> name.endsWith(".jar"));
        if (files != null) {
            for (File file : files) {
                try (URLClassLoader child = new URLClassLoader(
                        new URL[]{file.toURI().toURL()},
                        this.getClass().getClassLoader()
                )) {
                    System.out.println("[Eager Loading] Loaded: " + file.getName());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
