package com.pkuvr.commons.rc4;

public class ARC4 {
    private byte[] state;
    private byte x, y;

    public ARC4(byte[] key) {
        state = new byte[256];
        x = y = 0;
        KeySetup(key);
    }

    private static int getByteValue(byte b) {
        return b < 0 ? 256 + b : b;
    }

    public int Process(byte[] buffer, int start, int count) {
        return InternalTransformBlock(buffer, start, count, buffer, start);
    }

    private void KeySetup(byte[] key) {
        byte index1 = 0;
        byte index2 = 0;

        for (int counter = 0; counter < 256; counter++) {
            state[counter] = (byte) counter;
        }
        x = 0;
        y = 0;
        for (int counter = 0; counter < 256; counter++) {
            index2 = (byte) (key[getByteValue(index1)] + state[counter] + index2);
            // swap byte
            byte tmp = state[counter];
            state[counter] = state[getByteValue(index2)];
            state[getByteValue(index2)] = tmp;
            index1 = (byte) ((getByteValue(index1) + 1) % key.length);
        }
    }

    private int InternalTransformBlock(byte[] inputBuffer, int inputOffset, int inputCount, byte[] outputBuffer,
                                       int outputOffset) {
        for (int counter = 0; counter < inputCount; counter++) {
            x = (byte) (x + 1);
            y = (byte) (state[getByteValue(x)] + y);
            // swap byte
            byte tmp = state[getByteValue(x)];
            state[getByteValue(x)] = state[getByteValue(y)];
            state[getByteValue(y)] = tmp;

            int xorIndex = getByteValue((byte) (state[getByteValue(x)] + state[getByteValue(y)]));
            outputBuffer[outputOffset + counter] = (byte) (inputBuffer[inputOffset + counter] ^ state[xorIndex]);
        }
        return inputCount;
    }
}
