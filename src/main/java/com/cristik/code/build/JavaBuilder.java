package com.cristik.code.build;

import com.cristik.code.exception.BuildException;
import com.cristik.code.model.JavaModel;
import com.cristik.code.model.Model;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author cristik
 */
public class JavaBuilder implements Builder {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final String SUFFIX = ".java";

    @Override
    public void build(List<Model> models, Map<String, Object> context) {

        models.stream().forEach(model -> {
            if (model instanceof JavaModel) {
                JavaModel javaModel = (JavaModel) model;
                String templatePath = javaModel.getTemplatePath();
                String templateName = javaModel.getTemplateName();
                String directory = javaModel.getDirectory();
                String targetDirectory = javaModel.getTargetDirectory();
                String fileName = javaModel.getClassName() + SUFFIX;
                boolean override = javaModel.isOverride();
                try {
                    String content = FreemarkerHelper.convertTemplate(templatePath, templateName, context);
                    logger.info("loading templateName={} from templatePath={},overried={} ", templateName, templatePath
                            , override);
                    FreemarkerHelper.buildFile(directory, targetDirectory, fileName, override, content);
                } catch (IOException e) {
                    logger.error("build java {} failed for {}", javaModel.getClassName(), e.getMessage());
                    throw new BuildException(e.getMessage());
                }
            }
        });
    }
}
