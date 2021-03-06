// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: BI_Friend_Info.proto

package com.pkuvr.game_server.proto.commons;

public final class BI_Friend_Info {
    private static com.google.protobuf.Descriptors.Descriptor
            internal_static_Friend_Info_Mes_descriptor;
    private static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
            internal_static_Friend_Info_Mes_fieldAccessorTable;
    private static com.google.protobuf.Descriptors.FileDescriptor
            descriptor;

    static {
        String[] descriptorData = {
                "\n\024BI_Friend_Info.proto\"|\n\017Friend_Info_Me" +
                        "s\022\016\n\006roleId\030\001 \001(\005\022\020\n\010roleName\030\002 \001(\t\022\016\n\006c" +
                        "ampId\030\003 \001(\005\022\016\n\006avatar\030\004 \001(\t\022\025\n\rgeneralDe" +
                        "gree\030\005 \001(\005\022\020\n\010isOnline\030\006 \001(\005B3\n!com.gfan" +
                        ".gameserver.proto.commonsB\016BI_Friend_Inf" +
                        "o"
        };
        com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
                new com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner() {
                    public com.google.protobuf.ExtensionRegistry assignDescriptors(
                            com.google.protobuf.Descriptors.FileDescriptor root) {
                        descriptor = root;
                        internal_static_Friend_Info_Mes_descriptor =
                                getDescriptor().getMessageTypes().get(0);
                        internal_static_Friend_Info_Mes_fieldAccessorTable = new
                                com.google.protobuf.GeneratedMessage.FieldAccessorTable(
                                internal_static_Friend_Info_Mes_descriptor,
                                new String[]{"RoleId", "RoleName", "CampId", "Avatar", "GeneralDegree", "IsOnline",},
                                com.pkuvr.game_server.proto.commons.BI_Friend_Info.Friend_Info_Mes.class,
                                com.pkuvr.game_server.proto.commons.BI_Friend_Info.Friend_Info_Mes.Builder.class);
                        return null;
                    }
                };
        com.google.protobuf.Descriptors.FileDescriptor
                .internalBuildGeneratedFileFrom(descriptorData,
                        new com.google.protobuf.Descriptors.FileDescriptor[]{
                        }, assigner);
    }

    private BI_Friend_Info() {
    }

    public static void registerAllExtensions(
            com.google.protobuf.ExtensionRegistry registry) {
    }

    public static com.google.protobuf.Descriptors.FileDescriptor
    getDescriptor() {
        return descriptor;
    }

    public interface Friend_Info_MesOrBuilder
            extends com.google.protobuf.MessageOrBuilder {

        // optional int32 roleId = 1;
        boolean hasRoleId();

        int getRoleId();

        // optional string roleName = 2;
        boolean hasRoleName();

        String getRoleName();

        // optional int32 campId = 3;
        boolean hasCampId();

        int getCampId();

        // optional string avatar = 4;
        boolean hasAvatar();

        String getAvatar();

        // optional int32 generalDegree = 5;
        boolean hasGeneralDegree();

        int getGeneralDegree();

        // optional int32 isOnline = 6;
        boolean hasIsOnline();

        int getIsOnline();
    }

