// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: Pvp_Sync_Tactics_Notify_Res.proto

package com.pkuvr.game_server.proto.serverproto;

public final class Pvp_Sync_Tactics_Notify_Response {
    private static com.google.protobuf.Descriptors.Descriptor
            internal_static_Pvp_Sync_Tactics_Notify_Res_descriptor;
    private static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
            internal_static_Pvp_Sync_Tactics_Notify_Res_fieldAccessorTable;
    private static com.google.protobuf.Descriptors.FileDescriptor
            descriptor;

    static {
        String[] descriptorData = {
                "\n!Pvp_Sync_Tactics_Notify_Res.proto\"\245\001\n\033" +
                        "Pvp_Sync_Tactics_Notify_Res\022\021\n\ttacticsId" +
                        "\030\001 \001(\005\022\024\n\014tacticsLevel\030\002 \001(\005\022\030\n\020tacticsx" +
                        "Position\030\003 \001(\005\022\030\n\020tacticszPosition\030\004 \001(\005" +
                        "\022\025\n\rtacticsCommon\030\005 \001(\t\022\022\n\nserverTime\030\006 " +
                        "\001(\003BI\n%com.pkuvr.game_server.proto.serverp" +
                        "rotoB Pvp_Sync_Tactics_Notify_Response"
        };
        com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
                new com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner() {
                    public com.google.protobuf.ExtensionRegistry assignDescriptors(
                            com.google.protobuf.Descriptors.FileDescriptor root) {
                        descriptor = root;
                        internal_static_Pvp_Sync_Tactics_Notify_Res_descriptor =
                                getDescriptor().getMessageTypes().get(0);
                        internal_static_Pvp_Sync_Tactics_Notify_Res_fieldAccessorTable = new
                                com.google.protobuf.GeneratedMessage.FieldAccessorTable(
                                internal_static_Pvp_Sync_Tactics_Notify_Res_descriptor,
                                new String[]{"TacticsId", "TacticsLevel", "TacticsxPosition", "TacticszPosition", "TacticsCommon", "ServerTime",},
                                com.pkuvr.game_server.proto.serverproto.Pvp_Sync_Tactics_Notify_Response.Pvp_Sync_Tactics_Notify_Res.class,
                                com.pkuvr.game_server.proto.serverproto.Pvp_Sync_Tactics_Notify_Response.Pvp_Sync_Tactics_Notify_Res.Builder.class);
                        return null;
                    }
                };
        com.google.protobuf.Descriptors.FileDescriptor
                .internalBuildGeneratedFileFrom(descriptorData,
                        new com.google.protobuf.Descriptors.FileDescriptor[]{
                        }, assigner);
    }

    private Pvp_Sync_Tactics_Notify_Response() {
    }

    public static void registerAllExtensions(
            com.google.protobuf.ExtensionRegistry registry) {
    }

    public static com.google.protobuf.Descriptors.FileDescriptor
    getDescriptor() {
        return descriptor;
    }

    public interface Pvp_Sync_Tactics_Notify_ResOrBuilder
            extends com.google.protobuf.MessageOrBuilder {

        // optional int32 tacticsId = 1;
        boolean hasTacticsId();

        int getTacticsId();

        // optional int32 tacticsLevel = 2;
        boolean hasTacticsLevel();

        int getTacticsLevel();

        // optional int32 tacticsxPosition = 3;
        boolean hasTacticsxPosition();

        int getTacticsxPosition();

        // optional int32 tacticszPosition = 4;
        boolean hasTacticszPosition();

        int getTacticszPosition();

        // optional string tacticsCommon = 5;
        boolean hasTacticsCommon();

        String getTacticsCommon();

        // optional int64 serverTime = 6;
        boolean hasServerTime();

        long getServerTime();
    }

