package com.example.Users.queue.mappers;

import com.example.Users.entity.Users;
import com.example.Users.queue.dto.WolletProducerDTO;

public class WolletProducerMapper {

    public static WolletProducerDTO toWolletProducerDTO(Users users){
        WolletProducerDTO wolletProducerDTO = new WolletProducerDTO();
        wolletProducerDTO.setId(users.getId());
        return new WolletProducerDTO();
    }
}
