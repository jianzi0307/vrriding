// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: Pvp_Player_Exit_Req.proto

package com.pkuvr.game_server.proto.clientproto;

public final class Pvp_Player_Exit_Request {
    private static com.google.protobuf.Descriptors.Descriptor
            internal_static_Pvp_Player_Exit_Req_descriptor;
    private static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
            internal_static_Pvp_Player_Exit_Req_fieldAccessorTable;
    private static com.google.protobuf.Descriptors.FileDescriptor
            descriptor;

    static {
        String[] descriptorData = {
                "\n\031Pvp_Player_Exit_Req.proto\032\032BI_Battle_U" +
                        "nit_Start.proto\"D\n\023Pvp_Player_Exit_Req\022-" +
                        "\n\rownDamageList\030\001 \003(\0132\026.Battle_Unit_Star" +
                        "t_MesB@\n%com.pkuvr.game_server.proto.clien" +
                        "tprotoB\027Pvp_Player_Exit_Request"
        };
        com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
                new com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner() {
                    public com.google.protobuf.ExtensionRegistry assignDescriptors(
                            com.google.protobuf.Descriptors.FileDescriptor root) {
                        descriptor = root;
                        internal_static_Pvp_Player_Exit_Req_descriptor =
                                getDescriptor().getMessageTypes().get(0);
                        internal_static_Pvp_Player_Exit_Req_fieldAccessorTable = new
                                com.google.protobuf.GeneratedMessage.FieldAccessorTable(
                                internal_static_Pvp_Player_Exit_Req_descriptor,
                                new String[]{"OwnDamageList",},
                                com.pkuvr.game_server.proto.clientproto.Pvp_Player_Exit_Request.Pvp_Player_Exit_Req.class,
                                com.pkuvr.game_server.proto.clientproto.Pvp_Player_Exit_Request.Pvp_Player_Exit_Req.Builder.class);
                        return null;
                    }
                };
        com.google.protobuf.Descriptors.FileDescriptor
                .internalBuildGeneratedFileFrom(descriptorData,
                        new com.google.protobuf.Descriptors.FileDescriptor[]{
                                com.pkuvr.game_server.proto.commons.BI_Battle_Unit_Start.getDescriptor(),
                        }, assigner);
    }

    private Pvp_Player_Exit_Request() {
    }

    public static void registerAllExtensions(
            com.google.protobuf.ExtensionRegistry registry) {
    }

    public static com.google.protobuf.Descriptors.FileDescriptor
    getDescriptor() {
        return descriptor;
    }

    public interface Pvp_Player_Exit_ReqOrBuilder
            extends com.google.protobuf.MessageOrBuilder {

        // repeated .Battle_Unit_Start_Mes ownDamageList = 1;
        java.util.List<com.pkuvr.game_server.proto.commons.BI_Battle_Unit_Start.Battle_Unit_Start_Mes>
        getOwnDamageListList();

        com.pkuvr.game_server.proto.commons.BI_Battle_Unit_Start.Battle_Unit_Start_Mes getOwnDamageList(int index);

        int getOwnDamageListCount();

        java.util.List<? extends com.pkuvr.game_server.proto.commons.BI_Battle_Unit_Start.Battle_Unit_Start_MesOrBuilder>
        getOwnDamageListOrBuilderList();

        com.pkuvr.game_server.proto.commons.BI_Battle_Unit_Start.Battle_Unit_Start_MesOrBuilder getOwnDamageListOrBuilder(
                int index);
    }

    public static final class Pvp_Player_Exit_Req extends
            com.google.protobuf.GeneratedMessage
            implements Pvp_Player_Exit_ReqOrBuilder {
        // repeated .Battle_Unit_Start_Mes ownDamageList = 1;
        public static final int OWNDAMAGELIST_FIELD_NUMBER = 1;
        private static final Pvp_Player_Exit_Req defaultInstance;
        private static final long serialVersionUID = 0L;

        static {
            defaultInstance = new Pvp_Player_Exit_Req(true);
            defaultInstance.initFields();
        }

        private java.util.List<com.pkuvr.game_server.proto.commons.BI_Battle_Unit_Start.Battle_Unit_Start_Mes> ownDamageList_;
        private byte memoizedIsInitialized = -1;
        private int memoizedSerializedSize = -1;

        // Use Pvp_Player_Exit_Req.newBuilder() to construct.
        private Pvp_Player_Exit_Req(Builder builder) {
            super(builder);
        }
        private Pvp_Player_Exit_Req(boolean noInit) {
        }

        public static Pvp_Player_Exit_Req getDefaultInstance() {
            return defaultInstance;
        }

        public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
            return com.pkuvr.game_server.proto.clientproto.Pvp_Player_Exit_Request.internal_static_Pvp_Player_Exit_Req_descriptor;
        }

