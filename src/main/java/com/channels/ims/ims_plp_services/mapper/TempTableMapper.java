package com.channels.ims.ims_plp_services.mapper;

import com.channels.ims.ims_plp_services.dto.temptable.create.GenerateTempTableResponse;
import com.channels.ims.ims_plp_services.dto.temptable.fetch.single.SingleTempTableResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.time.format.DateTimeFormatter;

@Mapper
public interface TempTableMapper {

//    TempTableMapper INSTANCE = Mappers.getMapper(TempTableMapper.class);
//
//    @Mapping(target = "creationDate", expression = "java(formatDate(tempTable.getCreationDate()))")
//    GenerateTempTableResponse toResponse(TempTable tempTable);
//
//    @Mapping(target = "creationDate", expression = "java(formatDate(tempTable.getCreationDate()))")
//    SingleTempTableResponse singleResponse(TempTable tempTable);
//
//    default String formatDate(java.time.LocalDateTime dateTime) {
//        return dateTime != null ? dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) : null;
//    }
}
