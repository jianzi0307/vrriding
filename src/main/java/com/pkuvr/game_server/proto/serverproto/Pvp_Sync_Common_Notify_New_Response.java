// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: Pvp_Sync_Common_Notify_New_Res.proto

package com.pkuvr.game_server.proto.serverproto;

public final class Pvp_Sync_Common_Notify_New_Response {
    private static com.google.protobuf.Descriptors.Descriptor
            internal_static_Pvp_Sync_Common_Notify_New_Res_descriptor;
    private static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
            internal_static_Pvp_Sync_Common_Notify_New_Res_fieldAccessorTable;
    private static com.google.protobuf.Descriptors.FileDescriptor
            descriptor;

    static {
        String[] descriptorData = {
                "\n$Pvp_Sync_Common_Notify_New_Res.proto\032\037" +
                        "BI_Pvp_Sync_Common_Struct.proto\"I\n\036Pvp_S" +
                        "ync_Common_Notify_New_Res\022\'\n\006common\030\001 \001(" +
                        "\0132\027.Pvp_Sync_Common_StructBL\n%com.gfan.g" +
                        "ameserver.proto.serverprotoB#Pvp_Sync_Co" +
                        "mmon_Notify_New_Response"
        };
        com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
                new com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner() {
                    public com.google.protobuf.ExtensionRegistry assignDescriptors(
                            com.google.protobuf.Descriptors.FileDescriptor root) {
                        descriptor = root;
                        internal_static_Pvp_Sync_Common_Notify_New_Res_descriptor =
                                getDescriptor().getMessageTypes().get(0);
                        internal_static_Pvp_Sync_Common_Notify_New_Res_fieldAccessorTable = new
                                com.google.protobuf.GeneratedMessage.FieldAccessorTable(
                                internal_static_Pvp_Sync_Common_Notify_New_Res_descriptor,
                                new String[]{"Common",},
                                com.pkuvr.game_server.proto.serverproto.Pvp_Sync_Common_Notify_New_Response.Pvp_Sync_Common_Notify_New_Res.class,
                                com.pkuvr.game_server.proto.serverproto.Pvp_Sync_Common_Notify_New_Response.Pvp_Sync_Common_Notify_New_Res.Builder.class);
                        return null;
                    }
                };
        com.google.protobuf.Descriptors.FileDescriptor
                .internalBuildGeneratedFileFrom(descriptorData,
                        new com.google.protobuf.Descriptors.FileDescriptor[]{
                                com.pkuvr.game_server.proto.commons.BI_Pvp_Sync_Common_Struct.getDescriptor(),
                        }, assigner);
    }

    private Pvp_Sync_Common_Notify_New_Response() {
    }

    public static void registerAllExtensions(
            com.google.protobuf.ExtensionRegistry registry) {
    }

    public static com.google.protobuf.Descriptors.FileDescriptor
    getDescriptor() {
        return descriptor;
    }

    public interface Pvp_Sync_Common_Notify_New_ResOrBuilder
            extends com.google.protobuf.MessageOrBuilder {

        // optional .Pvp_Sync_Common_Struct common = 1;
        boolean hasCommon();

        com.pkuvr.game_server.proto.commons.BI_Pvp_Sync_Common_Struct.Pvp_Sync_Common_Struct getCommon();

        com.pkuvr.game_server.proto.commons.BI_Pvp_Sync_Common_Struct.Pvp_Sync_Common_StructOrBuilder getCommonOrBuilder();
    }

    public static final class Pvp_Sync_Common_Notify_New_Res extends
            com.google.protobuf.GeneratedMessage
            implements Pvp_Sync_Common_Notify_New_ResOrBuilder {
        // optional .Pvp_Sync_Common_Struct common = 1;
        public static final int COMMON_FIELD_NUMBER = 1;
        private static final Pvp_Sync_Common_Notify_New_Res defaultInstance;
        private static final long serialVersionUID = 0L;

        static {
            defaultInstance = new Pvp_Sync_Common_Notify_New_Res(true);
            defaultInstance.initFields();
        }

        private int bitField0_;
        private com.pkuvr.game_server.proto.commons.BI_Pvp_Sync_Common_Struct.Pvp_Sync_Common_Struct common_;
        private byte memoizedIsInitialized = -1;
        private int memoizedSerializedSize = -1;
        // Use Pvp_Sync_Common_Notify_New_Res.newBuilder() to construct.
        private Pvp_Sync_Common_Notify_New_Res(Builder builder) {
            super(builder);
        }
        private Pvp_Sync_Common_Notify_New_Res(boolean noInit) {
        }

        public static Pvp_Sync_Common_Notify_New_Res getDefaultInstance() {
            return defaultInstance;
        }

        public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
            return com.pkuvr.game_server.proto.serverproto.Pvp_Sync_Common_Notify_New_Response.internal_static_Pvp_Sync_Common_Notify_New_Res_descriptor;
        }

