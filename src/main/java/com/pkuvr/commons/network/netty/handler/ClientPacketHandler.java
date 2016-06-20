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

import com.pkuvr.commons.network.netty.packet.AbstractClientPacket;
import com.pkuvr.commons.network.webcore.State;
import javolution.util.FastMap;
import org.apache.log4j.Logger;
import org.jboss.netty.buffer.ChannelBuffer;

/**
 * 操作码 与 处理类 的对应关系类(全部处理类,按照状态不同,放在不同的map中,每个操作码对应一个处理类)
 *
 * @author lyahim
 */
public class ClientPacketHandler<T extends AbstractChannelHandler> {
    private static final Logger log = Logger.getLogger(ClientPacketHandler.class);
    private final FastMap<State, FastMap<Integer, AbstractClientPacket<T>>> packetsPrototypes = new FastMap<State, FastMap<Integer, AbstractClientPacket<T>>>();

    protected static void unknownPacket(State state, int id, String ip) {
        log.info("unknown Packet id = 0x" + Integer.toString(id, 16) + ";state=" + state.toString() + ";ip=" + ip);

        log.warn(String.format("[UNKNOWN PACKET] : received 0x%02X, state=%s %n", id, state.toString()));
    }

    public void addPacketPrototype(AbstractClientPacket<T> packetPrototype, State... states) {
        for (State state : states) {
            FastMap<Integer, AbstractClientPacket<T>> pm = packetsPrototypes.get(state);
            if (pm == null) {
                pm = new FastMap<Integer, AbstractClientPacket<T>>();
                packetsPrototypes.put(state, pm);
            }
            pm.put(packetPrototype.getOpCode(), packetPrototype);
        }
    }

    @SuppressWarnings("unchecked")
    protected AbstractClientPacket<T> getPacket(int id, ChannelBuffer buf, AbstractChannelHandler ch) {
        AbstractClientPacket<T> prototype = null;
//		System.out.println("ch.getState() = " + ch.getState());
        FastMap<Integer, AbstractClientPacket<T>> pm = packetsPrototypes.get(ch.getState());

        if (pm != null)
            prototype = pm.get(Integer.valueOf(id));

        if (prototype == null) {
            unknownPacket(ch.getState(), id, ch.getIP());
            return null;
        } else {
            AbstractClientPacket<T> res = prototype.clonePacket();
            res.setBuf(buf);
            res.setChannelHandler((T) ch);
            return res;
        }
    }

    public AbstractClientPacket<T> handle(ChannelBuffer data, T ch) {
        int id = data.readShort() & 0xffff;
        return getPacket(id, data, ch);
    }
}
