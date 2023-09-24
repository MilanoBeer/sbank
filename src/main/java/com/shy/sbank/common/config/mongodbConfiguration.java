package com.shy.sbank.common.config;


import org.apache.naming.factory.BeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.convert.DbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultDbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;

@Configuration
public class mongodbConfiguration {

//    @Bean
//    public MappingMongoConverter ConvertingConfiguration(MongoDatabaseFactory factory, MongoMappingContext ctx, BeanFactory beanFactory) {
//        DbRefResolver resolver = new DefaultDbRefResolver(factory);
//        MappingMongoConverter converter = new MappingMongoConverter(resolver, ctx);
//        converter.setTypeMapper(new DefaultMongoTypeMapper(null)); // Null 처리
//
//        return converter;
//    }
}
