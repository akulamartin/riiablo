package com.riiablo.attributes;

import java.util.Iterator;

import com.riiablo.codec.excel.ItemStatCost;
import com.riiablo.io.BitOutput;
import com.riiablo.logger.LogManager;
import com.riiablo.logger.Logger;
import com.riiablo.logger.MDC;

public class StatListWriter {
  private static final Logger log = LogManager.getLogger(StatListWriter.class);

  public void write(StatListGetter stats, StatGetter stat, BitOutput bits) {
    log.traceEntry("write(stats: {}, stat: {}, bits: {})", stats, stat, bits);
    final ItemStatCost.Entry entry = stat.entry();
    if (entry.Saved) {
      if (log.traceEnabled()) log.trace("Writing character save stat {}", stat.debugString());
      assert !entry.CSvSigned : "entry.CSvSigned(" + entry.CSvSigned + ") unsupported";
      bits.write63u(stat.param(), entry.CSvParam);
      bits.write63u(stat.value(), entry.CSvBits);
    } else {
      if (log.traceEnabled()) log.trace("Writing stat {}", stat.debugString());
      bits.write63u(stat.param(), entry.Save_Param_Bits);
      bits.write63u(stat.value() + entry.Save_Add, entry.Save_Bits);
    }
  }

  public void write(StatListGetter stats, BitOutput bits) {
    for (Iterator<StatGetter> it = stats.iterator(); it.hasNext();) {
      final StatGetter stat = it.next();
      final short id = stat.id();
      try {
        MDC.put("stat", id);
        bits.write15u(id, Stat.BITS);
        final byte numEncoded = Stat.getNumEncoded(id);
        try {
          if (numEncoded > 1) MDC.put("numEncoded", numEncoded);
          write(stats, stat, bits);
          for (short j = 1; j < numEncoded; j++) {
            final StatGetter next = it.next();
            assert next.id() == id + j : String.format(
                "it.next(%s) != %d : getNumEncoded(%s)[%d..%d]", next, id + j, id + j, id, id + numEncoded - 1);
            write(stats, stat, bits);
          }
        } finally {
          MDC.remove("numEncoded");
        }
      } finally {
        MDC.remove("stat");
      }
    }

    bits.write15u(Stat.NONE, Stat.BITS);
  }
}