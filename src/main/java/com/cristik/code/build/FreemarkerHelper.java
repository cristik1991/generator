package com.cristik.code.build;

import freemarker.cache.FileTemplateLoader;
import freemarker.cache.TemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.Map;

/**
 * @author cristik
 */
public class FreemarkerHelper {

    private static final Logger logger = LoggerFactory.getLogger(FreemarkerHelper.class);

    private static final File CURRENT_DIRECTORY = new File("");

    private static final String[] PATH_STRING = {"\\", "/"};

    /**
     * 将模板和数据生成文件内容
     */
    public static String convertTemplate(String templatePath, String templateName, Map<String, Object> context)
            throws IOException {
        Configuration configuration = new Configuration();
        configuration.setDefaultEncoding("utf-8");
        TemplateLoader templateLoader = new FileTemplateLoader(new File(templatePath));
        configuration.setTemplateLoader(templateLoader);
        Template template = configuration.getTemplate(templateName);
        StringWriter writer = null;
        try {
            writer = new StringWriter();
            template.process(context, writer);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            writer.close();
        }
        return writer.toString();
    }

    /**
     * 生成文件
     */
    public static void buildFile(String directory, String path, String fileName, Boolean override, String content)
            throws IOException {
        String absolutePath = getWorkspace() + "/" + formatPath(directory) + "/" + formatPath(path) + "/" + fileName;
        logger.debug("output target directory is {}", absolutePath);
        File file = new File(absolutePath);
        if (!file.getParentFile().exists()) {
            logger.debug("parent path {} not exist mkdir paths", file.getParentFile().getAbsolutePath());
            file.getParentFile().mkdirs();
        }
        if (file.exists() && !override) {
            logger.info("will not override existing file={}", file.getAbsolutePath());
            return;
        }
        if (!file.exists()) {
            logger.debug("Create new File {}", file.getAbsolutePath());
            file.createNewFile();
        }
        FileOutputStream fos = new FileOutputStream(file, false);
        OutputStreamWriter osw = new OutputStreamWriter(fos, "utf-8");
        BufferedWriter bw = new BufferedWriter(osw);
        bw.write(content);
        bw.close();
    }

    /**
     * 获取当前项目绝对路径
     */
    private static String getWorkspace() throws IOException {
        return formatPath(CURRENT_DIRECTORY.getCanonicalPath());
    }

    /**
     * 将.和\替换成/并截掉路径后的/部分
     */
    private static String formatPath(String path) {
        String tempPath = path.replaceAll("\\\\", "/");
        if (tempPath.endsWith(PATH_STRING[0]) || tempPath.endsWith(PATH_STRING[1])) {
            tempPath = tempPath.substring(0, tempPath.length() - 1);
        }
        return tempPath;
    }

    /**
     * 将.和\替换成/并截掉路径后的/部分
     */
    public static String formatPackage(String packagePath) {
        String tempPath = packagePath.replaceAll("\\\\", "/").replaceAll("\\.", "/");
        if (tempPath.endsWith(PATH_STRING[0]) || tempPath.endsWith(PATH_STRING[1])) {
            tempPath = tempPath.substring(0, tempPath.length() - 1);
        }
        return tempPath;
    }
}
