// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: Pvp_Npc_Battle_Match_Res.proto

package com.pkuvr.game_server.proto.serverproto;

public final class Pvp_Npc_Battle_Match_Response {
    private static com.google.protobuf.Descriptors.Descriptor
            internal_static_Pvp_Npc_Battle_Match_Res_descriptor;
    private static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
            internal_static_Pvp_Npc_Battle_Match_Res_fieldAccessorTable;
    private static com.google.protobuf.Descriptors.FileDescriptor
            descriptor;

    static {
        String[] descriptorData = {
                "\n\036Pvp_Npc_Battle_Match_Res.proto\032\026BI_Pvp" +
                        "_Rank_Info.proto\"Q\n\030Pvp_Npc_Battle_Match" +
                        "_Res\022\021\n\terrorCode\030\001 \002(\005\022\"\n\nplayerRank\030\002 " +
                        "\001(\0132\016.Pvp_Rank_InfoBF\n%com.gfan.gameserv" +
                        "er.proto.serverprotoB\035Pvp_Npc_Battle_Mat" +
                        "ch_Response"
        };
        com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
                new com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner() {
                    public com.google.protobuf.ExtensionRegistry assignDescriptors(
                            com.google.protobuf.Descriptors.FileDescriptor root) {
                        descriptor = root;
                        internal_static_Pvp_Npc_Battle_Match_Res_descriptor =
                                getDescriptor().getMessageTypes().get(0);
                        internal_static_Pvp_Npc_Battle_Match_Res_fieldAccessorTable = new
                                com.google.protobuf.GeneratedMessage.FieldAccessorTable(
                                internal_static_Pvp_Npc_Battle_Match_Res_descriptor,
                                new String[]{"ErrorCode", "PlayerRank",},
                                com.pkuvr.game_server.proto.serverproto.Pvp_Npc_Battle_Match_Response.Pvp_Npc_Battle_Match_Res.class,
                                com.pkuvr.game_server.proto.serverproto.Pvp_Npc_Battle_Match_Response.Pvp_Npc_Battle_Match_Res.Builder.class);
                        return null;
                    }
                };
        com.google.protobuf.Descriptors.FileDescriptor
                .internalBuildGeneratedFileFrom(descriptorData,
                        new com.google.protobuf.Descriptors.FileDescriptor[]{
                                com.pkuvr.game_server.proto.commons.BI_Pvp_Rank_Info.getDescriptor(),
                        }, assigner);
    }

    private Pvp_Npc_Battle_Match_Response() {
    }

    public static void registerAllExtensions(
            com.google.protobuf.ExtensionRegistry registry) {
    }

    public static com.google.protobuf.Descriptors.FileDescriptor
    getDescriptor() {
        return descriptor;
    }

    public interface Pvp_Npc_Battle_Match_ResOrBuilder
            extends com.google.protobuf.MessageOrBuilder {

        // required int32 errorCode = 1;
        boolean hasErrorCode();

        int getErrorCode();

        // optional .Pvp_Rank_Info playerRank = 2;
        boolean hasPlayerRank();

        com.pkuvr.game_server.proto.commons.BI_Pvp_Rank_Info.Pvp_Rank_Info getPlayerRank();

        com.pkuvr.game_server.proto.commons.BI_Pvp_Rank_Info.Pvp_Rank_InfoOrBuilder getPlayerRankOrBuilder();
    }

    public static final class Pvp_Npc_Battle_Match_Res extends
            com.google.protobuf.GeneratedMessage
            implements Pvp_Npc_Battle_Match_ResOrBuilder {
        // required int32 errorCode = 1;
        public static final int ERRORCODE_FIELD_NUMBER = 1;
        // optional .Pvp_Rank_Info playerRank = 2;
        public static final int PLAYERRANK_FIELD_NUMBER = 2;
        private static final Pvp_Npc_Battle_Match_Res defaultInstance;
        private static final long serialVersionUID = 0L;

        static {
            defaultInstance = new Pvp_Npc_Battle_Match_Res(true);
            defaultInstance.initFields();
        }

        private int bitField0_;
        private int errorCode_;
        private com.pkuvr.game_server.proto.commons.BI_Pvp_Rank_Info.Pvp_Rank_Info playerRank_;
        private byte memoizedIsInitialized = -1;
        private int memoizedSerializedSize = -1;

        // Use Pvp_Npc_Battle_Match_Res.newBuilder() to construct.
        private Pvp_Npc_Battle_Match_Res(Builder builder) {
            super(builder);
        }

        private Pvp_Npc_Battle_Match_Res(boolean noInit) {
        }

        public static Pvp_Npc_Battle_Match_Res getDefaultInstance() {
            return defaultInstance;
        }

        public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
            return com.pkuvr.game_server.proto.serverproto.Pvp_Npc_Battle_Match_Response.internal_static_Pvp_Npc_Battle_Match_Res_descriptor;
        }

