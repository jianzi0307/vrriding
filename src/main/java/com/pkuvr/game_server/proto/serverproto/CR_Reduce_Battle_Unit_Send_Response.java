// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: CR_Reduce_Battle_Unit_Send.proto

package com.pkuvr.game_server.proto.serverproto;

public final class CR_Reduce_Battle_Unit_Send_Response {
    private static com.google.protobuf.Descriptors.Descriptor
            internal_static_CR_Reduce_Battle_Unit_Send_descriptor;
    private static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
            internal_static_CR_Reduce_Battle_Unit_Send_fieldAccessorTable;
    private static com.google.protobuf.Descriptors.FileDescriptor
            descriptor;

    static {
        String[] descriptorData = {
                "\n CR_Reduce_Battle_Unit_Send.proto\032\032BI_B" +
                        "attle_Unit_Start.proto\"X\n\032CR_Reduce_Batt" +
                        "le_Unit_Send\022\016\n\006roleId\030\001 \002(\005\022*\n\ndamageLi" +
                        "st\030\002 \003(\0132\026.Battle_Unit_Start_MesBL\n%com." +
                        "pkuvr.game_server.proto.serverprotoB#CR_Re" +
                        "duce_Battle_Unit_Send_Response"
        };
        com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
                new com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner() {
                    public com.google.protobuf.ExtensionRegistry assignDescriptors(
                            com.google.protobuf.Descriptors.FileDescriptor root) {
                        descriptor = root;
                        internal_static_CR_Reduce_Battle_Unit_Send_descriptor =
                                getDescriptor().getMessageTypes().get(0);
                        internal_static_CR_Reduce_Battle_Unit_Send_fieldAccessorTable = new
                                com.google.protobuf.GeneratedMessage.FieldAccessorTable(
                                internal_static_CR_Reduce_Battle_Unit_Send_descriptor,
                                new String[]{"RoleId", "DamageList",},
                                com.pkuvr.game_server.proto.serverproto.CR_Reduce_Battle_Unit_Send_Response.CR_Reduce_Battle_Unit_Send.class,
                                com.pkuvr.game_server.proto.serverproto.CR_Reduce_Battle_Unit_Send_Response.CR_Reduce_Battle_Unit_Send.Builder.class);
                        return null;
                    }
                };
        com.google.protobuf.Descriptors.FileDescriptor
                .internalBuildGeneratedFileFrom(descriptorData,
                        new com.google.protobuf.Descriptors.FileDescriptor[]{
                                com.pkuvr.game_server.proto.commons.BI_Battle_Unit_Start.getDescriptor(),
                        }, assigner);
    }

    private CR_Reduce_Battle_Unit_Send_Response() {
    }

    public static void registerAllExtensions(
            com.google.protobuf.ExtensionRegistry registry) {
    }

    public static com.google.protobuf.Descriptors.FileDescriptor
    getDescriptor() {
        return descriptor;
    }

    public interface CR_Reduce_Battle_Unit_SendOrBuilder
            extends com.google.protobuf.MessageOrBuilder {

        // required int32 roleId = 1;
        boolean hasRoleId();

        int getRoleId();

        // repeated .Battle_Unit_Start_Mes damageList = 2;
        java.util.List<com.pkuvr.game_server.proto.commons.BI_Battle_Unit_Start.Battle_Unit_Start_Mes>
        getDamageListList();

        com.pkuvr.game_server.proto.commons.BI_Battle_Unit_Start.Battle_Unit_Start_Mes getDamageList(int index);

        int getDamageListCount();

        java.util.List<? extends com.pkuvr.game_server.proto.commons.BI_Battle_Unit_Start.Battle_Unit_Start_MesOrBuilder>
        getDamageListOrBuilderList();

        com.pkuvr.game_server.proto.commons.BI_Battle_Unit_Start.Battle_Unit_Start_MesOrBuilder getDamageListOrBuilder(
                int index);
    }

