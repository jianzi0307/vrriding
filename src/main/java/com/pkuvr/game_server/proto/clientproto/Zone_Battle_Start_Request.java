// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: Zone_Battle_Start_Req.proto

package com.pkuvr.game_server.proto.clientproto;

public final class Zone_Battle_Start_Request {
    private static com.google.protobuf.Descriptors.Descriptor
            internal_static_Zone_Battle_Start_Req_descriptor;
    private static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
            internal_static_Zone_Battle_Start_Req_fieldAccessorTable;
    private static com.google.protobuf.Descriptors.FileDescriptor
            descriptor;

    static {
        String[] descriptorData = {
                "\n\033Zone_Battle_Start_Req.proto\032\032BI_Battle" +
                        "_Unit_Start.proto\"\203\001\n\025Zone_Battle_Start_" +
                        "Req\022\022\n\nisRevanche\030\001 \001(\010\022\021\n\tresMineID\030\002 \001" +
                        "(\005\022\023\n\013battleLogID\030\003 \001(\003\022.\n\016battleUnitLis" +
                        "t\030\004 \003(\0132\026.Battle_Unit_Start_MesBB\n%com.g" +
                        "fan.gameserver.proto.clientprotoB\031Zone_B" +
                        "attle_Start_Request"
        };
        com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
                new com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner() {
                    public com.google.protobuf.ExtensionRegistry assignDescriptors(
                            com.google.protobuf.Descriptors.FileDescriptor root) {
                        descriptor = root;
                        internal_static_Zone_Battle_Start_Req_descriptor =
                                getDescriptor().getMessageTypes().get(0);
                        internal_static_Zone_Battle_Start_Req_fieldAccessorTable = new
                                com.google.protobuf.GeneratedMessage.FieldAccessorTable(
                                internal_static_Zone_Battle_Start_Req_descriptor,
                                new String[]{"IsRevanche", "ResMineID", "BattleLogID", "BattleUnitList",},
                                com.pkuvr.game_server.proto.clientproto.Zone_Battle_Start_Request.Zone_Battle_Start_Req.class,
                                com.pkuvr.game_server.proto.clientproto.Zone_Battle_Start_Request.Zone_Battle_Start_Req.Builder.class);
                        return null;
                    }
                };
        com.google.protobuf.Descriptors.FileDescriptor
                .internalBuildGeneratedFileFrom(descriptorData,
                        new com.google.protobuf.Descriptors.FileDescriptor[]{
                                com.pkuvr.game_server.proto.commons.BI_Battle_Unit_Start.getDescriptor(),
                        }, assigner);
    }

    private Zone_Battle_Start_Request() {
    }

    public static void registerAllExtensions(
            com.google.protobuf.ExtensionRegistry registry) {
    }

    public static com.google.protobuf.Descriptors.FileDescriptor
    getDescriptor() {
        return descriptor;
    }

    public interface Zone_Battle_Start_ReqOrBuilder
            extends com.google.protobuf.MessageOrBuilder {

        // optional bool isRevanche = 1;
        boolean hasIsRevanche();

        boolean getIsRevanche();

        // optional int32 resMineID = 2;
        boolean hasResMineID();

        int getResMineID();

        // optional int64 battleLogID = 3;
        boolean hasBattleLogID();

        long getBattleLogID();

        // repeated .Battle_Unit_Start_Mes battleUnitList = 4;
        java.util.List<com.pkuvr.game_server.proto.commons.BI_Battle_Unit_Start.Battle_Unit_Start_Mes>
        getBattleUnitListList();

        com.pkuvr.game_server.proto.commons.BI_Battle_Unit_Start.Battle_Unit_Start_Mes getBattleUnitList(int index);

        int getBattleUnitListCount();

        java.util.List<? extends com.pkuvr.game_server.proto.commons.BI_Battle_Unit_Start.Battle_Unit_Start_MesOrBuilder>
        getBattleUnitListOrBuilderList();

        com.pkuvr.game_server.proto.commons.BI_Battle_Unit_Start.Battle_Unit_Start_MesOrBuilder getBattleUnitListOrBuilder(
                int index);
    }

