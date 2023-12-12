package com.Proyecto.poyect1.http_response;

import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)//PARA QUE NO SALGAN LOS NULOS
@JsonPropertyOrder("Total Records,Page Size,Total Pages,Next,Previous,data")
@Builder
public class Response {


    @JsonProperty("Total Records")//PARA CAMBIAR EL NOMBRE DEL JSON Y SE VEA MAS LEGIBLE
    private Integer totalRecords;
    
    @JsonProperty("Page")
    private Integer page;

    @JsonProperty("Page Size")
    private Integer pageSize;

    @JsonProperty("Total Pages")
    private Integer totalPages;

    @JsonProperty("Next")
    private String next;

    @JsonProperty("Previous")
    private String previous;
    @JsonProperty("data")
    private Object data;
 
    //  public Response(Object data, Integer totalRecords, Optional<Integer> page, int pageSize) {
    //      this.data = data;
    //      this.totalRecords = totalRecords;
    //      if(page.isPresent())
    //          buildPaginationMetaData(totalRecords, pageSize, page.get());
    //  }
 
    private void buildPaginationMetaData(int totalRecords, int pageSize, int page) {
        this.page = page;
        this.pageSize = pageSize;
        int totalPages = (int) (Math.ceil((double) totalRecords / pageSize));
        this.totalPages = totalPages;
 
        if(page > 1 && totalPages > 1)
            this.previous = "/movies?page=" + (page - 1);
        if(page < totalPages)
            this.next = "/movies?page=" + (page + 1);
    }
}
