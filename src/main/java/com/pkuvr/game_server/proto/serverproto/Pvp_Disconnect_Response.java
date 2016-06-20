// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: Pvp_Disconnect_Res.proto

package com.pkuvr.game_server.proto.serverproto;

public final class Pvp_Disconnect_Response {
    private static com.google.protobuf.Descriptors.Descriptor
            internal_static_Pvp_Disconnect_Res_descriptor;
    private static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
            internal_static_Pvp_Disconnect_Res_fieldAccessorTable;
    private static com.google.protobuf.Descriptors.FileDescriptor
            descriptor;

    static {
        String[] descriptorData = {
                "\n\030Pvp_Disconnect_Res.proto\"h\n\022Pvp_Discon" +
                        "nect_Res\022\023\n\013matchRoleId\030\001 \001(\005\022\025\n\rmatchRo" +
                        "leName\030\002 \001(\t\022\021\n\tbattleRes\030\003 \001(\005\022\023\n\013rewar" +
                        "dHonor\030\004 \001(\005B@\n%com.pkuvr.game_server.prot" +
                        "o.serverprotoB\027Pvp_Disconnect_Response"
        };
        com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
                new com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner() {
                    public com.google.protobuf.ExtensionRegistry assignDescriptors(
                            com.google.protobuf.Descriptors.FileDescriptor root) {
                        descriptor = root;
                        internal_static_Pvp_Disconnect_Res_descriptor =
                                getDescriptor().getMessageTypes().get(0);
                        internal_static_Pvp_Disconnect_Res_fieldAccessorTable = new
                                com.google.protobuf.GeneratedMessage.FieldAccessorTable(
                                internal_static_Pvp_Disconnect_Res_descriptor,
                                new String[]{"MatchRoleId", "MatchRoleName", "BattleRes", "RewardHonor",},
                                com.pkuvr.game_server.proto.serverproto.Pvp_Disconnect_Response.Pvp_Disconnect_Res.class,
                                com.pkuvr.game_server.proto.serverproto.Pvp_Disconnect_Response.Pvp_Disconnect_Res.Builder.class);
                        return null;
                    }
                };
        com.google.protobuf.Descriptors.FileDescriptor
                .internalBuildGeneratedFileFrom(descriptorData,
                        new com.google.protobuf.Descriptors.FileDescriptor[]{
                        }, assigner);
    }

    private Pvp_Disconnect_Response() {
    }

    public static void registerAllExtensions(
            com.google.protobuf.ExtensionRegistry registry) {
    }

    public static com.google.protobuf.Descriptors.FileDescriptor
    getDescriptor() {
        return descriptor;
    }

    public interface Pvp_Disconnect_ResOrBuilder
            extends com.google.protobuf.MessageOrBuilder {

        // optional int32 matchRoleId = 1;
        boolean hasMatchRoleId();

        int getMatchRoleId();

        // optional string matchRoleName = 2;
        boolean hasMatchRoleName();

        String getMatchRoleName();

        // optional int32 battleRes = 3;
        boolean hasBattleRes();

        int getBattleRes();

        // optional int32 rewardHonor = 4;
        boolean hasRewardHonor();

        int getRewardHonor();
    }

    public static final class Pvp_Disconnect_Res extends
            com.google.protobuf.GeneratedMessage
            implements Pvp_Disconnect_ResOrBuilder {
        // optional int32 matchRoleId = 1;
        public static final int MATCHROLEID_FIELD_NUMBER = 1;
        // optional string matchRoleName = 2;
        public static final int MATCHROLENAME_FIELD_NUMBER = 2;
        // optional int32 battleRes = 3;
        public static final int BATTLERES_FIELD_NUMBER = 3;
        // optional int32 rewardHonor = 4;
        public static final int REWARDHONOR_FIELD_NUMBER = 4;
        private static final Pvp_Disconnect_Res defaultInstance;
        private static final long serialVersionUID = 0L;

        static {
            defaultInstance = new Pvp_Disconnect_Res(true);
            defaultInstance.initFields();
        }

        private int bitField0_;
        private int matchRoleId_;
        private Object matchRoleName_;
        private int battleRes_;
        private int rewardHonor_;
        private byte memoizedIsInitialized = -1;
        private int memoizedSerializedSize = -1;

        // Use Pvp_Disconnect_Res.newBuilder() to construct.
        private Pvp_Disconnect_Res(Builder builder) {
            super(builder);
        }

        private Pvp_Disconnect_Res(boolean noInit) {
        }

        public static Pvp_Disconnect_Res getDefaultInstance() {
            return defaultInstance;
        }

        public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
            return com.pkuvr.game_server.proto.serverproto.Pvp_Disconnect_Response.internal_static_Pvp_Disconnect_Res_descriptor;
        }

