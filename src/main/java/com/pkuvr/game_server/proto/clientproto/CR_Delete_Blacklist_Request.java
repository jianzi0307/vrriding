// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: CR_Delete_Blacklist_Req.proto

package com.pkuvr.game_server.proto.clientproto;

public final class CR_Delete_Blacklist_Request {
    private static com.google.protobuf.Descriptors.Descriptor
            internal_static_CR_Delete_Blacklist_Req_descriptor;
    private static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
            internal_static_CR_Delete_Blacklist_Req_fieldAccessorTable;
    private static com.google.protobuf.Descriptors.FileDescriptor
            descriptor;

    static {
        String[] descriptorData = {
                "\n\035CR_Delete_Blacklist_Req.proto\")\n\027CR_De" +
                        "lete_Blacklist_Req\022\016\n\006roleId\030\001 \002(\005BD\n%co" +
                        "m.pkuvr.game_server.proto.clientprotoB\033CR_" +
                        "Delete_Blacklist_Request"
        };
        com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
                new com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner() {
                    public com.google.protobuf.ExtensionRegistry assignDescriptors(
                            com.google.protobuf.Descriptors.FileDescriptor root) {
                        descriptor = root;
                        internal_static_CR_Delete_Blacklist_Req_descriptor =
                                getDescriptor().getMessageTypes().get(0);
                        internal_static_CR_Delete_Blacklist_Req_fieldAccessorTable = new
                                com.google.protobuf.GeneratedMessage.FieldAccessorTable(
                                internal_static_CR_Delete_Blacklist_Req_descriptor,
                                new String[]{"RoleId",},
                                com.pkuvr.game_server.proto.clientproto.CR_Delete_Blacklist_Request.CR_Delete_Blacklist_Req.class,
                                com.pkuvr.game_server.proto.clientproto.CR_Delete_Blacklist_Request.CR_Delete_Blacklist_Req.Builder.class);
                        return null;
                    }
                };
        com.google.protobuf.Descriptors.FileDescriptor
                .internalBuildGeneratedFileFrom(descriptorData,
                        new com.google.protobuf.Descriptors.FileDescriptor[]{
                        }, assigner);
    }

    private CR_Delete_Blacklist_Request() {
    }

    public static void registerAllExtensions(
            com.google.protobuf.ExtensionRegistry registry) {
    }

    public static com.google.protobuf.Descriptors.FileDescriptor
    getDescriptor() {
        return descriptor;
    }

    public interface CR_Delete_Blacklist_ReqOrBuilder
            extends com.google.protobuf.MessageOrBuilder {

        // required int32 roleId = 1;
        boolean hasRoleId();

        int getRoleId();
    }

    public static final class CR_Delete_Blacklist_Req extends
            com.google.protobuf.GeneratedMessage
            implements CR_Delete_Blacklist_ReqOrBuilder {
        // required int32 roleId = 1;
        public static final int ROLEID_FIELD_NUMBER = 1;
        private static final CR_Delete_Blacklist_Req defaultInstance;
        private static final long serialVersionUID = 0L;

        static {
            defaultInstance = new CR_Delete_Blacklist_Req(true);
            defaultInstance.initFields();
        }

        private int bitField0_;
        private int roleId_;
        private byte memoizedIsInitialized = -1;
        private int memoizedSerializedSize = -1;
        // Use CR_Delete_Blacklist_Req.newBuilder() to construct.
        private CR_Delete_Blacklist_Req(Builder builder) {
            super(builder);
        }
        private CR_Delete_Blacklist_Req(boolean noInit) {
        }

        public static CR_Delete_Blacklist_Req getDefaultInstance() {
            return defaultInstance;
        }

        public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
            return com.pkuvr.game_server.proto.clientproto.CR_Delete_Blacklist_Request.internal_static_CR_Delete_Blacklist_Req_descriptor;
        }

