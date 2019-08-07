package com.organization.payment.v1.mapper;

import com.organization.payment.entity.ClientEntity;
import com.organization.payment.v1.dto.ClientRequestDto;
import com.organization.payment.v1.dto.ClientResponseDto;

import java.util.Optional;

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
  
  public ClientResponseDto convertToResponseDto(final ClientEntity client) {
    return this.modelMapper.map(client, ClientResponseDto.class);
  }
  
  public ClientEntity convertToEntity(final ClientRequestDto clientDto) {
    return this.modelMapper.map(clientDto, ClientEntity.class);
  }
}
