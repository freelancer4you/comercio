package de.goldmann.comercio.server.util;

import org.springframework.web.client.RestTemplate;

public class RestTemplateFactory
{

    public RestTemplate buildTemplate()
    {
        // ObjectMapper mapper = new ObjectMapper();
        // mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        // mapper.registerModule(new Jackson2HalModule());
        //
        // MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        // converter.setSupportedMediaTypes(MediaType.parseMediaTypes("application/hal+json"));
        // converter.setObjectMapper(mapper);
        // return new RestTemplate(Arrays.asList(converter));
        return new RestTemplate();
    }

}
