package org.veupathdb.service.multiblast.service.conv;

import java.util.Arrays;

import mb.lib.db.model.impl.JobLinkImpl;
import mb.lib.db.model.impl.JobTargetImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.veupathdb.service.multiblast.generated.model.*;
import org.veupathdb.service.multiblast.model.blast.CompBasedStats;
import org.veupathdb.service.multiblast.model.blast.Location;
import org.veupathdb.service.multiblast.model.blast.QueryStrand;
import org.veupathdb.service.multiblast.model.blast.Seg;
import org.veupathdb.service.multiblast.model.blast.impl.LocationImpl;
import org.veupathdb.service.multiblast.model.blast.impl.SegImpl;
import org.veupathdb.service.multiblast.util.Format;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("BCC")
class BCCTest
{
  @Nested
  @DisplayName("::toExternal(JobLink)")
  class ToExternal1
  {
    @Test
    @DisplayName("Correctly translates job links")
    void test1() {
      var id  = new byte[]{0,2,4,6,8,10};
      var pid = new byte[]{1,3,5,7,9,11};
      var pos = 12;

      var in  = new JobLinkImpl(id, pid, pos);
      var out = BCC.toExternal(in);

      // Self ID is omitted from external form
      assertEquals(Format.toHexString(pid), out.getId());
      assertEquals(pos, out.getIndex());
    }
  }

  @Nested
  @DisplayName("::toExternal(JobTarget)")
  class ToExternal2
  {
    @Test
    @DisplayName("Correctly translates job targets")
    void test1() {
      var id  = new byte[]{0,1,2,4,8,16,32};
      var org = "spuds";
      var tgt = "somefile";

      var in  = new JobTargetImpl(id, org, tgt);
      var out = BCC.toExternal(in);

      // ID is omitted from external form
      assertEquals(org, out.organism());
      assertEquals(tgt, out.target());
    }
  }

  @Nested
  @DisplayName("::arrayToList(int[])")
  class ArrayToList
  {
    @Test
    @DisplayName("Correctly converts int array to int list")
    void test1() {
      var in  = new int[] {0, 1, 2, 3, 4, 5, 6, 12, 18, 24};
      var out = BCC.arrayToList(in);

      assertEquals(in.length, out.size());

      for (var i = 0; i < in.length; i++)
        assertEquals(in[i], out.get(i));
    }

    @Test
    @DisplayName("Returns null on null input")
    void test2() {
      assertNull(BCC.arrayToList(null));
    }
  }

  @Nested
  @DisplayName("::listToArray(List)")
  class ListToArray
  {
    @Test
    @DisplayName("Correctly converts in list to int array")
    void test1() {
      var in  = Arrays.asList(1, 2, 3, 6, 7, 8, 16, 17, 18);
      var out = BCC.listToArray(in);

      assertEquals(in.size(), out.length);

      for (var i = 0; i < out.length; i++)
        assertEquals(in.get(i), out[i]);
    }

    @Test
    @DisplayName("Returns null on null input")
    void test2() {
      assertNull(BCC.listToArray(null));
    }
  }

  @Nested
  @DisplayName("::nullToFalse(Boolean)")
  class NullToFalse
  {
    @Test
    @DisplayName("Returns true when input is true")
    void test1() {
      assertTrue(BCC.nullToFalse(true));
    }

    @Test
    @DisplayName("Returns false when input is false")
    void test2() {
      assertFalse(BCC.nullToFalse(false));
    }

    @Test
    @DisplayName("Returns false when input is null")
    void test3() {
      assertFalse(BCC.nullToFalse(null));
    }
  }

  @Nested
  @DisplayName("::toExternal(Location)")
  class ToExternal3
  {
    @Test
    @DisplayName("Correctly translates Locations")
    void test1() {
      var in  = new LocationImpl(10, 20);
      var out = BCC.toExternal(in);

      assertEquals(in.getStart(), out.getStart());
      assertEquals(in.getStop(), out.getStop());
    }

    @Test
    @DisplayName("Returns null on null input")
    void test2() {
      assertNull(BCC.toExternal((Location) null));
    }
  }

  @Nested
  @DisplayName("::toExternal(Seg)")
  class ToExternal4
  {
    @Test
    @DisplayName("Correctly translates Seg value.")
    void test1() {
      var in  = new SegImpl(10, 20, 30);
      var out = BCC.toExternal(in);

      assertEquals(in.getWindow(), out.getWindow());
      assertEquals(in.getHighCut(), out.getHicut());
      assertEquals(in.getLowCut(), out.getLocut());
    }

    @Test
    @DisplayName("Returns null on null input")
    void test2() {
      assertNull(BCC.toExternal((Seg) null));
    }
  }

  @Nested
  @DisplayName("::toInternal(IOBlastLocation)")
  class ToInternal2
  {
    @Test
    @DisplayName("Correctly translates IOBlastLocation value.")
    void test1() {
      var in = new IOBlastLocationImpl();
      in.setStart(10L);
      in.setStop(11L);

      var out = BCC.toInternal(in);

      assertEquals(in.getStart(), out.getStart());
      assertEquals(in.getStop(), out.getStop());
    }

    @Test
    @DisplayName("Returns null on null input")
    void test2() {
      assertNull(BCC.toInternal((IOBlastLocation) null));
    }
  }

  @Nested
  @DisplayName("::toInternal(IOBlastSegMask)")
  class ToInternal3
  {
    @Test
    @DisplayName("Correctly translates IOBlastSegMask value.")
    void test1() {
      var in = new IOBlastSegMaskImpl();
      in.setWindow(10);
      in.setHicut(20D);
      in.setLocut(30D);

      var out = BCC.toInternal(in);

      assertEquals(in.getWindow(), out.getWindow());
      assertEquals(in.getHicut(), out.getHighCut());
      assertEquals(in.getLocut(), out.getLowCut());
    }

    @Test
    @DisplayName("Returns null on null input")
    void test2() {
      assertNull(BCC.toInternal((IOBlastSegMask) null));
    }
  }
}