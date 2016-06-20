// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: BI_Pvp_Rank_Info.proto

package com.pkuvr.game_server.proto.commons;

public final class BI_Pvp_Rank_Info {
    private static com.google.protobuf.Descriptors.Descriptor
            internal_static_Pvp_Rank_Info_descriptor;
    private static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
            internal_static_Pvp_Rank_Info_fieldAccessorTable;
    private static com.google.protobuf.Descriptors.FileDescriptor
            descriptor;

    static {
        String[] descriptorData = {
                "\n\026BI_Pvp_Rank_Info.proto\"\210\001\n\rPvp_Rank_In" +
                        "fo\022\017\n\007pvpRank\030\001 \001(\005\022\016\n\006roleId\030\002 \001(\005\022\020\n\010r" +
                        "oleName\030\003 \001(\t\022\016\n\006campId\030\004 \001(\005\022\016\n\006avatar\030" +
                        "\005 \001(\t\022\025\n\rgeneralDegree\030\006 \001(\005\022\r\n\005honor\030\007 " +
                        "\001(\005B5\n!com.pkuvr.game_server.proto.commons" +
                        "B\020BI_Pvp_Rank_Info"
        };
        com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
                new com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner() {
                    public com.google.protobuf.ExtensionRegistry assignDescriptors(
                            com.google.protobuf.Descriptors.FileDescriptor root) {
                        descriptor = root;
                        internal_static_Pvp_Rank_Info_descriptor =
                                getDescriptor().getMessageTypes().get(0);
                        internal_static_Pvp_Rank_Info_fieldAccessorTable = new
                                com.google.protobuf.GeneratedMessage.FieldAccessorTable(
                                internal_static_Pvp_Rank_Info_descriptor,
                                new String[]{"PvpRank", "RoleId", "RoleName", "CampId", "Avatar", "GeneralDegree", "Honor",},
                                com.pkuvr.game_server.proto.commons.BI_Pvp_Rank_Info.Pvp_Rank_Info.class,
                                com.pkuvr.game_server.proto.commons.BI_Pvp_Rank_Info.Pvp_Rank_Info.Builder.class);
                        return null;
                    }
                };
        com.google.protobuf.Descriptors.FileDescriptor
                .internalBuildGeneratedFileFrom(descriptorData,
                        new com.google.protobuf.Descriptors.FileDescriptor[]{
                        }, assigner);
    }

    private BI_Pvp_Rank_Info() {
    }

    public static void registerAllExtensions(
            com.google.protobuf.ExtensionRegistry registry) {
    }

    public static com.google.protobuf.Descriptors.FileDescriptor
    getDescriptor() {
        return descriptor;
    }

    public interface Pvp_Rank_InfoOrBuilder
            extends com.google.protobuf.MessageOrBuilder {

        // optional int32 pvpRank = 1;
        boolean hasPvpRank();

        int getPvpRank();

        // optional int32 roleId = 2;
        boolean hasRoleId();

        int getRoleId();

        // optional string roleName = 3;
        boolean hasRoleName();

        String getRoleName();

        // optional int32 campId = 4;
        boolean hasCampId();

        int getCampId();

        // optional string avatar = 5;
        boolean hasAvatar();

        String getAvatar();

        // optional int32 generalDegree = 6;
        boolean hasGeneralDegree();

        int getGeneralDegree();

        // optional int32 honor = 7;
        boolean hasHonor();

        int getHonor();
    }