    public static final class Zone_Battle_Start_Req extends
            com.google.protobuf.GeneratedMessage
            implements Zone_Battle_Start_ReqOrBuilder {
        // optional bool isRevanche = 1;
        public static final int ISREVANCHE_FIELD_NUMBER = 1;
        // optional int32 resMineID = 2;
        public static final int RESMINEID_FIELD_NUMBER = 2;
        // optional int64 battleLogID = 3;
        public static final int BATTLELOGID_FIELD_NUMBER = 3;
        // repeated .Battle_Unit_Start_Mes battleUnitList = 4;
        public static final int BATTLEUNITLIST_FIELD_NUMBER = 4;
        private static final Zone_Battle_Start_Req defaultInstance;
        private static final long serialVersionUID = 0L;

        static {
            defaultInstance = new Zone_Battle_Start_Req(true);
            defaultInstance.initFields();
        }

        private int bitField0_;
        private boolean isRevanche_;
        private int resMineID_;
        private long battleLogID_;
        private java.util.List<com.pkuvr.game_server.proto.commons.BI_Battle_Unit_Start.Battle_Unit_Start_Mes> battleUnitList_;
        private byte memoizedIsInitialized = -1;
        private int memoizedSerializedSize = -1;

        // Use Zone_Battle_Start_Req.newBuilder() to construct.
        private Zone_Battle_Start_Req(Builder builder) {
            super(builder);
        }

        private Zone_Battle_Start_Req(boolean noInit) {
        }

        public static Zone_Battle_Start_Req getDefaultInstance() {
            return defaultInstance;
        }

        public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
            return com.pkuvr.game_server.proto.clientproto.Zone_Battle_Start_Request.internal_static_Zone_Battle_Start_Req_descriptor;
        }

