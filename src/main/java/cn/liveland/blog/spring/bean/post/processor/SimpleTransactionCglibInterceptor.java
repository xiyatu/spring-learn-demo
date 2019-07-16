package cn.liveland.blog.spring.bean.post.processor;


import org.springframework.beans.BeansException;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.lang.reflect.Method;

/**
 * @author xiyatu
 * @date 2019/7/16 15:19
 * @description 定义拦截器，事务控制的具体实现
 */
public class SimpleTransactionCglibInterceptor implements MethodInterceptor {

    /**
     * applicationContext 用来获取 DataSourceTransactionManager实例
     * 尝试使用ApplicationContextWave来获取，但是无效。
     * 后面直接用set方法进行设置
     */
    private ApplicationContext applicationContext;
    /**
     * 目标对象
     */
    private Object bean;

    public void setBean(Object bean) {
        this.bean = bean;
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        // 设置事务隔离性
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
        // 获取事务管理器
        DataSourceTransactionManager transactionManager = applicationContext.getBean(DataSourceTransactionManager.class);
        if (transactionManager == null) {
            throw new RuntimeException("系统错误");
        }
        // 开始事务
        TransactionStatus status = transactionManager.getTransaction(def);
        Object object;
        try {
            // 执行目标方法
            object = method.invoke(bean, objects);
            // 提交事务
            transactionManager.commit(status);
        } catch (Throwable e) {
            // 回滚事务
            transactionManager.rollback(status);
            throw e;
        }
        return object;
    }

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
