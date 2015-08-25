package org.lanternpowered.server.network.vanilla.message.processor.play;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.handler.codec.CodecException;

import java.util.List;

import org.lanternpowered.server.network.message.Message;
import org.lanternpowered.server.network.message.codec.CodecContext;
import org.lanternpowered.server.network.message.processor.Processor;
import org.lanternpowered.server.network.vanilla.message.type.play.MessagePlayInOutChannelPayload;

public class ProcessorPlayOutChannelPayload implements Processor<MessagePlayInOutChannelPayload> {

    @Override
    public void process(CodecContext context, MessagePlayInOutChannelPayload message, List<Message> output) throws CodecException {
        byte[] content = message.getContent().array();

        if (content.length < 16777136) {
            output.add(message);
        } else {
            boolean enabled = context.channel().attr(ProcessorPlayInChannelPayload.FML_MULTI_PART_MESSAGE_ENABLED).get();
            if (!enabled) {
                throw new CodecException("Payload may not be larger than 16777135 bytes.");
            }
            int parts = (int) Math.ceil(content.length / 16777135.0);
            if (parts > 255) {
                throw new CodecException("Payload may not be larger than -16797616 bytes.");
            }
            ByteBuf preamble = context.byteBufAlloc().buffer();
            context.write(preamble, String.class, message.getChannel());
            preamble.writeByte(parts);
            preamble.writeInt(content.length);
            output.add(new MessagePlayInOutChannelPayload("FML|MP", preamble));

            int offset = 0;
            for (int x = 0; x < parts; x++) {
                int length = Math.min(16777136, content.length - offset + 1);
                byte[] tmp = new byte[length];
                tmp[0] = ((byte) (x & 0xff));
                System.arraycopy(content, offset, tmp, 1, tmp.length - 1);
                offset += tmp.length - 1;
                output.add(new MessagePlayInOutChannelPayload("FML|MP", Unpooled.wrappedBuffer(tmp)));
            }
        }
    }

}