        public static com.pkuvr.game_server.proto.serverproto.Pvp_Sync_Common_Notify_New_Response.Pvp_Sync_Common_Notify_New_Res parseFrom(
                com.google.protobuf.ByteString data)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return newBuilder().mergeFrom(data).buildParsed();
        }

        public static com.pkuvr.game_server.proto.serverproto.Pvp_Sync_Common_Notify_New_Response.Pvp_Sync_Common_Notify_New_Res parseFrom(
                com.google.protobuf.ByteString data,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return newBuilder().mergeFrom(data, extensionRegistry)
                    .buildParsed();
        }

        public static com.pkuvr.game_server.proto.serverproto.Pvp_Sync_Common_Notify_New_Response.Pvp_Sync_Common_Notify_New_Res parseFrom(byte[] data)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return newBuilder().mergeFrom(data).buildParsed();
        }

        public static com.pkuvr.game_server.proto.serverproto.Pvp_Sync_Common_Notify_New_Response.Pvp_Sync_Common_Notify_New_Res parseFrom(
                byte[] data,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return newBuilder().mergeFrom(data, extensionRegistry)
                    .buildParsed();
        }

        public static com.pkuvr.game_server.proto.serverproto.Pvp_Sync_Common_Notify_New_Response.Pvp_Sync_Common_Notify_New_Res parseFrom(java.io.InputStream input)
                throws java.io.IOException {
            return newBuilder().mergeFrom(input).buildParsed();
        }

        public static com.pkuvr.game_server.proto.serverproto.Pvp_Sync_Common_Notify_New_Response.Pvp_Sync_Common_Notify_New_Res parseFrom(
                java.io.InputStream input,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws java.io.IOException {
            return newBuilder().mergeFrom(input, extensionRegistry)
                    .buildParsed();
        }

        public static com.pkuvr.game_server.proto.serverproto.Pvp_Sync_Common_Notify_New_Response.Pvp_Sync_Common_Notify_New_Res parseDelimitedFrom(java.io.InputStream input)
                throws java.io.IOException {
            Builder builder = newBuilder();
            if (builder.mergeDelimitedFrom(input)) {
                return builder.buildParsed();
            } else {
                return null;
            }
        }

        public static com.pkuvr.game_server.proto.serverproto.Pvp_Sync_Common_Notify_New_Response.Pvp_Sync_Common_Notify_New_Res parseDelimitedFrom(
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

        public static com.pkuvr.game_server.proto.serverproto.Pvp_Sync_Common_Notify_New_Response.Pvp_Sync_Common_Notify_New_Res parseFrom(
                com.google.protobuf.CodedInputStream input)
                throws java.io.IOException {
            return newBuilder().mergeFrom(input).buildParsed();
        }

        public static com.pkuvr.game_server.proto.serverproto.Pvp_Sync_Common_Notify_New_Response.Pvp_Sync_Common_Notify_New_Res parseFrom(
                com.google.protobuf.CodedInputStream input,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws java.io.IOException {
            return newBuilder().mergeFrom(input, extensionRegistry)
                    .buildParsed();
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        public static Builder newBuilder(com.pkuvr.game_server.proto.serverproto.Pvp_Sync_Common_Notify_New_Response.Pvp_Sync_Common_Notify_New_Res prototype) {
            return newBuilder().mergeFrom(prototype);
        }

        public Pvp_Sync_Common_Notify_New_Res getDefaultInstanceForType() {
            return defaultInstance;
        }

        protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
            return com.pkuvr.game_server.proto.serverproto.Pvp_Sync_Common_Notify_New_Response.internal_static_Pvp_Sync_Common_Notify_New_Res_fieldAccessorTable;
        }

        public boolean hasCommon() {
            return ((bitField0_ & 0x00000001) == 0x00000001);
        }

        public com.pkuvr.game_server.proto.commons.BI_Pvp_Sync_Common_Struct.Pvp_Sync_Common_Struct getCommon() {
            return common_;
        }

        public com.pkuvr.game_server.proto.commons.BI_Pvp_Sync_Common_Struct.Pvp_Sync_Common_StructOrBuilder getCommonOrBuilder() {
            return common_;
        }

        private void initFields() {
            common_ = com.pkuvr.game_server.proto.commons.BI_Pvp_Sync_Common_Struct.Pvp_Sync_Common_Struct.getDefaultInstance();
        }

        public final boolean isInitialized() {
            byte isInitialized = memoizedIsInitialized;
            if (isInitialized != -1) return isInitialized == 1;

            if (hasCommon()) {
                if (!getCommon().isInitialized()) {
                    memoizedIsInitialized = 0;
                    return false;
                }
            }
            memoizedIsInitialized = 1;
            return true;
        }

        public void writeTo(com.google.protobuf.CodedOutputStream output)
                throws java.io.IOException {
            getSerializedSize();
            if (((bitField0_ & 0x00000001) == 0x00000001)) {
                output.writeMessage(1, common_);
            }
            getUnknownFields().writeTo(output);
        }

        public int getSerializedSize() {
            int size = memoizedSerializedSize;
            if (size != -1) return size;

            size = 0;
            if (((bitField0_ & 0x00000001) == 0x00000001)) {
                size += com.google.protobuf.CodedOutputStream
                        .computeMessageSize(1, common_);
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
                implements com.pkuvr.game_server.proto.serverproto.Pvp_Sync_Common_Notify_New_Response.Pvp_Sync_Common_Notify_New_ResOrBuilder {
            private int bitField0_;
            // optional .Pvp_Sync_Common_Struct common = 1;
            private com.pkuvr.game_server.proto.commons.BI_Pvp_Sync_Common_Struct.Pvp_Sync_Common_Struct common_ = com.pkuvr.game_server.proto.commons.BI_Pvp_Sync_Common_Struct.Pvp_Sync_Common_Struct.getDefaultInstance();
            private com.google.protobuf.SingleFieldBuilder<
                    com.pkuvr.game_server.proto.commons.BI_Pvp_Sync_Common_Struct.Pvp_Sync_Common_Struct, com.pkuvr.game_server.proto.commons.BI_Pvp_Sync_Common_Struct.Pvp_Sync_Common_Struct.Builder, com.pkuvr.game_server.proto.commons.BI_Pvp_Sync_Common_Struct.Pvp_Sync_Common_StructOrBuilder> commonBuilder_;

            // Construct using com.pkuvr.game_server.proto.serverproto.Pvp_Sync_Common_Notify_New_Response.Pvp_Sync_Common_Notify_New_Res.newBuilder()
            private Builder() {
                maybeForceBuilderInitialization();
            }

            private Builder(BuilderParent parent) {
                super(parent);
                maybeForceBuilderInitialization();
            }

            public static final com.google.protobuf.Descriptors.Descriptor
            getDescriptor() {
                return com.pkuvr.game_server.proto.serverproto.Pvp_Sync_Common_Notify_New_Response.internal_static_Pvp_Sync_Common_Notify_New_Res_descriptor;
            }

            private static Builder create() {
                return new Builder();
            }

            protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
            internalGetFieldAccessorTable() {
                return com.pkuvr.game_server.proto.serverproto.Pvp_Sync_Common_Notify_New_Response.internal_static_Pvp_Sync_Common_Notify_New_Res_fieldAccessorTable;
            }

            private void maybeForceBuilderInitialization() {
                if (com.google.protobuf.GeneratedMessage.alwaysUseFieldBuilders) {
                    getCommonFieldBuilder();
                }
            }

            public Builder clear() {
                super.clear();
                if (commonBuilder_ == null) {
                    common_ = com.pkuvr.game_server.proto.commons.BI_Pvp_Sync_Common_Struct.Pvp_Sync_Common_Struct.getDefaultInstance();
                } else {
                    commonBuilder_.clear();
                }
                bitField0_ = (bitField0_ & ~0x00000001);
                return this;
            }

            public Builder clone() {
                return create().mergeFrom(buildPartial());
            }

            public com.google.protobuf.Descriptors.Descriptor
            getDescriptorForType() {
                return com.pkuvr.game_server.proto.serverproto.Pvp_Sync_Common_Notify_New_Response.Pvp_Sync_Common_Notify_New_Res.getDescriptor();
            }

            public com.pkuvr.game_server.proto.serverproto.Pvp_Sync_Common_Notify_New_Response.Pvp_Sync_Common_Notify_New_Res getDefaultInstanceForType() {
                return com.pkuvr.game_server.proto.serverproto.Pvp_Sync_Common_Notify_New_Response.Pvp_Sync_Common_Notify_New_Res.getDefaultInstance();
            }

            public com.pkuvr.game_server.proto.serverproto.Pvp_Sync_Common_Notify_New_Response.Pvp_Sync_Common_Notify_New_Res build() {
                com.pkuvr.game_server.proto.serverproto.Pvp_Sync_Common_Notify_New_Response.Pvp_Sync_Common_Notify_New_Res result = buildPartial();
                if (!result.isInitialized()) {
                    throw newUninitializedMessageException(result);
                }
                return result;
            }

            private com.pkuvr.game_server.proto.serverproto.Pvp_Sync_Common_Notify_New_Response.Pvp_Sync_Common_Notify_New_Res buildParsed()
                    throws com.google.protobuf.InvalidProtocolBufferException {
                com.pkuvr.game_server.proto.serverproto.Pvp_Sync_Common_Notify_New_Response.Pvp_Sync_Common_Notify_New_Res result = buildPartial();
                if (!result.isInitialized()) {
                    throw newUninitializedMessageException(
                            result).asInvalidProtocolBufferException();
                }
                return result;
            }

            public com.pkuvr.game_server.proto.serverproto.Pvp_Sync_Common_Notify_New_Response.Pvp_Sync_Common_Notify_New_Res buildPartial() {
                com.pkuvr.game_server.proto.serverproto.Pvp_Sync_Common_Notify_New_Response.Pvp_Sync_Common_Notify_New_Res result = new com.pkuvr.game_server.proto.serverproto.Pvp_Sync_Common_Notify_New_Response.Pvp_Sync_Common_Notify_New_Res(this);
                int from_bitField0_ = bitField0_;
                int to_bitField0_ = 0;
                if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
                    to_bitField0_ |= 0x00000001;
                }
                if (commonBuilder_ == null) {
                    result.common_ = common_;
                } else {
                    result.common_ = commonBuilder_.build();
                }
                result.bitField0_ = to_bitField0_;
                onBuilt();
                return result;
            }

            public Builder mergeFrom(com.google.protobuf.Message other) {
                if (other instanceof com.pkuvr.game_server.proto.serverproto.Pvp_Sync_Common_Notify_New_Response.Pvp_Sync_Common_Notify_New_Res) {
                    return mergeFrom((com.pkuvr.game_server.proto.serverproto.Pvp_Sync_Common_Notify_New_Response.Pvp_Sync_Common_Notify_New_Res) other);
                } else {
                    super.mergeFrom(other);
                    return this;
                }
            }

            public Builder mergeFrom(com.pkuvr.game_server.proto.serverproto.Pvp_Sync_Common_Notify_New_Response.Pvp_Sync_Common_Notify_New_Res other) {
                if (other == com.pkuvr.game_server.proto.serverproto.Pvp_Sync_Common_Notify_New_Response.Pvp_Sync_Common_Notify_New_Res.getDefaultInstance())
                    return this;
                if (other.hasCommon()) {
                    mergeCommon(other.getCommon());
                }
                this.mergeUnknownFields(other.getUnknownFields());
                return this;
            }

            public final boolean isInitialized() {
                if (hasCommon()) {
                    if (!getCommon().isInitialized()) {

                        return false;
                    }
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
                        case 10: {
                            com.pkuvr.game_server.proto.commons.BI_Pvp_Sync_Common_Struct.Pvp_Sync_Common_Struct.Builder subBuilder = com.pkuvr.game_server.proto.commons.BI_Pvp_Sync_Common_Struct.Pvp_Sync_Common_Struct.newBuilder();
                            if (hasCommon()) {
                                subBuilder.mergeFrom(getCommon());
                            }
                            input.readMessage(subBuilder, extensionRegistry);
                            setCommon(subBuilder.buildPartial());
                            break;
                        }
                    }
                }
            }

            public boolean hasCommon() {
                return ((bitField0_ & 0x00000001) == 0x00000001);
            }

            public com.pkuvr.game_server.proto.commons.BI_Pvp_Sync_Common_Struct.Pvp_Sync_Common_Struct getCommon() {
                if (commonBuilder_ == null) {
                    return common_;
                } else {
                    return commonBuilder_.getMessage();
                }
            }

            public Builder setCommon(
                    com.pkuvr.game_server.proto.commons.BI_Pvp_Sync_Common_Struct.Pvp_Sync_Common_Struct.Builder builderForValue) {
                if (commonBuilder_ == null) {
                    common_ = builderForValue.build();
                    onChanged();
                } else {
                    commonBuilder_.setMessage(builderForValue.build());
                }
                bitField0_ |= 0x00000001;
                return this;
            }

            public Builder setCommon(com.pkuvr.game_server.proto.commons.BI_Pvp_Sync_Common_Struct.Pvp_Sync_Common_Struct value) {
                if (commonBuilder_ == null) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    common_ = value;
                    onChanged();
                } else {
                    commonBuilder_.setMessage(value);
                }
                bitField0_ |= 0x00000001;
                return this;
            }

            public Builder mergeCommon(com.pkuvr.game_server.proto.commons.BI_Pvp_Sync_Common_Struct.Pvp_Sync_Common_Struct value) {
                if (commonBuilder_ == null) {
                    if (((bitField0_ & 0x00000001) == 0x00000001) &&
                            common_ != com.pkuvr.game_server.proto.commons.BI_Pvp_Sync_Common_Struct.Pvp_Sync_Common_Struct.getDefaultInstance()) {
                        common_ =
                                com.pkuvr.game_server.proto.commons.BI_Pvp_Sync_Common_Struct.Pvp_Sync_Common_Struct.newBuilder(common_).mergeFrom(value).buildPartial();
                    } else {
                        common_ = value;
                    }
                    onChanged();
                } else {
                    commonBuilder_.mergeFrom(value);
                }
                bitField0_ |= 0x00000001;
                return this;
            }

            public Builder clearCommon() {
                if (commonBuilder_ == null) {
                    common_ = com.pkuvr.game_server.proto.commons.BI_Pvp_Sync_Common_Struct.Pvp_Sync_Common_Struct.getDefaultInstance();
                    onChanged();
                } else {
                    commonBuilder_.clear();
                }
                bitField0_ = (bitField0_ & ~0x00000001);
                return this;
            }

            public com.pkuvr.game_server.proto.commons.BI_Pvp_Sync_Common_Struct.Pvp_Sync_Common_Struct.Builder getCommonBuilder() {
                bitField0_ |= 0x00000001;
                onChanged();
                return getCommonFieldBuilder().getBuilder();
            }

            public com.pkuvr.game_server.proto.commons.BI_Pvp_Sync_Common_Struct.Pvp_Sync_Common_StructOrBuilder getCommonOrBuilder() {
                if (commonBuilder_ != null) {
                    return commonBuilder_.getMessageOrBuilder();
                } else {
                    return common_;
                }
            }

            private com.google.protobuf.SingleFieldBuilder<
                    com.pkuvr.game_server.proto.commons.BI_Pvp_Sync_Common_Struct.Pvp_Sync_Common_Struct, com.pkuvr.game_server.proto.commons.BI_Pvp_Sync_Common_Struct.Pvp_Sync_Common_Struct.Builder, com.pkuvr.game_server.proto.commons.BI_Pvp_Sync_Common_Struct.Pvp_Sync_Common_StructOrBuilder>
            getCommonFieldBuilder() {
                if (commonBuilder_ == null) {
                    commonBuilder_ = new com.google.protobuf.SingleFieldBuilder<
                            com.pkuvr.game_server.proto.commons.BI_Pvp_Sync_Common_Struct.Pvp_Sync_Common_Struct, com.pkuvr.game_server.proto.commons.BI_Pvp_Sync_Common_Struct.Pvp_Sync_Common_Struct.Builder, com.pkuvr.game_server.proto.commons.BI_Pvp_Sync_Common_Struct.Pvp_Sync_Common_StructOrBuilder>(
                            common_,
                            getParentForChildren(),
                            isClean());
                    common_ = null;
                }
                return commonBuilder_;
            }

            // @@protoc_insertion_point(builder_scope:Pvp_Sync_Common_Notify_New_Res)
        }

        // @@protoc_insertion_point(class_scope:Pvp_Sync_Common_Notify_New_Res)
    }

    // @@protoc_insertion_point(outer_class_scope)
}
