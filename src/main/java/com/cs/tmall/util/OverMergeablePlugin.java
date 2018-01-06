package com.cs.tmall.util;

import org.mybatis.generator.api.GeneratedXmlFile;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;

import java.lang.reflect.Field;
import java.util.List;

/**
 * @author cs
 * @version V1.0
 * @Title: tmall_ssm
 * @Package com.cs.tmall.util
 * @Description: 防止第一次逆向生成之后，再次运行导致重复生成文件，出现bug
 * @date 2017/12/28 上午 09:43
 */
public class OverMergeablePlugin extends PluginAdapter {

    @Override
    public boolean validate(List<String> list) {
        return false;
    }

    @Override
    public boolean sqlMapGenerated(GeneratedXmlFile sqlMap, IntrospectedTable introspectedTable) {
        try{
            Field field = sqlMap.getClass().getDeclaredField("isMergeable");
            field.setAccessible(true);
            field.setBoolean(sqlMap,false);
        }catch (Exception e){
            e.printStackTrace();
        }
        return true;
    }
}
