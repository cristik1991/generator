package com.cristik.code.model;

import com.cristik.code.convert.Layer;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author cristik
 */

@Data
@Accessors(chain = true)
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
}
