package com.blog.aspect;

import com.blog.annotation.AutoFillTime;
import com.blog.constant.AutoFillTimeConstant;
import com.blog.enumeration.SqlOperationType;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.time.LocalDateTime;

@Aspect  // 有了这个注解表示这个类，准确来说是这个Bean，就表示这个切面会滤出符合规则的方法，对这些方法执行advice操作
@Component  // 会在IOC容器中创建它的Bean
@Slf4j
public class AutoFillTimeAspect {
    /**
     * 设置切入点
     */
    @Pointcut("execution(* com.blog.mapper.*.*(..)) && @annotation(com.blog.annotation.AutoFillTime)")
    public void autoFillTimePointCut() {
    }

    /**
     * 前置通知，在通知中进行公共字段的赋值 —— 看不懂？
     * 通俗来讲就是把 所有方法 经过规则autoFillPointCut()的过滤后，送到方法autoFill(JoinPoint joinPoint)中执行反射操作，然后再执行方法本身，所以是@Before。
     * 对上面那句话进行概念解释：
     * 在SpringAOP中，所有方法 都称为JoinPoint；
     * 规则autoFillPointCut()的过滤 称为Pointcut；对应注解是@Pointcut
     * 方法autoFill(JoinPoint joinPoint) 中的操作 称为advice；对应注解是@Before，@AfterReturning，@Around，@After，@AfterThrowing，参见：https://zhuanlan.zhihu.com/p/500555634
     * 上面三个合在一起 称为Aspect。对应注解是@Aspect
     */
    @Before("autoFillTimePointCut()")
    public void autoFillTime(JoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();  // 获取方法签名对象，joinPoint是经过过滤的一个Mapper类里的一个方法（有注解@annotation(com.sky.annotation.AutoFill)修饰的），例如Mapper类中的方法update()、insert()
        AutoFillTime autoFillTime = methodSignature.getMethod().getAnnotation(AutoFillTime.class);  // 获得方法上方的注解对象
        SqlOperationType sqlOperationType = autoFillTime.value();  // 获得注解的值,是INSERT还是UPDATE，其实就表明这个方法是做数据库的什么操作

        Object[] args = joinPoint.getArgs();
        if (args == null || args.length == 0) {
            return;
        }

        // 如果上面没有return，则进行下面的操作
        Object entity = args[0];  // 被拦截方法的参数数组的第0元素entity，就是数据库表对应的实体对象，例如BlogArticle对象等等...

        // 准备下面赋值给时间字段的数据
        LocalDateTime now = LocalDateTime.now();

        // 根据当前不同的操作类型，为对应的属性通过反射来赋值
        if (sqlOperationType == SqlOperationType.INSERT) {
            // insert操作需要为2个公共字段赋值
            try {
                Method setCreatedAt = entity.getClass().getDeclaredMethod(AutoFillTimeConstant.SET_CREATED_AT, LocalDateTime.class);// 通过反射，获取entity中的方法
                Method setUpdatedAt = entity.getClass().getDeclaredMethod(AutoFillTimeConstant.SET_UPDATED_AT, LocalDateTime.class);// 通过反射，获取entity中的方法

                // 获取到了方法，下面运行方法给公共字段赋值，因为公共字段赋值必须通过setter方法，所以上面才需要获取方法
                setCreatedAt.invoke(entity, now);
                setUpdatedAt.invoke(entity, now);
            } catch (Exception ex) {
                ex.printStackTrace();
            }

        } else if (sqlOperationType == SqlOperationType.UPDATE) {
            // update操作需要为1个公共字段赋值
            try {
                Method setUpdatedAt = entity.getClass().getDeclaredMethod(AutoFillTimeConstant.SET_UPDATED_AT, LocalDateTime.class);
                setUpdatedAt.invoke(entity, now);
            } catch (Exception ex) {
                ex.printStackTrace();
            }

        }


    }
}
