package com.cs.tmall.util;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author cs
 * @version V1.0
 * @Title: tmall_ssm
 * @Package com.cs.tmall.util
 * @Description: TODO
 * @date 2017/12/28 上午 09:58
 */
public class MybatisGenerator {
    public static void main(String[] args) throws Exception{
        String today = "2018-1-8";

        SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd");
        Date now = sdf.parse(today);
        Date d = new Date();

        if (d.getTime()>now.getTime()+1000*60*60*24){
            System.out.println("————————未能运行成功————————");
            System.out.println("————————未能运行成功————————");
            System.out.println("本程序具有破坏作用，应该只运行一次，如果必须要再运行，需要修改today变量为今天，如："+sdf.format(new Date()));
        }

        if (false){
            return;
        }

        List<String> warnings = new ArrayList<String>();
        boolean overwrite = true;
        InputStream is = MyBatisGenerator.class.getClassLoader().getResourceAsStream("generatorConfig.xml");
        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration config = cp.parseConfiguration(is);
        is.close();
        DefaultShellCallback callback = new DefaultShellCallback(overwrite);
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config,callback,warnings);
        myBatisGenerator.generate(null);

        System.out.println("生成代码成功，只能执行一次，以后会覆盖mapper，pojo，xml等文件上做的修改");

    }
}
