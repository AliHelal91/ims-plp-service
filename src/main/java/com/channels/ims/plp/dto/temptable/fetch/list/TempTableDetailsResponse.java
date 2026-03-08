package com.channels.ims.plp.dto.temptable.fetch.list;

import com.channels.ims.plp.dto.temptable.fetch.TempTableDetails;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@EqualsAndHashCode
public class TempTableDetailsResponse {

    private Long totalElement;
    private Integer totalPages;
    private List<TempTableDetails> tempTableDetails;


}
