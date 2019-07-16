package cn.liveland.blog.spring.bean.post.processor;

import cn.liveland.blog.spring.annotation.SimpleTransactional;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author xiyatu
 * @date 2019/7/16 15:06
 * @description 实现BeanPostProcessor，Bean创建后根据注解创建代理对象，从而实现AOP
 */
@Component
public class SimpleTransactionProcessor implements BeanPostProcessor {

    /**
     * 上下文
     */
    @Autowired
    private ApplicationContext applicationContext;

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    /**
     * 创建bean 的处理
     *
     * @param bean     目标对象
     * @param beanName 对象名称
     * @return 代理对象或目标对象
     */
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Class<?> targetCls = bean.getClass();
        Method[] methods = targetCls.getDeclaredMethods();
        boolean needProxy = false;
        if (methods.length > 0) {
            for (Method method : methods) {
                // 如果目标对象的方法被SimpleTransactional修饰，则创建代理对象
                // 否则不创建直接返回目标对象
                if (method.isAnnotationPresent(SimpleTransactional.class)) {
                    needProxy = true;
                    break;
                }
            }
        }
        if (needProxy) {
            return SimpleTransactionProxyFactory.createProxy(bean, applicationContext);
        }
        return bean;
    }
}
