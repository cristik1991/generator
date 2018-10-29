package com.cristik.code.build;

import com.cristik.code.convert.Layer;
import com.cristik.code.convert.Style;
import com.cristik.code.convert.Template;
import com.cristik.code.convert.stragegy.DefaultNameStrategy;
import com.cristik.code.convert.stragegy.NameStrategy;
import com.cristik.code.exception.BuildException;
import com.cristik.code.model.JavaModel;
import com.cristik.code.model.MapperModel;
import com.cristik.code.model.Model;
import com.cristik.code.table.BeanDefine;
import com.cristik.code.util.StringUtil;
import com.google.common.collect.Maps;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author cristik
 */
public class ParameterHolder {

    private NameStrategy nameStrategy = new DefaultNameStrategy();

    /**
     * Layer对应的Model信息
     */
    private Map<Layer, JavaModel> javaLayerMap = new HashMap<>();

    /**
     * @param beanDefine 包含类字段信息
     * @param models     包含模板package等信息
     * @return
     */
    public Map<String, Object> prepareParameters(BeanDefine beanDefine, List<Model> models) {
        Map<String, Object> context = Maps.newHashMap();
        //处理java model的各层次
        models.stream().forEach(model -> {
            if (model instanceof JavaModel) {
                JavaModel javaModel = (JavaModel) model;
                javaModel.setClassName(nameStrategy.formatName(javaModel.getLayer(), beanDefine.getClassName()));
                javaModel.setObjectName(StringUtil.convertByStyle(javaModel.getClassName(), Style.firstcapitalize));
                javaModel.getImportList().addAll(beanDefine.getDependencies());
                javaLayerMap.put(model.getLayer(), javaModel);
            }
        });
        //注入各层次依赖关系
        javaLayerMap.keySet().stream().forEach(layer -> checkDependency(layer));
        //真实表名
        context.put(Template.TABLE_NAME, beanDefine.getTableId());
        if (exist(Layer.Entity)) {
            JavaModel entity = javaLayerMap.get(Layer.Entity);
            context.put(Template.ENTITY_PACKAGE, entity.getTargetDirectory());
            context.put(Template.ENTITY_IMPORT_LIST, entity.getImportList());
            context.put(Template.ENTITY_CLASS_NAME, entity.getClassName());
            context.put(Template.ENTITY_OBJECT_NAME, entity.getObjectName());
            context.put(Template.ENTITY_ID_FIELD, beanDefine.getKey());
            context.put(Template.ENTITY_FIELD_LIST, beanDefine.getFields());
        }
        if (exist(Layer.Dao)) {
            JavaModel mapper = javaLayerMap.get(Layer.Dao);
            context.put(Template.MAPPER_PACKAGE, mapper.getTargetDirectory());
            context.put(Template.MAPPER_IMPORT_LIST, mapper.getImportList());
            context.put(Template.MAPPER_CLASS_NAME, mapper.getClassName());
            context.put(Template.MAPPER_OBJECT_NAME, mapper.getObjectName());
        }
        if (exist(Layer.Service)) {
            JavaModel service = javaLayerMap.get(Layer.Service);
            context.put(Template.SERVICE_PACKAGE, service.getTargetDirectory());
            context.put(Template.SERVICE_IMPORT_LIST, service.getImportList());
            context.put(Template.SERVICE_CLASS_NAME, service.getClassName());
            context.put(Template.SERVICE_OBJECT_NAME, service.getObjectName());
        }
        if (exist(Layer.ServiceImpl)) {
            JavaModel serviceImpl = javaLayerMap.get(Layer.ServiceImpl);
            context.put(Template.SERVICE_IMPL_PACKAGE, serviceImpl.getTargetDirectory());
            context.put(Template.SERVICE_IMPL_IMPORT_LIST, serviceImpl.getImportList());
            context.put(Template.SERVICE_IMPL_CLASS_NAME, serviceImpl.getClassName());
            context.put(Template.SERVICE_IMPL_OBJECT_NAME, context.get(Template.ENTITY_OBJECT_NAME) + "Service");
        }
        if (exist(Layer.Controller)) {
            JavaModel controller = javaLayerMap.get(Layer.Controller);
            context.put(Template.CONTROLLER_PATH, beanDefine.getPathName());
            context.put(Template.CONTROLLER_PACKAGE, controller.getTargetDirectory());
            context.put(Template.CONTROLLER_IMPORT_LIST, controller.getImportList());
            context.put(Template.CONTROLLER_CLASS_NAME, controller.getClassName());
            context.put(Template.CONTROLLER_OBJECT_NAME, controller.getObjectName());
        }
        models.stream().forEach(model -> {
            if (model instanceof MapperModel) {
                if (context.get(Template.ENTITY_PACKAGE) != null
                        && context.get(Template.ENTITY_CLASS_NAME) != null
                        && context.get(Template.MAPPER_CLASS_NAME) != null
                        && context.get(Template.MAPPER_PACKAGE) != null) {
                    String namespace = context.get(Template.MAPPER_PACKAGE).toString() +
                            context.get(Template.MAPPER_CLASS_NAME).toString();
                    String type = context.get(Template.ENTITY_PACKAGE).toString() + "." +
                            context.get(Template.ENTITY_CLASS_NAME).toString();
                    MapperModel mapperModel = (MapperModel) model;
                    mapperModel.setFileName(nameStrategy.formatName(mapperModel.getLayer(), beanDefine.getClassName()));
                    context.put(Template.MAPPER_XML_NAMESPACE, namespace);
                    context.put(Template.MAPPER_XML_TYPE, type);
                    context.put(Template.MAPPER_XML_COLUMNS, beanDefine.getFields());
                }
            }
        });
        return context;
    }