    public static final class CR_Reduce_Battle_Unit_Send extends
            com.google.protobuf.GeneratedMessage
            implements CR_Reduce_Battle_Unit_SendOrBuilder {
        // required int32 roleId = 1;
        public static final int ROLEID_FIELD_NUMBER = 1;
        // repeated .Battle_Unit_Start_Mes damageList = 2;
        public static final int DAMAGELIST_FIELD_NUMBER = 2;
        private static final CR_Reduce_Battle_Unit_Send defaultInstance;
        private static final long serialVersionUID = 0L;

        static {
            defaultInstance = new CR_Reduce_Battle_Unit_Send(true);
            defaultInstance.initFields();
        }

        private int bitField0_;
        private int roleId_;
        private java.util.List<com.pkuvr.game_server.proto.commons.BI_Battle_Unit_Start.Battle_Unit_Start_Mes> damageList_;
        private byte memoizedIsInitialized = -1;
        private int memoizedSerializedSize = -1;

        // Use CR_Reduce_Battle_Unit_Send.newBuilder() to construct.
        private CR_Reduce_Battle_Unit_Send(Builder builder) {
            super(builder);
        }

        private CR_Reduce_Battle_Unit_Send(boolean noInit) {
        }

        public static CR_Reduce_Battle_Unit_Send getDefaultInstance() {
            return defaultInstance;
        }

        public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
            return com.pkuvr.game_server.proto.serverproto.CR_Reduce_Battle_Unit_Send_Response.internal_static_CR_Reduce_Battle_Unit_Send_descriptor;
        }