        public static com.pkuvr.game_server.proto.clientproto.Zone_Battle_Start_Request.Zone_Battle_Start_Req parseFrom(
                com.google.protobuf.ByteString data)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return newBuilder().mergeFrom(data).buildParsed();
        }

        public static com.pkuvr.game_server.proto.clientproto.Zone_Battle_Start_Request.Zone_Battle_Start_Req parseFrom(
                com.google.protobuf.ByteString data,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return newBuilder().mergeFrom(data, extensionRegistry)
                    .buildParsed();
        }

        public static com.pkuvr.game_server.proto.clientproto.Zone_Battle_Start_Request.Zone_Battle_Start_Req parseFrom(byte[] data)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return newBuilder().mergeFrom(data).buildParsed();
        }

        public static com.pkuvr.game_server.proto.clientproto.Zone_Battle_Start_Request.Zone_Battle_Start_Req parseFrom(
                byte[] data,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return newBuilder().mergeFrom(data, extensionRegistry)
                    .buildParsed();
        }

        public static com.pkuvr.game_server.proto.clientproto.Zone_Battle_Start_Request.Zone_Battle_Start_Req parseFrom(java.io.InputStream input)
                throws java.io.IOException {
            return newBuilder().mergeFrom(input).buildParsed();
        }

        public static com.pkuvr.game_server.proto.clientproto.Zone_Battle_Start_Request.Zone_Battle_Start_Req parseFrom(
                java.io.InputStream input,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws java.io.IOException {
            return newBuilder().mergeFrom(input, extensionRegistry)
                    .buildParsed();
        }

        public static com.pkuvr.game_server.proto.clientproto.Zone_Battle_Start_Request.Zone_Battle_Start_Req parseDelimitedFrom(java.io.InputStream input)
                throws java.io.IOException {
            Builder builder = newBuilder();
            if (builder.mergeDelimitedFrom(input)) {
                return builder.buildParsed();
            } else {
                return null;
            }
        }

        public static com.pkuvr.game_server.proto.clientproto.Zone_Battle_Start_Request.Zone_Battle_Start_Req parseDelimitedFrom(
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

        public static com.pkuvr.game_server.proto.clientproto.Zone_Battle_Start_Request.Zone_Battle_Start_Req parseFrom(
                com.google.protobuf.CodedInputStream input)
                throws java.io.IOException {
            return newBuilder().mergeFrom(input).buildParsed();
        }

        public static com.pkuvr.game_server.proto.clientproto.Zone_Battle_Start_Request.Zone_Battle_Start_Req parseFrom(
                com.google.protobuf.CodedInputStream input,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws java.io.IOException {
            return newBuilder().mergeFrom(input, extensionRegistry)
                    .buildParsed();
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        public static Builder newBuilder(com.pkuvr.game_server.proto.clientproto.Zone_Battle_Start_Request.Zone_Battle_Start_Req prototype) {
            return newBuilder().mergeFrom(prototype);
        }

        public Zone_Battle_Start_Req getDefaultInstanceForType() {
            return defaultInstance;
        }

        protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
            return com.pkuvr.game_server.proto.clientproto.Zone_Battle_Start_Request.internal_static_Zone_Battle_Start_Req_fieldAccessorTable;
        }

        public boolean hasIsRevanche() {
            return ((bitField0_ & 0x00000001) == 0x00000001);
        }

        public boolean getIsRevanche() {
            return isRevanche_;
        }

        public boolean hasResMineID() {
            return ((bitField0_ & 0x00000002) == 0x00000002);
        }

        public int getResMineID() {
            return resMineID_;
        }

        public boolean hasBattleLogID() {
            return ((bitField0_ & 0x00000004) == 0x00000004);
        }

        public long getBattleLogID() {
            return battleLogID_;
        }

        public java.util.List<com.pkuvr.game_server.proto.commons.BI_Battle_Unit_Start.Battle_Unit_Start_Mes> getBattleUnitListList() {
            return battleUnitList_;
        }

        public java.util.List<? extends com.pkuvr.game_server.proto.commons.BI_Battle_Unit_Start.Battle_Unit_Start_MesOrBuilder>
        getBattleUnitListOrBuilderList() {
            return battleUnitList_;
        }

        public int getBattleUnitListCount() {
            return battleUnitList_.size();
        }

        public com.pkuvr.game_server.proto.commons.BI_Battle_Unit_Start.Battle_Unit_Start_Mes getBattleUnitList(int index) {
            return battleUnitList_.get(index);
        }

        public com.pkuvr.game_server.proto.commons.BI_Battle_Unit_Start.Battle_Unit_Start_MesOrBuilder getBattleUnitListOrBuilder(
                int index) {
            return battleUnitList_.get(index);
        }

        private void initFields() {
            isRevanche_ = false;
            resMineID_ = 0;
            battleLogID_ = 0L;
            battleUnitList_ = java.util.Collections.emptyList();
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
                output.writeBool(1, isRevanche_);
            }
            if (((bitField0_ & 0x00000002) == 0x00000002)) {
                output.writeInt32(2, resMineID_);
            }
            if (((bitField0_ & 0x00000004) == 0x00000004)) {
                output.writeInt64(3, battleLogID_);
            }
            for (int i = 0; i < battleUnitList_.size(); i++) {
                output.writeMessage(4, battleUnitList_.get(i));
            }
            getUnknownFields().writeTo(output);
        }

        public int getSerializedSize() {
            int size = memoizedSerializedSize;
            if (size != -1) return size;

            size = 0;
            if (((bitField0_ & 0x00000001) == 0x00000001)) {
                size += com.google.protobuf.CodedOutputStream
                        .computeBoolSize(1, isRevanche_);
            }
            if (((bitField0_ & 0x00000002) == 0x00000002)) {
                size += com.google.protobuf.CodedOutputStream
                        .computeInt32Size(2, resMineID_);
            }
            if (((bitField0_ & 0x00000004) == 0x00000004)) {
                size += com.google.protobuf.CodedOutputStream
                        .computeInt64Size(3, battleLogID_);
            }
            for (int i = 0; i < battleUnitList_.size(); i++) {
                size += com.google.protobuf.CodedOutputStream
                        .computeMessageSize(4, battleUnitList_.get(i));
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
                implements com.pkuvr.game_server.proto.clientproto.Zone_Battle_Start_Request.Zone_Battle_Start_ReqOrBuilder {
            private int bitField0_;
            // optional bool isRevanche = 1;
            private boolean isRevanche_;
            // optional int32 resMineID = 2;
            private int resMineID_;
            // optional int64 battleLogID = 3;
            private long battleLogID_;
            // repeated .Battle_Unit_Start_Mes battleUnitList = 4;
            private java.util.List<com.pkuvr.game_server.proto.commons.BI_Battle_Unit_Start.Battle_Unit_Start_Mes> battleUnitList_ =
                    java.util.Collections.emptyList();
            private com.google.protobuf.RepeatedFieldBuilder<
                    com.pkuvr.game_server.proto.commons.BI_Battle_Unit_Start.Battle_Unit_Start_Mes, com.pkuvr.game_server.proto.commons.BI_Battle_Unit_Start.Battle_Unit_Start_Mes.Builder, com.pkuvr.game_server.proto.commons.BI_Battle_Unit_Start.Battle_Unit_Start_MesOrBuilder> battleUnitListBuilder_;

            // Construct using com.pkuvr.game_server.proto.clientproto.Zone_Battle_Start_Request.Zone_Battle_Start_Req.newBuilder()
            private Builder() {
                maybeForceBuilderInitialization();
            }

            private Builder(BuilderParent parent) {
                super(parent);
                maybeForceBuilderInitialization();
            }

            public static final com.google.protobuf.Descriptors.Descriptor
            getDescriptor() {
                return com.pkuvr.game_server.proto.clientproto.Zone_Battle_Start_Request.internal_static_Zone_Battle_Start_Req_descriptor;
            }

            private static Builder create() {
                return new Builder();
            }

            protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
            internalGetFieldAccessorTable() {
                return com.pkuvr.game_server.proto.clientproto.Zone_Battle_Start_Request.internal_static_Zone_Battle_Start_Req_fieldAccessorTable;
            }

            private void maybeForceBuilderInitialization() {
                if (com.google.protobuf.GeneratedMessage.alwaysUseFieldBuilders) {
                    getBattleUnitListFieldBuilder();
                }
            }

            public Builder clear() {
                super.clear();
                isRevanche_ = false;
                bitField0_ = (bitField0_ & ~0x00000001);
                resMineID_ = 0;
                bitField0_ = (bitField0_ & ~0x00000002);
                battleLogID_ = 0L;
                bitField0_ = (bitField0_ & ~0x00000004);
                if (battleUnitListBuilder_ == null) {
                    battleUnitList_ = java.util.Collections.emptyList();
                    bitField0_ = (bitField0_ & ~0x00000008);
                } else {
                    battleUnitListBuilder_.clear();
                }
                return this;
            }

            public Builder clone() {
                return create().mergeFrom(buildPartial());
            }

            public com.google.protobuf.Descriptors.Descriptor
            getDescriptorForType() {
                return com.pkuvr.game_server.proto.clientproto.Zone_Battle_Start_Request.Zone_Battle_Start_Req.getDescriptor();
            }

            public com.pkuvr.game_server.proto.clientproto.Zone_Battle_Start_Request.Zone_Battle_Start_Req getDefaultInstanceForType() {
                return com.pkuvr.game_server.proto.clientproto.Zone_Battle_Start_Request.Zone_Battle_Start_Req.getDefaultInstance();
            }

            public com.pkuvr.game_server.proto.clientproto.Zone_Battle_Start_Request.Zone_Battle_Start_Req build() {
                com.pkuvr.game_server.proto.clientproto.Zone_Battle_Start_Request.Zone_Battle_Start_Req result = buildPartial();
                if (!result.isInitialized()) {
                    throw newUninitializedMessageException(result);
                }
                return result;
            }

            private com.pkuvr.game_server.proto.clientproto.Zone_Battle_Start_Request.Zone_Battle_Start_Req buildParsed()
                    throws com.google.protobuf.InvalidProtocolBufferException {
                com.pkuvr.game_server.proto.clientproto.Zone_Battle_Start_Request.Zone_Battle_Start_Req result = buildPartial();
                if (!result.isInitialized()) {
                    throw newUninitializedMessageException(
                            result).asInvalidProtocolBufferException();
                }
                return result;
            }

            public com.pkuvr.game_server.proto.clientproto.Zone_Battle_Start_Request.Zone_Battle_Start_Req buildPartial() {
                com.pkuvr.game_server.proto.clientproto.Zone_Battle_Start_Request.Zone_Battle_Start_Req result = new com.pkuvr.game_server.proto.clientproto.Zone_Battle_Start_Request.Zone_Battle_Start_Req(this);
                int from_bitField0_ = bitField0_;
                int to_bitField0_ = 0;
                if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
                    to_bitField0_ |= 0x00000001;
                }
                result.isRevanche_ = isRevanche_;
                if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
                    to_bitField0_ |= 0x00000002;
                }
                result.resMineID_ = resMineID_;
                if (((from_bitField0_ & 0x00000004) == 0x00000004)) {
                    to_bitField0_ |= 0x00000004;
                }
                result.battleLogID_ = battleLogID_;
                if (battleUnitListBuilder_ == null) {
                    if (((bitField0_ & 0x00000008) == 0x00000008)) {
                        battleUnitList_ = java.util.Collections.unmodifiableList(battleUnitList_);
                        bitField0_ = (bitField0_ & ~0x00000008);
                    }
                    result.battleUnitList_ = battleUnitList_;
                } else {
                    result.battleUnitList_ = battleUnitListBuilder_.build();
                }
                result.bitField0_ = to_bitField0_;
                onBuilt();
                return result;
            }

            public Builder mergeFrom(com.google.protobuf.Message other) {
                if (other instanceof com.pkuvr.game_server.proto.clientproto.Zone_Battle_Start_Request.Zone_Battle_Start_Req) {
                    return mergeFrom((com.pkuvr.game_server.proto.clientproto.Zone_Battle_Start_Request.Zone_Battle_Start_Req) other);
                } else {
                    super.mergeFrom(other);
                    return this;
                }
            }

            public Builder mergeFrom(com.pkuvr.game_server.proto.clientproto.Zone_Battle_Start_Request.Zone_Battle_Start_Req other) {
                if (other == com.pkuvr.game_server.proto.clientproto.Zone_Battle_Start_Request.Zone_Battle_Start_Req.getDefaultInstance())
                    return this;
                if (other.hasIsRevanche()) {
                    setIsRevanche(other.getIsRevanche());
                }
                if (other.hasResMineID()) {
                    setResMineID(other.getResMineID());
                }
                if (other.hasBattleLogID()) {
                    setBattleLogID(other.getBattleLogID());
                }
                if (battleUnitListBuilder_ == null) {
                    if (!other.battleUnitList_.isEmpty()) {
                        if (battleUnitList_.isEmpty()) {
                            battleUnitList_ = other.battleUnitList_;
                            bitField0_ = (bitField0_ & ~0x00000008);
                        } else {
                            ensureBattleUnitListIsMutable();
                            battleUnitList_.addAll(other.battleUnitList_);
                        }
                        onChanged();
                    }
                } else {
                    if (!other.battleUnitList_.isEmpty()) {
                        if (battleUnitListBuilder_.isEmpty()) {
                            battleUnitListBuilder_.dispose();
                            battleUnitListBuilder_ = null;
                            battleUnitList_ = other.battleUnitList_;
                            bitField0_ = (bitField0_ & ~0x00000008);
                            battleUnitListBuilder_ =
                                    com.google.protobuf.GeneratedMessage.alwaysUseFieldBuilders ?
                                            getBattleUnitListFieldBuilder() : null;
                        } else {
                            battleUnitListBuilder_.addAllMessages(other.battleUnitList_);
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
                        case 8: {
                            bitField0_ |= 0x00000001;
                            isRevanche_ = input.readBool();
                            break;
                        }
                        case 16: {
                            bitField0_ |= 0x00000002;
                            resMineID_ = input.readInt32();
                            break;
                        }
                        case 24: {
                            bitField0_ |= 0x00000004;
                            battleLogID_ = input.readInt64();
                            break;
                        }
                        case 34: {
                            com.pkuvr.game_server.proto.commons.BI_Battle_Unit_Start.Battle_Unit_Start_Mes.Builder subBuilder = com.pkuvr.game_server.proto.commons.BI_Battle_Unit_Start.Battle_Unit_Start_Mes.newBuilder();
                            input.readMessage(subBuilder, extensionRegistry);
                            addBattleUnitList(subBuilder.buildPartial());
                            break;
                        }
                    }
                }
            }

            public boolean hasIsRevanche() {
                return ((bitField0_ & 0x00000001) == 0x00000001);
            }

            public boolean getIsRevanche() {
                return isRevanche_;
            }

            public Builder setIsRevanche(boolean value) {
                bitField0_ |= 0x00000001;
                isRevanche_ = value;
                onChanged();
                return this;
            }

            public Builder clearIsRevanche() {
                bitField0_ = (bitField0_ & ~0x00000001);
                isRevanche_ = false;
                onChanged();
                return this;
            }

            public boolean hasResMineID() {
                return ((bitField0_ & 0x00000002) == 0x00000002);
            }

            public int getResMineID() {
                return resMineID_;
            }

            public Builder setResMineID(int value) {
                bitField0_ |= 0x00000002;
                resMineID_ = value;
                onChanged();
                return this;
            }

            public Builder clearResMineID() {
                bitField0_ = (bitField0_ & ~0x00000002);
                resMineID_ = 0;
                onChanged();
                return this;
            }

            public boolean hasBattleLogID() {
                return ((bitField0_ & 0x00000004) == 0x00000004);
            }

            public long getBattleLogID() {
                return battleLogID_;
            }

            public Builder setBattleLogID(long value) {
                bitField0_ |= 0x00000004;
                battleLogID_ = value;
                onChanged();
                return this;
            }

            public Builder clearBattleLogID() {
                bitField0_ = (bitField0_ & ~0x00000004);
                battleLogID_ = 0L;
                onChanged();
                return this;
            }

            private void ensureBattleUnitListIsMutable() {
                if (!((bitField0_ & 0x00000008) == 0x00000008)) {
                    battleUnitList_ = new java.util.ArrayList<com.pkuvr.game_server.proto.commons.BI_Battle_Unit_Start.Battle_Unit_Start_Mes>(battleUnitList_);
                    bitField0_ |= 0x00000008;
                }
            }

            public java.util.List<com.pkuvr.game_server.proto.commons.BI_Battle_Unit_Start.Battle_Unit_Start_Mes> getBattleUnitListList() {
                if (battleUnitListBuilder_ == null) {
                    return java.util.Collections.unmodifiableList(battleUnitList_);
                } else {
                    return battleUnitListBuilder_.getMessageList();
                }
            }

            public int getBattleUnitListCount() {
                if (battleUnitListBuilder_ == null) {
                    return battleUnitList_.size();
                } else {
                    return battleUnitListBuilder_.getCount();
                }
            }

            public com.pkuvr.game_server.proto.commons.BI_Battle_Unit_Start.Battle_Unit_Start_Mes getBattleUnitList(int index) {
                if (battleUnitListBuilder_ == null) {
                    return battleUnitList_.get(index);
                } else {
                    return battleUnitListBuilder_.getMessage(index);
                }
            }

            public Builder setBattleUnitList(
                    int index, com.pkuvr.game_server.proto.commons.BI_Battle_Unit_Start.Battle_Unit_Start_Mes value) {
                if (battleUnitListBuilder_ == null) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    ensureBattleUnitListIsMutable();
                    battleUnitList_.set(index, value);
                    onChanged();
                } else {
                    battleUnitListBuilder_.setMessage(index, value);
                }
                return this;
            }

            public Builder setBattleUnitList(
                    int index, com.pkuvr.game_server.proto.commons.BI_Battle_Unit_Start.Battle_Unit_Start_Mes.Builder builderForValue) {
                if (battleUnitListBuilder_ == null) {
                    ensureBattleUnitListIsMutable();
                    battleUnitList_.set(index, builderForValue.build());
                    onChanged();
                } else {
                    battleUnitListBuilder_.setMessage(index, builderForValue.build());
                }
                return this;
            }

            public Builder addBattleUnitList(com.pkuvr.game_server.proto.commons.BI_Battle_Unit_Start.Battle_Unit_Start_Mes value) {
                if (battleUnitListBuilder_ == null) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    ensureBattleUnitListIsMutable();
                    battleUnitList_.add(value);
                    onChanged();
                } else {
                    battleUnitListBuilder_.addMessage(value);
                }
                return this;
            }

            public Builder addBattleUnitList(
                    int index, com.pkuvr.game_server.proto.commons.BI_Battle_Unit_Start.Battle_Unit_Start_Mes value) {
                if (battleUnitListBuilder_ == null) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    ensureBattleUnitListIsMutable();
                    battleUnitList_.add(index, value);
                    onChanged();
                } else {
                    battleUnitListBuilder_.addMessage(index, value);
                }
                return this;
            }

            public Builder addBattleUnitList(
                    com.pkuvr.game_server.proto.commons.BI_Battle_Unit_Start.Battle_Unit_Start_Mes.Builder builderForValue) {
                if (battleUnitListBuilder_ == null) {
                    ensureBattleUnitListIsMutable();
                    battleUnitList_.add(builderForValue.build());
                    onChanged();
                } else {
                    battleUnitListBuilder_.addMessage(builderForValue.build());
                }
                return this;
            }

            public Builder addBattleUnitList(
                    int index, com.pkuvr.game_server.proto.commons.BI_Battle_Unit_Start.Battle_Unit_Start_Mes.Builder builderForValue) {
                if (battleUnitListBuilder_ == null) {
                    ensureBattleUnitListIsMutable();
                    battleUnitList_.add(index, builderForValue.build());
                    onChanged();
                } else {
                    battleUnitListBuilder_.addMessage(index, builderForValue.build());
                }
                return this;
            }

            public Builder addAllBattleUnitList(
                    Iterable<? extends com.pkuvr.game_server.proto.commons.BI_Battle_Unit_Start.Battle_Unit_Start_Mes> values) {
                if (battleUnitListBuilder_ == null) {
                    ensureBattleUnitListIsMutable();
                    super.addAll(values, battleUnitList_);
                    onChanged();
                } else {
                    battleUnitListBuilder_.addAllMessages(values);
                }
                return this;
            }

            public Builder clearBattleUnitList() {
                if (battleUnitListBuilder_ == null) {
                    battleUnitList_ = java.util.Collections.emptyList();
                    bitField0_ = (bitField0_ & ~0x00000008);
                    onChanged();
                } else {
                    battleUnitListBuilder_.clear();
                }
                return this;
            }

            public Builder removeBattleUnitList(int index) {
                if (battleUnitListBuilder_ == null) {
                    ensureBattleUnitListIsMutable();
                    battleUnitList_.remove(index);
                    onChanged();
                } else {
                    battleUnitListBuilder_.remove(index);
                }
                return this;
            }

            public com.pkuvr.game_server.proto.commons.BI_Battle_Unit_Start.Battle_Unit_Start_Mes.Builder getBattleUnitListBuilder(
                    int index) {
                return getBattleUnitListFieldBuilder().getBuilder(index);
            }

            public com.pkuvr.game_server.proto.commons.BI_Battle_Unit_Start.Battle_Unit_Start_MesOrBuilder getBattleUnitListOrBuilder(
                    int index) {
                if (battleUnitListBuilder_ == null) {
                    return battleUnitList_.get(index);
                } else {
                    return battleUnitListBuilder_.getMessageOrBuilder(index);
                }
            }

            public java.util.List<? extends com.pkuvr.game_server.proto.commons.BI_Battle_Unit_Start.Battle_Unit_Start_MesOrBuilder>
            getBattleUnitListOrBuilderList() {
                if (battleUnitListBuilder_ != null) {
                    return battleUnitListBuilder_.getMessageOrBuilderList();
                } else {
                    return java.util.Collections.unmodifiableList(battleUnitList_);
                }
            }

            public com.pkuvr.game_server.proto.commons.BI_Battle_Unit_Start.Battle_Unit_Start_Mes.Builder addBattleUnitListBuilder() {
                return getBattleUnitListFieldBuilder().addBuilder(
                        com.pkuvr.game_server.proto.commons.BI_Battle_Unit_Start.Battle_Unit_Start_Mes.getDefaultInstance());
            }

            public com.pkuvr.game_server.proto.commons.BI_Battle_Unit_Start.Battle_Unit_Start_Mes.Builder addBattleUnitListBuilder(
                    int index) {
                return getBattleUnitListFieldBuilder().addBuilder(
                        index, com.pkuvr.game_server.proto.commons.BI_Battle_Unit_Start.Battle_Unit_Start_Mes.getDefaultInstance());
            }

            public java.util.List<com.pkuvr.game_server.proto.commons.BI_Battle_Unit_Start.Battle_Unit_Start_Mes.Builder>
            getBattleUnitListBuilderList() {
                return getBattleUnitListFieldBuilder().getBuilderList();
            }

            private com.google.protobuf.RepeatedFieldBuilder<
                    com.pkuvr.game_server.proto.commons.BI_Battle_Unit_Start.Battle_Unit_Start_Mes, com.pkuvr.game_server.proto.commons.BI_Battle_Unit_Start.Battle_Unit_Start_Mes.Builder, com.pkuvr.game_server.proto.commons.BI_Battle_Unit_Start.Battle_Unit_Start_MesOrBuilder>
            getBattleUnitListFieldBuilder() {
                if (battleUnitListBuilder_ == null) {
                    battleUnitListBuilder_ = new com.google.protobuf.RepeatedFieldBuilder<
                            com.pkuvr.game_server.proto.commons.BI_Battle_Unit_Start.Battle_Unit_Start_Mes, com.pkuvr.game_server.proto.commons.BI_Battle_Unit_Start.Battle_Unit_Start_Mes.Builder, com.pkuvr.game_server.proto.commons.BI_Battle_Unit_Start.Battle_Unit_Start_MesOrBuilder>(
                            battleUnitList_,
                            ((bitField0_ & 0x00000008) == 0x00000008),
                            getParentForChildren(),
                            isClean());
                    battleUnitList_ = null;
                }
                return battleUnitListBuilder_;
            }

            // @@protoc_insertion_point(builder_scope:Zone_Battle_Start_Req)
        }

        // @@protoc_insertion_point(class_scope:Zone_Battle_Start_Req)
    }

    // @@protoc_insertion_point(outer_class_scope)
}
