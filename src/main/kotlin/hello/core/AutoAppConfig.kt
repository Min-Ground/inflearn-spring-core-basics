package hello.core

import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.FilterType

@Configuration
@ComponentScan(
//    basePackages = ["hello.core.member"],
//    basePackageClasses = [AutoAppConfig::class],

    // 기본값 : @ComponentScan 애노테이션이 붙은 현재 패키지 하위를 전부 스캔 (hello.core)
    excludeFilters = [ComponentScan.Filter(type = FilterType.ANNOTATION, value = [Configuration::class])]
)
class AutoAppConfig {
}
