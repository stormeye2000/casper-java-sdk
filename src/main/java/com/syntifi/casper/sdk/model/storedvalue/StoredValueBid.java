package com.syntifi.casper.sdk.model.storedvalue;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.syntifi.casper.sdk.model.bid.Bid;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * Stored Value for {@link Bid}
 * 
 * @author Alexandre Carvalho
 * @author Andre Bertolace
 * @see StoredValue
 * @since 0.0.1
 */
@Getter
@Setter
@Builder
@JsonTypeName("Bid")
public class StoredValueBid implements StoredValue<Bid> {
    @JsonProperty("Bid")
    public Bid value;
}
