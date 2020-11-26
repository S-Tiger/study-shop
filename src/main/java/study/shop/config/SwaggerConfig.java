package study.shop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@EnableSwagger2 //스웨거2사용 어노테이션
@Configuration //config 파일에 사용하는 어노테이션
public class SwaggerConfig {

    @Bean
    public Docket api(){

        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("study.shop.apicontroller")) //설정할 패키지설정 //any일경우 전부
                .paths(PathSelectors.any()) //설정한 url들만 필터링 //any일경우 전부
                .build()
                .apiInfo(apiInfo())
                .useDefaultResponseMessages(false)
                .globalResponseMessage(RequestMethod.GET, getArrayList());
    }

    private ArrayList<ResponseMessage> getArrayList(){
        ArrayList<ResponseMessage> lists = new ArrayList<ResponseMessage>();
        lists.add(new ResponseMessageBuilder().code(500).message("서버에러").build());
        lists.add(new ResponseMessageBuilder().code(403).message("권한없음").build());
        return lists;
    }

    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("API 타이틀")
                .description("API 상세소개 및 사용법")
                .version("1.0") //현재배포버전전
                .build();
    }

}
