package com.rest.api.config;

import org.modelmapper.ModelMapper;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Dependent;
import javax.ws.rs.Produces;

@Dependent
public class AppConfig {

    @Produces
    @ApplicationScoped
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
}
