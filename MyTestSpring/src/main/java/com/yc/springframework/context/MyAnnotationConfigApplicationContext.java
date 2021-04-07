package com.yc.springframework.context;

import com.yc.springframework.stereotype.*;

import java.io.File;
import java.io.FileFilter;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.*;

/**
 * @program: TestSpring
 * @description:
 * @author: 作者 :林木木
 * @create: 2021-04-05 14:05
 */
public class MyAnnotationConfigApplicationContext implements MyApplicationContext {

    private Map<String,Object> beanMap = new HashMap<String,Object>();

    public MyAnnotationConfigApplicationContext(Class<?>...componentClasses) throws IllegalAccessException, InvocationTargetException, InstantiationException {
        try {
            register(componentClasses);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void register(Class<?>[] componentClasses) throws Exception {
        //实现代码

        //如果没有传入配置类，则显示未指定配置类
        if(componentClasses ==null || componentClasses.length<=0) {
            throw new RuntimeException("未指定配置类");
        }

        //解析获取到的配置类
        for (Class cl:componentClasses){

            if(!cl.isAnnotationPresent(MyConfiguration.class)){//判断是否能找到MyConfiguration，找不到，则退出去
                continue;
            }
            String [] basePackages=getAppConfigBasePackages(cl);
            if (cl.isAnnotationPresent(MyComponentScan.class)){
                MyComponentScan msc = (MyComponentScan) cl.getAnnotation(MyComponentScan.class);
                if (msc.basePackages()!=null && msc.basePackages().length>0){
                    basePackages=msc.basePackages();
                }
            }
            //处理@Bean 的情况
            //obj就是当前解析的MyAppConfig对象
            Object obj=cl.newInstance();
            
            handleAtBean(cl,obj);

            //处理 basePackages基础包下的所有托管bean
            for ( String basePackage:basePackages){
                System.out.println("扫描包路径"+basePackage);
                scanPackageAndSubPackageClasses(basePackage);
            }
            //继续处理其他的托管Bean
            handleManagedBean();
            //版本2：DI
            //handleAutowired(beanMap);//循环beanMap中的每个bean，找到每个有@Auto wired@Resource注解的方法实现di
            handDi(beanMap);
        }

    }
    /**
     * 循环 beanMap中的每个bean，找到他们每个类中的每个有@Autowried@Resource注解的方法实现id
     * @param beanMap
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    private void handDi(Map<String, Object> beanMap) throws InvocationTargetException, IllegalAccessException {
        Collection<Object> objectCollection=beanMap.values();
        for (Object obj:objectCollection){
            Class c=obj.getClass();
            Method[] ms=c.getDeclaredMethods();
            for (Method m:ms){
                if (m.isAnnotationPresent(MyAutowired.class)&&m.getName().startsWith("set")){

                    invokeAutowiredMethod(m,obj);
                }else if (m.isAnnotationPresent(MyResource.class)&&m.getName().startsWith("set")){

                    invokeResourceMethod(m,obj);
                }
            }
            Field[] fs=c.getDeclaredFields();
            for (Field field:fs){
                if (field.isAnnotationPresent(MyAutowired.class)){

                }else if (field.isAnnotationPresent(MyResource.class)){

                }
            }
        }
    }



    private void invokeResourceMethod(Method m, Object obj) throws InvocationTargetException, IllegalAccessException {
        //1.取出MyResource取出name属性值渠道，当初beanId
        MyResource mr=m.getAnnotation(MyResource.class);
        String beanId=mr.name();
        //2.如果没有，则取出m方法中参数的类型名，改成首字母小写  当初beanId
        if (beanId==null || beanId.equalsIgnoreCase("")){
            String pname=m.getParameterTypes()[0].getSimpleName();
            beanId=pname.substring(0,1).toLowerCase()+pname.substring(1);
        }
        //3.不管怎么样，都会从beanMap中取出
        Object o=beanMap.get(beanId);
        //invoke
        m.invoke(obj,o);
    }
    private void invokeAutowiredMethod(Method m, Object obj) throws InvocationTargetException, IllegalAccessException {
        //1.取出m的参数类型
        Class typesClass=m.getParameterTypes()[0];
        //2.从bean中取出循环所有的object
        Set<String >keys=beanMap.keySet();
        for (String key:keys){
        //4.如果是，则从beanMap取出
            Object o=beanMap.get(key);
        //3.判断这些Object是否为参数类型的实例instanceof
            if (o.getClass().getName().equalsIgnoreCase(typesClass.getName())){
                //invoke
                m.invoke(obj,o);
            }
        }
    }


    /**
     * 处理managedBeanClasses所有的Class嘞，筛选出@Component@Service等等的类，并实例化，存到beanMap中
     */
    private void handleManagedBean() throws IllegalAccessException, InstantiationException, InvocationTargetException {
        for (Class c:managedBeanclasses){
            if (c.isAnnotationPresent(MyComponent.class)){
                saveManagedBean(c);
            }else if(c.isAnnotationPresent(MyService.class)){
                saveManagedBean(c);
            }else if (c.isAnnotationPresent(MyRepository.class)){
                saveManagedBean(c);
            }else if (c.isAnnotationPresent(MyController.class)){
                saveManagedBean(c);
            }else {
                continue;
            }

        }
    }
    private void saveManagedBean(Class c) throws IllegalAccessException, InstantiationException, InvocationTargetException {
        Object o=c.newInstance();//实例化这个对象
        handlePostConstruct(o,c);
        String beanId=c.getSimpleName().substring(0,1).toLowerCase()+c.getSimpleName().substring(1);
        beanMap.put(beanId,o);
    }

    /**
     * 扫描包和子类
     * @param basePackage
     * @throws Exception
     */
    private void scanPackageAndSubPackageClasses(String basePackage)throws Exception {
        String packagePath=basePackage.replaceAll("\\.","/");//.是转义符，需要转义
        System.out.println("扫描包路径"+basePackage+"替换后："+packagePath);//com.yc实际的路径
        Enumeration<URL> files=Thread.currentThread().getContextClassLoader().getResources(packagePath);

        while (files.hasMoreElements()){
            URL url = files.nextElement();
            System.out.println("扫描路径为"+url.getFile());
            //TODO:运行这些目录，查看是否出结果
            findClassesInPackages(url.getFile(),basePackage);//第二个参数：com.yc.bean
        }
    }

    private Set<Class>managedBeanclasses=new HashSet<Class>();
    /**
     * 查找file及子包中所有要托管的class，存到一个Set(managedBeanclasses)中，
     * @param file
     * @param basePackage
     */
    private void findClassesInPackages(String file, String basePackage) throws ClassNotFoundException {
        //具体写扫描
        File f=new File( file );
        File[] fff=f.listFiles();
        File[] ClassFiles=f.listFiles(new FileFilter() {
            @Override
            public boolean accept(File file) {
                System.out.println(file.getName());
                return file.getName().endsWith(".class")||file.isDirectory();
            }
        });
        System.out.println(ClassFiles);
        for (File cf:ClassFiles){
            if (cf.isDirectory()){
                //如果是目录，则递归
                //拼接子目录
                basePackage+="."+cf.getName().substring(cf.getName().lastIndexOf("/")+1);
                findClassesInPackages(cf.getAbsolutePath(),basePackage);
            }else {
                //加载cf作为class文件
                URL[]urls=new URL[]{};
                URLClassLoader ucl=new URLClassLoader(urls);
                //com.yc.bean.Hello.class->com.yc.bean.Hello
                Class c=ucl.loadClass(basePackage+"."+cf.getName().replaceAll(".class","") );//com.yc.bean.Hello.class--->
                managedBeanclasses.add(  c  );
            }
        }
    }

    /**
     * 处理MyAppConfig配置类中的@Bean注解，完成IOC操作
     * @param cls
     * @param obj
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    private void handleAtBean( Class cls,Object obj) throws InvocationTargetException, IllegalAccessException {
        //获取cls中所有的method
        Method[]ms=cls.getDeclaredMethods();
        //循环，判断每个method上是否有@MyBean注解
        for (Method m : ms){
            if (m.isAnnotationPresent(MyBean.class)){
                Object o=m.invoke(obj);
                //TODO:加入处理 @MyBean注解对应的方法所实例化的类中@MyPostConstruct对应的方法
                handlePostConstruct(o,o.getClass());//在这里o是指helloworld对象，o.getclass他的反射对象
                beanMap.put(m.getName(),o);
            }
        }
        //有，则invoke它，他有返回值，
    }

    /**
     * 处理一个bean中的MyPostContruct对应的方法
     * @param o
     * @param cls
     */
    private void handlePostConstruct(Object o, Class<?> cls) throws InvocationTargetException, IllegalAccessException {
        Method[] ms= cls.getDeclaredMethods();
        for (Method m:ms){
            if(m.isAnnotationPresent(MyPostConstruct.class)){
                m.invoke(o);
            }
        }
    }

    private String [] getAppConfigBasePackages(Class cl){
        String [] paths=new String [1];
        paths[0]=cl.getPackage().getName();
        return paths;
    }
    @Override
    public Object getBean(String id) {
        return beanMap.get(id);
    }
}