        public static com.pkuvr.game_server.proto.serverproto.Pvp_Npc_Battle_Match_Response.Pvp_Npc_Battle_Match_Res parseFrom(
                com.google.protobuf.ByteString data)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return newBuilder().mergeFrom(data).buildParsed();
        }

        public static com.pkuvr.game_server.proto.serverproto.Pvp_Npc_Battle_Match_Response.Pvp_Npc_Battle_Match_Res parseFrom(
                com.google.protobuf.ByteString data,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return newBuilder().mergeFrom(data, extensionRegistry)
                    .buildParsed();
        }

        public static com.pkuvr.game_server.proto.serverproto.Pvp_Npc_Battle_Match_Response.Pvp_Npc_Battle_Match_Res parseFrom(byte[] data)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return newBuilder().mergeFrom(data).buildParsed();
        }

        public static com.pkuvr.game_server.proto.serverproto.Pvp_Npc_Battle_Match_Response.Pvp_Npc_Battle_Match_Res parseFrom(
                byte[] data,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return newBuilder().mergeFrom(data, extensionRegistry)
                    .buildParsed();
        }

        public static com.pkuvr.game_server.proto.serverproto.Pvp_Npc_Battle_Match_Response.Pvp_Npc_Battle_Match_Res parseFrom(java.io.InputStream input)
                throws java.io.IOException {
            return newBuilder().mergeFrom(input).buildParsed();
        }

        public static com.pkuvr.game_server.proto.serverproto.Pvp_Npc_Battle_Match_Response.Pvp_Npc_Battle_Match_Res parseFrom(
                java.io.InputStream input,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws java.io.IOException {
            return newBuilder().mergeFrom(input, extensionRegistry)
                    .buildParsed();
        }

        public static com.pkuvr.game_server.proto.serverproto.Pvp_Npc_Battle_Match_Response.Pvp_Npc_Battle_Match_Res parseDelimitedFrom(java.io.InputStream input)
                throws java.io.IOException {
            Builder builder = newBuilder();
            if (builder.mergeDelimitedFrom(input)) {
                return builder.buildParsed();
            } else {
                return null;
            }
        }

        public static com.pkuvr.game_server.proto.serverproto.Pvp_Npc_Battle_Match_Response.Pvp_Npc_Battle_Match_Res parseDelimitedFrom(
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

        public static com.pkuvr.game_server.proto.serverproto.Pvp_Npc_Battle_Match_Response.Pvp_Npc_Battle_Match_Res parseFrom(
                com.google.protobuf.CodedInputStream input)
                throws java.io.IOException {
            return newBuilder().mergeFrom(input).buildParsed();
        }

        public static com.pkuvr.game_server.proto.serverproto.Pvp_Npc_Battle_Match_Response.Pvp_Npc_Battle_Match_Res parseFrom(
                com.google.protobuf.CodedInputStream input,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws java.io.IOException {
            return newBuilder().mergeFrom(input, extensionRegistry)
                    .buildParsed();
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        public static Builder newBuilder(com.pkuvr.game_server.proto.serverproto.Pvp_Npc_Battle_Match_Response.Pvp_Npc_Battle_Match_Res prototype) {
            return newBuilder().mergeFrom(prototype);
        }

        public Pvp_Npc_Battle_Match_Res getDefaultInstanceForType() {
            return defaultInstance;
        }

        protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
            return com.pkuvr.game_server.proto.serverproto.Pvp_Npc_Battle_Match_Response.internal_static_Pvp_Npc_Battle_Match_Res_fieldAccessorTable;
        }

        public boolean hasErrorCode() {
            return ((bitField0_ & 0x00000001) == 0x00000001);
        }

        public int getErrorCode() {
            return errorCode_;
        }

        public boolean hasPlayerRank() {
            return ((bitField0_ & 0x00000002) == 0x00000002);
        }

        public com.pkuvr.game_server.proto.commons.BI_Pvp_Rank_Info.Pvp_Rank_Info getPlayerRank() {
            return playerRank_;
        }

        public com.pkuvr.game_server.proto.commons.BI_Pvp_Rank_Info.Pvp_Rank_InfoOrBuilder getPlayerRankOrBuilder() {
            return playerRank_;
        }

        private void initFields() {
            errorCode_ = 0;
            playerRank_ = com.pkuvr.game_server.proto.commons.BI_Pvp_Rank_Info.Pvp_Rank_Info.getDefaultInstance();
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
                output.writeMessage(2, playerRank_);
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
                        .computeMessageSize(2, playerRank_);
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
                implements com.pkuvr.game_server.proto.serverproto.Pvp_Npc_Battle_Match_Response.Pvp_Npc_Battle_Match_ResOrBuilder {
            private int bitField0_;
            // required int32 errorCode = 1;
            private int errorCode_;
            // optional .Pvp_Rank_Info playerRank = 2;
            private com.pkuvr.game_server.proto.commons.BI_Pvp_Rank_Info.Pvp_Rank_Info playerRank_ = com.pkuvr.game_server.proto.commons.BI_Pvp_Rank_Info.Pvp_Rank_Info.getDefaultInstance();
            private com.google.protobuf.SingleFieldBuilder<
                    com.pkuvr.game_server.proto.commons.BI_Pvp_Rank_Info.Pvp_Rank_Info, com.pkuvr.game_server.proto.commons.BI_Pvp_Rank_Info.Pvp_Rank_Info.Builder, com.pkuvr.game_server.proto.commons.BI_Pvp_Rank_Info.Pvp_Rank_InfoOrBuilder> playerRankBuilder_;

            // Construct using com.pkuvr.game_server.proto.serverproto.Pvp_Npc_Battle_Match_Response.Pvp_Npc_Battle_Match_Res.newBuilder()
            private Builder() {
                maybeForceBuilderInitialization();
            }

            private Builder(BuilderParent parent) {
                super(parent);
                maybeForceBuilderInitialization();
            }

            public static final com.google.protobuf.Descriptors.Descriptor
            getDescriptor() {
                return com.pkuvr.game_server.proto.serverproto.Pvp_Npc_Battle_Match_Response.internal_static_Pvp_Npc_Battle_Match_Res_descriptor;
            }

            private static Builder create() {
                return new Builder();
            }

            protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
            internalGetFieldAccessorTable() {
                return com.pkuvr.game_server.proto.serverproto.Pvp_Npc_Battle_Match_Response.internal_static_Pvp_Npc_Battle_Match_Res_fieldAccessorTable;
            }

            private void maybeForceBuilderInitialization() {
                if (com.google.protobuf.GeneratedMessage.alwaysUseFieldBuilders) {
                    getPlayerRankFieldBuilder();
                }
            }

            public Builder clear() {
                super.clear();
                errorCode_ = 0;
                bitField0_ = (bitField0_ & ~0x00000001);
                if (playerRankBuilder_ == null) {
                    playerRank_ = com.pkuvr.game_server.proto.commons.BI_Pvp_Rank_Info.Pvp_Rank_Info.getDefaultInstance();
                } else {
                    playerRankBuilder_.clear();
                }
                bitField0_ = (bitField0_ & ~0x00000002);
                return this;
            }

            public Builder clone() {
                return create().mergeFrom(buildPartial());
            }

            public com.google.protobuf.Descriptors.Descriptor
            getDescriptorForType() {
                return com.pkuvr.game_server.proto.serverproto.Pvp_Npc_Battle_Match_Response.Pvp_Npc_Battle_Match_Res.getDescriptor();
            }

            public com.pkuvr.game_server.proto.serverproto.Pvp_Npc_Battle_Match_Response.Pvp_Npc_Battle_Match_Res getDefaultInstanceForType() {
                return com.pkuvr.game_server.proto.serverproto.Pvp_Npc_Battle_Match_Response.Pvp_Npc_Battle_Match_Res.getDefaultInstance();
            }

            public com.pkuvr.game_server.proto.serverproto.Pvp_Npc_Battle_Match_Response.Pvp_Npc_Battle_Match_Res build() {
                com.pkuvr.game_server.proto.serverproto.Pvp_Npc_Battle_Match_Response.Pvp_Npc_Battle_Match_Res result = buildPartial();
                if (!result.isInitialized()) {
                    throw newUninitializedMessageException(result);
                }
                return result;
            }

            private com.pkuvr.game_server.proto.serverproto.Pvp_Npc_Battle_Match_Response.Pvp_Npc_Battle_Match_Res buildParsed()
                    throws com.google.protobuf.InvalidProtocolBufferException {
                com.pkuvr.game_server.proto.serverproto.Pvp_Npc_Battle_Match_Response.Pvp_Npc_Battle_Match_Res result = buildPartial();
                if (!result.isInitialized()) {
                    throw newUninitializedMessageException(
                            result).asInvalidProtocolBufferException();
                }
                return result;
            }

            public com.pkuvr.game_server.proto.serverproto.Pvp_Npc_Battle_Match_Response.Pvp_Npc_Battle_Match_Res buildPartial() {
                com.pkuvr.game_server.proto.serverproto.Pvp_Npc_Battle_Match_Response.Pvp_Npc_Battle_Match_Res result = new com.pkuvr.game_server.proto.serverproto.Pvp_Npc_Battle_Match_Response.Pvp_Npc_Battle_Match_Res(this);
                int from_bitField0_ = bitField0_;
                int to_bitField0_ = 0;
                if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
                    to_bitField0_ |= 0x00000001;
                }
                result.errorCode_ = errorCode_;
                if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
                    to_bitField0_ |= 0x00000002;
                }
                if (playerRankBuilder_ == null) {
                    result.playerRank_ = playerRank_;
                } else {
                    result.playerRank_ = playerRankBuilder_.build();
                }
                result.bitField0_ = to_bitField0_;
                onBuilt();
                return result;
            }

            public Builder mergeFrom(com.google.protobuf.Message other) {
                if (other instanceof com.pkuvr.game_server.proto.serverproto.Pvp_Npc_Battle_Match_Response.Pvp_Npc_Battle_Match_Res) {
                    return mergeFrom((com.pkuvr.game_server.proto.serverproto.Pvp_Npc_Battle_Match_Response.Pvp_Npc_Battle_Match_Res) other);
                } else {
                    super.mergeFrom(other);
                    return this;
                }
            }

            public Builder mergeFrom(com.pkuvr.game_server.proto.serverproto.Pvp_Npc_Battle_Match_Response.Pvp_Npc_Battle_Match_Res other) {
                if (other == com.pkuvr.game_server.proto.serverproto.Pvp_Npc_Battle_Match_Response.Pvp_Npc_Battle_Match_Res.getDefaultInstance())
                    return this;
                if (other.hasErrorCode()) {
                    setErrorCode(other.getErrorCode());
                }
                if (other.hasPlayerRank()) {
                    mergePlayerRank(other.getPlayerRank());
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
                        case 18: {
                            com.pkuvr.game_server.proto.commons.BI_Pvp_Rank_Info.Pvp_Rank_Info.Builder subBuilder = com.pkuvr.game_server.proto.commons.BI_Pvp_Rank_Info.Pvp_Rank_Info.newBuilder();
                            if (hasPlayerRank()) {
                                subBuilder.mergeFrom(getPlayerRank());
                            }
                            input.readMessage(subBuilder, extensionRegistry);
                            setPlayerRank(subBuilder.buildPartial());
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

            public boolean hasPlayerRank() {
                return ((bitField0_ & 0x00000002) == 0x00000002);
            }

            public com.pkuvr.game_server.proto.commons.BI_Pvp_Rank_Info.Pvp_Rank_Info getPlayerRank() {
                if (playerRankBuilder_ == null) {
                    return playerRank_;
                } else {
                    return playerRankBuilder_.getMessage();
                }
            }

            public Builder setPlayerRank(
                    com.pkuvr.game_server.proto.commons.BI_Pvp_Rank_Info.Pvp_Rank_Info.Builder builderForValue) {
                if (playerRankBuilder_ == null) {
                    playerRank_ = builderForValue.build();
                    onChanged();
                } else {
                    playerRankBuilder_.setMessage(builderForValue.build());
                }
                bitField0_ |= 0x00000002;
                return this;
            }

            public Builder setPlayerRank(com.pkuvr.game_server.proto.commons.BI_Pvp_Rank_Info.Pvp_Rank_Info value) {
                if (playerRankBuilder_ == null) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    playerRank_ = value;
                    onChanged();
                } else {
                    playerRankBuilder_.setMessage(value);
                }
                bitField0_ |= 0x00000002;
                return this;
            }

            public Builder mergePlayerRank(com.pkuvr.game_server.proto.commons.BI_Pvp_Rank_Info.Pvp_Rank_Info value) {
                if (playerRankBuilder_ == null) {
                    if (((bitField0_ & 0x00000002) == 0x00000002) &&
                            playerRank_ != com.pkuvr.game_server.proto.commons.BI_Pvp_Rank_Info.Pvp_Rank_Info.getDefaultInstance()) {
                        playerRank_ =
                                com.pkuvr.game_server.proto.commons.BI_Pvp_Rank_Info.Pvp_Rank_Info.newBuilder(playerRank_).mergeFrom(value).buildPartial();
                    } else {
                        playerRank_ = value;
                    }
                    onChanged();
                } else {
                    playerRankBuilder_.mergeFrom(value);
                }
                bitField0_ |= 0x00000002;
                return this;
            }

            public Builder clearPlayerRank() {
                if (playerRankBuilder_ == null) {
                    playerRank_ = com.pkuvr.game_server.proto.commons.BI_Pvp_Rank_Info.Pvp_Rank_Info.getDefaultInstance();
                    onChanged();
                } else {
                    playerRankBuilder_.clear();
                }
                bitField0_ = (bitField0_ & ~0x00000002);
                return this;
            }

            public com.pkuvr.game_server.proto.commons.BI_Pvp_Rank_Info.Pvp_Rank_Info.Builder getPlayerRankBuilder() {
                bitField0_ |= 0x00000002;
                onChanged();
                return getPlayerRankFieldBuilder().getBuilder();
            }

            public com.pkuvr.game_server.proto.commons.BI_Pvp_Rank_Info.Pvp_Rank_InfoOrBuilder getPlayerRankOrBuilder() {
                if (playerRankBuilder_ != null) {
                    return playerRankBuilder_.getMessageOrBuilder();
                } else {
                    return playerRank_;
                }
            }

            private com.google.protobuf.SingleFieldBuilder<
                    com.pkuvr.game_server.proto.commons.BI_Pvp_Rank_Info.Pvp_Rank_Info, com.pkuvr.game_server.proto.commons.BI_Pvp_Rank_Info.Pvp_Rank_Info.Builder, com.pkuvr.game_server.proto.commons.BI_Pvp_Rank_Info.Pvp_Rank_InfoOrBuilder>
            getPlayerRankFieldBuilder() {
                if (playerRankBuilder_ == null) {
                    playerRankBuilder_ = new com.google.protobuf.SingleFieldBuilder<
                            com.pkuvr.game_server.proto.commons.BI_Pvp_Rank_Info.Pvp_Rank_Info, com.pkuvr.game_server.proto.commons.BI_Pvp_Rank_Info.Pvp_Rank_Info.Builder, com.pkuvr.game_server.proto.commons.BI_Pvp_Rank_Info.Pvp_Rank_InfoOrBuilder>(
                            playerRank_,
                            getParentForChildren(),
                            isClean());
                    playerRank_ = null;
                }
                return playerRankBuilder_;
            }

            // @@protoc_insertion_point(builder_scope:Pvp_Npc_Battle_Match_Res)
        }

        // @@protoc_insertion_point(class_scope:Pvp_Npc_Battle_Match_Res)
    }

    // @@protoc_insertion_point(outer_class_scope)
}
