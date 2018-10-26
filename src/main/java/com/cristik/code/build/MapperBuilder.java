package com.cristik.code.build;

import com.cristik.code.exception.BuildException;
import com.cristik.code.model.MapperModel;
import com.cristik.code.model.Model;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author cristik
 */
public class MapperBuilder implements Builder {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final String SUFFIX = ".xml";

    @Override
    public void build(List<Model> models, Map<String, Object> context) {
        models.stream().forEach(model -> {
            if (model instanceof MapperModel) {
                MapperModel mapperModel = (MapperModel) model;
                String templatePath = mapperModel.getTemplatePath();
                String templateName = mapperModel.getTemplateName();
                String directory = mapperModel.getDirectory();
                String targetDirectory = mapperModel.getTargetDirectory();
                String fileName = mapperModel.getFileName() + SUFFIX;
                boolean override = mapperModel.isOverride();
                try {
                    String content = FreemarkerHelper.convertTemplate(templatePath, templateName, context);
                    logger.info("loading templateName={} from templatePath={},overried={} ", templateName, templatePath
                            , override);
                    FreemarkerHelper.buildFile(directory, targetDirectory, fileName, override, content);
                } catch (IOException e) {
                    logger.error("build mapper file {} failed for {}", mapperModel.getFileName(), e.getMessage());
                    throw new BuildException(e.getMessage());
                }
            }
        });
    }
}
