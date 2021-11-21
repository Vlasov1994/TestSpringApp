package com.example.testspringapp.mapper;

import com.example.testspringapp.dto.InterfaceDto;
import com.example.testspringapp.model.InterfaceModel;
import com.example.testspringapp.util.Util;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Type;

public abstract class AbstractMapper<T extends InterfaceModel, Y extends InterfaceDto> {

    @Autowired
    protected ModelMapper mapper;

    public Y toDto(T t) {
        return mapper.map(t, (Type) Util.getGenericParameterClass(this.getClass(), 1));
    }

    public T toModel(Y y) {
        return mapper.map(y, (Type) Util.getGenericParameterClass(this.getClass(), 0));
    }
}
