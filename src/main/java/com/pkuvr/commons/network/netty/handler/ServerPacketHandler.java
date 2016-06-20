/*
 * This file is part of aion-unique <aion-unique.org>.
 *
 *  aion-unique is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  aion-unique is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with aion-unique.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.pkuvr.commons.network.netty.handler;

import com.pkuvr.commons.network.netty.packet.AbstractServerPacket;
import javolution.util.FastMap;

/**
 * 操作码 与 处理类 的对应关系类(全部 处理类 都在map中,按照类映射操作码),用于下行数据
 *
 * @author lyahim
 */
public class ServerPacketHandler<T extends AbstractChannelHandler> {
    private FastMap<Class<? extends AbstractServerPacket<T>>, Integer> opcodes = new FastMap<Class<? extends AbstractServerPacket<T>>, Integer>();

    public int getOpCode(Class<? extends AbstractServerPacket<AbstractChannelHandler>> packetClass) {
        Integer opcode = opcodes.get(packetClass);
        if (opcode == null)
            throw new IllegalArgumentException("There is no opcode for " + packetClass + " defined.");

        return opcode;
    }

    public void addPacketOpcode(Class<? extends AbstractServerPacket<T>> packetClass, int opcode) {
        if (opcode < 0)
            return;

        if (opcodes.values().contains(opcode))
            throw new IllegalArgumentException(String.format("There already exists another packet with id 0x%02X", opcode));

        opcodes.put(packetClass, opcode);
    }
}
