// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: Mine_Collect_Start_Res.proto

package com.pkuvr.game_server.proto.serverproto;

public final class Mine_Collect_Start_Response {
    private static com.google.protobuf.Descriptors.Descriptor
            internal_static_Mine_Collect_Start_Res_descriptor;
    private static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
            internal_static_Mine_Collect_Start_Res_fieldAccessorTable;
    private static com.google.protobuf.Descriptors.FileDescriptor
            descriptor;

    static {
        String[] descriptorData = {
                "\n\034Mine_Collect_Start_Res.proto\"+\n\026Mine_C" +
                        "ollect_Start_Res\022\021\n\terrorCode\030\001 \002(\005BD\n%c" +
                        "om.pkuvr.game_server.proto.serverprotoB\033Mi" +
                        "ne_Collect_Start_Response"
        };
        com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
                new com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner() {
                    public com.google.protobuf.ExtensionRegistry assignDescriptors(
                            com.google.protobuf.Descriptors.FileDescriptor root) {
                        descriptor = root;
                        internal_static_Mine_Collect_Start_Res_descriptor =
                                getDescriptor().getMessageTypes().get(0);
                        internal_static_Mine_Collect_Start_Res_fieldAccessorTable = new
                                com.google.protobuf.GeneratedMessage.FieldAccessorTable(
                                internal_static_Mine_Collect_Start_Res_descriptor,
                                new String[]{"ErrorCode",},
                                com.pkuvr.game_server.proto.serverproto.Mine_Collect_Start_Response.Mine_Collect_Start_Res.class,
                                com.pkuvr.game_server.proto.serverproto.Mine_Collect_Start_Response.Mine_Collect_Start_Res.Builder.class);
                        return null;
                    }
                };
        com.google.protobuf.Descriptors.FileDescriptor
                .internalBuildGeneratedFileFrom(descriptorData,
                        new com.google.protobuf.Descriptors.FileDescriptor[]{
                        }, assigner);
    }

    private Mine_Collect_Start_Response() {
    }

    public static void registerAllExtensions(
            com.google.protobuf.ExtensionRegistry registry) {
    }

    public static com.google.protobuf.Descriptors.FileDescriptor
    getDescriptor() {
        return descriptor;
    }

    public interface Mine_Collect_Start_ResOrBuilder
            extends com.google.protobuf.MessageOrBuilder {

        // required int32 errorCode = 1;
        boolean hasErrorCode();

        int getErrorCode();
    }

    public static final class Mine_Collect_Start_Res extends
            com.google.protobuf.GeneratedMessage
            implements Mine_Collect_Start_ResOrBuilder {
        // required int32 errorCode = 1;
        public static final int ERRORCODE_FIELD_NUMBER = 1;
        private static final Mine_Collect_Start_Res defaultInstance;
        private static final long serialVersionUID = 0L;

        static {
            defaultInstance = new Mine_Collect_Start_Res(true);
            defaultInstance.initFields();
        }

        private int bitField0_;
        private int errorCode_;
        private byte memoizedIsInitialized = -1;
        private int memoizedSerializedSize = -1;
        // Use Mine_Collect_Start_Res.newBuilder() to construct.
        private Mine_Collect_Start_Res(Builder builder) {
            super(builder);
        }
        private Mine_Collect_Start_Res(boolean noInit) {
        }

        public static Mine_Collect_Start_Res getDefaultInstance() {
            return defaultInstance;
        }

        public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
            return com.pkuvr.game_server.proto.serverproto.Mine_Collect_Start_Response.internal_static_Mine_Collect_Start_Res_descriptor;
        }

