package com.account.my.common;


import com.account.my.config.AppConfig;
import com.account.my.pageObjects.LoginPage;
import org.apache.juli.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureWebClient;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.convert.support.ConfigurableConversionService;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MissingRequiredPropertiesException;
import org.springframework.core.env.MutablePropertySources;
//import org.springframework.core.env.Profiles;
import org.springframework.test.context.ContextConfiguration;

import java.util.Arrays;
import java.util.Map;
import java.util.logging.LogManager;
//mvn clean install -Dspring.profiles.active=dev
@ContextConfiguration(classes = {AppConfig.class,ApiClient.class},loader = SpringBootContextLoader.class)
@AutoConfigureWebClient(registerRestTemplate = true)
@EnableConfigurationProperties
public class AbstractStepDef {
    public static final Logger log = LoggerFactory.getLogger(AbstractStepDef.class.getName());

    public static String getUIUrlByEnv() {
        String url;
        String activeProfile;
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        ConfigurableEnvironment env = context.getEnvironment();
        activeProfile =  Arrays.toString(env.getActiveProfiles());
        log.debug("Environment received is:" +activeProfile );
        switch (activeProfile){
            case("[sit]"):
            {
                url = CommonConstants.SIT_UI_HOMEPAGE;
                log.debug("UI SIT URL SET TO GO: "+url);
                break;
            }
            case("[st]"):
            {
                url = CommonConstants.ST_UI_HOMEPAGE;
                log.debug("UI ST URL SET TO GO: "+url);
                break;
            }
            case("[dev]"):
            {
                url = CommonConstants.DEV_UI_HOMEPAGE;
                log.debug("UI DEV URL SET TO GO: "+url);
                break;
            }
            default:
            {
                url = CommonConstants.SIT_UI_HOMEPAGE;
                log.debug("UI Default URL SET TO GO: "+url);
                break;
            }

        }
        return url;
    }
}
