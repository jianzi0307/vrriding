// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: Friend_Invite_Send.proto

package com.pkuvr.game_server.proto.serverproto;

public final class GS2C_Friend_Invite_Send {
    private static com.google.protobuf.Descriptors.Descriptor
            internal_static_Friend_Invite_Send_descriptor;
    private static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
            internal_static_Friend_Invite_Send_fieldAccessorTable;
    private static com.google.protobuf.Descriptors.FileDescriptor
            descriptor;

    static {
        String[] descriptorData = {
                "\n\030Friend_Invite_Send.proto\"o\n\022Friend_Inv" +
                        "ite_Send\022\021\n\terrorCode\030\001 \002(\005\022\014\n\004name\030\002 \002(" +
                        "\t\022\016\n\006avatar\030\003 \002(\t\022\014\n\004camp\030\004 \002(\005\022\r\n\005level" +
                        "\030\005 \002(\005\022\013\n\003vip\030\006 \002(\005B@\n%com.gfan.gameserv" +
                        "er.proto.serverprotoB\027GS2C_Friend_Invite" +
                        "_Send"
        };
        com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
                new com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner() {
                    public com.google.protobuf.ExtensionRegistry assignDescriptors(
                            com.google.protobuf.Descriptors.FileDescriptor root) {
                        descriptor = root;
                        internal_static_Friend_Invite_Send_descriptor =
                                getDescriptor().getMessageTypes().get(0);
                        internal_static_Friend_Invite_Send_fieldAccessorTable = new
                                com.google.protobuf.GeneratedMessage.FieldAccessorTable(
                                internal_static_Friend_Invite_Send_descriptor,
                                new String[]{"ErrorCode", "Name", "Avatar", "Camp", "Level", "Vip",},
                                com.pkuvr.game_server.proto.serverproto.GS2C_Friend_Invite_Send.Friend_Invite_Send.class,
                                com.pkuvr.game_server.proto.serverproto.GS2C_Friend_Invite_Send.Friend_Invite_Send.Builder.class);
                        return null;
                    }
                };
        com.google.protobuf.Descriptors.FileDescriptor
                .internalBuildGeneratedFileFrom(descriptorData,
                        new com.google.protobuf.Descriptors.FileDescriptor[]{
                        }, assigner);
    }

    private GS2C_Friend_Invite_Send() {
    }

    public static void registerAllExtensions(
            com.google.protobuf.ExtensionRegistry registry) {
    }

    public static com.google.protobuf.Descriptors.FileDescriptor
    getDescriptor() {
        return descriptor;
    }

    public interface Friend_Invite_SendOrBuilder
            extends com.google.protobuf.MessageOrBuilder {

        // required int32 errorCode = 1;
        boolean hasErrorCode();

        int getErrorCode();

        // required string name = 2;
        boolean hasName();

        String getName();

        // required string avatar = 3;
        boolean hasAvatar();

        String getAvatar();

        // required int32 camp = 4;
        boolean hasCamp();

        int getCamp();

        // required int32 level = 5;
        boolean hasLevel();

        int getLevel();

        // required int32 vip = 6;
        boolean hasVip();

        int getVip();
    }

    public static final class Friend_Invite_Send extends
            com.google.protobuf.GeneratedMessage
            implements Friend_Invite_SendOrBuilder {
        // required int32 errorCode = 1;
        public static final int ERRORCODE_FIELD_NUMBER = 1;
        // required string name = 2;
        public static final int NAME_FIELD_NUMBER = 2;
        // required string avatar = 3;
        public static final int AVATAR_FIELD_NUMBER = 3;
        // required int32 camp = 4;
        public static final int CAMP_FIELD_NUMBER = 4;
        // required int32 level = 5;
        public static final int LEVEL_FIELD_NUMBER = 5;
        // required int32 vip = 6;
        public static final int VIP_FIELD_NUMBER = 6;
        private static final Friend_Invite_Send defaultInstance;
        private static final long serialVersionUID = 0L;

        static {
            defaultInstance = new Friend_Invite_Send(true);
            defaultInstance.initFields();
        }

        private int bitField0_;
        private int errorCode_;
        private Object name_;
        private Object avatar_;
        private int camp_;
        private int level_;
        private int vip_;
        private byte memoizedIsInitialized = -1;
        private int memoizedSerializedSize = -1;
        // Use Friend_Invite_Send.newBuilder() to construct.
        private Friend_Invite_Send(Builder builder) {
            super(builder);
        }

        private Friend_Invite_Send(boolean noInit) {
        }

        public static Friend_Invite_Send getDefaultInstance() {
            return defaultInstance;
        }

        public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
            return com.pkuvr.game_server.proto.serverproto.GS2C_Friend_Invite_Send.internal_static_Friend_Invite_Send_descriptor;
        }

