package com.casper.sdk.model.clvalue;

import com.casper.sdk.exception.NoSuchTypeException;
import com.casper.sdk.model.clvalue.cltype.AbstractCLTypeWithChildren;
import com.casper.sdk.model.clvalue.cltype.CLTypeData;
import com.casper.sdk.model.clvalue.cltype.CLTypeTuple3;
import com.casper.sdk.model.clvalue.serde.Target;
import com.fasterxml.jackson.annotation.JsonProperty;
import dev.oak3.sbs4j.DeserializerBuffer;
import dev.oak3.sbs4j.SerializerBuffer;
import dev.oak3.sbs4j.exception.ValueSerializationException;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bouncycastle.util.encoders.Hex;
import org.javatuples.Triplet;

import java.util.Arrays;

/**
 * Casper Tuple3 CLValue implementation
 *
 * @author Alexandre Carvalho
 * @author Andre Bertolace
 * @see AbstractCLValue
 * @since 0.0.1
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class CLValueTuple3 extends
        AbstractCLValueWithChildren<Triplet<? extends AbstractCLValue<?, ?>, ? extends AbstractCLValue<?, ?>, ? extends AbstractCLValue<?, ?>>, CLTypeTuple3> {
    @JsonProperty("cl_type")
    private CLTypeTuple3 clType = new CLTypeTuple3();

    public CLValueTuple3(Triplet<? extends AbstractCLValue<?, ?>, ? extends AbstractCLValue<?, ?>, ? extends AbstractCLValue<?, ?>> value) throws ValueSerializationException {
        setChildTypes(value);
        this.setValue(value);
    }

    @Override
    public void serialize(SerializerBuffer ser, Target target) throws NoSuchTypeException, ValueSerializationException {
        if (this.getValue() == null) return;

        if (target.equals(Target.BYTE)) {
            super.serializePrefixWithLength(ser);
        }

        setChildTypes(this.getValue());

        getValue().getValue0().serialize(ser);
        getValue().getValue1().serialize(ser);
        getValue().getValue2().serialize(ser);

        if (target.equals(Target.BYTE)) {
            this.encodeType(ser);
        }

        this.setBytes(Hex.toHexString(ser.toByteArray()));
    }

    @Override
    public void deserializeCustom(DeserializerBuffer deser) throws Exception {
        CLTypeData childTypeData1 = clType.getChildClTypeData(0);
        CLTypeData childTypeData2 = clType.getChildClTypeData(1);
        CLTypeData childTypeData3 = clType.getChildClTypeData(2);

        AbstractCLValue<?, ?> child1 = CLTypeData.createCLValueFromCLTypeData(childTypeData1);
        if (child1.getClType() instanceof AbstractCLTypeWithChildren) {
            ((AbstractCLTypeWithChildren) child1.getClType())
                    .setChildTypes(((AbstractCLTypeWithChildren) clType.getChildTypes().get(0)).getChildTypes());
        }
        child1.deserializeCustom(deser);

        AbstractCLValue<?, ?> child2 = CLTypeData.createCLValueFromCLTypeData(childTypeData2);
        if (child2.getClType() instanceof AbstractCLTypeWithChildren) {
            ((AbstractCLTypeWithChildren) child2.getClType())
                    .setChildTypes(((AbstractCLTypeWithChildren) clType.getChildTypes().get(1)).getChildTypes());
        }
        child2.deserializeCustom(deser);

        AbstractCLValue<?, ?> child3 = CLTypeData.createCLValueFromCLTypeData(childTypeData3);
        if (child3.getClType() instanceof AbstractCLTypeWithChildren) {
            ((AbstractCLTypeWithChildren) child3.getClType())
                    .setChildTypes(((AbstractCLTypeWithChildren) clType.getChildTypes().get(2)).getChildTypes());
        }
        child3.deserializeCustom(deser);

        setValue(new Triplet<>(child1, child2, child3));
    }

    @Override
    protected void setChildTypes(Triplet<? extends AbstractCLValue<?, ?>, ? extends AbstractCLValue<?, ?>, ? extends AbstractCLValue<?, ?>> value) {
        clType.setChildTypes(Arrays.asList(value.getValue0().getClType(), value.getValue1().getClType(),
                value.getValue2().getClType()));

    }
}
