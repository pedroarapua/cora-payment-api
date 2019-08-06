package com.cora.payment.v1.mapper;

import com.cora.payment.entity.ClientEntity;
import com.cora.payment.v1.dto.ClientRequestDto;
import com.cora.payment.v1.dto.ClientResponseDto;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ClientMapper {
  private final ModelMapper modelMapper;
  
  @Autowired public ClientMapper(
      final ModelMapper modelMapper) { 
    this.modelMapper = modelMapper; 
  }
  
  public ClientResponseDto convertToDto(final ClientEntity client) {
    return this.modelMapper.map(client, ClientResponseDto.class);
  }
  
  public ClientEntity convertToEntity(final ClientRequestDto clientDto) {
    return this.modelMapper.map(clientDto, ClientEntity.class);
  }
}
