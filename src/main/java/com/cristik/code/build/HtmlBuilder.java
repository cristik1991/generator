package com.cristik.code.build;

import com.cristik.code.exception.BuildException;
import com.cristik.code.model.HtmlModel;
import com.cristik.code.model.Model;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author cristik
 */
public class HtmlBuilder implements Builder {

    private static final Logger logger = LoggerFactory.getLogger(HtmlBuilder.class);

    private static final String DEFAULT_SUFFIX = ".html";

    private String suffix = DEFAULT_SUFFIX;

    @Override
    public void build(List<Model> models, Map<String, Object> context) {
        models.stream().forEach(model -> {
            if (model instanceof HtmlModel) {
                HtmlModel htmlModel = (HtmlModel) model;
                String templatePath = htmlModel.getTemplatePath();
                String templateName = htmlModel.getTemplateName();
                String directory = htmlModel.getDirectory();
                String targetDirectory = htmlModel.getTargetDirectory();
                String fileName = htmlModel.getLayer().name() + suffix;
                boolean override = htmlModel.isOverride();
                try {
                    String content = FreemarkerHelper.convertTemplate(templatePath, templateName, context);
                    logger.info("loading templateName={} from templatePath={},overried={} ", templateName, templatePath
                            , override);
                    FreemarkerHelper.buildFile(directory, targetDirectory, fileName, override, content);
                } catch (IOException e) {
                    logger.error("build mapper file {} failed for {}", fileName, e.getMessage());
                    throw new BuildException(e.getMessage());
                }
            }
        });
    }
}
