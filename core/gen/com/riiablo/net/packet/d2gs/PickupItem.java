// automatically generated by the FlatBuffers compiler, do not modify

package com.riiablo.net.packet.d2gs;

import com.google.flatbuffers.FlatBufferBuilder;
import com.google.flatbuffers.Table;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

@SuppressWarnings("unused")
public final class PickupItem extends Table {
  public static PickupItem getRootAsPickupItem(ByteBuffer _bb) { return getRootAsPickupItem(_bb, new PickupItem()); }
  public static PickupItem getRootAsPickupItem(ByteBuffer _bb, PickupItem obj) { _bb.order(ByteOrder.LITTLE_ENDIAN); return (obj.__assign(_bb.getInt(_bb.position()) + _bb.position(), _bb)); }
  public void __init(int _i, ByteBuffer _bb) { bb_pos = _i; bb = _bb; vtable_start = bb_pos - bb.getInt(bb_pos); vtable_size = bb.getShort(vtable_start); }
  public PickupItem __assign(int _i, ByteBuffer _bb) { __init(_i, _bb); return this; }

  public int itemId() { int o = __offset(4); return o != 0 ? bb.getInt(o + bb_pos) : 0; }

  public static int createPickupItem(FlatBufferBuilder builder,
      int itemId) {
    builder.startObject(1);
    PickupItem.addItemId(builder, itemId);
    return PickupItem.endPickupItem(builder);
  }

  public static void startPickupItem(FlatBufferBuilder builder) { builder.startObject(1); }
  public static void addItemId(FlatBufferBuilder builder, int itemId) { builder.addInt(0, itemId, 0); }
  public static int endPickupItem(FlatBufferBuilder builder) {
    int o = builder.endObject();
    return o;
  }
}