        public static com.pkuvr.game_server.proto.serverproto.Pvp_Disconnect_Response.Pvp_Disconnect_Res parseFrom(
                com.google.protobuf.ByteString data)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return newBuilder().mergeFrom(data).buildParsed();
        }

        public static com.pkuvr.game_server.proto.serverproto.Pvp_Disconnect_Response.Pvp_Disconnect_Res parseFrom(
                com.google.protobuf.ByteString data,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return newBuilder().mergeFrom(data, extensionRegistry)
                    .buildParsed();
        }

        public static com.pkuvr.game_server.proto.serverproto.Pvp_Disconnect_Response.Pvp_Disconnect_Res parseFrom(byte[] data)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return newBuilder().mergeFrom(data).buildParsed();
        }

        public static com.pkuvr.game_server.proto.serverproto.Pvp_Disconnect_Response.Pvp_Disconnect_Res parseFrom(
                byte[] data,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return newBuilder().mergeFrom(data, extensionRegistry)
                    .buildParsed();
        }

        public static com.pkuvr.game_server.proto.serverproto.Pvp_Disconnect_Response.Pvp_Disconnect_Res parseFrom(java.io.InputStream input)
                throws java.io.IOException {
            return newBuilder().mergeFrom(input).buildParsed();
        }

        public static com.pkuvr.game_server.proto.serverproto.Pvp_Disconnect_Response.Pvp_Disconnect_Res parseFrom(
                java.io.InputStream input,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws java.io.IOException {
            return newBuilder().mergeFrom(input, extensionRegistry)
                    .buildParsed();
        }

        public static com.pkuvr.game_server.proto.serverproto.Pvp_Disconnect_Response.Pvp_Disconnect_Res parseDelimitedFrom(java.io.InputStream input)
                throws java.io.IOException {
            Builder builder = newBuilder();
            if (builder.mergeDelimitedFrom(input)) {
                return builder.buildParsed();
            } else {
                return null;
            }
        }

        public static com.pkuvr.game_server.proto.serverproto.Pvp_Disconnect_Response.Pvp_Disconnect_Res parseDelimitedFrom(
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

        public static com.pkuvr.game_server.proto.serverproto.Pvp_Disconnect_Response.Pvp_Disconnect_Res parseFrom(
                com.google.protobuf.CodedInputStream input)
                throws java.io.IOException {
            return newBuilder().mergeFrom(input).buildParsed();
        }

        public static com.pkuvr.game_server.proto.serverproto.Pvp_Disconnect_Response.Pvp_Disconnect_Res parseFrom(
                com.google.protobuf.CodedInputStream input,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws java.io.IOException {
            return newBuilder().mergeFrom(input, extensionRegistry)
                    .buildParsed();
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        public static Builder newBuilder(com.pkuvr.game_server.proto.serverproto.Pvp_Disconnect_Response.Pvp_Disconnect_Res prototype) {
            return newBuilder().mergeFrom(prototype);
        }

        public Pvp_Disconnect_Res getDefaultInstanceForType() {
            return defaultInstance;
        }

        protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
            return com.pkuvr.game_server.proto.serverproto.Pvp_Disconnect_Response.internal_static_Pvp_Disconnect_Res_fieldAccessorTable;
        }

        public boolean hasMatchRoleId() {
            return ((bitField0_ & 0x00000001) == 0x00000001);
        }

        public int getMatchRoleId() {
            return matchRoleId_;
        }

        public boolean hasMatchRoleName() {
            return ((bitField0_ & 0x00000002) == 0x00000002);
        }

        public String getMatchRoleName() {
            Object ref = matchRoleName_;
            if (ref instanceof String) {
                return (String) ref;
            } else {
                com.google.protobuf.ByteString bs =
                        (com.google.protobuf.ByteString) ref;
                String s = bs.toStringUtf8();
                if (com.google.protobuf.Internal.isValidUtf8(bs)) {
                    matchRoleName_ = s;
                }
                return s;
            }
        }

        private com.google.protobuf.ByteString getMatchRoleNameBytes() {
            Object ref = matchRoleName_;
            if (ref instanceof String) {
                com.google.protobuf.ByteString b =
                        com.google.protobuf.ByteString.copyFromUtf8((String) ref);
                matchRoleName_ = b;
                return b;
            } else {
                return (com.google.protobuf.ByteString) ref;
            }
        }

        public boolean hasBattleRes() {
            return ((bitField0_ & 0x00000004) == 0x00000004);
        }

        public int getBattleRes() {
            return battleRes_;
        }

        public boolean hasRewardHonor() {
            return ((bitField0_ & 0x00000008) == 0x00000008);
        }

        public int getRewardHonor() {
            return rewardHonor_;
        }

        private void initFields() {
            matchRoleId_ = 0;
            matchRoleName_ = "";
            battleRes_ = 0;
            rewardHonor_ = 0;
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
                output.writeInt32(1, matchRoleId_);
            }
            if (((bitField0_ & 0x00000002) == 0x00000002)) {
                output.writeBytes(2, getMatchRoleNameBytes());
            }
            if (((bitField0_ & 0x00000004) == 0x00000004)) {
                output.writeInt32(3, battleRes_);
            }
            if (((bitField0_ & 0x00000008) == 0x00000008)) {
                output.writeInt32(4, rewardHonor_);
            }
            getUnknownFields().writeTo(output);
        }

        public int getSerializedSize() {
            int size = memoizedSerializedSize;
            if (size != -1) return size;

            size = 0;
            if (((bitField0_ & 0x00000001) == 0x00000001)) {
                size += com.google.protobuf.CodedOutputStream
                        .computeInt32Size(1, matchRoleId_);
            }
            if (((bitField0_ & 0x00000002) == 0x00000002)) {
                size += com.google.protobuf.CodedOutputStream
                        .computeBytesSize(2, getMatchRoleNameBytes());
            }
            if (((bitField0_ & 0x00000004) == 0x00000004)) {
                size += com.google.protobuf.CodedOutputStream
                        .computeInt32Size(3, battleRes_);
            }
            if (((bitField0_ & 0x00000008) == 0x00000008)) {
                size += com.google.protobuf.CodedOutputStream
                        .computeInt32Size(4, rewardHonor_);
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
                implements com.pkuvr.game_server.proto.serverproto.Pvp_Disconnect_Response.Pvp_Disconnect_ResOrBuilder {
            private int bitField0_;
            // optional int32 matchRoleId = 1;
            private int matchRoleId_;
            // optional string matchRoleName = 2;
            private Object matchRoleName_ = "";
            // optional int32 battleRes = 3;
            private int battleRes_;
            // optional int32 rewardHonor = 4;
            private int rewardHonor_;

            // Construct using com.pkuvr.game_server.proto.serverproto.Pvp_Disconnect_Response.Pvp_Disconnect_Res.newBuilder()
            private Builder() {
                maybeForceBuilderInitialization();
            }

            private Builder(BuilderParent parent) {
                super(parent);
                maybeForceBuilderInitialization();
            }

            public static final com.google.protobuf.Descriptors.Descriptor
            getDescriptor() {
                return com.pkuvr.game_server.proto.serverproto.Pvp_Disconnect_Response.internal_static_Pvp_Disconnect_Res_descriptor;
            }

            private static Builder create() {
                return new Builder();
            }

            protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
            internalGetFieldAccessorTable() {
                return com.pkuvr.game_server.proto.serverproto.Pvp_Disconnect_Response.internal_static_Pvp_Disconnect_Res_fieldAccessorTable;
            }

            private void maybeForceBuilderInitialization() {
                if (com.google.protobuf.GeneratedMessage.alwaysUseFieldBuilders) {
                }
            }

            public Builder clear() {
                super.clear();
                matchRoleId_ = 0;
                bitField0_ = (bitField0_ & ~0x00000001);
                matchRoleName_ = "";
                bitField0_ = (bitField0_ & ~0x00000002);
                battleRes_ = 0;
                bitField0_ = (bitField0_ & ~0x00000004);
                rewardHonor_ = 0;
                bitField0_ = (bitField0_ & ~0x00000008);
                return this;
            }

            public Builder clone() {
                return create().mergeFrom(buildPartial());
            }

            public com.google.protobuf.Descriptors.Descriptor
            getDescriptorForType() {
                return com.pkuvr.game_server.proto.serverproto.Pvp_Disconnect_Response.Pvp_Disconnect_Res.getDescriptor();
            }

            public com.pkuvr.game_server.proto.serverproto.Pvp_Disconnect_Response.Pvp_Disconnect_Res getDefaultInstanceForType() {
                return com.pkuvr.game_server.proto.serverproto.Pvp_Disconnect_Response.Pvp_Disconnect_Res.getDefaultInstance();
            }

            public com.pkuvr.game_server.proto.serverproto.Pvp_Disconnect_Response.Pvp_Disconnect_Res build() {
                com.pkuvr.game_server.proto.serverproto.Pvp_Disconnect_Response.Pvp_Disconnect_Res result = buildPartial();
                if (!result.isInitialized()) {
                    throw newUninitializedMessageException(result);
                }
                return result;
            }

            private com.pkuvr.game_server.proto.serverproto.Pvp_Disconnect_Response.Pvp_Disconnect_Res buildParsed()
                    throws com.google.protobuf.InvalidProtocolBufferException {
                com.pkuvr.game_server.proto.serverproto.Pvp_Disconnect_Response.Pvp_Disconnect_Res result = buildPartial();
                if (!result.isInitialized()) {
                    throw newUninitializedMessageException(
                            result).asInvalidProtocolBufferException();
                }
                return result;
            }

            public com.pkuvr.game_server.proto.serverproto.Pvp_Disconnect_Response.Pvp_Disconnect_Res buildPartial() {
                com.pkuvr.game_server.proto.serverproto.Pvp_Disconnect_Response.Pvp_Disconnect_Res result = new com.pkuvr.game_server.proto.serverproto.Pvp_Disconnect_Response.Pvp_Disconnect_Res(this);
                int from_bitField0_ = bitField0_;
                int to_bitField0_ = 0;
                if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
                    to_bitField0_ |= 0x00000001;
                }
                result.matchRoleId_ = matchRoleId_;
                if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
                    to_bitField0_ |= 0x00000002;
                }
                result.matchRoleName_ = matchRoleName_;
                if (((from_bitField0_ & 0x00000004) == 0x00000004)) {
                    to_bitField0_ |= 0x00000004;
                }
                result.battleRes_ = battleRes_;
                if (((from_bitField0_ & 0x00000008) == 0x00000008)) {
                    to_bitField0_ |= 0x00000008;
                }
                result.rewardHonor_ = rewardHonor_;
                result.bitField0_ = to_bitField0_;
                onBuilt();
                return result;
            }

            public Builder mergeFrom(com.google.protobuf.Message other) {
                if (other instanceof com.pkuvr.game_server.proto.serverproto.Pvp_Disconnect_Response.Pvp_Disconnect_Res) {
                    return mergeFrom((com.pkuvr.game_server.proto.serverproto.Pvp_Disconnect_Response.Pvp_Disconnect_Res) other);
                } else {
                    super.mergeFrom(other);
                    return this;
                }
            }

            public Builder mergeFrom(com.pkuvr.game_server.proto.serverproto.Pvp_Disconnect_Response.Pvp_Disconnect_Res other) {
                if (other == com.pkuvr.game_server.proto.serverproto.Pvp_Disconnect_Response.Pvp_Disconnect_Res.getDefaultInstance())
                    return this;
                if (other.hasMatchRoleId()) {
                    setMatchRoleId(other.getMatchRoleId());
                }
                if (other.hasMatchRoleName()) {
                    setMatchRoleName(other.getMatchRoleName());
                }
                if (other.hasBattleRes()) {
                    setBattleRes(other.getBattleRes());
                }
                if (other.hasRewardHonor()) {
                    setRewardHonor(other.getRewardHonor());
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
                            matchRoleId_ = input.readInt32();
                            break;
                        }
                        case 18: {
                            bitField0_ |= 0x00000002;
                            matchRoleName_ = input.readBytes();
                            break;
                        }
                        case 24: {
                            bitField0_ |= 0x00000004;
                            battleRes_ = input.readInt32();
                            break;
                        }
                        case 32: {
                            bitField0_ |= 0x00000008;
                            rewardHonor_ = input.readInt32();
                            break;
                        }
                    }
                }
            }

            public boolean hasMatchRoleId() {
                return ((bitField0_ & 0x00000001) == 0x00000001);
            }

            public int getMatchRoleId() {
                return matchRoleId_;
            }

            public Builder setMatchRoleId(int value) {
                bitField0_ |= 0x00000001;
                matchRoleId_ = value;
                onChanged();
                return this;
            }

            public Builder clearMatchRoleId() {
                bitField0_ = (bitField0_ & ~0x00000001);
                matchRoleId_ = 0;
                onChanged();
                return this;
            }

            public boolean hasMatchRoleName() {
                return ((bitField0_ & 0x00000002) == 0x00000002);
            }

            public String getMatchRoleName() {
                Object ref = matchRoleName_;
                if (!(ref instanceof String)) {
                    String s = ((com.google.protobuf.ByteString) ref).toStringUtf8();
                    matchRoleName_ = s;
                    return s;
                } else {
                    return (String) ref;
                }
            }

            void setMatchRoleName(com.google.protobuf.ByteString value) {
                bitField0_ |= 0x00000002;
                matchRoleName_ = value;
                onChanged();
            }

            public Builder setMatchRoleName(String value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                bitField0_ |= 0x00000002;
                matchRoleName_ = value;
                onChanged();
                return this;
            }

            public Builder clearMatchRoleName() {
                bitField0_ = (bitField0_ & ~0x00000002);
                matchRoleName_ = getDefaultInstance().getMatchRoleName();
                onChanged();
                return this;
            }

            public boolean hasBattleRes() {
                return ((bitField0_ & 0x00000004) == 0x00000004);
            }

            public int getBattleRes() {
                return battleRes_;
            }

            public Builder setBattleRes(int value) {
                bitField0_ |= 0x00000004;
                battleRes_ = value;
                onChanged();
                return this;
            }

            public Builder clearBattleRes() {
                bitField0_ = (bitField0_ & ~0x00000004);
                battleRes_ = 0;
                onChanged();
                return this;
            }

            public boolean hasRewardHonor() {
                return ((bitField0_ & 0x00000008) == 0x00000008);
            }

            public int getRewardHonor() {
                return rewardHonor_;
            }

            public Builder setRewardHonor(int value) {
                bitField0_ |= 0x00000008;
                rewardHonor_ = value;
                onChanged();
                return this;
            }

            public Builder clearRewardHonor() {
                bitField0_ = (bitField0_ & ~0x00000008);
                rewardHonor_ = 0;
                onChanged();
                return this;
            }

            // @@protoc_insertion_point(builder_scope:Pvp_Disconnect_Res)
        }

        // @@protoc_insertion_point(class_scope:Pvp_Disconnect_Res)
    }

    // @@protoc_insertion_point(outer_class_scope)
}
