package com.example.testspringapp.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public abstract class AbstractMapper<Model, Dto> {

    @Autowired
    protected ModelMapper mapper;

    public Dto toDto(Model model) {
        return mapper.map(model, (Type) getGenericParameterClass(this.getClass(), 1));
    }

    public Model toModel(Dto dto) {
        return mapper.map(dto, (Type) getGenericParameterClass(this.getClass(), 0));
    }

    protected static Class<?> getGenericParameterClass(Class<?> actualClass, int parameterIndex) {
        return (Class<?>) ((ParameterizedType) actualClass.getGenericSuperclass()).getActualTypeArguments()[parameterIndex];
    }
}