    public static final class Friend_Info_Mes extends
            com.google.protobuf.GeneratedMessage
            implements Friend_Info_MesOrBuilder {
        // optional int32 roleId = 1;
        public static final int ROLEID_FIELD_NUMBER = 1;
        // optional string roleName = 2;
        public static final int ROLENAME_FIELD_NUMBER = 2;
        // optional int32 campId = 3;
        public static final int CAMPID_FIELD_NUMBER = 3;
        // optional string avatar = 4;
        public static final int AVATAR_FIELD_NUMBER = 4;
        // optional int32 generalDegree = 5;
        public static final int GENERALDEGREE_FIELD_NUMBER = 5;
        // optional int32 isOnline = 6;
        public static final int ISONLINE_FIELD_NUMBER = 6;
        private static final Friend_Info_Mes defaultInstance;
        private static final long serialVersionUID = 0L;

        static {
            defaultInstance = new Friend_Info_Mes(true);
            defaultInstance.initFields();
        }

        private int bitField0_;
        private int roleId_;
        private Object roleName_;
        private int campId_;
        private Object avatar_;
        private int generalDegree_;
        private int isOnline_;
        private byte memoizedIsInitialized = -1;
        private int memoizedSerializedSize = -1;
        // Use Friend_Info_Mes.newBuilder() to construct.
        private Friend_Info_Mes(Builder builder) {
            super(builder);
        }

        private Friend_Info_Mes(boolean noInit) {
        }

        public static Friend_Info_Mes getDefaultInstance() {
            return defaultInstance;
        }

        public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
            return com.pkuvr.game_server.proto.commons.BI_Friend_Info.internal_static_Friend_Info_Mes_descriptor;
        }