        public static com.pkuvr.game_server.proto.serverproto.CR_Reduce_Battle_Unit_Send_Response.CR_Reduce_Battle_Unit_Send parseFrom(
                com.google.protobuf.ByteString data)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return newBuilder().mergeFrom(data).buildParsed();
        }

        public static com.pkuvr.game_server.proto.serverproto.CR_Reduce_Battle_Unit_Send_Response.CR_Reduce_Battle_Unit_Send parseFrom(
                com.google.protobuf.ByteString data,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return newBuilder().mergeFrom(data, extensionRegistry)
                    .buildParsed();
        }

        public static com.pkuvr.game_server.proto.serverproto.CR_Reduce_Battle_Unit_Send_Response.CR_Reduce_Battle_Unit_Send parseFrom(byte[] data)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return newBuilder().mergeFrom(data).buildParsed();
        }

        public static com.pkuvr.game_server.proto.serverproto.CR_Reduce_Battle_Unit_Send_Response.CR_Reduce_Battle_Unit_Send parseFrom(
                byte[] data,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return newBuilder().mergeFrom(data, extensionRegistry)
                    .buildParsed();
        }

        public static com.pkuvr.game_server.proto.serverproto.CR_Reduce_Battle_Unit_Send_Response.CR_Reduce_Battle_Unit_Send parseFrom(java.io.InputStream input)
                throws java.io.IOException {
            return newBuilder().mergeFrom(input).buildParsed();
        }

        public static com.pkuvr.game_server.proto.serverproto.CR_Reduce_Battle_Unit_Send_Response.CR_Reduce_Battle_Unit_Send parseFrom(
                java.io.InputStream input,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws java.io.IOException {
            return newBuilder().mergeFrom(input, extensionRegistry)
                    .buildParsed();
        }

        public static com.pkuvr.game_server.proto.serverproto.CR_Reduce_Battle_Unit_Send_Response.CR_Reduce_Battle_Unit_Send parseDelimitedFrom(java.io.InputStream input)
                throws java.io.IOException {
            Builder builder = newBuilder();
            if (builder.mergeDelimitedFrom(input)) {
                return builder.buildParsed();
            } else {
                return null;
            }
        }

        public static com.pkuvr.game_server.proto.serverproto.CR_Reduce_Battle_Unit_Send_Response.CR_Reduce_Battle_Unit_Send parseDelimitedFrom(
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

        public static com.pkuvr.game_server.proto.serverproto.CR_Reduce_Battle_Unit_Send_Response.CR_Reduce_Battle_Unit_Send parseFrom(
                com.google.protobuf.CodedInputStream input)
                throws java.io.IOException {
            return newBuilder().mergeFrom(input).buildParsed();
        }

        public static com.pkuvr.game_server.proto.serverproto.CR_Reduce_Battle_Unit_Send_Response.CR_Reduce_Battle_Unit_Send parseFrom(
                com.google.protobuf.CodedInputStream input,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws java.io.IOException {
            return newBuilder().mergeFrom(input, extensionRegistry)
                    .buildParsed();
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        public static Builder newBuilder(com.pkuvr.game_server.proto.serverproto.CR_Reduce_Battle_Unit_Send_Response.CR_Reduce_Battle_Unit_Send prototype) {
            return newBuilder().mergeFrom(prototype);
        }

        public CR_Reduce_Battle_Unit_Send getDefaultInstanceForType() {
            return defaultInstance;
        }

        protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
            return com.pkuvr.game_server.proto.serverproto.CR_Reduce_Battle_Unit_Send_Response.internal_static_CR_Reduce_Battle_Unit_Send_fieldAccessorTable;
        }

        public boolean hasRoleId() {
            return ((bitField0_ & 0x00000001) == 0x00000001);
        }

        public int getRoleId() {
            return roleId_;
        }

        public java.util.List<com.pkuvr.game_server.proto.commons.BI_Battle_Unit_Start.Battle_Unit_Start_Mes> getDamageListList() {
            return damageList_;
        }

        public java.util.List<? extends com.pkuvr.game_server.proto.commons.BI_Battle_Unit_Start.Battle_Unit_Start_MesOrBuilder>
        getDamageListOrBuilderList() {
            return damageList_;
        }

        public int getDamageListCount() {
            return damageList_.size();
        }

        public com.pkuvr.game_server.proto.commons.BI_Battle_Unit_Start.Battle_Unit_Start_Mes getDamageList(int index) {
            return damageList_.get(index);
        }

        public com.pkuvr.game_server.proto.commons.BI_Battle_Unit_Start.Battle_Unit_Start_MesOrBuilder getDamageListOrBuilder(
                int index) {
            return damageList_.get(index);
        }

        private void initFields() {
            roleId_ = 0;
            damageList_ = java.util.Collections.emptyList();
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
            for (int i = 0; i < damageList_.size(); i++) {
                output.writeMessage(2, damageList_.get(i));
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
            for (int i = 0; i < damageList_.size(); i++) {
                size += com.google.protobuf.CodedOutputStream
                        .computeMessageSize(2, damageList_.get(i));
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
                implements com.pkuvr.game_server.proto.serverproto.CR_Reduce_Battle_Unit_Send_Response.CR_Reduce_Battle_Unit_SendOrBuilder {
            private int bitField0_;
            // required int32 roleId = 1;
            private int roleId_;
            // repeated .Battle_Unit_Start_Mes damageList = 2;
            private java.util.List<com.pkuvr.game_server.proto.commons.BI_Battle_Unit_Start.Battle_Unit_Start_Mes> damageList_ =
                    java.util.Collections.emptyList();
            private com.google.protobuf.RepeatedFieldBuilder<
                    com.pkuvr.game_server.proto.commons.BI_Battle_Unit_Start.Battle_Unit_Start_Mes, com.pkuvr.game_server.proto.commons.BI_Battle_Unit_Start.Battle_Unit_Start_Mes.Builder, com.pkuvr.game_server.proto.commons.BI_Battle_Unit_Start.Battle_Unit_Start_MesOrBuilder> damageListBuilder_;

            // Construct using com.pkuvr.game_server.proto.serverproto.CR_Reduce_Battle_Unit_Send_Response.CR_Reduce_Battle_Unit_Send.newBuilder()
            private Builder() {
                maybeForceBuilderInitialization();
            }

            private Builder(BuilderParent parent) {
                super(parent);
                maybeForceBuilderInitialization();
            }

            public static final com.google.protobuf.Descriptors.Descriptor
            getDescriptor() {
                return com.pkuvr.game_server.proto.serverproto.CR_Reduce_Battle_Unit_Send_Response.internal_static_CR_Reduce_Battle_Unit_Send_descriptor;
            }

            private static Builder create() {
                return new Builder();
            }

            protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
            internalGetFieldAccessorTable() {
                return com.pkuvr.game_server.proto.serverproto.CR_Reduce_Battle_Unit_Send_Response.internal_static_CR_Reduce_Battle_Unit_Send_fieldAccessorTable;
            }

            private void maybeForceBuilderInitialization() {
                if (com.google.protobuf.GeneratedMessage.alwaysUseFieldBuilders) {
                    getDamageListFieldBuilder();
                }
            }

            public Builder clear() {
                super.clear();
                roleId_ = 0;
                bitField0_ = (bitField0_ & ~0x00000001);
                if (damageListBuilder_ == null) {
                    damageList_ = java.util.Collections.emptyList();
                    bitField0_ = (bitField0_ & ~0x00000002);
                } else {
                    damageListBuilder_.clear();
                }
                return this;
            }

            public Builder clone() {
                return create().mergeFrom(buildPartial());
            }

            public com.google.protobuf.Descriptors.Descriptor
            getDescriptorForType() {
                return com.pkuvr.game_server.proto.serverproto.CR_Reduce_Battle_Unit_Send_Response.CR_Reduce_Battle_Unit_Send.getDescriptor();
            }

            public com.pkuvr.game_server.proto.serverproto.CR_Reduce_Battle_Unit_Send_Response.CR_Reduce_Battle_Unit_Send getDefaultInstanceForType() {
                return com.pkuvr.game_server.proto.serverproto.CR_Reduce_Battle_Unit_Send_Response.CR_Reduce_Battle_Unit_Send.getDefaultInstance();
            }

            public com.pkuvr.game_server.proto.serverproto.CR_Reduce_Battle_Unit_Send_Response.CR_Reduce_Battle_Unit_Send build() {
                com.pkuvr.game_server.proto.serverproto.CR_Reduce_Battle_Unit_Send_Response.CR_Reduce_Battle_Unit_Send result = buildPartial();
                if (!result.isInitialized()) {
                    throw newUninitializedMessageException(result);
                }
                return result;
            }

            private com.pkuvr.game_server.proto.serverproto.CR_Reduce_Battle_Unit_Send_Response.CR_Reduce_Battle_Unit_Send buildParsed()
                    throws com.google.protobuf.InvalidProtocolBufferException {
                com.pkuvr.game_server.proto.serverproto.CR_Reduce_Battle_Unit_Send_Response.CR_Reduce_Battle_Unit_Send result = buildPartial();
                if (!result.isInitialized()) {
                    throw newUninitializedMessageException(
                            result).asInvalidProtocolBufferException();
                }
                return result;
            }

            public com.pkuvr.game_server.proto.serverproto.CR_Reduce_Battle_Unit_Send_Response.CR_Reduce_Battle_Unit_Send buildPartial() {
                com.pkuvr.game_server.proto.serverproto.CR_Reduce_Battle_Unit_Send_Response.CR_Reduce_Battle_Unit_Send result = new com.pkuvr.game_server.proto.serverproto.CR_Reduce_Battle_Unit_Send_Response.CR_Reduce_Battle_Unit_Send(this);
                int from_bitField0_ = bitField0_;
                int to_bitField0_ = 0;
                if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
                    to_bitField0_ |= 0x00000001;
                }
                result.roleId_ = roleId_;
                if (damageListBuilder_ == null) {
                    if (((bitField0_ & 0x00000002) == 0x00000002)) {
                        damageList_ = java.util.Collections.unmodifiableList(damageList_);
                        bitField0_ = (bitField0_ & ~0x00000002);
                    }
                    result.damageList_ = damageList_;
                } else {
                    result.damageList_ = damageListBuilder_.build();
                }
                result.bitField0_ = to_bitField0_;
                onBuilt();
                return result;
            }

            public Builder mergeFrom(com.google.protobuf.Message other) {
                if (other instanceof com.pkuvr.game_server.proto.serverproto.CR_Reduce_Battle_Unit_Send_Response.CR_Reduce_Battle_Unit_Send) {
                    return mergeFrom((com.pkuvr.game_server.proto.serverproto.CR_Reduce_Battle_Unit_Send_Response.CR_Reduce_Battle_Unit_Send) other);
                } else {
                    super.mergeFrom(other);
                    return this;
                }
            }

            public Builder mergeFrom(com.pkuvr.game_server.proto.serverproto.CR_Reduce_Battle_Unit_Send_Response.CR_Reduce_Battle_Unit_Send other) {
                if (other == com.pkuvr.game_server.proto.serverproto.CR_Reduce_Battle_Unit_Send_Response.CR_Reduce_Battle_Unit_Send.getDefaultInstance())
                    return this;
                if (other.hasRoleId()) {
                    setRoleId(other.getRoleId());
                }
                if (damageListBuilder_ == null) {
                    if (!other.damageList_.isEmpty()) {
                        if (damageList_.isEmpty()) {
                            damageList_ = other.damageList_;
                            bitField0_ = (bitField0_ & ~0x00000002);
                        } else {
                            ensureDamageListIsMutable();
                            damageList_.addAll(other.damageList_);
                        }
                        onChanged();
                    }
                } else {
                    if (!other.damageList_.isEmpty()) {
                        if (damageListBuilder_.isEmpty()) {
                            damageListBuilder_.dispose();
                            damageListBuilder_ = null;
                            damageList_ = other.damageList_;
                            bitField0_ = (bitField0_ & ~0x00000002);
                            damageListBuilder_ =
                                    com.google.protobuf.GeneratedMessage.alwaysUseFieldBuilders ?
                                            getDamageListFieldBuilder() : null;
                        } else {
                            damageListBuilder_.addAllMessages(other.damageList_);
                        }
                    }
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
                        case 18: {
                            com.pkuvr.game_server.proto.commons.BI_Battle_Unit_Start.Battle_Unit_Start_Mes.Builder subBuilder = com.pkuvr.game_server.proto.commons.BI_Battle_Unit_Start.Battle_Unit_Start_Mes.newBuilder();
                            input.readMessage(subBuilder, extensionRegistry);
                            addDamageList(subBuilder.buildPartial());
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

            private void ensureDamageListIsMutable() {
                if (!((bitField0_ & 0x00000002) == 0x00000002)) {
                    damageList_ = new java.util.ArrayList<com.pkuvr.game_server.proto.commons.BI_Battle_Unit_Start.Battle_Unit_Start_Mes>(damageList_);
                    bitField0_ |= 0x00000002;
                }
            }

            public java.util.List<com.pkuvr.game_server.proto.commons.BI_Battle_Unit_Start.Battle_Unit_Start_Mes> getDamageListList() {
                if (damageListBuilder_ == null) {
                    return java.util.Collections.unmodifiableList(damageList_);
                } else {
                    return damageListBuilder_.getMessageList();
                }
            }

            public int getDamageListCount() {
                if (damageListBuilder_ == null) {
                    return damageList_.size();
                } else {
                    return damageListBuilder_.getCount();
                }
            }

            public com.pkuvr.game_server.proto.commons.BI_Battle_Unit_Start.Battle_Unit_Start_Mes getDamageList(int index) {
                if (damageListBuilder_ == null) {
                    return damageList_.get(index);
                } else {
                    return damageListBuilder_.getMessage(index);
                }
            }

            public Builder setDamageList(
                    int index, com.pkuvr.game_server.proto.commons.BI_Battle_Unit_Start.Battle_Unit_Start_Mes value) {
                if (damageListBuilder_ == null) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    ensureDamageListIsMutable();
                    damageList_.set(index, value);
                    onChanged();
                } else {
                    damageListBuilder_.setMessage(index, value);
                }
                return this;
            }

            public Builder setDamageList(
                    int index, com.pkuvr.game_server.proto.commons.BI_Battle_Unit_Start.Battle_Unit_Start_Mes.Builder builderForValue) {
                if (damageListBuilder_ == null) {
                    ensureDamageListIsMutable();
                    damageList_.set(index, builderForValue.build());
                    onChanged();
                } else {
                    damageListBuilder_.setMessage(index, builderForValue.build());
                }
                return this;
            }

            public Builder addDamageList(com.pkuvr.game_server.proto.commons.BI_Battle_Unit_Start.Battle_Unit_Start_Mes value) {
                if (damageListBuilder_ == null) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    ensureDamageListIsMutable();
                    damageList_.add(value);
                    onChanged();
                } else {
                    damageListBuilder_.addMessage(value);
                }
                return this;
            }

            public Builder addDamageList(
                    int index, com.pkuvr.game_server.proto.commons.BI_Battle_Unit_Start.Battle_Unit_Start_Mes value) {
                if (damageListBuilder_ == null) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    ensureDamageListIsMutable();
                    damageList_.add(index, value);
                    onChanged();
                } else {
                    damageListBuilder_.addMessage(index, value);
                }
                return this;
            }

            public Builder addDamageList(
                    com.pkuvr.game_server.proto.commons.BI_Battle_Unit_Start.Battle_Unit_Start_Mes.Builder builderForValue) {
                if (damageListBuilder_ == null) {
                    ensureDamageListIsMutable();
                    damageList_.add(builderForValue.build());
                    onChanged();
                } else {
                    damageListBuilder_.addMessage(builderForValue.build());
                }
                return this;
            }

            public Builder addDamageList(
                    int index, com.pkuvr.game_server.proto.commons.BI_Battle_Unit_Start.Battle_Unit_Start_Mes.Builder builderForValue) {
                if (damageListBuilder_ == null) {
                    ensureDamageListIsMutable();
                    damageList_.add(index, builderForValue.build());
                    onChanged();
                } else {
                    damageListBuilder_.addMessage(index, builderForValue.build());
                }
                return this;
            }

            public Builder addAllDamageList(
                    Iterable<? extends com.pkuvr.game_server.proto.commons.BI_Battle_Unit_Start.Battle_Unit_Start_Mes> values) {
                if (damageListBuilder_ == null) {
                    ensureDamageListIsMutable();
                    super.addAll(values, damageList_);
                    onChanged();
                } else {
                    damageListBuilder_.addAllMessages(values);
                }
                return this;
            }

            public Builder clearDamageList() {
                if (damageListBuilder_ == null) {
                    damageList_ = java.util.Collections.emptyList();
                    bitField0_ = (bitField0_ & ~0x00000002);
                    onChanged();
                } else {
                    damageListBuilder_.clear();
                }
                return this;
            }

            public Builder removeDamageList(int index) {
                if (damageListBuilder_ == null) {
                    ensureDamageListIsMutable();
                    damageList_.remove(index);
                    onChanged();
                } else {
                    damageListBuilder_.remove(index);
                }
                return this;
            }

            public com.pkuvr.game_server.proto.commons.BI_Battle_Unit_Start.Battle_Unit_Start_Mes.Builder getDamageListBuilder(
                    int index) {
                return getDamageListFieldBuilder().getBuilder(index);
            }

            public com.pkuvr.game_server.proto.commons.BI_Battle_Unit_Start.Battle_Unit_Start_MesOrBuilder getDamageListOrBuilder(
                    int index) {
                if (damageListBuilder_ == null) {
                    return damageList_.get(index);
                } else {
                    return damageListBuilder_.getMessageOrBuilder(index);
                }
            }

            public java.util.List<? extends com.pkuvr.game_server.proto.commons.BI_Battle_Unit_Start.Battle_Unit_Start_MesOrBuilder>
            getDamageListOrBuilderList() {
                if (damageListBuilder_ != null) {
                    return damageListBuilder_.getMessageOrBuilderList();
                } else {
                    return java.util.Collections.unmodifiableList(damageList_);
                }
            }

            public com.pkuvr.game_server.proto.commons.BI_Battle_Unit_Start.Battle_Unit_Start_Mes.Builder addDamageListBuilder() {
                return getDamageListFieldBuilder().addBuilder(
                        com.pkuvr.game_server.proto.commons.BI_Battle_Unit_Start.Battle_Unit_Start_Mes.getDefaultInstance());
            }

            public com.pkuvr.game_server.proto.commons.BI_Battle_Unit_Start.Battle_Unit_Start_Mes.Builder addDamageListBuilder(
                    int index) {
                return getDamageListFieldBuilder().addBuilder(
                        index, com.pkuvr.game_server.proto.commons.BI_Battle_Unit_Start.Battle_Unit_Start_Mes.getDefaultInstance());
            }

            public java.util.List<com.pkuvr.game_server.proto.commons.BI_Battle_Unit_Start.Battle_Unit_Start_Mes.Builder>
            getDamageListBuilderList() {
                return getDamageListFieldBuilder().getBuilderList();
            }

            private com.google.protobuf.RepeatedFieldBuilder<
                    com.pkuvr.game_server.proto.commons.BI_Battle_Unit_Start.Battle_Unit_Start_Mes, com.pkuvr.game_server.proto.commons.BI_Battle_Unit_Start.Battle_Unit_Start_Mes.Builder, com.pkuvr.game_server.proto.commons.BI_Battle_Unit_Start.Battle_Unit_Start_MesOrBuilder>
            getDamageListFieldBuilder() {
                if (damageListBuilder_ == null) {
                    damageListBuilder_ = new com.google.protobuf.RepeatedFieldBuilder<
                            com.pkuvr.game_server.proto.commons.BI_Battle_Unit_Start.Battle_Unit_Start_Mes, com.pkuvr.game_server.proto.commons.BI_Battle_Unit_Start.Battle_Unit_Start_Mes.Builder, com.pkuvr.game_server.proto.commons.BI_Battle_Unit_Start.Battle_Unit_Start_MesOrBuilder>(
                            damageList_,
                            ((bitField0_ & 0x00000002) == 0x00000002),
                            getParentForChildren(),
                            isClean());
                    damageList_ = null;
                }
                return damageListBuilder_;
            }

            // @@protoc_insertion_point(builder_scope:CR_Reduce_Battle_Unit_Send)
        }

        // @@protoc_insertion_point(class_scope:CR_Reduce_Battle_Unit_Send)
    }

    // @@protoc_insertion_point(outer_class_scope)
}