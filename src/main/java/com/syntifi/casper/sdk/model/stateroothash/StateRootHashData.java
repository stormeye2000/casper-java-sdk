package com.syntifi.casper.sdk.model.stateroothash;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * Root class for a Casper state root hash request
 * 
 * @author Alexandre Carvalho
 * @author Andre Bertolace
 * @since 0.0.1
 */
@Getter
@Setter
@Builder
public class StateRootHashData {

    /**
     * The RPC API version
     */
    @JsonProperty("api_version")
    private String apiVersion;

    /**
     * Hash
     */
    @JsonProperty("state_root_hash")
    private String stateRootHash;
}