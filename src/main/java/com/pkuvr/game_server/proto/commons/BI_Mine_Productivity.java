// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: BI_Mine_Productivity.proto

package com.pkuvr.game_server.proto.commons;

public final class BI_Mine_Productivity {
    private static com.google.protobuf.Descriptors.Descriptor
            internal_static_Mine_Productivity_Mes_descriptor;
    private static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
            internal_static_Mine_Productivity_Mes_fieldAccessorTable;
    private static com.google.protobuf.Descriptors.FileDescriptor
            descriptor;

    static {
        String[] descriptorData = {
                "\n\032BI_Mine_Productivity.proto\"[\n\025Mine_Pro" +
                        "ductivity_Mes\022\024\n\014npcResMineId\030\001 \001(\003\022\024\n\014p" +
                        "roductivity\030\002 \001(\005\022\026\n\016produceResType\030\003 \001(" +
                        "\005B9\n!com.pkuvr.game_server.proto.commonsB\024" +
                        "BI_Mine_Productivity"
        };
        com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
                new com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner() {
                    public com.google.protobuf.ExtensionRegistry assignDescriptors(
                            com.google.protobuf.Descriptors.FileDescriptor root) {
                        descriptor = root;
                        internal_static_Mine_Productivity_Mes_descriptor =
                                getDescriptor().getMessageTypes().get(0);
                        internal_static_Mine_Productivity_Mes_fieldAccessorTable = new
                                com.google.protobuf.GeneratedMessage.FieldAccessorTable(
                                internal_static_Mine_Productivity_Mes_descriptor,
                                new String[]{"NpcResMineId", "Productivity", "ProduceResType",},
                                com.pkuvr.game_server.proto.commons.BI_Mine_Productivity.Mine_Productivity_Mes.class,
                                com.pkuvr.game_server.proto.commons.BI_Mine_Productivity.Mine_Productivity_Mes.Builder.class);
                        return null;
                    }
                };
        com.google.protobuf.Descriptors.FileDescriptor
                .internalBuildGeneratedFileFrom(descriptorData,
                        new com.google.protobuf.Descriptors.FileDescriptor[]{
                        }, assigner);
    }

    private BI_Mine_Productivity() {
    }

    public static void registerAllExtensions(
            com.google.protobuf.ExtensionRegistry registry) {
    }

    public static com.google.protobuf.Descriptors.FileDescriptor
    getDescriptor() {
        return descriptor;
    }

    public interface Mine_Productivity_MesOrBuilder
            extends com.google.protobuf.MessageOrBuilder {

        // optional int64 npcResMineId = 1;
        boolean hasNpcResMineId();

        long getNpcResMineId();

        // optional int32 productivity = 2;
        boolean hasProductivity();

        int getProductivity();

        // optional int32 produceResType = 3;
        boolean hasProduceResType();

        int getProduceResType();
    }

    public static final class Mine_Productivity_Mes extends
            com.google.protobuf.GeneratedMessage
            implements Mine_Productivity_MesOrBuilder {
        // optional int64 npcResMineId = 1;
        public static final int NPCRESMINEID_FIELD_NUMBER = 1;
        // optional int32 productivity = 2;
        public static final int PRODUCTIVITY_FIELD_NUMBER = 2;
        // optional int32 produceResType = 3;
        public static final int PRODUCERESTYPE_FIELD_NUMBER = 3;
        private static final Mine_Productivity_Mes defaultInstance;
        private static final long serialVersionUID = 0L;

        static {
            defaultInstance = new Mine_Productivity_Mes(true);
            defaultInstance.initFields();
        }

        private int bitField0_;
        private long npcResMineId_;
        private int productivity_;
        private int produceResType_;
        private byte memoizedIsInitialized = -1;
        private int memoizedSerializedSize = -1;

        // Use Mine_Productivity_Mes.newBuilder() to construct.
        private Mine_Productivity_Mes(Builder builder) {
            super(builder);
        }
        private Mine_Productivity_Mes(boolean noInit) {
        }

        public static Mine_Productivity_Mes getDefaultInstance() {
            return defaultInstance;
        }

        public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
            return com.pkuvr.game_server.proto.commons.BI_Mine_Productivity.internal_static_Mine_Productivity_Mes_descriptor;
        }