    public static final class Pvp_Rank_Info extends
            com.google.protobuf.GeneratedMessage
            implements Pvp_Rank_InfoOrBuilder {
        // optional int32 pvpRank = 1;
        public static final int PVPRANK_FIELD_NUMBER = 1;
        // optional int32 roleId = 2;
        public static final int ROLEID_FIELD_NUMBER = 2;
        // optional string roleName = 3;
        public static final int ROLENAME_FIELD_NUMBER = 3;
        // optional int32 campId = 4;
        public static final int CAMPID_FIELD_NUMBER = 4;
        // optional string avatar = 5;
        public static final int AVATAR_FIELD_NUMBER = 5;
        // optional int32 generalDegree = 6;
        public static final int GENERALDEGREE_FIELD_NUMBER = 6;
        // optional int32 honor = 7;
        public static final int HONOR_FIELD_NUMBER = 7;
        private static final Pvp_Rank_Info defaultInstance;
        private static final long serialVersionUID = 0L;

        static {
            defaultInstance = new Pvp_Rank_Info(true);
            defaultInstance.initFields();
        }

        private int bitField0_;
        private int pvpRank_;
        private int roleId_;
        private Object roleName_;
        private int campId_;
        private Object avatar_;
        private int generalDegree_;
        private int honor_;
        private byte memoizedIsInitialized = -1;
        private int memoizedSerializedSize = -1;

        // Use Pvp_Rank_Info.newBuilder() to construct.
        private Pvp_Rank_Info(Builder builder) {
            super(builder);
        }

        private Pvp_Rank_Info(boolean noInit) {
        }

        public static Pvp_Rank_Info getDefaultInstance() {
            return defaultInstance;
        }

        public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
            return com.pkuvr.game_server.proto.commons.BI_Pvp_Rank_Info.internal_static_Pvp_Rank_Info_descriptor;
        }

