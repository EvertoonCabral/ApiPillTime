package br.unipar.api.ApiPillTime.model.dto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CuidadorMapper {
    CuidadorMapper INSTANCE = Mappers.getMapper(CuidadorMapper.class);

 //   CuidadorDTO toDto(Cuidador cuidador);

  //  Cuidador fromDto(CuidadorDTO cuidadorDTO);
}

