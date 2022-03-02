package com.syntifi.casper.sdk.model.deploy.transform;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.syntifi.casper.sdk.model.deploy.EraInfo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * An implmentation of Transform that Writes the given EraInfo to global state.
 * 
 * @see Transform
 * 
 * @author Alexandre Carvalho
 * @author Andre Bertolace
 * @since 0.0.1
 */
@Getter
@Setter
@Builder
@JsonTypeName("WriteEraInfo")
public class WriteEraInfo implements Transform {

    /**
     * @see EraInfo
     */
    @JsonProperty("WriteEraInfo")
    private EraInfo deployInfo;
}
