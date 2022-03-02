package com.syntifi.casper.sdk.model.block;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * A JSON-friendly representation of `Block`
 * 
 * @author Alexandre Carvalho
 * @author Andre Bertolace
 * @see JsonBlockData
 * @since 0.0.1
 */
@Getter
@Setter
@Builder
public class JsonBlock {

    /**
     * The block's hash.
     */
    @JsonProperty("hash")
    private String hash;

    /**
     * {@link JsonBlockHeader}
     */
    @JsonProperty("header")
    private JsonBlockHeader header;

    /**
     * {@link JsonBlockBody}
     */
    @JsonProperty("body")
    private JsonBlockBody body;

    /**
     * List of {@link JsonProof}
     */
    @JsonProperty("proofs")
    private List<JsonProof> proofs;
}
