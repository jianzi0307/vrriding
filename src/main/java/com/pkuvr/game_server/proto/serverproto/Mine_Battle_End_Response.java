// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: Mine_Battle_End_Res.proto

package com.pkuvr.game_server.proto.serverproto;

public final class Mine_Battle_End_Response {
    private static com.google.protobuf.Descriptors.Descriptor
            internal_static_Mine_Battle_End_Res_descriptor;
    private static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
            internal_static_Mine_Battle_End_Res_fieldAccessorTable;
    private static com.google.protobuf.Descriptors.FileDescriptor
            descriptor;

    static {
        String[] descriptorData = {
                "\n\031Mine_Battle_End_Res.proto\"o\n\023Mine_Batt" +
                        "le_End_Res\022\021\n\terrorCode\030\001 \002(\005\022\014\n\004gold\030\002 " +
                        "\001(\005\022\014\n\004iron\030\003 \001(\005\022\013\n\003oil\030\004 \001(\005\022\r\n\005stone\030" +
                        "\005 \001(\005\022\r\n\005honor\030\006 \001(\005BA\n%com.gfan.gameser" +
                        "ver.proto.serverprotoB\030Mine_Battle_End_R" +
                        "esponse"
        };
        com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
                new com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner() {
                    public com.google.protobuf.ExtensionRegistry assignDescriptors(
                            com.google.protobuf.Descriptors.FileDescriptor root) {
                        descriptor = root;
                        internal_static_Mine_Battle_End_Res_descriptor =
                                getDescriptor().getMessageTypes().get(0);
                        internal_static_Mine_Battle_End_Res_fieldAccessorTable = new
                                com.google.protobuf.GeneratedMessage.FieldAccessorTable(
                                internal_static_Mine_Battle_End_Res_descriptor,
                                new String[]{"ErrorCode", "Gold", "Iron", "Oil", "Stone", "Honor",},
                                com.pkuvr.game_server.proto.serverproto.Mine_Battle_End_Response.Mine_Battle_End_Res.class,
                                com.pkuvr.game_server.proto.serverproto.Mine_Battle_End_Response.Mine_Battle_End_Res.Builder.class);
                        return null;
                    }
                };
        com.google.protobuf.Descriptors.FileDescriptor
                .internalBuildGeneratedFileFrom(descriptorData,
                        new com.google.protobuf.Descriptors.FileDescriptor[]{
                        }, assigner);
    }

    private Mine_Battle_End_Response() {
    }

    public static void registerAllExtensions(
            com.google.protobuf.ExtensionRegistry registry) {
    }

    public static com.google.protobuf.Descriptors.FileDescriptor
    getDescriptor() {
        return descriptor;
    }

    public interface Mine_Battle_End_ResOrBuilder
            extends com.google.protobuf.MessageOrBuilder {

        // required int32 errorCode = 1;
        boolean hasErrorCode();

        int getErrorCode();

        // optional int32 gold = 2;
        boolean hasGold();

        int getGold();

        // optional int32 iron = 3;
        boolean hasIron();

        int getIron();

        // optional int32 oil = 4;
        boolean hasOil();

        int getOil();

        // optional int32 stone = 5;
        boolean hasStone();

        int getStone();

        // optional int32 honor = 6;
        boolean hasHonor();

        int getHonor();
    }

    public static final class Mine_Battle_End_Res extends
            com.google.protobuf.GeneratedMessage
            implements Mine_Battle_End_ResOrBuilder {
        // required int32 errorCode = 1;
        public static final int ERRORCODE_FIELD_NUMBER = 1;
        // optional int32 gold = 2;
        public static final int GOLD_FIELD_NUMBER = 2;
        // optional int32 iron = 3;
        public static final int IRON_FIELD_NUMBER = 3;
        // optional int32 oil = 4;
        public static final int OIL_FIELD_NUMBER = 4;
        // optional int32 stone = 5;
        public static final int STONE_FIELD_NUMBER = 5;
        // optional int32 honor = 6;
        public static final int HONOR_FIELD_NUMBER = 6;
        private static final Mine_Battle_End_Res defaultInstance;
        private static final long serialVersionUID = 0L;

        static {
            defaultInstance = new Mine_Battle_End_Res(true);
            defaultInstance.initFields();
        }

        private int bitField0_;
        private int errorCode_;
        private int gold_;
        private int iron_;
        private int oil_;
        private int stone_;
        private int honor_;
        private byte memoizedIsInitialized = -1;
        private int memoizedSerializedSize = -1;

        // Use Mine_Battle_End_Res.newBuilder() to construct.
        private Mine_Battle_End_Res(Builder builder) {
            super(builder);
        }

        private Mine_Battle_End_Res(boolean noInit) {
        }

        public static Mine_Battle_End_Res getDefaultInstance() {
            return defaultInstance;
        }

        public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
            return com.pkuvr.game_server.proto.serverproto.Mine_Battle_End_Response.internal_static_Mine_Battle_End_Res_descriptor;
        }