        public static com.pkuvr.game_server.proto.commons.BI_Friend_Info.Friend_Info_Mes parseFrom(
                com.google.protobuf.ByteString data)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return newBuilder().mergeFrom(data).buildParsed();
        }

        public static com.pkuvr.game_server.proto.commons.BI_Friend_Info.Friend_Info_Mes parseFrom(
                com.google.protobuf.ByteString data,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return newBuilder().mergeFrom(data, extensionRegistry)
                    .buildParsed();
        }

        public static com.pkuvr.game_server.proto.commons.BI_Friend_Info.Friend_Info_Mes parseFrom(byte[] data)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return newBuilder().mergeFrom(data).buildParsed();
        }

        public static com.pkuvr.game_server.proto.commons.BI_Friend_Info.Friend_Info_Mes parseFrom(
                byte[] data,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return newBuilder().mergeFrom(data, extensionRegistry)
                    .buildParsed();
        }

        public static com.pkuvr.game_server.proto.commons.BI_Friend_Info.Friend_Info_Mes parseFrom(java.io.InputStream input)
                throws java.io.IOException {
            return newBuilder().mergeFrom(input).buildParsed();
        }

        public static com.pkuvr.game_server.proto.commons.BI_Friend_Info.Friend_Info_Mes parseFrom(
                java.io.InputStream input,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws java.io.IOException {
            return newBuilder().mergeFrom(input, extensionRegistry)
                    .buildParsed();
        }

        public static com.pkuvr.game_server.proto.commons.BI_Friend_Info.Friend_Info_Mes parseDelimitedFrom(java.io.InputStream input)
                throws java.io.IOException {
            Builder builder = newBuilder();
            if (builder.mergeDelimitedFrom(input)) {
                return builder.buildParsed();
            } else {
                return null;
            }
        }

        public static com.pkuvr.game_server.proto.commons.BI_Friend_Info.Friend_Info_Mes parseDelimitedFrom(
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

        public static com.pkuvr.game_server.proto.commons.BI_Friend_Info.Friend_Info_Mes parseFrom(
                com.google.protobuf.CodedInputStream input)
                throws java.io.IOException {
            return newBuilder().mergeFrom(input).buildParsed();
        }

        public static com.pkuvr.game_server.proto.commons.BI_Friend_Info.Friend_Info_Mes parseFrom(
                com.google.protobuf.CodedInputStream input,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws java.io.IOException {
            return newBuilder().mergeFrom(input, extensionRegistry)
                    .buildParsed();
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        public static Builder newBuilder(com.pkuvr.game_server.proto.commons.BI_Friend_Info.Friend_Info_Mes prototype) {
            return newBuilder().mergeFrom(prototype);
        }

        public Friend_Info_Mes getDefaultInstanceForType() {
            return defaultInstance;
        }

        protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
            return com.pkuvr.game_server.proto.commons.BI_Friend_Info.internal_static_Friend_Info_Mes_fieldAccessorTable;
        }

        public boolean hasRoleId() {
            return ((bitField0_ & 0x00000001) == 0x00000001);
        }

        public int getRoleId() {
            return roleId_;
        }

        public boolean hasRoleName() {
            return ((bitField0_ & 0x00000002) == 0x00000002);
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
            return ((bitField0_ & 0x00000004) == 0x00000004);
        }

        public int getCampId() {
            return campId_;
        }

        public boolean hasAvatar() {
            return ((bitField0_ & 0x00000008) == 0x00000008);
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
            return ((bitField0_ & 0x00000010) == 0x00000010);
        }

        public int getGeneralDegree() {
            return generalDegree_;
        }

        public boolean hasIsOnline() {
            return ((bitField0_ & 0x00000020) == 0x00000020);
        }

        public int getIsOnline() {
            return isOnline_;
        }

        private void initFields() {
            roleId_ = 0;
            roleName_ = "";
            campId_ = 0;
            avatar_ = "";
            generalDegree_ = 0;
            isOnline_ = 0;
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
                output.writeInt32(1, roleId_);
            }
            if (((bitField0_ & 0x00000002) == 0x00000002)) {
                output.writeBytes(2, getRoleNameBytes());
            }
            if (((bitField0_ & 0x00000004) == 0x00000004)) {
                output.writeInt32(3, campId_);
            }
            if (((bitField0_ & 0x00000008) == 0x00000008)) {
                output.writeBytes(4, getAvatarBytes());
            }
            if (((bitField0_ & 0x00000010) == 0x00000010)) {
                output.writeInt32(5, generalDegree_);
            }
            if (((bitField0_ & 0x00000020) == 0x00000020)) {
                output.writeInt32(6, isOnline_);
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
            if (((bitField0_ & 0x00000002) == 0x00000002)) {
                size += com.google.protobuf.CodedOutputStream
                        .computeBytesSize(2, getRoleNameBytes());
            }
            if (((bitField0_ & 0x00000004) == 0x00000004)) {
                size += com.google.protobuf.CodedOutputStream
                        .computeInt32Size(3, campId_);
            }
            if (((bitField0_ & 0x00000008) == 0x00000008)) {
                size += com.google.protobuf.CodedOutputStream
                        .computeBytesSize(4, getAvatarBytes());
            }
            if (((bitField0_ & 0x00000010) == 0x00000010)) {
                size += com.google.protobuf.CodedOutputStream
                        .computeInt32Size(5, generalDegree_);
            }
            if (((bitField0_ & 0x00000020) == 0x00000020)) {
                size += com.google.protobuf.CodedOutputStream
                        .computeInt32Size(6, isOnline_);
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
                implements com.pkuvr.game_server.proto.commons.BI_Friend_Info.Friend_Info_MesOrBuilder {
            private int bitField0_;
            // optional int32 roleId = 1;
            private int roleId_;
            // optional string roleName = 2;
            private Object roleName_ = "";
            // optional int32 campId = 3;
            private int campId_;
            // optional string avatar = 4;
            private Object avatar_ = "";
            // optional int32 generalDegree = 5;
            private int generalDegree_;
            // optional int32 isOnline = 6;
            private int isOnline_;

            // Construct using com.pkuvr.game_server.proto.commons.BI_Friend_Info.Friend_Info_Mes.newBuilder()
            private Builder() {
                maybeForceBuilderInitialization();
            }

            private Builder(BuilderParent parent) {
                super(parent);
                maybeForceBuilderInitialization();
            }

            public static final com.google.protobuf.Descriptors.Descriptor
            getDescriptor() {
                return com.pkuvr.game_server.proto.commons.BI_Friend_Info.internal_static_Friend_Info_Mes_descriptor;
            }

            private static Builder create() {
                return new Builder();
            }

            protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
            internalGetFieldAccessorTable() {
                return com.pkuvr.game_server.proto.commons.BI_Friend_Info.internal_static_Friend_Info_Mes_fieldAccessorTable;
            }

            private void maybeForceBuilderInitialization() {
                if (com.google.protobuf.GeneratedMessage.alwaysUseFieldBuilders) {
                }
            }

            public Builder clear() {
                super.clear();
                roleId_ = 0;
                bitField0_ = (bitField0_ & ~0x00000001);
                roleName_ = "";
                bitField0_ = (bitField0_ & ~0x00000002);
                campId_ = 0;
                bitField0_ = (bitField0_ & ~0x00000004);
                avatar_ = "";
                bitField0_ = (bitField0_ & ~0x00000008);
                generalDegree_ = 0;
                bitField0_ = (bitField0_ & ~0x00000010);
                isOnline_ = 0;
                bitField0_ = (bitField0_ & ~0x00000020);
                return this;
            }

            public Builder clone() {
                return create().mergeFrom(buildPartial());
            }

            public com.google.protobuf.Descriptors.Descriptor
            getDescriptorForType() {
                return com.pkuvr.game_server.proto.commons.BI_Friend_Info.Friend_Info_Mes.getDescriptor();
            }

            public com.pkuvr.game_server.proto.commons.BI_Friend_Info.Friend_Info_Mes getDefaultInstanceForType() {
                return com.pkuvr.game_server.proto.commons.BI_Friend_Info.Friend_Info_Mes.getDefaultInstance();
            }

            public com.pkuvr.game_server.proto.commons.BI_Friend_Info.Friend_Info_Mes build() {
                com.pkuvr.game_server.proto.commons.BI_Friend_Info.Friend_Info_Mes result = buildPartial();
                if (!result.isInitialized()) {
                    throw newUninitializedMessageException(result);
                }
                return result;
            }

            private com.pkuvr.game_server.proto.commons.BI_Friend_Info.Friend_Info_Mes buildParsed()
                    throws com.google.protobuf.InvalidProtocolBufferException {
                com.pkuvr.game_server.proto.commons.BI_Friend_Info.Friend_Info_Mes result = buildPartial();
                if (!result.isInitialized()) {
                    throw newUninitializedMessageException(
                            result).asInvalidProtocolBufferException();
                }
                return result;
            }

            public com.pkuvr.game_server.proto.commons.BI_Friend_Info.Friend_Info_Mes buildPartial() {
                com.pkuvr.game_server.proto.commons.BI_Friend_Info.Friend_Info_Mes result = new com.pkuvr.game_server.proto.commons.BI_Friend_Info.Friend_Info_Mes(this);
                int from_bitField0_ = bitField0_;
                int to_bitField0_ = 0;
                if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
                    to_bitField0_ |= 0x00000001;
                }
                result.roleId_ = roleId_;
                if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
                    to_bitField0_ |= 0x00000002;
                }
                result.roleName_ = roleName_;
                if (((from_bitField0_ & 0x00000004) == 0x00000004)) {
                    to_bitField0_ |= 0x00000004;
                }
                result.campId_ = campId_;
                if (((from_bitField0_ & 0x00000008) == 0x00000008)) {
                    to_bitField0_ |= 0x00000008;
                }
                result.avatar_ = avatar_;
                if (((from_bitField0_ & 0x00000010) == 0x00000010)) {
                    to_bitField0_ |= 0x00000010;
                }
                result.generalDegree_ = generalDegree_;
                if (((from_bitField0_ & 0x00000020) == 0x00000020)) {
                    to_bitField0_ |= 0x00000020;
                }
                result.isOnline_ = isOnline_;
                result.bitField0_ = to_bitField0_;
                onBuilt();
                return result;
            }

            public Builder mergeFrom(com.google.protobuf.Message other) {
                if (other instanceof com.pkuvr.game_server.proto.commons.BI_Friend_Info.Friend_Info_Mes) {
                    return mergeFrom((com.pkuvr.game_server.proto.commons.BI_Friend_Info.Friend_Info_Mes) other);
                } else {
                    super.mergeFrom(other);
                    return this;
                }
            }

            public Builder mergeFrom(com.pkuvr.game_server.proto.commons.BI_Friend_Info.Friend_Info_Mes other) {
                if (other == com.pkuvr.game_server.proto.commons.BI_Friend_Info.Friend_Info_Mes.getDefaultInstance())
                    return this;
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
                if (other.hasIsOnline()) {
                    setIsOnline(other.getIsOnline());
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
                            roleId_ = input.readInt32();
                            break;
                        }
                        case 18: {
                            bitField0_ |= 0x00000002;
                            roleName_ = input.readBytes();
                            break;
                        }
                        case 24: {
                            bitField0_ |= 0x00000004;
                            campId_ = input.readInt32();
                            break;
                        }
                        case 34: {
                            bitField0_ |= 0x00000008;
                            avatar_ = input.readBytes();
                            break;
                        }
                        case 40: {
                            bitField0_ |= 0x00000010;
                            generalDegree_ = input.readInt32();
                            break;
                        }
                        case 48: {
                            bitField0_ |= 0x00000020;
                            isOnline_ = input.readInt32();
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

            public boolean hasRoleName() {
                return ((bitField0_ & 0x00000002) == 0x00000002);
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
                bitField0_ |= 0x00000002;
                roleName_ = value;
                onChanged();
            }

            public Builder setRoleName(String value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                bitField0_ |= 0x00000002;
                roleName_ = value;
                onChanged();
                return this;
            }

            public Builder clearRoleName() {
                bitField0_ = (bitField0_ & ~0x00000002);
                roleName_ = getDefaultInstance().getRoleName();
                onChanged();
                return this;
            }

            public boolean hasCampId() {
                return ((bitField0_ & 0x00000004) == 0x00000004);
            }

            public int getCampId() {
                return campId_;
            }

            public Builder setCampId(int value) {
                bitField0_ |= 0x00000004;
                campId_ = value;
                onChanged();
                return this;
            }

            public Builder clearCampId() {
                bitField0_ = (bitField0_ & ~0x00000004);
                campId_ = 0;
                onChanged();
                return this;
            }

            public boolean hasAvatar() {
                return ((bitField0_ & 0x00000008) == 0x00000008);
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
                bitField0_ |= 0x00000008;
                avatar_ = value;
                onChanged();
            }

            public Builder setAvatar(String value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                bitField0_ |= 0x00000008;
                avatar_ = value;
                onChanged();
                return this;
            }

            public Builder clearAvatar() {
                bitField0_ = (bitField0_ & ~0x00000008);
                avatar_ = getDefaultInstance().getAvatar();
                onChanged();
                return this;
            }

            public boolean hasGeneralDegree() {
                return ((bitField0_ & 0x00000010) == 0x00000010);
            }

            public int getGeneralDegree() {
                return generalDegree_;
            }

            public Builder setGeneralDegree(int value) {
                bitField0_ |= 0x00000010;
                generalDegree_ = value;
                onChanged();
                return this;
            }

            public Builder clearGeneralDegree() {
                bitField0_ = (bitField0_ & ~0x00000010);
                generalDegree_ = 0;
                onChanged();
                return this;
            }

            public boolean hasIsOnline() {
                return ((bitField0_ & 0x00000020) == 0x00000020);
            }

            public int getIsOnline() {
                return isOnline_;
            }

            public Builder setIsOnline(int value) {
                bitField0_ |= 0x00000020;
                isOnline_ = value;
                onChanged();
                return this;
            }

            public Builder clearIsOnline() {
                bitField0_ = (bitField0_ & ~0x00000020);
                isOnline_ = 0;
                onChanged();
                return this;
            }

            // @@protoc_insertion_point(builder_scope:Friend_Info_Mes)
        }

        // @@protoc_insertion_point(class_scope:Friend_Info_Mes)
    }

    // @@protoc_insertion_point(outer_class_scope)
}