        public static com.pkuvr.game_server.proto.serverproto.Mine_Collect_Start_Response.Mine_Collect_Start_Res parseFrom(
                com.google.protobuf.ByteString data)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return newBuilder().mergeFrom(data).buildParsed();
        }

        public static com.pkuvr.game_server.proto.serverproto.Mine_Collect_Start_Response.Mine_Collect_Start_Res parseFrom(
                com.google.protobuf.ByteString data,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return newBuilder().mergeFrom(data, extensionRegistry)
                    .buildParsed();
        }

        public static com.pkuvr.game_server.proto.serverproto.Mine_Collect_Start_Response.Mine_Collect_Start_Res parseFrom(byte[] data)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return newBuilder().mergeFrom(data).buildParsed();
        }

        public static com.pkuvr.game_server.proto.serverproto.Mine_Collect_Start_Response.Mine_Collect_Start_Res parseFrom(
                byte[] data,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return newBuilder().mergeFrom(data, extensionRegistry)
                    .buildParsed();
        }

        public static com.pkuvr.game_server.proto.serverproto.Mine_Collect_Start_Response.Mine_Collect_Start_Res parseFrom(java.io.InputStream input)
                throws java.io.IOException {
            return newBuilder().mergeFrom(input).buildParsed();
        }

        public static com.pkuvr.game_server.proto.serverproto.Mine_Collect_Start_Response.Mine_Collect_Start_Res parseFrom(
                java.io.InputStream input,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws java.io.IOException {
            return newBuilder().mergeFrom(input, extensionRegistry)
                    .buildParsed();
        }

        public static com.pkuvr.game_server.proto.serverproto.Mine_Collect_Start_Response.Mine_Collect_Start_Res parseDelimitedFrom(java.io.InputStream input)
                throws java.io.IOException {
            Builder builder = newBuilder();
            if (builder.mergeDelimitedFrom(input)) {
                return builder.buildParsed();
            } else {
                return null;
            }
        }

        public static com.pkuvr.game_server.proto.serverproto.Mine_Collect_Start_Response.Mine_Collect_Start_Res parseDelimitedFrom(
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

        public static com.pkuvr.game_server.proto.serverproto.Mine_Collect_Start_Response.Mine_Collect_Start_Res parseFrom(
                com.google.protobuf.CodedInputStream input)
                throws java.io.IOException {
            return newBuilder().mergeFrom(input).buildParsed();
        }

        public static com.pkuvr.game_server.proto.serverproto.Mine_Collect_Start_Response.Mine_Collect_Start_Res parseFrom(
                com.google.protobuf.CodedInputStream input,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws java.io.IOException {
            return newBuilder().mergeFrom(input, extensionRegistry)
                    .buildParsed();
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        public static Builder newBuilder(com.pkuvr.game_server.proto.serverproto.Mine_Collect_Start_Response.Mine_Collect_Start_Res prototype) {
            return newBuilder().mergeFrom(prototype);
        }

        public Mine_Collect_Start_Res getDefaultInstanceForType() {
            return defaultInstance;
        }

        protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
            return com.pkuvr.game_server.proto.serverproto.Mine_Collect_Start_Response.internal_static_Mine_Collect_Start_Res_fieldAccessorTable;
        }

        public boolean hasErrorCode() {
            return ((bitField0_ & 0x00000001) == 0x00000001);
        }

        public int getErrorCode() {
            return errorCode_;
        }

        private void initFields() {
            errorCode_ = 0;
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
                implements com.pkuvr.game_server.proto.serverproto.Mine_Collect_Start_Response.Mine_Collect_Start_ResOrBuilder {
            private int bitField0_;
            // required int32 errorCode = 1;
            private int errorCode_;

            // Construct using com.pkuvr.game_server.proto.serverproto.Mine_Collect_Start_Response.Mine_Collect_Start_Res.newBuilder()
            private Builder() {
                maybeForceBuilderInitialization();
            }

            private Builder(BuilderParent parent) {
                super(parent);
                maybeForceBuilderInitialization();
            }

            public static final com.google.protobuf.Descriptors.Descriptor
            getDescriptor() {
                return com.pkuvr.game_server.proto.serverproto.Mine_Collect_Start_Response.internal_static_Mine_Collect_Start_Res_descriptor;
            }

            private static Builder create() {
                return new Builder();
            }

            protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
            internalGetFieldAccessorTable() {
                return com.pkuvr.game_server.proto.serverproto.Mine_Collect_Start_Response.internal_static_Mine_Collect_Start_Res_fieldAccessorTable;
            }

            private void maybeForceBuilderInitialization() {
                if (com.google.protobuf.GeneratedMessage.alwaysUseFieldBuilders) {
                }
            }

            public Builder clear() {
                super.clear();
                errorCode_ = 0;
                bitField0_ = (bitField0_ & ~0x00000001);
                return this;
            }

            public Builder clone() {
                return create().mergeFrom(buildPartial());
            }

            public com.google.protobuf.Descriptors.Descriptor
            getDescriptorForType() {
                return com.pkuvr.game_server.proto.serverproto.Mine_Collect_Start_Response.Mine_Collect_Start_Res.getDescriptor();
            }

            public com.pkuvr.game_server.proto.serverproto.Mine_Collect_Start_Response.Mine_Collect_Start_Res getDefaultInstanceForType() {
                return com.pkuvr.game_server.proto.serverproto.Mine_Collect_Start_Response.Mine_Collect_Start_Res.getDefaultInstance();
            }

            public com.pkuvr.game_server.proto.serverproto.Mine_Collect_Start_Response.Mine_Collect_Start_Res build() {
                com.pkuvr.game_server.proto.serverproto.Mine_Collect_Start_Response.Mine_Collect_Start_Res result = buildPartial();
                if (!result.isInitialized()) {
                    throw newUninitializedMessageException(result);
                }
                return result;
            }

            private com.pkuvr.game_server.proto.serverproto.Mine_Collect_Start_Response.Mine_Collect_Start_Res buildParsed()
                    throws com.google.protobuf.InvalidProtocolBufferException {
                com.pkuvr.game_server.proto.serverproto.Mine_Collect_Start_Response.Mine_Collect_Start_Res result = buildPartial();
                if (!result.isInitialized()) {
                    throw newUninitializedMessageException(
                            result).asInvalidProtocolBufferException();
                }
                return result;
            }

            public com.pkuvr.game_server.proto.serverproto.Mine_Collect_Start_Response.Mine_Collect_Start_Res buildPartial() {
                com.pkuvr.game_server.proto.serverproto.Mine_Collect_Start_Response.Mine_Collect_Start_Res result = new com.pkuvr.game_server.proto.serverproto.Mine_Collect_Start_Response.Mine_Collect_Start_Res(this);
                int from_bitField0_ = bitField0_;
                int to_bitField0_ = 0;
                if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
                    to_bitField0_ |= 0x00000001;
                }
                result.errorCode_ = errorCode_;
                result.bitField0_ = to_bitField0_;
                onBuilt();
                return result;
            }

            public Builder mergeFrom(com.google.protobuf.Message other) {
                if (other instanceof com.pkuvr.game_server.proto.serverproto.Mine_Collect_Start_Response.Mine_Collect_Start_Res) {
                    return mergeFrom((com.pkuvr.game_server.proto.serverproto.Mine_Collect_Start_Response.Mine_Collect_Start_Res) other);
                } else {
                    super.mergeFrom(other);
                    return this;
                }
            }

            public Builder mergeFrom(com.pkuvr.game_server.proto.serverproto.Mine_Collect_Start_Response.Mine_Collect_Start_Res other) {
                if (other == com.pkuvr.game_server.proto.serverproto.Mine_Collect_Start_Response.Mine_Collect_Start_Res.getDefaultInstance())
                    return this;
                if (other.hasErrorCode()) {
                    setErrorCode(other.getErrorCode());
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

            // @@protoc_insertion_point(builder_scope:Mine_Collect_Start_Res)
        }

        // @@protoc_insertion_point(class_scope:Mine_Collect_Start_Res)
    }

    // @@protoc_insertion_point(outer_class_scope)
}
