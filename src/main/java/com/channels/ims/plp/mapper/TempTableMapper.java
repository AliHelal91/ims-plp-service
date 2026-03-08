//package com.channels.ims.plp.mapper;
//
//import org.mapstruct.Mapper;
//
//@Mapper
//public interface TempTableMapper {
//
////    TempTableMapper INSTANCE = Mappers.getMapper(TempTableMapper.class);
////
////    @Mapping(target = "creationDate", expression = "java(formatDate(tempTable.getCreationDate()))")
////    GenerateTempTableResponse toResponse(TempTable tempTable);
////
////    @Mapping(target = "creationDate", expression = "java(formatDate(tempTable.getCreationDate()))")
////    SingleTempTableResponse singleResponse(TempTable tempTable);
////
////    default String formatDate(java.time.LocalDateTime dateTime) {
////        return dateTime != null ? dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) : null;
////    }
//}