        public static com.pkuvr.game_server.proto.serverproto.Mine_Battle_End_Response.Mine_Battle_End_Res parseFrom(
                com.google.protobuf.ByteString data)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return newBuilder().mergeFrom(data).buildParsed();
        }

        public static com.pkuvr.game_server.proto.serverproto.Mine_Battle_End_Response.Mine_Battle_End_Res parseFrom(
                com.google.protobuf.ByteString data,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return newBuilder().mergeFrom(data, extensionRegistry)
                    .buildParsed();
        }

        public static com.pkuvr.game_server.proto.serverproto.Mine_Battle_End_Response.Mine_Battle_End_Res parseFrom(byte[] data)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return newBuilder().mergeFrom(data).buildParsed();
        }

        public static com.pkuvr.game_server.proto.serverproto.Mine_Battle_End_Response.Mine_Battle_End_Res parseFrom(
                byte[] data,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return newBuilder().mergeFrom(data, extensionRegistry)
                    .buildParsed();
        }

        public static com.pkuvr.game_server.proto.serverproto.Mine_Battle_End_Response.Mine_Battle_End_Res parseFrom(java.io.InputStream input)
                throws java.io.IOException {
            return newBuilder().mergeFrom(input).buildParsed();
        }

        public static com.pkuvr.game_server.proto.serverproto.Mine_Battle_End_Response.Mine_Battle_End_Res parseFrom(
                java.io.InputStream input,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws java.io.IOException {
            return newBuilder().mergeFrom(input, extensionRegistry)
                    .buildParsed();
        }

        public static com.pkuvr.game_server.proto.serverproto.Mine_Battle_End_Response.Mine_Battle_End_Res parseDelimitedFrom(java.io.InputStream input)
                throws java.io.IOException {
            Builder builder = newBuilder();
            if (builder.mergeDelimitedFrom(input)) {
                return builder.buildParsed();
            } else {
                return null;
            }
        }

        public static com.pkuvr.game_server.proto.serverproto.Mine_Battle_End_Response.Mine_Battle_End_Res parseDelimitedFrom(
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

        public static com.pkuvr.game_server.proto.serverproto.Mine_Battle_End_Response.Mine_Battle_End_Res parseFrom(
                com.google.protobuf.CodedInputStream input)
                throws java.io.IOException {
            return newBuilder().mergeFrom(input).buildParsed();
        }

        public static com.pkuvr.game_server.proto.serverproto.Mine_Battle_End_Response.Mine_Battle_End_Res parseFrom(
                com.google.protobuf.CodedInputStream input,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws java.io.IOException {
            return newBuilder().mergeFrom(input, extensionRegistry)
                    .buildParsed();
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        public static Builder newBuilder(com.pkuvr.game_server.proto.serverproto.Mine_Battle_End_Response.Mine_Battle_End_Res prototype) {
            return newBuilder().mergeFrom(prototype);
        }

        public Mine_Battle_End_Res getDefaultInstanceForType() {
            return defaultInstance;
        }

        protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
            return com.pkuvr.game_server.proto.serverproto.Mine_Battle_End_Response.internal_static_Mine_Battle_End_Res_fieldAccessorTable;
        }

        public boolean hasErrorCode() {
            return ((bitField0_ & 0x00000001) == 0x00000001);
        }

        public int getErrorCode() {
            return errorCode_;
        }

        public boolean hasGold() {
            return ((bitField0_ & 0x00000002) == 0x00000002);
        }

        public int getGold() {
            return gold_;
        }

        public boolean hasIron() {
            return ((bitField0_ & 0x00000004) == 0x00000004);
        }

        public int getIron() {
            return iron_;
        }

        public boolean hasOil() {
            return ((bitField0_ & 0x00000008) == 0x00000008);
        }

        public int getOil() {
            return oil_;
        }

        public boolean hasStone() {
            return ((bitField0_ & 0x00000010) == 0x00000010);
        }

        public int getStone() {
            return stone_;
        }

        public boolean hasHonor() {
            return ((bitField0_ & 0x00000020) == 0x00000020);
        }

        public int getHonor() {
            return honor_;
        }

        private void initFields() {
            errorCode_ = 0;
            gold_ = 0;
            iron_ = 0;
            oil_ = 0;
            stone_ = 0;
            honor_ = 0;
        }

        public final boolean isInitialized() {
            byte isInitialized = memoizedIsInitialized;
            if (isInitialized != -1) return isInitialized == 1;

            if (!hasErrorCode()) {
                memoizedIsInitialized = 0;
                return false;
            }
            memoizedIsInitialized = 1;
            return true;
        }

        public void writeTo(com.google.protobuf.CodedOutputStream output)
                throws java.io.IOException {
            getSerializedSize();
            if (((bitField0_ & 0x00000001) == 0x00000001)) {
                output.writeInt32(1, errorCode_);
            }
            if (((bitField0_ & 0x00000002) == 0x00000002)) {
                output.writeInt32(2, gold_);
            }
            if (((bitField0_ & 0x00000004) == 0x00000004)) {
                output.writeInt32(3, iron_);
            }
            if (((bitField0_ & 0x00000008) == 0x00000008)) {
                output.writeInt32(4, oil_);
            }
            if (((bitField0_ & 0x00000010) == 0x00000010)) {
                output.writeInt32(5, stone_);
            }
            if (((bitField0_ & 0x00000020) == 0x00000020)) {
                output.writeInt32(6, honor_);
            }
            getUnknownFields().writeTo(output);
        }

        public int getSerializedSize() {
            int size = memoizedSerializedSize;
            if (size != -1) return size;

            size = 0;
            if (((bitField0_ & 0x00000001) == 0x00000001)) {
                size += com.google.protobuf.CodedOutputStream
                        .computeInt32Size(1, errorCode_);
            }
            if (((bitField0_ & 0x00000002) == 0x00000002)) {
                size += com.google.protobuf.CodedOutputStream
                        .computeInt32Size(2, gold_);
            }
            if (((bitField0_ & 0x00000004) == 0x00000004)) {
                size += com.google.protobuf.CodedOutputStream
                        .computeInt32Size(3, iron_);
            }
            if (((bitField0_ & 0x00000008) == 0x00000008)) {
                size += com.google.protobuf.CodedOutputStream
                        .computeInt32Size(4, oil_);
            }
            if (((bitField0_ & 0x00000010) == 0x00000010)) {
                size += com.google.protobuf.CodedOutputStream
                        .computeInt32Size(5, stone_);
            }
            if (((bitField0_ & 0x00000020) == 0x00000020)) {
                size += com.google.protobuf.CodedOutputStream
                        .computeInt32Size(6, honor_);
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
                implements com.pkuvr.game_server.proto.serverproto.Mine_Battle_End_Response.Mine_Battle_End_ResOrBuilder {
            private int bitField0_;
            // required int32 errorCode = 1;
            private int errorCode_;
            // optional int32 gold = 2;
            private int gold_;
            // optional int32 iron = 3;
            private int iron_;
            // optional int32 oil = 4;
            private int oil_;
            // optional int32 stone = 5;
            private int stone_;
            // optional int32 honor = 6;
            private int honor_;

            // Construct using com.pkuvr.game_server.proto.serverproto.Mine_Battle_End_Response.Mine_Battle_End_Res.newBuilder()
            private Builder() {
                maybeForceBuilderInitialization();
            }

            private Builder(BuilderParent parent) {
                super(parent);
                maybeForceBuilderInitialization();
            }

            public static final com.google.protobuf.Descriptors.Descriptor
            getDescriptor() {
                return com.pkuvr.game_server.proto.serverproto.Mine_Battle_End_Response.internal_static_Mine_Battle_End_Res_descriptor;
            }

            private static Builder create() {
                return new Builder();
            }

            protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
            internalGetFieldAccessorTable() {
                return com.pkuvr.game_server.proto.serverproto.Mine_Battle_End_Response.internal_static_Mine_Battle_End_Res_fieldAccessorTable;
            }

            private void maybeForceBuilderInitialization() {
                if (com.google.protobuf.GeneratedMessage.alwaysUseFieldBuilders) {
                }
            }

            public Builder clear() {
                super.clear();
                errorCode_ = 0;
                bitField0_ = (bitField0_ & ~0x00000001);
                gold_ = 0;
                bitField0_ = (bitField0_ & ~0x00000002);
                iron_ = 0;
                bitField0_ = (bitField0_ & ~0x00000004);
                oil_ = 0;
                bitField0_ = (bitField0_ & ~0x00000008);
                stone_ = 0;
                bitField0_ = (bitField0_ & ~0x00000010);
                honor_ = 0;
                bitField0_ = (bitField0_ & ~0x00000020);
                return this;
            }

            public Builder clone() {
                return create().mergeFrom(buildPartial());
            }

            public com.google.protobuf.Descriptors.Descriptor
            getDescriptorForType() {
                return com.pkuvr.game_server.proto.serverproto.Mine_Battle_End_Response.Mine_Battle_End_Res.getDescriptor();
            }

            public com.pkuvr.game_server.proto.serverproto.Mine_Battle_End_Response.Mine_Battle_End_Res getDefaultInstanceForType() {
                return com.pkuvr.game_server.proto.serverproto.Mine_Battle_End_Response.Mine_Battle_End_Res.getDefaultInstance();
            }

            public com.pkuvr.game_server.proto.serverproto.Mine_Battle_End_Response.Mine_Battle_End_Res build() {
                com.pkuvr.game_server.proto.serverproto.Mine_Battle_End_Response.Mine_Battle_End_Res result = buildPartial();
                if (!result.isInitialized()) {
                    throw newUninitializedMessageException(result);
                }
                return result;
            }

            private com.pkuvr.game_server.proto.serverproto.Mine_Battle_End_Response.Mine_Battle_End_Res buildParsed()
                    throws com.google.protobuf.InvalidProtocolBufferException {
                com.pkuvr.game_server.proto.serverproto.Mine_Battle_End_Response.Mine_Battle_End_Res result = buildPartial();
                if (!result.isInitialized()) {
                    throw newUninitializedMessageException(
                            result).asInvalidProtocolBufferException();
                }
                return result;
            }

            public com.pkuvr.game_server.proto.serverproto.Mine_Battle_End_Response.Mine_Battle_End_Res buildPartial() {
                com.pkuvr.game_server.proto.serverproto.Mine_Battle_End_Response.Mine_Battle_End_Res result = new com.pkuvr.game_server.proto.serverproto.Mine_Battle_End_Response.Mine_Battle_End_Res(this);
                int from_bitField0_ = bitField0_;
                int to_bitField0_ = 0;
                if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
                    to_bitField0_ |= 0x00000001;
                }
                result.errorCode_ = errorCode_;
                if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
                    to_bitField0_ |= 0x00000002;
                }
                result.gold_ = gold_;
                if (((from_bitField0_ & 0x00000004) == 0x00000004)) {
                    to_bitField0_ |= 0x00000004;
                }
                result.iron_ = iron_;
                if (((from_bitField0_ & 0x00000008) == 0x00000008)) {
                    to_bitField0_ |= 0x00000008;
                }
                result.oil_ = oil_;
                if (((from_bitField0_ & 0x00000010) == 0x00000010)) {
                    to_bitField0_ |= 0x00000010;
                }
                result.stone_ = stone_;
                if (((from_bitField0_ & 0x00000020) == 0x00000020)) {
                    to_bitField0_ |= 0x00000020;
                }
                result.honor_ = honor_;
                result.bitField0_ = to_bitField0_;
                onBuilt();
                return result;
            }

            public Builder mergeFrom(com.google.protobuf.Message other) {
                if (other instanceof com.pkuvr.game_server.proto.serverproto.Mine_Battle_End_Response.Mine_Battle_End_Res) {
                    return mergeFrom((com.pkuvr.game_server.proto.serverproto.Mine_Battle_End_Response.Mine_Battle_End_Res) other);
                } else {
                    super.mergeFrom(other);
                    return this;
                }
            }

            public Builder mergeFrom(com.pkuvr.game_server.proto.serverproto.Mine_Battle_End_Response.Mine_Battle_End_Res other) {
                if (other == com.pkuvr.game_server.proto.serverproto.Mine_Battle_End_Response.Mine_Battle_End_Res.getDefaultInstance())
                    return this;
                if (other.hasErrorCode()) {
                    setErrorCode(other.getErrorCode());
                }
                if (other.hasGold()) {
                    setGold(other.getGold());
                }
                if (other.hasIron()) {
                    setIron(other.getIron());
                }
                if (other.hasOil()) {
                    setOil(other.getOil());
                }
                if (other.hasStone()) {
                    setStone(other.getStone());
                }
                if (other.hasHonor()) {
                    setHonor(other.getHonor());
                }
                this.mergeUnknownFields(other.getUnknownFields());
                return this;
            }

            public final boolean isInitialized() {
                if (!hasErrorCode()) {

                    return false;
                }
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
                            errorCode_ = input.readInt32();
                            break;
                        }
                        case 16: {
                            bitField0_ |= 0x00000002;
                            gold_ = input.readInt32();
                            break;
                        }
                        case 24: {
                            bitField0_ |= 0x00000004;
                            iron_ = input.readInt32();
                            break;
                        }
                        case 32: {
                            bitField0_ |= 0x00000008;
                            oil_ = input.readInt32();
                            break;
                        }
                        case 40: {
                            bitField0_ |= 0x00000010;
                            stone_ = input.readInt32();
                            break;
                        }
                        case 48: {
                            bitField0_ |= 0x00000020;
                            honor_ = input.readInt32();
                            break;
                        }
                    }
                }
            }

            public boolean hasErrorCode() {
                return ((bitField0_ & 0x00000001) == 0x00000001);
            }

            public int getErrorCode() {
                return errorCode_;
            }

            public Builder setErrorCode(int value) {
                bitField0_ |= 0x00000001;
                errorCode_ = value;
                onChanged();
                return this;
            }

            public Builder clearErrorCode() {
                bitField0_ = (bitField0_ & ~0x00000001);
                errorCode_ = 0;
                onChanged();
                return this;
            }

            public boolean hasGold() {
                return ((bitField0_ & 0x00000002) == 0x00000002);
            }

            public int getGold() {
                return gold_;
            }

            public Builder setGold(int value) {
                bitField0_ |= 0x00000002;
                gold_ = value;
                onChanged();
                return this;
            }

            public Builder clearGold() {
                bitField0_ = (bitField0_ & ~0x00000002);
                gold_ = 0;
                onChanged();
                return this;
            }

            public boolean hasIron() {
                return ((bitField0_ & 0x00000004) == 0x00000004);
            }

            public int getIron() {
                return iron_;
            }

            public Builder setIron(int value) {
                bitField0_ |= 0x00000004;
                iron_ = value;
                onChanged();
                return this;
            }

            public Builder clearIron() {
                bitField0_ = (bitField0_ & ~0x00000004);
                iron_ = 0;
                onChanged();
                return this;
            }

            public boolean hasOil() {
                return ((bitField0_ & 0x00000008) == 0x00000008);
            }

            public int getOil() {
                return oil_;
            }

            public Builder setOil(int value) {
                bitField0_ |= 0x00000008;
                oil_ = value;
                onChanged();
                return this;
            }

            public Builder clearOil() {
                bitField0_ = (bitField0_ & ~0x00000008);
                oil_ = 0;
                onChanged();
                return this;
            }

            public boolean hasStone() {
                return ((bitField0_ & 0x00000010) == 0x00000010);
            }

            public int getStone() {
                return stone_;
            }

            public Builder setStone(int value) {
                bitField0_ |= 0x00000010;
                stone_ = value;
                onChanged();
                return this;
            }

            public Builder clearStone() {
                bitField0_ = (bitField0_ & ~0x00000010);
                stone_ = 0;
                onChanged();
                return this;
            }

            public boolean hasHonor() {
                return ((bitField0_ & 0x00000020) == 0x00000020);
            }

            public int getHonor() {
                return honor_;
            }

            public Builder setHonor(int value) {
                bitField0_ |= 0x00000020;
                honor_ = value;
                onChanged();
                return this;
            }

            public Builder clearHonor() {
                bitField0_ = (bitField0_ & ~0x00000020);
                honor_ = 0;
                onChanged();
                return this;
            }

            // @@protoc_insertion_point(builder_scope:Mine_Battle_End_Res)
        }

        // @@protoc_insertion_point(class_scope:Mine_Battle_End_Res)
    }

    // @@protoc_insertion_point(outer_class_scope)
}