    public static final class Pvp_Sync_Tactics_Notify_Res extends
            com.google.protobuf.GeneratedMessage
            implements Pvp_Sync_Tactics_Notify_ResOrBuilder {
        // optional int32 tacticsId = 1;
        public static final int TACTICSID_FIELD_NUMBER = 1;
        // optional int32 tacticsLevel = 2;
        public static final int TACTICSLEVEL_FIELD_NUMBER = 2;
        // optional int32 tacticsxPosition = 3;
        public static final int TACTICSXPOSITION_FIELD_NUMBER = 3;
        // optional int32 tacticszPosition = 4;
        public static final int TACTICSZPOSITION_FIELD_NUMBER = 4;
        // optional string tacticsCommon = 5;
        public static final int TACTICSCOMMON_FIELD_NUMBER = 5;
        // optional int64 serverTime = 6;
        public static final int SERVERTIME_FIELD_NUMBER = 6;
        private static final Pvp_Sync_Tactics_Notify_Res defaultInstance;
        private static final long serialVersionUID = 0L;

        static {
            defaultInstance = new Pvp_Sync_Tactics_Notify_Res(true);
            defaultInstance.initFields();
        }

        private int bitField0_;
        private int tacticsId_;
        private int tacticsLevel_;
        private int tacticsxPosition_;
        private int tacticszPosition_;
        private Object tacticsCommon_;
        private long serverTime_;
        private byte memoizedIsInitialized = -1;
        private int memoizedSerializedSize = -1;

        // Use Pvp_Sync_Tactics_Notify_Res.newBuilder() to construct.
        private Pvp_Sync_Tactics_Notify_Res(Builder builder) {
            super(builder);
        }

        private Pvp_Sync_Tactics_Notify_Res(boolean noInit) {
        }

        public static Pvp_Sync_Tactics_Notify_Res getDefaultInstance() {
            return defaultInstance;
        }

        public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
            return com.pkuvr.game_server.proto.serverproto.Pvp_Sync_Tactics_Notify_Response.internal_static_Pvp_Sync_Tactics_Notify_Res_descriptor;
        }

