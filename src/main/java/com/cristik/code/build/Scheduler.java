package com.cristik.code.build;

import com.cristik.code.convert.Layer;
import com.cristik.code.convert.stragegy.NameStrategy;
import com.cristik.code.model.Model;
import com.cristik.code.table.BeanDefine;
import com.google.common.collect.Lists;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author cristik
 */
public class Scheduler {

    private NameStrategy nameStrategy;

    ParameterHolder parameterHolder = new ParameterHolder();

    private List<Builder> builders = Lists.newArrayList();

    {
        initDefaultBuilders();
    }

    private Map<String, Object> buildContext(List<Model> models, BeanDefine beanDefine) {
        if (nameStrategy != null) {
            parameterHolder.setNameStrategy(nameStrategy);
        }
        return parameterHolder.prepareParameters(beanDefine, models);
    }

    private void build(List<Model> models, Map<String, Object> context) {
        builders.stream().forEach(builder -> builder.build(models, context));
    }

    private void build(List<Model> models, BeanDefine beanDefine) {
        builders.stream().forEach(builder -> builder.build(models, buildContext(models, beanDefine)));
    }

    public void build(List<Model> models, BeanDefine beanDefine, List<Layer> includes) {
        Map<String, Object> context = buildContext(models, beanDefine);
        if (includes != null && includes.size() > 0) {
            List<Model> includeModels = models.stream().filter(model -> includes.stream()
                    .anyMatch(layer -> layer == model.getLayer())).collect(Collectors.toList());
            build(includeModels, context);
        } else {
            build(models, context);
        }
    }

    private void initDefaultBuilders() {
        builders.add(new JavaBuilder());
        builders.add(new MapperBuilder());
        builders.add(new HtmlBuilder());
    }

    public void setNameStrategy(NameStrategy nameStrategy) {
        this.nameStrategy = nameStrategy;
    }
}
