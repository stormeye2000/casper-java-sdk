package com.syntifi.casper.sdk.model.peer;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * The node ID and network address of each connected peer.
 * 
 * @author Alexandre Carvalho
 * @author Andre Bertolace
 * @since 0.0.1
 */
@Getter
@Setter
@Builder
public class PeerEntry {

    /**
     * IP:PORT
     */
    private String address;

    /**
     * node ID
     */
    @JsonProperty("node_id")
    private String nodeId;
}