        public static com.pkuvr.game_server.proto.commons.BI_Pvp_Rank_Info.Pvp_Rank_Info parseFrom(
                com.google.protobuf.ByteString data)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return newBuilder().mergeFrom(data).buildParsed();
        }

        public static com.pkuvr.game_server.proto.commons.BI_Pvp_Rank_Info.Pvp_Rank_Info parseFrom(
                com.google.protobuf.ByteString data,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return newBuilder().mergeFrom(data, extensionRegistry)
                    .buildParsed();
        }

        public static com.pkuvr.game_server.proto.commons.BI_Pvp_Rank_Info.Pvp_Rank_Info parseFrom(byte[] data)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return newBuilder().mergeFrom(data).buildParsed();
        }

        public static com.pkuvr.game_server.proto.commons.BI_Pvp_Rank_Info.Pvp_Rank_Info parseFrom(
                byte[] data,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return newBuilder().mergeFrom(data, extensionRegistry)
                    .buildParsed();
        }

        public static com.pkuvr.game_server.proto.commons.BI_Pvp_Rank_Info.Pvp_Rank_Info parseFrom(java.io.InputStream input)
                throws java.io.IOException {
            return newBuilder().mergeFrom(input).buildParsed();
        }

        public static com.pkuvr.game_server.proto.commons.BI_Pvp_Rank_Info.Pvp_Rank_Info parseFrom(
                java.io.InputStream input,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws java.io.IOException {
            return newBuilder().mergeFrom(input, extensionRegistry)
                    .buildParsed();
        }

        public static com.pkuvr.game_server.proto.commons.BI_Pvp_Rank_Info.Pvp_Rank_Info parseDelimitedFrom(java.io.InputStream input)
                throws java.io.IOException {
            Builder builder = newBuilder();
            if (builder.mergeDelimitedFrom(input)) {
                return builder.buildParsed();
            } else {
                return null;
            }
        }

        public static com.pkuvr.game_server.proto.commons.BI_Pvp_Rank_Info.Pvp_Rank_Info parseDelimitedFrom(
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

        public static com.pkuvr.game_server.proto.commons.BI_Pvp_Rank_Info.Pvp_Rank_Info parseFrom(
                com.google.protobuf.CodedInputStream input)
                throws java.io.IOException {
            return newBuilder().mergeFrom(input).buildParsed();
        }

        public static com.pkuvr.game_server.proto.commons.BI_Pvp_Rank_Info.Pvp_Rank_Info parseFrom(
                com.google.protobuf.CodedInputStream input,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws java.io.IOException {
            return newBuilder().mergeFrom(input, extensionRegistry)
                    .buildParsed();
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        public static Builder newBuilder(com.pkuvr.game_server.proto.commons.BI_Pvp_Rank_Info.Pvp_Rank_Info prototype) {
            return newBuilder().mergeFrom(prototype);
        }

        public Pvp_Rank_Info getDefaultInstanceForType() {
            return defaultInstance;
        }

        protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
            return com.pkuvr.game_server.proto.commons.BI_Pvp_Rank_Info.internal_static_Pvp_Rank_Info_fieldAccessorTable;
        }

        public boolean hasPvpRank() {
            return ((bitField0_ & 0x00000001) == 0x00000001);
        }

        public int getPvpRank() {
            return pvpRank_;
        }

        public boolean hasRoleId() {
            return ((bitField0_ & 0x00000002) == 0x00000002);
        }

        public int getRoleId() {
            return roleId_;
        }

        public boolean hasRoleName() {
            return ((bitField0_ & 0x00000004) == 0x00000004);
        }

        public String getRoleName() {
            Object ref = roleName_;
            if (ref instanceof String) {
                return (String) ref;
            } else {
                com.google.protobuf.ByteString bs =
                        (com.google.protobuf.ByteString) ref;
                String s = bs.toStringUtf8();
                if (com.google.protobuf.Internal.isValidUtf8(bs)) {
                    roleName_ = s;
                }
                return s;
            }
        }

        private com.google.protobuf.ByteString getRoleNameBytes() {
            Object ref = roleName_;
            if (ref instanceof String) {
                com.google.protobuf.ByteString b =
                        com.google.protobuf.ByteString.copyFromUtf8((String) ref);
                roleName_ = b;
                return b;
            } else {
                return (com.google.protobuf.ByteString) ref;
            }
        }

        public boolean hasCampId() {
            return ((bitField0_ & 0x00000008) == 0x00000008);
        }

        public int getCampId() {
            return campId_;
        }

        public boolean hasAvatar() {
            return ((bitField0_ & 0x00000010) == 0x00000010);
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

        public boolean hasGeneralDegree() {
            return ((bitField0_ & 0x00000020) == 0x00000020);
        }

        public int getGeneralDegree() {
            return generalDegree_;
        }

        public boolean hasHonor() {
            return ((bitField0_ & 0x00000040) == 0x00000040);
        }

        public int getHonor() {
            return honor_;
        }

        private void initFields() {
            pvpRank_ = 0;
            roleId_ = 0;
            roleName_ = "";
            campId_ = 0;
            avatar_ = "";
            generalDegree_ = 0;
            honor_ = 0;
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
                output.writeInt32(1, pvpRank_);
            }
            if (((bitField0_ & 0x00000002) == 0x00000002)) {
                output.writeInt32(2, roleId_);
            }
            if (((bitField0_ & 0x00000004) == 0x00000004)) {
                output.writeBytes(3, getRoleNameBytes());
            }
            if (((bitField0_ & 0x00000008) == 0x00000008)) {
                output.writeInt32(4, campId_);
            }
            if (((bitField0_ & 0x00000010) == 0x00000010)) {
                output.writeBytes(5, getAvatarBytes());
            }
            if (((bitField0_ & 0x00000020) == 0x00000020)) {
                output.writeInt32(6, generalDegree_);
            }
            if (((bitField0_ & 0x00000040) == 0x00000040)) {
                output.writeInt32(7, honor_);
            }
            getUnknownFields().writeTo(output);
        }

        public int getSerializedSize() {
            int size = memoizedSerializedSize;
            if (size != -1) return size;

            size = 0;
            if (((bitField0_ & 0x00000001) == 0x00000001)) {
                size += com.google.protobuf.CodedOutputStream
                        .computeInt32Size(1, pvpRank_);
            }
            if (((bitField0_ & 0x00000002) == 0x00000002)) {
                size += com.google.protobuf.CodedOutputStream
                        .computeInt32Size(2, roleId_);
            }
            if (((bitField0_ & 0x00000004) == 0x00000004)) {
                size += com.google.protobuf.CodedOutputStream
                        .computeBytesSize(3, getRoleNameBytes());
            }
            if (((bitField0_ & 0x00000008) == 0x00000008)) {
                size += com.google.protobuf.CodedOutputStream
                        .computeInt32Size(4, campId_);
            }
            if (((bitField0_ & 0x00000010) == 0x00000010)) {
                size += com.google.protobuf.CodedOutputStream
                        .computeBytesSize(5, getAvatarBytes());
            }
            if (((bitField0_ & 0x00000020) == 0x00000020)) {
                size += com.google.protobuf.CodedOutputStream
                        .computeInt32Size(6, generalDegree_);
            }
            if (((bitField0_ & 0x00000040) == 0x00000040)) {
                size += com.google.protobuf.CodedOutputStream
                        .computeInt32Size(7, honor_);
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
                implements com.pkuvr.game_server.proto.commons.BI_Pvp_Rank_Info.Pvp_Rank_InfoOrBuilder {
            private int bitField0_;
            // optional int32 pvpRank = 1;
            private int pvpRank_;
            // optional int32 roleId = 2;
            private int roleId_;
            // optional string roleName = 3;
            private Object roleName_ = "";
            // optional int32 campId = 4;
            private int campId_;
            // optional string avatar = 5;
            private Object avatar_ = "";
            // optional int32 generalDegree = 6;
            private int generalDegree_;
            // optional int32 honor = 7;
            private int honor_;

            // Construct using com.pkuvr.game_server.proto.commons.BI_Pvp_Rank_Info.Pvp_Rank_Info.newBuilder()
            private Builder() {
                maybeForceBuilderInitialization();
            }

            private Builder(BuilderParent parent) {
                super(parent);
                maybeForceBuilderInitialization();
            }

            public static final com.google.protobuf.Descriptors.Descriptor
            getDescriptor() {
                return com.pkuvr.game_server.proto.commons.BI_Pvp_Rank_Info.internal_static_Pvp_Rank_Info_descriptor;
            }

            private static Builder create() {
                return new Builder();
            }

            protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
            internalGetFieldAccessorTable() {
                return com.pkuvr.game_server.proto.commons.BI_Pvp_Rank_Info.internal_static_Pvp_Rank_Info_fieldAccessorTable;
            }

            private void maybeForceBuilderInitialization() {
                if (com.google.protobuf.GeneratedMessage.alwaysUseFieldBuilders) {
                }
            }

            public Builder clear() {
                super.clear();
                pvpRank_ = 0;
                bitField0_ = (bitField0_ & ~0x00000001);
                roleId_ = 0;
                bitField0_ = (bitField0_ & ~0x00000002);
                roleName_ = "";
                bitField0_ = (bitField0_ & ~0x00000004);
                campId_ = 0;
                bitField0_ = (bitField0_ & ~0x00000008);
                avatar_ = "";
                bitField0_ = (bitField0_ & ~0x00000010);
                generalDegree_ = 0;
                bitField0_ = (bitField0_ & ~0x00000020);
                honor_ = 0;
                bitField0_ = (bitField0_ & ~0x00000040);
                return this;
            }

            public Builder clone() {
                return create().mergeFrom(buildPartial());
            }

            public com.google.protobuf.Descriptors.Descriptor
            getDescriptorForType() {
                return com.pkuvr.game_server.proto.commons.BI_Pvp_Rank_Info.Pvp_Rank_Info.getDescriptor();
            }

            public com.pkuvr.game_server.proto.commons.BI_Pvp_Rank_Info.Pvp_Rank_Info getDefaultInstanceForType() {
                return com.pkuvr.game_server.proto.commons.BI_Pvp_Rank_Info.Pvp_Rank_Info.getDefaultInstance();
            }

            public com.pkuvr.game_server.proto.commons.BI_Pvp_Rank_Info.Pvp_Rank_Info build() {
                com.pkuvr.game_server.proto.commons.BI_Pvp_Rank_Info.Pvp_Rank_Info result = buildPartial();
                if (!result.isInitialized()) {
                    throw newUninitializedMessageException(result);
                }
                return result;
            }

            private com.pkuvr.game_server.proto.commons.BI_Pvp_Rank_Info.Pvp_Rank_Info buildParsed()
                    throws com.google.protobuf.InvalidProtocolBufferException {
                com.pkuvr.game_server.proto.commons.BI_Pvp_Rank_Info.Pvp_Rank_Info result = buildPartial();
                if (!result.isInitialized()) {
                    throw newUninitializedMessageException(
                            result).asInvalidProtocolBufferException();
                }
                return result;
            }

            public com.pkuvr.game_server.proto.commons.BI_Pvp_Rank_Info.Pvp_Rank_Info buildPartial() {
                com.pkuvr.game_server.proto.commons.BI_Pvp_Rank_Info.Pvp_Rank_Info result = new com.pkuvr.game_server.proto.commons.BI_Pvp_Rank_Info.Pvp_Rank_Info(this);
                int from_bitField0_ = bitField0_;
                int to_bitField0_ = 0;
                if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
                    to_bitField0_ |= 0x00000001;
                }
                result.pvpRank_ = pvpRank_;
                if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
                    to_bitField0_ |= 0x00000002;
                }
                result.roleId_ = roleId_;
                if (((from_bitField0_ & 0x00000004) == 0x00000004)) {
                    to_bitField0_ |= 0x00000004;
                }
                result.roleName_ = roleName_;
                if (((from_bitField0_ & 0x00000008) == 0x00000008)) {
                    to_bitField0_ |= 0x00000008;
                }
                result.campId_ = campId_;
                if (((from_bitField0_ & 0x00000010) == 0x00000010)) {
                    to_bitField0_ |= 0x00000010;
                }
                result.avatar_ = avatar_;
                if (((from_bitField0_ & 0x00000020) == 0x00000020)) {
                    to_bitField0_ |= 0x00000020;
                }
                result.generalDegree_ = generalDegree_;
                if (((from_bitField0_ & 0x00000040) == 0x00000040)) {
                    to_bitField0_ |= 0x00000040;
                }
                result.honor_ = honor_;
                result.bitField0_ = to_bitField0_;
                onBuilt();
                return result;
            }

            public Builder mergeFrom(com.google.protobuf.Message other) {
                if (other instanceof com.pkuvr.game_server.proto.commons.BI_Pvp_Rank_Info.Pvp_Rank_Info) {
                    return mergeFrom((com.pkuvr.game_server.proto.commons.BI_Pvp_Rank_Info.Pvp_Rank_Info) other);
                } else {
                    super.mergeFrom(other);
                    return this;
                }
            }

            public Builder mergeFrom(com.pkuvr.game_server.proto.commons.BI_Pvp_Rank_Info.Pvp_Rank_Info other) {
                if (other == com.pkuvr.game_server.proto.commons.BI_Pvp_Rank_Info.Pvp_Rank_Info.getDefaultInstance())
                    return this;
                if (other.hasPvpRank()) {
                    setPvpRank(other.getPvpRank());
                }
                if (other.hasRoleId()) {
                    setRoleId(other.getRoleId());
                }
                if (other.hasRoleName()) {
                    setRoleName(other.getRoleName());
                }
                if (other.hasCampId()) {
                    setCampId(other.getCampId());
                }
                if (other.hasAvatar()) {
                    setAvatar(other.getAvatar());
                }
                if (other.hasGeneralDegree()) {
                    setGeneralDegree(other.getGeneralDegree());
                }
                if (other.hasHonor()) {
                    setHonor(other.getHonor());
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
                            pvpRank_ = input.readInt32();
                            break;
                        }
                        case 16: {
                            bitField0_ |= 0x00000002;
                            roleId_ = input.readInt32();
                            break;
                        }
                        case 26: {
                            bitField0_ |= 0x00000004;
                            roleName_ = input.readBytes();
                            break;
                        }
                        case 32: {
                            bitField0_ |= 0x00000008;
                            campId_ = input.readInt32();
                            break;
                        }
                        case 42: {
                            bitField0_ |= 0x00000010;
                            avatar_ = input.readBytes();
                            break;
                        }
                        case 48: {
                            bitField0_ |= 0x00000020;
                            generalDegree_ = input.readInt32();
                            break;
                        }
                        case 56: {
                            bitField0_ |= 0x00000040;
                            honor_ = input.readInt32();
                            break;
                        }
                    }
                }
            }

            public boolean hasPvpRank() {
                return ((bitField0_ & 0x00000001) == 0x00000001);
            }

            public int getPvpRank() {
                return pvpRank_;
            }

            public Builder setPvpRank(int value) {
                bitField0_ |= 0x00000001;
                pvpRank_ = value;
                onChanged();
                return this;
            }

            public Builder clearPvpRank() {
                bitField0_ = (bitField0_ & ~0x00000001);
                pvpRank_ = 0;
                onChanged();
                return this;
            }

            public boolean hasRoleId() {
                return ((bitField0_ & 0x00000002) == 0x00000002);
            }

            public int getRoleId() {
                return roleId_;
            }

            public Builder setRoleId(int value) {
                bitField0_ |= 0x00000002;
                roleId_ = value;
                onChanged();
                return this;
            }

            public Builder clearRoleId() {
                bitField0_ = (bitField0_ & ~0x00000002);
                roleId_ = 0;
                onChanged();
                return this;
            }

            public boolean hasRoleName() {
                return ((bitField0_ & 0x00000004) == 0x00000004);
            }

            public String getRoleName() {
                Object ref = roleName_;
                if (!(ref instanceof String)) {
                    String s = ((com.google.protobuf.ByteString) ref).toStringUtf8();
                    roleName_ = s;
                    return s;
                } else {
                    return (String) ref;
                }
            }

            void setRoleName(com.google.protobuf.ByteString value) {
                bitField0_ |= 0x00000004;
                roleName_ = value;
                onChanged();
            }

            public Builder setRoleName(String value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                bitField0_ |= 0x00000004;
                roleName_ = value;
                onChanged();
                return this;
            }

            public Builder clearRoleName() {
                bitField0_ = (bitField0_ & ~0x00000004);
                roleName_ = getDefaultInstance().getRoleName();
                onChanged();
                return this;
            }

            public boolean hasCampId() {
                return ((bitField0_ & 0x00000008) == 0x00000008);
            }

            public int getCampId() {
                return campId_;
            }

            public Builder setCampId(int value) {
                bitField0_ |= 0x00000008;
                campId_ = value;
                onChanged();
                return this;
            }

            public Builder clearCampId() {
                bitField0_ = (bitField0_ & ~0x00000008);
                campId_ = 0;
                onChanged();
                return this;
            }

            public boolean hasAvatar() {
                return ((bitField0_ & 0x00000010) == 0x00000010);
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
                bitField0_ |= 0x00000010;
                avatar_ = value;
                onChanged();
            }

            public Builder setAvatar(String value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                bitField0_ |= 0x00000010;
                avatar_ = value;
                onChanged();
                return this;
            }

            public Builder clearAvatar() {
                bitField0_ = (bitField0_ & ~0x00000010);
                avatar_ = getDefaultInstance().getAvatar();
                onChanged();
                return this;
            }

            public boolean hasGeneralDegree() {
                return ((bitField0_ & 0x00000020) == 0x00000020);
            }

            public int getGeneralDegree() {
                return generalDegree_;
            }

            public Builder setGeneralDegree(int value) {
                bitField0_ |= 0x00000020;
                generalDegree_ = value;
                onChanged();
                return this;
            }

            public Builder clearGeneralDegree() {
                bitField0_ = (bitField0_ & ~0x00000020);
                generalDegree_ = 0;
                onChanged();
                return this;
            }

            public boolean hasHonor() {
                return ((bitField0_ & 0x00000040) == 0x00000040);
            }

            public int getHonor() {
                return honor_;
            }

            public Builder setHonor(int value) {
                bitField0_ |= 0x00000040;
                honor_ = value;
                onChanged();
                return this;
            }

            public Builder clearHonor() {
                bitField0_ = (bitField0_ & ~0x00000040);
                honor_ = 0;
                onChanged();
                return this;
            }

            // @@protoc_insertion_point(builder_scope:Pvp_Rank_Info)
        }

        // @@protoc_insertion_point(class_scope:Pvp_Rank_Info)
    }

    // @@protoc_insertion_point(outer_class_scope)
}
