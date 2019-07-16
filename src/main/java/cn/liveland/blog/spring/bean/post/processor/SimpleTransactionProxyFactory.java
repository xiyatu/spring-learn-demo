package cn.liveland.blog.spring.bean.post.processor;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.context.ApplicationContext;

/**
 * @author xiyatu
 * @date 2019/7/16 15:17
 * @description 定义事务代理工厂，用来创建代理对象
 */
public class SimpleTransactionProxyFactory {

    /**
     * 创建代理对象
     *
     * @param bean               目标对象
     * @param applicationContext 上下文
     * @return 返回代理对象
     */
    public static Object createProxy(Object bean, ApplicationContext applicationContext) {
        return cglibCreateProxy(bean, applicationContext);
    }

    /**
     * 创建
     *
     * @param bean               目标对象
     * @param applicationContext 上下文
     * @return 返回代理对象
     */
    private static Object cglibCreateProxy(Object bean, ApplicationContext applicationContext) {
        // CGlib代理
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(bean.getClass());
        // 设置拦截器
        SimpleTransactionCglibInterceptor interceptor = new SimpleTransactionCglibInterceptor();
        interceptor.setBean(bean);
        interceptor.setApplicationContext(applicationContext);
        enhancer.setCallback(interceptor);
        // 创建
        return enhancer.create();
    }


}
