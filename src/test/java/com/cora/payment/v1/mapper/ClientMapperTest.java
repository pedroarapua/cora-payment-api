package com.cora.payment.v1.mapper;

import com.cora.payment.factory.ClientEntityFactory;
import com.cora.payment.factory.ClientRequestDtoFactory;
import com.cora.payment.entity.ClientEntity;
import com.cora.payment.v1.dto.ClientRequestDto;
import com.cora.payment.v1.dto.ClientResponseDto;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

@RunWith(MockitoJUnitRunner.class)
public class ClientMapperTest {

  private ClientMapper clientMapper;

  @InjectMocks
  private ClientEntityFactory clientFactory;
  
  @InjectMocks
  private ClientRequestDtoFactory clientRequestDtoFactory;
  
  @InjectMocks
  private ModelMapper modelMapper;

  @Before
  public void setUp() {
    this.clientMapper = new ClientMapper(this.modelMapper);
  }

  @Test
  public void convertToResponseDto() {

    final ClientEntity clientEntity = this.clientFactory.simple();

    final ClientResponseDto clientResponseDto =
        this.clientMapper.convertToResponseDto(clientEntity);

    Assert.assertNotNull(clientResponseDto);
    Assert.assertEquals(clientEntity.getId(), clientResponseDto.getId());
    Assert.assertEquals(clientEntity.getName(), clientResponseDto.getName());
  }
  
  @Test
  public void convertToEntity() {

    final ClientRequestDto clientRequestDto = this.clientRequestDtoFactory.simple();

    final ClientEntity clientEntity =
        this.clientMapper.convertToEntity(clientRequestDto);

    Assert.assertNotNull(clientEntity);
    Assert.assertEquals(clientRequestDto.getName(), clientEntity.getName());
  }

}
