package kr.co.songjava.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import kr.co.songjava.configuration.servlet.handler.BaseHandlerInterceptor;
import kr.co.songjava.framework.data.web.MySQLPageRequestHandleMethodArgumentResolver;
import kr.co.songjava.mvc.domain.BaseCodeLabelEnum;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.http.MediaType;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import java.util.List;
import java.util.Locale;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {

    @Bean
    public ReloadableResourceBundleMessageSource messageSource(){
        ReloadableResourceBundleMessageSource source = new ReloadableResourceBundleMessageSource();
        source.setBasename("classpath:/messages/message");
        source.setDefaultEncoding("UTF-8");
        source.setCacheSeconds(60);
        source.setDefaultLocale(Locale.KOREAN);
        source.setUseCodeAsDefaultMessage(true);
        return source;
    }

    @Bean
    public BaseHandlerInterceptor baseHandlerInterceptor(){
        return new BaseHandlerInterceptor();
    }

    @Bean
    public ObjectMapper objectMapper(){
        ObjectMapper objectMapper = new ObjectMapper();
        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addSerializer(BaseCodeLabelEnum.class, new BaseCodeLabelEnumJsonSerializer());
        objectMapper.registerModule(simpleModule);
        return objectMapper;
    }

    @Bean
    public MappingJackson2JsonView mappingJackson2JsonView(){
        MappingJackson2JsonView jsonView = new MappingJackson2JsonView();
        jsonView.setContentType(MediaType.APPLICATION_JSON_VALUE);
        jsonView.setObjectMapper(objectMapper());
        return jsonView;
    }

    @Bean
    public GlobalConfig config(){
        return new GlobalConfig();
    }

    @Bean
    public FilterRegistrationBean<SitemeshConfiguration> sitemeshBean(){
        FilterRegistrationBean<SitemeshConfiguration> filter = new FilterRegistrationBean<SitemeshConfiguration>();
        filter.setFilter(new SitemeshConfiguration());
        return filter;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(baseHandlerInterceptor());
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolverList){
        resolverList.add(new MySQLPageRequestHandleMethodArgumentResolver());
    }

    //서버에서 파일에 직접 접근해서 볼수 있음
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){
        //업로드 파일 static resource 접근 경로
        String resourcePattern = config().getUploadResourcePath() + "**";
        //로컬이 윈도우일떄
        if(config().isLocal()){
            registry.addResourceHandler(resourcePattern).addResourceLocations("file:///"+ config().getUploadFilePath());
        }else{
            //리눅스 또는 유닉스
            registry.addResourceHandler(resourcePattern).addResourceLocations("file:"+ config().getUploadFilePath());
        }
    }

    @Override
    public void addCorsMappings(CorsRegistry registry){
        registry.addMapping("/**").allowedOrigins("http://localhost:8080").allowedMethods("*");//.allowedMethods("*") : put 메소드 활용하려면 모두허용해야 CORS 에러 안뜸 
    }




}
