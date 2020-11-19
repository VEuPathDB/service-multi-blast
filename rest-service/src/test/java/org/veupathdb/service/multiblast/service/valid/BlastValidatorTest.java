package org.veupathdb.service.multiblast.service.valid;

import java.io.File;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.veupathdb.service.multiblast.model.ErrorMap;
import org.veupathdb.service.multiblast.model.blast.BlastConfig;
import org.veupathdb.service.multiblast.model.blast.QueryLocation;
import org.veupathdb.service.multiblast.model.blast.ToolOption;
import org.veupathdb.service.multiblast.model.io.JsonKeys;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("BlastValidator")
class BlastValidatorTest
{
//  @Nested
//  @DisplayName("#validateDbName(ErrorMap, BlastConfig, boolean)")
//  class ValidateDbName
//  {
//
//    @Nested
//    @DisplayName("When ext is true")
//    class ExtTrue
//    {
//
//      @Nested
//      @DisplayName("and the config db flag is unset")
//      class Unset
//      {
//        @Test
//        @DisplayName("returns an error keyed with the JSON field name")
//        void test1() {
//          var err = new ErrorMap();
//          var con = new BlastConfig();
//
//          BlastValidator.validateDbName(err, con, true);
//
//          assertTrue(err.containsKey(JsonKeys.BlastDatabase));
//          assertEquals(err.get(JsonKeys.BlastDatabase).size(), 1);
//        }
//      }
//
//      @Nested
//      @DisplayName("and the config db flag is an empty value")
//      class Set1
//      {
//        @Test
//        @DisplayName("returns an error keyed with the JSON field name")
//        void test1() {
//          var err = new ErrorMap();
//          var con = new BlastConfig();
//
//          con.setBlastDatabase("");
//
//          BlastValidator.validateDbName(err, con, true);
//
//          assertTrue(err.containsKey(JsonKeys.BlastDatabase));
//          assertEquals(err.get(JsonKeys.BlastDatabase).size(), 1);
//        }
//      }
//
//      @Nested
//      @DisplayName("and the config db flag is a blank value")
//      class Set2
//      {
//        @Test
//        @DisplayName("returns an error keyed with the JSON field name")
//        void test1() {
//          var err = new ErrorMap();
//          var con = new BlastConfig();
//
//          con.setBlastDatabase("    ");
//
//          BlastValidator.validateDbName(err, con, true);
//
//          assertTrue(err.containsKey(JsonKeys.BlastDatabase));
//          assertEquals(err.get(JsonKeys.BlastDatabase).size(), 1);
//        }
//      }
//
//      @Nested
//      @DisplayName("and the config db flag is a non-empty value")
//      class Set3
//      {
//        @Test
//        @DisplayName("returns no error")
//        void test1() {
//          var err = new ErrorMap();
//          var con = new BlastConfig();
//
//          con.setBlastDatabase("hi");
//
//          BlastValidator.validateDbName(err, con, true);
//
//          assertTrue(err.isEmpty());
//        }
//      }
//    }
//
//    @Nested
//    @DisplayName("When ext is false")
//    class ExtFalse
//    {
//
//      @Nested
//      @DisplayName("and the config db flag is unset")
//      class Unset
//      {
//        @Test
//        @DisplayName("returns an error keyed with the ToolOption field name")
//        void test1() {
//          var err = new ErrorMap();
//          var con = new BlastConfig();
//
//          BlastValidator.validateDbName(err, con, false);
//
//          assertTrue(err.containsKey(ToolOption.BlastDatabase.toString()));
//          assertEquals(err.get(ToolOption.BlastDatabase.toString()).size(), 1);
//        }
//      }
//
//      @Nested
//      @DisplayName("and the config db flag is an empty value")
//      class Set1
//      {
//        @Test
//        @DisplayName("returns an error keyed with the JSON field name")
//        void test1() {
//          var err = new ErrorMap();
//          var con = new BlastConfig();
//
//          con.setBlastDatabase("");
//
//          BlastValidator.validateDbName(err, con, true);
//
//          assertTrue(err.containsKey(ToolOption.BlastDatabase.toString()));
//          assertEquals(err.get(ToolOption.BlastDatabase.toString()).size(), 1);
//        }
//      }
//
//      @Nested
//      @DisplayName("and the config db flag is a blank value")
//      class Set2
//      {
//        @Test
//        @DisplayName("returns an error keyed with the JSON field name")
//        void test1() {
//          var err = new ErrorMap();
//          var con = new BlastConfig();
//
//          con.setBlastDatabase("    ");
//
//          BlastValidator.validateDbName(err, con, true);
//
//          assertTrue(err.containsKey(ToolOption.BlastDatabase.toString()));
//          assertEquals(err.get(ToolOption.BlastDatabase.toString()).size(), 1);
//        }
//      }
//
//      @Nested
//      @DisplayName("and the config db flag is a non-empty value")
//      class Set3
//      {
//        @Test
//        @DisplayName("returns no error")
//        void test1() {
//          var err = new ErrorMap();
//          var con = new BlastConfig();
//
//          con.setBlastDatabase("hi");
//
//          BlastValidator.validateDbName(err, con, true);
//
//          assertTrue(err.isEmpty());
//        }
//      }
//    }
//  }
//
//  @Nested
//  @DisplayName("#validateQuery(ErrorMap, BlastConfig, boolean)")
//  class ValidateQuery
//  {
//    @Nested
//    @DisplayName("When ext is true")
//    class ExtTrue
//    {
//      @Nested
//      @DisplayName("and the config query flag is unset")
//      class Unset
//      {
//        @Test
//        @DisplayName("returns an error keyed with the JSON field name")
//        void test1() {
//          var err = new ErrorMap();
//          var con = new BlastConfig();
//
//          BlastValidator.validateQuery(err, con, true);
//
//          assertTrue(err.containsKey(JsonKeys.Query));
//          assertEquals(err.get(JsonKeys.Query).size(), 1);
//        }
//      }
//
//      @Nested
//      @DisplayName("and the config query flag is set")
//      class Set
//      {
//        @Test
//        @DisplayName("returns no error")
//        void test1() {
//          var err = new ErrorMap();
//          var con = new BlastConfig();
//
//          con.setQuery(new File("Some.file"));
//
//          BlastValidator.validateQuery(err, con, true);
//
//          assertTrue(err.isEmpty());
//        }
//      }
//    }
//
//    @Nested
//    @DisplayName("When ext is false")
//    class ExtFalse
//    {
//      @Nested
//      @DisplayName("and the config query flag is unset")
//      class Unset
//      {
//        @Test
//        @DisplayName("returns an error keyed with the ToolOption field name")
//        void test1() {
//          var err = new ErrorMap();
//          var con = new BlastConfig();
//
//          BlastValidator.validateQuery(err, con, false);
//
//          assertTrue(err.containsKey(ToolOption.Query.toString()));
//          assertEquals(err.get(ToolOption.Query.toString()).size(), 1);
//        }
//      }
//
//      @Nested
//      @DisplayName("and the config query flag is set")
//      class Set
//      {
//        @Test
//        @DisplayName("returns no error")
//        void test1() {
//          var err = new ErrorMap();
//          var con = new BlastConfig();
//
//          con.setQuery(new File("Some.file"));
//
//          BlastValidator.validateQuery(err, con, false);
//
//          assertTrue(err.isEmpty());
//        }
//      }
//    }
//  }
//
//  @Nested
//  @DisplayName("#validateQueryLocation(ErrorMap, BlastConfig, boolean)")
//  class ValidateQueryLocation
//  {
//    @Nested
//    @DisplayName("When ext is true")
//    class ExtTrue
//    {
//      @Nested
//      @DisplayName("and the config query location field is unset")
//      class Unset
//      {
//        @Test
//        @DisplayName("returns no error")
//        void test1() {
//          var err = new ErrorMap();
//          var con = new BlastConfig();
//
//          BlastValidator.validateQueryLocation(err, con, true);
//
//          assertTrue(err.isEmpty());
//        }
//      }
//
//      @Nested
//      @DisplayName("and the config query location start > stop")
//      class Set1
//      {
//        @Test
//        @DisplayName("returns an error keyed with the JSON field name")
//        void test1() {
//          var err = new ErrorMap();
//          var con = new BlastConfig();
//
//          con.setQueryLoc(new QueryLocation(3, 1));
//
//          BlastValidator.validateQueryLocation(err, con, true);
//
//          assertTrue(err.containsKey(JsonKeys.QueryLocation));
//          assertEquals(err.get(JsonKeys.QueryLocation).size(), 1);
//        }
//      }
//
//      @Nested
//      @DisplayName("and the config query location start = stop")
//      class Set2
//      {
//        @Test
//        @DisplayName("returns an error keyed with the JSON field name")
//        void test1() {
//          var err = new ErrorMap();
//          var con = new BlastConfig();
//
//          con.setQueryLoc(new QueryLocation(3, 3));
//
//          BlastValidator.validateQueryLocation(err, con, true);
//
//          assertTrue(err.containsKey(JsonKeys.QueryLocation));
//          assertEquals(err.get(JsonKeys.QueryLocation).size(), 1);
//        }
//      }
//
//      @Nested
//      @DisplayName("and the config query location start < stop")
//      class Set3
//      {
//        @Test
//        @DisplayName("returns no error")
//        void test1() {
//          var err = new ErrorMap();
//          var con = new BlastConfig();
//
//          con.setQueryLoc(new QueryLocation(1, 3));
//
//          BlastValidator.validateQueryLocation(err, con, true);
//
//          assertTrue(err.isEmpty());
//        }
//      }
//    }
//
//    @Nested
//    @DisplayName("When ext is false")
//    class ExtFalse
//    {
//      @Nested
//      @DisplayName("and the config query location field is unset")
//      class Unset
//      {
//        @Test
//        @DisplayName("returns no error")
//        void test1() {
//          var err = new ErrorMap();
//          var con = new BlastConfig();
//
//          BlastValidator.validateQueryLocation(err, con, false);
//
//          assertTrue(err.isEmpty());
//        }
//      }
//
//      @Nested
//      @DisplayName("and the config query location start > stop")
//      class Set1
//      {
//        @Test
//        @DisplayName("returns an error keyed with the JSON field name")
//        void test1() {
//          var err = new ErrorMap();
//          var con = new BlastConfig();
//
//          con.setQueryLoc(new QueryLocation(3, 1));
//
//          BlastValidator.validateQueryLocation(err, con, false);
//
//          assertTrue(err.containsKey(ToolOption.QueryLocation.toString()));
//          assertEquals(err.get(ToolOption.QueryLocation.toString()).size(), 1);
//        }
//      }
//
//      @Nested
//      @DisplayName("and the config query location start = stop")
//      class Set2
//      {
//        @Test
//        @DisplayName("returns an error keyed with the JSON field name")
//        void test1() {
//          var err = new ErrorMap();
//          var con = new BlastConfig();
//
//          con.setQueryLoc(new QueryLocation(3, 3));
//
//          BlastValidator.validateQueryLocation(err, con, false);
//
//          assertTrue(err.containsKey(ToolOption.QueryLocation.toString()));
//          assertEquals(err.get(ToolOption.QueryLocation.toString()).size(), 1);
//        }
//      }
//
//      @Nested
//      @DisplayName("and the config query location start < stop")
//      class Set3
//      {
//        @Test
//        @DisplayName("returns no error")
//        void test1() {
//          var err = new ErrorMap();
//          var con = new BlastConfig();
//
//          con.setQueryLoc(new QueryLocation(1, 3));
//
//          BlastValidator.validateQueryLocation(err, con, false);
//
//          assertTrue(err.isEmpty());
//        }
//      }
//    }
//  }
}