        public static com.pkuvr.game_server.proto.clientproto.CR_Delete_Blacklist_Request.CR_Delete_Blacklist_Req parseFrom(
                com.google.protobuf.ByteString data)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return newBuilder().mergeFrom(data).buildParsed();
        }

        public static com.pkuvr.game_server.proto.clientproto.CR_Delete_Blacklist_Request.CR_Delete_Blacklist_Req parseFrom(
                com.google.protobuf.ByteString data,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return newBuilder().mergeFrom(data, extensionRegistry)
                    .buildParsed();
        }

        public static com.pkuvr.game_server.proto.clientproto.CR_Delete_Blacklist_Request.CR_Delete_Blacklist_Req parseFrom(byte[] data)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return newBuilder().mergeFrom(data).buildParsed();
        }

        public static com.pkuvr.game_server.proto.clientproto.CR_Delete_Blacklist_Request.CR_Delete_Blacklist_Req parseFrom(
                byte[] data,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return newBuilder().mergeFrom(data, extensionRegistry)
                    .buildParsed();
        }

        public static com.pkuvr.game_server.proto.clientproto.CR_Delete_Blacklist_Request.CR_Delete_Blacklist_Req parseFrom(java.io.InputStream input)
                throws java.io.IOException {
            return newBuilder().mergeFrom(input).buildParsed();
        }

        public static com.pkuvr.game_server.proto.clientproto.CR_Delete_Blacklist_Request.CR_Delete_Blacklist_Req parseFrom(
                java.io.InputStream input,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws java.io.IOException {
            return newBuilder().mergeFrom(input, extensionRegistry)
                    .buildParsed();
        }

        public static com.pkuvr.game_server.proto.clientproto.CR_Delete_Blacklist_Request.CR_Delete_Blacklist_Req parseDelimitedFrom(java.io.InputStream input)
                throws java.io.IOException {
            Builder builder = newBuilder();
            if (builder.mergeDelimitedFrom(input)) {
                return builder.buildParsed();
            } else {
                return null;
            }
        }

        public static com.pkuvr.game_server.proto.clientproto.CR_Delete_Blacklist_Request.CR_Delete_Blacklist_Req parseDelimitedFrom(
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

        public static com.pkuvr.game_server.proto.clientproto.CR_Delete_Blacklist_Request.CR_Delete_Blacklist_Req parseFrom(
                com.google.protobuf.CodedInputStream input)
                throws java.io.IOException {
            return newBuilder().mergeFrom(input).buildParsed();
        }

        public static com.pkuvr.game_server.proto.clientproto.CR_Delete_Blacklist_Request.CR_Delete_Blacklist_Req parseFrom(
                com.google.protobuf.CodedInputStream input,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws java.io.IOException {
            return newBuilder().mergeFrom(input, extensionRegistry)
                    .buildParsed();
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        public static Builder newBuilder(com.pkuvr.game_server.proto.clientproto.CR_Delete_Blacklist_Request.CR_Delete_Blacklist_Req prototype) {
            return newBuilder().mergeFrom(prototype);
        }

        public CR_Delete_Blacklist_Req getDefaultInstanceForType() {
            return defaultInstance;
        }

        protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
            return com.pkuvr.game_server.proto.clientproto.CR_Delete_Blacklist_Request.internal_static_CR_Delete_Blacklist_Req_fieldAccessorTable;
        }

        public boolean hasRoleId() {
            return ((bitField0_ & 0x00000001) == 0x00000001);
        }

        public int getRoleId() {
            return roleId_;
        }

        private void initFields() {
            roleId_ = 0;
        }

        public final boolean isInitialized() {
            byte isInitialized = memoizedIsInitialized;
            if (isInitialized != -1) return isInitialized == 1;

            if (!hasRoleId()) {
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
                output.writeInt32(1, roleId_);
            }
            getUnknownFields().writeTo(output);
        }

        public int getSerializedSize() {
            int size = memoizedSerializedSize;
            if (size != -1) return size;

            size = 0;
            if (((bitField0_ & 0x00000001) == 0x00000001)) {
                size += com.google.protobuf.CodedOutputStream
                        .computeInt32Size(1, roleId_);
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
                implements com.pkuvr.game_server.proto.clientproto.CR_Delete_Blacklist_Request.CR_Delete_Blacklist_ReqOrBuilder {
            private int bitField0_;
            // required int32 roleId = 1;
            private int roleId_;

            // Construct using com.pkuvr.game_server.proto.clientproto.CR_Delete_Blacklist_Request.CR_Delete_Blacklist_Req.newBuilder()
            private Builder() {
                maybeForceBuilderInitialization();
            }

            private Builder(BuilderParent parent) {
                super(parent);
                maybeForceBuilderInitialization();
            }

            public static final com.google.protobuf.Descriptors.Descriptor
            getDescriptor() {
                return com.pkuvr.game_server.proto.clientproto.CR_Delete_Blacklist_Request.internal_static_CR_Delete_Blacklist_Req_descriptor;
            }

            private static Builder create() {
                return new Builder();
            }

            protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
            internalGetFieldAccessorTable() {
                return com.pkuvr.game_server.proto.clientproto.CR_Delete_Blacklist_Request.internal_static_CR_Delete_Blacklist_Req_fieldAccessorTable;
            }

            private void maybeForceBuilderInitialization() {
                if (com.google.protobuf.GeneratedMessage.alwaysUseFieldBuilders) {
                }
            }

            public Builder clear() {
                super.clear();
                roleId_ = 0;
                bitField0_ = (bitField0_ & ~0x00000001);
                return this;
            }

            public Builder clone() {
                return create().mergeFrom(buildPartial());
            }

            public com.google.protobuf.Descriptors.Descriptor
            getDescriptorForType() {
                return com.pkuvr.game_server.proto.clientproto.CR_Delete_Blacklist_Request.CR_Delete_Blacklist_Req.getDescriptor();
            }

            public com.pkuvr.game_server.proto.clientproto.CR_Delete_Blacklist_Request.CR_Delete_Blacklist_Req getDefaultInstanceForType() {
                return com.pkuvr.game_server.proto.clientproto.CR_Delete_Blacklist_Request.CR_Delete_Blacklist_Req.getDefaultInstance();
            }

            public com.pkuvr.game_server.proto.clientproto.CR_Delete_Blacklist_Request.CR_Delete_Blacklist_Req build() {
                com.pkuvr.game_server.proto.clientproto.CR_Delete_Blacklist_Request.CR_Delete_Blacklist_Req result = buildPartial();
                if (!result.isInitialized()) {
                    throw newUninitializedMessageException(result);
                }
                return result;
            }

            private com.pkuvr.game_server.proto.clientproto.CR_Delete_Blacklist_Request.CR_Delete_Blacklist_Req buildParsed()
                    throws com.google.protobuf.InvalidProtocolBufferException {
                com.pkuvr.game_server.proto.clientproto.CR_Delete_Blacklist_Request.CR_Delete_Blacklist_Req result = buildPartial();
                if (!result.isInitialized()) {
                    throw newUninitializedMessageException(
                            result).asInvalidProtocolBufferException();
                }
                return result;
            }

            public com.pkuvr.game_server.proto.clientproto.CR_Delete_Blacklist_Request.CR_Delete_Blacklist_Req buildPartial() {
                com.pkuvr.game_server.proto.clientproto.CR_Delete_Blacklist_Request.CR_Delete_Blacklist_Req result = new com.pkuvr.game_server.proto.clientproto.CR_Delete_Blacklist_Request.CR_Delete_Blacklist_Req(this);
                int from_bitField0_ = bitField0_;
                int to_bitField0_ = 0;
                if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
                    to_bitField0_ |= 0x00000001;
                }
                result.roleId_ = roleId_;
                result.bitField0_ = to_bitField0_;
                onBuilt();
                return result;
            }

            public Builder mergeFrom(com.google.protobuf.Message other) {
                if (other instanceof com.pkuvr.game_server.proto.clientproto.CR_Delete_Blacklist_Request.CR_Delete_Blacklist_Req) {
                    return mergeFrom((com.pkuvr.game_server.proto.clientproto.CR_Delete_Blacklist_Request.CR_Delete_Blacklist_Req) other);
                } else {
                    super.mergeFrom(other);
                    return this;
                }
            }

            public Builder mergeFrom(com.pkuvr.game_server.proto.clientproto.CR_Delete_Blacklist_Request.CR_Delete_Blacklist_Req other) {
                if (other == com.pkuvr.game_server.proto.clientproto.CR_Delete_Blacklist_Request.CR_Delete_Blacklist_Req.getDefaultInstance())
                    return this;
                if (other.hasRoleId()) {
                    setRoleId(other.getRoleId());
                }
                this.mergeUnknownFields(other.getUnknownFields());
                return this;
            }

            public final boolean isInitialized() {
                if (!hasRoleId()) {

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
                            roleId_ = input.readInt32();
                            break;
                        }
                    }
                }
            }

            public boolean hasRoleId() {
                return ((bitField0_ & 0x00000001) == 0x00000001);
            }

            public int getRoleId() {
                return roleId_;
            }

            public Builder setRoleId(int value) {
                bitField0_ |= 0x00000001;
                roleId_ = value;
                onChanged();
                return this;
            }

            public Builder clearRoleId() {
                bitField0_ = (bitField0_ & ~0x00000001);
                roleId_ = 0;
                onChanged();
                return this;
            }

            // @@protoc_insertion_point(builder_scope:CR_Delete_Blacklist_Req)
        }

        // @@protoc_insertion_point(class_scope:CR_Delete_Blacklist_Req)
    }

    // @@protoc_insertion_point(outer_class_scope)
}