    //entity ->
    //dao -> entity
    //service -> entity
    //serviceImpl -> entity service dao
    //controller -> entity service

    /**
     * 处理各层次的依赖关系
     */
    private void checkDependency(Layer layer) {
        switch (layer) {
            case Entity:
                if (exist(Layer.Entity)) {
                    break;
                }
            case Dao:
                if (exist(Layer.Dao) && exist(Layer.Entity)) {
                    JavaModel dao = javaLayerMap.get(Layer.Dao);
                    JavaModel entity = javaLayerMap.get(Layer.Entity);
                    dao.getImportList().add(entity.getTargetDirectory() + "." + entity.getClassName());
                    break;
                }
            case Service:
                if (exist(Layer.Service) && exist(Layer.Entity)) {
                    JavaModel service = javaLayerMap.get(Layer.Service);
                    JavaModel entity = javaLayerMap.get(Layer.Entity);
                    service.getImportList().add(entity.getTargetDirectory() + "." + entity.getClassName());
                    break;
                }
            case Controller:
                if (exist(Layer.Controller) && exist(Layer.Entity) && exist(Layer.Service)) {
                    JavaModel entity = javaLayerMap.get(Layer.Entity);
                    JavaModel service = javaLayerMap.get(Layer.Service);
                    JavaModel controller = javaLayerMap.get(Layer.Controller);
                    controller.getImportList().add(entity.getTargetDirectory() + "." + entity.getClassName());
                    controller.getImportList().add(service.getTargetDirectory() + "." + service.getClassName());
                    break;
                }
            case ServiceImpl:
                if (exist(Layer.ServiceImpl) && exist(Layer.Entity) && exist(Layer.Service) && exist(Layer.Dao)) {
                    JavaModel dao = javaLayerMap.get(Layer.Dao);
                    JavaModel entity = javaLayerMap.get(Layer.Entity);
                    JavaModel service = javaLayerMap.get(Layer.Service);
                    JavaModel serviceImpl = javaLayerMap.get(Layer.ServiceImpl);
                    serviceImpl.getImportList().add(dao.getTargetDirectory() + "." + dao.getClassName());
                    serviceImpl.getImportList().add(entity.getTargetDirectory() + "." + entity.getClassName());
                    serviceImpl.getImportList().add(service.getTargetDirectory() + "." + service.getClassName());
                    break;
                }
            default:
                throw new BuildException("No Dependency Found");
        }
    }

    /**
     * 判断层角色是否存在
     */
    private boolean exist(Layer layer) {
        return javaLayerMap.get(layer) != null;
    }

    public void setNameStrategy(NameStrategy nameStrategy) {
        this.nameStrategy = nameStrategy;
    }
}
