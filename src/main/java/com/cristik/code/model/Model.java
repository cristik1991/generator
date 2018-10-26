package com.cristik.code.model;

import com.cristik.code.convert.Layer;

/**
 * @author cristik
 */
public abstract class Model {

    /**
     * 模板名称
     */
    private String templateName;

    /**
     * 模板位置
     */
    private String templatePath;

    /**
     * 包名相对工程路径
     */
    private String directory;

    /**
     * 文件输出目录
     */
    private String targetDirectory;

    /**
     * 是否覆盖源文件
     */
    private boolean override = false;

    /**
     * 层级
     */
    private Layer layer;

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public String getTemplatePath() {
        return templatePath;
    }

    public void setTemplatePath(String templatePath) {
        this.templatePath = templatePath;
    }

    public String getDirectory() {
        return directory;
    }

    public void setDirectory(String directory) {
        this.directory = directory;
    }

    public String getTargetDirectory() {
        return targetDirectory;
    }

    public void setTargetDirectory(String targetDirectory) {
        this.targetDirectory = targetDirectory;
    }

    public boolean isOverride() {
        return override;
    }

    public void setOverride(boolean override) {
        this.override = override;
    }

    public Layer getLayer() {
        return layer;
    }

    public void setLayer(Layer layer) {
        this.layer = layer;
    }
}