        public static com.pkuvr.game_server.proto.serverproto.GS2C_Friend_Invite_Send.Friend_Invite_Send parseFrom(
                com.google.protobuf.ByteString data)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return newBuilder().mergeFrom(data).buildParsed();
        }

        public static com.pkuvr.game_server.proto.serverproto.GS2C_Friend_Invite_Send.Friend_Invite_Send parseFrom(
                com.google.protobuf.ByteString data,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return newBuilder().mergeFrom(data, extensionRegistry)
                    .buildParsed();
        }

        public static com.pkuvr.game_server.proto.serverproto.GS2C_Friend_Invite_Send.Friend_Invite_Send parseFrom(byte[] data)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return newBuilder().mergeFrom(data).buildParsed();
        }

        public static com.pkuvr.game_server.proto.serverproto.GS2C_Friend_Invite_Send.Friend_Invite_Send parseFrom(
                byte[] data,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return newBuilder().mergeFrom(data, extensionRegistry)
                    .buildParsed();
        }

        public static com.pkuvr.game_server.proto.serverproto.GS2C_Friend_Invite_Send.Friend_Invite_Send parseFrom(java.io.InputStream input)
                throws java.io.IOException {
            return newBuilder().mergeFrom(input).buildParsed();
        }

        public static com.pkuvr.game_server.proto.serverproto.GS2C_Friend_Invite_Send.Friend_Invite_Send parseFrom(
                java.io.InputStream input,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws java.io.IOException {
            return newBuilder().mergeFrom(input, extensionRegistry)
                    .buildParsed();
        }

        public static com.pkuvr.game_server.proto.serverproto.GS2C_Friend_Invite_Send.Friend_Invite_Send parseDelimitedFrom(java.io.InputStream input)
                throws java.io.IOException {
            Builder builder = newBuilder();
            if (builder.mergeDelimitedFrom(input)) {
                return builder.buildParsed();
            } else {
                return null;
            }
        }

        public static com.pkuvr.game_server.proto.serverproto.GS2C_Friend_Invite_Send.Friend_Invite_Send parseDelimitedFrom(
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

        public static com.pkuvr.game_server.proto.serverproto.GS2C_Friend_Invite_Send.Friend_Invite_Send parseFrom(
                com.google.protobuf.CodedInputStream input)
                throws java.io.IOException {
            return newBuilder().mergeFrom(input).buildParsed();
        }

        public static com.pkuvr.game_server.proto.serverproto.GS2C_Friend_Invite_Send.Friend_Invite_Send parseFrom(
                com.google.protobuf.CodedInputStream input,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws java.io.IOException {
            return newBuilder().mergeFrom(input, extensionRegistry)
                    .buildParsed();
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        public static Builder newBuilder(com.pkuvr.game_server.proto.serverproto.GS2C_Friend_Invite_Send.Friend_Invite_Send prototype) {
            return newBuilder().mergeFrom(prototype);
        }

        public Friend_Invite_Send getDefaultInstanceForType() {
            return defaultInstance;
        }

        protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
            return com.pkuvr.game_server.proto.serverproto.GS2C_Friend_Invite_Send.internal_static_Friend_Invite_Send_fieldAccessorTable;
        }

        public boolean hasErrorCode() {
            return ((bitField0_ & 0x00000001) == 0x00000001);
        }

        public int getErrorCode() {
            return errorCode_;
        }

        public boolean hasName() {
            return ((bitField0_ & 0x00000002) == 0x00000002);
        }

        public String getName() {
            Object ref = name_;
            if (ref instanceof String) {
                return (String) ref;
            } else {
                com.google.protobuf.ByteString bs =
                        (com.google.protobuf.ByteString) ref;
                String s = bs.toStringUtf8();
                if (com.google.protobuf.Internal.isValidUtf8(bs)) {
                    name_ = s;
                }
                return s;
            }
        }

        private com.google.protobuf.ByteString getNameBytes() {
            Object ref = name_;
            if (ref instanceof String) {
                com.google.protobuf.ByteString b =
                        com.google.protobuf.ByteString.copyFromUtf8((String) ref);
                name_ = b;
                return b;
            } else {
                return (com.google.protobuf.ByteString) ref;
            }
        }

        public boolean hasAvatar() {
            return ((bitField0_ & 0x00000004) == 0x00000004);
        }

        public String getAvatar() {
            Object ref = avatar_;
            if (ref instanceof String) {
                return (String) ref;
            } else {
                com.google.protobuf.ByteString bs =
                        (com.google.protobuf.ByteString) ref;
                String s = bs.toStringUtf8();
                if (com.google.protobuf.Internal.isValidUtf8(bs)) {
                    avatar_ = s;
                }
                return s;
            }
        }

        private com.google.protobuf.ByteString getAvatarBytes() {
            Object ref = avatar_;
            if (ref instanceof String) {
                com.google.protobuf.ByteString b =
                        com.google.protobuf.ByteString.copyFromUtf8((String) ref);
                avatar_ = b;
                return b;
            } else {
                return (com.google.protobuf.ByteString) ref;
            }
        }

        public boolean hasCamp() {
            return ((bitField0_ & 0x00000008) == 0x00000008);
        }

        public int getCamp() {
            return camp_;
        }

        public boolean hasLevel() {
            return ((bitField0_ & 0x00000010) == 0x00000010);
        }

        public int getLevel() {
            return level_;
        }

        public boolean hasVip() {
            return ((bitField0_ & 0x00000020) == 0x00000020);
        }

        public int getVip() {
            return vip_;
        }

        private void initFields() {
            errorCode_ = 0;
            name_ = "";
            avatar_ = "";
            camp_ = 0;
            level_ = 0;
            vip_ = 0;
        }

        public final boolean isInitialized() {
            byte isInitialized = memoizedIsInitialized;
            if (isInitialized != -1) return isInitialized == 1;

            if (!hasErrorCode()) {
                memoizedIsInitialized = 0;
                return false;
            }
            if (!hasName()) {
                memoizedIsInitialized = 0;
                return false;
            }
            if (!hasAvatar()) {
                memoizedIsInitialized = 0;
                return false;
            }
            if (!hasCamp()) {
                memoizedIsInitialized = 0;
                return false;
            }
            if (!hasLevel()) {
                memoizedIsInitialized = 0;
                return false;
            }
            if (!hasVip()) {
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
                output.writeBytes(2, getNameBytes());
            }
            if (((bitField0_ & 0x00000004) == 0x00000004)) {
                output.writeBytes(3, getAvatarBytes());
            }
            if (((bitField0_ & 0x00000008) == 0x00000008)) {
                output.writeInt32(4, camp_);
            }
            if (((bitField0_ & 0x00000010) == 0x00000010)) {
                output.writeInt32(5, level_);
            }
            if (((bitField0_ & 0x00000020) == 0x00000020)) {
                output.writeInt32(6, vip_);
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
                        .computeBytesSize(2, getNameBytes());
            }
            if (((bitField0_ & 0x00000004) == 0x00000004)) {
                size += com.google.protobuf.CodedOutputStream
                        .computeBytesSize(3, getAvatarBytes());
            }
            if (((bitField0_ & 0x00000008) == 0x00000008)) {
                size += com.google.protobuf.CodedOutputStream
                        .computeInt32Size(4, camp_);
            }
            if (((bitField0_ & 0x00000010) == 0x00000010)) {
                size += com.google.protobuf.CodedOutputStream
                        .computeInt32Size(5, level_);
            }
            if (((bitField0_ & 0x00000020) == 0x00000020)) {
                size += com.google.protobuf.CodedOutputStream
                        .computeInt32Size(6, vip_);
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
                implements com.pkuvr.game_server.proto.serverproto.GS2C_Friend_Invite_Send.Friend_Invite_SendOrBuilder {
            private int bitField0_;
            // required int32 errorCode = 1;
            private int errorCode_;
            // required string name = 2;
            private Object name_ = "";
            // required string avatar = 3;
            private Object avatar_ = "";
            // required int32 camp = 4;
            private int camp_;
            // required int32 level = 5;
            private int level_;
            // required int32 vip = 6;
            private int vip_;

            // Construct using com.pkuvr.game_server.proto.serverproto.GS2C_Friend_Invite_Send.Friend_Invite_Send.newBuilder()
            private Builder() {
                maybeForceBuilderInitialization();
            }

            private Builder(BuilderParent parent) {
                super(parent);
                maybeForceBuilderInitialization();
            }

            public static final com.google.protobuf.Descriptors.Descriptor
            getDescriptor() {
                return com.pkuvr.game_server.proto.serverproto.GS2C_Friend_Invite_Send.internal_static_Friend_Invite_Send_descriptor;
            }

            private static Builder create() {
                return new Builder();
            }

            protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
            internalGetFieldAccessorTable() {
                return com.pkuvr.game_server.proto.serverproto.GS2C_Friend_Invite_Send.internal_static_Friend_Invite_Send_fieldAccessorTable;
            }

            private void maybeForceBuilderInitialization() {
                if (com.google.protobuf.GeneratedMessage.alwaysUseFieldBuilders) {
                }
            }

            public Builder clear() {
                super.clear();
                errorCode_ = 0;
                bitField0_ = (bitField0_ & ~0x00000001);
                name_ = "";
                bitField0_ = (bitField0_ & ~0x00000002);
                avatar_ = "";
                bitField0_ = (bitField0_ & ~0x00000004);
                camp_ = 0;
                bitField0_ = (bitField0_ & ~0x00000008);
                level_ = 0;
                bitField0_ = (bitField0_ & ~0x00000010);
                vip_ = 0;
                bitField0_ = (bitField0_ & ~0x00000020);
                return this;
            }

            public Builder clone() {
                return create().mergeFrom(buildPartial());
            }

            public com.google.protobuf.Descriptors.Descriptor
            getDescriptorForType() {
                return com.pkuvr.game_server.proto.serverproto.GS2C_Friend_Invite_Send.Friend_Invite_Send.getDescriptor();
            }

            public com.pkuvr.game_server.proto.serverproto.GS2C_Friend_Invite_Send.Friend_Invite_Send getDefaultInstanceForType() {
                return com.pkuvr.game_server.proto.serverproto.GS2C_Friend_Invite_Send.Friend_Invite_Send.getDefaultInstance();
            }

            public com.pkuvr.game_server.proto.serverproto.GS2C_Friend_Invite_Send.Friend_Invite_Send build() {
                com.pkuvr.game_server.proto.serverproto.GS2C_Friend_Invite_Send.Friend_Invite_Send result = buildPartial();
                if (!result.isInitialized()) {
                    throw newUninitializedMessageException(result);
                }
                return result;
            }

            private com.pkuvr.game_server.proto.serverproto.GS2C_Friend_Invite_Send.Friend_Invite_Send buildParsed()
                    throws com.google.protobuf.InvalidProtocolBufferException {
                com.pkuvr.game_server.proto.serverproto.GS2C_Friend_Invite_Send.Friend_Invite_Send result = buildPartial();
                if (!result.isInitialized()) {
                    throw newUninitializedMessageException(
                            result).asInvalidProtocolBufferException();
                }
                return result;
            }

            public com.pkuvr.game_server.proto.serverproto.GS2C_Friend_Invite_Send.Friend_Invite_Send buildPartial() {
                com.pkuvr.game_server.proto.serverproto.GS2C_Friend_Invite_Send.Friend_Invite_Send result = new com.pkuvr.game_server.proto.serverproto.GS2C_Friend_Invite_Send.Friend_Invite_Send(this);
                int from_bitField0_ = bitField0_;
                int to_bitField0_ = 0;
                if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
                    to_bitField0_ |= 0x00000001;
                }
                result.errorCode_ = errorCode_;
                if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
                    to_bitField0_ |= 0x00000002;
                }
                result.name_ = name_;
                if (((from_bitField0_ & 0x00000004) == 0x00000004)) {
                    to_bitField0_ |= 0x00000004;
                }
                result.avatar_ = avatar_;
                if (((from_bitField0_ & 0x00000008) == 0x00000008)) {
                    to_bitField0_ |= 0x00000008;
                }
                result.camp_ = camp_;
                if (((from_bitField0_ & 0x00000010) == 0x00000010)) {
                    to_bitField0_ |= 0x00000010;
                }
                result.level_ = level_;
                if (((from_bitField0_ & 0x00000020) == 0x00000020)) {
                    to_bitField0_ |= 0x00000020;
                }
                result.vip_ = vip_;
                result.bitField0_ = to_bitField0_;
                onBuilt();
                return result;
            }

            public Builder mergeFrom(com.google.protobuf.Message other) {
                if (other instanceof com.pkuvr.game_server.proto.serverproto.GS2C_Friend_Invite_Send.Friend_Invite_Send) {
                    return mergeFrom((com.pkuvr.game_server.proto.serverproto.GS2C_Friend_Invite_Send.Friend_Invite_Send) other);
                } else {
                    super.mergeFrom(other);
                    return this;
                }
            }

            public Builder mergeFrom(com.pkuvr.game_server.proto.serverproto.GS2C_Friend_Invite_Send.Friend_Invite_Send other) {
                if (other == com.pkuvr.game_server.proto.serverproto.GS2C_Friend_Invite_Send.Friend_Invite_Send.getDefaultInstance())
                    return this;
                if (other.hasErrorCode()) {
                    setErrorCode(other.getErrorCode());
                }
                if (other.hasName()) {
                    setName(other.getName());
                }
                if (other.hasAvatar()) {
                    setAvatar(other.getAvatar());
                }
                if (other.hasCamp()) {
                    setCamp(other.getCamp());
                }
                if (other.hasLevel()) {
                    setLevel(other.getLevel());
                }
                if (other.hasVip()) {
                    setVip(other.getVip());
                }
                this.mergeUnknownFields(other.getUnknownFields());
                return this;
            }

            public final boolean isInitialized() {
                if (!hasErrorCode()) {

                    return false;
                }
                if (!hasName()) {

                    return false;
                }
                if (!hasAvatar()) {

                    return false;
                }
                if (!hasCamp()) {

                    return false;
                }
                if (!hasLevel()) {

                    return false;
                }
                if (!hasVip()) {

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
                        case 18: {
                            bitField0_ |= 0x00000002;
                            name_ = input.readBytes();
                            break;
                        }
                        case 26: {
                            bitField0_ |= 0x00000004;
                            avatar_ = input.readBytes();
                            break;
                        }
                        case 32: {
                            bitField0_ |= 0x00000008;
                            camp_ = input.readInt32();
                            break;
                        }
                        case 40: {
                            bitField0_ |= 0x00000010;
                            level_ = input.readInt32();
                            break;
                        }
                        case 48: {
                            bitField0_ |= 0x00000020;
                            vip_ = input.readInt32();
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

            public boolean hasName() {
                return ((bitField0_ & 0x00000002) == 0x00000002);
            }

            public String getName() {
                Object ref = name_;
                if (!(ref instanceof String)) {
                    String s = ((com.google.protobuf.ByteString) ref).toStringUtf8();
                    name_ = s;
                    return s;
                } else {
                    return (String) ref;
                }
            }

            void setName(com.google.protobuf.ByteString value) {
                bitField0_ |= 0x00000002;
                name_ = value;
                onChanged();
            }

            public Builder setName(String value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                bitField0_ |= 0x00000002;
                name_ = value;
                onChanged();
                return this;
            }

            public Builder clearName() {
                bitField0_ = (bitField0_ & ~0x00000002);
                name_ = getDefaultInstance().getName();
                onChanged();
                return this;
            }

            public boolean hasAvatar() {
                return ((bitField0_ & 0x00000004) == 0x00000004);
            }

            public String getAvatar() {
                Object ref = avatar_;
                if (!(ref instanceof String)) {
                    String s = ((com.google.protobuf.ByteString) ref).toStringUtf8();
                    avatar_ = s;
                    return s;
                } else {
                    return (String) ref;
                }
            }

            void setAvatar(com.google.protobuf.ByteString value) {
                bitField0_ |= 0x00000004;
                avatar_ = value;
                onChanged();
            }

            public Builder setAvatar(String value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                bitField0_ |= 0x00000004;
                avatar_ = value;
                onChanged();
                return this;
            }

            public Builder clearAvatar() {
                bitField0_ = (bitField0_ & ~0x00000004);
                avatar_ = getDefaultInstance().getAvatar();
                onChanged();
                return this;
            }

            public boolean hasCamp() {
                return ((bitField0_ & 0x00000008) == 0x00000008);
            }

            public int getCamp() {
                return camp_;
            }

            public Builder setCamp(int value) {
                bitField0_ |= 0x00000008;
                camp_ = value;
                onChanged();
                return this;
            }

            public Builder clearCamp() {
                bitField0_ = (bitField0_ & ~0x00000008);
                camp_ = 0;
                onChanged();
                return this;
            }

            public boolean hasLevel() {
                return ((bitField0_ & 0x00000010) == 0x00000010);
            }

            public int getLevel() {
                return level_;
            }

            public Builder setLevel(int value) {
                bitField0_ |= 0x00000010;
                level_ = value;
                onChanged();
                return this;
            }

            public Builder clearLevel() {
                bitField0_ = (bitField0_ & ~0x00000010);
                level_ = 0;
                onChanged();
                return this;
            }

            public boolean hasVip() {
                return ((bitField0_ & 0x00000020) == 0x00000020);
            }

            public int getVip() {
                return vip_;
            }

            public Builder setVip(int value) {
                bitField0_ |= 0x00000020;
                vip_ = value;
                onChanged();
                return this;
            }

            public Builder clearVip() {
                bitField0_ = (bitField0_ & ~0x00000020);
                vip_ = 0;
                onChanged();
                return this;
            }

            // @@protoc_insertion_point(builder_scope:Friend_Invite_Send)
        }

        // @@protoc_insertion_point(class_scope:Friend_Invite_Send)
    }

    // @@protoc_insertion_point(outer_class_scope)
}