        public static com.pkuvr.game_server.proto.clientproto.Pvp_Player_Exit_Request.Pvp_Player_Exit_Req parseFrom(
                com.google.protobuf.ByteString data)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return newBuilder().mergeFrom(data).buildParsed();
        }

        public static com.pkuvr.game_server.proto.clientproto.Pvp_Player_Exit_Request.Pvp_Player_Exit_Req parseFrom(
                com.google.protobuf.ByteString data,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return newBuilder().mergeFrom(data, extensionRegistry)
                    .buildParsed();
        }

        public static com.pkuvr.game_server.proto.clientproto.Pvp_Player_Exit_Request.Pvp_Player_Exit_Req parseFrom(byte[] data)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return newBuilder().mergeFrom(data).buildParsed();
        }

        public static com.pkuvr.game_server.proto.clientproto.Pvp_Player_Exit_Request.Pvp_Player_Exit_Req parseFrom(
                byte[] data,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return newBuilder().mergeFrom(data, extensionRegistry)
                    .buildParsed();
        }

        public static com.pkuvr.game_server.proto.clientproto.Pvp_Player_Exit_Request.Pvp_Player_Exit_Req parseFrom(java.io.InputStream input)
                throws java.io.IOException {
            return newBuilder().mergeFrom(input).buildParsed();
        }

        public static com.pkuvr.game_server.proto.clientproto.Pvp_Player_Exit_Request.Pvp_Player_Exit_Req parseFrom(
                java.io.InputStream input,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws java.io.IOException {
            return newBuilder().mergeFrom(input, extensionRegistry)
                    .buildParsed();
        }

        public static com.pkuvr.game_server.proto.clientproto.Pvp_Player_Exit_Request.Pvp_Player_Exit_Req parseDelimitedFrom(java.io.InputStream input)
                throws java.io.IOException {
            Builder builder = newBuilder();
            if (builder.mergeDelimitedFrom(input)) {
                return builder.buildParsed();
            } else {
                return null;
            }
        }

        public static com.pkuvr.game_server.proto.clientproto.Pvp_Player_Exit_Request.Pvp_Player_Exit_Req parseDelimitedFrom(
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

        public static com.pkuvr.game_server.proto.clientproto.Pvp_Player_Exit_Request.Pvp_Player_Exit_Req parseFrom(
                com.google.protobuf.CodedInputStream input)
                throws java.io.IOException {
            return newBuilder().mergeFrom(input).buildParsed();
        }

        public static com.pkuvr.game_server.proto.clientproto.Pvp_Player_Exit_Request.Pvp_Player_Exit_Req parseFrom(
                com.google.protobuf.CodedInputStream input,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws java.io.IOException {
            return newBuilder().mergeFrom(input, extensionRegistry)
                    .buildParsed();
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        public static Builder newBuilder(com.pkuvr.game_server.proto.clientproto.Pvp_Player_Exit_Request.Pvp_Player_Exit_Req prototype) {
            return newBuilder().mergeFrom(prototype);
        }

        public Pvp_Player_Exit_Req getDefaultInstanceForType() {
            return defaultInstance;
        }

        protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
            return com.pkuvr.game_server.proto.clientproto.Pvp_Player_Exit_Request.internal_static_Pvp_Player_Exit_Req_fieldAccessorTable;
        }

        public java.util.List<com.pkuvr.game_server.proto.commons.BI_Battle_Unit_Start.Battle_Unit_Start_Mes> getOwnDamageListList() {
            return ownDamageList_;
        }

        public java.util.List<? extends com.pkuvr.game_server.proto.commons.BI_Battle_Unit_Start.Battle_Unit_Start_MesOrBuilder>
        getOwnDamageListOrBuilderList() {
            return ownDamageList_;
        }

        public int getOwnDamageListCount() {
            return ownDamageList_.size();
        }

        public com.pkuvr.game_server.proto.commons.BI_Battle_Unit_Start.Battle_Unit_Start_Mes getOwnDamageList(int index) {
            return ownDamageList_.get(index);
        }

        public com.pkuvr.game_server.proto.commons.BI_Battle_Unit_Start.Battle_Unit_Start_MesOrBuilder getOwnDamageListOrBuilder(
                int index) {
            return ownDamageList_.get(index);
        }

        private void initFields() {
            ownDamageList_ = java.util.Collections.emptyList();
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
            for (int i = 0; i < ownDamageList_.size(); i++) {
                output.writeMessage(1, ownDamageList_.get(i));
            }
            getUnknownFields().writeTo(output);
        }

        public int getSerializedSize() {
            int size = memoizedSerializedSize;
            if (size != -1) return size;

            size = 0;
            for (int i = 0; i < ownDamageList_.size(); i++) {
                size += com.google.protobuf.CodedOutputStream
                        .computeMessageSize(1, ownDamageList_.get(i));
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
                implements com.pkuvr.game_server.proto.clientproto.Pvp_Player_Exit_Request.Pvp_Player_Exit_ReqOrBuilder {
            private int bitField0_;
            // repeated .Battle_Unit_Start_Mes ownDamageList = 1;
            private java.util.List<com.pkuvr.game_server.proto.commons.BI_Battle_Unit_Start.Battle_Unit_Start_Mes> ownDamageList_ =
                    java.util.Collections.emptyList();
            private com.google.protobuf.RepeatedFieldBuilder<
                    com.pkuvr.game_server.proto.commons.BI_Battle_Unit_Start.Battle_Unit_Start_Mes, com.pkuvr.game_server.proto.commons.BI_Battle_Unit_Start.Battle_Unit_Start_Mes.Builder, com.pkuvr.game_server.proto.commons.BI_Battle_Unit_Start.Battle_Unit_Start_MesOrBuilder> ownDamageListBuilder_;

            // Construct using com.pkuvr.game_server.proto.clientproto.Pvp_Player_Exit_Request.Pvp_Player_Exit_Req.newBuilder()
            private Builder() {
                maybeForceBuilderInitialization();
            }

            private Builder(BuilderParent parent) {
                super(parent);
                maybeForceBuilderInitialization();
            }

            public static final com.google.protobuf.Descriptors.Descriptor
            getDescriptor() {
                return com.pkuvr.game_server.proto.clientproto.Pvp_Player_Exit_Request.internal_static_Pvp_Player_Exit_Req_descriptor;
            }

            private static Builder create() {
                return new Builder();
            }

            protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
            internalGetFieldAccessorTable() {
                return com.pkuvr.game_server.proto.clientproto.Pvp_Player_Exit_Request.internal_static_Pvp_Player_Exit_Req_fieldAccessorTable;
            }

            private void maybeForceBuilderInitialization() {
                if (com.google.protobuf.GeneratedMessage.alwaysUseFieldBuilders) {
                    getOwnDamageListFieldBuilder();
                }
            }

            public Builder clear() {
                super.clear();
                if (ownDamageListBuilder_ == null) {
                    ownDamageList_ = java.util.Collections.emptyList();
                    bitField0_ = (bitField0_ & ~0x00000001);
                } else {
                    ownDamageListBuilder_.clear();
                }
                return this;
            }

            public Builder clone() {
                return create().mergeFrom(buildPartial());
            }

            public com.google.protobuf.Descriptors.Descriptor
            getDescriptorForType() {
                return com.pkuvr.game_server.proto.clientproto.Pvp_Player_Exit_Request.Pvp_Player_Exit_Req.getDescriptor();
            }

            public com.pkuvr.game_server.proto.clientproto.Pvp_Player_Exit_Request.Pvp_Player_Exit_Req getDefaultInstanceForType() {
                return com.pkuvr.game_server.proto.clientproto.Pvp_Player_Exit_Request.Pvp_Player_Exit_Req.getDefaultInstance();
            }

            public com.pkuvr.game_server.proto.clientproto.Pvp_Player_Exit_Request.Pvp_Player_Exit_Req build() {
                com.pkuvr.game_server.proto.clientproto.Pvp_Player_Exit_Request.Pvp_Player_Exit_Req result = buildPartial();
                if (!result.isInitialized()) {
                    throw newUninitializedMessageException(result);
                }
                return result;
            }

            private com.pkuvr.game_server.proto.clientproto.Pvp_Player_Exit_Request.Pvp_Player_Exit_Req buildParsed()
                    throws com.google.protobuf.InvalidProtocolBufferException {
                com.pkuvr.game_server.proto.clientproto.Pvp_Player_Exit_Request.Pvp_Player_Exit_Req result = buildPartial();
                if (!result.isInitialized()) {
                    throw newUninitializedMessageException(
                            result).asInvalidProtocolBufferException();
                }
                return result;
            }

            public com.pkuvr.game_server.proto.clientproto.Pvp_Player_Exit_Request.Pvp_Player_Exit_Req buildPartial() {
                com.pkuvr.game_server.proto.clientproto.Pvp_Player_Exit_Request.Pvp_Player_Exit_Req result = new com.pkuvr.game_server.proto.clientproto.Pvp_Player_Exit_Request.Pvp_Player_Exit_Req(this);
                int from_bitField0_ = bitField0_;
                if (ownDamageListBuilder_ == null) {
                    if (((bitField0_ & 0x00000001) == 0x00000001)) {
                        ownDamageList_ = java.util.Collections.unmodifiableList(ownDamageList_);
                        bitField0_ = (bitField0_ & ~0x00000001);
                    }
                    result.ownDamageList_ = ownDamageList_;
                } else {
                    result.ownDamageList_ = ownDamageListBuilder_.build();
                }
                onBuilt();
                return result;
            }

            public Builder mergeFrom(com.google.protobuf.Message other) {
                if (other instanceof com.pkuvr.game_server.proto.clientproto.Pvp_Player_Exit_Request.Pvp_Player_Exit_Req) {
                    return mergeFrom((com.pkuvr.game_server.proto.clientproto.Pvp_Player_Exit_Request.Pvp_Player_Exit_Req) other);
                } else {
                    super.mergeFrom(other);
                    return this;
                }
            }

            public Builder mergeFrom(com.pkuvr.game_server.proto.clientproto.Pvp_Player_Exit_Request.Pvp_Player_Exit_Req other) {
                if (other == com.pkuvr.game_server.proto.clientproto.Pvp_Player_Exit_Request.Pvp_Player_Exit_Req.getDefaultInstance())
                    return this;
                if (ownDamageListBuilder_ == null) {
                    if (!other.ownDamageList_.isEmpty()) {
                        if (ownDamageList_.isEmpty()) {
                            ownDamageList_ = other.ownDamageList_;
                            bitField0_ = (bitField0_ & ~0x00000001);
                        } else {
                            ensureOwnDamageListIsMutable();
                            ownDamageList_.addAll(other.ownDamageList_);
                        }
                        onChanged();
                    }
                } else {
                    if (!other.ownDamageList_.isEmpty()) {
                        if (ownDamageListBuilder_.isEmpty()) {
                            ownDamageListBuilder_.dispose();
                            ownDamageListBuilder_ = null;
                            ownDamageList_ = other.ownDamageList_;
                            bitField0_ = (bitField0_ & ~0x00000001);
                            ownDamageListBuilder_ =
                                    com.google.protobuf.GeneratedMessage.alwaysUseFieldBuilders ?
                                            getOwnDamageListFieldBuilder() : null;
                        } else {
                            ownDamageListBuilder_.addAllMessages(other.ownDamageList_);
                        }
                    }
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
                        case 10: {
                            com.pkuvr.game_server.proto.commons.BI_Battle_Unit_Start.Battle_Unit_Start_Mes.Builder subBuilder = com.pkuvr.game_server.proto.commons.BI_Battle_Unit_Start.Battle_Unit_Start_Mes.newBuilder();
                            input.readMessage(subBuilder, extensionRegistry);
                            addOwnDamageList(subBuilder.buildPartial());
                            break;
                        }
                    }
                }
            }

            private void ensureOwnDamageListIsMutable() {
                if (!((bitField0_ & 0x00000001) == 0x00000001)) {
                    ownDamageList_ = new java.util.ArrayList<com.pkuvr.game_server.proto.commons.BI_Battle_Unit_Start.Battle_Unit_Start_Mes>(ownDamageList_);
                    bitField0_ |= 0x00000001;
                }
            }

            public java.util.List<com.pkuvr.game_server.proto.commons.BI_Battle_Unit_Start.Battle_Unit_Start_Mes> getOwnDamageListList() {
                if (ownDamageListBuilder_ == null) {
                    return java.util.Collections.unmodifiableList(ownDamageList_);
                } else {
                    return ownDamageListBuilder_.getMessageList();
                }
            }

            public int getOwnDamageListCount() {
                if (ownDamageListBuilder_ == null) {
                    return ownDamageList_.size();
                } else {
                    return ownDamageListBuilder_.getCount();
                }
            }

            public com.pkuvr.game_server.proto.commons.BI_Battle_Unit_Start.Battle_Unit_Start_Mes getOwnDamageList(int index) {
                if (ownDamageListBuilder_ == null) {
                    return ownDamageList_.get(index);
                } else {
                    return ownDamageListBuilder_.getMessage(index);
                }
            }

            public Builder setOwnDamageList(
                    int index, com.pkuvr.game_server.proto.commons.BI_Battle_Unit_Start.Battle_Unit_Start_Mes value) {
                if (ownDamageListBuilder_ == null) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    ensureOwnDamageListIsMutable();
                    ownDamageList_.set(index, value);
                    onChanged();
                } else {
                    ownDamageListBuilder_.setMessage(index, value);
                }
                return this;
            }

            public Builder setOwnDamageList(
                    int index, com.pkuvr.game_server.proto.commons.BI_Battle_Unit_Start.Battle_Unit_Start_Mes.Builder builderForValue) {
                if (ownDamageListBuilder_ == null) {
                    ensureOwnDamageListIsMutable();
                    ownDamageList_.set(index, builderForValue.build());
                    onChanged();
                } else {
                    ownDamageListBuilder_.setMessage(index, builderForValue.build());
                }
                return this;
            }

            public Builder addOwnDamageList(com.pkuvr.game_server.proto.commons.BI_Battle_Unit_Start.Battle_Unit_Start_Mes value) {
                if (ownDamageListBuilder_ == null) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    ensureOwnDamageListIsMutable();
                    ownDamageList_.add(value);
                    onChanged();
                } else {
                    ownDamageListBuilder_.addMessage(value);
                }
                return this;
            }

            public Builder addOwnDamageList(
                    int index, com.pkuvr.game_server.proto.commons.BI_Battle_Unit_Start.Battle_Unit_Start_Mes value) {
                if (ownDamageListBuilder_ == null) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    ensureOwnDamageListIsMutable();
                    ownDamageList_.add(index, value);
                    onChanged();
                } else {
                    ownDamageListBuilder_.addMessage(index, value);
                }
                return this;
            }

            public Builder addOwnDamageList(
                    com.pkuvr.game_server.proto.commons.BI_Battle_Unit_Start.Battle_Unit_Start_Mes.Builder builderForValue) {
                if (ownDamageListBuilder_ == null) {
                    ensureOwnDamageListIsMutable();
                    ownDamageList_.add(builderForValue.build());
                    onChanged();
                } else {
                    ownDamageListBuilder_.addMessage(builderForValue.build());
                }
                return this;
            }

            public Builder addOwnDamageList(
                    int index, com.pkuvr.game_server.proto.commons.BI_Battle_Unit_Start.Battle_Unit_Start_Mes.Builder builderForValue) {
                if (ownDamageListBuilder_ == null) {
                    ensureOwnDamageListIsMutable();
                    ownDamageList_.add(index, builderForValue.build());
                    onChanged();
                } else {
                    ownDamageListBuilder_.addMessage(index, builderForValue.build());
                }
                return this;
            }

            public Builder addAllOwnDamageList(
                    Iterable<? extends com.pkuvr.game_server.proto.commons.BI_Battle_Unit_Start.Battle_Unit_Start_Mes> values) {
                if (ownDamageListBuilder_ == null) {
                    ensureOwnDamageListIsMutable();
                    super.addAll(values, ownDamageList_);
                    onChanged();
                } else {
                    ownDamageListBuilder_.addAllMessages(values);
                }
                return this;
            }

            public Builder clearOwnDamageList() {
                if (ownDamageListBuilder_ == null) {
                    ownDamageList_ = java.util.Collections.emptyList();
                    bitField0_ = (bitField0_ & ~0x00000001);
                    onChanged();
                } else {
                    ownDamageListBuilder_.clear();
                }
                return this;
            }

            public Builder removeOwnDamageList(int index) {
                if (ownDamageListBuilder_ == null) {
                    ensureOwnDamageListIsMutable();
                    ownDamageList_.remove(index);
                    onChanged();
                } else {
                    ownDamageListBuilder_.remove(index);
                }
                return this;
            }

            public com.pkuvr.game_server.proto.commons.BI_Battle_Unit_Start.Battle_Unit_Start_Mes.Builder getOwnDamageListBuilder(
                    int index) {
                return getOwnDamageListFieldBuilder().getBuilder(index);
            }

            public com.pkuvr.game_server.proto.commons.BI_Battle_Unit_Start.Battle_Unit_Start_MesOrBuilder getOwnDamageListOrBuilder(
                    int index) {
                if (ownDamageListBuilder_ == null) {
                    return ownDamageList_.get(index);
                } else {
                    return ownDamageListBuilder_.getMessageOrBuilder(index);
                }
            }

            public java.util.List<? extends com.pkuvr.game_server.proto.commons.BI_Battle_Unit_Start.Battle_Unit_Start_MesOrBuilder>
            getOwnDamageListOrBuilderList() {
                if (ownDamageListBuilder_ != null) {
                    return ownDamageListBuilder_.getMessageOrBuilderList();
                } else {
                    return java.util.Collections.unmodifiableList(ownDamageList_);
                }
            }

            public com.pkuvr.game_server.proto.commons.BI_Battle_Unit_Start.Battle_Unit_Start_Mes.Builder addOwnDamageListBuilder() {
                return getOwnDamageListFieldBuilder().addBuilder(
                        com.pkuvr.game_server.proto.commons.BI_Battle_Unit_Start.Battle_Unit_Start_Mes.getDefaultInstance());
            }

            public com.pkuvr.game_server.proto.commons.BI_Battle_Unit_Start.Battle_Unit_Start_Mes.Builder addOwnDamageListBuilder(
                    int index) {
                return getOwnDamageListFieldBuilder().addBuilder(
                        index, com.pkuvr.game_server.proto.commons.BI_Battle_Unit_Start.Battle_Unit_Start_Mes.getDefaultInstance());
            }

            public java.util.List<com.pkuvr.game_server.proto.commons.BI_Battle_Unit_Start.Battle_Unit_Start_Mes.Builder>
            getOwnDamageListBuilderList() {
                return getOwnDamageListFieldBuilder().getBuilderList();
            }

            private com.google.protobuf.RepeatedFieldBuilder<
                    com.pkuvr.game_server.proto.commons.BI_Battle_Unit_Start.Battle_Unit_Start_Mes, com.pkuvr.game_server.proto.commons.BI_Battle_Unit_Start.Battle_Unit_Start_Mes.Builder, com.pkuvr.game_server.proto.commons.BI_Battle_Unit_Start.Battle_Unit_Start_MesOrBuilder>
            getOwnDamageListFieldBuilder() {
                if (ownDamageListBuilder_ == null) {
                    ownDamageListBuilder_ = new com.google.protobuf.RepeatedFieldBuilder<
                            com.pkuvr.game_server.proto.commons.BI_Battle_Unit_Start.Battle_Unit_Start_Mes, com.pkuvr.game_server.proto.commons.BI_Battle_Unit_Start.Battle_Unit_Start_Mes.Builder, com.pkuvr.game_server.proto.commons.BI_Battle_Unit_Start.Battle_Unit_Start_MesOrBuilder>(
                            ownDamageList_,
                            ((bitField0_ & 0x00000001) == 0x00000001),
                            getParentForChildren(),
                            isClean());
                    ownDamageList_ = null;
                }
                return ownDamageListBuilder_;
            }

            // @@protoc_insertion_point(builder_scope:Pvp_Player_Exit_Req)
        }

        // @@protoc_insertion_point(class_scope:Pvp_Player_Exit_Req)
    }

    // @@protoc_insertion_point(outer_class_scope)
}
