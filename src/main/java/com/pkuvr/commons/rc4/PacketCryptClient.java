package com.pkuvr.commons.rc4;

import org.apache.log4j.Logger;

public class PacketCryptClient {
    private static final Logger logger = Logger.getLogger(PacketCryptClient.class);
    // / <summary>
    // / This is the key the client uses to encrypt its packets
    // / This is also the key the server uses to decrypt the packets
    // / </summary>
    private static byte[] ServerDecryptionKey =
            {(byte) 0xC2, (byte) 0xB3, 0x72, 0x3C, (byte) 0xC6, (byte) 0xAE, (byte) 0xD9, (byte) 0xB5, 0x34, 0x3C, 0x53,
                    (byte) 0xEE, 0x2F, 0x43, 0x67, (byte) 0xCE};
    // This is valid as HMAC-SHA1 transforms can be reused
    static HMACSHA1 s_decryptClientDataHMAC = new HMACSHA1(ServerDecryptionKey);
    // / <summary>
    // / This is the key the client uses to decrypt server packets
    // / This is also the key the server uses to encrypt the packets
    // / </summary>
    private static byte[] ServerEncryptionKey =
            {(byte) 0xCC, (byte) 0x98, (byte) 0xAE, 0x04, (byte) 0xE8, (byte) 0x97, (byte) 0xEA, (byte) 0xCA, 0x12,
                    (byte) 0xDD, (byte) 0xC0, (byte) 0x93, 0x42, (byte) 0x91, 0x53, 0x57};
    static HMACSHA1 s_encryptServerDataHMAC = new HMACSHA1(ServerEncryptionKey);
    // / <summary>
    // / The amount of bytes to drop from the stream initially.
    // /
    // / This is to resist the FMS attack.
    // / </summary>
    public int DropN = 1024;
    // / <summary>
    // / Encrypts data sent to the client
    // / </summary>
    private ARC4 encryptClientData;
    // / <summary>
    // / Decrypts data sent from the client
    // / </summary>
    private ARC4 decryptServerData;

    public PacketCryptClient(String sessionKey) {
        byte[] encryptHash = null;
        byte[] decryptHash = null;
        try {
            encryptHash = s_encryptServerDataHMAC.ComputeHash(sessionKey);
            decryptHash = s_decryptClientDataHMAC.ComputeHash(sessionKey);
//			logger.info( "sessionKey=" + sessionKey );
//			logger.info( "encryptHash=" + bytes2Hex( encryptHash ) );
//			logger.info( "decryptHash=" + bytes2Hex( decryptHash ) );
        } catch (Exception e) {
            logger.error("sessionKey=" + sessionKey);
            logger.error("encryptHash=" + bytes2Hex(encryptHash));
            logger.error("decryptHash=" + bytes2Hex(decryptHash));
            logger.error("解包出错", e);
        }

        // Used by the client to decrypt packets sent by the server
        decryptServerData = new ARC4(encryptHash); // CLIENT-SIDE
        // Used by the server to decrypt packets sent by the client
//		decryptClientData = new ARC4( decryptHash ); // SERVER-SIDE
        // Used by the server to encrypt packets sent to the client
//		encryptServerData = new ARC4( encryptHash ); // SERVER-SIDE
        // Used by the client to encrypt packets sent to the server
        encryptClientData = new ARC4(decryptHash); // CLIENT-SIDE

        // Use the 2 encryption objects to generate a common starting point
        byte[] syncBuffer = new byte[DropN];
//		encryptServerData.Process( syncBuffer, 0, syncBuffer.length );
        encryptClientData.Process(syncBuffer, 0, syncBuffer.length);

        // Use the 2 decryption objects to generate a common starting point
        syncBuffer = new byte[DropN];
        decryptServerData.Process(syncBuffer, 0, syncBuffer.length);
//		decryptClientData.Process( syncBuffer, 0, syncBuffer.length );
    }

    public static String bytes2Hex(byte[] bts) {
        String des = "";
        String tmp = null;
        for (int i = 0; i < bts.length; i++) {
            tmp = (Integer.toHexString(bts[i] & 0xFF));
            if (tmp.length() == 1) {
                des += "0";
            }
            des += tmp;
        }
        return des;
    }

    public void Decrypt(byte[] data, int start, int count) {
        decryptServerData.Process(data, start, count);
    }

    public void Encrypt(byte[] data, int start, int count) {
        encryptClientData.Process(data, start, count);
    }

    public void decrypt(byte[] data) {
        decryptServerData.Process(data, 0, data.length);
    }

    public void encrypt(byte[] data) {
        encryptClientData.Process(data, 0, data.length);
    }
}
