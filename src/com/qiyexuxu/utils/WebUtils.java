package com.qiyexuxu.utils;

import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

public class WebUtils {
    /**
     * 将用户请求封装为对应的 JavaBean
     *
     * @param request 用户请求
     * @param tClass  JavaBean 的类型
     * @param <T>     返回的 JavaBean 类型
     * @return
     */
    public static <T> T request2Bean(HttpServletRequest request, Class<T> tClass) {
        try {
            T bean = tClass.newInstance();
            Enumeration enumeration = request.getParameterNames();
            while (enumeration.hasMoreElements()) {
                String name = (String) enumeration.nextElement();
                String value = request.getParameter(name);
                BeanUtils.setProperty(bean, name, value);
            }

            return bean;
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }
}