        public static com.pkuvr.game_server.proto.commons.BI_Mine_Productivity.Mine_Productivity_Mes parseFrom(
                com.google.protobuf.ByteString data)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return newBuilder().mergeFrom(data).buildParsed();
        }

        public static com.pkuvr.game_server.proto.commons.BI_Mine_Productivity.Mine_Productivity_Mes parseFrom(
                com.google.protobuf.ByteString data,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return newBuilder().mergeFrom(data, extensionRegistry)
                    .buildParsed();
        }

        public static com.pkuvr.game_server.proto.commons.BI_Mine_Productivity.Mine_Productivity_Mes parseFrom(byte[] data)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return newBuilder().mergeFrom(data).buildParsed();
        }

        public static com.pkuvr.game_server.proto.commons.BI_Mine_Productivity.Mine_Productivity_Mes parseFrom(
                byte[] data,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return newBuilder().mergeFrom(data, extensionRegistry)
                    .buildParsed();
        }

        public static com.pkuvr.game_server.proto.commons.BI_Mine_Productivity.Mine_Productivity_Mes parseFrom(java.io.InputStream input)
                throws java.io.IOException {
            return newBuilder().mergeFrom(input).buildParsed();
        }

        public static com.pkuvr.game_server.proto.commons.BI_Mine_Productivity.Mine_Productivity_Mes parseFrom(
                java.io.InputStream input,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws java.io.IOException {
            return newBuilder().mergeFrom(input, extensionRegistry)
                    .buildParsed();
        }

        public static com.pkuvr.game_server.proto.commons.BI_Mine_Productivity.Mine_Productivity_Mes parseDelimitedFrom(java.io.InputStream input)
                throws java.io.IOException {
            Builder builder = newBuilder();
            if (builder.mergeDelimitedFrom(input)) {
                return builder.buildParsed();
            } else {
                return null;
            }
        }

        public static com.pkuvr.game_server.proto.commons.BI_Mine_Productivity.Mine_Productivity_Mes parseDelimitedFrom(
                java.io.InputStream input,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws java.io.IOException {
            Builder builder = newBuilder();
            if (builder.mergeDelimitedFrom(input, extensionRegistry)) {
                return builder.buildParsed();
            } else {
                return null;
            }
        }

        public static com.pkuvr.game_server.proto.commons.BI_Mine_Productivity.Mine_Productivity_Mes parseFrom(
                com.google.protobuf.CodedInputStream input)
                throws java.io.IOException {
            return newBuilder().mergeFrom(input).buildParsed();
        }

        public static com.pkuvr.game_server.proto.commons.BI_Mine_Productivity.Mine_Productivity_Mes parseFrom(
                com.google.protobuf.CodedInputStream input,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws java.io.IOException {
            return newBuilder().mergeFrom(input, extensionRegistry)
                    .buildParsed();
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        public static Builder newBuilder(com.pkuvr.game_server.proto.commons.BI_Mine_Productivity.Mine_Productivity_Mes prototype) {
            return newBuilder().mergeFrom(prototype);
        }

        public Mine_Productivity_Mes getDefaultInstanceForType() {
            return defaultInstance;
        }

        protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
            return com.pkuvr.game_server.proto.commons.BI_Mine_Productivity.internal_static_Mine_Productivity_Mes_fieldAccessorTable;
        }

        public boolean hasNpcResMineId() {
            return ((bitField0_ & 0x00000001) == 0x00000001);
        }

        public long getNpcResMineId() {
            return npcResMineId_;
        }

        public boolean hasProductivity() {
            return ((bitField0_ & 0x00000002) == 0x00000002);
        }

        public int getProductivity() {
            return productivity_;
        }

        public boolean hasProduceResType() {
            return ((bitField0_ & 0x00000004) == 0x00000004);
        }

        public int getProduceResType() {
            return produceResType_;
        }

        private void initFields() {
            npcResMineId_ = 0L;
            productivity_ = 0;
            produceResType_ = 0;
        }

        public final boolean isInitialized() {
            byte isInitialized = memoizedIsInitialized;
            if (isInitialized != -1) return isInitialized == 1;

            memoizedIsInitialized = 1;
            return true;
        }

        public void writeTo(com.google.protobuf.CodedOutputStream output)
                throws java.io.IOException {
            getSerializedSize();
            if (((bitField0_ & 0x00000001) == 0x00000001)) {
                output.writeInt64(1, npcResMineId_);
            }
            if (((bitField0_ & 0x00000002) == 0x00000002)) {
                output.writeInt32(2, productivity_);
            }
            if (((bitField0_ & 0x00000004) == 0x00000004)) {
                output.writeInt32(3, produceResType_);
            }
            getUnknownFields().writeTo(output);
        }

        public int getSerializedSize() {
            int size = memoizedSerializedSize;
            if (size != -1) return size;

            size = 0;
            if (((bitField0_ & 0x00000001) == 0x00000001)) {
                size += com.google.protobuf.CodedOutputStream
                        .computeInt64Size(1, npcResMineId_);
            }
            if (((bitField0_ & 0x00000002) == 0x00000002)) {
                size += com.google.protobuf.CodedOutputStream
                        .computeInt32Size(2, productivity_);
            }
            if (((bitField0_ & 0x00000004) == 0x00000004)) {
                size += com.google.protobuf.CodedOutputStream
                        .computeInt32Size(3, produceResType_);
            }
            size += getUnknownFields().getSerializedSize();
            memoizedSerializedSize = size;
            return size;
        }

        @Override
        protected Object writeReplace()
                throws java.io.ObjectStreamException {
            return super.writeReplace();
        }

        public Builder newBuilderForType() {
            return newBuilder();
        }

        public Builder toBuilder() {
            return newBuilder(this);
        }

        @Override
        protected Builder newBuilderForType(
                com.google.protobuf.GeneratedMessage.BuilderParent parent) {
            Builder builder = new Builder(parent);
            return builder;
        }

        public static final class Builder extends
                com.google.protobuf.GeneratedMessage.Builder<Builder>
                implements com.pkuvr.game_server.proto.commons.BI_Mine_Productivity.Mine_Productivity_MesOrBuilder {
            private int bitField0_;
            // optional int64 npcResMineId = 1;
            private long npcResMineId_;
            // optional int32 productivity = 2;
            private int productivity_;
            // optional int32 produceResType = 3;
            private int produceResType_;

            // Construct using com.pkuvr.game_server.proto.commons.BI_Mine_Productivity.Mine_Productivity_Mes.newBuilder()
            private Builder() {
                maybeForceBuilderInitialization();
            }

            private Builder(BuilderParent parent) {
                super(parent);
                maybeForceBuilderInitialization();
            }

            public static final com.google.protobuf.Descriptors.Descriptor
            getDescriptor() {
                return com.pkuvr.game_server.proto.commons.BI_Mine_Productivity.internal_static_Mine_Productivity_Mes_descriptor;
            }

            private static Builder create() {
                return new Builder();
            }

            protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
            internalGetFieldAccessorTable() {
                return com.pkuvr.game_server.proto.commons.BI_Mine_Productivity.internal_static_Mine_Productivity_Mes_fieldAccessorTable;
            }

            private void maybeForceBuilderInitialization() {
                if (com.google.protobuf.GeneratedMessage.alwaysUseFieldBuilders) {
                }
            }

            public Builder clear() {
                super.clear();
                npcResMineId_ = 0L;
                bitField0_ = (bitField0_ & ~0x00000001);
                productivity_ = 0;
                bitField0_ = (bitField0_ & ~0x00000002);
                produceResType_ = 0;
                bitField0_ = (bitField0_ & ~0x00000004);
                return this;
            }

            public Builder clone() {
                return create().mergeFrom(buildPartial());
            }

            public com.google.protobuf.Descriptors.Descriptor
            getDescriptorForType() {
                return com.pkuvr.game_server.proto.commons.BI_Mine_Productivity.Mine_Productivity_Mes.getDescriptor();
            }

            public com.pkuvr.game_server.proto.commons.BI_Mine_Productivity.Mine_Productivity_Mes getDefaultInstanceForType() {
                return com.pkuvr.game_server.proto.commons.BI_Mine_Productivity.Mine_Productivity_Mes.getDefaultInstance();
            }

            public com.pkuvr.game_server.proto.commons.BI_Mine_Productivity.Mine_Productivity_Mes build() {
                com.pkuvr.game_server.proto.commons.BI_Mine_Productivity.Mine_Productivity_Mes result = buildPartial();
                if (!result.isInitialized()) {
                    throw newUninitializedMessageException(result);
                }
                return result;
            }

            private com.pkuvr.game_server.proto.commons.BI_Mine_Productivity.Mine_Productivity_Mes buildParsed()
                    throws com.google.protobuf.InvalidProtocolBufferException {
                com.pkuvr.game_server.proto.commons.BI_Mine_Productivity.Mine_Productivity_Mes result = buildPartial();
                if (!result.isInitialized()) {
                    throw newUninitializedMessageException(
                            result).asInvalidProtocolBufferException();
                }
                return result;
            }

            public com.pkuvr.game_server.proto.commons.BI_Mine_Productivity.Mine_Productivity_Mes buildPartial() {
                com.pkuvr.game_server.proto.commons.BI_Mine_Productivity.Mine_Productivity_Mes result = new com.pkuvr.game_server.proto.commons.BI_Mine_Productivity.Mine_Productivity_Mes(this);
                int from_bitField0_ = bitField0_;
                int to_bitField0_ = 0;
                if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
                    to_bitField0_ |= 0x00000001;
                }
                result.npcResMineId_ = npcResMineId_;
                if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
                    to_bitField0_ |= 0x00000002;
                }
                result.productivity_ = productivity_;
                if (((from_bitField0_ & 0x00000004) == 0x00000004)) {
                    to_bitField0_ |= 0x00000004;
                }
                result.produceResType_ = produceResType_;
                result.bitField0_ = to_bitField0_;
                onBuilt();
                return result;
            }

            public Builder mergeFrom(com.google.protobuf.Message other) {
                if (other instanceof com.pkuvr.game_server.proto.commons.BI_Mine_Productivity.Mine_Productivity_Mes) {
                    return mergeFrom((com.pkuvr.game_server.proto.commons.BI_Mine_Productivity.Mine_Productivity_Mes) other);
                } else {
                    super.mergeFrom(other);
                    return this;
                }
            }

            public Builder mergeFrom(com.pkuvr.game_server.proto.commons.BI_Mine_Productivity.Mine_Productivity_Mes other) {
                if (other == com.pkuvr.game_server.proto.commons.BI_Mine_Productivity.Mine_Productivity_Mes.getDefaultInstance())
                    return this;
                if (other.hasNpcResMineId()) {
                    setNpcResMineId(other.getNpcResMineId());
                }
                if (other.hasProductivity()) {
                    setProductivity(other.getProductivity());
                }
                if (other.hasProduceResType()) {
                    setProduceResType(other.getProduceResType());
                }
                this.mergeUnknownFields(other.getUnknownFields());
                return this;
            }

            public final boolean isInitialized() {
                return true;
            }

            public Builder mergeFrom(
                    com.google.protobuf.CodedInputStream input,
                    com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                    throws java.io.IOException {
                com.google.protobuf.UnknownFieldSet.Builder unknownFields =
                        com.google.protobuf.UnknownFieldSet.newBuilder(
                                this.getUnknownFields());
                while (true) {
                    int tag = input.readTag();
                    switch (tag) {
                        case 0:
                            this.setUnknownFields(unknownFields.build());
                            onChanged();
                            return this;
                        default: {
                            if (!parseUnknownField(input, unknownFields,
                                    extensionRegistry, tag)) {
                                this.setUnknownFields(unknownFields.build());
                                onChanged();
                                return this;
                            }
                            break;
                        }
                        case 8: {
                            bitField0_ |= 0x00000001;
                            npcResMineId_ = input.readInt64();
                            break;
                        }
                        case 16: {
                            bitField0_ |= 0x00000002;
                            productivity_ = input.readInt32();
                            break;
                        }
                        case 24: {
                            bitField0_ |= 0x00000004;
                            produceResType_ = input.readInt32();
                            break;
                        }
                    }
                }
            }

            public boolean hasNpcResMineId() {
                return ((bitField0_ & 0x00000001) == 0x00000001);
            }

            public long getNpcResMineId() {
                return npcResMineId_;
            }

            public Builder setNpcResMineId(long value) {
                bitField0_ |= 0x00000001;
                npcResMineId_ = value;
                onChanged();
                return this;
            }

            public Builder clearNpcResMineId() {
                bitField0_ = (bitField0_ & ~0x00000001);
                npcResMineId_ = 0L;
                onChanged();
                return this;
            }

            public boolean hasProductivity() {
                return ((bitField0_ & 0x00000002) == 0x00000002);
            }

            public int getProductivity() {
                return productivity_;
            }

            public Builder setProductivity(int value) {
                bitField0_ |= 0x00000002;
                productivity_ = value;
                onChanged();
                return this;
            }

            public Builder clearProductivity() {
                bitField0_ = (bitField0_ & ~0x00000002);
                productivity_ = 0;
                onChanged();
                return this;
            }

            public boolean hasProduceResType() {
                return ((bitField0_ & 0x00000004) == 0x00000004);
            }

            public int getProduceResType() {
                return produceResType_;
            }

            public Builder setProduceResType(int value) {
                bitField0_ |= 0x00000004;
                produceResType_ = value;
                onChanged();
                return this;
            }

            public Builder clearProduceResType() {
                bitField0_ = (bitField0_ & ~0x00000004);
                produceResType_ = 0;
                onChanged();
                return this;
            }

            // @@protoc_insertion_point(builder_scope:Mine_Productivity_Mes)
        }

        // @@protoc_insertion_point(class_scope:Mine_Productivity_Mes)
    }

    // @@protoc_insertion_point(outer_class_scope)
}
