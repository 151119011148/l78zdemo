package com.scofen.study.designpattern.delegate.mvc;



import com.scofen.study.designpattern.delegate.mvc.controller.MemberAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Create by  GF  in  14:36 2018/11/19
 * Description:
 * Modified  By:
 */
public class DispatcherServlet {

    private static List<Handler> handlerMapping = Collections.emptyList();

    public DispatcherServlet(){
        Class<?> memberActionClass = MemberAction.class;

        try {
            handlerMapping.add(Handler.builder()
                    .controller(memberActionClass.newInstance())
                    .method(memberActionClass.getMethod("getMemberById", String.class))
                    .url("/web/getMemberById.json")
                    .build());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public void doService(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse){
        doDispatch(httpServletRequest, httpServletResponse);
    }

    //委派模式
    private void doDispatch(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse){
        /**
         * 1.获取用户请求的url
         *      如果按照J2EE的标准，每个url对应一个servlet，url由浏览器输入
         * 2.servlet拿到URL后，做权衡，（判断和选择）
         *      根据url找到对应这个url的java类的方法
         * 3. 过url去handlerMapping(我们把它认为是策略常量)
         * 4.将具体的任务分发给Method（通过反射调用其对应方法）
         * 5.获取结果，通过response返回回去
         */
        String uri = httpServletRequest.getRequestURI();

        Handler handler =handlerMapping.stream().filter(h -> h.url.equals(uri)).collect(Collectors.toList()).get(0);

        Object result = null;
        try {
            result = handler.getMethod().invoke(handler.getController());
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            httpServletResponse.getWriter().write(result.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }



    static class Handler{
        private Object controller;
        private Method method;
        private String url;

        public Object getController() {
            return controller;
        }

        public Method getMethod() {
            return method;
        }

        public String getUrl() {
            return url;
        }

        public  Handler(Object controller, Method method, String url){
            this.controller = controller;
            this.method = method;
            this.url = url;
        }
        private static Builder builder(){
            return new Builder();
        }

        static class Builder{
            private Object controller;
            private Method method;
            private String url;
            public  Builder(){}

            public Handler build(){
                return new Handler(this.controller, this.method, this.url);
            }
            private Builder controller(Object controller){
                this.controller = controller;
                return this;
            }
            private Builder method(Method method){
                this.method = method;
                return this;
            }
            private Builder url(String url){
                this.url = url;
                return this;
            }

        }

    }

}
