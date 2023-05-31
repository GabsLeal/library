package com.moroTechLibrary.library.model.mapper;

import com.moroTechLibrary.library.dao.format.FormatDAO;
import com.moroTechLibrary.library.dto.FormatDTO;
import com.moroTechLibrary.library.model.Format;
import lombok.*;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Data
@Builder
@RequiredArgsConstructor
@Component
public class FormatMapper {
    private final ModelMapper modelMapper;

    public FormatDTO convertToDto(Format format) {
        return modelMapper.map(format, FormatDTO.class);
    }

    public Format convertToEntity(FormatDTO formatDTO) {
        return modelMapper.map(formatDTO, Format.class);
    }

    public FormatDAO convertToDAO(Format format) {
        return modelMapper.map(format, FormatDAO.class);
    }

    public List<FormatDTO> convertToListDto(List<Format> formatList) {
        return formatList.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public List<Format> convertDAOToListModel(@NonNull List<FormatDAO> formatDTOList) {
        return formatDTOList.stream()
                .map(this::convertDAOToFormat)
                .collect(Collectors.toList());
    }

    public Format convertDAOToFormat(FormatDAO formatDAO) {
        return modelMapper.map(formatDAO, Format.class);
    }

    public FormatDAO convertToFormatDAO(Format format) {
        return modelMapper.map(format, FormatDAO.class);
    }

    public List<FormatDTO> convertToListDtoFromDAO(List<FormatDAO> formatDAOList) {
        return formatDAOList.stream()
                .map(this::convertToDtoFromDAO)
                .collect(Collectors.toList());
    }

    public FormatDTO convertToDtoFromDAO(FormatDAO formatDAO) {
        return modelMapper.map(formatDAO, FormatDTO.class);
    }

    public List<FormatDAO> convertToListDAO(List<Format> formatList) {
        return formatList.stream()
                .map(this::convertToDAO)
                .collect(Collectors.toList());
    }


}