        public static com.pkuvr.game_server.proto.serverproto.Pvp_Sync_Tactics_Notify_Response.Pvp_Sync_Tactics_Notify_Res parseFrom(
                com.google.protobuf.ByteString data)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return newBuilder().mergeFrom(data).buildParsed();
        }

        public static com.pkuvr.game_server.proto.serverproto.Pvp_Sync_Tactics_Notify_Response.Pvp_Sync_Tactics_Notify_Res parseFrom(
                com.google.protobuf.ByteString data,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return newBuilder().mergeFrom(data, extensionRegistry)
                    .buildParsed();
        }

        public static com.pkuvr.game_server.proto.serverproto.Pvp_Sync_Tactics_Notify_Response.Pvp_Sync_Tactics_Notify_Res parseFrom(byte[] data)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return newBuilder().mergeFrom(data).buildParsed();
        }

        public static com.pkuvr.game_server.proto.serverproto.Pvp_Sync_Tactics_Notify_Response.Pvp_Sync_Tactics_Notify_Res parseFrom(
                byte[] data,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return newBuilder().mergeFrom(data, extensionRegistry)
                    .buildParsed();
        }

        public static com.pkuvr.game_server.proto.serverproto.Pvp_Sync_Tactics_Notify_Response.Pvp_Sync_Tactics_Notify_Res parseFrom(java.io.InputStream input)
                throws java.io.IOException {
            return newBuilder().mergeFrom(input).buildParsed();
        }

        public static com.pkuvr.game_server.proto.serverproto.Pvp_Sync_Tactics_Notify_Response.Pvp_Sync_Tactics_Notify_Res parseFrom(
                java.io.InputStream input,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws java.io.IOException {
            return newBuilder().mergeFrom(input, extensionRegistry)
                    .buildParsed();
        }

        public static com.pkuvr.game_server.proto.serverproto.Pvp_Sync_Tactics_Notify_Response.Pvp_Sync_Tactics_Notify_Res parseDelimitedFrom(java.io.InputStream input)
                throws java.io.IOException {
            Builder builder = newBuilder();
            if (builder.mergeDelimitedFrom(input)) {
                return builder.buildParsed();
            } else {
                return null;
            }
        }

        public static com.pkuvr.game_server.proto.serverproto.Pvp_Sync_Tactics_Notify_Response.Pvp_Sync_Tactics_Notify_Res parseDelimitedFrom(
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

        public static com.pkuvr.game_server.proto.serverproto.Pvp_Sync_Tactics_Notify_Response.Pvp_Sync_Tactics_Notify_Res parseFrom(
                com.google.protobuf.CodedInputStream input)
                throws java.io.IOException {
            return newBuilder().mergeFrom(input).buildParsed();
        }

        public static com.pkuvr.game_server.proto.serverproto.Pvp_Sync_Tactics_Notify_Response.Pvp_Sync_Tactics_Notify_Res parseFrom(
                com.google.protobuf.CodedInputStream input,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws java.io.IOException {
            return newBuilder().mergeFrom(input, extensionRegistry)
                    .buildParsed();
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        public static Builder newBuilder(com.pkuvr.game_server.proto.serverproto.Pvp_Sync_Tactics_Notify_Response.Pvp_Sync_Tactics_Notify_Res prototype) {
            return newBuilder().mergeFrom(prototype);
        }

        public Pvp_Sync_Tactics_Notify_Res getDefaultInstanceForType() {
            return defaultInstance;
        }

        protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
            return com.pkuvr.game_server.proto.serverproto.Pvp_Sync_Tactics_Notify_Response.internal_static_Pvp_Sync_Tactics_Notify_Res_fieldAccessorTable;
        }

        public boolean hasTacticsId() {
            return ((bitField0_ & 0x00000001) == 0x00000001);
        }

        public int getTacticsId() {
            return tacticsId_;
        }

        public boolean hasTacticsLevel() {
            return ((bitField0_ & 0x00000002) == 0x00000002);
        }

        public int getTacticsLevel() {
            return tacticsLevel_;
        }

        public boolean hasTacticsxPosition() {
            return ((bitField0_ & 0x00000004) == 0x00000004);
        }

        public int getTacticsxPosition() {
            return tacticsxPosition_;
        }

        public boolean hasTacticszPosition() {
            return ((bitField0_ & 0x00000008) == 0x00000008);
        }

        public int getTacticszPosition() {
            return tacticszPosition_;
        }

        public boolean hasTacticsCommon() {
            return ((bitField0_ & 0x00000010) == 0x00000010);
        }

        public String getTacticsCommon() {
            Object ref = tacticsCommon_;
            if (ref instanceof String) {
                return (String) ref;
            } else {
                com.google.protobuf.ByteString bs =
                        (com.google.protobuf.ByteString) ref;
                String s = bs.toStringUtf8();
                if (com.google.protobuf.Internal.isValidUtf8(bs)) {
                    tacticsCommon_ = s;
                }
                return s;
            }
        }

        private com.google.protobuf.ByteString getTacticsCommonBytes() {
            Object ref = tacticsCommon_;
            if (ref instanceof String) {
                com.google.protobuf.ByteString b =
                        com.google.protobuf.ByteString.copyFromUtf8((String) ref);
                tacticsCommon_ = b;
                return b;
            } else {
                return (com.google.protobuf.ByteString) ref;
            }
        }

        public boolean hasServerTime() {
            return ((bitField0_ & 0x00000020) == 0x00000020);
        }

        public long getServerTime() {
            return serverTime_;
        }

        private void initFields() {
            tacticsId_ = 0;
            tacticsLevel_ = 0;
            tacticsxPosition_ = 0;
            tacticszPosition_ = 0;
            tacticsCommon_ = "";
            serverTime_ = 0L;
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
                output.writeInt32(1, tacticsId_);
            }
            if (((bitField0_ & 0x00000002) == 0x00000002)) {
                output.writeInt32(2, tacticsLevel_);
            }
            if (((bitField0_ & 0x00000004) == 0x00000004)) {
                output.writeInt32(3, tacticsxPosition_);
            }
            if (((bitField0_ & 0x00000008) == 0x00000008)) {
                output.writeInt32(4, tacticszPosition_);
            }
            if (((bitField0_ & 0x00000010) == 0x00000010)) {
                output.writeBytes(5, getTacticsCommonBytes());
            }
            if (((bitField0_ & 0x00000020) == 0x00000020)) {
                output.writeInt64(6, serverTime_);
            }
            getUnknownFields().writeTo(output);
        }

        public int getSerializedSize() {
            int size = memoizedSerializedSize;
            if (size != -1) return size;

            size = 0;
            if (((bitField0_ & 0x00000001) == 0x00000001)) {
                size += com.google.protobuf.CodedOutputStream
                        .computeInt32Size(1, tacticsId_);
            }
            if (((bitField0_ & 0x00000002) == 0x00000002)) {
                size += com.google.protobuf.CodedOutputStream
                        .computeInt32Size(2, tacticsLevel_);
            }
            if (((bitField0_ & 0x00000004) == 0x00000004)) {
                size += com.google.protobuf.CodedOutputStream
                        .computeInt32Size(3, tacticsxPosition_);
            }
            if (((bitField0_ & 0x00000008) == 0x00000008)) {
                size += com.google.protobuf.CodedOutputStream
                        .computeInt32Size(4, tacticszPosition_);
            }
            if (((bitField0_ & 0x00000010) == 0x00000010)) {
                size += com.google.protobuf.CodedOutputStream
                        .computeBytesSize(5, getTacticsCommonBytes());
            }
            if (((bitField0_ & 0x00000020) == 0x00000020)) {
                size += com.google.protobuf.CodedOutputStream
                        .computeInt64Size(6, serverTime_);
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
                implements com.pkuvr.game_server.proto.serverproto.Pvp_Sync_Tactics_Notify_Response.Pvp_Sync_Tactics_Notify_ResOrBuilder {
            private int bitField0_;
            // optional int32 tacticsId = 1;
            private int tacticsId_;
            // optional int32 tacticsLevel = 2;
            private int tacticsLevel_;
            // optional int32 tacticsxPosition = 3;
            private int tacticsxPosition_;
            // optional int32 tacticszPosition = 4;
            private int tacticszPosition_;
            // optional string tacticsCommon = 5;
            private Object tacticsCommon_ = "";
            // optional int64 serverTime = 6;
            private long serverTime_;

            // Construct using com.pkuvr.game_server.proto.serverproto.Pvp_Sync_Tactics_Notify_Response.Pvp_Sync_Tactics_Notify_Res.newBuilder()
            private Builder() {
                maybeForceBuilderInitialization();
            }

            private Builder(BuilderParent parent) {
                super(parent);
                maybeForceBuilderInitialization();
            }

            public static final com.google.protobuf.Descriptors.Descriptor
            getDescriptor() {
                return com.pkuvr.game_server.proto.serverproto.Pvp_Sync_Tactics_Notify_Response.internal_static_Pvp_Sync_Tactics_Notify_Res_descriptor;
            }

            private static Builder create() {
                return new Builder();
            }

            protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
            internalGetFieldAccessorTable() {
                return com.pkuvr.game_server.proto.serverproto.Pvp_Sync_Tactics_Notify_Response.internal_static_Pvp_Sync_Tactics_Notify_Res_fieldAccessorTable;
            }

            private void maybeForceBuilderInitialization() {
                if (com.google.protobuf.GeneratedMessage.alwaysUseFieldBuilders) {
                }
            }

            public Builder clear() {
                super.clear();
                tacticsId_ = 0;
                bitField0_ = (bitField0_ & ~0x00000001);
                tacticsLevel_ = 0;
                bitField0_ = (bitField0_ & ~0x00000002);
                tacticsxPosition_ = 0;
                bitField0_ = (bitField0_ & ~0x00000004);
                tacticszPosition_ = 0;
                bitField0_ = (bitField0_ & ~0x00000008);
                tacticsCommon_ = "";
                bitField0_ = (bitField0_ & ~0x00000010);
                serverTime_ = 0L;
                bitField0_ = (bitField0_ & ~0x00000020);
                return this;
            }

            public Builder clone() {
                return create().mergeFrom(buildPartial());
            }

            public com.google.protobuf.Descriptors.Descriptor
            getDescriptorForType() {
                return com.pkuvr.game_server.proto.serverproto.Pvp_Sync_Tactics_Notify_Response.Pvp_Sync_Tactics_Notify_Res.getDescriptor();
            }

            public com.pkuvr.game_server.proto.serverproto.Pvp_Sync_Tactics_Notify_Response.Pvp_Sync_Tactics_Notify_Res getDefaultInstanceForType() {
                return com.pkuvr.game_server.proto.serverproto.Pvp_Sync_Tactics_Notify_Response.Pvp_Sync_Tactics_Notify_Res.getDefaultInstance();
            }

            public com.pkuvr.game_server.proto.serverproto.Pvp_Sync_Tactics_Notify_Response.Pvp_Sync_Tactics_Notify_Res build() {
                com.pkuvr.game_server.proto.serverproto.Pvp_Sync_Tactics_Notify_Response.Pvp_Sync_Tactics_Notify_Res result = buildPartial();
                if (!result.isInitialized()) {
                    throw newUninitializedMessageException(result);
                }
                return result;
            }

            private com.pkuvr.game_server.proto.serverproto.Pvp_Sync_Tactics_Notify_Response.Pvp_Sync_Tactics_Notify_Res buildParsed()
                    throws com.google.protobuf.InvalidProtocolBufferException {
                com.pkuvr.game_server.proto.serverproto.Pvp_Sync_Tactics_Notify_Response.Pvp_Sync_Tactics_Notify_Res result = buildPartial();
                if (!result.isInitialized()) {
                    throw newUninitializedMessageException(
                            result).asInvalidProtocolBufferException();
                }
                return result;
            }

            public com.pkuvr.game_server.proto.serverproto.Pvp_Sync_Tactics_Notify_Response.Pvp_Sync_Tactics_Notify_Res buildPartial() {
                com.pkuvr.game_server.proto.serverproto.Pvp_Sync_Tactics_Notify_Response.Pvp_Sync_Tactics_Notify_Res result = new com.pkuvr.game_server.proto.serverproto.Pvp_Sync_Tactics_Notify_Response.Pvp_Sync_Tactics_Notify_Res(this);
                int from_bitField0_ = bitField0_;
                int to_bitField0_ = 0;
                if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
                    to_bitField0_ |= 0x00000001;
                }
                result.tacticsId_ = tacticsId_;
                if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
                    to_bitField0_ |= 0x00000002;
                }
                result.tacticsLevel_ = tacticsLevel_;
                if (((from_bitField0_ & 0x00000004) == 0x00000004)) {
                    to_bitField0_ |= 0x00000004;
                }
                result.tacticsxPosition_ = tacticsxPosition_;
                if (((from_bitField0_ & 0x00000008) == 0x00000008)) {
                    to_bitField0_ |= 0x00000008;
                }
                result.tacticszPosition_ = tacticszPosition_;
                if (((from_bitField0_ & 0x00000010) == 0x00000010)) {
                    to_bitField0_ |= 0x00000010;
                }
                result.tacticsCommon_ = tacticsCommon_;
                if (((from_bitField0_ & 0x00000020) == 0x00000020)) {
                    to_bitField0_ |= 0x00000020;
                }
                result.serverTime_ = serverTime_;
                result.bitField0_ = to_bitField0_;
                onBuilt();
                return result;
            }

            public Builder mergeFrom(com.google.protobuf.Message other) {
                if (other instanceof com.pkuvr.game_server.proto.serverproto.Pvp_Sync_Tactics_Notify_Response.Pvp_Sync_Tactics_Notify_Res) {
                    return mergeFrom((com.pkuvr.game_server.proto.serverproto.Pvp_Sync_Tactics_Notify_Response.Pvp_Sync_Tactics_Notify_Res) other);
                } else {
                    super.mergeFrom(other);
                    return this;
                }
            }

            public Builder mergeFrom(com.pkuvr.game_server.proto.serverproto.Pvp_Sync_Tactics_Notify_Response.Pvp_Sync_Tactics_Notify_Res other) {
                if (other == com.pkuvr.game_server.proto.serverproto.Pvp_Sync_Tactics_Notify_Response.Pvp_Sync_Tactics_Notify_Res.getDefaultInstance())
                    return this;
                if (other.hasTacticsId()) {
                    setTacticsId(other.getTacticsId());
                }
                if (other.hasTacticsLevel()) {
                    setTacticsLevel(other.getTacticsLevel());
                }
                if (other.hasTacticsxPosition()) {
                    setTacticsxPosition(other.getTacticsxPosition());
                }
                if (other.hasTacticszPosition()) {
                    setTacticszPosition(other.getTacticszPosition());
                }
                if (other.hasTacticsCommon()) {
                    setTacticsCommon(other.getTacticsCommon());
                }
                if (other.hasServerTime()) {
                    setServerTime(other.getServerTime());
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
                            tacticsId_ = input.readInt32();
                            break;
                        }
                        case 16: {
                            bitField0_ |= 0x00000002;
                            tacticsLevel_ = input.readInt32();
                            break;
                        }
                        case 24: {
                            bitField0_ |= 0x00000004;
                            tacticsxPosition_ = input.readInt32();
                            break;
                        }
                        case 32: {
                            bitField0_ |= 0x00000008;
                            tacticszPosition_ = input.readInt32();
                            break;
                        }
                        case 42: {
                            bitField0_ |= 0x00000010;
                            tacticsCommon_ = input.readBytes();
                            break;
                        }
                        case 48: {
                            bitField0_ |= 0x00000020;
                            serverTime_ = input.readInt64();
                            break;
                        }
                    }
                }
            }

            public boolean hasTacticsId() {
                return ((bitField0_ & 0x00000001) == 0x00000001);
            }

            public int getTacticsId() {
                return tacticsId_;
            }

            public Builder setTacticsId(int value) {
                bitField0_ |= 0x00000001;
                tacticsId_ = value;
                onChanged();
                return this;
            }

            public Builder clearTacticsId() {
                bitField0_ = (bitField0_ & ~0x00000001);
                tacticsId_ = 0;
                onChanged();
                return this;
            }

            public boolean hasTacticsLevel() {
                return ((bitField0_ & 0x00000002) == 0x00000002);
            }

            public int getTacticsLevel() {
                return tacticsLevel_;
            }

            public Builder setTacticsLevel(int value) {
                bitField0_ |= 0x00000002;
                tacticsLevel_ = value;
                onChanged();
                return this;
            }

            public Builder clearTacticsLevel() {
                bitField0_ = (bitField0_ & ~0x00000002);
                tacticsLevel_ = 0;
                onChanged();
                return this;
            }

            public boolean hasTacticsxPosition() {
                return ((bitField0_ & 0x00000004) == 0x00000004);
            }

            public int getTacticsxPosition() {
                return tacticsxPosition_;
            }

            public Builder setTacticsxPosition(int value) {
                bitField0_ |= 0x00000004;
                tacticsxPosition_ = value;
                onChanged();
                return this;
            }

            public Builder clearTacticsxPosition() {
                bitField0_ = (bitField0_ & ~0x00000004);
                tacticsxPosition_ = 0;
                onChanged();
                return this;
            }

            public boolean hasTacticszPosition() {
                return ((bitField0_ & 0x00000008) == 0x00000008);
            }

            public int getTacticszPosition() {
                return tacticszPosition_;
            }

            public Builder setTacticszPosition(int value) {
                bitField0_ |= 0x00000008;
                tacticszPosition_ = value;
                onChanged();
                return this;
            }

            public Builder clearTacticszPosition() {
                bitField0_ = (bitField0_ & ~0x00000008);
                tacticszPosition_ = 0;
                onChanged();
                return this;
            }

            public boolean hasTacticsCommon() {
                return ((bitField0_ & 0x00000010) == 0x00000010);
            }

            public String getTacticsCommon() {
                Object ref = tacticsCommon_;
                if (!(ref instanceof String)) {
                    String s = ((com.google.protobuf.ByteString) ref).toStringUtf8();
                    tacticsCommon_ = s;
                    return s;
                } else {
                    return (String) ref;
                }
            }

            void setTacticsCommon(com.google.protobuf.ByteString value) {
                bitField0_ |= 0x00000010;
                tacticsCommon_ = value;
                onChanged();
            }

            public Builder setTacticsCommon(String value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                bitField0_ |= 0x00000010;
                tacticsCommon_ = value;
                onChanged();
                return this;
            }

            public Builder clearTacticsCommon() {
                bitField0_ = (bitField0_ & ~0x00000010);
                tacticsCommon_ = getDefaultInstance().getTacticsCommon();
                onChanged();
                return this;
            }

            public boolean hasServerTime() {
                return ((bitField0_ & 0x00000020) == 0x00000020);
            }

            public long getServerTime() {
                return serverTime_;
            }

            public Builder setServerTime(long value) {
                bitField0_ |= 0x00000020;
                serverTime_ = value;
                onChanged();
                return this;
            }

            public Builder clearServerTime() {
                bitField0_ = (bitField0_ & ~0x00000020);
                serverTime_ = 0L;
                onChanged();
                return this;
            }

            // @@protoc_insertion_point(builder_scope:Pvp_Sync_Tactics_Notify_Res)
        }

        // @@protoc_insertion_point(class_scope:Pvp_Sync_Tactics_Notify_Res)
    }

    // @@protoc_insertion_point(outer_class_scope)
}